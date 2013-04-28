package com.rtzoeller.frcscouting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MatchDataReviewActivity extends Activity {
	
	ListView matches;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_data_review);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// We need to load match data from disk and store it here, but for now we are using dummy matches
		// TODO: Stop using dummy matches and load from an outside file
		Match match_data[] = new Match[] {
				new Match(1, 2169, 2220, 2175, 118, 254, 1114),
				new Match(2, 2169, 2220, 2175, 118, 254, 1114),
				new Match(3, 2169, 2220, 2175, 118, 254, 1114)
		};
		
		// Build the ListView from the array using our custom ArrayAdapter
		matches = (ListView)findViewById(R.id.matches);
		MatchAdapter adapter = new MatchAdapter(this, R.layout.listview_match, match_data);
		matches.setAdapter(adapter);
		
		//Start monitoring the ListView for clicks
        matches.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) // Catch onClick events on the ListView
            {
                // Handle the item click
            	// TODO: Launch match report
            }
        });
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match_data_review, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
