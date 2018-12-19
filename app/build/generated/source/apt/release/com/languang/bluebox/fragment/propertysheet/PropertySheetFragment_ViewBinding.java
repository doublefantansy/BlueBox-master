// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.propertysheet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.luck.easyrecyclerview.EasyRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PropertySheetFragment_ViewBinding implements Unbinder {
  private PropertySheetFragment target;

  private View view2131230891;

  private View view2131230888;

  @UiThread
  public PropertySheetFragment_ViewBinding(final PropertySheetFragment target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", EasyRecyclerView.class);
    view = Utils.findRequiredView(source, R.id.item_title_right_layout, "method 'onViewClicked'");
    view2131230891 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_title_left_layout, "method 'onViewClicked'");
    view2131230888 = view;
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
    PropertySheetFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;

    view2131230891.setOnClickListener(null);
    view2131230891 = null;
    view2131230888.setOnClickListener(null);
    view2131230888 = null;
  }
}
