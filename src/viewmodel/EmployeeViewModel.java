package viewmodel;

import java.time.LocalDateTime;
import java.util.*;

import model.Employee;

public class EmployeeViewModel {
    private List<Employee> employees;

    public EmployeeViewModel() {
        this.employees = new ArrayList<>();
    }
    

    public void addEmployee(int id, String name, String role, String mobileno, String address) {
        employees.add(new Employee(id, name, role, mobileno, address));
    }

    public void updateEmployee(int updateid, int choice, String newValue) {
        for (Employee emp : employees) {
            if (emp.getId() == updateid) {
                emp = new Employee(updateid,choice, newValue);
            }
    }
    }

    public boolean deleteEmployee(int deleteId) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.getId() == deleteId) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public boolean markEmployeeAttendance(int empId) {
        for (Employee emp : employees) {
            if (emp.getId() == empId) {
                emp.MarkAttendance();
                return true;
            }
        }
        return false;
    }

    public List<Employee> getPresentEmployees() {
        List<Employee> presentEmployees = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.isPresent() && emp.getAttendanceDateTime() != null) {
                presentEmployees.add(emp);
            }
        }
        return presentEmployees;
    }

    public List<Employee> getAbsentEmployees() {
        List<Employee> absentEmployees = new ArrayList<>();
        for (Employee emp : employees) {
            if (!emp.isPresent() || emp.getAttendanceDateTime() == null) {
                absentEmployees.add(emp);
            }
        }
        return absentEmployees;
    }

    public List<Employee> getEmployeesByMonthAndYear(int year, int month) {
        List<Employee> employeesByMonthAndYear = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.isPresent() && emp.getAttendanceDateTime() != null &&
                    emp.getAttendanceDateTime().getYear() == year &&
                    emp.getAttendanceDateTime().getMonthValue() == month) {
                employeesByMonthAndYear.add(emp);
            }
        }
        return employeesByMonthAndYear;
    }
}
