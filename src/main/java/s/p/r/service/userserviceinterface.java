package s.p.r.service;

import java.util.List;

import s.p.r.model.usermodel;

public interface userserviceinterface {
	//data insert
	public usermodel createuser(usermodel user);
	public List<usermodel>fetchall();
	public void deletdata(int id);
	public usermodel get(int id);

}
 