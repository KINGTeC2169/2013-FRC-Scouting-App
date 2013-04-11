package com.rtzoeller.frcscouting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AutoScoutingFragment extends ScoutingFragment {

	public static String ARG_SECTION_NUMBER = "section_number";
		
	public AutoScoutingFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Create a new TextView and set its text to the fragment's section
		// number argument value.			
				final LinearLayout auto = (LinearLayout)inflater.inflate(R.layout.fragment_autonomous_scouting, container, false);
				
				((Button)auto.findViewById(R.id.lowgoaldec)).setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		                lowgoal = decrement(lowgoal);
		                ((TextView)auto.findViewById(R.id.lowgoalcounter)).setText(Integer.toString(lowgoal));
		            }
		        });
		        
		        ((Button)auto.findViewById(R.id.lowgoalinc)).setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		            	lowgoal = increment(lowgoal);
		                ((TextView)auto.findViewById(R.id.lowgoalcounter)).setText(Integer.toString(lowgoal));
		            }
		        });
		        
		        ((Button)auto.findViewById(R.id.medgoaldec)).setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		            	mediumgoal = decrement(mediumgoal);
		                ((TextView)auto.findViewById(R.id.medgoalcounter)).setText(Integer.toString(mediumgoal));
		            }
		        });
		        
		        ((Button)auto.findViewById(R.id.medgoalinc)).setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		                mediumgoal = increment(mediumgoal);
		                ((TextView)auto.findViewById(R.id.medgoalcounter)).setText(Integer.toString(mediumgoal));
		            }
		        });
		        
		        ((Button)auto.findViewById(R.id.highgoaldec)).setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		                highgoal = decrement(highgoal);
		                ((TextView)auto.findViewById(R.id.highgoalcounter)).setText(Integer.toString(highgoal));
		            }
		        });
		        
		        ((Button)auto.findViewById(R.id.highgoalinc)).setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		                highgoal = increment(highgoal);
		                ((TextView)auto.findViewById(R.id.highgoalcounter)).setText(Integer.toString(highgoal));
		            }
		        });
		        
		        return auto;
	}
}