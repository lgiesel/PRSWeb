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
import prs.domain.product.ProductRepository;
import prs.domain.purchaserequest.PurchaseRequest;
import prs.domain.purchaserequest.PurchaseRequestLineItem;
import prs.domain.purchaserequest.PurchaseRequestLineItemRepository;
import prs.domain.purchaserequest.PurchaseRequestRepository;
import prs.web.PurchaseRequestController;

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

	@Autowired
	private ProductRepository productRepository;
	
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
			recalculatePRTotal(purchaseRequestLI);
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
			System.out.println("PurchaseRequestLineItem updated:  "+purchaseRequestLI);
			recalculatePRTotal(purchaseRequestLI);
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
			recalculatePRTotal(purchaseRequestLineItem);
		} catch (Exception e ) {
			purchaseRequestLineItem = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequestLineItem);
	}	

	public void recalculatePRTotal (PurchaseRequestLineItem prli) {
		double total = 0.0;
		PurchaseRequest pr = purchaseRequestRepository.findOne(prli.getPurchaseRequestID());
		List<PurchaseRequestLineItem> prlis = new ArrayList<>();
		prlis = purchaseRequestLineItemRepository.findAllByPurchaseRequestID(pr.getId());
				
		for (PurchaseRequestLineItem li : prlis) {
			Product prod = productRepository.findOne(li.getProductID());
				total += prod.getPrice() * li.getQuantity();
		}
		pr.setTotal(total);				
		purchaseRequestRepository.save(pr);
		System.out.println("Total "+total);
	}

}	


