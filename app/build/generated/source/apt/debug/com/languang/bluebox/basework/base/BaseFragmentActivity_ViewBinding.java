// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.basework.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseFragmentActivity_ViewBinding implements Unbinder {
  private BaseFragmentActivity target;

  @UiThread
  public BaseFragmentActivity_ViewBinding(BaseFragmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BaseFragmentActivity_ViewBinding(BaseFragmentActivity target, View source) {
    this.target = target;

    target.backLayout = Utils.findOptionalViewAsType(source, R.id.item_title_back_layout, "field 'backLayout'", LinearLayout.class);
    target.titleName = Utils.findOptionalViewAsType(source, R.id.item_title_name, "field 'titleName'", TextView.class);
    target.titleSmallName = Utils.findOptionalViewAsType(source, R.id.item_title_small_name, "field 'titleSmallName'", TextView.class);
    target.rightText = Utils.findOptionalViewAsType(source, R.id.item_title_right_tv, "field 'rightText'", TextView.class);
    target.rightLayout = Utils.findOptionalViewAsType(source, R.id.item_title_right_layout, "field 'rightLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseFragmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.backLayout = null;
    target.titleName = null;
    target.titleSmallName = null;
    target.rightText = null;
    target.rightLayout = null;
  }
}
