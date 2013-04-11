package com.rtzoeller.frcscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// This class serves as a template for our match scouting fragments
// The idea behind it was to make data filtering easier to apply to all inputs

public abstract class ScoutingFragment extends Fragment {

	public int lowgoal = 0;
	public int mediumgoal = 0;
	public int highgoal = 0;
	public int pyramidgoal = 0;

	public ScoutingFragment() {
		super();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// We provide a blank View if not overridden
		return null;
	}

	public int increment(int x) {
		// We don't currently filter the score to an upper-bound, so we won't worry about this number
		return ++x;
	}
	
	public int decrement(int x) {
		// We don't want negative numbers for Frisbees scored, so we will prevent the number from going negative
		if (x>0)
			--x;
		return x;
	}
	
}