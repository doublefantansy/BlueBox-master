// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PropertySheetAdapter$ViewHolder_ViewBinding implements Unbinder {
  private PropertySheetAdapter.ViewHolder target;

  @UiThread
  public PropertySheetAdapter$ViewHolder_ViewBinding(PropertySheetAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.layout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'layout'", RelativeLayout.class);
    target.id = Utils.findRequiredViewAsType(source, R.id.serial_num, "field 'id'", TextView.class);
    target.state = Utils.findRequiredViewAsType(source, R.id.state, "field 'state'", TextView.class);
    target.serial_tv = Utils.findRequiredViewAsType(source, R.id.serial_tv, "field 'serial_tv'", TextView.class);
    target.right_iv = Utils.findRequiredViewAsType(source, R.id.right_iv, "field 'right_iv'", ImageView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PropertySheetAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layout = null;
    target.id = null;
    target.state = null;
    target.serial_tv = null;
    target.right_iv = null;
    target.recyclerView = null;
  }
}
