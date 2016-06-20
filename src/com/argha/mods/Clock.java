package com.argha.mods;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class Clock extends TextView {
	

	Calendar mCalendar;
	String mHour;
	String mMin;
	String mAm;
	SimpleDateFormat mH;
	SimpleDateFormat mM;
	SimpleDateFormat mA;
	Context context;
	String sclock;
	Boolean sclocks;
	Boolean sampms;
    
	
	public Clock(final Context context, AttributeSet attrs) {
		super(context, attrs);
		
	     SharedPreferences sharedPreferences = context.getSharedPreferences("ArghaPrefs",Context.MODE_PRIVATE);    
	     sclock = sharedPreferences.getString("clockC","#ff000000");
	     setTextColor(Color.parseColor(sclock));
	     sclocks = sharedPreferences.getBoolean("switchOnClock", false);
	        if (sclocks == false){
	            setVisibility(0);
	        } else {
		        setVisibility(GONE);
	        } 
	        
	   		        
	      final Handler h = new Handler();
	       h.post(new Runnable() {
	      @Override
	      public void run() {
	          updateTime();
	          h.postDelayed(this, 1000);
	      }
	           }); 
	       
	       BroadcastReceiver mclockcolorReceiver = new BroadcastReceiver() {
	           @Override
	           public void onReceive(Context c, Intent i) {
	         	   String clockColor = i.getStringExtra("clockc");
	         	   setTextColor(Color.parseColor(clockColor));
	               SharedPreferences sharedPreferences = context.getSharedPreferences("ArghaPrefs",Context.MODE_PRIVATE);
	               SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
	               editor.putString("clockC", clockColor); //true or false
	               editor.commit();	 
	           }
	           
	       };
           BroadcastReceiver mclockHideReceiver = new BroadcastReceiver() {
               @Override
               public void onReceive(Context c, Intent i) {
             	   setVisibility(GONE);
                   SharedPreferences sharedPreferences = context.getSharedPreferences("ArghaPrefs",Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                   editor.putBoolean("switchOnClock", true); //true or false
                   editor.commit();	 
               }
               
           };
           BroadcastReceiver mclockUnhideReceiver = new BroadcastReceiver() {
               @Override
               public void onReceive(Context c, Intent i) {
             	   setVisibility(VISIBLE);
                   SharedPreferences sharedPreferences = context.getSharedPreferences("ArghaPrefs",Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
                   editor.putBoolean("switchOnClock", false); //true or false
                   editor.commit();	 
               }
               
           };
	       
	       context.registerReceiver(mclockcolorReceiver, new IntentFilter("com.argha.statusbar.CHANGE_CLOCK_COLOR"));  
	       context.registerReceiver(mclockHideReceiver, new IntentFilter("com.argha.statusbar.HIDE_CLOCK"));
	       context.registerReceiver(mclockUnhideReceiver, new IntentFilter("com.argha.statusbar.UNHIDE_CLOCK"));
	}
		
		

	protected void updateTime() {
		mCalendar = Calendar.getInstance();
		mH = new SimpleDateFormat ("h");
		//mA = new SimpleDateFormat ("aa");
		mM = new SimpleDateFormat ("mm");
		mHour = mH.format(mCalendar.getTime());
		mMin = mM.format(mCalendar.getTime());
		//mAm = mA.format(mCalendar.getTime());
		setTextSize(19);
		setText(""+mHour+":"+mMin);
	}
		
	}

