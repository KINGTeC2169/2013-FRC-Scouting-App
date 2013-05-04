package com.rtzoeller.frcscouting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	public final static String POSITION = "com.rtzoeller.frcscouting.POSITION";
	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// This array holds the item names for our ListView
		String[] listdata = {"Pit Scouting","Match Scouting", "Match Data Review"};
		
		// Build the ListView from the array
		list = (ListView)findViewById(R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listdata);
		list.setAdapter(adapter);
		
		//Start monitoring the ListView for clicks
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) // Catch onClick events on the ListView
            {
                 // Handle the item click
            	launchScoutingDisplay(v, position);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			Intent intent = new Intent(this, AboutDialogActivity.class);
        	startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void launchScoutingDisplay(View view, int position) {
		switch(position){
		case 0:
			// Pit Scouting selected
			// TODO: Add Pit Scouting activity
			break;
		case 1:
			// Match Scouting selected
			startActivity(new Intent(this, MatchScoutingActivity.class));
			break;
		case 2:
			// Match Data Review selected
			startActivity(new Intent(this, MatchDataReviewActivity.class));
			break;
		default:
			// Something's wrong, log it
			Log.e("MainActivity", "Tried to access invalid list item");
		}
	}
}
