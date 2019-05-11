package com.effs.estoque.services.impl;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.effs.estoque.domain.Cidade;
import com.effs.estoque.domain.Cliente;
import com.effs.estoque.domain.Endereco;
import com.effs.estoque.domain.enums.Perfil;
import com.effs.estoque.domain.enums.TipoCliente;
import com.effs.estoque.dto.ClienteDto;
import com.effs.estoque.dto.ClienteNewDto;
import com.effs.estoque.dto.EnderecoDto;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.repositories.EnderecoRepository;
import com.effs.estoque.security.UserSS;
import com.effs.estoque.services.ClienteService;
import com.effs.estoque.services.ImageService;
import com.effs.estoque.services.S3Service;
import com.effs.estoque.services.UserService;
import com.effs.estoque.services.exception.AuthorizationException;
import com.effs.estoque.services.exception.DataIntegrityException;
import com.effs.estoque.services.exception.ObjectNotFoundException;

/**
 * @author eduardosatyra
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private int size;

	@Override
	public Cliente findComplete(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Optional<Cliente> cli = this.clienteRepository.findById(id);
		if (!cli.isPresent()) {
			throw new ObjectNotFoundException(
					"Cliente não encontrado pelo id: " + id + ", Objeto :" + Cliente.class.getName());
		}
		return cli.get();
	}
	
	@Override
	public ClienteDto find(Integer id) {
		Optional<Cliente> c = this.clienteRepository.findById(id);
		if (!c.isPresent()) {
			throw new ObjectNotFoundException(
					"Cliente não encontrado pelo id: " + id + ", Objeto :" + Cliente.class.getName());
		}
		return this.parseEntityToDto(c.get());
	}

	@Override
	public List<ClienteDto> findAll() {
		List<Cliente> cList = this.clienteRepository.findAll();
		if (cList.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cliente encontrado.");
		}
		List<ClienteDto> cDtoList = cList.stream().map(c -> new ClienteDto(c)).collect(Collectors.toList());
		return cDtoList;
	}

	@Override
	public Page<ClienteDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> pageList = this.clienteRepository.findAll(pageRequest);
		if (pageList.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cliente encontrada.");
		}
		Page<ClienteDto> pageDto = pageList.map(c -> new ClienteDto(c));
		return pageDto;
	}

	@Override
	public ClienteDto update(ClienteDto cDto) {
		Cliente c = this.clienteRepository.getOne(cDto.getId());
		if (c == null) {
			throw new ObjectNotFoundException("Nenhum cliente encontrado para o id: " + cDto.getId());
		}
		updateData(cDto, c);
		c = this.clienteRepository.saveAndFlush(c);
		return cDto = this.parseEntityToDto(c);
	}

	@Override
	public void delete(Integer id) {
		Cliente c = this.findComplete(id);
		try {
			this.clienteRepository.delete(c);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir porque há pedidos relacionadas.");
		}
	}

	@Transactional
	@Override
	public ClienteNewDto insert(ClienteNewDto cDto) {
		Cliente cli = this.parseClienteNewDtoToEntity(cDto);
		cli = this.clienteRepository.saveAndFlush(cli);
		this.enderecoRepository.saveAll(cli.getEndereco());
		cDto.setId(cli.getId());
		return cDto;
	}
	
	public Cliente parseClienteNewDtoToEntity(ClienteNewDto cn) {
		Cliente c = new Cliente(null, cn.getNome(), cn.getEmail(), cn.getCpfOuCnpj(), bCrypt.encode(cn.getSenha()), TipoCliente.toEnum(cn.getTipo()));		
		c.getTelefones().addAll(cn.getTelefones());		
		for (EnderecoDto eDto : cn.getEnderecos()) {
			Cidade cid = new Cidade(eDto.getCidade().getId(), null, null);
			Endereco end = new Endereco(null, eDto.getLogradouro(), eDto.getNumero(), eDto.getComplemento(), eDto.getBairro(), eDto.getCep(), cid, c);
			c.getEndereco().add(end);
		}
		return c;
	}

//	public Cliente parseDtoToEntity(ClienteDto cDto) {
//		return new Cliente(cDto.getId(), cDto.getNome(), cDto.getEmail(), null, null, null);
//	}

	public ClienteDto parseEntityToDto(Cliente c) {
		ClienteDto cDto = new ClienteDto(c);
		return cDto;
	}

	private void updateData(ClienteDto cDto, Cliente c) {
		c.setNome(cDto.getNome());
		c.setEmail(cDto.getEmail());
	}

	@Override
	public URI uploadProfilePicture(MultipartFile multipartFile) {		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado!");
		}		
		BufferedImage jpgImage = this.imageService.getJpgImageFromFile(multipartFile);
		jpgImage = this.imageService.cropSquare(jpgImage);
		jpgImage = this.imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		return this.s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
