package com.languang.bluebox.coustomview.tabview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.languang.bluebox.R;

import java.util.List;

/**
 * @author mrj
 * @date 17/4/26
 */
public class MainViewAdapter extends BaseAdapter {
    private List<Fragment> fragmentArray;
    private FragmentManager fragmentManager;
    private int hasMsgIndex = 0;

    public void setHasMsgIndex(int hasMsgIndex) {
        this.hasMsgIndex = hasMsgIndex;
    }

    public MainViewAdapter(FragmentManager fragmentManager, List<Fragment> fragmentArray) {
        this.fragmentManager = fragmentManager;
        this.fragmentArray = fragmentArray;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int hasMsgIndex() {
        return hasMsgIndex;
    }

    @Override
    public String[] getTextArray() {
        return new String[]{
                "新增文件", "图库", "归档", "迁出单", "设备"
        };
    }

    @Override
    public int[] getIconImageArray() {
        return new int[]{
                R.mipmap.ic_picture_storage, R.mipmap.ic_picture_storage, R.mipmap.ic_disk,
                R.mipmap.ic_property_sheet, R.mipmap.ic_facility
        };
    }

    @Override
    public int[] getSelectedIconImageArray() {
        return new int[]{
                R.mipmap.ic_picture_storege_selected, R.mipmap.ic_picture_storege_selected, R.mipmap.ic_disk_selected,
                R.mipmap.ic_property_sheet_selected, R.mipmap.ic_facility_selected
        };
    }

    @Override
    public Fragment[] getFragmentArray() {
        return new Fragment[]{fragmentArray.get(0), fragmentArray.get(1), fragmentArray.get(2), fragmentArray.get(3), fragmentArray.get(4)};
    }

    @Override
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }
}
