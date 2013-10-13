package pl.indecoders.archetype.domain.payment;

/**
 * The Enum PaymentType.
 * @author Mateusz
 */
public enum PaymentType {

	TRANSFER, CASH;
	
	public String getPaymentType() {
		return toString();
	}
}
