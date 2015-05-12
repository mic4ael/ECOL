package pl.indecoders.archetype.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;

@Component
public class MailEventPublisher implements ApplicationEventPublisherAware {
	
	private ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	public void publish(Account account) {
		this.publisher.publishEvent(new MailEvent(account));
	}

}
