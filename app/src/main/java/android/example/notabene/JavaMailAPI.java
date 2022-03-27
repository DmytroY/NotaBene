package android.example.notabene;

import android.content.Context;

import com.google.android.material.snackbar.Snackbar;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI {

    //public Boolean sendResult = false;
    private Session session;
    private Context context;
    private String email, subject, message, from, password;

    public JavaMailAPI(Context context, String email, String subject, String message, String from, String password) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.from = from;
        this.password = password;
    }

    public void send() {

        // define email server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        // authorize on email server
        //session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // compose and send email
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
            MainActivity.myHandler.sendEmptyMessage(MainActivity.STATUS_SENT);
        } catch (MessagingException e) {
            MainActivity.myHandler.sendEmptyMessage(MainActivity.STATUS_ERROR);
        }
    }
}
