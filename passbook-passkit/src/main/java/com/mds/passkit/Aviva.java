package com.mds.passkit;

import com.mds.passkit.constants.PasskitConstants;
import com.mds.passkit.exception.PasskitException;
import com.mds.passkit.manager.PassKitManager;
import com.mds.passkit.manager.impl.IOSPassKitManager;
import com.mds.passkit.utils.PassKitUtils;
import com.ryantenney.passkit4j.Pass;
import com.ryantenney.passkit4j.PassResource;
import com.ryantenney.passkit4j.model.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/*
 * Create dummy static pass Aviva pass for development
 */
public class Aviva {

    private PassKitManager passKitManager = new IOSPassKitManager();

    public void generateStorePass(String outFilePath, String authToken, String serialNumber, PassInformation passInfo) throws PasskitException, IOException {
        
    	Properties properties = PassKitUtils.getProperties();
    	// Bypass default Java Security certificate key length 
    	try {
	    		java.lang.reflect.Field field = Class.forName("javax.crypto.JceSecurity").
	    		getDeclaredField("isRestricted");
	    		field.setAccessible(true);
	    		field.set(null, java.lang.Boolean.FALSE);
	    		} catch (Exception ex) {
	    		ex.printStackTrace();
    		}
   
    	// Create Pass in pkpass file with signed certificates 
    	try {
            String resourcePath = properties.getProperty(PasskitConstants.Keys.RESOURCE_LOCATION_PATH);
            
            Location location = new Location();
            location.latitude(28.6139);
            location.longitude(77.2090);
            location.relevantText("Golf Pass Update");
            Barcode barcode = new Barcode(BarcodeFormat.PDF417, "12122");
            Pass pass = new Pass()
                    .teamIdentifier(properties.getProperty(PasskitConstants.Keys.TEAM_IDENTIFIER))
                    .passTypeIdentifier(properties.getProperty(PasskitConstants.Keys.PASS_TYPE_IDENTIFIER))
                    .organizationName(properties.getProperty(PasskitConstants.Keys.ORGANIZATION_NAME))
                    .authenticationToken(authToken)
                    .serialNumber(serialNumber)
                    .webServiceURL(properties.getProperty(PasskitConstants.Keys.WEB_SERVICE_URL))
                    .description(properties.getProperty(PasskitConstants.Keys.DESCRIPTION))
                    .logoText(properties.getProperty(PasskitConstants.Keys.LOGO_TEXT))
                    .foregroundColor(Color.BLACK)
                    .backgroundColor(new Color(254, 220, 56))
                    .relevantDate(new Date())
                    .description(properties.getProperty(PasskitConstants.Keys.ORGANIZATION_NAME))
                    .barcode(barcode)
                    .files(
                            new PassResource("en.lproj/pass.strings", new File(resourcePath + "/en.lproj/pass.strings")),
                            new PassResource(resourcePath + "/icon.png"),
                            new PassResource(resourcePath + "/icon@2x.png"),
                            new PassResource(resourcePath + "/logo.png"),
                            new PassResource(resourcePath + "/logo@2x.png"),
                            new PassResource(resourcePath + "/thumbnail.png")
//                            new PassResource(resourcePath + "/thumbnail@2x.png")
                    )
                    .passInformation(passInfo);

            String P12_CERTIFICATE_FILE_PATH = properties.getProperty(PasskitConstants.Keys.P12_CERTIFICATE_FILE_PATH);
            String APPLE_WWDRCA_FILE_PATH = properties.getProperty(PasskitConstants.Keys.APPLE_WWDRCA_FILE_PATH);
            String PASSWORD = properties.getProperty(PasskitConstants.Keys.P12_CERTIFICATE_PASSWORD);
            String P12_CERTIFICATE_ALIAS = properties.getProperty(PasskitConstants.Keys.P12_CERTIFICATE_ALIAS);
            
            passKitManager.generate(pass, outFilePath, P12_CERTIFICATE_FILE_PATH, APPLE_WWDRCA_FILE_PATH, PASSWORD, P12_CERTIFICATE_ALIAS);
        } catch (Exception e) {
            throw new PasskitException(e);
        }

    }
}
