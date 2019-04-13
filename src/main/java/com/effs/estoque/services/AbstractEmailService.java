package com.effs.estoque.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.domain.Pedido;

/**
 * @author eduardosatyra
 *
 */
public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido p) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(p);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido p) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(p.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado, número: " + p.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(p.toString());
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String senha) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, senha);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String senha) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + senha);
		return sm;
	}
}
