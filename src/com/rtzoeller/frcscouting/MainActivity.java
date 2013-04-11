package com.rtzoeller.frcscouting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
		
		String[] listdata = {"Pit Scouting","Match Scouting"};
		
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

	public void launchScoutingDisplay(View view, int position) {
		switch(position){
		case 0:
			break;
		case 1:
			startActivity(new Intent(this, MatchScoutingActivity.class));
			break;
		default:
			// Something's wrong, log it
			Log.e("MainActivity", "Tried to access invalid list item");
		}
	}
}
