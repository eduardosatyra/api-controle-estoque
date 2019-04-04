package com.effs.estoque.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

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

}
