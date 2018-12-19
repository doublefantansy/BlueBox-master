// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.initialize;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingWanActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private SettingWanActivity target;

  @UiThread
  public SettingWanActivity_ViewBinding(SettingWanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingWanActivity_ViewBinding(SettingWanActivity target, View source) {
    super(target, source);

    this.target = target;

    target.down = Utils.findRequiredViewAsType(source, R.id.down, "field 'down'", ImageView.class);
    target.display = Utils.findRequiredViewAsType(source, R.id.eye, "field 'display'", TextView.class);
    target.next = Utils.findRequiredViewAsType(source, R.id.next, "field 'next'", TextView.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", TextView.class);
    target.wifi = Utils.findRequiredViewAsType(source, R.id.wifi, "field 'wifi'", TextView.class);
  }

  @Override
  public void unbind() {
    SettingWanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.down = null;
    target.display = null;
    target.next = null;
    target.password = null;
    target.wifi = null;

    super.unbind();
  }
}
