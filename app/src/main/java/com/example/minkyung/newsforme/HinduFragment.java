package com.example.minkyung.newsforme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by minkyung on 2017-06-05.
 */

public class HinduFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    //for Logging usage.
    final String LOG_TAG = HinduFragment.class.getName();
    private NewsAdapter adapter;
    //Every fragment should be empty, and public.
    public HinduFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //use inflater method to create a rootView. This root view will be our base.
        View rootView = inflater.inflate(R.layout.list, container, false);

        //create NewsAdapter object. send super-fresh ArrayList to NewsAdapter constructor.
        //if I declare adapter outside of method at Class and declare as private, there is no need to declare adapter as final at here , even though I am using setOnItemClickListener.
        adapter = new NewsAdapter(getActivity(), 0, new ArrayList<News>(), R.color.color_natural);

        //here comes the usage of rootView: creation of listView.
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //set Adapter of listView.
        listView.setAdapter(adapter);

        //set clickListener at here. This will direct user to corresponding URL.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News currentNews = adapter.getItem(i);
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getUrl()));
                startActivity(myIntent);
            }
        });

        /*first I tried with android.app.LoaderManager, but initLoader didn't recognized NationalFragment as a LoaderCallback implemented class.
        Even though I coded NationalFragment to implement android.app.LoaderManager, initLoader demanded android.support.v4.app LoaderCallback class implemented object.
        So I revised android.app.LoaderManager to android.v4.app.LoaderManager. Still don't know the reason...
         */
        getLoaderManager().initLoader(1, null, HinduFragment.this);

        //another usage of rootView: itself is a return value.
        return rootView;
    }

    @Override
    //which place would be good to put initLoader method at here or at onCreateView?
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ArrayList<News>> loader, ArrayList<News> data) {
        //if there is no data, method is finished.
        if(data == null)
            return;

        //if there is data, adapter is cleared.

        adapter.clear();

        //Adapter absorbs the data. Remember Adapter has been cleared at recent code.
        if(data != null && !data.isEmpty())
            adapter.addAll(data);
        Log.i(LOG_TAG, "Load finished");
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
        Log.i(LOG_TAG, "Loader reset");
        //NotifyDataSetChanged() will be evoked at NewsAdapter.
        adapter.setNewses(new ArrayList<News>());



    }

    //At here, you have to create NewsAsyncLoader object and return it.

    @Override
    public android.support.v4.content.Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "Loader created");
        //Since first parameter has to be Context, I used getActivity() method. Do not know what exact Activity is though.
        //Used getString method to convert R.String resource ID to String class.
        return new NewsAsyncLoader(getActivity() ,getString(R.string.the_hindu_api));
    }
}
