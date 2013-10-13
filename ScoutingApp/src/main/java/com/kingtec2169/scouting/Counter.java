package com.kingtec2169.scouting;

import android.content.Context;
import android.graphics.Canvas;
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
    private int step_size = 1;
    private int count = 0;
    private Integer floor = null;

    public Counter(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater layoutInflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.counter, this);

        button_subtract = (Button)findViewById(R.id.button_subtract);
        button_add = (Button)findViewById(R.id.button_add);
        display = (TextView)findViewById(R.id.display);

        button_subtract.setOnClickListener(this);
        button_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == button_subtract) {
            if (floor == null || count > floor) {
                count -= step_size;
                refresh();
            }
        } else if (view == button_add) {
            count += step_size;
            refresh();
        }
    }
    
    private void refresh() {
        display.setText(Integer.toString(count));
    }

    public void setFloor(Integer x) {
        floor = x;
        if (count < floor) {
            count = floor;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int x) {
        count = x;
    }

}
