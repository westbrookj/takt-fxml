/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktfxml;

import java.util.ArrayList;
import java.util.Properties;
import javafx.application.Platform;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author westbrookj
 */
public class SendEmail implements Runnable
{
    private ArrayList<String> emailList;
    private Properties props;
    private Session session;
    private Message message;
    
    public void SendEmail()
    {
        emailList = TAKTFXMLModel.getEmailList();
        
        props = new Properties();
        props.put("mail.smtp.host", "10.1.1.9");
        props.put("mail.smtp.port", "25");
        
        session = Session.getInstance(props, null);
    }
    
    @Override
    public void run()
    {
        Platform.runLater(() -> {
            try
            {
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress("TAKTClock@nu-way.net"));
                for(int i = 0; i < TAKTFXMLModel.getNumberOfEmails(); i++)
                {
                    if(emailList.get(i) != null)
                    {
                        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(emailList.get(i)));
                    }
                }
                message.setSubject("TAKT Clock Notification: Part Number " + TAKTFXMLModel.getPartNumber());
                message.setText("Hello,\nThe unit with part number " + TAKTFXMLModel.getPartNumber() + 
                        " has reached the set unit goal of " + TAKTFXMLModel.getUnitGoal() + " units.\n\n" +
                        "This is an auto-generated e-mail. Please do not reply to this address.");

                Transport.send(message);
            }catch(MessagingException e)
            {
                throw new RuntimeException(e);
            }
        });
    }
}
