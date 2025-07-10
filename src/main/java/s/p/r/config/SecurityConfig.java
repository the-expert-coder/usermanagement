package s.p.r.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordencoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getDetailService()
	{
		return new UserDetailService();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider()
	{
		
		DaoAuthenticationProvider daoauthenticationprovider=new DaoAuthenticationProvider();
		daoauthenticationprovider.setUserDetailsService(getDetailService());
		daoauthenticationprovider.setPasswordEncoder(passwordencoder());
		return daoauthenticationprovider;
		
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/","/reg","/sinp","/sinupsave","/usersave").permitAll()
		.requestMatchers("/show","/delete/{id}","/edit/{id}","/update").authenticated().and()
		.formLogin().loginPage("/log").loginProcessingUrl("/login")
		.defaultSuccessUrl("/show").permitAll();
		return http.build();
	}
	

}
