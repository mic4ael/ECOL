package pl.indecoders.archetype.controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.EDITED_GROUP_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.GROUPS_COUNT_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.GROUP_FORM_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.GROUP_LIST_ATTRIBUTE;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.form.product.EditionProductGroupForm;
import pl.indecoders.archetype.form.product.NewProductGroupForm;
import pl.indecoders.archetype.repository.product.ProductGroupRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.product.ProductGroupService;

/**
 * The Class ProductGroupsController.
 * 
 * @author Mateusz
 */
@Controller
@SessionAttributes({ GROUP_LIST_ATTRIBUTE })
public class ProductGroupsController {

	@Autowired
	private ProductGroupRepository productGroupRepository;

	@Autowired
	private ProductGroupService productGroupService;

	@Autowired
	private SecurityUserContext userContext;
	
	@ModelAttribute(GROUPS_COUNT_ATTRIBUTE) 
	public Long countProductGroups() {
		return productGroupRepository.countByOwner(userContext.getSignedUser());
	}
	
	@ModelAttribute(GROUP_LIST_ATTRIBUTE)
	public List<ProductGroup> sendGroups() {
		return productGroupRepository.findByOwner(userContext.getSignedUser());
	}
	
	@RequestMapping(value = PRODUCT_GROUPS_PATH, method = GET)
	public String showProductGroupsPage(final Model model, final HttpSession session) {
		model.addAttribute(GROUP_FORM_ATTRIBUTE, new NewProductGroupForm());
		return PRODUCT_GROUPS_VIEW;
	}

	/* Adding a new one */

	@RequestMapping(value = PRODUCT_GROUPS_PATH, method = POST)
	public String processProductGroupsPage(@Valid @ModelAttribute(GROUP_FORM_ATTRIBUTE) NewProductGroupForm form, final BindingResult result,
			final HttpSession session) {
		if (result.hasErrors()) {
			return PRODUCT_GROUPS_VIEW;
		}
		productGroupService.persistProductGroup(form, userContext.getSignedUser());
		return PRODUCT_GROUP_REDIRECT;
	}

	/* Removing */

	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/" + "{id}" + "/" + "delete", method = GET)
	public String deleteProductGroup(@PathVariable Long id) {
		productGroupService.removeProductGroup(id);
		return PRODUCT_GROUP_REDIRECT;
	}

	/* Editing */

	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/" + "{id}" + "/" + "edit", method = GET)
	public String editProductGroup(@PathVariable Long id, final Model model) {
		model.addAttribute(EDITED_GROUP_ATTRIBUTE, productGroupRepository.findOne(id));
		model.addAttribute(GROUP_FORM_ATTRIBUTE, new EditionProductGroupForm());
		
		return PRODUCT_GROUPS_VIEW;
	}

	@RequestMapping(value = PRODUCT_GROUPS_PATH + "/" + "{id}" + "/" + "edit", method = POST)
	public String processProductGroupEdition(@Valid @ModelAttribute(GROUP_FORM_ATTRIBUTE) EditionProductGroupForm form, final BindingResult result,
			@PathVariable Long id) {
		if(result.hasErrors()) {
			return PRODUCT_GROUPS_VIEW;
		}
		productGroupService.editProductGroup(id, form);
		return PRODUCT_GROUP_REDIRECT;
	}
}
