package com.ultimate.eems.utils;

import java.text.DateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSTrackerActivity extends BroadcastReceiver {
	
    private Context mContext; 
    private Bundle mBundle;
    private Intent mIntent;
    
	private static final String TAG = "SMSTRACKER";
    private static final Uri STATUS_URI = Uri.parse("content://sms");
    
    private SmsSentObserver smsSentObserver = null;
    
	public void onReceive(Context context, Intent intent) {
		try{
			mContext = context;
			mIntent = intent;
			mBundle = intent.getExtras();  
			Log.e(TAG, "Intent Action : "+intent.getAction());
		    if (mBundle != null){
		    	getSMSDetails();
		    }
		    else
		    	Log.e(TAG, "Bundle is Empty!");
		    
		    if(smsSentObserver == null){
			    smsSentObserver = new SmsSentObserver(new Handler(), mContext);
			    mContext.getContentResolver().registerContentObserver(STATUS_URI, true, smsSentObserver);
		    }
		}
		catch(Exception sgh){
			Log.e(TAG, "Error in Init : "+sgh.toString());
		}
	}//fn onReceive

	private void getSMSDetails()
	{	     
	    SmsMessage[] msgs = null;
		try{
			Object[] pdus = (Object[]) mBundle.get("pdus");
			if(pdus != null){
				msgs = new SmsMessage[pdus.length];
				Log.e(TAG, "pdus length : "+pdus.length);
				for(int k=0; k<msgs.length; k++)
				{
					msgs[k] = SmsMessage.createFromPdu((byte[])pdus[k]);  
					Log.e(TAG, "getDisplayMessageBody : "+msgs[k].getDisplayMessageBody());
					Log.e(TAG, "getDisplayOriginatingAddress : "+msgs[k].getDisplayOriginatingAddress());
					Log.e(TAG, "getMessageBody : "+msgs[k].getMessageBody());
					Log.e(TAG, "getOriginatingAddress : "+msgs[k].getOriginatingAddress());
					Log.e(TAG, "getProtocolIdentifier : "+msgs[k].getProtocolIdentifier());
					Log.e(TAG, "getStatus : "+msgs[k].getStatus());
					Log.e(TAG, "getStatusOnIcc : "+msgs[k].getStatusOnIcc());
					
					
					String Time=DateFormat.getDateTimeInstance().format(new Date());
					
					
					String content="Incoming SMS From "+msgs[k].getDisplayOriginatingAddress()+"\n Content : "+msgs[k].getDisplayMessageBody()+"\n Received Time : "+Time;
					
					Log.e(TAG, ""+content);
					Toast.makeText(mContext, content,Toast.LENGTH_LONG).show();
					mContext.sendBroadcast(new Intent("com.aditya.utility.pack.SMS"));
				}
			}
		}
		catch(Exception sfgh){
			Log.e(TAG, "Error in getSMSDetails : "+sfgh.toString());
		}
	}//fn getSMSDetails
	
	
}//End of class SMSTrackerActivity
