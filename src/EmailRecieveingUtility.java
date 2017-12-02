

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class EmailRecieveingUtility{

	 public static void sendEmail(String host, String port,String name,
	            final String userName, final String password,
	            String sender, String First_Name,String phone,String Email,String Msg) throws AddressException,
	            MessagingException, IOException {
	 
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	       // properties.put("mail.smtp.port", name);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	 
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message

	        Message msg = new MimeMessage(session);
	        
	        InternetAddress from = new InternetAddress(userName);
	        
	        msg.setFrom(from);
	        InternetAddress[] toAddresses = { new InternetAddress(sender) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(First_Name);
	        //msg.setFrom(from);
	        msg.setSentDate(new Date());
	        msg.setText(Msg);
	        // sends the e-mail
	        Transport.send(msg);
	    }
}

	 
	 
	 
	 
	
