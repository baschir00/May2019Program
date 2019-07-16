package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
// Initialize the JUnit Test with Spring Boot Application Environment
// Spring Boot Test: used to test Spring ApplicationContext 
//with all the test cases identified

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.entities.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {
	
	//scan in memory all the components and provide the best match object in the component
	
	@Autowired
	DepartmentService depService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	Employee emp;
	
	
	
	@Test
	public void addEmployeeUsingService() {
//		//Employee emp = new Employee();
		emp.setEmpno(5);
		emp.setName("Changed Employee 5");
		emp.setSalary(6565);
		emp = empService.registerOrUpdateEmployee(emp);
		assertNotNull(emp);
		
	}
	
	@Test
	public void findByEmpnoUsingService() {
		int empno =1;
		assertNotNull(empService.findByEmpno(empno));
		
	}
	
	@Test
	public void deleteByEmpnoUsingService() {
		int empno =6;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
		
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmmployeesBySalaryRane(0, 1000);
		for (Employee employee : emps) {
			System.out.println(employee);
			
		}
		assertEquals(emps.size(),3);
		
	}
	
	@Test
	public void manageAssociations() {
		Department d1 = new Department();
		d1.setDepartmentname("Admin");
		d1.setLocation("UK");
		
		Employee emp1 = new Employee();
		emp1.setName("Admin Emp 1");
		emp1.setSalary(3433);
		
		Employee emp2 = new Employee();
		emp2.setName("Admin Emp 2");
		emp2.setSalary(3453);
		
		Project p1 = new Project();
		p1.setCustomerName("UK Customer");
		p1.setProjectname("Uk Project");
		
		Project p2 = new Project();
		p2.setCustomerName("US Customer");
		p2.setProjectname("US Project");
		
		// one to many: one Department has many employees
		d1.getMembers().add(emp1);
		d1.getMembers().add(emp2);
		
		//many to One for each employee to assign the department
		emp1.setCurrentDepartment(d1);
		emp2.setCurrentDepartment(d1);
		
		
		//Many to many
		emp1.getAssignments().add(p1);
		emp1.getAssignments().add(p2);
		emp1.getAssignments().add(p1);
		
		depService.registerOrUpdateDepartment(d1);
		
		
		
	}
	

	
	

}
