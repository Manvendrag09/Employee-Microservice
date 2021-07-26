package com.cts.employeemicroservice.model;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="Offer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeOffers {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//name of the offer
	private String name;
	
	//description of the offer
	private String description;
	
	//category of the offer
	private String category;

	// opening/creation date of the offer
	private Date openDate;
	
	// engaged date ( when a buyer shows interest)
	private Date engagedDate;
	
	//closing date(when the buyer buys)
	private Date closedDate;
	
	//employee who created the Offer
	private int empId;
	
	//employee which showed interest in the offer
	private int engagedEmpId;
	
	//no of likes on an offer
	private int likes;

}