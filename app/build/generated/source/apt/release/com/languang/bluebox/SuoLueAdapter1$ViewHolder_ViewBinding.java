// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SuoLueAdapter1$ViewHolder_ViewBinding implements Unbinder {
  private SuoLueAdapter1.ViewHolder target;

  @UiThread
  public SuoLueAdapter1$ViewHolder_ViewBinding(SuoLueAdapter1.ViewHolder target, View source) {
    this.target = target;

    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.gou = Utils.findRequiredViewAsType(source, R.id.gou, "field 'gou'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SuoLueAdapter1.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
    target.gou = null;
  }
}
