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

import prs.domain.status.Status;
import prs.domain.status.StatusRepository;
import prs.util.PRSMaintenanceReturn;

@CrossOrigin
@Controller
@RequestMapping(path="/Statuses") 
public class StatusController extends BaseController {
	
	@Autowired
	private StatusRepository statusRepository;	
	
	@GetMapping(path="/List")
	public @ResponseBody Iterable<Status> getAllStatuses() {
		return statusRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get")
	public @ResponseBody List<Status> getStatus (@RequestParam int id) {
		Status s = statusRepository.findOne(id);		
		return getReturnArray(s);
	}	

	@PostMapping(path="Add")
	public @ResponseBody PRSMaintenanceReturn addNewStatus(@RequestBody Status status){
		try {
			statusRepository.save(status);
			System.out.println("Status added:  "+status);
		} catch (Exception e) {
			status = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(status);
	}
	
	@PostMapping(path="Update")
	public @ResponseBody PRSMaintenanceReturn updateStatus(@RequestBody Status status){
		try {
			statusRepository.save(status);
			System.out.println("Status updated:  "+status);
		} catch (Exception e) {
			status = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(status);
	}	
		
	@GetMapping(path="/Delete")
	public @ResponseBody PRSMaintenanceReturn deleteStatusById (@RequestParam int id) {
		Status status = null;
		try {
			status = statusRepository.findOne(id);
			statusRepository.delete(status);
			System.out.println("Status deleted:  "+status);			
		} catch (Exception e) { //EmptyResultDataAccessException exc) {
			status = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(status);
	}	
	
}
