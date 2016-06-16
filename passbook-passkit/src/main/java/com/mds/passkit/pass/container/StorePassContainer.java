package com.mds.passkit.pass.container;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.mds.passkit.constants.PasskitConstants;
import com.mds.passkit.exception.PasskitException;
import com.mds.passkit.manager.PassKitManager;
import com.mds.passkit.manager.impl.IOSPassKitManager;
import com.mds.passkit.pass.PassContainer;
import com.mds.passkit.utils.PassKitUtils;
import com.ryantenney.passkit4j.Pass;
import com.ryantenney.passkit4j.PassResource;
import com.ryantenney.passkit4j.model.Color;
import com.ryantenney.passkit4j.model.PassInformation;

public class StorePassContainer implements PassContainer{
	
	private Properties properties;
	private String P12_CERTIFICATE_FILE_PATH;
    private String APPLE_WWDRCA_FILE_PATH;
    private String PASSWORD;
    private String P12_CERTIFICATE_ALIAS;
    private String resourcePath;
    private PassKitManager passKitManager;
	
	public void createPass(String fileLocation, String authToken, String serialNumber, PassInformation passInfo) throws IOException{
		
		String resourcePath;
		properties.getProperty(PasskitConstants.Keys.P12_CERTIFICATE_FILE_PATH);
		properties.getProperty(PasskitConstants.Keys.APPLE_WWDRCA_FILE_PATH);
	   	properties.getProperty(PasskitConstants.Keys.P12_CERTIFICATE_PASSWORD);
	    properties.getProperty(PasskitConstants.Keys.P12_CERTIFICATE_ALIAS);
		
		resourcePath = properties.getProperty(PasskitConstants.Keys.RESOURCE_LOCATION_PATH);
		properties = PassKitUtils.getProperties();
		passKitManager = new IOSPassKitManager();
		
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
                .relevantDate(new Date().getTime())
                .description("Just Updating and Testing")
                .files(
                        new PassResource("en.lproj/pass.strings", new File(resourcePath + "/en.lproj/pass.strings")),
                        new PassResource(resourcePath + "/icon.png"),
                        new PassResource(resourcePath + "/icon@2x.png"),
                        new PassResource(resourcePath + "/logo.png"),
                        new PassResource(resourcePath + "/logo@2x.png"),
                        new PassResource(resourcePath + "/strip.png"),
                        new PassResource(resourcePath + "/strip@2x.png")
                )
                .passInformation(passInfo);
        
        try {
			passKitManager.generate(pass, fileLocation, P12_CERTIFICATE_FILE_PATH, APPLE_WWDRCA_FILE_PATH, PASSWORD, P12_CERTIFICATE_ALIAS);
		} catch (PasskitException e) {
			e.printStackTrace();
		}

	}

}
