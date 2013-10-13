package pl.indecoders.archetype.domain.product;

/**
 * The Enum Unit.
 * @author Mateusz
 */
public enum Unit {

	SZT, KG, MB, M2, L, OPAK, TONA, NIE_DOTYCZY;
	
	public String getUnit() {
		return toString();
	}
}
