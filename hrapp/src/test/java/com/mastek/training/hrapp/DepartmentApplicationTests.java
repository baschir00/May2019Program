package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentApplicationTests {
	@Autowired
	DepartmentService depService;
	
	
	@Autowired
	Department dep;
	
	
	
	@Test
	public void addDepartmentUsingService() {

		dep.setDepno(12);
		dep.setDepartmentname("Ra");
		dep.setLocation("Leeds");
		dep = depService.registerOrUpdateDepartment(dep);
		assertNotNull(dep);
		
	}
	
	@Test
	public void findByDepnoUsingService() {
		int depno =8;
		assertNotNull(depService.findByDepno(depno));
		
	}
	

	@Test
	public void deleteByDepnoUsingService() {
		int depno =6;
		depService.deleteByDepno(depno);
		assertNull(depService.findByDepno(depno));
		
	}
	
	@Test
	public void checkFetchByLocation() {
		List<Department> deps = depService.fetchDEpartmentByLocatione("Leeds");
		for (Department department : deps) {
			System.out.println(department);
			
		}
		assertEquals(deps.size(),6);
		
	}
	
	
	
	
	
	
}
