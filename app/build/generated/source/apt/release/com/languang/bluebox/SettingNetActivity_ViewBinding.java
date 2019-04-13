// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingNetActivity_ViewBinding implements Unbinder {
  private SettingNetActivity target;

  @UiThread
  public SettingNetActivity_ViewBinding(SettingNetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingNetActivity_ViewBinding(SettingNetActivity target, View source) {
    this.target = target;

    target.next = Utils.findRequiredViewAsType(source, R.id.next, "field 'next'", TextView.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", TextView.class);
    target.wifi = Utils.findRequiredViewAsType(source, R.id.wifi, "field 'wifi'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingNetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.next = null;
    target.password = null;
    target.wifi = null;
  }
}
