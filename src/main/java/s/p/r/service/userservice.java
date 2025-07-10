package s.p.r.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import s.p.r.model.sigmodel;
import s.p.r.model.usermodel;
import s.p.r.repository.Signrepo;
import s.p.r.repository.userrepository;
@Service
public class userservice {
	@Autowired
	private userrepository userrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	
	@Autowired
	private Signrepo repo;
	
	//data save in database
	public usermodel createuser(usermodel user)
	{
		
		return userrepo.save(user);
	} 
	
	//signupdata save 
	public sigmodel signupdata(sigmodel user1)
	{
		String password = passwordencoder.encode(user1.getPassword( ));
		user1.setPassword(password);
		user1.setRole("ROLE_USER");
		return repo.save(user1);
	}
	
	//fetch data from database
	public List<usermodel> fetchall()
	{
		return userrepo.findAll();
	}
	
	//delete data
	public void deletdata(int id)
	{
		userrepo.deleteById(id);
	}
	
	//update data 
	public usermodel get(int id)
	{
		return userrepo.findById(id).get();
	}
	
	
}
   