// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.mapstorage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MapStorageFragment_ViewBinding implements Unbinder {
  private MapStorageFragment target;

  private View view2131230893;

  private View view2131230896;

  @UiThread
  public MapStorageFragment_ViewBinding(final MapStorageFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.item_title_add_layout, "field 'itemTitleAddLayout' and method 'onViewClicked'");
    target.itemTitleAddLayout = Utils.castView(view, R.id.item_title_add_layout, "field 'itemTitleAddLayout'", LinearLayout.class);
    view2131230893 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.radio_group, "field 'radioGroup'", RadioGroup.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.item_title_left_layout, "method 'onViewClicked'");
    view2131230896 = view;
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
    MapStorageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.itemTitleAddLayout = null;
    target.radioGroup = null;
    target.viewPager = null;

    view2131230893.setOnClickListener(null);
    view2131230893 = null;
    view2131230896.setOnClickListener(null);
    view2131230896 = null;
  }
}
