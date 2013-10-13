package pl.indecoders.archetype.repository.accout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.indecoders.archetype.domain.account.Account;

/**
 * The Class AccountRepository.
 * @author Mateusz
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findByIdentifier(String identifier);
	public Account findByEmail(String email);
}
