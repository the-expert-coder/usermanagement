package s.p.r.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import s.p.r.model.sigmodel;
import s.p.r.model.usermodel;
import s.p.r.repository.Signrepo;
import s.p.r.repository.userrepository; // Although not directly used, good to keep if needed later
import s.p.r.service.userservice;

@Controller
public class controller {
	@Autowired
	private userservice userserv;
	
	@Autowired
	private Signrepo repo;
	
	// userrepository is autowired but not directly used in the provided methods,
	// if it's not used elsewhere, it can be removed.
	@Autowired
	private userrepository userrepo; 
	
	
	@GetMapping("/log")
	public String login()
	{
		return "login"; // Corrected: removed "()"
	}
	
	
	@RequestMapping("/sinp")
	public String signupdata()
	{
		return "SignUp";
	}
	
	@PostMapping("/sinupsave")
	public String savedata(@ModelAttribute sigmodel user)
	{
		userserv.signupdata(user); // This method is used for both create and update
		return "SignUp"; // Consider redirecting to /show or a confirmation page
	}
	
	/**
	 * Handles requests to the registration page.
	 * @return The name of the registration HTML template.
	 */
	@RequestMapping("/reg")
	public String index() 
	{
		return "Registration";
	}
	
	/**
	 * Handles requests to the home page.
	 * @return The name of the home HTML template.
	 */
	@RequestMapping("/") 
	public String Home()
	{
		return "Home";
	}
	
	/**
	 * Saves new user data submitted from the registration form.
	 * @param user The usermodel object populated with form data.
	 * @return Redirects to the registration page after saving.
	 */
	@PostMapping("/usersave")
	public String savedata(@ModelAttribute usermodel user)
	{
		userserv.createuser(user); // This method is used for both create and update
		return "Registration"; // Consider redirecting to /show or a confirmation page
	}
	
	/**
	 * Displays all user data in a dashboard.
	 * @param mod The Model object to pass data to the view.
	 * @return The name of the dashboard HTML template.
	 */
	@GetMapping("/show")
	public String showdata(Model mod)
	{
		List<usermodel> list1 = userserv.fetchall();
		mod.addAttribute("list1",list1);
		return "dashboard"; 
	}
	
	/**
	 * Deletes a user by their ID.
	 * @param id The ID of the user to delete.
	 * @return Redirects to the dashboard page after deletion.
	 */
	@RequestMapping("/delete/{id}")
	public String deleteinfo(@PathVariable(name="id") int id) // Renamed from deletinfo for clarity
	{
		userserv.deletdata(id);
		return "redirect:/show";
	} 
	
	/**
	 * Handles the submission of the update form.
	 * This method uses createuser, which in JPA, for an entity with an existing ID, performs an update.
	 * @param user The usermodel object populated with updated form data (including the existing ID).
	 * @return Redirects to the dashboard page after successful update.
	 */
	@PostMapping("/update")
	public String updatedata(@ModelAttribute usermodel user)
	{
		userserv.createuser(user); // JPA's save() method (called by createuser) handles both insert and update based on ID presence
		return "redirect:/show"; // Redirect to show all data after update
	}
	
	/**
	 * Displays the update form pre-filled with data for a specific user.
	 * @param id The ID of the user to edit.
	 * @param model The Model object to pass the user data to the view.
	 * @return The name of the update HTML template.
	 */
	@RequestMapping("/edit/{id}")
	public String editUserForm(@PathVariable(name="id") int id, Model model) // Renamed for clarity
	{
		usermodel product = userserv.get(id); // 'product' is used as the model attribute name in update.html
		model.addAttribute("product", product); // Pass the retrieved user object to the model
		return "update"; // Return the update HTML page
	}
				
}