package org.camunda.bpm.getstarted.alumniservice;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AlumniRegistriertMail implements JavaDelegate {

  // TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "camundauser@gmail.com";
  private static final String PWD = "pyZ-Bk3-u2u-WY9";

  private final static Logger LOGGER = Logger.getLogger(AlumniRegistriertMail.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
	  String name = (String) execution.getVariable("name");
	  String pmail = (String) execution.getVariable("pmail");
	  String degree = (String) execution.getVariable("degree");
	  String anrede = (String) execution.getVariable("anrede");
 
	  String recipient = pmail;
      String etext;
      if ( anrede.equals( "Herr"))
      {etext = "Sehr geehrter Herr "+ name +", \n\n"
    		  		+ "herzlichen Dank für die Registration als Alumni. Wir wünschen Ihnen viel Glück beim abschließen Ihres "+ degree +"studiengangs und wünschen Ihnen viel Erfolg in Ihrem zukünftigen, beruflichen Werdegang.\n\n"
    		  		+ "Es freut uns sehr, dass Sie als Alumnus mit der Technischen Hochschule Brandenburg in Kontakt bleiben wollen. Der Alumni-Service kann Sie mit dem Newsletter der THB, Veranstaltungen, Aktionen und anderen Aktivitäten der Hochschule versorgen. Sollten Sie Fragen haben, können Sie sich jeder Zeit per Email bei uns melden.\n"
    		  		+ "\n Mit freundlichen Gruessen, \n\n Der Alumni-Service der THB";}
      
      else  {etext = "Sehr geehrte Frau "+ name +", \n\n"
		  		+ "herzlichen Dank für die Registration als Alumni. Wir wünschen Ihnen viel Glück beim abschließen Ihres "+ degree +"studiengangs und wünschen Ihnen viel Erfolg in Ihrem zukünftigen, beruflichen Werdegang.\n\n"
		  		+ "Es freut uns sehr, dass Sie als Alumna mit der Technischen Hochschule Brandenburg in Kontakt bleiben wollen. Der Alumni-Service kann Sie mit dem Newsletter der THB, Veranstaltungen, Aktionen und anderen Aktivitäten der Hochschule versorgen. Sollten Sie Fragen haben, können Sie sich jeder Zeit per Email bei uns melden.\n"
		  		+ "\n Mit freundlichen Gruessen, \n\n Der Alumni-Service der THB";}
      
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("alumniservice@th-brandenburg.org");
          email.setSubject("Alumni Registration");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          LOGGER.info("Task Assignment Email successfully sent to address: " + recipient); 

        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
        }
      
    }

}
