// Generated code from Butter Knife. Do not modify!
package com.languang;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LvJingAdapter$ViewHolder_ViewBinding implements Unbinder {
  private LvJingAdapter.ViewHolder target;

  @UiThread
  public LvJingAdapter$ViewHolder_ViewBinding(LvJingAdapter.ViewHolder target, View source) {
    this.target = target;

    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.layout = Utils.findRequiredViewAsType(source, R.id.layout, "field 'layout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LvJingAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
    target.layout = null;
  }
}
