package com.kingtec2169.scouting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by rtzoeller on 8/29/13.
 */
public class Counter extends LinearLayout implements View.OnClickListener {
    private Button button_subtract;
    private Button button_add;
    private TextView display;
    private State state;

    public Counter(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater layoutInflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.counter, this);

        state = new State();

        button_subtract = (Button)findViewById(R.id.button_subtract);
        button_add = (Button)findViewById(R.id.button_add);
        display = (TextView)findViewById(R.id.display);

        button_subtract.setOnClickListener(this);
        button_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == button_subtract) {
            // Make sure we don't go below the floor, if one exists
            if (state.floor == null || state.count > state.floor) {
                state.count -= state.step_size;
                refresh();
            }
        } else if (view == button_add) {
            state.count += state.step_size;
            refresh();
        }
    }
    
    private void refresh() {
        // Update the display to show the new count
        display.setText(Integer.toString(state.count));
    }

    public void setFloor(Integer x) {
        // Set the floor
        state.floor = x;
        // Check if a floor exists and force our count to be above the floor if one does.
        if (state.floor != null && state.count < state.floor) {
            state.count = state.floor;
            // Refresh the counter to show the new value
            refresh();
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        // Set the state of the counter to the passed parameter, then
        // refresh the display to match the new data
        this.state = state;
        refresh();
    }

    public class State {
        private int count = 0;
        private Integer floor = null;
        private int step_size = 1;

        public int save() {
            // Return the data that we need to save for the match
            // This isn't the entire state, it is only the count.
            // We don't care about the step size used or the floor value
            // when we are analyzing the match results, so we won't return it
            return count;
        }
    }

}
