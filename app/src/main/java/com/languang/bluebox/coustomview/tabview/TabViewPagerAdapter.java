package com.languang.bluebox.coustomview.tabview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


/**
 *
 * @author 49829
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragmentArray;

    public TabViewPagerAdapter(FragmentManager mFragmentManager, Fragment[] fragmentArray) {
        super(mFragmentManager);
        this.fragmentArray = fragmentArray;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentArray[position];
    }

    @Override
    public int getItemPosition(Object object) {



        return super.getItemPosition(object);

    }

    @Override
    public int getCount() {
        return fragmentArray.length;
    }


}

