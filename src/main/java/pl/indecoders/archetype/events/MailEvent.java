package pl.indecoders.archetype.events;

import org.springframework.context.ApplicationEvent;

public class MailEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public MailEvent(Object source) {
		super(source);
	}
	
	public String toString() {
		return "MailEvent";
	}
}
