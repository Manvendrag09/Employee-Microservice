  
package com.cts.employeemicroservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.employeemicroservice.client.AuthClient;
import com.cts.employeemicroservice.client.OfferClient;
import com.cts.employeemicroservice.exception.InvalidUserException;
import com.cts.employeemicroservice.model.AuthResponse;
import com.cts.employeemicroservice.model.Employee;
import com.cts.employeemicroservice.model.EmployeeOffers;
import com.cts.employeemicroservice.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {


	@Autowired
	AuthClient authClient;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	OfferClient offerClient;

	@Override
	public List<EmployeeOffers> viewEmpOffers(String token, int id) 
	{
		log.info("Inside view employee offers");
		AuthResponse authResponse = authClient.getValidity(token).getBody();
		if (authResponse.isValid()) 
		{
			List<EmployeeOffers> empOffers = offerClient.getOffersById(token, id).getBody();
			for (EmployeeOffers offers : empOffers) 
			{
				offers.setCategory(null);
			}
			return empOffers;	
		} 
		else 
		{
			log.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}

	@Override
	public Employee viewEmployee(String token, int id) 
	{
		log.info("Inside view employee");
		AuthResponse authResponse = authClient.getValidity(token).getBody();
		if (authResponse.isValid()) 
		{
			Employee employee = employeeRepository.findById(id).orElse(null);
			if (employee == null) 
			{
				log.error("Invalid employee id");
				throw new NoSuchElementException();
			}
			return employee;
		} 
		else 
		{
			log.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}
	
	@Override
	public List<EmployeeOffers> viewTopOffers(String token, int employeeId) 
	{
		log.info("Inside view top offers");
		AuthResponse authResponse = authClient.getValidity(token).getBody();
		if (authResponse.isValid()) 
		{
			List<EmployeeOffers> emp = offerClient.getOffersById(token, employeeId).getBody();
			List<EmployeeOffers> empList = emp.stream().sorted(Comparator.comparing(EmployeeOffers::getLikes).reversed()).limit(3).collect(Collectors.toList());
			return empList;
		} 
		else 
		{
			log.error("Token invalid");
			throw new InvalidUserException("Invalid User");
		}
	}
}