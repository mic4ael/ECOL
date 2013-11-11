package pl.indecoders.archetype.controller.product;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static pl.indecoders.archetype.navigation.Navigator.EDIT_PRODUCT_PATH;
import static pl.indecoders.archetype.navigation.Navigator.GET_PRODUCTS_JSON;
import static pl.indecoders.archetype.navigation.Navigator.NEW_INVOICE_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_ATTRIBUTES;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_FORM_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_PATH;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_REDIRECT;
import static pl.indecoders.archetype.navigation.Navigator.NEW_PRODUCT_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST_PATH;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCTS_LIST_VIEW;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_COUNT_ATTRIBUTE;
import static pl.indecoders.archetype.navigation.Navigator.PRODUCT_PAGES_COUNT;
import static pl.indecoders.archetype.navigation.Navigator.REMOVE_PRODUCT_PATH;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.dto.product.NewProductAttributesDto;
import pl.indecoders.archetype.form.product.NewProductForm;
import pl.indecoders.archetype.repository.product.ProductRepository;
import pl.indecoders.archetype.security.SecurityUserContext;
import pl.indecoders.archetype.service.product.ProductService;
import pl.indecoders.archetype.specification.product.ProductSpecifications;
import pl.indecoders.archetype.utils.PaginationUtils;

/**
* The Class ProductController.
* @author Mateusz
*/
@Controller
@SessionAttributes( { NEW_PRODUCT_FORM_ATTRIBUTE } )
public class ProductController {
        private final int RESULTS_ON_PAGE = 10;
        private final String PRODUCT_SORT_DIR = "productSortDir";
        private final String PRODUCT_SORT_FIELD = "productSortField";
        
        @Autowired
        private ProductRepository productRepository;
        
        @Autowired
        private ProductService productService;
        
        @Autowired
        private SecurityUserContext userContext;
        
        @ModelAttribute(PRODUCT_COUNT_ATTRIBUTE)
        public Long countProducts() {
                return productRepository.countByOwnerAndIsVisible(userContext.getSignedUser(), true);
        }
        
        @ModelAttribute(PRODUCTS_LIST)
        public List<Product> getAllProducts() {
                return productRepository.findByOwnerAndIsVisible(userContext.getSignedUser(), true);
        }
        
        @ModelAttribute(PRODUCT_PAGES_COUNT)
        public Integer getAmountOfPages() {
                return new PaginationUtils(RESULTS_ON_PAGE).numberOfPages(productRepository.countByOwnerAndIsVisible(userContext.getSignedUser(), true));
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
                
                rs.addFlashAttribute("message", true);
                
                return NEW_PRODUCT_REDIRECT;
        }
        
        /* Edit */
        @RequestMapping(value = EDIT_PRODUCT_PATH + "/{id}/edit", method = GET)
        public String editProductGet(final Model model, @PathVariable Long id) {
                NewProductForm editForm = new NewProductForm();
                
                editForm = productService.prepareEditForm(id);
                model.addAttribute(NEW_PRODUCT_FORM_ATTRIBUTE, editForm);
                
                return NEW_PRODUCT_VIEW;
        }
        
        @RequestMapping(value = EDIT_PRODUCT_PATH + "/{id}/edit", method = POST)
        public String editProductPost(final @PathVariable Long id, @Valid @ModelAttribute(NEW_PRODUCT_FORM_ATTRIBUTE) NewProductForm form,
                        final BindingResult result) {
                
                if (result.hasErrors())
                        return NEW_PRODUCT_VIEW;
                
                
                return NEW_PRODUCT_VIEW;
        }
        
        /* Remove */
        
        @RequestMapping(value = REMOVE_PRODUCT_PATH + "/{id}/delete", method = GET)
        public String removeProductPost(@PathVariable Long id, final Model model, final RedirectAttributes ra) {
                
                if (id != null) {
                        productService.removeProduct(id);
                        ra.addFlashAttribute("message", true);
                } else {
                        ra.addFlashAttribute("message", false);
                }
                
                return "redirect:" + PRODUCTS_LIST_PATH + "/1";
        }
        
        /* Products list */
        
        @RequestMapping(value = PRODUCTS_LIST_PATH, method = GET)
        public String redirectToFirstPage(RedirectAttributes rs) {
                return "redirect:" + PRODUCTS_LIST_PATH + "/1";
        }
        
        @RequestMapping(value = PRODUCTS_LIST_PATH + "/{page}", method = GET)
        public String showProductsListPage(final HttpSession session, final Model model, @PathVariable Integer page) {
                
                if(session.getAttribute(PRODUCT_SORT_DIR) == null && session.getAttribute(PRODUCT_SORT_FIELD) == null) {
                        model.addAttribute(PRODUCTS_LIST, productService.getProductsPerPage(page - 1, RESULTS_ON_PAGE, userContext.getSignedUser()));
                        
                        return PRODUCTS_LIST_VIEW;
                }
                
                model.addAttribute(PRODUCTS_LIST, productService.getSortedAndPagedResults(page - 1, RESULTS_ON_PAGE, userContext.getSignedUser(),
                                (String) session.getAttribute(PRODUCT_SORT_FIELD), (String) session.getAttribute(PRODUCT_SORT_DIR)));
                
                return PRODUCTS_LIST_VIEW;
        }
        
        @RequestMapping(value = PRODUCTS_LIST_PATH + "/{page}", method = POST)
        public String sortResults(final Model model, @RequestParam("sortDir") String sortDir, @RequestParam("sortField") String sortField,
                        @PathVariable Integer page, final HttpSession session) {
                if (sortDir == null || sortDir.equals(""))
                        sortDir = "ASC";
                
                session.setAttribute(PRODUCT_SORT_DIR, sortDir);
                
                if(sortField == null || sortField.equals(""))
                        sortField = "productName";
                
                session.setAttribute(PRODUCT_SORT_FIELD, sortField);
                
                model.addAttribute(PRODUCTS_LIST, productService.getSortedAndPagedResults(page - 1, RESULTS_ON_PAGE, userContext.getSignedUser(), sortField, sortDir));
                
                return PRODUCTS_LIST_VIEW;
        }
        
        /* JSons */
        
    	@ResponseBody
    	@RequestMapping(value = NEW_INVOICE_PATH + "/" + GET_PRODUCTS_JSON, method = POST)
    	public List<Product> getProductsJson(@RequestBody String pattern) {
    		return productRepository.findAll(ProductSpecifications.containsPattern(pattern, userContext.getSignedUser()));
    	}
}