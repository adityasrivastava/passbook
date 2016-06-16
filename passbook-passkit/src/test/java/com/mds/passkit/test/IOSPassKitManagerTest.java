package com.mds.passkit.test;

import com.mds.passkit.Aviva;
import com.mds.passkit.exception.PasskitException;
import com.mds.passkit.utils.PassKitUtils;
import com.ryantenney.passkit4j.model.Field;
import com.ryantenney.passkit4j.model.PassInformation;
import com.ryantenney.passkit4j.model.StoreCard;
import com.ryantenney.passkit4j.model.TextField;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class IOSPassKitManagerTest {

  @Test
    public  void readCertificate() throws IOException
    {

      Aviva aviva = new Aviva();
      
      long unixTime = System.currentTimeMillis() / 1000L;
      
  	TextField changeField = new TextField("heading", "Head", ""+new Date().getTime());
	changeField.changeMessage("Update with new Pass");
	


      try {
        aviva.generateStorePass("/Users/adityasrivastava/Desktop/file3.pkpass", "12345678912345678", "2221",  new StoreCard()
//        	      .headerFields(new TextField("card", "card_label"))
        			.headerFields(changeField)
        	      .auxiliaryFields(
        	              new TextField("name", "client_name", "Test Client"),
        	              new TextField("id", "client_id", ""+unixTime)
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
