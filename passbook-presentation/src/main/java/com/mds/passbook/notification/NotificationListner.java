package com.mds.passbook.notification;

import com.relayrides.pushy.apns.ApnsClient;
import com.relayrides.pushy.apns.ApnsClientMetricsListener;
import com.relayrides.pushy.apns.ApnsPushNotification;

/*
 * Notification device response listner
 */
public class NotificationListner implements ApnsClientMetricsListener{

	@Override
	public void handleConnectionAttemptFailed(ApnsClient<? extends ApnsPushNotification> arg0) {
		System.out.println("handleConnectionAttemptFailed");
	}

	@Override
	public void handleConnectionAttemptStarted(ApnsClient<? extends ApnsPushNotification> arg0) {
		System.out.println("handleConnectionAttemptStarted");
		
	}

	@Override
	public void handleConnectionAttemptSucceeded(ApnsClient<? extends ApnsPushNotification> arg0) {
		System.out.println("handleConnectionAttemptSucceeded");
		
	}

	@Override
	public void handleNotificationAccepted(ApnsClient<? extends ApnsPushNotification> arg0, long arg1) {
		System.out.println("handleNotificationAccepted");
		
	}

	@Override
	public void handleNotificationRejected(ApnsClient<? extends ApnsPushNotification> arg0, long arg1) {
		System.out.println("handleNotificationRejected");
		
	}

	@Override
	public void handleNotificationSent(ApnsClient<? extends ApnsPushNotification> arg0, long arg1) {
		System.out.println("handleNotificationSent");
		
	}

	@Override
	public void handleWriteFailure(ApnsClient<? extends ApnsPushNotification> arg0, long arg1) {
		System.out.println("handleWriteFailure");
		
	}

}
