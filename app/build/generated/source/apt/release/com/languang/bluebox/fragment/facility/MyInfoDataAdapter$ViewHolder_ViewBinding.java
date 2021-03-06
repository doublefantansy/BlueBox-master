// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.facility;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyInfoDataAdapter$ViewHolder_ViewBinding implements Unbinder {
  private MyInfoDataAdapter.ViewHolder target;

  @UiThread
  public MyInfoDataAdapter$ViewHolder_ViewBinding(MyInfoDataAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.device = Utils.findRequiredViewAsType(source, R.id.device, "field 'device'", LinearLayout.class);
    target.passWord = Utils.findRequiredViewAsType(source, R.id.passWord, "field 'passWord'", LinearLayout.class);
    target.net = Utils.findRequiredViewAsType(source, R.id.net, "field 'net'", LinearLayout.class);
    target.code = Utils.findRequiredViewAsType(source, R.id.code, "field 'code'", LinearLayout.class);
    target.rename = Utils.findRequiredViewAsType(source, R.id.rename, "field 'rename'", LinearLayout.class);
    target.erweima = Utils.findRequiredViewAsType(source, R.id.erweima, "field 'erweima'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyInfoDataAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.title = null;
    target.device = null;
    target.passWord = null;
    target.net = null;
    target.code = null;
    target.rename = null;
    target.erweima = null;
  }
}
