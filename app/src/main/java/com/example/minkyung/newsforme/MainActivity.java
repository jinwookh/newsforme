package com.example.minkyung.newsforme;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minkyung.newsforme.data.SettingContract;
import com.example.minkyung.newsforme.data.SettingDbHelper;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private SettingDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int columnNumber = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new SettingDbHelper(this);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + SettingContract.SettingEntry.TABLE_NAME, null);
        columnNumber = cursor.getCount();
        try {
            TextView displayView = (TextView) findViewById(R.id.textView_test);
            if (columnNumber != 4 ) {
                displayView.setText("You have to choose 4 media. \nGo to setting page, which is at top right corner!" +"\nYou have choosed" + columnNumber + "media");

                displayView.setTextSize(45);
                displayView.setVisibility(View.VISIBLE);
            }
        } finally {
            cursor.close();
        }

        String[] projection = {
                SettingContract.SettingEntry._ID,
                SettingContract.SettingEntry.COLUMN_URL,
                SettingContract.SettingEntry.COLUMN_NAME,
                 };

        cursor = db.query(
                SettingContract.SettingEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        int urlColumnIndex = cursor.getColumnIndex(SettingContract.SettingEntry.COLUMN_URL);
        int nameColumnIndex = cursor.getColumnIndex(SettingContract.SettingEntry.COLUMN_NAME);

        if (columnNumber == 4) {
            // Use that index to extract the String or Int value of the word
            // at the current row the cursor is on.
            ArrayList<String> urlList = new ArrayList<String>();
            ArrayList<String> nameList = new ArrayList<String>();
            while (cursor.moveToNext()) {

                String currentURL = cursor.getString(urlColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                urlList.add(currentURL);
                nameList.add(currentName);

            }

            if (urlList.size() == 4 && nameList.size() == 4) {
                ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
                SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), urlList, nameList);
                viewPager.setAdapter(adapter);

                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
            }
            else {
                Toast.makeText(getApplicationContext(), "Something wrong with getting data from db. row number is" + urlList.size(), Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_setting:
                Intent intent = new Intent(MainActivity.this, settingActivity.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
