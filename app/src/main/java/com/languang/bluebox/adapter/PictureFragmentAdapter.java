package com.languang.bluebox.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 *图库viewPager适配器
 * @author 49829
 * @date 2018/3/29
 */

public class PictureFragmentAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> fragments;
    public PictureFragmentAdapter(FragmentManager fm , List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
