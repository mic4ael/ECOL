package pl.indecoders.archetype.controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_ATTRIBUTES;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_FORM_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_REDIRECT;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST_PATH;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_COUNT_ATTRIBUTE;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.dto.product.NewProductAttributesDto;
import pl.indecoders.archetype.form.product.NewProductForm;
import pl.indecoders.archetype.repository.product.ProductRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.product.ProductService;

/**
 * The Class ProductController.
 * @author Mateusz
 */
@Controller
@SessionAttributes( { NEW_PRODUCT_FORM_ATTRIBUTE } )
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SecurityUserContext userContext;
	
	@ModelAttribute(PRODUCT_COUNT_ATTRIBUTE)
	public Long countProducts() {
		return productRepository.countByOwner(userContext.getSignedUser());
	}
	
	@ModelAttribute(PRODUCTS_LIST)
	public List<Product> getAllProducts() {
		return productRepository.findByOwnerAndIsVisible(userContext.getSignedUser(), true);
	}
	
	@ModelAttribute(NEW_PRODUCT_ATTRIBUTES)
	public NewProductAttributesDto prepareAttributes() {
		return productService.prepareNewProductsAttributes(userContext.getSignedUser());
	}
	
	/* New product */
	
	@RequestMapping(value = NEW_PRODUCT_PATH, method = GET)
	public String showNewProductPage(final Model model, final HttpSession session) {
		model.addAttribute(NEW_PRODUCT_FORM_ATTRIBUTE, new NewProductForm());
		
		return NEW_PRODUCT_VIEW;
	}
	
	@RequestMapping(value = NEW_PRODUCT_PATH, method = POST)
	public String processNewProductPage(@Valid @ModelAttribute(NEW_PRODUCT_FORM_ATTRIBUTE) NewProductForm form, final BindingResult result, 
			RedirectAttributes rs, final HttpSession session) {
		if(result.hasErrors()) {
			return NEW_PRODUCT_VIEW;
		}
		
		productService.persistProduct(form, userContext.getSignedUser());
		return NEW_PRODUCT_REDIRECT;
	}
	
	/* Products list */
	
	@RequestMapping(value = PRODUCTS_LIST_PATH, method = GET)
	public String showProductsListPage() {
		return PRODUCTS_LIST_VIEW;
	}
}
