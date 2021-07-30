package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.UserLoginDto;

public class AuthDao {

	public UserLoginDto findUserLogin(String email) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT email, password FROM user WHERE email = ?";
		UserLoginDto dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				dto = new UserLoginDto();
				dto.setEmail(resultSet.getString("email"));
				dto.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return dto;
	}

}
