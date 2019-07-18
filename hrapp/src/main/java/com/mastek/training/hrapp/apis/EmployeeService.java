package com.mastek.training.hrapp.apis;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
@Path("/employees/") //map the URL pattern with the class as service
public class EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeService() {
		System.out.println("Employee Service Created");
	}
	@POST //http method to send the form data
	@Path("/register") //url pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //form data
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) //JSON Data
	public Employee registerOrUpdateEmployee(@BeanParam Employee emp) {//Input Bean using form
		emp = employeeRepository.save(emp);
		System.out.println("Employee registered"+emp);
		return emp;
	}
	// use find method using pathparam 
	// /find/122 : 122 ->empno to pass as param to this method
	
	@Path("/find/{empno}")
	@GET //HTTP METHOD  used to call the api
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	//declare all possible content types of return value
	//employee object to be given in JSON FORMAT OR XML FORMAT
	public Employee findByEmpno(@PathParam("empno") int empno) {
		// use the path paramter as the arguements for the method
		///fetches the Employee Details from DB by empno
		
		try {
			return employeeRepository.findById(empno).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@GET //http method
	@Path("/fetchBySalary") //url pattern
	@Produces(MediaType.APPLICATION_JSON) //respons content type
	public List<Employee> fetchEmmployeesBySalaryRane(@QueryParam("min")double min,@QueryParam("max")double max){
		return employeeRepository.findBySalary(min, max);
	}
	
	@DELETE //delete http method
	@Path("/delete/{empno}")
	public void deleteByEmpno(@PathParam("empno") int empno) {
		 employeeRepository.deleteById(empno);
	}

}
