package pl.indecoders.archetype.utils;

/**
 * The Class PaginationUtils.
 * 
 * @author Mateusz
 */
public class PaginationUtils {

	private Integer sizeOfPage;

	public PaginationUtils(Integer sizeOfPage) {
		this.sizeOfPage = sizeOfPage;
	}

	public Integer numberOfPages(long howManyResults) {
		if (howManyResults <= sizeOfPage) {
			return 1;
		}
		if (howManyResults % sizeOfPage == 0) {
			return (int) (howManyResults / sizeOfPage);
		}
		return (int) ((howManyResults / sizeOfPage) + 1);
	}
}
