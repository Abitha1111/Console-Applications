package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	public static void insertdatabase(int Id, String Name, String Role, String MobileNumber, String Address) {
		try (Connection connection = repository.getConnection()) {
			String insertQuery = "INSERT INTO attendance (Id,Name,Role,MobileNumber, Address)VALUES (?, ?,?,?,?) ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
				preparedStatement.setInt(1, Id);
				preparedStatement.setString(2, Name);
				preparedStatement.setString(3, Role);
				preparedStatement.setString(4, MobileNumber);
				preparedStatement.setString(5, Address);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Employee added successfully...\n");
				} else {
					System.out.println("Failed to add\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean isEmployeeIdPresent(int employeeId) {
		boolean isPresent = false;

		try (Connection connection = repository.getConnection()) {
			String checkQuery = "SELECT COUNT(*) FROM attendance WHERE Id = ?";
			try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
				checkStatement.setInt(1, employeeId);
				try (ResultSet resultSet = checkStatement.executeQuery()) {
					resultSet.next();
					int count = resultSet.getInt(1);
					if (count > 0) {
						isPresent = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isPresent;
	}

	public static void updateEmployee(int updateId, String columnToUpdate, String newValue) {
		try (Connection connection = repository.getConnection()) {
			String sql = "UPDATE attendance SET " + columnToUpdate + " = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newValue);
			statement.setInt(2, updateId);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee " + columnToUpdate + " Update successful....!");
			} else {
				System.out.println("Update failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteEmployee(int id) {
		try (Connection connection = repository.getConnection()) {
			String deleteQuery = "DELETE FROM attendance WHERE Id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
				preparedStatement.setInt(1, id);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Employee Removed successfully...\n");
				} else {
					System.out.println("Employee not found!\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
