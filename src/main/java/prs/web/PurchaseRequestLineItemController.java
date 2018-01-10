package prs.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import prs.domain.purchaserequest.PurchaseRequest;
import prs.domain.purchaserequest.PurchaseRequestLineItem;
import prs.domain.purchaserequest.PurchaseRequestLineItemRepository;
import prs.domain.purchaserequest.PurchaseRequestRepository;
import prs.domain.vendor.Vendor;
import prs.util.PRSMaintenanceReturn;
//import util.DBUtil;

@CrossOrigin
@Controller
@RequestMapping(path="/PurchaseRequestLineItems")
public class PurchaseRequestLineItemController extends BaseController {
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;
	
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
			purchaseRequestLineItemRepository.save(purchaseRequestLI);
			recalculatePRTotal(purchaseRequestLI.getPurchaseRequestID());
			System.out.println("PurchaseRequestLineItem updated:  "+purchaseRequestLI);
		}
		catch (Exception e) {
			purchaseRequestLI = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequestLI);
	}		

	@GetMapping(path="/Delete")
	public @ResponseBody PRSMaintenanceReturn deletePurchaseRequestLineItem (@RequestParam int id) {
		PurchaseRequestLineItem purchaseRequestLineItem = purchaseRequestLineItemRepository.findOne(id);
		try {
			purchaseRequestLineItemRepository.delete(purchaseRequestLineItem);
			System.out.println("PurchaseRequestLineItem updated:  "+purchaseRequestLineItem);
		} catch (Exception e ) {
			purchaseRequestLineItem = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequestLineItem);
	}	

	public void recalculatePRTotal (int prID) {
		double total = 0.0;
		PurchaseRequest pr = purchaseRequestRepository.findOne(prID);		
		List<PurchaseRequestLineItem> prlis = new ArrayList<PurchaseRequestLineItem>();
		
		prlis = (List<PurchaseRequestLineItem>) purchaseRequestLineItemRepository.findAll();
		
		for (PurchaseRequestLineItem li : prlis) {
			if (li.getPurchaseRequestID() == pr.getId()) {
				total += li.getQuantity(); //NEED PRICE
				System.out.println("PRLI ID counted=" + li.getId() + "qty= " + li.getQuantity());				
			}			
		}					
//		pr.setTotal(total);
		
		//Call recalc and pass in the PR
		//Cal prliRep.findAll to get all prlis back
		//Loop thru prlis returned and find only those that match the PR 
		//Find price for li product id - getProductID? prli.ProductID
		//Multiple price x qty for li total only for PR
		//Update the PR total
	

		
//		@GetMapping(path="/Get") 
//		public @ResponseBody List<Product> getProduct (@RequestParam int id) {
//			Product p = productRepository.findOne(id);
//			return getReturnArray(p);
//		}		
		purchaseRequestRepository.save(pr);
	}
	


}	


