package com.languang.bluebox.fragment.mapstorage;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.PictureFragmentAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.popupwd.AddPicturePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 图库页面
 *
 * @author 49829
 * @date 2018/3/26
 */

public class MapStorageFragment extends BaseFragment {

    @BindView(R.id.item_title_add_layout)
    LinearLayout itemTitleAddLayout;

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    private List<Fragment> fragments = new ArrayList<>(3);

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_map_storage;
    }

    @Override
    protected void initView(View view) {

        initRadioGroup();

        initViewPager();

    }

    /**
     * 初始化碎片
     */
    private void initViewPager() {
        fragments.add(new PictureTimeFragment());
        fragments.add(new PictureAddressFragment());
        fragments.add(new PictureTagFragment());

        PictureFragmentAdapter adapter = new PictureFragmentAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.time_radio);
                        break;
                    case 1:
                        radioGroup.check(R.id.address_radio);
                        break;
                    case 2:
                        radioGroup.check(R.id.person_radio);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 为顶部标签进行初始化及状态监听
     */
    private void initRadioGroup() {
        radioGroup.check(R.id.time_radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.time_radio:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.address_radio:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.person_radio:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    protected void initData() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.item_title_left_layout, R.id.item_title_add_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //添加
            case R.id.item_title_left_layout:
                getActivity().finish();
                break;
            //选择
            case R.id.item_title_add_layout:
                WindowManager m = getActivity().getWindowManager();
                DisplayMetrics outMetrics = new DisplayMetrics();
                m.getDefaultDisplay().getMetrics(outMetrics);
                int width = (int) (outMetrics.widthPixels * 0.6);
                AddPicturePopupWindow picturePopupWindow = new AddPicturePopupWindow(getActivity(), width);
                picturePopupWindow.showAsDropDown(itemTitleAddLayout);
                break;
            default:
                break;
        }
    }


}
