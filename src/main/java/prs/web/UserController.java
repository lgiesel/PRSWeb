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

import prs.domain.user.User;
import prs.domain.user.UserRepository;

@Controller
@RequestMapping(path="/Users") 
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//####	Paste localhost:8080/Users/List in browser to get all users returned #####
	@GetMapping(path="/List")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll(); 
	}		
	
	//####	Paste localhost:8080/Users/Get?id=3 in browser to get one user ##############
	@GetMapping(path="/Get")
	public @ResponseBody User getUserById (@RequestParam int id) {		
		User u = userRepository.findOne(id);		
		return u;
	}	

	//####	Paste localhost:8080/Users/Delete?id=3 in browser to delete user - "User 3 deleted" displayed #######
	//####	Repeat same id to delete - id not found - "User 3 not found; no delete" displayed   #################
	@GetMapping(path="/Delete")
	public @ResponseBody String deleteUserById (@RequestParam int id) {
		
		String msg = "";
		try {
			userRepository.delete(id);
			msg = "User " + id+ " deleted";			
			
		} catch (EmptyResultDataAccessException exc) {
			msg = "User " + id+ " not found; no delete";			
		}
		return msg;
	}		
	
	//#	Paste localhost:8080/Users/Add in browser to add user - "Added" displayed ###
	@PostMapping(path="/Add") // Map ONLY POST Requests
	public @ResponseBody User addNewUser (@RequestBody User user) {     
        userRepository.save(user);
        System.out.println("User saved:  "+user);
        return user;
    }	
	
	@GetMapping(path="/Authenticate") // Map ONLY GET Requests
	public @ResponseBody User authenticate (@RequestParam String username, @RequestParam String pwd) {

		User u = userRepository.findByUsernameAndPassword(username, pwd);
		return u;
	}
}
