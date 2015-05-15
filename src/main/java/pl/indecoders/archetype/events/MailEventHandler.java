package pl.indecoders.archetype.events;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;

@Component
public class MailEventHandler implements ApplicationListener<MailEvent> {
	
	@Autowired
	MailSender sender;
	
	@Autowired
	Logger logger;
	
	@Override
	public void onApplicationEvent(MailEvent evt) {
		Account accountRegistered = (Account) evt.getSource();
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(accountRegistered.getEmail());
		msg.setSubject("Activation mail");
		msg.setFrom("noreply@ecol.pl");
		msg.setText("You have been successfully registered");
		
		try {
			sender.send(msg);
		} catch (MailException ex) {
			System.out.println(ex);
			logger.debug("Can't send mail to " + accountRegistered.getEmail());
			logger.debug("Reason: " + ex.getMessage());
		}
	}

}