package com.languang.bluebox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import com.languang.bluebox.R;

/**
 * 
 * @author ywl5320
 *
 */
public class BaseDataAdapter extends BaseAdapter {

	protected Context context;
	protected LayoutInflater mlayoutInflate;
	protected List<String> mDatas = new ArrayList<String>();

	public BaseDataAdapter(Context context, List<String> mDatas) {
		this.context = context;
		this.mDatas = mDatas;
		mlayoutInflate = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mlayoutInflate.inflate(R.layout.listview_item, parent, false);
		
		return convertView;
	}

}
