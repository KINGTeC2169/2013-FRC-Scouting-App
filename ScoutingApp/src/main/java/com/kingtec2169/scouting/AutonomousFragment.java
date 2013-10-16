package com.kingtec2169.scouting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by rtzoeller on 10/13/13.
 */
public class AutonomousFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_autonomous, container, false);

        // List to contain the states of the counters that we might want to save
        ArrayList<Counter.State> counterState = new ArrayList<Counter.State>();

        if(AutonomousData.goals == null) {
            // If there aren't references to any views (first run)
            // then configure them
            ((Counter)layout.findViewById(R.id.high_goal)).setFloor(0);
            ((Counter)layout.findViewById(R.id.medium_goal)).setFloor(0);
            ((Counter)layout.findViewById(R.id.low_goal)).setFloor(0);
        } else {
            // If references exist, save their state
            for (Counter counter : AutonomousData.goals) {
                counterState.add(counter.getState());
            }
        }

        // Refresh the references of the counters so that they match the current layout
        // This is necessary so we can bind the saved state to the layout
        AutonomousData.updateReferences(layout);

        // Iterate through and set the state of each Counter to it's saved state
        for (int i = 0; i < counterState.size() && i < AutonomousData.goals.size(); i++) {
            AutonomousData.goals.get(i).setState(counterState.get(i));
        }

        return layout;
    }
}
