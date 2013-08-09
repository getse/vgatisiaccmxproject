/*
 * SendEmail.java        01/03/2013
 *
 * Copyright (c) 2013 Centro de Competitividad México
 * Todos los Derechos Reservados.
 *
 * Este software es confidencial y de uso exclusivo del
 * Centro de Competitividad México.
 *
 */
package mx.com.vgati.framework.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mx.com.vgati.ccmx.vinculacion.dto.Contacto;

public class SendEmail {


	String d_email = "cotorrito.papirrin@gmail.com", d_password = "papirrin",
	d_host = "smtp.gmail.com", d_port = "587", m_to = "",
	m_subject = "", m_text = "";

	public SendEmail(String emailTo, String asunto, String mensaje,
			List<Contacto> emailsTo) {

		this.m_to = emailTo;
		this.m_subject = asunto;
		this.m_text = mensaje;

		Properties props = new Properties();
		props.put("mail.smtp.user", d_email);
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.debug", true);
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.SocketFactory");
		props.put("mail.smtp.socketFactory.fallback", false);

		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			MimeMessage msg = new MimeMessage(session);
			msg.setSubject(m_subject, "utf-8");
			msg.setContent(m_text, "text/html");
			msg.setText(m_text, "UTF-8", "html");
			if (m_to == null && emailsTo.size() > 0) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						d_email));
				for (Contacto c : emailsTo) {
					msg.addRecipient(Message.RecipientType.BCC,
							new InternetAddress(c.getCorreoElectronico()));
				}
			} else {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						m_to));
			}
			msg.setFrom(new InternetAddress(d_email, "CCMX"));
			Transport.send(msg);
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(d_email, d_password);
		}
	}
}