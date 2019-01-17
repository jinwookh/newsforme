package com.example.minkyung.newsforme;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.minkyung.newsforme.data.SettingContract;
import com.example.minkyung.newsforme.data.SettingDbHelper;

import java.util.ArrayList;


public class settingActivity extends AppCompatActivity {

    private SettingDbHelper mDbHelper;
    private CheckBox cb_al;
    private CheckBox cb_am;
    private CheckBox cb_ver;
    private CheckBox cb_ars;
    private CheckBox cb_na;
    private CheckBox cb_hin;
    private CheckBox cb_hack;
    private CheckBox cb_kor;
    private CheckBox cb_jap;
    private CheckBox cb_china;
    private CheckBox cb_taiwan;




    private int count = 0;
    private int max = 4;
    //will count will be refreshed every time when setting view is opened?

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        cb_al = (CheckBox)findViewById(R.id.check_al_jazeera);
        cb_am = (CheckBox)findViewById(R.id.check_american_conservative);
        cb_ver = (CheckBox)findViewById(R.id.check_the_verge);
        cb_ars = (CheckBox)findViewById(R.id.check_ars_technica);
        cb_na = (CheckBox)findViewById(R.id.check_national_geographic);
        cb_hin = (CheckBox)findViewById(R.id.check_the_hindu);
        cb_hack = (CheckBox)findViewById(R.id.check_the_hacker_news);

        cb_kor = (CheckBox)findViewById(R.id.check_korea);
        cb_jap = (CheckBox)findViewById(R.id.check_japan);
        cb_china = (CheckBox)findViewById(R.id.check_china);
        cb_taiwan = (CheckBox)findViewById(R.id.check_taiwan);


        CompoundButton.OnCheckedChangeListener ch = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if( count == max && b ==  true) {
                    compoundButton.setChecked((false));
                    Toast.makeText(getApplicationContext(),"You picked more than 4 media!", Toast.LENGTH_SHORT).show();
                }
                else if(count< max && b == true) {

                    count++;

                }
                else if ( b ==  false) {

                    count--;
                }
            }
        };

        cb_al.setOnCheckedChangeListener(ch);
        cb_am.setOnCheckedChangeListener(ch);
        cb_ars.setOnCheckedChangeListener(ch);
        cb_hin.setOnCheckedChangeListener(ch);
        cb_na.setOnCheckedChangeListener(ch);
        cb_ver.setOnCheckedChangeListener(ch);
        cb_hack.setOnCheckedChangeListener(ch);

        cb_kor.setOnCheckedChangeListener(ch);
        cb_jap.setOnCheckedChangeListener(ch);
        cb_china.setOnCheckedChangeListener(ch);
        cb_taiwan.setOnCheckedChangeListener(ch);

        mDbHelper = new SettingDbHelper(this);

        Button button = (Button) findViewById(R.id.update_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> url = new ArrayList<String>();
                ArrayList<String> name = new ArrayList<String>();
                if(cb_al.isChecked() == true) {
                    url.add(getString(R.string.al_jazeera_uri));
                    name.add(getString(R.string.al_jazeera));
                }
                if(cb_am.isChecked() == true) {
                    url.add(getString(R.string.american_conversative_uri));
                    name.add(getString(R.string.american_conservative));
                }
                if(cb_ver.isChecked() == true) {
                    url.add(getString(R.string.the_verge_uri));
                    name.add(getString(R.string.the_verge));
                }
                if(cb_ars.isChecked() == true) {
                    url.add(getString(R.string.ars_technica_uri));
                    name.add(getString(R.string.ars_technica));
                }
                if(cb_na.isChecked() == true) {
                    url.add(getString(R.string.national_geographic_uri));
                    name.add(getString(R.string.national_geographic));
                }
                if(cb_hin.isChecked() == true) {
                    url.add(getString(R.string.the_hindu_uri));
                    name.add(getString(R.string.the_hindu));
                }
                if(cb_hack.isChecked() == true) {
                    url.add(getString(R.string.hacker_news_uri));
                    name.add(getString(R.string.hacker_news));
                }


                if(cb_kor.isChecked() == true) {
                    url.add(getString(R.string.korea_uri));
                    name.add(getString(R.string.korea));
                }

                if(cb_jap.isChecked() == true) {
                    url.add(getString(R.string.japan_uri));
                    name.add(getString(R.string.japan));
                }

                if(cb_china.isChecked() == true) {
                    url.add(getString(R.string.china_url));
                    name.add(getString(R.string.chaina));
                }

                if(cb_taiwan.isChecked() == true) {
                    url.add(getString(R.string.taiwan_url));
                    name.add(getString(R.string.taiwan));
                }

                if(url.size() != 4 || name.size() != 4) {

                    Toast.makeText(getApplicationContext(),"More or less than 4 media checked! error!",Toast.LENGTH_SHORT);
                }

                else {
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    db.delete(SettingContract.SettingEntry.TABLE_NAME, null,null);
                    //deletes all data from table 'setting'


                    int errorcheck = 1;

                    for(int i = 0; i < url.size(); i++) {
                        ContentValues values = new ContentValues();
                        values.put(SettingContract.SettingEntry.COLUMN_URL, url.get(i));
                        values.put(SettingContract.SettingEntry.COLUMN_NAME, name.get(i));
                        long newRowId = db.insert(SettingContract.SettingEntry.TABLE_NAME, null, values);
                        errorcheck *= newRowId;
                        if (newRowId == -1) {
                            // If the row ID is -1, then there was an error with insertion.
                            Toast.makeText(getApplicationContext(), "Error with saving settings", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if (errorcheck== -1) {
                        // If the row ID is -1, then there was an error with insertion.
                        Toast.makeText(getApplicationContext(), "Error with saving settings", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Successfully stored your settings", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        getApplicationContext().startActivity(i);

                        finish();
                    }


                }


            }

        });
    }


}
