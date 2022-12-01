package com.marketingapp.controller;



import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.Services.LeadService;
import com.marketingapp.dto.LeadData;
import com.marketingapp.entities.Lead;
import com.marketingapp.utility.EmailService;


@Controller  
public class LeadController {
	
	@Autowired
	private LeadService leadService;  
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/viewLeadPage")   
	public String viewSaveLeadPage() {
		return "new_lead";  
	}
//	  @RequestMapping("/saveLead")  
	                                    //bcz from action attribute is saveLead
//       public String saveOneLead(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email,@RequestParam("mobile") long mobile) {
	                                        //@RequestParam is use when u want to copy the form data to the method argument one by one 
	        
	// Requestparam is does it redirect the particular page
	                                      //next form reads the data from using sop method in below
//		Lead l=new Lead();
//		l.setFirstName(firstName);
//		l.setLastName(lastName);        //taking data into requestParam and puting into entityclass like setFirst name
//		l.setEmail(email);
//		l.setMobile(mobile);
//	    leadService.saveLead(l);   //entity calss givinginto repository Serviceimpl it will covert into table
//        return "new_lead";	
//	}
	
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute Lead lead,ModelMap model) { //modelMap this is a springClass works likes request.setAttributes and request.getAttributes (setAttribute doing storing value,setAttribute doing JSP file exchanging viewing exchanging error messages thet all)inssted of get and set use model 
		                            //@ModelAttribute is used when you want to copy the data from the form to entity class
                                   //right entity class name(Lead lead),apply @ModelAttribute ,attribute does when i submit the form it will atometically create Lead object and it will put form data into lead object(everything autometic here) 
	    //view layer call this methods
	    	leadService.saveLead(lead);
	    	emailService.sendSimpleMessage("rohitnavi2723@gmail.com", "Rohit@1999", "Lead is saved!!");
	    	model.addAttribute("msg", "Lead is saved!!"); //model.addAttribute is just like request.setAttribute
	return "new_lead";
	}
//	@RequestMapping("/saveLead")
//	public String saveOneLead(LeadData leadData) { 
		//DTO purpose of this is only to transfer the data from the form to this class object
		//dto to connection with form data
		//dto data transfer object class--this is an ordinary java class to develop intention  copy  from data to this object
		
//		Lead l = new Lead();
//		l.setFirstName(leadData.getFirstName()); //first we copy the data into data transfer object ,,data transfer object copy into entity class object,,entity class is their to database
//	    l.setLastName(leadData.getLastName());   //in web services important of data services
//	    l.setEmail(leadData.getEmail());
//	    l.setMobile(leadData.getMobile());
//	    leadService.saveLead(l);
//		return "new_lead";
//	}
	@RequestMapping("/listall") //next apter impl create and delete purpose
	public String listAll(ModelMap model) {  //do modelMap helps us to exchange the data using the controller and view layer
		List<Lead> leads = leadService.getLeads();
		model.addAttribute("leads", leads); //it is used for copying all the object for do it
		return "lead_search_result";  //redirect page jsp 
		
	}
	@RequestMapping("/delete")  // it is used delete Baton purpose it cannot delete the value it it stores delete id number into console
	public String deleteOneLocation(@RequestParam("id") long id,ModelMap model) {
	   leadService.deleteLead(id);  //create the method on lead Service layer
	   List<Lead> leads = leadService.getLeads();  //them delete the record then it will relocate the feature
		model.addAttribute("leads", leads); 
		return "lead_search_result";  
	}
	@RequestMapping("/update")  // it is used update Baton purpose it cannot update the value it it stores delete id number into console
	public String updateOneLocation(@RequestParam("id") long id,ModelMap model) {
	  Lead lead = leadService.findOneLead(id);  //method create one in service layer
	  model.addAttribute("lead",lead);  //model map call update lead
		return "update_lead"; 
}
	 @RequestMapping("/updateLead")
   	public String updateLead(LeadData lead,ModelMap model) {  //LeadData will be used in update the values purpose
	
	    Lead l= new Lead();  // modeAttribute it is used every time creating new data  ,update purpose we can use LeadData 
	
	    l.setId(lead.getId());//it is id update purpose  same id to update value purpose
	    l.setFirstName(lead.getFirstName());
		 l.setLastName(lead.getLastName());
		 l.setEmail(lead.getEmail());
		 l.setMobile(lead.getMobile());
		 
		 leadService.saveLead(l);  //savelead class will be create
		
	    	model.addAttribute("msg", "Lead is updated!!");
	    	List<Lead> leads = leadService.getLeads();
	   	model.addAttribute("leads", leads);
	return "lead_search_result";
	 }	
}


