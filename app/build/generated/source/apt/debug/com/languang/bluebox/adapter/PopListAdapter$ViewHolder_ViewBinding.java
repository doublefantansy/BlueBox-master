// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PopListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private PopListAdapter.ViewHolder target;

  @UiThread
  public PopListAdapter$ViewHolder_ViewBinding(PopListAdapter.ViewHolder target, View source) {
    this.target = target;

    target.mobile = Utils.findRequiredViewAsType(source, R.id.mobile, "field 'mobile'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PopListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mobile = null;
  }
}
