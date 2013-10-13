package pl.indecoders.archetype.service.account;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static pl.indecoders.archetype.domain.account.RoleType.USER;
import static pl.indecoders.archetype.navigation.Navigator.USERNAME_NOT_FOUND;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.account.Role;
import pl.indecoders.archetype.domain.account.RoleType;
import pl.indecoders.archetype.form.account.RegisterAccountForm;
import pl.indecoders.archetype.repository.accout.AccountRepository;

public class UserService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(username);
		if (account == null) {
			throw new UsernameNotFoundException(USERNAME_NOT_FOUND);
		}
		return createUser(account);
	}

	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}

	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, createAuthority(account));
	}

	private User createUser(Account account) {
		return new User(account.getEmail(), account.getPassword(), createAuthority(account));
	}

	/* Roles chain */
	
	private List<GrantedAuthority> createAuthority(Account account) {
		return account.getRoles() != null ? grantAuthorities(account) : grantBasicAuthorities();
	}

	private List<GrantedAuthority> grantAuthorities(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Role> roles = account.getRoles();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getRole())));
		}
		return Collections.unmodifiableList(authorities);
	}

	private List<GrantedAuthority> grantBasicAuthorities() {
		List<GrantedAuthority> basicAuthorities = new ArrayList<GrantedAuthority>();
		basicAuthorities.add(new SimpleGrantedAuthority(String.valueOf(RoleType.USER)));
		Collections.unmodifiableList(basicAuthorities);
		return basicAuthorities;
	}
	
	/* Creating account chain */
	
	public Account registerNewAccount(RegisterAccountForm form) {
		
		ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
		
		Account account = new Account();
		account.setEmail(form.getEmail());
		account.setPassword(passwordEncoder.encodePassword(form.getPassword(), null));
		account.setRoles(prepareBasicRoles());
		account.setIdentifier(randomAlphanumeric(10));
		return accountRepository.save(account);
	}

	private List<Role> prepareBasicRoles() {
		List<Role> basicRoles = new LinkedList<>();
		basicRoles.add(new Role(USER));
		return basicRoles;
	}
}
