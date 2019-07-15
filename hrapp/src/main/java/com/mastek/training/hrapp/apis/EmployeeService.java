package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.repositories.EmployeeRepository;
//indicates to Spring to create an object of this class as component

//@C0mponent: indicate to Spring to an object of this class
//@Sc0pe : singleton: one object for application [default]
// prototype: one object copy for each usage

@Component
@Scope("singleton")
public class EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeService() {
		System.out.println("Employee Service Created");
	}
	
	
	public Employee registerOrUpdateEmployee(Employee emp) {
		emp = employeeRepository.save(emp);
		System.out.println("Employee registered"+emp);
		return emp;
	}


	public Employee findByEmpno(int empno) {
		
		///fetches the Employee Details from DB by empno
		
		try {
			return employeeRepository.findById(empno).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Employee> fetchEmmployeesBySalaryRane(double min,double max){
		return employeeRepository.findBySalary(min, max);
	}
	


	public void deleteByEmpno(int empno) {
		 employeeRepository.deleteById(empno);
	}

}
