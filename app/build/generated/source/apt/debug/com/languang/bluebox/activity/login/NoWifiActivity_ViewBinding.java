// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.login;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoWifiActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private NoWifiActivity target;

  private View view2131230782;

  private View view2131231026;

  @UiThread
  public NoWifiActivity_ViewBinding(NoWifiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NoWifiActivity_ViewBinding(final NoWifiActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.cancel, "method 'onViewClicked'");
    view2131230782 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.refresh, "method 'onViewClicked'");
    view2131231026 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230782.setOnClickListener(null);
    view2131230782 = null;
    view2131231026.setOnClickListener(null);
    view2131231026 = null;

    super.unbind();
  }
}
