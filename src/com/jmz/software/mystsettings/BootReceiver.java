package com.jmz.software.mystsettings;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;

public class BootReceiver extends BroadcastReceiver {

	private static final String BOOT_ACTION_NAME = "android.intent.action.BOOT_COMPLETED";
	
	public void onReceive(Context context, Intent intent)
    {
        if (BOOT_ACTION_NAME.equals(intent.getAction()))
        {
            
        	SharedPreferences prefs = context.getSharedPreferences("com.jmz.software.mystsettings_preferences", 0);
        	boolean restoredText = prefs.getBoolean("sweep2wake", false);
        	  if(restoredText) {
        		  Commands.sweep2wake(1);        		  
        	  } else {
        		  Commands.sweep2wake(0);
        	  }
        	  
        	boolean restoredText2 = prefs.getBoolean("forcecharge", false);
          	  if(restoredText2) {
          		  Commands.forcecharge(1);        		  
          	  } else {
          		  Commands.forcecharge(0);
          	  }
        }
    }
}
