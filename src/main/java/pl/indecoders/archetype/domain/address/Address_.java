package pl.indecoders.archetype.domain.address;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * The Class Address_.
 * @author Mateusz
 */
@StaticMetamodel(Address.class)
public class Address_ {

	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> homeNumber;
}
