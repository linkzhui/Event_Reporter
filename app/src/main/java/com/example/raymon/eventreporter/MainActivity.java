package com.example.raymon.eventreporter;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements EventFragment.OnItemSelectListener{


    EventFragment listFragment;
    CommentFragment gridFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Life cycle test", "We are at onCreate()");

        //Show different fragment based on the screen size.

//        if(findViewById(R.id.fragment_container)!=null)
//        {
//            Fragment fragment  = isTable()? new CommentFragment():new EventFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
        // Get ListView object from xml.
//        ListView eventListView = (ListView) findViewById(R.id.event_list);
//
////        // Initialize an adapter.
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
////                this,
////                R.layout.event_item,
////                R.id.event_name,
////                getEventNames());
////
////        // Assign adapter to ListView.
////        eventListView.setAdapter(adapter);
//        EventAdapter adapter = new EventAdapter(this);
//        eventListView.setAdapter(adapter);

        //add list view
        if(isTable())
        {
            listFragment = new EventFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.event_container,listFragment).commit();
        }

        //add Gridview
        gridFragment = new CommentFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.comment_container,gridFragment).commit();
    }

    @Override
    public void onItemSelected(int position)
    {
        gridFragment.onItemSelected(position);
    }

    /**
     * A dummy function to get fake event names.
     *
     * @return an array of fake event names.
     */
//    private String[] getEventNames() {
//        String[] names = {
//                "Event1", "Event2", "Event3",
//                "Event4", "Event5", "Event6",
//                "Event7", "Event8", "Event9",
//                "Event10", "Event11", "Event12"};
//        return names;
//
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are at onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }

    private boolean isTable(){
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;

    }
}
