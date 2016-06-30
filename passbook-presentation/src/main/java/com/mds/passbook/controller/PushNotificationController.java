package com.mds.passbook.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mds.passbook.bean.http.GolfHttpUpdates;
import com.mds.passbook.bean.pass.PassRegistrations;
import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.security.dao.UserProfile;
import com.mds.passbook.notification.PassbookNotification;
import com.mds.passbook.service.GolfService;
import com.mds.passbook.service.PassbookService;

/**
 * Apple Wallet webservice which allow Update, Delete, Add new Pass to wallet of
 * an iOS device
 * 
 * @author adityasrivastava
 *
 */
@RestController
public class PushNotificationController {

	public static final Logger logger = LoggerFactory.getLogger(PushNotificationController.class);
	public static String token = "15c19c99888bed405f91785e4140b9f267c3f8fc191556ae562fb96ab31f83f4";

	@Autowired
	GolfService golfService;

	@Autowired
	UserProfileRepository userProfileRepo;

	@Autowired
	PassbookService passbookService;

	/**
	 * Check Server status
	 */
	@RequestMapping(value = "/serverStatus", method = RequestMethod.GET, produces = "text/html")
	public String serverStatus() {
		return "Server Working...";
	}

	@RequestMapping(value = "/createPassbook", method = RequestMethod.GET, produces = "application/vnd.apple.pkpass")
	public ResponseEntity<InputStreamResource> createPass(@RequestParam Map<String, String> requestParams,
			Principal principal) throws IOException {

		HttpHeaders responseHeaders;
		InputStream passInputStream = null;

		responseHeaders = new HttpHeaders();
		
		String username = principal.getName();
		
		UserProfile profile = userProfileRepo.findByEmail(username);

		passbookService.setFileName(username);
		passInputStream = passbookService.createPassbook(Long.valueOf(requestParams.get("gameId")));

		// Setup headers for 0 expiry and no cache
		responseHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		responseHeaders.add("Pragma", "no-cache");
		responseHeaders.add("Expires", "0");
		responseHeaders.setContentDispositionFormData("filename", passbookService.getFileName());
		responseHeaders.setLastModified(new Date().getTime());

		// Send in response
		return ResponseEntity.ok().headers(responseHeaders).contentLength(passbookService.getFileSize())
				.body(new InputStreamResource(passInputStream));
	}

	/**
	 * To Add newly register pass of a device with push token
	 * 
	 * @param deviceLibraryIdentifier
	 *            - Device UUID ( DeviceId )
	 * @param passTypeIdentifier
	 *            - Pass Type Id ( Bundle Id )
	 * @param serialNumber
	 *            - Pass Serial Number
	 * @param payload
	 *            - Wallet request body with Push Token ( Device Token )
	 * @return 201 Status
	 */
	@RequestMapping(value = "/v1/devices/{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{serialNumber}", method = RequestMethod.POST)
	public ResponseEntity<String> addPassbook(@PathVariable("deviceLibraryIdentifier") String deviceLibraryIdentifier,
			@PathVariable("passTypeIdentifier") String passTypeIdentifier,
			@PathVariable("serialNumber") String serialNumber,
			@RequestBody(required = false) Map<String, Object> payload) {

		String token = null;
		logger.info("Adding Passbook......");
		logger.debug("DeviceLib: {} >>> PassType: {} >>> SerialNo.: {}", deviceLibraryIdentifier, passTypeIdentifier,
				serialNumber);
		logger.debug("Request: {}", payload);

		token = payload.get("pushToken").toString();
		logger.info("Push Token {}", token);

		passbookService.addPassbook(serialNumber, deviceLibraryIdentifier, passTypeIdentifier, token);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	/**
	 * Send a push notification to APN server for new updates notification to
	 * devices with registered passes
	 */
	@RequestMapping(value = "/pushNotifications")
	public void pushToken(@RequestParam(name = "hole", required = true) String hole,
			@RequestParam(name = "score", required = true) String score, @RequestParam(name = "gameId") String gameId) {

		String token = passbookService.updateGolfScore(hole, score, gameId);

		PassbookNotification pushNotification = new PassbookNotification();
		pushNotification.initialize(token);
		logger.info("Push notification initiated....");

	}

	/**
	 * Send response with list of serial number of pass which have been recently
	 * updated with last update timestamp in JSON
	 * 
	 * @param deviceLibraryIdentifier
	 *            - Device UUID ( DeviceId )
	 * @param passTypeIdentifier
	 *            - Pass Type Id ( Bundle Id )
	 * @param passesUpdatedSince
	 *            - Updated last timestamp
	 * @param payload
	 *            - Wallet request body
	 * @return JSON string with serial number array and lastUpdated
	 */

	@RequestMapping(value = "/v1/devices/{deviceLibraryIdentifier}/registrations/{passTypeIdentifier:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GolfHttpUpdates> getSerialIdsOfPassForDevice(
			@PathVariable("deviceLibraryIdentifier") String deviceLibraryIdentifier,
			@PathVariable("passTypeIdentifier") String passTypeIdentifier,
			@RequestParam(value = "passesUpdatedSince", required = false) String passesUpdatedSince,
			@RequestBody(required = false) Map<String, Object> payload) {

		logger.info("Sending list of serial no. request for update....");
		logger.debug("DeviceLib: {} >>> PassType: {}", deviceLibraryIdentifier, passTypeIdentifier);

		logger.debug("Update Tag: " + passesUpdatedSince);

		logger.debug("Request: {}", payload);

		GolfHttpUpdates responseUpdates = passbookService.getListOfUpdatePass(deviceLibraryIdentifier, passTypeIdentifier, passesUpdatedSince);
		return new ResponseEntity<GolfHttpUpdates>(responseUpdates, HttpStatus.OK);
	}

	/**
	 * 
	 * @param deviceLibraryIdentifier
	 *            - Device UUID ( DeviceId )
	 * @param passTypeIdentifier
	 *            - Pass Type Id ( Bundle Id )
	 * @return updated new pass in response to device
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1/passes/{passTypeIdentifier}/{serialNumber}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> updatePassbook(
			@PathVariable("passTypeIdentifier") String passTypeIdentifier,
			@PathVariable("serialNumber") String serialNumber,
			@RequestBody(required = false) Map<String, Object> payload, Principal principal) throws IOException {


		logger.info("Generating pass for update response....");
		logger.debug("PassType: {} >>> SerialNo.: {}", passTypeIdentifier, serialNumber);
		logger.debug("Request: {}", payload);

		long unixTime = System.currentTimeMillis() / 1000L;
		
		InputStream passInputStream = null;
		HttpHeaders responseHeaders;
		String fileName = "passbook-"+unixTime;

		responseHeaders = new HttpHeaders();
		
		passbookService.setFileName(fileName);
//		passInputStream = passbookService.updatePassbook(serialNumber, passTypeIdentifier, payload);
		passInputStream = passbookService.createPassbook(Long.valueOf(serialNumber));

		// Setup headers for 0 expiry and no cache
		responseHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		responseHeaders.add("Pragma", "no-cache");
		responseHeaders.add("Expires", "0");
		responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		responseHeaders.setContentDispositionFormData("filename", passbookService.getFileName());
		responseHeaders.setLastModified(new Date().getTime());

		// Send in response
		return ResponseEntity.ok().headers(responseHeaders).contentLength(passbookService.getFileSize())
				.body(new InputStreamResource(passInputStream));
	}

	/**
	 * 
	 * @param deviceLibraryIdentifier
	 *            - Device UUID ( DeviceId )
	 * @param passTypeIdentifier
	 *            - Pass Type Id ( Bundle Id )
	 * @param serialNumber
	 *            - Pass Serial Number
	 * @return 200 Status
	 */
	@RequestMapping(value = "/v1/devices/{deviceLibraryIdentifier}/registrations/{passTypeIdentifier}/{serialNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePassbook(
			@PathVariable("deviceLibraryIdentifier") String deviceLibraryIdentifier,
			@PathVariable("passTypeIdentifier") String passTypeIdentifier,
			@PathVariable("serialNumber") String serialNumber,
			@RequestBody(required = false) Map<String, Object> payload) {

		logger.info("Delete pass as requested by user.....");
		logger.debug("DeviceLib: {} >>> PassType: {} >>> SerialNo.: {}", deviceLibraryIdentifier, passTypeIdentifier,
				serialNumber);
		logger.debug("Request: {}", payload);

		PassRegistrations registrations = new PassRegistrations();
		registrations.setPassTypeId(passTypeIdentifier);
		registrations.setSerialNumber(serialNumber);

		golfService.deletePassRegistrations(registrations);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * Logging device errors from request body sent by a particular Device
	 */
	@RequestMapping(value = "/v1/log", method = RequestMethod.POST)
	public void logPassbookErrors(@RequestBody Map<String, Object> payload) {

		logger.info("Request: {}", payload);

	}
}
