package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//Component
@Scope("prototype") // one copy for each test case
@Entity //declares the class as an Enitity
@Table (name="JPA_DEPARTMENT") //Declaring the table name for the class
@NamedQueries({@NamedQuery(name="Department.findByLocation",query="select d from Department d where d.location = :loc")})
@XmlRootElement
public class Department implements Serializable{
	
	private int depno;
	@FormParam("name")
	private String departmentname;
	@FormParam("location") 
	private String location;
	//OnetoMany: One department has many employees
    //@OneToMany: used on the get method of set to configure association
    //fetch=LAZY: delay the initialization until method getMembers() is called
    //         EAGER: Initialize the collection on entity find post load event
    //Cascade= All the entity operation done on department would be performed
    //                on each associated employee object
    //            ALL: [Persist, Merge, Delete, Refresh]
    // mappedby: Identifies the propertyname in child class where the JoinColumn configuration is present
    //            JoinColumn:ForeignKey
	
	private Set<Employee> members = new HashSet<>();
	
	
	
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="currentDepartment")
	@XmlTransient
	public Set<Employee> getMembers() {
		return members;
	}

	public void setMembers(Set<Employee> members) {
		this.members = members;
	}

	public Department() {
		System.out.println("Department Created");
	}
	
	@Id
	@Column(name="department_number")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDepno() {
		return depno;
	}
	public void setDepno(int depno) {
		this.depno = depno;
	}

	@Column(name="department_name",nullable=false,length=45)
	public String getDepartmentname() {
		return departmentname;
	}


	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	@Column(name="location",nullable=false,length=45)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
	
	
	
}

