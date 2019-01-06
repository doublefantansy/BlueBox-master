// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
    target.firstImage = Utils.findRequiredViewAsType(source, R.id.first_image, "field 'firstImage'", ImageView.class);
    target.second_image = Utils.findRequiredViewAsType(source, R.id.second_image, "field 'second_image'", ImageView.class);
    target.third_image = Utils.findRequiredViewAsType(source, R.id.third_image, "field 'third_image'", ImageView.class);
    target.serial_tv = Utils.findRequiredViewAsType(source, R.id.serial_tv, "field 'serial_tv'", TextView.class);
    target.right_iv = Utils.findRequiredViewAsType(source, R.id.right_iv, "field 'right_iv'", ImageView.class);
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
    target.firstImage = null;
    target.second_image = null;
    target.third_image = null;
    target.serial_tv = null;
    target.right_iv = null;
  }
}
