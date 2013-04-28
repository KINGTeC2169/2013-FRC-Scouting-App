package com.rtzoeller.frcscouting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MatchAdapter extends ArrayAdapter<Match>{
	Context context;
	int layoutResourceId;
	Match data[] = null;
	
	public MatchAdapter(Context context, int layoutResourceId, Match[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		MatchHolder holder = null;
		
		if(row == null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new MatchHolder();
			holder.matchNumber = (TextView)row.findViewById(R.id.matchnumber);
			holder.red1 = (TextView)row.findViewById(R.id.red1);
			holder.red2 = (TextView)row.findViewById(R.id.red2);
			holder.red3 = (TextView)row.findViewById(R.id.red3);
			holder.blue1 = (TextView)row.findViewById(R.id.blue1);
			holder.blue2 = (TextView)row.findViewById(R.id.blue2);
			holder.blue3 = (TextView)row.findViewById(R.id.blue3);
			
			row.setTag(holder);
		} else {
			holder = (MatchHolder)row.getTag();
		}
		
		Match match = data[position];
		holder.matchNumber.setText(Integer.toString(match.matchNumber));
		holder.red1.setText(Integer.toString(match.red1));
		holder.red2.setText(Integer.toString(match.red2));
		holder.red3.setText(Integer.toString(match.red3));
		holder.blue1.setText(Integer.toString(match.blue1));
		holder.blue2.setText(Integer.toString(match.blue2));
		holder.blue3.setText(Integer.toString(match.blue3));
		return row;
	}
	
	static class MatchHolder {
		TextView matchNumber;
		TextView red1;
		TextView red2;
		TextView red3;
		TextView blue1;
		TextView blue2;
		TextView blue3;
	}
}
