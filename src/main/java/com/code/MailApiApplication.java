package com.code;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailApiApplication.class, args);
		mailSending();
	}

	static void mailSending() {
		String username = "aasifali1088@gmail.com";
		String password = "ygpi urhx hfho vneu";
		String host = "smtp.gmail.com";
		int port = 587;

		// Recipient email addresses
		String[] recipients = { "dineshsoma666@gmail.com", "maheshsadhu5213@gmail.com" };

		// Email properties
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(username));

			for (String recipient : recipients) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			}

			message.setSubject("Your Subject Here");

			Multipart multipart = new MimeMultipart();

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Your email content here.");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			String attachmentFilePath = "C:\\Users\\aasif\\Downloads\\resumes\\aliaasif_resume.pdf"; // Replace with the
																										// actual path
																										// to your
			// resume file
			DataSource source = new FileDataSource(attachmentFilePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("aliaasif_resume.pdf");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Email sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
