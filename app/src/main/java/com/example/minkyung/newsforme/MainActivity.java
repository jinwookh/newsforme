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

import com.example.minkyung.newsforme.data.SettingContract;
import com.example.minkyung.newsforme.data.SettingDbHelper;

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
                displayView.setText("You have to choose 4 media. \nGo to setting page, which is at top right corner!");
                displayView.setTextSize(45);
                displayView.setVisibility(View.VISIBLE);
            }
        } finally {
            cursor.close();
        }

        if (columnNumber == 4) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
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
