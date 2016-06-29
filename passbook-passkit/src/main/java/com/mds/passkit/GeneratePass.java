package com.mds.passkit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.mds.passkit.constants.PasskitConstants;
import com.mds.passkit.exception.PasskitException;
import com.mds.passkit.pass.container.StorePassContainer;
import com.mds.passkit.utils.PassKitUtils;
import com.ryantenney.passkit4j.model.DateField;
import com.ryantenney.passkit4j.model.EventTicket;
import com.ryantenney.passkit4j.model.Field;
import com.ryantenney.passkit4j.model.Generic;
import com.ryantenney.passkit4j.model.TextAlignment;
import com.ryantenney.passkit4j.model.TextField;

/*
 * Generate new pass and store to system configure file in myapplication.properties file in resources
 */
public class GeneratePass {
	
	private Properties properties;
	private StorePassContainer storeCardPass;
	
	public void createGenericPass(String passLocation, List<GolfScore> wallet) throws IOException{
		
		List<Field<?>> scoreFields = new ArrayList<>();
		int totalScore = 0;
		int totalPar = 0;
		int count = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("E dd.mm.yyyy 'at' hh:mm a");
		
		
		scoreFields.add(new TextField("datetime", "Date & Time", format.format(new Date())));
		
		for(GolfScore score: wallet){
			totalScore += score.getScore();
			totalPar += score.getPar();
			
			String score_;
			
			if(score.getScore() == 0){
				score_ = "N/A";
			}else{
				score_ = Integer.toString(score.getScore());
			}
			
			String fieldText = "Score "+ score_ +" , "+ score.getPar()+" Par, "+score.getStroke()+" Stroke, "+score.getTeeType()+" Tee, "+score.getYards()+" Yards";
			scoreFields.add(new TextField("hole_"+count++,"Hole "+count, fieldText));
		}

		properties = PassKitUtils.getProperties();
		
		String url = properties.getProperty(PasskitConstants.Keys.WEB_SERVICE_URL);
		String serialNumber = wallet.get(0).getUser().getSerialNumber();
		
		scoreFields.add(new TextField("update_url", "Update Url: ", "<a href='"+url+"/update?game_id="+serialNumber+"'>Update Pass</a>"));
		
		long unixTime = System.currentTimeMillis() / 1000L;
		Aviva aviva = new Aviva();
		
		TextField changeField = new TextField("heading", "Score", ""+totalScore);
		changeField.changeMessage("Update with new Pass");

	      try {
	        aviva.generateStorePass(passLocation, "12345678912345678", wallet.get(0).getUser().getSerialNumber(),  new Generic()
	        	      .headerFields(changeField)
	        	      .primaryFields(new TextField("course_name","Course",wallet.get(0).getUser().getGolfCourseName()))
	        	      .auxiliaryFields(
	        	    		  new TextField("game","Game", wallet.get(0).getUser().getGolfHoleType()+" Hole").textAlignment(TextAlignment.LEFT),
	        	              new TextField("par", "Par", ""+totalPar).textAlignment(TextAlignment.RIGHT)
	        	      )
	        	      .secondaryFields(
	        	    		  new TextField("age","Age", wallet.get(0).getUser().getUserGender().charAt(0)+wallet.get(0).getUser().getUserAge()).textAlignment(TextAlignment.LEFT),
	        	              new TextField("name", "Name", ""+wallet.get(0).getUser().getUserName()).textAlignment(TextAlignment.RIGHT)
	        	    		 )
	        	      .backFields(scoreFields)
	        	    );
	      } catch (PasskitException e) {
	        e.printStackTrace();
	      }
	}
	
	public void createPass(String passLocation, String serialNumber, String name, String age, String gender) throws IOException{
		
		properties = PassKitUtils.getProperties();
		
		long unixTime = System.currentTimeMillis() / 1000L;
		Aviva aviva = new Aviva();
		
		TextField changeField = new TextField("heading", "Gulf Course", ""+unixTime);
		changeField.changeMessage("Update with new Pass");

	      try {
	        aviva.generateStorePass(passLocation, "12345678912345678", serialNumber,  new EventTicket()
	        	      .headerFields(changeField)
//	        	      .primaryFields(new TextField("client", name))
	        	      .auxiliaryFields(
	        	    		  new TextField("gender","Gender", gender),
	        	              new TextField("score", "Score", ""+43)
	        	      )
	        	      .secondaryFields(
	        	    		  new TextField("age","Age", age),
	        	              new TextField("name", "Name", ""+name)
	        	    		 )
	        	      .backFields(
	        	              new TextField("address", "address_label", "address_value"),
	        	              new TextField("phone", "phone_label", "phone_value"),
	        	              new TextField("email", "email_label", "email_value"),
	        	              new TextField("site", "site_label", "site_value"),
	        	              new TextField("disclaimer", "disclaimer_label", "disclaimer_value")
	        	              
	        	      ));
	      } catch (PasskitException e) {
	        e.printStackTrace();
	      }
	}
}
