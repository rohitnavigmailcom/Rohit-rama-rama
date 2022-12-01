package com.marketingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketingapp.entities.Lead;
import com.marketingapp.repositories.LeadRepository;

@RestController    //this makes web services ur application  (all webservices work in here)
public class LeadRestController {
	@Autowired
	private LeadRepository leadRepo;
	
	@GetMapping("listallleads") //if run the code java object autometically convert into Json object
	public List<Lead>getAllLeads() {   //this method runs intract with database get all the data from the database and put that into Leads object(if 10 record it will create 10 object in lead,10 objects are java objects)
		List<Lead> leads = leadRepo.findAll();
		return leads;
	}
	@PostMapping("/saveapi")   // ,json object quik to get and go to this method 
    public void saveLead(@RequestBody Lead lead) { //from that requestbody fix that json object contain and puts that into lead object
		
		leadRepo.save(lead);
	}
	@DeleteMapping("/delete/{id}")  //id can delete the exact number id parat to delete the details
	public void deleteLead(@PathVariable long id) {
		leadRepo.deleteById(id);
	}
	@PutMapping("/updateapi")
	public void updateLead(@RequestBody Lead lead) {
		leadRepo.save(lead);
	}
	@GetMapping("/getapi/{id}")   //url to id data will print in chrome browser purpoise this will use ,microservice purpose
	public Lead getOneLead(@PathVariable long id) {
		try {
			Optional<Lead> findById = leadRepo.findById(id);
			Lead lead=findById.get();
			return lead;
		} catch(Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
}
     
