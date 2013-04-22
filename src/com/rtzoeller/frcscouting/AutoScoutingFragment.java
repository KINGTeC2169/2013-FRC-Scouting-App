package com.rtzoeller.frcscouting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AutoScoutingFragment extends ScoutingFragment {

	public static String ARG_SECTION_NUMBER = "section_number";
	
	private LinearLayout auto = null;
	
	public AutoScoutingFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
		setRetainInstance(true);
		final LinearLayout auto = (LinearLayout)inflater.inflate(R.layout.fragment_autonomous_scouting, container, false);
		this.auto = auto;

		((Button)auto.findViewById(R.id.lowgoaldec)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lowgoal = decrement(lowgoal);
				refresh();
			}
		});

		((Button)auto.findViewById(R.id.lowgoalinc)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lowgoal = increment(lowgoal);
				refresh();
			}
		});

		((Button)auto.findViewById(R.id.medgoaldec)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mediumgoal = decrement(mediumgoal);
				refresh();
			}
		});

		((Button)auto.findViewById(R.id.medgoalinc)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mediumgoal = increment(mediumgoal);
				refresh();
			}
		});

		((Button)auto.findViewById(R.id.highgoaldec)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				highgoal = decrement(highgoal);
				refresh();
			}
		});

		((Button)auto.findViewById(R.id.highgoalinc)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				highgoal = increment(highgoal);
				refresh();
			}
		});

		return auto;
	}
	
	@Override
	public String[] serialize() {
		// Return a string array with our autonomous data
		// We ignore the pyramid goal in autonomous so we only pull out the first three elements
		String[] goals = super.serialize();
				
		String[] s = {((EditText)auto.findViewById(R.id.matchnumber)).getText().toString(),
				((EditText)auto.findViewById(R.id.robotnumber)).getText().toString(),
				goals[0], goals[1], goals[2], // Here we are only using the first three goals
				((EditText)auto.findViewById(R.id.comments)).getText().toString()};
		return s;
	}
	
	@Override
	public void nextMatch() {
		// Zero our goal counts
		super.nextMatch();
		// Increment our match number
		((EditText)auto.findViewById(R.id.matchnumber)).setText(Integer.toString(Integer.parseInt(((EditText)auto.findViewById(R.id.matchnumber)).getText().toString()) + 1));
		// Clear our robot number and comments
		((EditText)auto.findViewById(R.id.robotnumber)).setText("");
		((EditText)auto.findViewById(R.id.comments)).setText("");
	}

	@Override
	public void refresh() {
		// We only need to refresh our TextViews, everything else manages itself
		((TextView)auto.findViewById(R.id.lowgoalcounter)).setText(Integer.toString(lowgoal));
		((TextView)auto.findViewById(R.id.medgoalcounter)).setText(Integer.toString(mediumgoal));
		((TextView)auto.findViewById(R.id.highgoalcounter)).setText(Integer.toString(highgoal));
	}
}