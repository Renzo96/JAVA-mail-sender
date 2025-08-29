package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.*;

public class MailSender {
    public static void main(String[] args) {
        final String remitente = "rensogonsales@gmail.com";
        final String clave = "fqwv gdwq owlf trtj";
        final String nombreTutor = "Renzo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        Map<String, String> destinatarios = Map.of(
                "lic.rsmg@gmail.com", "Sosa Renzo",
                "fercovichargento@gmail.com", "Agustin",
                "aguscaro88@gmail.com", "Agustin",
                "gaston.cejas@gmail.com", "Gaston",
                "jonathanjrios@outlook.com", "Jonathan",
                "rodriguezmaximilianoxavier@gmail.com", "Maximiliano",
                "juanmartinroqueszeballos@gmail.com", "Juan Martin"
        );

        for (Map.Entry<String, String> entry : destinatarios.entrySet()) {
            try {
                Message mensaje = new MimeMessage(session);
                mensaje.setFrom(new InternetAddress(remitente));
                mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(entry.getKey()));
                mensaje.setSubject("Saludos, " + entry.getValue());

                String cuerpo = String.format(
                        "Hola, mi nombre es %s, soy tu tutor de Programación 2.\n\n" +
                                "Desde la cátedra hemos notado que estás algo atrasado con la materia, " +
                                "por lo que te incentivamos a que trates de ponerte al día.\n\n" +
                                "Sabés que ante cualquier duda o inquietud nos podés consultar, no hay problema.\n\n" +
                                "Atte,\n%s", nombreTutor, nombreTutor
                );

                mensaje.setText(cuerpo);



                Transport.send(mensaje);
                System.out.println("Correo enviado a: " + entry.getValue());
            } catch (MessagingException e) {
                System.err.println("Error al enviar a " + entry.getValue() + ": " + e.getMessage());
            }
        }
    }
}
