package org.jsp.employeeapp.controller;

import java.util.List;
import java.util.Scanner;

import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.jsp.employeeapp.dao.EmployeeDao;
import org.jsp.employeeapp.dto.Employee;

public class EmployeeController {
	static Scanner s = new Scanner(System.in);
	static EmployeeDao dao = new EmployeeDao();

	public static void main(String[] args) {
		System.out.println("1.Save Employee");
		System.out.println("2.Update Employee");
		System.out.println("3.Find Employee By Id");
		System.out.println("4.Verify Employee By Phone and Password");
		System.out.println("5.Verify Employee By Id and Password");
		System.out.println("6.DeleteEmployee By Id");
		System.out.println("7.Find Employee By designation");

		int choice = s.nextInt();
		switch (choice) {
		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			findById();
			break;
		}
		case 4: {
			verifyByPhone();
			break;
		}
		case 5: {
			verifyById();
			break;
		}
		case 6: {
			delete();
			break;
		}
		case 7: {
			findByDesg();
			break;
		}
		}

	}

	public static void save() {
		System.out.println("Enter the employee name,desg,salary,phone and password to register");
		Employee e = new Employee();
		e.setName(s.next());
		e.setDesg(s.next());
		e.setSalary(s.nextDouble());
		e.setPhone(s.nextLong());
		e.setPassword(s.next());
		e = dao.saveEmployee(e);
		System.out.println("Employee saved with Id:" + e.getId());

	}

	public static void update() {
		System.out.println("Enter your Id to update");
		int id = s.nextInt();
		System.out.println("Enter the employee name,desg,salary,phone and password to register");
		Employee e = new Employee();
		e.setId(id);
		e.setName(s.next());
		e.setDesg(s.next());
		e.setSalary(s.nextDouble());
		e.setPhone(s.nextLong());
		e.setPassword(s.next());
		e = dao.updateEmployee(e);
		System.out.println("Employee updated with Id:" + e.getId());

	}

	public static void verifyByPhone() {
		System.out.println("Enter the Employee phone and password to verify");
		long phone = s.nextLong();
		String password = s.next();
		Employee e = dao.verifyEmployee(phone, password);
		if (e != null) {
			System.out.println("Employee Id :" + e.getId());
			System.out.println("Employee Name :" + e.getName());
			System.out.println("Employee Designation :" + e.getDesg());
			System.out.println("Employee Salary :" + e.getSalary());
			System.out.println("Employee Phone :" + e.getPhone());
		} else {
			System.err.println("Invalid Phone number or password");
		}
	}

	public static void delete() {
		System.out.println("Enter Employee Id to delete");
		int id = s.nextInt();
		boolean deleted = dao.deleteEmployee(id);
		if (deleted) {
			System.out.println("Employee deleted");

		} else {
			System.err.println("you have entered an invalid id");
		}

	}

	public static void verifyById() {
		System.out.println("Enter the Employee Id and password to verify");
		int id = s.nextInt();
		String password = s.next();
		Employee e = dao.verifyEmployee(id, password);
		if (e != null) {
			System.out.println("Employee Id :" + e.getId());
			System.out.println("Employee Name :" + e.getName());
			System.out.println("Employee Designation :" + e.getDesg());
			System.out.println("Employee Salary :" + e.getSalary());
			System.out.println("Employee Phone :" + e.getPhone());
		} else {
			System.err.println("Invalid id or password");
		}
	}

	public static void findByDesg() {
		System.out.println("Enter the Employee Designation to display the details");
		String desg = s.next();
		List<Employee> emps = dao.findEmployeeByDesg(desg);
		if (emps.size() > 0) {
			for (Employee e : emps) {
				System.out.println("Employee Id :" + e.getId());
				System.out.println("Employee Name :" + e.getName());
				System.out.println("Employee Designation :" + e.getDesg());
				System.out.println("Employee Salary :" + e.getSalary());
				System.out.println("Employee Phone :" + e.getPhone());
			}
		} else {
			System.err.println("You have entered an Invalid Designation");
		}
	}

	public static void findById() {
		System.out.println("Enter the Employee Id to display the details");
		int id = s.nextInt();
		Employee e = dao.findById(id);
		if (e != null) {
			System.out.println("Employee Id :" + e.getId());
			System.out.println("Employee Name :" + e.getName());
			System.out.println("Employee Designation :" + e.getDesg());
			System.out.println("Employee Salary :" + e.getSalary());
			System.out.println("Employee Phone :" + e.getPhone());
		} else {
			System.err.println("You have entered an Invalid id");
		}
	}

}
