package s.p.r.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import s.p.r.model.sigmodel;
import s.p.r.repository.Signrepo;

public class UserDetailService  implements UserDetailsService {
	
	@Autowired
	private Signrepo sinrepo;
		
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		sigmodel user=sinrepo.findByEmail(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("user not found");	
		}
		else
		{
			return new CustomUser(user);
		}
		
		
	}
	
}
