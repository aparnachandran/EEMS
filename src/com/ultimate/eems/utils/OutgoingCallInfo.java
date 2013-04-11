package com.ultimate.eems.utils;

import java.text.DateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OutgoingCallInfo extends BroadcastReceiver {

    
	private Context mContext; 
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Bundle bundle = intent.getExtras();
		mContext = context;
        if(null == bundle)
                return;
        
        String phonenumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        String Time=DateFormat.getDateTimeInstance().format(new Date());
        if(!phonenumber.equalsIgnoreCase(""))
	    {
	    	 	String outgoingcall_number=phonenumber;
			   
			    String outgoing_call_info="EEMS User Trying an Outgoing call to   "+outgoingcall_number+" at "+Time;
			    
			    Toast.makeText(mContext, outgoing_call_info,Toast.LENGTH_LONG).show();
			    
			    mContext.sendBroadcast(new Intent("com.aditya.utility.pack.SMS"));
	    }
       
		
	} 
}

