// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PopView_ViewBinding implements Unbinder {
  private PopView target;

  private View view2131231172;

  private View view2131231183;

  @UiThread
  public PopView_ViewBinding(final PopView target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.waishe, "method 'onViewClicked'");
    view2131231172 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.xiangce, "method 'onViewClicked'");
    view2131231183 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131231172.setOnClickListener(null);
    view2131231172 = null;
    view2131231183.setOnClickListener(null);
    view2131231183 = null;
  }
}
