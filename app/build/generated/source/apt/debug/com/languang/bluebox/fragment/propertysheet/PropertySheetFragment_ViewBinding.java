// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.propertysheet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PropertySheetFragment_ViewBinding implements Unbinder {
  private PropertySheetFragment target;

  @UiThread
  public PropertySheetFragment_ViewBinding(PropertySheetFragment target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.allout = Utils.findRequiredViewAsType(source, R.id.allout, "field 'allout'", TextView.class);
    target.del = Utils.findRequiredViewAsType(source, R.id.del, "field 'del'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PropertySheetFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.allout = null;
    target.del = null;
  }
}
