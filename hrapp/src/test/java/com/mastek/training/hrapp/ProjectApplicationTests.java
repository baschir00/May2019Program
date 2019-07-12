package com.mastek.training.hrapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest


public class ProjectApplicationTests {

	
	@Autowired
	EmployeeService empservice;
	
	@Autowired
	Employee emp;
	
	@Test
	public void exampleProjectTest() {
		System.out.println("Project Test scenario");
		emp.setSalary(455500);
		emp.setEmpno(555);
		empservice.registerEmployee(emp);
		
	}
	
	
}
