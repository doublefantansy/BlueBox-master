// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.facility;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdminPwdActivity_ViewBinding implements Unbinder {
  private AdminPwdActivity target;

  private View view2131230952;

  @UiThread
  public AdminPwdActivity_ViewBinding(AdminPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AdminPwdActivity_ViewBinding(final AdminPwdActivity target, View source) {
    this.target = target;

    View view;
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.eye, "field 'eye' and method 'onViewClicked'");
    target.eye = Utils.castView(view, R.id.eye, "field 'eye'", ImageView.class);
    view2131230952 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.next = Utils.findRequiredViewAsType(source, R.id.next, "field 'next'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdminPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pwdEt = null;
    target.eye = null;
    target.next = null;

    view2131230952.setOnClickListener(null);
    view2131230952 = null;
  }
}
