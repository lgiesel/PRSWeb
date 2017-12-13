package prs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import prs.domain.purchaserequest.PurchaseRequest;
import prs.domain.purchaserequest.PurchaseRequestLineItem;
import prs.domain.purchaserequest.PurchaseRequestLineItemRepository;

@Controller
@RequestMapping(path="/PurchaseRequestLineItems")
public class PurchaseRequestLineItemController {
	
	@Autowired
	private PurchaseRequestLineItemRepository purchaseRequestLineItemRepository;	

	@GetMapping(path="/List")
	public @ResponseBody Iterable<PurchaseRequestLineItem> getAllPurchaseRequestLineItems() {
		return purchaseRequestLineItemRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get")
	public @ResponseBody PurchaseRequestLineItem getPurchaseRequestLineItemById (@RequestParam int id) {
//	public @ResponseBody PurchaseRequestLineItem getPurchaseRequestLineItemById (@RequestParam PurchaseRequest pr) {
				
		PurchaseRequestLineItem v = purchaseRequestLineItemRepository.findOne(id);		
		return v;
	}	

	@GetMapping(path="/Delete")
	public @ResponseBody String deletePurchaseRequestLineItemById (@RequestParam int id) {
//	public @ResponseBody String deletePurchaseRequestLineItemById (@RequestParam PurchaseRequest pr) {
		
		String msg = "";
		try {
			purchaseRequestLineItemRepository.delete(id);
			msg = "PurchaseRequestLineItem " + id + " deleted";			
			
		} catch (EmptyResultDataAccessException exc) {
			msg = "PurchaseRequestLineItem " + id + " not found; no delete";			
		}
		return msg;	
	}		
}
