package com.effs.estoque.services;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	@Autowired
	private Environment env;

	@Override
	public void sendEmail(SimpleMailMessage msg) {

		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			log.info("Enviando e-mail pelo profile de TESTE...");
			mailSender.send(msg);
			log.info("E-mail enviado pelo profile de TESTE.");
		} else if (Arrays.asList(env.getActiveProfiles()).contains("dev")) {
			log.info("Enviando e-mail pelo profile de DEV...");
			mailSender.send(msg);
			log.info("E-mail enviado pelo profile de DEV.");
		} else {
			log.info("Enviando e-mail...");
			mailSender.send(msg);
			log.info("E-mail enviado.");
		}
	}
}
