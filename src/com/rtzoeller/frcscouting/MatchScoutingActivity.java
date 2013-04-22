package com.rtzoeller.frcscouting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MatchScoutingActivity extends FragmentActivity implements
ActionBar.TabListener {
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_scouting);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_match_scouting, menu);
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
		case R.id.menu_submit:
			String[] autodata = null;
			String[] teleopdata = null;

			// Serialize our autonomous data
			final AutoScoutingFragment auto = (AutoScoutingFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.pager+":0");
			if(auto != null)  // Make sure our fragment has been instantiated
			{
				if(auto.getView() != null) // Make sure the fragment's view has not been destroyed
				{
					// Call our function
					autodata = auto.serialize();
				}
			}

			// Serialize our tele-op data
			final TeleopScoutingFragment teleop = (TeleopScoutingFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:"+R.id.pager+":1");
			if(teleop != null)  // Make sure our fragment has been instantiated
			{
				if(teleop.getView() != null) // Make sure the fragment's view has not been destroyed
				{
					// Call our function
					teleopdata = teleop.serialize();
				}
			}

			// Verify that all of the required fields are filled in
			boolean baddata = false;
			for(int i = 0; i < autodata.length - 1; i++) { // We don't care if the comments are empty
				if (autodata[i].isEmpty()) {
					baddata = true;
					break;
				}
			}
			for(int i = 0; i < teleopdata.length - 1; i++) { // We don't care if the comments are empty
				if (teleopdata[i].isEmpty()) {
					baddata = true;
					break;
				}				
			}

			if(baddata) { // Alert the user if we have an incomplete record
				Toast toast = Toast.makeText(getApplicationContext(), "Incomplete Data, Match Not Submitted", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			} else { // If our form is filled out, save the match data
				String[] matchdata = Arrays.copyOf(autodata, autodata.length + teleopdata.length);
				System.arraycopy(teleopdata, 0, matchdata, autodata.length, teleopdata.length);
				
				// There isn't a good way of passing context to an asynctask but we are going to hope for the best
				// We will build the dialog here and pass it to the asynctask
				// We should probably verify the context is still valid at some point
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setMessage("Match Data has been saved. Would you like to reset data for the next match?").setCancelable(false)
		                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int id) {
		                        // Reset for the next match
		                    	auto.nextMatch();
		                    	teleop.nextMatch();
		                    }
		                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int id) {
		                        // Don't do anything
		                    }
		                });
		        AlertDialog alert = builder.create();
				
				new WriteFile(alert).execute(matchdata);
				Log.d("Save Match Data", matchdata[0]); // Log the match number stored
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// Launch both Autonomous and Tele-op fragments
			Fragment fragment = null;
			Bundle args = null;
			switch(position){
			case 0:
				fragment = new AutoScoutingFragment();
				args = new Bundle();
				args.putInt(AutoScoutingFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				break;
			case 1:
				fragment = new TeleopScoutingFragment();
				args = new Bundle();
				args.putInt(TeleopScoutingFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// Set the titles for the match scoring tabs
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			}
			return null;
		}
	}
	
	private class WriteFile extends AsyncTask<String, Void, String> {
		private Dialog dialog;
		
		private WriteFile(Dialog dialog) {
			this.dialog = dialog;
		}
		
		@Override
		protected String doInBackground(String... data) {
			File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/matchdata.csv");
			try {
				FileWriter writer = new FileWriter(file, true);

				for(int i = 0; i < data.length; i++) {
					writer.write(data[i]);
					writer.write(",");
				}
				
				writer.write("\r\n");
				writer.flush();
				writer.close();

			} catch(IOException e) {
				Log.e("WriteFile", "Unable to write match data");
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String s) {
			// Alert the user that we have saved match data
			dialog.show();
		}
	}
}
