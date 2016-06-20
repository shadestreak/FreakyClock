package com.argha.freakyclock;

import net.margaritov.preference.colorpicker.ColorPickerPreference;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;


public class MainActivity extends PreferenceActivity {

    String statusbar;

	@SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.clock_prefs);
        findPreference("arghaxda").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference)
            {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://forum.xda-developers.com/member.php?u=5981481"));
                startActivity(intent);
                return true;
            }
        }
        );
        
        ((ColorPickerPreference)findPreference("statusbarcolor")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

 			@Override
 			public boolean onPreferenceChange(Preference preference, Object newValue) {
 				statusbar = (ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));;
 				preference.setSummary(statusbar);
 	            SharedPreferences sharedPreferences = getSharedPreferences("ArghaPrefs",MODE_PRIVATE);
 	            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
 	            editor.putString("statusbarC", statusbar); //true or false
 	            editor.commit();
 				Intent intent = new Intent();
 				intent.setAction("com.argha.statusbar.CHANGE_STATUSBAR_COLOR");
 				intent.putExtra("statusbarc",statusbar.toString());
 				sendBroadcast(intent);				
 				return false;
 			}

         });
         String statusbarcol = getSharedPreferences("ArghaPrefs",MODE_PRIVATE).getString("statusbarC","#ffffffff"); 
 	    
 	    ((ColorPickerPreference)findPreference("statusbarcolor")).setDefaultValue(statusbarcol);
 	    ((ColorPickerPreference)findPreference("statusbarcolor")).setSummary(statusbarcol);
        

        ((CheckBoxPreference)findPreference("showhide")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference, Object newValue) {
		        if (newValue.toString().equals("true")) {
					Intent intent = new Intent();
					intent.setAction("com.argha.statusbar.HIDE_CLOCK");
					sendBroadcast(intent);
		            SharedPreferences sharedPreferences = getSharedPreferences("ArghaPrefs",MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("switchOnClock", true); //true or false
		            editor.commit();
		        } else {
					Intent intent = new Intent();
					intent.setAction("com.argha.statusbar.UNHIDE_CLOCK");
					sendBroadcast(intent);
		            SharedPreferences sharedPreferences = getSharedPreferences("ArghaPrefs",MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("switchOnClock", false); //true or false
		            editor.commit();
		        }
				return true;
				
			}
        });
        Boolean switchPrefClock = getSharedPreferences("ArghaPrefs",MODE_PRIVATE).getBoolean("switchOnClock",false);
        ((CheckBoxPreference)findPreference("showhide")).setChecked(switchPrefClock);
		
        
        ((ColorPickerPreference)findPreference("clockcolor")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

 			@Override
 			public boolean onPreferenceChange(Preference preference, Object newValue) {
 				String clock = (ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));;
 				preference.setSummary(clock);
 	            SharedPreferences sharedPreferences = getSharedPreferences("ArghaPrefs",MODE_PRIVATE);
 	            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
 	            editor.putString("clockC", clock); //true or false
 	            editor.commit();
 				Intent intent = new Intent();
 				intent.setAction("com.argha.statusbar.CHANGE_CLOCK_COLOR");
 				intent.putExtra("clockc",clock.toString());
 				sendBroadcast(intent);				
 				return false;
 			}

         });
         String clockcol = getSharedPreferences("ArghaPrefs",MODE_PRIVATE).getString("clockC","#ffffffff"); 
 	    
 	    ((ColorPickerPreference)findPreference("clockcolor")).setDefaultValue(clockcol);
 	    ((ColorPickerPreference)findPreference("clockcolor")).setSummary(clockcol);
 	    
        ((CheckBoxPreference)findPreference("hideampm")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference, Object newValue) {
		        if (newValue.toString().equals("true")) {
					Intent intent = new Intent();
					intent.setAction("com.argha.statusbar.HIDEAMPM_CLOCK");
					sendBroadcast(intent);
		            SharedPreferences sharedPreferences = getSharedPreferences("ArghaPrefs",MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("switchamPM", true); //true or false
		            editor.commit();
		        } else {
					Intent intent = new Intent();
					intent.setAction("com.argha.statusbar.UNHIDEAMPM_CLOCK");
					sendBroadcast(intent);
		            SharedPreferences sharedPreferences = getSharedPreferences("ArghaPrefs",MODE_PRIVATE);
		            SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
		            editor.putBoolean("switchamPM", false); //true or false
		            editor.commit();
		        }
				return true;
				
			}
        });
        Boolean hideampm = getSharedPreferences("ArghaPrefs",MODE_PRIVATE).getBoolean("switchamPM",false);
        ((CheckBoxPreference)findPreference("hideampm")).setChecked(hideampm);
    }


}