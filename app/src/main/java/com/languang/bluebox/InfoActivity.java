package com.languang.bluebox;

import android.widget.TextView;

import com.google.gson.Gson;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import com.languang.bluebox.entity.facility.FacilityListInfo;
import com.languang.bluebox.fragment.facility.HeziBean;

public class InfoActivity extends BaseFragmentActivity {
    TextView deviceName;
    TextView code;
    TextView wanIp;
    TextView cdCode;
    TextView media;
    TextView photo;
    TextView max;
    TextView canUse;

    @Override
    protected int getLayoutResId() {
        return R.layout.info;
    }

    @Override
    protected void initView() {
        deviceName = findViewById(R.id.deviceName);
        code = findViewById(R.id.uuid);
        wanIp = findViewById(R.id.wanIp);
        cdCode = findViewById(R.id.cd);
        media = findViewById(R.id.mediaCount);
        photo = findViewById(R.id.photoCount);
        max = findViewById(R.id.allSave);
        canUse = findViewById(R.id.hasSave);
        FacilityListInfo info = new Gson().fromJson(getIntent().getStringExtra("a"), FacilityListInfo.class);
        deviceName.setText(info.getBluename());
        code.setText(info.getBlueuuid());
        wanIp.setText(info.getWlanip());
        cdCode.setText(info.getModelsn());
        HeziBean heziBean = new Gson().fromJson(getIntent().getStringExtra("b"), HeziBean.class);
        media.setText(heziBean.getCountfile()
                .getMov());
        photo.setText(heziBean.getCountfile()
                .getRaw());
        max.setText(heziBean.getDev()
                .get(0)
                .getTotal() / 1024 / 1024 / 1024 + "G"
        );
        canUse.setText(heziBean.getDev()
                .get(0)
                .getFree() / 1024 / 1024 / 1024 + "G"
        );
    }

    @Override
    protected void initData() {
    }
}
