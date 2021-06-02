package ar.unrn.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.unrn.parcial.modelo.Factura;
import ar.unrn.parcial.modelo.Observer;
import ar.unrn.parcial.modelo.ValidarLitrosException;

public class Mail implements Observer {

	public Mail() {
	}

	public void envio(String mensaje, String destinatario) {
		// remitente
		String to = destinatario;
		// destinatario
		String from = "from@example.com";

		// usuario y clave que se obtiene desde Mailtrap
		final String username = "47bfb49aee2b31";
		final String password = "21ddc984cda1b3";
		String host = "smtp.mailtrap.io";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "2525");
		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Carga de combustible"); // Titulo del email
			message.setText(mensaje);
			// Send message
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void actualizar(Factura factura) throws ValidarLitrosException {
		String mensaje = "Fecha: " + factura.fechaDeFactura() + " \nTipo de combustible: "
				+ factura.verTipoDeCombustible() + " \nCantidad de Litros cargados: " + factura.cantidadDeLitros()
				+ " \nMonto Total: " + factura.obtenerMonto() + " \n¡Muchas gracias por su compra!";
		String destinatario = factura.verEmail();
		envio(mensaje, destinatario);

	}
}
