package com.languang.bluebox.fragment.propertysheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.languang.bluebox.constant.ApiConstant;
import com.languang.bluebox.model.PropertySheetModel;
import com.languang.bluebox.presenter.IPropertySheet;
import com.luck.easyrecyclerview.EasyRecyclerView;
import com.luck.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.luck.easyrecyclerview.decoration.RecycleViewDivider;
import com.mrj.framworklib.utils.OkHttpCallBack;
import com.mrj.framworklib.utils.ScreenUtilBase;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;
import com.languang.bluebox.activity.property.ExportFacilityActivity;
import com.languang.bluebox.adapter.PropertySheetAdapter;
import com.languang.bluebox.basework.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 回迁单页面
 *
 * @author 49829
 * @date 2018/3/29
 */

public class PropertySheetFragment extends BaseFragment implements OkHttpCallBack, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {


    @BindView(R.id.recycler_view)
    EasyRecyclerView recyclerView;
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

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_property_sheet;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, ScreenUtilBase.dip2px(getActivity(), 0.5F), getResources().getColor(R.color.color_fff)));
        adapter = new PropertySheetAdapter(getActivity());
//        recyclerView.setRefreshListener(this);
//        adapter.setMore(R.layout.view_more, this);
//        adapter.setNoMore(R.layout.view_nomore);
//        adapter.clear();
//        adapter.addAll(list);
        recyclerView.setAdapter(adapter);
//        adapter.clear();
//        adapter.notifyDataSetChanged();
//        recyclerView.showEmpty();

        list.add("第一个");
        list.add("第二个");
        list.add("第三个");
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                startActivity(new Intent(getActivity(), ExportFacilityActivity.class));
            }
        });

    }

    @Override
    protected void initData() {
        propertySheetModel = new PropertySheetModel(getActivity());
        propertySheetModel.getOutList(this);
    }

    @OnClick({R.id.item_title_right_layout, R.id.item_title_left_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_title_right_layout:
                startActivity(new Intent(getActivity(), ExportFacilityActivity.class));
                break;
            case R.id.item_title_left_layout:
                String[] files = new String[2];
                propertySheetModel.deleteOutFile(files, this);
                break;
        }

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onSucceed(String requestUrl, String response) {
        if (ApiConstant.BOX_LIST_OUT.equals(requestUrl)) {

        } else if (ApiConstant.BOX_DEL_OUT.equals(requestUrl)) {

        } else if (ApiConstant.BOX_START_OUT.equals(requestUrl)) {

        }
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
