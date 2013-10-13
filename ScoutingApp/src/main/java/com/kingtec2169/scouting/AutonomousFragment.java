package com.kingtec2169.scouting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rtzoeller on 10/13/13.
 */
public class AutonomousFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_autonomous, container, false);

        ((Counter)layout.findViewById(R.id.high_goal)).setFloor(0);
        ((Counter)layout.findViewById(R.id.medium_goal)).setFloor(0);
        ((Counter)layout.findViewById(R.id.low_goal)).setFloor(0);

        return layout;
    }
}
