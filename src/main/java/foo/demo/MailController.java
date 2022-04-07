package foo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Value(value = "${identity.email}")
    private String from;

    @Value(value = "${email.to}")
    private String to;

    @Value(value = "${identity.domain}")
    private String domain;

    @Autowired
    private MailSender emailSender;

    @GetMapping(path = "/send/email-identity")
    public String sendMailByEmailIdentity(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Email Identity POC");
        simpleMailMessage.setText("AWS SES - Email Identity POC");

        emailSender.send(simpleMailMessage);

        return "AWS SES - Email Identity POC";
    }

    @GetMapping(path = "/send/domain-identity")
    public String sendMailByDomainIdentity(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hello@"+domain);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Domain Identity POC");
        simpleMailMessage.setText("AWS SES - Domain Identity POC");

        emailSender.send(simpleMailMessage);

        return "AWS SES - Domain Identity POC";
    }
}
