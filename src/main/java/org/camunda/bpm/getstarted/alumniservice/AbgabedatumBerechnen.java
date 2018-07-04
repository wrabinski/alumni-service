package org.camunda.bpm.getstarted.alumniservice;
import java.util.Calendar;
import java.util.Date ;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.text.SimpleDateFormat;

public class AbgabedatumBerechnen implements JavaDelegate {
	
	public void execute(DelegateExecution execution) throws Exception{
		String  degree = (String) execution.getVariable("degree");
		
	    Calendar c = Calendar.getInstance();
	          
	   
	           SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	           
	           
	           
	           if (degree == "Bachelor" ) {
	        	   c.add(Calendar.WEEK_OF_MONTH, 8);
		        	 Date date1 = c.getTime();
     	        	 String date = format.format(date1);
     	        	 
		        	 Date odate = c.getTime();
     		           
     		           execution.setVariable("date", date);
     		           execution.setVariable("odate", odate);
          }
	           else {
	        	   c.add(Calendar.MONTH, 5);
	        	   Date date2 = c.getTime();  	        	  
	        	   String date = format.format(date2);
	        	   
	        	   Date odate = c.getTime();
  		           
  		           execution.setVariable("date", date);
  		           execution.setVariable("odate", odate);
	           }
	           
	           

	          
//	          if(degree.equals("Bachelor")){
//	        	  c.add(Calendar.WEEK_OF_MONTH, 8);
//	        	  date2 = c.getTime();
////	        	  System.out.println(date3); 
//	          }	
//	          else {
//	        	  c.add(Calendar.MONTH, 5);
//	        	  date3 = c.getTime();
////	        	  System.out.println(date3);
//	          }	    
			   
	}
 }




    