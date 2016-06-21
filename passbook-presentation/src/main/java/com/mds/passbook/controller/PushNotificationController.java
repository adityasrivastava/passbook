package com.mds.passbook.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.mds.passbook.bean.GameUpdate;
import com.mds.passbook.bean.Golf;
import com.mds.passbook.bean.GolfPass;
import com.mds.passbook.bean.GolfScore;
import com.mds.passbook.bean.PassRegistrations;
import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.dao.GolfDao;
import com.mds.passbook.data.repository.dao.GolfScoreDao;
import com.mds.passbook.data.repository.dao.PassRegistrationsDao;
import com.mds.passbook.data.repository.dao.UserProfile;
import com.mds.passbook.notification.PassbookNotification;
import com.mds.passbook.service.GolfService;
import com.mds.passbook.service.PassbookService;
import com.mds.passkit.GeneratePass;
import com.mds.passkit.GolfWallet;

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

		passInputStream = passbookService.createPassbook(requestParams, profile.getUserId());

		// Setup headers for 0 expiry and no cache
		responseHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		responseHeaders.add("Pragma", "no-cache");
		responseHeaders.add("Expires", "0");
		responseHeaders.setContentDispositionFormData("filename", "file1.pkpass");
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
	public ResponseEntity<GameUpdate> getSerialIdsOfPassForDevice(
			@PathVariable("deviceLibraryIdentifier") String deviceLibraryIdentifier,
			@PathVariable("passTypeIdentifier") String passTypeIdentifier,
			@RequestParam(value = "passesUpdatedSince", required = false) String passesUpdatedSince,
			@RequestBody(required = false) Map<String, Object> payload) {

		logger.info("Sending list of serial no. request for update....");
		logger.debug("DeviceLib: {} >>> PassType: {}", deviceLibraryIdentifier, passTypeIdentifier);

		logger.debug("Update Tag: " + passesUpdatedSince);

		logger.debug("Request: {}", payload);

		List<PassRegistrations> register = golfService.findUpdatedPass(passTypeIdentifier, deviceLibraryIdentifier);
		List<String> serialNumbersList = new ArrayList<String>();

		GameUpdate update = new GameUpdate();
		update.setLastUpdated(update.currentTimeStamp());

		for (PassRegistrations passRegi : register) {
			serialNumbersList.add(passRegi.getSerialNumber());
		}

		String[] passRegistrationsArr = serialNumbersList.toArray(new String[serialNumbersList.size()]);

		update.setSerialNumbers(passRegistrationsArr);

		return new ResponseEntity<GameUpdate>(update, HttpStatus.OK);
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
		
		InputStream passInputStream = null;
		HttpHeaders responseHeaders;
		List<GolfScoreDao> golfDaoList = null;
		List<com.mds.passkit.GolfScore> scores = null;
		GolfWallet wallet;

		responseHeaders = new HttpHeaders();

		wallet = new GolfWallet();

		golfDaoList = golfService.getScoresById(Long.valueOf(serialNumber));

		GolfDao golfDao = golfDaoList.get(0).getGolf();

		scores = passbookService.generateGolfScore(golfDao, golfDaoList);

		passbookService.generatePass("passes/file3.pkpass", scores);

		// Get new generated pass
		passInputStream = passbookService.readPassFile("passes/file3.pkpass");

		// Setup headers for 0 expiry and no cache
		responseHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
		responseHeaders.add("Pragma", "no-cache");
		responseHeaders.add("Expires", "0");
		responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		responseHeaders.setContentDispositionFormData("filename", "file3.pkpass");
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
