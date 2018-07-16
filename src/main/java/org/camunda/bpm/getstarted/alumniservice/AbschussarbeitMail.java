package org.camunda.bpm.getstarted.alumniservice;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AbschussarbeitMail implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "<EMAIL-ADRESS>";
  private static final String PWD = "<PASSWORD>";

  private final static Logger LOGGER = Logger.getLogger(AbschussarbeitMail.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
	  String name = (String) execution.getVariable("name");
	  String first_supervisor  = (String) execution.getVariable("first_supervisor");
	  String second_supervisor  = (String) execution.getVariable("second_supervisor");
	  String anrede = (String) execution.getVariable("anrede");
	  String firstname = (String) execution.getVariable("firstname");
//	  String mnumber = (String) execution.getVariable("mnumber");
	  String program = (String) execution.getVariable("program");
	  String theme = (String) execution.getVariable("theme");
	  String unimail = (String) execution.getVariable("unimail");
	  String degree = (String) execution.getVariable("degree");
	  String date = (String) execution.getVariable("date");
	  String recipient = unimail;
      
	  String etext ;
	  if ( anrede.equals( "Herr"))
	  {etext = 
    		  "Sehr geehrter Herr "+ name +", \n\n"
    		  		+ "Sie haben Ihre "+degree+"abschlussarbeit erfolgreich angemeldet. Folgende Daten sind bei uns eingegangen:\n\n"
    		  		+ "Vorname: "+ firstname +"\n\n"
    		  		+ "Name: "+ name +"\n\n"
//    		  		+ "Matrikelnummer: "+ mnumber +"\n\n"
    		  		+ "Studiengang: "+ program +"\n\n"
    		  		+ "Abschluss: "+ degree +"\n\n"
    		  		+ "Titel der Abschlussarbeit: \n"
    		  			+ theme +"\n\n"
    		  		+ "Ersete Betreuer: "+ first_supervisor+"\n\n"
    		  		+ "Zweit Betreuer: "+ second_supervisor+"\n\n" 
    		  		+ "Abgabedatum: "+ date +"\n"
    		  		+ "\n\n Mit freundlichen Gruessen, \n\n Prüfungsamt der THB";}
	  else  {etext = 
    		  "Sehr geehrte Frau "  + name +", \n\n"
      		  		+ "Sie haben Ihre "+degree+"abschlussarbeit erfolgreich angemeldet. Folgende Daten sind bei uns eingegangen:\n\n"
      		  		+ "Vorname: "+ firstname +"\n\n"
      		  		+ "Name: "+ name +"\n\n"
//      		  	+ "Matrikelnummer: "+ mnumber +"\n\n"
      		  		+ "Studiengang: "+ program +"\n\n"
      		  		+ "Abschluss: "+ degree +"\n\n"
      		  		+ "Titel der Abschlussarbeit: \n"
      		  			+ theme +"\n\n"
      		  		+ "Ersete Betreuer: "+ first_supervisor+"\n\n" 
      		  		+ "Abgabedatum: "+ date +"\n"
      		  		+ "\n\n Mit freundlichen Gruessen, \n\n Prüfungsamt der THB";}
      
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("pruefungsamt@th-brandenburg.org");
          email.setSubject("Abschlussarbeit anmelden");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          LOGGER.info("Task Assignment Email successfully sent to address: " + recipient); 

        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
        }
      
    }

}
