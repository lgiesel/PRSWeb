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

import prs.domain.vendor.Vendor;
import prs.domain.vendor.VendorRepository;

@Controller
@RequestMapping(path="/Vendors") 
public class VendorController {
	
	@Autowired
	private VendorRepository vendorRepository; 		

	@GetMapping(path="/List")
	public @ResponseBody Iterable<Vendor> getAllVendors() {
		return vendorRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get")
	public @ResponseBody Vendor getVendorById (@RequestParam int id) {
		
		Vendor v = vendorRepository.findOne(id);		
		return v;
	}	

	@GetMapping(path="/Delete")
	public @ResponseBody String deleteVendorById (@RequestParam int id) {
		
		String msg = "";
		try {
			vendorRepository.delete(id);
			msg = "Vendor " + id+ " deleted";			
			
		} catch (EmptyResultDataAccessException exc) {
			msg = "Vendor " + id+ " not found; no delete";			
		}
		return msg;
	}			
	
	@PostMapping(path="/Add") // Map ONLY POST Requests
	public @ResponseBody Vendor addNewVendor (@RequestBody Vendor vendor) {     
        vendorRepository.save(vendor);
        System.out.println("Vendor saved:  "+vendor);
        return vendor;
//		NEEDED FOR PR DATES        
//        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        user.setDateCreated(ts);
        //See PRSConsoleSprintBoog in Seans GITHUB to get timestamp example for handling date in CONTROLLERS
        //Date submitted - set at time of 'submit for review'
    }
}
