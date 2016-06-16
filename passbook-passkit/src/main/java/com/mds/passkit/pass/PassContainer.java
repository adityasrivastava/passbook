package com.mds.passkit.pass;

import java.io.IOException;
import java.util.Properties;

import com.ryantenney.passkit4j.model.PassInformation;

public interface PassContainer {
	void createPass(String fileLocation, String authToken, String serialNumber, PassInformation passInfo) throws IOException;
}
