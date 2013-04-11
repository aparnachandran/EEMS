package com.ultimate.eems.utils;

import java.text.DateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhonecallInfo extends BroadcastReceiver
{
	private Context mContext; 
	
 
private static final String TAG = "CustomBroadcastReceiver";
@Override
public void onReceive(Context context, Intent intent) 
{
	mContext = context;
	Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
    TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();

    telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

 
    Bundle bundle = intent.getExtras();
    String phoneNr= bundle.getString("incoming_number");
    Log.v(TAG, "phoneNr: "+phoneNr);
	
}




	public class CustomPhoneStateListener extends PhoneStateListener
	{
	
			private static final String TAG = "CustomPhoneStateListener";
			Handler handler=new Handler();
			@Override
			public void onCallStateChanged(int state, String incomingNumber) 
			{
				Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
			    Log.v(TAG, incomingNumber);
			   
			    if(!incomingNumber.equalsIgnoreCase(""))
			    {
			    	 	String Time=DateFormat.getDateTimeInstance().format(new Date());
			    		String incomingcall_number=incomingNumber;
					    
			    		String incoming_call_info="UtilityHub User Got an Incoming Call From "+incomingcall_number+" at "+Time;
					    mContext.sendBroadcast(new Intent("com.aditya.utility.pack.SMS"));
					    Toast.makeText(mContext, incoming_call_info,Toast.LENGTH_LONG).show();
			    	
			    	 	
			    }
			   
			   
				super.onCallStateChanged(state, incomingNumber);
			}

	}		



}
