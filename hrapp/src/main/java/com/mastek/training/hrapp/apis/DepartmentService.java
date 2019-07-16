package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Department;

import com.mastek.training.hrapp.repositories.DepartmentRepository;

@Component
@Scope("singleton")
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public DepartmentService() {
		System.out.println("Department Service created");
	}

	
	public Department registerOrUpdateDepartment(Department dep) {
		dep = departmentRepository.save(dep);
		System.out.println("Department registered"+dep);
		return dep;
	}


	public Department findByDepno(int depno) {
		
		///fetches the Employee Details from DB by empno
		
		try {
			return departmentRepository.findById(depno).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Department> fetchDEpartmentByLocatione(String loc){
		return departmentRepository.findByLocation(loc);
	}
	

	public void deleteByDepno(int depno) {
		departmentRepository.deleteById(depno);
	}

	
}
