package view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Repository.Database;
import model.Employee;

public class AttendanceSystem {
	private static Scanner sc = new Scanner(System.in);
	private static List<Employee> employees = new ArrayList<>();

	public static void main(String[] args) {
		showMenu();
	}

	private static void showMenu() {
		int choice;
		do {
			System.out.println("Choose an option:");
			System.out.println("1. Admin Panel");
			System.out.println("2. Employee Panel");
			System.out.println("3. Manager Panel");
			System.out.println("0. Exit");
			System.out.println("Enter your choice :");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				adminPanel();
				break;
			case 2:
				employeePanel();
				break;
			case 3:
				managerPanel();
				break;
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} while (choice != 0);
	}

	private static void adminPanel() {
		int adminChoice;
		do {
			System.out.println("\n  -Admin Panel-");
			System.out.println("1. Add Employee");
			System.out.println("2. Update Employee");
			System.out.println("3. Delete Employee");
			System.out.println("0. Back to Main Menu");
			System.out.println("Enter your choice :");
			adminChoice = sc.nextInt();
			sc.nextLine();
			switch (adminChoice) {
			case 1:
				addEmployee();
				break;
			case 2:
				updateEmployee();
				break;
			case 3:
				deleteEmployee();
				break;
			case 0:
				System.out.println("Returning to Main Menu...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} while (adminChoice != 0);
	}

	private static void addEmployee() {
		System.out.println("-- Add Employee --");
		System.out.println("Enter Employee Id:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Name:");
		String name = sc.nextLine();
		System.out.println("Enter Role:");
		String role = sc.nextLine();
		System.out.println("Enter Mobile Number:");
		String mobileNo = sc.nextLine();
		System.out.println("Enter Address:");
		String address = sc.nextLine();
		Database.insertdatabase(id, name, role, mobileNo, address);
	}

	public static void updateEmployee() {
		System.out.println("--Update Employee Details--\n");
		System.out.println("Enter employee Id to update:");
		int updateId = sc.nextInt();
		boolean IdisPresent = Database.isEmployeeIdPresent(updateId);
		boolean notfound = false;

		if (IdisPresent != notfound) {
			System.out.println("Employee found...\nWhat do you want to update?");
			System.out.println("1. Name");
			System.out.println("2. Role");
			System.out.println("3. Mobile Number");
			System.out.println("4. Address");
			System.out.println("0.Back to Main Menu");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter new name:");
				String newname = sc.next();
				Database.updateEmployee(updateId, "Name", newname);
				break;
			case 2:
				System.out.println("Enter new role:");
				String newrole = sc.next();
				Database.updateEmployee(updateId, "Role", newrole);
				break;
			case 3:
				System.out.println("Enter new mobile number:");
				String newmobile = sc.next();
				Database.updateEmployee(updateId, "MobileNumber", newmobile);
				break;
			case 4:
				System.out.println("Enter new address:");
				String newaddress = sc.next();
				Database.updateEmployee(updateId, "Address", newaddress);

				break;
			default:
				System.out.println("Invalid option.");
				break;
			}
		} else {
			System.out.println("Employee not found.");
		}
	}

	private static void deleteEmployee() {
		System.out.println("-- Delete Employee --");
		System.out.println("Enter Employee Id to delete:");
		int deleteId = sc.nextInt();
		Database.deleteEmployee(deleteId);

	}

	private static void employeePanel() {
		System.out.println("-- Employee Panel --");
		System.out.println("1. Mark your attendance");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
		int empChoice = sc.nextInt();
		sc.nextLine();
		switch (empChoice) {
		case 1:
			markAttendance();
			break;
		case 0:
			System.out.println("Returning to Main Menu...");
			break;
		default:
			System.out.println("Invalid choice.");
			break;
		}
	}

	private static void markAttendance() {
		System.out.println("-- Mark Your Attendance --");
		System.out.println("Enter Your Employee Id:");
		int empId = sc.nextInt();
		boolean IdisPresent = Database.isEmployeeIdPresent(empId);
		boolean notfound = false;

		if (IdisPresent != notfound) {
			System.out.println("Enter your Status IN / OUT : ");
			String status = sc.next();
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = currentDateTime.format(formatter);
			Database.MarkAttendance(empId, status, formattedDateTime);
		} else {
			System.out.println("Employee not found..!\n");
		}

	}

	private static void managerPanel() {
		int managerChoice;
		do {
			System.out.println("Manager Panel:");
			System.out.println("1. View Employees Attendance");
			System.out.println("2. Generate Reports");
			System.out.println("0. Back to Main Menu");
			System.out.println("Enter your choice :");
			managerChoice = sc.nextInt();
			sc.nextLine();

			switch (managerChoice) {
			case 1:
				viewAttendance();
				break;
			case 2:
				generateAttendanceReport();
				break;
			case 0:
				System.out.println("Returning to Main Menu...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} while (managerChoice != 0);
	}

	private static void viewAttendance() {
		System.out.println("-- Employees Attendance --");
		Database.ViewAttendance();
		
	}

	private static void generateAttendanceReport() {
		System.out.println("-- Generate Attendance Report --");
		System.out.println("Enter the Year (YYYY):");
		int year = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Month (MM):");
		int month = sc.nextInt();
		sc.nextLine();

		System.out.println("Attendance Report for " + month + "/" + year + ":");

		List<Employee> presentEmployees = new ArrayList<>();
		List<Employee> absentEmployees = new ArrayList<>();

		for (Employee emp : employees) {
			if (emp.isPresent() && emp.getAttendanceDateTime() != null && emp.getAttendanceDateTime().getYear() == year
					&& emp.getAttendanceDateTime().getMonthValue() == month) {
				presentEmployees.add(emp);
			} else {
				absentEmployees.add(emp);
			}
		}

		System.out.println("Present Employees:");
		for (Employee emp : presentEmployees) {
			System.out.println("Employee ID: " + emp.getId() + ", Name: " + emp.getName());
		}

		System.out.println("\nAbsent Employees:");
		for (Employee emp : absentEmployees) {
			System.out.println("Employee ID: " + emp.getId() + ", Name: " + emp.getName());
		}
	}

}
