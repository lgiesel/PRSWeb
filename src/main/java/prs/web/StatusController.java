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

import prs.domain.status.Status;
import prs.domain.status.StatusRepository;

@Controller
@RequestMapping(path="/Statuses") 
public class StatusController {
	
	@Autowired
	private StatusRepository statusRepository;	
	
	@GetMapping(path="/List")
	public @ResponseBody Iterable<Status> getAllStatuses() {
		return statusRepository.findAll(); 
	}		
	
	@GetMapping(path="/Get")
	public @ResponseBody Status getStatusById (@RequestParam int id) {
		
		Status v = statusRepository.findOne(id);		
		return v;
	}	

	@GetMapping(path="/Delete")
	public @ResponseBody String deleteStatusById (@RequestParam int id) {
		
		String msg = "";
		try {
			statusRepository.delete(id);
			msg = "Status " + id+ " deleted";			
			
		} catch (EmptyResultDataAccessException exc) {
			msg = "Status " + id+ " not found; no delete";			
		}
		return msg;
	}				
	
	//#	Paste localhost:8080/Status/Add in browser to add status
	@PostMapping(path="/Add") // Map ONLY POST Requests
	public @ResponseBody Status addNewStatus (@RequestBody Status status) {     
        statusRepository.save(status);
        System.out.println("Status saved:  "+status);
        return status;
    }		

}
