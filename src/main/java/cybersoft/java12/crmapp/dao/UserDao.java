package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.model.User;

public class UserDao {

	public List<User> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();

		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email, "
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));

				int roleId = resultSet.getInt("role_id");
				Role role = getRoleFromList(roles, roleId);

				if (role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));

					roles.add(role);
				}

				user.setRole(role);

				users.add(user);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return users;
	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	private Role getRoleFromList(List<Role> roles, int roleId) {
		for (Role role : roles)
			if (role.getId() == roleId)
				return role;
		return null;
	}

	public boolean add(UserCreateDto dto) throws SQLException {
		String query = "INSERT INTO user(email, password, name, address, phone, role_id)" + "VALUES(?,?,?,?,?,?)";
		String findByEmailQuery = "SELECT * FROM user WHERE email=?";

		Connection connection = MySqlConnection.getConnection();
		boolean isSuccess = false;
		try {
			PreparedStatement statement = connection.prepareStatement(findByEmailQuery);
			statement.setString(1, dto.getEmail());
			ResultSet executeQuery = statement.executeQuery();
			if (executeQuery.next()) {
				System.out.println("Email exist");
				isSuccess = false;
			} else {
				PreparedStatement statementInsert = connection.prepareStatement(query);

				statementInsert.setNString(1, dto.getEmail());
				statementInsert.setNString(2, dto.getPassword());
				statementInsert.setNString(3, dto.getName());
				statementInsert.setNString(4, dto.getAddress());
				statementInsert.setNString(5, dto.getPhone());
				statementInsert.setInt(6, dto.getRoleId());

				statementInsert.executeUpdate();
				isSuccess = true;
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return isSuccess;
	}

	public User findById(int id) throws SQLException {
		String query = "SELECT * FROM user WHERE id=?";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			User user = null;
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
			}
			if(user != null)
				return user;

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}
}
