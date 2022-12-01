package com.marketingapp.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

//import org.springframework.stereotype.Service;

import com.marketingapp.entities.Lead;
import com.marketingapp.repositories.LeadRepository;


 @Service  //it will interact with data base
public class LeadServiceImpl implements LeadService {  //ad unimpliment method(LeadServiceImpl)
	
	@Autowired
	private LeadRepository leadRepo;  //data implementation purpose we can use 

	@Override
      public void saveLead(Lead lead)  {
        leadRepo.save(lead);  //import the data service
	}

	@Override
	public List<Lead> getLeads() {  //unimpliment method
		List<Lead> leads=leadRepo.findAll(); //findall is method it does it will take the all the data put that the store List<lead> we call as leads
		return leads;
	}

	@Override
	public void deleteLead(long id) {  // we ad to delete purpose unimplemented methods
       leadRepo.deleteById(id);		
	}

	@Override
	public Lead findOneLead(long id) {  //we update purpose create one unimplemented method,return type will change Lead(void to Lead)
		Optional<Lead> findById = leadRepo.findById(id);
		Lead lead = findById.get();
		return lead;
	}

	

}

