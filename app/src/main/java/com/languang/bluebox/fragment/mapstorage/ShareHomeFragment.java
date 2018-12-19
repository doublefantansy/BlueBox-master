package com.languang.bluebox.fragment.mapstorage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.adapter.picturestorege.ShareHorizontalAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.coustomview.HorizontalListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分享首页
 *
 * @author 49829
 * @date 2018/4/12
 */

public class ShareHomeFragment extends BaseFragment {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.list_view)
    HorizontalListView listView;
    @BindView(R.id.picture_state)
    TextView pictureState;
    @BindView(R.id.speak)
    ImageView speak;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ShareHorizontalAdapter adapter;
    private List<String> list = new ArrayList<>();
    int choosePosition = 0;

    private List<Fragment> imageFragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_share_img;
    }

    @Override
    protected void initView(View view) {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        adapter = new ShareHorizontalAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                choosePosition = position;
                switch (position) {
                    case 0:
                        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.meinv));
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.p1));
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.p2));
                        viewPager.setCurrentItem(2);
                        break;
                    case 3:
                        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.p4));
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                }
            }
        });

        imageFragments.add(new ImageFragment(BitmapFactory.decodeResource(getResources(), R.mipmap.meinv)));
        imageFragments.add(new ImageFragment(BitmapFactory.decodeResource(getResources(), R.mipmap.p1)));
        imageFragments.add(new ImageFragment(BitmapFactory.decodeResource(getResources(), R.mipmap.p2)));
        imageFragments.add(new ImageFragment(BitmapFactory.decodeResource(getResources(), R.mipmap.p4)));
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager());

        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.selectImage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 显示请说话图片
     */
    public void showSpeakIv() {
        pictureState.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        speak.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏请说话图片
     */
    public void hideSpeakIv() {
        pictureState.setVisibility(View.VISIBLE);
        listView.setVisibility(View.VISIBLE);
        speak.setVisibility(View.GONE);
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = null;
        switch (choosePosition) {
            case 0:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.meinv);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p1);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p2);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.p4);
                break;
            default:
        }
        return bitmap;
    }


    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return imageFragments.get(position);
        }

        @Override
        public int getCount() {
            return imageFragments.size();
        }


    }
}
