package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component

@Scope("prototype") // one copy for each test case
@Entity //declares the class as an Enitity
@Table (name="JPA_EMPLOYEE") //Declaring the table name for the class
@EntityListeners({EmployeeLifecycleListener.class})
@NamedQueries({@NamedQuery(name="Employee.findBySalary",query="select e from Employee e where e.salary between :min and :max")})
@XmlRootElement
public class Employee implements Serializable { //manage serialization of Objects
	
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", empno=" + empno + ", salary=" + salary + "]";
	}
	@Value("Default Employee")
	@FormParam("name") //name of parameters passed via HTML Form
	private String name;
	
	@Value("-1")
	private int empno;
	
	@Value("100.0")
	@FormParam("salary") 
	private double salary;

	private Set<Project> assignments = new HashSet<>();
	//@ManytoMany : configuring the association for both the entities
	//@Jointable: Provides all the configuration for the third table
	//name: name of the Join table
	//joinColumns: Foreign Key column name for current class
	//inverseJoinColumns: Foreign key Column for other class
	
	
@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
@JoinTable(name="JPA_ASSIGNMENTS",joinColumns=@JoinColumn(name="FK_EMPNO"),inverseJoinColumns=@JoinColumn(name="FK_PROJECTID"))

@XmlTransient //ignore the collections while using api
public Set<Project> getAssignments() {
		return assignments;
	}
	public void setAssignments(Set<Project> assignments) {
		this.assignments = assignments;
	}
	//@ManytoOne Each employee belongs to one department
	private Department currentDepartment;
	
	
	
	public Employee() {
		System.out.println("Employee Created");
	}
	@Column(name="employee_name",nullable=false,length=45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Id //declare the property as primary key
	@Column(name="employee_number") //declare the column name
	@GeneratedValue(strategy=GenerationType.AUTO) //Auto-numbering the primary key
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) { //JPA will apply default configuration
		this.salary = salary;
	}
	//Many to one associating
	
	@ManyToOne
	@JoinColumn(name="FK_DepartmentID")
	
	public Department getCurrentDepartment() {
		return currentDepartment;
	}
	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}
	

}
