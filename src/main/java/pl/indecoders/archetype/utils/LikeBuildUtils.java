package pl.indecoders.archetype.utils;

/**
 * The Class LikeBuildUtils.
 * @author Mateusz
 */
public class LikeBuildUtils {

	public static String prepareLikeClause(String pattern) {
		return "%" + pattern.toLowerCase() + "%";
	}
}
