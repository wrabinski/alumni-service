package org.camunda.bpm.getstarted.alumniservice;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AlumniErinnerungsMail implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "camundauser@gmail.com";
  private static final String PWD = "pyZ-Bk3-u2u-WY9";

  private final static Logger LOGGER = Logger.getLogger(AbschussarbeitMail.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
	  String name = (String) execution.getVariable("name");
	  String unimail = (String) execution.getVariable("unimail");
	  String anrede = (String) execution.getVariable("anrede");
	  String recipient = unimail;
      String etext ;
	  if ( anrede.equals( "Herr"))
	  {etext ="Sehr geehrter Herr "+ name +", \n\n"
    		  		+ "hiermit m\u00f6chte ich Sie erinnern, dass Sie sich für unser Netzwerk Alumni noch nicht regetriert haben.\n\n"
    		  		+ "Gerne ku00fcnnen Sie sich HIER registrieren.\n\n"
    		  		+ "\n\n Mit freundlichen Gruessen, \n\n Demo Demo";}
      
	  else   {etext ="Sehr geehrte Frau "+ name +", \n\n"
		  		+ "hiermit m\u00f6chte ich Sie erinnern, dass Sie sich für unser Netzwerk Alumni noch nicht regetriert haben.\n\n"
		  		+ "Gerne ku00fcnnen Sie sich HIER registrieren.\n\n"
		  		+ "\n\n Mit freundlichen Gruessen, \n\n Demo Demo";}
	  
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("pruefungsamt@th-brandenburg.org");
          email.setSubject("Alumni registrieren");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          LOGGER.info("Task Assignment Email successfully sent to address: " + recipient); 

        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
        }
      
    }

}
