// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.initialize;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingWanActivity_ViewBinding implements Unbinder {
  private SettingWanActivity target;

  @UiThread
  public SettingWanActivity_ViewBinding(SettingWanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingWanActivity_ViewBinding(SettingWanActivity target, View source) {
    this.target = target;

    target.next = Utils.findRequiredViewAsType(source, R.id.next, "field 'next'", TextView.class);
    target.display = Utils.findRequiredViewAsType(source, R.id.display, "field 'display'", LinearLayout.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", TextView.class);
    target.wifi = Utils.findRequiredViewAsType(source, R.id.wifi, "field 'wifi'", MaterialSpinner.class);
    target.type1 = Utils.findRequiredViewAsType(source, R.id.type1, "field 'type1'", MaterialSpinner.class);
    target.linearLayout = Utils.findRequiredViewAsType(source, R.id.ssss, "field 'linearLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingWanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.next = null;
    target.display = null;
    target.password = null;
    target.wifi = null;
    target.type1 = null;
    target.linearLayout = null;
  }
}
