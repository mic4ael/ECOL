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
	public static final String NEW_CUSTOMER_REDIRECT = "redirect:/new-customer";
	
	public static final String BAD_CREDENTIALS = "bad-credentials";
	public static final String HOME_PATH = "/";
	public static final String LOGIN_PATH = "/login";
	public static final String REGISTRATION_PATH = "/registration";
	
	public static final String NEW_PRODUCT_PATH = "/new-product";
	public static final String NEW_CUSTOMER_PATH = "/new-customer";
	public static final String PRODUCTS_LIST_PATH = "/products-list";
	public static final String EDIT_PRODUCT_PATH = "/products";
	public static final String REMOVE_PRODUCT_PATH = "/products";
	public static final String CUSTOMERS_LIST_PATH = "/customers-list";
	public static final String PRODUCT_GROUPS_PATH = "/product-groups";
	public static final String GROUP_REDIRECT_ON_ERROR = "redirect:/product-groups/%d/edit";
	public static final String NEW_INVOICE_PATH = "/new-invoice";
	public static final String NEW_INVOICE_PRODUCTS_PATH = "/products";
	public static final String INVOICES_LIST_PATH = "/invoices-list";
	
	public static final String IS_CUSTOMER_AVAILABLE="/isCustomerAvailable";
	public static final String GET_CUSTOMERS_JSON = "customersJson";
	public static final String GET_PRODUCTS_JSON = "productsJson";
	public static final String GET_PROPER_CUSTOMER_JSON = "customerJson";
	
	public static final String INVOICE_DETAILS_PATH = "/invoice-details";
	public static final String SOLD_REPORT_PATH = "/sold-report";
	public static final String PDF_GENERATE_PATH = "/generate-pdf";
	
	/* Views */

	public static final String HOME_VIEW = "home";
	public static final String LOGIN_VIEW = "login";
	public static final String REGISTRATION_VIEW = "registration";
	public static final String PDF_VIEW = "InvoicePdfGenerator";
	public static final String NEW_PRODUCT_VIEW = "newProduct";
	public static final String NEW_CUSTOMER_VIEW = "newCustomer";
	public static final String CUSTOMERS_LIST_VIEW = "customersList";
	public static final String PRODUCTS_LIST_VIEW = "productsList";
	public static final String PRODUCT_GROUPS_VIEW = "productGroups";
	public static final String EDIT_CUSTOMER_VIEW = "editCustomer";
	public static final String EDIT_PRODUCT_VIEW = "editProduct";
	public static final String NEW_INVOICE_VIEW = "newInvoice";
	public static final String NEW_INVOICE_PRODUCTS_VIEW = "newInvoiceStep2";
	public static final String INVOICES_LIST_VIEW = "invoicesList";
	
	public static final String INVOICE_DETAILS_VIEW = "invoiceDetails";
	
	public static final String SOLD_REPORT_VIEW = "soldReport";
	
	/* Attributes */
	
	public static final String REGISTER_ACCOUNT_FORM_ATTRIBUTE = "registerAccount";
	public static final String SIGNED_USER_ATTRIBUTE = "signedUser";
	public static final String PERSONAL_INFORMATIONS_FORM = "personalInfos";
	public static final String CURRENTLY_SIGNED = "signedUser";
	
	public static final String PRODUCT_COUNT_ATTRIBUTE = "productsCount";
	public static final String PRODUCTS_LIST = "productsList";
	
	public static final String GROUPS_COUNT_ATTRIBUTE = "groupsCount";
	public static final String GROUP_FORM_ATTRIBUTE = "groupForm";
	public static final String GROUP_LIST_ATTRIBUTE = "groupList";
	public static final String GROUPS_PAGES_COUNT = "groupPagesCount";
	public static final String PRODUCT_PAGES_COUNT = "productPagesCount";
	public static final String EDITED_GROUP_ATTRIBUTE = "editedGroup";
	public static final String CURRENTLY_EDITED_GROUP_ID = "currentlyEditedGroupId";
	
	public static final String NEW_PRODUCT_REDIRECT = "redirect:/new-product";
	public static final String NEW_PRODUCT_FORM_ATTRIBUTE = "newProduct";
	public static final String EDIT_PRODUCT_FORM_ATTRIBUTE = "editProductForm";
	public static final String NEW_PRODUCT_ATTRIBUTES = "productAttributes";
	public static final String CURRENTLY_EDITED_PRODUCT_ID = "currentlyEditedProductId";
	
	public static final String NEW_CUSTOMER_FORM_ATTRIBUTE = "newCustomer";
	public static final String CUSTOMER_COUNT_ATTRIBUTE = "customersCount";
	public static final String CUSTOMER_LIST_ATTRIBUTE = "customersList";
	public static final String CUSTOMER_LIST_SORT_ATTRIBUTE = "customersSort";
	public static final String CUSTOMER_LIST_DIR_ATTRIBUTE = "customersDir";
	public static final String CUSTOMER_LIST_PAGES_ATTRIBUTE = "customersPages";
	public static final String EDITED_CUSTOMER_ATTRIBUTE = "editedCustomer";
	
	public static final String OPERATION_SUCCESS = "success";
	
	public static final String NEW_INVOICE_FORM = "newInvoiceForm";
	public static final String SESSION_STORED_INVOICE = "sessionStoredInvoice";
	public static final String SESSION_STORED_PRODUCTS = "sessionStoredProducts";
	
	public static final String NEW_INVOICE_ATTRIBUTES = "newInvoice";
	public static final String NEW_INVOICE_NUMBER_ATTRIBUTE = "invoiceNumber";
	
	public static final String PRODUCT_ROW_FORM_ATTRIBUTE = "productRow";
	
	public static final String INVOICE_DETAILS_ATTRIBUTE = "invoiceDetails";
	public static final String INVOICE_ATTRIBUTE = "invoices";
	public static final String INVOICE_LIST_SORT_ATTRIBUTE = "invoicesSort";
	public static final String INVOICE_LIST_DIR_ATTRIBUTE = "invoicesDir";
	public static final String INVOICES_LIST_PAGES_ATTRIBUTE = "invoicesPages";
	
	public static final String SOLD_REPORT_ATTRIBUTE = "soldForm";
	public static final String REPORT_ATTRIBUTE = "report";
	
	/* Keys */
	
	public static final String NEXT_KEY = "next";
	public static final String BAD_CREDENTIALS_KEY = "badCredentials";
	public static final String FINALIZE_REGISTRATION_KEY = "finalize";
	
	/* Reqular expressions */
	
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String NIP_REGEX = "^[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}$";
	public static final String REGON_REGEX = "^[0-9]{3}-[0-9]{3}-[0-9]{3}$";
	public static final String PHONE_MOBILE_INTERN_REGEX = "^[0-9]{2}\\s*[0-9]{3}\\s*[0-9]{3}\\s*[0-9]{3}$";
	public static final String PHONE_MOBILE_REGEX = "^[0-9]{3}\\s*[0-9]{3}\\s*[0-9]{3}$";
	public static final String PHONE_DOMESTIC_REGEX = "^[0-9]{2}\\s*[0-9]{3}\\s*[0-9]{2}\\s*[0-9]{2}$";
	public static final String POSTAL_CODE_REGEX = "^[0-9]{2}-[0-9]{3}$";
	public static final String BANK_REGEX = "^[0-9]{2}\\s*[0-9]{4}\\s*[0-9]{4}\\s*[0-9]{4}\\s*[0-9]{4}\\s*[0-9]{4}\\s*[0-9]{4}$";
}
