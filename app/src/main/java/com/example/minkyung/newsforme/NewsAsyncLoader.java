package com.example.minkyung.newsforme;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by minkyung on 2017-05-29.
 */

public class NewsAsyncLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<News>> {

    public static final String LOG_TAG = NewsAsyncLoader.class.getName();
    private String urlString;


    public NewsAsyncLoader(Context context, String URL)  {

        super(context);
        this.urlString = URL;


    }

    @Override
    public ArrayList<News> loadInBackground() {
        Log.i(LOG_TAG, "Load In Background, another thread");


        // Create URL object
        URL url = createUrl(urlString);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {


            // TODO Handle the IOException
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<News> newses = extractFeatureFromJson(jsonResponse);


        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return newses;



    }

    @Override
    protected void onStartLoading() {

        super.onStartLoading();
        Log.i(LOG_TAG, "Start loading");
        forceLoad();

    }

    private ArrayList<News> extractFeatureFromJson(String newsJSON) {
        ArrayList<News> newses = new  ArrayList<News>();
        if(TextUtils.isEmpty(newsJSON))
            return null;
        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONArray articles = baseJsonResponse.getJSONArray("articles");

            // If there are results in the features array
            for (int i = 0; i < articles.length(); i ++) {
                JSONObject properties = articles.getJSONObject(i);
                String title = properties.getString("title");
                String description = properties.getString("description");

                String url = properties.getString("url");





               News news = new News(title, description,url);
                newses.add(news);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return newses;
    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        if(url == null) {
            return jsonResponse;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else {
                Log.e("makeHttpRequest", "connection code is not 200. It is" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            //Log.e("e",urlConnection.getResponseMessage()) does not work. If getResponseMessage is included, Log does not show up.
            e.printStackTrace();

            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close(); //IOException occurs at here too.
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
