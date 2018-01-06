package prs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import prs.domain.product.Product;
import prs.domain.product.ProductRepository;
import prs.domain.vendor.Vendor;
import prs.domain.vendor.VendorRepository;
import prs.util.PRSMaintenanceReturn;

@CrossOrigin	
@Controller
@RequestMapping(path="/Vendors") 
public class VendorController extends BaseController {
	
	@Autowired
	private VendorRepository vendorRepository; 		
	
	@Autowired
	private ProductRepository productRepository;	

	@GetMapping(path="/List")
	public @ResponseBody Iterable<Vendor> getAllVendors() {
		return vendorRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get") // Map ONLY GET Requests
	public @ResponseBody List<Vendor> getVendor (@RequestParam int id) {
		Vendor v = vendorRepository.findOne(id);
		return getReturnArray(v);
	}

	@PostMapping(path="/Add") // Map ONLY GET Requests
	public @ResponseBody PRSMaintenanceReturn addNewVendor (@RequestBody Vendor vendor) {
		try {
			vendorRepository.save(vendor);
			System.out.println("Vendor added:  "+vendor);
		}
		catch (Exception e) {
			vendor = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(vendor);
	}
	
	@PostMapping(path="/Update") // Map ONLY GET Requests
	public @ResponseBody PRSMaintenanceReturn updateVendor (@RequestBody Vendor vendor) {
		try {
			vendorRepository.save(vendor);
			System.out.println("Vendor updated:  "+vendor);
		}
		catch (Exception e) {
			vendor = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(vendor);
	}	
	
	@GetMapping(path="/Delete")
	public @ResponseBody PRSMaintenanceReturn deleteVendor (@RequestParam int id) {
		Vendor vendor = vendorRepository.findOne(id);
		try {
			vendorRepository.delete(vendor);
			System.out.println("Vendor deleted:  "+vendor);			
		} catch (Exception e ) {
			vendor = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(vendor);
	}	
	
//	@GetMapping(path="/Summary")
//	public @ResponseBody List<VendorSummary> getVendorSummary(@RequestParam int id) {
//		// This returns a JSON or XML with the users
//		VendorSummary vs = new VendorSummary();
//		Vendor vendor = vendorRepository.findOne(id);
//		vs.setVendor(vendor);
//		List<Product> products = productRepository.findAllByVendorID(vendor.getId());
//		vs.setProducts(products);
//		return getReturnArray(vs);
//	}	
	
	/*
	 * This method tests a 'find' method that excludes the id passed in.
	 * Hint:  this would be useful for finding PRs NOT assigned to the 
	 * 			id passed in.
	 */
	@GetMapping(path="/GetNot")
	public @ResponseBody List<Product> getVendorNot(@RequestParam int id) {
		// This returns a JSON or XML with the users
		List<Product> products = productRepository.findAllByVendorIDNot(id);
		return products;
	}	
}
