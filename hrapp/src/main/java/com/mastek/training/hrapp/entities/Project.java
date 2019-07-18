package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
@Scope("prototype") // one copy for each test case
@Entity //declares the class as an Enitity
@Table (name="JPA_PROJECT") //Declaring the table name for the class
//@NamedQueries({@NamedQuery(name="Project.findByCustomer",query="select p from Project p where p.cutstomerName = :cus")})
public class Project implements Serializable{
	
	private int projectId;
	@FormParam("name")
	private String projectname;
	@FormParam("customer")
	private String customerName;
	
	
	private Set<Employee> team = new HashSet<>();
	
	
	
	
	public Project() {
		System.out.println("Project Created");
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectname=" + projectname + ", customerName=" + customerName
				+ "]";
	}

	//mappedBy: check the configuration for Many to Many association
	//in Employee class getAssignments() method
	@ManyToMany(mappedBy="assignments")	
	@XmlTransient
	public Set<Employee> getTeam() {
		return team;
	}



	public void setTeam(Set<Employee> team) {
		this.team = team;
	}
	

}
