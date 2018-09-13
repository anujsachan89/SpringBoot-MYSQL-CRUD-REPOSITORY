package com.anuj.springrest.sample;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	
/*	@PostMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}*/
	
	@PostMapping("/add")
	public User saveCustomer(@RequestBody User theUser) {
		//also just in case  pass an idin json set to 0
		//this is a force a save of new item.. instead of update
		theUser.setId(0);
		userRepository.save(theUser);
		return theUser;
	}
	
	@GetMapping("/user/{userId}")
	public Optional<User> getUserId(@PathVariable int userId){
		Optional<User> theUser = userRepository.findById(userId);
		return theUser;
	}
	@DeleteMapping("/user/{userId}")
	public String deleteCustomerId(@PathVariable int userId){
		userRepository.deleteById(userId);
		return "Deleted with customer id: "+userId;
	}
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
