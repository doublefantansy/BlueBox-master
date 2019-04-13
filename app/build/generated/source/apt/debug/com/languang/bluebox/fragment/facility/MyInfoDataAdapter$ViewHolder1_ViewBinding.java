// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.facility;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyInfoDataAdapter$ViewHolder1_ViewBinding implements Unbinder {
  private MyInfoDataAdapter.ViewHolder1 target;

  @UiThread
  public MyInfoDataAdapter$ViewHolder1_ViewBinding(MyInfoDataAdapter.ViewHolder1 target,
      View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.erweima = Utils.findRequiredViewAsType(source, R.id.erweima, "field 'erweima'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyInfoDataAdapter.ViewHolder1 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.title = null;
    target.erweima = null;
  }
}
