package com.example.minkyung.newsforme;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minkyung on 2017-05-29.
 */
/*This class function as adapter of listView.
Adapter do such things as below.
1. get resource objects by constructor.
2. collaborate with LoadFinished method by making method setNewses(ArrayList<News> data).
3. refines the data by getView method.
    At getView method, textViews will be updated to corresponding data.(use getItem method.)
4. Also, you should set color of respective Fragment at here.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private ArrayList<News> newses;
    // two variable for colors. color is to get value from Fragments, and colorForView is to give value to view.setBackgroundColor method.
    private int color;
    private int colorForView;


    //By constroctor, newses property of NewsAdapter object is initialized.
    public NewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<News> objects, int color) {
        super(context, resource, objects);
        this.newses = objects;
        this.color = color;
    }

    //collaborates with LoadFinished method of Fragments. Though don't know what notifyDataSetChanged() do......
    public void setNewses(ArrayList<News> data) {
        newses.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //set itemView value as converView. And if convertView is null, new inflated view will be granted to itemView.
        View itemView = convertView;
        if(convertView == null)
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        //currentNews is item of specific position.
        News currentNews = getItem(position);

        //Now it is time to update. Search the textView you want to update, and change it with data in currentNews
        TextView title = (TextView)itemView.findViewById(R.id.turtle);
        title.setText(currentNews.getTitle());
        TextView description = (TextView)itemView.findViewById(R.id.description);
        description.setText(currentNews.getDescription());

        //Code below is for making amendment to background color of each tabs.
        //Decided not to have color in background.

        return itemView;
    }
}
