package com.argha.mods;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class Statusbar extends LinearLayout {

    String stat;
	String statusbarcolor;

	public Statusbar(final Context context, AttributeSet attrs) {
		super(context, attrs);
		
	     SharedPreferences sharedPreferences = context.getSharedPreferences("ArghaPrefs",Context.MODE_PRIVATE);    
	     stat = sharedPreferences.getString("statusbarC","#ffffffff");
	     setBackgroundColor(Color.parseColor(stat));
	     
	     BroadcastReceiver mStatbarColorReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context c, Intent i) {
	            statusbarcolor = i.getStringExtra("statusbarc");
		        setBackgroundColor(Color.parseColor(statusbarcolor));
	            SharedPreferences sharedPreferences = context.getSharedPreferences("ArghaPrefs",Context.MODE_PRIVATE);
	            SharedPreferences.Editor editor = sharedPreferences.edit(); 
	            editor.putString("statusbarC", statusbarcolor); 
	            editor.commit();	 
	        }
	        
	    };      
	    context.registerReceiver(mStatbarColorReceiver, new IntentFilter("com.argha.statusbar.CHANGE_STATUSBAR_COLOR"));
	     
	}

}

