package com.effs.estoque.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author eduardosatyra
 *
 */
public class SmtpEmailService extends AbstractEmailService {

	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		log.info("Enviando e-mail...");
		mailSender.send(msg);
		log.info("E-mail enviado.");
	}
}
