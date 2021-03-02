package seedu.address.notification;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This is a email class where sending out the mail to user.
 * Credit goes to https://pepipost.com/tutorials/send-email-in-java-using-gmail-smtp/
 */
public class SendEmail {
    private String receiver;
    private String messages;

    /**
     * SendEmail a msg by using the function.
     */
    public SendEmail(String receiver, String message) {

        this.receiver = receiver;
        this.messages = message;
        sendEmail();

    }

    /**
     * Send a msg by using the function.
     */

    public void sendEmail() {
        // Recipient's email ID needs to be mentioned.
        String to = receiver;

        // Sender's email ID needs to be mentioned
        String from = "PoliceAddressBook@project.com";

        // Assuming you are sending email from through gmail smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("takeaways2017@gmail.com", "1QazxsW23Edc");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("The data");

            // Now set the actual message
            message.setText("Hi \n" + "This Task has been send you as a notification.\n\n" + messages
                    + "\n\n Best regards \n The Police Address Book");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
