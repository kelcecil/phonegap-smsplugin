 package com.cecilresearch.phonegap.plugin.SMS;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SMS extends Plugin {
	
	public static final String SEND = "send";
	public static final String COMPOSE = "compose";
	public static final String MULTIPART_SEND = "multipart";
	
	public static String MESSAGE = "";
	
	public enum SMS_STATUS { SUCCESS, GENERIC_FAILURE, NO_SERVICE, NULL_PDU, RADIO_OFF, NOT_DELIVERED };
	
	String returnValue = "";
	
    @Override
    public PluginResult execute(String action, JSONArray data, String callbackId) {
    	Log.i("execute","Entering execute");
    	PluginResult result = null;
    	
    	if (SEND.equals(action)) {
    			try {
					sendSingleSMS(data.getString(0),data.getString(1));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
    	}
    	else if (MULTIPART_SEND.equals(action)){
    		
    	}
    	else {
    		Log.e("execute", "Unknown action. Returning null");
    		result = new PluginResult(Status.INVALID_ACTION, "Invalid Action");
    		return result;
    	}
    	    	    	
    	result = new PluginResult(Status.OK);
    	return result;
    }
    
    public static void setStatusMessage(String message) {
    	MESSAGE = message;
    }
    
    private void sendSingleSMS(String phoneNumber, String message) {
    	final String SENT = "SMS_SENT";
    	    	
    	PendingIntent sent = PendingIntent.getBroadcast(this.ctx.getContext(), 0, new Intent(SENT), 0);
    	
    	this.ctx.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				switch(this.getResultCode()) {
				case Activity.RESULT_OK:
					Log.i("sendSMS","SMS sent");
					Toast.makeText(context, "SMS Sent!", 5000).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Log.i("sendSMS","General failure");
					SMS.setStatusMessage("GENERIC_FAILURE");
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Log.i("sendSMS","No service");
					Toast.makeText(context, "Service is currently unavailable!", 5000).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Log.i("sendSMS","No PDU");
					returnValue = "NULL_PDU";
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Log.i("sendSMS","Radio is off");
					Toast.makeText(context, "Check your network settings and try again!", 5000).show();
					break;
				}
			}
    	}, new IntentFilter(SENT)); 

    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(phoneNumber, null, message, sent, null);
    }
}