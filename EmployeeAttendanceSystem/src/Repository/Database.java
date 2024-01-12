package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	public static void insertdatabase(int Id, String Name, String Role, String MobileNumber, String Address) {
		try (Connection connection = repository.getConnection()) {
			String insertQuery = "INSERT INTO employeedetails  (Id,Name,Role,MobileNumber, Address)VALUES (?, ?,?,?,?) ";
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
			String checkQuery = "SELECT COUNT(*) FROM employeedetails  WHERE Id = ?";
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
			String sql = "UPDATE employeedetails  SET " + columnToUpdate + " = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newValue);
			statement.setInt(2, updateId);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println(columnToUpdate + " Update successful....!");
			} else {
				System.out.println("Update failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	    public static void deleteEmployee(int employeeId) {
//	        try (Connection connection = repository.getConnection();
//	             PreparedStatement deleteAttendance = connection.prepareStatement("DELETE FROM attendance WHERE Id = ?");
//	             PreparedStatement deleteEmployee = connection.prepareStatement("DELETE FROM employeedetails WHERE Id = ?")) {
//	            deleteAttendance.setInt(1, employeeId);
//	            deleteAttendance.executeUpdate();
//	            deleteEmployee.setInt(1, employeeId);
//	            deleteEmployee.executeUpdate();
//
//	            System.out.println("Employee deleted successfully.");
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
	
	public static void deleteEmployee(int id) {
		try (Connection connection = repository.getConnection()) {
			String deleteQuery = "DELETE FROM employeedetails WHERE Id = ?";
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

	public static void MarkAttendance(int Id, String Status, String formattedDateTime) {
		try (Connection connection = repository.getConnection()) {
			String insertQuery = "INSERT INTO attendance(Id,Date,Status)VALUES (?, ?,?) ";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
				preparedStatement.setInt(1, Id);
				preparedStatement.setString(2, formattedDateTime);
				preparedStatement.setString(3, Status);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Attendance marked...\n");
				} else {
					System.out.println("Failed to Mark \n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void ViewAttendance() {
		try (Connection connection = repository.getConnection()) {
			String searchQuery = "SELECT employeedetails.Id, employeedetails.Name, employeedetails.Role, employeedetails.MobileNumber, employeedetails.Address, attendance.Date, attendance.Status\n"
					+ "FROM employeedetails\n"
					+ "LEFT JOIN attendance ON employeedetails.Id = attendance.Id;";
			try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					System.out.println("----------------------------------------------------");
					System.out.printf("| %-6s | %-12s | %-20s |%-9s\n", "Id", "Name", "Date","Status");
					System.out.println("----------------------------------------------------");

					while (resultSet.next()) {
						int id = resultSet.getInt("Id");
						String name = resultSet.getString("Name");
						String date = resultSet.getString("Date");
						String status = resultSet.getString("Status");

						System.out.printf("| %-6d | %-12s | %-20s |%-9s\n", id, name, date,status);
					}
					System.out.println("----------------------------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}
}