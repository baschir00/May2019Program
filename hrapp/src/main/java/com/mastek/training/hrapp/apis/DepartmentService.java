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

import com.mastek.training.hrapp.entities.Department;

import com.mastek.training.hrapp.repositories.DepartmentRepository;

@Component
@Scope("singleton")
@Path("/department/")
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public DepartmentService() {
		System.out.println("Department Service created");
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Department registerOrUpdateDepartment(@BeanParam Department dep) {
		dep = departmentRepository.save(dep);
		System.out.println("Department registered"+dep);
		return dep;
	}

	@GET
	@Path("find/{depno}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Department findByDepno(@PathParam("depno") int depno) {
		
		///fetches the Employee Details from DB by empno
		
		try {
			return departmentRepository.findById(depno).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@GET //http method
	@Path("/fetchByLocation") //url pattern
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> fetchDEpartmentByLocatione(@QueryParam("loc")String loc){
		return departmentRepository.findByLocation(loc);
	}
	
	@DELETE //delete http method
	@Path("/delete")
	public void deleteByDepno(@QueryParam("dell")int depno) {
		departmentRepository.deleteById(depno);
	}

	
}
