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


import com.mastek.training.hrapp.entities.Project;

import com.mastek.training.hrapp.repositories.ProjectRepository;

@Component
@Scope("singleton")
@Path("/project/")
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectService() {
		System.out.println("Project Service created");
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Project registerOrUpdateProject(@BeanParam Project pro) {
		pro = projectRepository.save(pro);
		System.out.println("Project registered"+pro);
		return pro;
	}

	@Path("/find/{projectid}")
	@GET //HTTP METHOD  used to call the api
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Project findByProject(int projectid) {
		
		///fetches the Employee Details from DB by empno
		
		try {
			return projectRepository.findById(projectid).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
//	public List<Project> fetchProjectByCustomer(String cus){
//		return projectRepository.findByP(cus);
//	}
//	
	@DELETE //delete http method
	@Path("/delete/{project}")
	public void deleteByprojectid(@PathParam("project")int projectid) {
		projectRepository.deleteById(projectid);
	}

	
	
	
}
