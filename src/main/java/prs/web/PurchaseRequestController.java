package prs.web;

import java.sql.Timestamp;
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

import prs.domain.purchaserequest.PurchaseRequest;
import prs.domain.purchaserequest.PurchaseRequestRepository;
import prs.util.PRSMaintenanceReturn;

@CrossOrigin
@Controller
@RequestMapping(path="/PurchaseRequests")
public class PurchaseRequestController extends BaseController {
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;	

	@GetMapping(path="/List")
	public @ResponseBody Iterable<PurchaseRequest> getAllPurchaseRequests() {
		return purchaseRequestRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get")
	public @ResponseBody List<PurchaseRequest> getPurchaseRequest (@RequestParam int id) {
		PurchaseRequest v = purchaseRequestRepository.findOne(id);
		return getReturnArray(v);
	}

	@PostMapping(path="/Add")
	public @ResponseBody PRSMaintenanceReturn addNewPurchaseRequest (@RequestBody PurchaseRequest purchaseRequest) {
		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis()); //Submit date cannot be null
			purchaseRequest.setSubmittedDate(ts);			
			purchaseRequestRepository.save(purchaseRequest);
			System.out.println("PurchaseRequest added:  "+purchaseRequest);
		}
		catch (Exception e) {
			purchaseRequest = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequest);
	}	

	@PostMapping(path="/Update")
	public @ResponseBody PRSMaintenanceReturn updatePurchaseRequest (@RequestBody PurchaseRequest purchaseRequest) {
		try {
			purchaseRequestRepository.save(purchaseRequest);
			System.out.println("PurchaseRequest updated:  "+purchaseRequest);
		}
		catch (Exception e) {
			purchaseRequest = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequest);
	}	
	
	@GetMapping(path="/Delete")
	public @ResponseBody PRSMaintenanceReturn deletePurchaseRequest (@RequestParam int id) {
		PurchaseRequest purchaseRequest = null; //purchaseRequestRepository.findOne(id);;
		try {
			purchaseRequest = purchaseRequestRepository.findOne(id);
			purchaseRequestRepository.delete(purchaseRequest);		
		} catch (Exception e ) {
			purchaseRequest = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(purchaseRequest);
	}	
	
//	@GetMapping(path="/MyPRs")
//	public @ResponseBody List<PurchaseRequest> getMyPRs (@RequestParam int id) {
//		PurchaseRequest pr = purchaseRequestRepository.findAllByPurchaseRequestId(id);
//		return getReturnArray(pr);
//	}	
}
