package prs.web;

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

@Controller
@RequestMapping(path="/Products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping(path="/List")
	public @ResponseBody Iterable<Product> getAllProducts() {
		return productRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get")
	public @ResponseBody Product getProductById (@RequestParam int id) {
		
		Product v = productRepository.findOne(id);		
		return v;
	}	

	@GetMapping(path="/Delete")
	public @ResponseBody String deleteProductById (@RequestParam int id) {
		
		String msg = "";
		try {
			productRepository.delete(id);
			msg = "Product " + id+ " deleted";			
			
		} catch (EmptyResultDataAccessException exc) {
			msg = "Product " + id+ " not found; no delete";			
		}
		return msg;	
	}	
	
	@PostMapping(path="/Add") 
	public @ResponseBody Product addNewProduct (@RequestBody Product product) {     
        productRepository.save(product);
        System.out.println("Product saved:  "+product);
        return product;
    }	
}
