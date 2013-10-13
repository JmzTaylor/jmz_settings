package com.jmz.software.mystsettings;

import java.io.File;
import java.util.prefs.PreferencesFactory;

import com.htc.preference.*;
import com.htc.widget.ActionBarContainer;
import com.htc.widget.ActionBarExt;
import com.htc.widget.ActionBarText;
import com.stericson.RootTools.RootTools;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.CheckBox;

public class MainActivity extends HtcPreferenceActivity implements HtcPreference.OnPreferenceClickListener {
    
//	public HtcTwoStatePreference led;
//	public HtcTwoStatePreference vibrate;
	public HtcTwoStatePreference sweep2wake;
	public HtcTwoStatePreference forcecharge;
	public SharedPreferences prefs;
	public static ActionBarText mActionText;
	AlertDialog s2wDialog;
	String model = android.os.Build.PRODUCT.toString ();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_main);
//		led = (HtcTwoStatePreference) findPreference("checkbox");
//		led.setOnPreferenceClickListener(this);
//		vibrate = (HtcTwoStatePreference) findPreference("vibrate");
//		vibrate.setOnPreferenceClickListener(this);
		sweep2wake = (HtcTwoStatePreference) findPreference("sweep2wake");
		sweep2wake.setOnPreferenceClickListener(this);
		forcecharge = (HtcTwoStatePreference) findPreference("forcecharge");
		forcecharge.setOnPreferenceClickListener(this);
		SetupActionBar();
		
//		if(!model.toString().equals("mystul")) {
//			vibrate.setEnabled(false);
//			led.setEnabled(false);
//		}
		if (RootTools.isAccessGiven()) {

		} else {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				MainActivity.this);

			alertDialog.setPositiveButton("OK", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});

			alertDialog.setMessage("Root is required for this app");
			alertDialog.setTitle("Jmz Settings");
			alertDialog.show();
		}
		
		File file = new File("/sys/android_touch/sweep2wake");
		if(!file.exists()) {
			sweep2wake.setEnabled(false);
		}
		
		File file2 = new File("/sys/kernel/fast_charge/force_fast_charge");
		if(!file2.exists()) {
			forcecharge.setEnabled(false);
		}
	}
	
	public boolean onPreferenceClick(HtcPreference pref) {
//		if (pref == led) {
//			boolean value = led.isChecked();
//			if(value == true) {
//				Settings.System.putInt(getContentResolver(), "htcledstatus", 1);
//                        }
//			if(value == false) {
//				Settings.System.putInt(getContentResolver(), "htcledstatus", 0);
//			}
//		}
//		if (pref == vibrate) {
//			boolean value = vibrate.isChecked();
//			if(value == true) {
//				Settings.System.putInt(getContentResolver(), "htc_virtual_key_haptic_feedback", 1);
//                        }
//			if(value == false) {
//				Settings.System.putInt(getContentResolver(), "htc_virtual_key_haptic_feedback", 0);
//			}
//		}
		if (pref == sweep2wake) {
			boolean value = sweep2wake.isChecked();
			if(value == true) {
				Commands.sweep2wake(1);
            }
			if(value == false) {
				Commands.sweep2wake(0);
			}
		}
		if (pref == forcecharge) {
			boolean value = forcecharge.isChecked();
			if(value == true) {
				Commands.forcecharge(1);
            }
			if(value == false) {
				Commands.forcecharge(0);
			}
		}
		return true;
	}

	private void SetupActionBar()
	    {
	        Object obj = new ActionBarExt(this, getActionBar());
	        ((ActionBarExt)obj).setFullScreenEnabled(true);
	        ((ActionBarExt)obj).enableHTCLandscape(false);
	        mActionText = new ActionBarText(this);
	        mActionText.setPrimaryText("Jmz Settings");
	        obj = ((ActionBarExt)obj).getCustomContainer();
	        ((ActionBarContainer)obj).setRightDividerEnabled(true);
	        ((ActionBarContainer)obj).addCenterView(mActionText);
	    }
	
	@Override
	protected void onResume() {
	 super.onResume();
	 SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	String str = SP.getString("statusbar_text", "NA");
	Settings.System.putString(getContentResolver(), "custom_statusbartext", str);
	}
	
}