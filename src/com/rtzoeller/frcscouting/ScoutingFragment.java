package com.rtzoeller.frcscouting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ScoutingFragment extends Fragment {

	public int lowgoal = 0;
	public int mediumgoal = 0;
	public int highgoal = 0;
	public int pyramidgoal = 0;

	public ScoutingFragment() {
		super();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return null;
	}

	public int increment(int x) {
		return ++x;
	}
	
	public int decrement(int x) {
		if (x>0)
			--x;
		return x;
	}
	
}