// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.coustomview.MyGridView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Myadapter$ViewHolder_ViewBinding implements Unbinder {
  private Myadapter.ViewHolder target;

  @UiThread
  public Myadapter$ViewHolder_ViewBinding(Myadapter.ViewHolder target, View source) {
    this.target = target;

    target.aa = Utils.findRequiredViewAsType(source, R.id.time, "field 'aa'", TextView.class);
    target.bb = Utils.findRequiredViewAsType(source, R.id.my_grid, "field 'bb'", MyGridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Myadapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.aa = null;
    target.bb = null;
  }
}
