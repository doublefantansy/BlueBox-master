// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.adapter.picturestorege;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PictureTimeAdapter$CouponViewHolder_ViewBinding implements Unbinder {
  private PictureTimeAdapter.CouponViewHolder target;

  @UiThread
  public PictureTimeAdapter$CouponViewHolder_ViewBinding(PictureTimeAdapter.CouponViewHolder target,
      View source) {
    this.target = target;

    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.myGridView = Utils.findRequiredViewAsType(source, R.id.my_grid, "field 'myGridView'", GridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PictureTimeAdapter.CouponViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.time = null;
    target.myGridView = null;
  }
}
