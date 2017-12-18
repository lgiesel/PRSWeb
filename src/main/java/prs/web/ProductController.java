package prs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import prs.domain.product.Product;
import prs.domain.product.ProductRepository;
import prs.domain.vendor.Vendor;
import prs.util.PRSMaintenanceReturn;

@Controller
@RequestMapping(path="/Products")
public class ProductController extends BaseController {
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping(path="/List")
	public @ResponseBody Iterable<Product> getAllProducts() {
		return productRepository.findAll(); 
	}		

	@GetMapping(path="/Get") 
	public @ResponseBody List<Product> getProduct (@RequestParam int id) {
		Product p = productRepository.findOne(id);
		return getReturnArray(p);
	}

	@PostMapping(path="/Add") 
	public @ResponseBody PRSMaintenanceReturn addNewProduct (@RequestBody Product product) {     
		try {
			productRepository.save(product);
			System.out.println("Product added:  "+product);
		} catch (Exception e) {
			product = null;
		}
        return PRSMaintenanceReturn.getMaintReturn(product);
    }

	@PostMapping(path="/Update") 
	public @ResponseBody PRSMaintenanceReturn updateProduct (@RequestBody Product product) {     
		try {
			productRepository.save(product);
			System.out.println("Product updated:  "+product);
		} catch (Exception e) {
			product = null;
		}
        return PRSMaintenanceReturn.getMaintReturn(product);
    }
	
	@GetMapping(path="/Delete")
	public @ResponseBody PRSMaintenanceReturn deleteProduct (@RequestParam int id) {
		Product product = null;
		try {
			product = productRepository.findOne(id);
			productRepository.delete(product);
		} catch (Exception e) {//EmptyResultDataAccessException exc) {
			product = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(product);	
	}	
}
