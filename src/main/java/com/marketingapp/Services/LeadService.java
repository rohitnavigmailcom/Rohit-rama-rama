package com.marketingapp.Services;



import java.util.List;



import com.marketingapp.entities.Lead;



public interface LeadService {
	public void saveLead(Lead lead);
	 public List<Lead> getLeads();  //in an interface impl
	public void deleteLead(long id);  // delete purpose we can create the method 
	public Lead findOneLead(long id); //create method update purpose
	
}
