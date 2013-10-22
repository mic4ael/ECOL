package pl.indecoders.archetype.utils;

import org.springframework.data.domain.Sort.Direction;

/**
 * The Class SortTranslationUtils.
 * @author Mateusz
 */
public class SortTranslationUtils {

	public static Direction translateDirection(final String dir) {
		if (dir.equalsIgnoreCase(Direction.ASC.toString())) {
			return Direction.ASC;
		} else {
			return Direction.DESC;
		}
	}
}
