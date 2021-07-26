package com.cts.employeemicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="Employee")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
	
	@Id
	private int id;

	// name of the employee
	private String name;

	// department of the employee
	private String department;

	// gender
	private String gender;

	// age
	private int age;

	// contact info
	@Column(name = "contact")
	private long contactNumber;

	// email id
	private String email;

	@Column(name = "points")
	// points gained by the employee
	private int pointsGained;

}