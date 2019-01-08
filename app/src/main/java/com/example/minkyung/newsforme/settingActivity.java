package com.example.minkyung.newsforme;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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

import java.util.ArrayList;


public class settingActivity extends AppCompatActivity {

    private CheckBox cb_al;
    private CheckBox cb_am;
    private CheckBox cb_ver;
    private CheckBox cb_ars;
    private CheckBox cb_kor;
    private CheckBox cb_na;
    private CheckBox cb_hin;
    private CheckBox cb_hack;
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
        cb_kor = (CheckBox)findViewById(R.id.check_korea);
        cb_na = (CheckBox)findViewById(R.id.check_national_geographic);
        cb_hin = (CheckBox)findViewById(R.id.check_the_hindu);
        cb_hack = (CheckBox)findViewById(R.id.check_the_hacker_news);



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
        cb_kor.setOnCheckedChangeListener(ch);
        cb_hack.setOnCheckedChangeListener(ch);



        Button button = (Button) findViewById(R.id.update_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList url = new ArrayList();
                if(cb_al.isChecked() == true) url.add(getString(R.string.al_jazeera_uri));
                if(cb_am.isChecked() == true) url.add(getString(R.string.american_conversative_uri));
                if(cb_ver.isChecked() == true) url.add(getString(R.string.the_verge_uri));
                if(cb_ars.isChecked() == true) url.add(getString(R.string.ars_technica_uri));
                if(cb_kor.isChecked() == true) url.add(getString(R.string.korea_uri));
                if(cb_na.isChecked() == true) url.add(getString(R.string.national_geographic_uri));
                if(cb_hin.isChecked() == true) url.add(getString(R.string.the_hindu_uri));
                if(cb_hack.isChecked() == true) url.add(getString(R.string.hacker_news_uri));

                if(url.size() != 4) {

                    Toast.makeText(getApplicationContext(),"More or less than 4 media checked! error!",Toast.LENGTH_SHORT);
                }

                else {

                }


            }

        });
    }


}
