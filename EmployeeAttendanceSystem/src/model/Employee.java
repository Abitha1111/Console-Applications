package model;

import java.time.LocalDateTime;

public class Employee {
	private int id;
	private String name;
	private String role;
	private String mobileNo;
	private String address;
	private boolean isPresent;
	private LocalDateTime attendanceDateTime;

	public Employee(int id, String name, String role, String mobileNo, String address) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.mobileNo = mobileNo;
		this.address = address;
		this.isPresent = false;
		this.attendanceDateTime = null;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public LocalDateTime getAttendanceDateTime() {
		return attendanceDateTime;
	}

	public Employee(int updateid, int choice, String newValue) {

	}

	public void markAttendance() {
		isPresent = !isPresent;
		if (isPresent) {
			attendanceDateTime = LocalDateTime.now();
		} else {
			attendanceDateTime = null;
		}
	}
}
