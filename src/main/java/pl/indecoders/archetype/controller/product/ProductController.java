package pl.indecoders.archetype.controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static pl.indecoders.archetype.navigation.Navigator.CURRENTLY_SIGNED;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST_PATH;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_COUNT_ATTRIBUTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.indecoders.archetype.repository.product.ProductRepository;

/**
 * The Class ProductController.
 * @author Mateusz
 */
@Controller
@SessionAttributes(CURRENTLY_SIGNED)
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value = NEW_PRODUCT_PATH, method = GET)
	public String showNewProductPage(final Model model) {
		model.addAttribute(PRODUCT_COUNT_ATTRIBUTE, productRepository.count());
		return NEW_PRODUCT_VIEW;
	}
	
	@RequestMapping(value = PRODUCTS_LIST_PATH, method = GET)
	public String showProductsListPage() {
		return PRODUCTS_LIST_VIEW;
	}
}
