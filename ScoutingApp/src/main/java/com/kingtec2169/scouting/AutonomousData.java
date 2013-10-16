package com.kingtec2169.scouting;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by rtzoeller on 10/14/13.
 */
public class AutonomousData {
    // Stores the references to the counters
    public static ArrayList<Counter> goals;

    public static void updateReferences(View layout) {
        // Used to store data across fragments
        // This method updates the references to the Views holding the data
        // It should be called upon inflating a new layout in order to re-bind to the views
        // State needs to be saved externally and then set to the refreshed views

        // Wipe/Create a list of our views
        if (goals == null) {
            goals = new ArrayList<Counter>();
        } else {
            goals.clear();
        }

        // Populate the list with updated references
        goals.add(((Counter)layout.findViewById(R.id.high_goal)));
        goals.add(((Counter)layout.findViewById(R.id.medium_goal)));
        goals.add(((Counter)layout.findViewById(R.id.low_goal)));
    }

}
