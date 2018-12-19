package com.languang.bluebox;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.languang.bluebox.coustomview.MyGridView;
import com.languang.bluebox.entity.ImgEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private Context context;
    private Map<String, Map<String, ImgEntity>> list;
    List<String> keys = new ArrayList<>();
    Map<String, List<ImgEntity>> imgEntitys = new LinkedTreeMap<>();
    GridAdapter adapter;
    List<ImgEntity> imgEntities;
    CountInterface countInterface;

    public Myadapter(Context context, Map<String, Map<String, ImgEntity>> list, CountInterface countInterface) {
        super();
        this.context = context;
        this.countInterface = countInterface;
        this.list = list;
        for (String key : list.keySet()) {
            keys.add(key);
        }
        for (String key : keys) {
            for (String newKey : list.get(key)
                    .keySet()) {
                if (imgEntitys.containsKey(key)) {
                    imgEntities.add(list.get(key)
                            .get(newKey));
                    imgEntitys.put(key, imgEntities);
                } else {
                    imgEntities = new ArrayList<>();
                    imgEntitys.put(key, imgEntities);
                    imgEntities.add(list.get(key)
                            .get(newKey));
                }
            }
        }
    }

    @NonNull
    @Override
    public Myadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.moneyitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.aa.setText(keys.get(i));
        Log.d("ccnb", keys.get(i));
        viewHolder.aa.setText(keys.get(i));
        adapter = new GridAdapter(context, imgEntitys.get(keys.get(i)), countInterface);
        viewHolder.bb.setAdapter(adapter);
        viewHolder.bb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("lsy1", i + "");
//                adapterView.setBackgroundColor(context.getResources()
//                        .getColor(R.color.default_blue));
//                adapter.open(i);
            }
        });
    }

    public void begin() {
        adapter.begin();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
//    public void open() {
//        adapter.begin();
//    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView aa;
        @BindView(R.id.my_grid)
        MyGridView bb;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

