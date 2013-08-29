package com.kingtec2169.scouting;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooserActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Build and set the adapter for our data */
        setListAdapter(buildListAdapter());
    }

    private SimpleAdapter buildListAdapter() {
        /* List that holds a map of each row item */
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        /* The names of the TextViews
        The strings in this array do match the layout we bind the data to,
        but they don't need to. Since the the map and the adapter both pull from this
        array, any unique values will work. */
        final String[] viewNames = {"text1", "text2"};
        /* The resource id's of the views we are binding data to */
        final int[] viewResources = {android.R.id.text1, android.R.id.text2};
        /* The xml string-arrays we should pull data from */
        final int[] resources = {R.array.field, R.array.pit};

        /* Iterate through the string arrays and add their contents to the map list */
        for(int resourceId : resources) {
            /* Stores the string array to prevent multiple reads from the xml file*/
            String[] resourceArray = getResources().getStringArray(resourceId);
            /* The map representing the current row */
            Map map = new HashMap();
            /* For ever item in viewNames, add its matching resource to the map */
            for(int j = 0; j < viewNames.length; j++) {
                map.put(viewNames[j], resourceArray[j]);
            }
            /* Add the current row to the list map */
            mapList.add(map);
        }
        /* Build a SimpleAdapter with the data we just packaged */
        return new SimpleAdapter(this, mapList, android.R.layout.simple_list_item_2, viewNames, viewResources);
    }
}
