package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.mastek.training.hrapp.entities.Project;

import com.mastek.training.hrapp.repositories.ProjectRepository;

@Component
@Scope("singleton")
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectService() {
		System.out.println("Project Service created");
	}

	
	public Project registerOrUpdateProject(Project pro) {
		pro = projectRepository.save(pro);
		System.out.println("Project registered"+pro);
		return pro;
	}


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

	public void deleteByprojectid(int projectid) {
		projectRepository.deleteById(projectid);
	}

	
	
	
}
