package mx.com.vgati.framework.util;



import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message; 
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.InternetAddress; 


public class SendEmail{

	String  d_email = "ad.tra.ccmx@gmail.com",
	d_password = "tractoras2012",
	d_host = "smtp.gmail.com",
	d_port  = "465",
	m_to = "",
	m_subject = "",
	m_text = "";

	public SendEmail(String emailTo, String asunto, String mensaje){

		this.m_to=emailTo;
		this.m_subject=asunto;
		this.m_text=mensaje;

		Properties props = new Properties();
		props.put("mail.smtp.user", d_email);
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);
		props.put("mail.smtp.starttls.enable",true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.debug", true);
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", false);

		try
		{
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			MimeMessage msg = new MimeMessage(session);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
			msg.setSubject(m_subject);
			msg.setContent(m_text, "text/html");
			Transport.send(msg);
		}
		catch (Exception mex)
		{
			mex.printStackTrace();
		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator{

		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(d_email, d_password);
		}
	}
}