package com.example.minkyung.newsforme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by minkyung on 2017-05-29.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> urlList;
    private ArrayList<String> nameList;
    private String[] tabTitles = {"National Geographic", "The Hindu","The Verge", "Al Jazeera"};

    SimpleFragmentPagerAdapter(FragmentManager fm) { super(fm); }

    SimpleFragmentPagerAdapter(FragmentManager fm, ArrayList<String> arrayList, ArrayList<String > arrayList2) {
        super(fm);
        urlList = arrayList;
        nameList =  arrayList2;
        tabTitles[0] = nameList.get(0);
        tabTitles[1] = nameList.get(1);
        tabTitles[2] = nameList.get(2);
        tabTitles[3] = nameList.get(3);

    }

    @Override
    //getItem returns NationalFragment or HinduFragment.
    //FragmentPagerAdapter가 v4.app 버전이므로 Fragment도 adapter에 맞춰서 v4.app의 Fragment를 사용한다.
    public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    Bundle bundle1 = new Bundle();
                    NationalFragment firstFragment = new NationalFragment();
                    bundle1.putString("url", urlList.get(0) );
                    firstFragment.setArguments(bundle1);

                    return firstFragment;

                case 1:
                    Bundle bundle2 = new Bundle();
                    HinduFragment secondFragment = new HinduFragment();
                    bundle2.putString("url", urlList.get(1) );
                    secondFragment.setArguments(bundle2);
                    return secondFragment;

                case 2:
                    Bundle bundle3 = new Bundle();
                    VergeFragment thirdFragment = new VergeFragment();
                    bundle3.putString("url", urlList.get(2) );
                    thirdFragment.setArguments(bundle3);
                    return thirdFragment;

                default:
                    Bundle bundle4 = new Bundle();
                    AlJazeeraFragment fourthFragment = new AlJazeeraFragment();
                    bundle4.putString("url", urlList.get(3) );
                    fourthFragment.setArguments(bundle4);
                    return fourthFragment;
            }


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nameList.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
