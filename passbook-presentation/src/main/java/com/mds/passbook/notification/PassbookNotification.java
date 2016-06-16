package com.mds.passbook.notification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.SSLException;

import com.relayrides.pushy.apns.ApnsClient;
import com.relayrides.pushy.apns.ClientNotConnectedException;
import com.relayrides.pushy.apns.PushNotificationResponse;
import com.relayrides.pushy.apns.util.ApnsPayloadBuilder;
import com.relayrides.pushy.apns.util.SimpleApnsPushNotification;

import io.netty.util.concurrent.Future;

/**
 * Push Notification to Apple Push Notification Service for new update of pass for devices.
 */
public class PassbookNotification {
	
	private ApnsClient<SimpleApnsPushNotification> apnsClient;
	private Future<Void> connectFuture;
	private ApnsPayloadBuilder builder;
	
	public void initialize(String token){
		try {
			
		
			// Initiate Apns credentials and wait for response
			
			apnsClient = new ApnsClient<>(new File("logs/Certificates.p12"),"passbookapp");
			apnsClient.setMetricsListener(new NotificationListner());		
			connectFuture = apnsClient.connect(ApnsClient.PRODUCTION_APNS_HOST);
			connectFuture.await();
			
			// Create and send response to APNS
			builder = new ApnsPayloadBuilder();
			SimpleApnsPushNotification notification = new SimpleApnsPushNotification(token,"pass.com.mds.passbookapp", builder.buildWithDefaultMaximumLength());
			Future<PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture = apnsClient.sendNotification(notification);
			
			
			// Get APNS Response for checking
			
			try {
			    final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
			            sendNotificationFuture.get();

			    if (pushNotificationResponse.isAccepted()) {
			        System.out.println("Push notitification accepted by APNs gateway.");
			    } else {
			        System.out.println("Notification rejected by the APNs gateway: " +
			                pushNotificationResponse.getRejectionReason());

			        if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
			            System.out.println("\t…and the token is invalid as of " +
			                pushNotificationResponse.getTokenInvalidationTimestamp());
			        }
			    }
			} catch (final ExecutionException e) {
			    System.err.println("Failed to send push notification.");
			    e.printStackTrace();

			    if (e.getCause() instanceof ClientNotConnectedException) {
			        System.out.println("Waiting for client to reconnect…");
			        apnsClient.getReconnectionFuture().await();
			        System.out.println("Reconnected.");
			    }
			}
			
		} catch (SSLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			Future<Void> disconnectFuture = apnsClient.disconnect();
			try {
				disconnectFuture.await();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
	}
	
}
