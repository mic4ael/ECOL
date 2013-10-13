package pl.indecoders.archetype.domain.payment;

/**
 * The Enum DiscountType.
 * @author Mateusz
 */
public enum DiscountType {

	MONETISED, PERCENTAGE;
	
	public String getDiscountType() {
		return toString();
	}
}
