package pl.indecoders.archetype.navigation;

/**
 * The Class Navigator.
 * @author Mateusz
 */
public class Navigator {

	/* Exception messages */
	
	public static final String USERNAME_NOT_FOUND = "There is no user with given e-mail";

	/* Request mapping paths */
	
	public static final String MAIN_REDIRECT = "redirect:/";
	public static final String PRODUCT_GROUP_REDIRECT = "redirect:/product-groups";
	
	public static final String BAD_CREDENTIALS = "bad-credentials";
	public static final String HOME_PATH = "/";
	public static final String LOGIN_PATH = "/login";
	public static final String REGISTRATION_PATH = "/registration";
	
	public static final String NEW_PRODUCT_PATH = "/new-product";
	public static final String NEW_CUSTOMER_PATH = "/new-customer";
	public static final String PRODUCTS_LIST_PATH = "/products-list";
	public static final String CUSTOMERS_LIST_PATH = "/customers-list";
	public static final String PRODUCT_GROUPS_PATH = "/product-groups";
	
	/* Views */

	public static final String HOME_VIEW = "home";
	public static final String LOGIN_VIEW = "login";
	public static final String REGISTRATION_VIEW = "registration";
	
	public static final String NEW_PRODUCT_VIEW = "newProduct";
	public static final String NEW_CUSTOMER_VIEW = "newCustomer";
	public static final String CUSTOMERS_LIST_VIEW = "customersList";
	public static final String PRODUCTS_LIST_VIEW = "productsList";
	public static final String PRODUCT_GROUPS_VIEW = "productGroups";
	
	/* Attributes */
	
	public static final String REGISTER_ACCOUNT_FORM_ATTRIBUTE = "registerAccount";
	public static final String SIGNED_USER_ATTRIBUTE = "signedUser";
	public static final String PERSONAL_INFORMATIONS_FORM = "personalInfos";
	public static final String CURRENTLY_SIGNED = "signedUser";
	
	public static final String PRODUCT_COUNT_ATTRIBUTE = "productsCount";
	
	public static final String GROUPS_COUNT_ATTRIBUTE = "groupsCount";
	public static final String GROUP_FORM_ATTRIBUTE = "groupForm";
	public static final String GROUP_LIST_ATTRIBUTE = "groupList";
	public static final String EDITED_GROUP_ATTRIBUTE = "editedGroup";
	
	/* Keys */
	
	public static final String FINALIZE_REGISTRATION_KEY = "finalize";
	public static final String BAD_CREDENTIALS_KEY = "badCredentials";
	
	/* Reqular expressions */
	
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String NIP_REGEX = "^[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}$";
	public static final String REGON_REGEX = "^[0-9]{3}-[0-9]{3}-[0-9]{3}$";
	public static final String PHONE_MOBILE_INTERN_REGEX = "^[0-9]{2} [0-9]{3} [0-9]{3} [0-9]{3}$";
	public static final String PHONE_MOBILE_REGEX = "^[0-9]{3} [0-9]{3} [0-9]{3}$";
	public static final String PHONE_DOMESTIC_REGEX = "^[0-9]{2} [0-9]{3} [0-9]{2} [0-9]{2}$";
	public static final String POSTAL_CODE_REGEX = "^[0-9]{2}-[0-9]{3}$";
}
