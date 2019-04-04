package com.effs.estoque.services;

import org.springframework.mail.SimpleMailMessage;

import com.effs.estoque.domain.Pedido;

/**
 * @author eduardosatyra
 *
 */
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido p);

	void sendEmail(SimpleMailMessage msg);
}
