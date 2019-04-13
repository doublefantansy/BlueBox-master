package com.languang.bluebox.fragment.propertysheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.languang.bluebox.Onclick1;
import com.languang.bluebox.R;
import com.languang.bluebox.TimeUtils;
import com.languang.bluebox.activity.property.ExportFacilityActivity;
import com.languang.bluebox.adapter.PropertySheetAdapter;
import com.languang.bluebox.basework.base.BaseFragment;
import com.languang.bluebox.basework.net.OkHttpUtils;
import com.languang.bluebox.entity.ResponseMessage;
import com.languang.bluebox.model.PropertySheetModel;
import com.languang.bluebox.presenter.IPropertySheet;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mrj.framworklib.utils.OkHttpCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 回迁单页面
 *
 * @author 49829
 * @date 2018/3/29
 */
public class PropertySheetFragment extends BaseFragment implements OkHttpCallBack, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.allout)
    TextView allout;
    @BindView(R.id.del)
    TextView del;
    Unbinder unbinder;
    /**
     * page 页码 isRefresh 是否下拉刷新中 isLoad 是否加载更多中 hasNextPage 是否有下一页
     */
    private int page = 1;
    private boolean isRefresh = false;
    private boolean isLoad = false;
    private boolean hasNextpage = false;
    private List<String> list = new ArrayList<>();
    private PropertySheetAdapter adapter;
    private IPropertySheet propertySheetModel;
    //    map;
    Map<String, Map<String, OutBean.Nocd.ImgEntitysp>> newMap = new HashMap<>();
    List<OutBean.Nocd.ImgEntitysp> newlist = new ArrayList<>();
    List<OutBean.Nocd.ImgEntitysp> chooseList = new ArrayList<>();
    boolean isDel = true;
    private boolean first = true;
//    int count = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_property_sheet;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lsy", "inin");
        propertySheetModel = new PropertySheetModel(getActivity());
        propertySheetModel.getOutList(this);
    }

    @Override
    protected void initView(View view) {
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del.setText(isDel == true ? "完成" : "删除");
                isDel = !isDel;
                if (isDel) {
                    for (OutBean.Nocd.ImgEntitysp imgEntitysp : chooseList) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("files", imgEntitysp.getUuid());
//                        ApiConstant
                        OkHttpUtils.getInstance()
                                .okPost(getActivity(), TimeUtils.getWlanIp() + "/delout", map, new OkHttpCallBack() {
                                    @Override
                                    public void onSucceed(String requestUrl, String response) {
                                        Log.d("ccnb", response);
                                        onResume();
                                    }

                                    @Override
                                    public void onFailed() {
                                    }
                                });
                    }
                }
                adapter.change(isDel);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.HORIZONTAL, ScreenUtilBase.dip2px(getActivity(), 0.5F), getResources().getColor(R.color.color_fff)));
        allout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExportFacilityActivity.class);
                intent.putExtra("spe", new Gson().toJson(newlist));
                intent.putExtra("spe1", newlist.size());
                startActivity(intent);
//                count = 0;
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        Log.d("ccnb", response);
        newlist.clear();
        newMap.clear();
        ResponseMessage<OutBean> responseMessage = new Gson().fromJson(response, new TypeToken<ResponseMessage<OutBean>>() {
        }.getType());
        Map<String, OutBean.Nocd> map = responseMessage.getData().files;
        if (map != null) {
            for (Map.Entry<String, OutBean.Nocd> entry : map.entrySet()) {
                newMap.put(entry.getKey(), entry.getValue().files);
//            count += entry.getValue().count;
                for (Map.Entry<String, OutBean.Nocd.ImgEntitysp> entityspEntry : entry.getValue().files.entrySet()) {
                    newlist.add(entityspEntry.getValue());
                }
            }
//
//            adapter.notifyDataSetChanged();
        }
        adapter = new PropertySheetAdapter(getActivity(), newMap, new Onclick1() {
            @Override
            public void click(int p, int count, boolean isadd, OutBean.Nocd.ImgEntitysp imgEntity) {
                if (count == 0) {
                    chooseList.clear();
                } else {
                    chooseList.add(imgEntity);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        }?
    }

    @Override
    public void onFailed() {
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
