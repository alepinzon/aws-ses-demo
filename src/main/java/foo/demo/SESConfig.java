package foo.demo;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class SESConfig {

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {

      return AmazonSimpleEmailServiceClientBuilder.standard()
          .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
          .build();
    }

    @Bean
    public MailSender mailSender(final AmazonSimpleEmailService amazonSimpleEmailService) {
      return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
    }
}