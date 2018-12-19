package com.languang.bluebox.activity.login;

import android.os.Bundle;
import android.view.View;

import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 无网络页面
 *
 * @author 49829
 * @date 2018/4/9
 */

public class NoWifiActivity extends BaseFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_no_match;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.cancel, R.id.refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.refresh:

                break;
            default:

                break;
        }
    }
}
