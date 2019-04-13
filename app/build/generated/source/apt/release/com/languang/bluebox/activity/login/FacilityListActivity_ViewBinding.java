// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.luck.easyrecyclerview.EasyRecyclerView;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FacilityListActivity_ViewBinding implements Unbinder {
  private FacilityListActivity target;

  private View view2131231128;

  private View view2131231125;

  private View view2131231363;

  private View view2131231037;

  @UiThread
  public FacilityListActivity_ViewBinding(FacilityListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FacilityListActivity_ViewBinding(final FacilityListActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.online, "field 'online' and method 'onViewClicked'");
    target.online = Utils.castView(view, R.id.online, "field 'online'", TextView.class);
    view2131231128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.offline, "field 'offline' and method 'onViewClicked'");
    target.offline = Utils.castView(view, R.id.offline, "field 'offline'", TextView.class);
    view2131231125 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_new, "field 'tonew' and method 'onViewClicked'");
    target.tonew = Utils.castView(view, R.id.tv_new, "field 'tonew'", TextView.class);
    view2131231363 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.bar = Utils.findRequiredViewAsType(source, R.id.ssssss, "field 'bar'", TitleBar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", EasyRecyclerView.class);
    view = Utils.findRequiredView(source, R.id.iv_refresh, "method 'onViewClicked'");
    view2131231037 = view;
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
    FacilityListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.online = null;
    target.offline = null;
    target.tonew = null;
    target.bar = null;
    target.recyclerView = null;

    view2131231128.setOnClickListener(null);
    view2131231128 = null;
    view2131231125.setOnClickListener(null);
    view2131231125 = null;
    view2131231363.setOnClickListener(null);
    view2131231363 = null;
    view2131231037.setOnClickListener(null);
    view2131231037 = null;
  }
}
