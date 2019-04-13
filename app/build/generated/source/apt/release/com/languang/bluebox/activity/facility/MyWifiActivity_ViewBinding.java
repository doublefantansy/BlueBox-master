// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.facility;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyWifiActivity_ViewBinding implements Unbinder {
  private MyWifiActivity target;

  private View view2131230952;

  private View view2131231220;

  @UiThread
  public MyWifiActivity_ViewBinding(MyWifiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyWifiActivity_ViewBinding(final MyWifiActivity target, View source) {
    this.target = target;

    View view;
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.eye, "field 'eye' and method 'onViewClicked'");
    target.eye = Utils.castView(view, R.id.eye, "field 'eye'", ImageView.class);
    view2131230952 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.save_or_restart, "method 'onViewClicked'");
    view2131231220 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MyWifiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pwdEt = null;
    target.eye = null;

    view2131230952.setOnClickListener(null);
    view2131230952 = null;
    view2131231220.setOnClickListener(null);
    view2131231220 = null;
  }
}
