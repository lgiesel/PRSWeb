package prs.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import prs.domain.user.User;
import prs.domain.user.UserRepository;
import prs.util.PRSMaintenanceReturn;
//replaces the enable cross origin in browser

@CrossOrigin	
@Controller
@RequestMapping(path="/Users")
public class UserController extends BaseController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path="/List")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path="/Get") // Map ONLY GET Requests
	public @ResponseBody List<User> getUser (@RequestParam int id) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		User u = userRepository.findOne(id);
		return getReturnArray(u);
	}

	@GetMapping(path="/Authenticate") // Map ONLY GET Requests
	public @ResponseBody List<User> authenticate (@RequestParam String uname, @RequestParam String pwd) {
		User u = userRepository.findByUsernameAndPassword(uname, pwd);
		return getReturnArray(u);
	}

	@PostMapping(path="/Add") // Map ONLY GET Requests
	public @ResponseBody PRSMaintenanceReturn addNewUser (@RequestBody User user) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		// Commenting out DateCreated as DB is going to handle; good example of how to manage a date on the back end though.
		//Timestamp ts = new Timestamp(System.currentTimeMillis());
		//user.setDateCreated(ts);

		try {
			userRepository.save(user);
			System.out.println("User saved:  "+user);
		}
		catch (Exception e) {
			user = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(user);
	}

	@PostMapping(path="/Update") // Map ONLY GET Requests
	public @ResponseBody PRSMaintenanceReturn updateUser (@RequestBody User user) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		try {
			userRepository.save(user);
			System.out.println("User updated:  "+user);
		}
		catch (Exception e) {
			user = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(user);
	}

	@GetMapping(path="/Remove") // Map ONLY GET Requests
	public @ResponseBody PRSMaintenanceReturn deleteUser (@RequestParam int id) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User user = userRepository.findOne(id);
		try {
			userRepository.delete(user);
			System.out.println("User deleted:  "+user);
		}
		catch (Exception e) {
			user = null;
		}
		return PRSMaintenanceReturn.getMaintReturn(user);
	}
	
}