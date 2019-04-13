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

public class VisitorPwdActivity_ViewBinding implements Unbinder {
  private VisitorPwdActivity target;

  private View view2131230951;

  @UiThread
  public VisitorPwdActivity_ViewBinding(VisitorPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisitorPwdActivity_ViewBinding(final VisitorPwdActivity target, View source) {
    this.target = target;

    View view;
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.eye, "field 'eye' and method 'onViewClicked'");
    target.eye = Utils.castView(view, R.id.eye, "field 'eye'", ImageView.class);
    view2131230951 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    VisitorPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pwdEt = null;
    target.eye = null;

    view2131230951.setOnClickListener(null);
    view2131230951 = null;
  }
}
