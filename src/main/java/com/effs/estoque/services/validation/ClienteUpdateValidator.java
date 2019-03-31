package com.effs.estoque.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.dto.ClienteDto;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.resources.exceptions.FieldMessage;

/**
 * @author eduardosatyra
 *
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		Cliente c = this.clienteRepository.findByEmail(objDto.getEmail());
		if (c != null && !c.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "E-mail já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
