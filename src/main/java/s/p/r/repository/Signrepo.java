package s.p.r.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import s.p.r.model.sigmodel;

public interface Signrepo extends JpaRepository<sigmodel,Integer>{
	
	public sigmodel findByEmail(String username);
	

}
