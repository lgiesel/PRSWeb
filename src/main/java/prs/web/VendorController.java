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

import prs.domain.vendor.Vendor;
import prs.domain.vendor.VendorRepository;
import prs.util.PRSMaintenanceReturn;

@Controller
@RequestMapping(path="/Vendors") 
public class VendorController extends BaseController {
	
	@Autowired
	private VendorRepository vendorRepository; 		

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
		Vendor vendor = null; //vendorRepository.findOne(id);;
		try {
			vendor = vendorRepository.findOne(id);
			vendorRepository.delete(vendor);
			System.out.println("Vendor deleted:  "+vendor);			
		} catch (Exception e ) {//EmptyResultDataAccessException exc) {
			vendor = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(vendor);
	}	
}
