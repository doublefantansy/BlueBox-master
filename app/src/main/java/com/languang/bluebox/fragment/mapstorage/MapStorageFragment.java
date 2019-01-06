package com.languang.bluebox.fragment.mapstorage;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.languang.bluebox.FFInterface;
import com.languang.bluebox.MainActivity;
import com.languang.bluebox.R;
import com.languang.bluebox.adapter.PictureFragmentAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.coustomview.CustomViewPager;
import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;
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
    CustomViewPager viewPager;
    public PictureTimeFragment fragment1;
    public PictureAddressFragment fragment2;
    public PictureTagFragment fragment3;
    List<ImgEntity> imgEntities1 = new ArrayList<ImgEntity>();
    private List<Fragment> fragments = new ArrayList<>(3);
    List<ImgEntity> choose = new ArrayList<>();
    List<ImgListEntity> chooseTag = new ArrayList<>();
    //    FFInterface ffInterface;
    public List<ImgListEntity> imgEntities;
    public static int count = 0;
    AdressInterface adressInterface;
    boolean first = true;

    public void setIn(AdressInterface adressInterface) {
        this.adressInterface = adressInterface;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_map_storage;
    }

    @Override
    protected void initView(View view) {
        initRadioGroup();
        initViewPager();
    }

    @Override
    public void onResume() {
        super.onResume();
        count = 0;
        imgEntities1.clear();
        choose.clear();
        chooseTag.clear();
    }

    /**
     * 初始化碎片
     */
    private void initViewPager() {
        fragment1 = new PictureTimeFragment();
        fragment1.setFF1(new FF1Interface() {
            @Override
            public void click(boolean t, boolean isadd, ImgEntity imgEntities) {
//                ss2.setVisibility(View.VISIBLE);
                if (isadd) {
                    imgEntities1.add(imgEntities);
                } else {
                    imgEntities1.remove(imgEntities);
                }
                if (!t) {
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            radioGroup.check(R.id.time_radio);
                        }
                    });
                } else {
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.time_radio:
                                    radioGroup.check(R.id.time_radio);
                                    viewPager.setCurrentItem(0);
                                    break;
                                case R.id.address_radio:
                                    radioGroup.check(R.id.address_radio);
                                    viewPager.setCurrentItem(1);
                                    break;
                                case R.id.person_radio:
                                    radioGroup.check(R.id.person_radio);
                                    viewPager.setCurrentItem(2);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
                ((MainActivity) getActivity()).setGone(t, imgEntities1);
            }
        });
        fragments.add(fragment1);
        fragment2 = new PictureAddressFragment();
        fragment2.setIn(new AdressInterface() {
            @Override
            public void click(boolean cheaked, int a, List<ImgEntity> imgEntities) {
                if (cheaked) {
                    choose.addAll(imgEntities);
                } else {
                    choose.removeAll(imgEntities);
                }
                if (a == 0 ? false : true) {
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            radioGroup.check(R.id.address_radio);
                        }
                    });
                } else {
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.time_radio:
                                    radioGroup.check(R.id.time_radio);
                                    viewPager.setCurrentItem(0);
                                    break;
                                case R.id.address_radio:
                                    radioGroup.check(R.id.address_radio);
                                    viewPager.setCurrentItem(1);
                                    break;
                                case R.id.person_radio:
                                    radioGroup.check(R.id.person_radio);
                                    viewPager.setCurrentItem(2);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
                Log.d("ccnb", a + "");
                ((MainActivity) getActivity()).setGone(a == 0 ? true : false, choose);
                Log.d("ccnbccnbccnbccnb1ccnb", choose.size() + "");
            }
        });
        fragments.add(fragment2);
        fragment3 = new PictureTagFragment();
        fragment3.setIn(new TagInterface() {
            @Override
            public void click(boolean cheaked, int count, ImgListEntity imgEntities) {
                if (cheaked) {
                    chooseTag.add(imgEntities);
                } else {
                    chooseTag.remove(imgEntities);
                }
                ArrayList<ImgEntity> imgEntities2 = new ArrayList<>();
                for (int i = 0; i < chooseTag.size(); i++) {
                    imgEntities2.addAll(chooseTag.get(i)
                            .getImgEntityList());
                }
                if (count == 0 ? false : true) {
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            radioGroup.check(R.id.person_radio);
                        }
                    });
                } else {
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.time_radio:
                                    radioGroup.check(R.id.time_radio);
                                    viewPager.setCurrentItem(0);
                                    break;
                                case R.id.address_radio:
                                    radioGroup.check(R.id.address_radio);
                                    viewPager.setCurrentItem(1);
                                    break;
                                case R.id.person_radio:
                                    radioGroup.check(R.id.person_radio);
                                    viewPager.setCurrentItem(2);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
//                Log.d("lovelsy", count + "");
                ((MainActivity) getActivity()).setGone(count == 0 ? true : false, imgEntities2);
//                Log.d("lovelsy", chooseTag.size() + "");
            }
        });
        fragments.add(fragment3);
        fragment1.setFF(new FFInterface() {
            @Override
            public void click(List<ImgListEntity> imgEntities) {
                MapStorageFragment.this.imgEntities = imgEntities;
//                fragment2.setList(imgEntities);
//                fragment3.setList(imgEntities);
            }
        });
        PictureFragmentAdapter adapter = new PictureFragmentAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
//        viewPager.setEnabled(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                Log.d("nnnn", position + "");
//                switch (position) {
//                    case 0:
//                        radioGroup.check(R.id.time_radio);
//                        break;
//                    case 1:
//                        radioGroup.check(R.id.address_radio);
//                        fragment1.adapter.adapter.clear();
//                        break;
//                    case 2:
//                        radioGroup.check(R.id.person_radio);
//                        break;
//                    default:
//                        break;
//                }
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
                        radioGroup.check(R.id.time_radio);
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.address_radio:
                        radioGroup.check(R.id.address_radio);
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.person_radio:
                        radioGroup.check(R.id.person_radio);
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
                m.getDefaultDisplay()
                        .getMetrics(outMetrics);
                int width = (int) (outMetrics.widthPixels * 0.6);
                AddPicturePopupWindow picturePopupWindow = new AddPicturePopupWindow(getActivity(), width);
                picturePopupWindow.showAsDropDown(itemTitleAddLayout);
                break;
            default:
                break;
        }
    }
}
