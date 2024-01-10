package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class repository {
	private static final String URL = "jdbc:mysql://localhost:3306/employeeattendance";
	private static final String USERNAME = "Abi";
	private static final String PASSWORD = "Pra#zh@2023";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}