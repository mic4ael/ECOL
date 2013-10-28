package pl.indecoders.archetype.domain.customer;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.address.Address;

@StaticMetamodel(Customer.class)
public class Customer_ {

	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, Address> address;
	public static volatile SingularAttribute<Customer, String> nip;
	public static volatile SingularAttribute<Customer, String> email;
	public static volatile SingularAttribute<Customer, String> contactPhone;
	public static volatile SingularAttribute<Customer, String> faxPhone;
	public static volatile SingularAttribute<Customer, Boolean> isVisible;
	public static volatile SingularAttribute<Customer, Account> owner;
}
