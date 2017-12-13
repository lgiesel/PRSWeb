package prs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import prs.domain.purchaserequest.PurchaseRequest;
import prs.domain.purchaserequest.PurchaseRequestRepository;

@Controller
@RequestMapping(path="/PurchaseRequests")
public class PurchaseRequestController {
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;	

	@GetMapping(path="/List")
	public @ResponseBody Iterable<PurchaseRequest> getAllPurchaseRequests() {
		return purchaseRequestRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get")
	public @ResponseBody PurchaseRequest getPurchaseRequestById (@RequestParam int id) {
		
		PurchaseRequest v = purchaseRequestRepository.findOne(id);		
		return v;
	}	

	@GetMapping(path="/Delete")
	public @ResponseBody String deletePurchaseRequestById (@RequestParam int id) {
		
		String msg = "";
		try {
			purchaseRequestRepository.delete(id);
			msg = "PurchaseRequest " + id+ " deleted";			
			
		} catch (EmptyResultDataAccessException exc) {
			msg = "PurchaseRequest " + id+ " not found; no delete";			
		}
		return msg;	
	}		
}
