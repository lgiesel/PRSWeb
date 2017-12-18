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

import prs.domain.purchaserequest.PurchaseRequest;
import prs.domain.purchaserequest.PurchaseRequestLineItem;
import prs.domain.purchaserequest.PurchaseRequestLineItemRepository;
import prs.domain.vendor.Vendor;
import prs.util.PRSMaintenanceReturn;

@Controller
@RequestMapping(path="/PurchaseRequestLineItems")
public class PurchaseRequestLineItemController extends BaseController {
	
	@Autowired
	private PurchaseRequestLineItemRepository purchaseRequestLineItemRepository;	

	@GetMapping(path="/List")
	public @ResponseBody Iterable<PurchaseRequestLineItem> getAllPurchaseRequestLineItems() {
		return purchaseRequestLineItemRepository.findAll(); 
	}		
	
//	@GetMapping(path="/Get")
//	public @ResponseBody PurchaseRequestLineItem getPurchaseRequestLineItemById (@RequestParam int id) {
////	public @ResponseBody PurchaseRequestLineItem getPurchaseRequestLineItemById (@RequestParam PurchaseRequest pr) {
//				
//		PurchaseRequestLineItem v = purchaseRequestLineItemRepository.findOne(id);		
//		return v;
//	}	

	@GetMapping(path="/Get") // Map ONLY GET Requests
	public @ResponseBody List<PurchaseRequestLineItem> getPurchaseRequestLineItem (@RequestParam int id) {
		PurchaseRequestLineItem v = purchaseRequestLineItemRepository.findOne(id);
		return getReturnArray(v);
	}
	
//	@PostMapping(path="/Add") 
//	public @ResponseBody PurchaseRequestLineItem addNewPurchaseRequestLineItem (@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {     
//        purchaseRequestLineItemRepository.save(purchaseRequestLineItem);
//        System.out.println("PurchaseRequestLineItem saved:  "+purchaseRequestLineItem);
//        return purchaseRequestLineItem;
//    }	

	@PostMapping(path="/Add")
	public @ResponseBody PRSMaintenanceReturn addNewPurchaseRequestLineItem (@RequestBody PurchaseRequestLineItem purchaseRequestLI) {
		try {
			purchaseRequestLineItemRepository.save(purchaseRequestLI);
//			System.out.println("PurchaseRequestLineItem added:  "+purchaseRequestLI);
		}
		catch (Exception e) {
			purchaseRequestLI = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequestLI);
	}

	@PostMapping(path="/Update")
	public @ResponseBody PRSMaintenanceReturn updatePurchaseRequestLineItem (@RequestBody PurchaseRequestLineItem purchaseRequestLI) {
		try {
			purchaseRequestLI = purchaseRequestLineItemRepository.findOne(purchaseRequestLI.getId());
			purchaseRequestLineItemRepository.save(purchaseRequestLI);
			System.out.println("PurchaseRequestLineItem updated:  "+purchaseRequestLI);
		}
		catch (Exception e) {
			purchaseRequestLI = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequestLI);
	}		

	@GetMapping(path="/Delete")
	public @ResponseBody PRSMaintenanceReturn deletePurchaseRequestLineItem (@RequestParam int id) {
		PurchaseRequestLineItem purchaseRequestLI = null; //purchaseRequestLineItemRepository.findOne(id);;
		try {
			purchaseRequestLI = purchaseRequestLineItemRepository.findOne(id);
			purchaseRequestLineItemRepository.delete(purchaseRequestLI);
			System.out.println("PurchaseRequestLineItem deleted:  "+purchaseRequestLI);			
		} catch (Exception e ) {//EmptyResultDataAccessException exc) {
			purchaseRequestLI = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequestLI);
	}	

}

