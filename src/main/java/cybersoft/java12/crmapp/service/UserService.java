package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import cybersoft.java12.crmapp.dao.UserDao;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.util.PwdUtils;

public class UserService {
	private UserDao dao;
	
	public UserService() {
		dao = new UserDao();
	}

	public List<User> findAll() {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean add(UserCreateDto dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10));
		dto.setPassword(hashedPwd);
		
		try {
			return dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public User findById(int id) {
		try {
			return dao.findById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
