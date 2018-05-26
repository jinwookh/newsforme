package com.example.minkyung.newsforme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by minkyung on 2017-05-29.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] tabTitles = {"National Geographic", "The Hindu","The Verge", "Al Jazeera"};

    SimpleFragmentPagerAdapter(FragmentManager fm) { super(fm); }

    @Override
    //getItem returns NationalFragment or HinduFragment.
    //FragmentPagerAdapter가 v4.app 버전이므로 Fragment도 adapter에 맞춰서 v4.app의 Fragment를 사용한다.
    public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new NationalFragment();

                case 1:
                    return new HinduFragment();

                case 2:
                    return new VergeFragment();

                default:
                    return new AlJazeeraFragment();
            }


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 4;
    }
}
