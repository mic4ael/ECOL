package pl.indecoders.archetype.controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_EDITED_GROUP_ID;
import static pl.indecoders.archetype.navigation.Navigator.EDITED_GROUP_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.GROUPS_COUNT_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.GROUPS_PAGES_COUNT;
import static pl.indecoders.archetype.navigation.Navigator.GROUP_FORM_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.GROUP_LIST_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.GROUP_REDIRECT_ON_ERROR;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_GROUPS_PATH;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_GROUPS_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_GROUP_REDIRECT;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.form.product.EditionProductGroupForm;
import pl.indecoders.archetype.form.product.NewProductGroupForm;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.product.ProductGroupService;
import pl.indecoders.archetype.utils.PaginationUtils;

/**
 * The Class ProductGroupsController.
 * 
 * @author Mateusz
 */
@Controller
@SessionAttributes({ GROUP_LIST_ATTRIBUTE })
public class ProductGroupsController {
	private final Integer RESULTS_ON_PAGE = 6;
	private final String PRODUCT_GROUP_SORT_FIELD = "productGroupSortField";
	private final String PRODUCT_GROUP_SORT_DIR = "productGroupSortDir";
	
	@Autowired
	private ProductGroupRepository productGroupRepository;

	@Autowired
	private ProductGroupService productGroupService;

	@Autowired
	private SecurityUserContext userContext;
	
	@ModelAttribute(GROUPS_COUNT_ATTRIBUTE) 
	public Long countProductGroups() {
		return productGroupRepository.countByOwnerAndIsActive(userContext.getSignedUser(), true);
	}
	
	@ModelAttribute(GROUPS_PAGES_COUNT)
	public Integer groupsPageCount() {
		return new PaginationUtils(RESULTS_ON_PAGE).numberOfPages(productGroupRepository.countByOwnerAndIsActive(userContext.getSignedUser(), true));
	}
	
	@ModelAttribute(GROUP_LIST_ATTRIBUTE)
	public List<ProductGroup> sendGroups() {
		return productGroupRepository.findByOwnerAndIsActive(userContext.getSignedUser(), true);
	}
	
	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/{page}", method = GET)
	public String showProductGroupsPage(final Model model, final HttpSession session, @PathVariable Integer page) {
		model.addAttribute(GROUP_FORM_ATTRIBUTE, new NewProductGroupForm());
		
		session.removeAttribute(CURRENTLY_EDITED_GROUP_ID);
		
		if (session.getAttribute(PRODUCT_GROUP_SORT_DIR) == null && session.getAttribute(PRODUCT_GROUP_SORT_FIELD) == null) {
			model.addAttribute(GROUP_LIST_ATTRIBUTE, productGroupService.getProductGroupsPerPage(userContext.getSignedUser(), page - 1, RESULTS_ON_PAGE));
		} else {
			model.addAttribute(GROUP_LIST_ATTRIBUTE, productGroupService.getSortedAndPagedProducts(userContext.getSignedUser(), page - 1, RESULTS_ON_PAGE,
					(String) session.getAttribute(PRODUCT_GROUP_SORT_DIR), (String) session.getAttribute(PRODUCT_GROUP_SORT_FIELD)));	
		}
		
		return PRODUCT_GROUPS_VIEW;
	}
	
	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/{page}", method = POST)
	public String sortResults(@ModelAttribute (GROUP_FORM_ATTRIBUTE) NewProductGroupForm form, 
			final HttpSession session, final Model model, @PathVariable Integer page, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir) {
		
		if (sortField == null || sortField.equals(""))
			sortField = "name";
		
		session.setAttribute(PRODUCT_GROUP_SORT_FIELD, sortField);
		
		if (sortDir == null || sortField.equals(""))
			sortDir = "ASC";
		
		session.setAttribute(PRODUCT_GROUP_SORT_DIR, sortDir);
		
		
		model.addAttribute(GROUP_LIST_ATTRIBUTE, productGroupService.getSortedAndPagedProducts(userContext.getSignedUser(), page - 1,
				RESULTS_ON_PAGE, sortDir, sortField));
		
		return PRODUCT_GROUPS_VIEW;
	}

	/* Adding a new one */

	@RequestMapping(value = PRODUCT_GROUPS_PATH, method = POST)
	public String processProductGroupsPage(@Valid @ModelAttribute(GROUP_FORM_ATTRIBUTE) NewProductGroupForm form, final BindingResult result,
			final HttpSession session, RedirectAttributes ra) {
		
		if (result.hasErrors()) {
			return  PRODUCT_GROUPS_VIEW;
		}
		
		productGroupService.persistProductGroup(form, userContext.getSignedUser());
		
		ra.addFlashAttribute("message", true);
		return PRODUCT_GROUP_REDIRECT + "/1";
	}

	/* Removing */

	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/" + "{id}" + "/" + "delete", method = GET)
	public String deleteProductGroup(@PathVariable Long id, RedirectAttributes ra) {
		productGroupService.removeProductGroup(id);
		
		ra.addFlashAttribute("message", true);
		
		return PRODUCT_GROUP_REDIRECT + "/1";
	}

	/* Editing */

	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/" + "{id}" + "/" + "edit", method = GET)
	public String editProductGroup(@PathVariable Long id, final Model model, final HttpSession session) {
		model.addAttribute(EDITED_GROUP_ATTRIBUTE, productGroupRepository.findOne(id));
		model.addAttribute(GROUP_FORM_ATTRIBUTE, new EditionProductGroupForm());
		
		session.setAttribute(CURRENTLY_EDITED_GROUP_ID, id);
		
		return PRODUCT_GROUPS_VIEW;
	}

	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/" + "{id}" + "/" + "edit", method = POST)
	public String processProductGroupEdition(@Valid @ModelAttribute(GROUP_FORM_ATTRIBUTE) EditionProductGroupForm form, final BindingResult result,
			@PathVariable Long id, RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			if (result.getFieldError("name") != null)
				ra.addFlashAttribute("errorname", result.getFieldError("name").getDefaultMessage());
			
			if (result.getFieldError("specification") != null)
				ra.addFlashAttribute("specerror", result.getFieldError("specification").getDefaultMessage());
			
			return String.format(GROUP_REDIRECT_ON_ERROR, new Object[] {id});
		}
		
		productGroupService.editProductGroup(id, form);
		return PRODUCT_GROUP_REDIRECT + "/1";
	}
}
