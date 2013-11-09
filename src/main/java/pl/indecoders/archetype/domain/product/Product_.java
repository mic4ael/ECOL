package pl.indecoders.archetype.domain.product;

import java.math.BigDecimal;
import java.security.acl.Owner;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * The Class Product_.
 * @author Mateusz
 */
@StaticMetamodel(Product.class)
public class Product_ {

	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SingularAttribute<Product, String> productName;
	public static volatile SingularAttribute<Product, String> productSpecification;
	public static volatile SingularAttribute<Product, BigDecimal> taxAmount;
	public static volatile SingularAttribute<Product, Type> productType;
	public static volatile SingularAttribute<Product, Unit> productUnit;
	public static volatile SingularAttribute<Product, BigDecimal> basePrice;
	public static volatile SingularAttribute<Product, Owner> owner;
	public static volatile SingularAttribute<Product, Boolean> isVisible;
	public static volatile SingularAttribute<Product, ProductGroup> group;
}
