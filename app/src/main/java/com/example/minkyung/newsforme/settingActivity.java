package com.example.minkyung.newsforme;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class settingActivity extends AppCompatActivity {

    private Spinner firstUriSpinner;

    private Spinner secondUriSpinner;

    private Spinner thirdUriSpinner;

    private Spinner fourthUriSpinner;

    private String uri1 = "";

    private String uri2 = "";

    private String uri3 = "";

    private String uri4 ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        firstUriSpinner = (Spinner) findViewById(R.id.spinner);

        secondUriSpinner = (Spinner) findViewById(R.id.spinner2);

        thirdUriSpinner = (Spinner) findViewById(R.id.spinner3);

        fourthUriSpinner = (Spinner) findViewById(R.id.spinner4);

        setupSpinner();

        Button button = (Button) findViewById(R.id.update_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        final ArrayAdapter uriSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_uri_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        uriSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        firstUriSpinner.setAdapter(uriSpinnerAdapter);
        secondUriSpinner.setAdapter(uriSpinnerAdapter);
        thirdUriSpinner.setAdapter(uriSpinnerAdapter);
        fourthUriSpinner.setAdapter(uriSpinnerAdapter);

        // Set the integer mSelected to the constant values
        firstUriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.al_jazeera))) {
                        uri1 = getString(R.string.al_jazeera_uri);
                    } else if (selection.equals(getString(R.string.national_geographic))) {
                        uri1 = getString(R.string.national_geographic_uri);
                    } else if (selection.equals(getString(R.string.the_hindu))){
                        uri1 = getString(R.string.the_hindu_uri);
                    } else if (selection.equals(getString(R.string.the_verge))){
                        uri1 = getString(R.string.the_verge_uri);
                    } else if (selection.equals(getString(R.string.american_conservative))){
                        uri1 = getString(R.string.american_conversative_uri);
                    } else if (selection.equals(getString(R.string.ars_technica))){
                        uri1 = getString(R.string.ars_technica_uri);
                    } else if (selection.equals(getString(R.string.hacker_news))){
                        uri1 = getString(R.string.hacker_news_uri);
                    } else if (selection.equals(getString(R.string.korea))){
                        uri1 = getString(R.string.korea_uri);
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                uri1 = getString(R.string.korea_uri);
            }
        });

        secondUriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.al_jazeera))) {
                        uri2 = getString(R.string.al_jazeera_uri);
                    } else if (selection.equals(getString(R.string.national_geographic))) {
                        uri2 = getString(R.string.national_geographic_uri);
                    } else if (selection.equals(getString(R.string.the_hindu))){
                        uri2 = getString(R.string.the_hindu_uri);
                    } else if (selection.equals(getString(R.string.the_verge))){
                        uri2 = getString(R.string.the_verge_uri);
                    } else if (selection.equals(getString(R.string.american_conservative))){
                        uri2 = getString(R.string.american_conversative_uri);
                    } else if (selection.equals(getString(R.string.ars_technica))){
                        uri2 = getString(R.string.ars_technica_uri);
                    } else if (selection.equals(getString(R.string.hacker_news))){
                        uri2 = getString(R.string.hacker_news_uri);
                    } else if (selection.equals(getString(R.string.korea))){
                        uri2 = getString(R.string.korea_uri);
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                uri2 = getString(R.string.national_geographic_uri);
            }
        });

        thirdUriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.al_jazeera))) {
                        uri3 = getString(R.string.al_jazeera_uri);
                    } else if (selection.equals(getString(R.string.national_geographic))) {
                        uri3 = getString(R.string.national_geographic_uri);
                    } else if (selection.equals(getString(R.string.the_hindu))){
                        uri3 = getString(R.string.the_hindu_uri);
                    } else if (selection.equals(getString(R.string.the_verge))){
                        uri3 = getString(R.string.the_verge_uri);
                    } else if (selection.equals(getString(R.string.american_conservative))){
                        uri3 = getString(R.string.american_conversative_uri);
                    } else if (selection.equals(getString(R.string.ars_technica))){
                        uri3 = getString(R.string.ars_technica_uri);
                    } else if (selection.equals(getString(R.string.hacker_news))){
                        uri3 = getString(R.string.hacker_news_uri);
                    } else if (selection.equals(getString(R.string.korea))){
                        uri3 = getString(R.string.korea_uri);
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                uri3 = getString(R.string.hacker_news_uri);
            }
        });

        fourthUriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.al_jazeera))) {
                        uri4 = getString(R.string.al_jazeera_uri);
                    } else if (selection.equals(getString(R.string.national_geographic))) {
                        uri4 = getString(R.string.national_geographic_uri);
                    } else if (selection.equals(getString(R.string.the_hindu))){
                        uri4 = getString(R.string.the_hindu_uri);
                    } else if (selection.equals(getString(R.string.the_verge))){
                        uri4 = getString(R.string.the_verge_uri);
                    } else if (selection.equals(getString(R.string.american_conservative))){
                        uri4 = getString(R.string.american_conversative_uri);
                    } else if (selection.equals(getString(R.string.ars_technica))){
                        uri4 = getString(R.string.ars_technica_uri);
                    } else if (selection.equals(getString(R.string.hacker_news))){
                        uri4 = getString(R.string.hacker_news_uri);
                    } else if (selection.equals(getString(R.string.korea))){
                        uri4 = getString(R.string.korea_uri);
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                uri4 = getString(R.string.american_conversative_uri);
            }
        });
    }
}
