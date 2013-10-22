package pl.indecoders.archetype.security;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.repository.accout.AccountRepository;

/**
 * The Class SecurityUserContext.
 * @author Mateusz
 */
@Component
public class SecurityUserContext {

	@Autowired
	private AccountRepository accountRepository;
	
	public Account getSignedUser(Principal principal) {
		return accountRepository.findByEmail(principal.getName());
	}
	
	public Account getSignedUser() {
		return accountRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
