package cybersoft.java12.crmapp.service;

import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java12.crmapp.dao.AuthDao;
import cybersoft.java12.crmapp.dto.UserLoginDto;

public class AuthService {
	private AuthDao dao;

	public AuthService() {
		dao = new AuthDao();
	}

	public boolean login(String email, String password) {
		
		boolean isExists = false;
		
		
		try {
			 UserLoginDto userLogin = dao.findUserLogin(email);
			 isExists = BCrypt.checkpw(password, userLogin.getPassword());
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
		return isExists;
	}

	

}
