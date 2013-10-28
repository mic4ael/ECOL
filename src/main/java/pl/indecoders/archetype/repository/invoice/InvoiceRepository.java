package pl.indecoders.archetype.repository.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.document.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

	@Query("select count(i) from Invoice i where i.owner = ?1")
	public Long countByOwner(final Account owner);

}
