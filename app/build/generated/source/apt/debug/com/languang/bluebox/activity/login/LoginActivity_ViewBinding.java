// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.login;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private LoginActivity target;

  private View view2131231089;

  private View view2131230862;

  private View view2131230938;

  private View view2131231147;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.userPhoneEt = Utils.findRequiredViewAsType(source, R.id.user_phone_et, "field 'userPhoneEt'", EditText.class);
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.sms_login, "field 'sms_login' and method 'onViewClicked'");
    target.sms_login = Utils.castView(view, R.id.sms_login, "field 'sms_login'", TextView.class);
    view2131231089 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.find_pwd, "field 'find_pwd' and method 'onViewClicked'");
    target.find_pwd = Utils.castView(view, R.id.find_pwd, "field 'find_pwd'", TextView.class);
    view2131230862 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_submit, "method 'onViewClicked'");
    view2131230938 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.to_register, "method 'onViewClicked'");
    view2131231147 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userPhoneEt = null;
    target.pwdEt = null;
    target.sms_login = null;
    target.find_pwd = null;

    view2131231089.setOnClickListener(null);
    view2131231089 = null;
    view2131230862.setOnClickListener(null);
    view2131230862 = null;
    view2131230938.setOnClickListener(null);
    view2131230938 = null;
    view2131231147.setOnClickListener(null);
    view2131231147 = null;

    super.unbind();
  }
}
