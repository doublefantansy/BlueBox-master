// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SuoLueAdapter2$ViewHolder_ViewBinding implements Unbinder {
  private SuoLueAdapter2.ViewHolder target;

  @UiThread
  public SuoLueAdapter2$ViewHolder_ViewBinding(SuoLueAdapter2.ViewHolder target, View source) {
    this.target = target;

    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.gou = Utils.findRequiredViewAsType(source, R.id.gou, "field 'gou'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SuoLueAdapter2.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
    target.gou = null;
  }
}
