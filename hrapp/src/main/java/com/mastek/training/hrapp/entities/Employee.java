package com.mastek.training.hrapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // one copy for each test case
@Entity //declares the class as an Enitity
@Table (name="JPA_EMPLOYEE") //Declaring the table name for the class
@EntityListeners({EmployeeLifecycleListener.class})
@NamedQueries({@NamedQuery(name="Employee.findBySalary",query="select e from Employee e where e.salary between :min and :max")})
public class Employee implements Serializable { //manage serialization of Objects
	
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", empno=" + empno + ", salary=" + salary + "]";
	}
	@Value("Default Employee")
	private String name;
	@Value("-1")
	private int empno;
	@Value("100.0")
	private double salary;

	
	
	
	
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
	

}
