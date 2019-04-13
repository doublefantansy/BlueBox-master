// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131230974;

  private View view2131231181;

  private View view2131230803;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.userPhoneEt = Utils.findRequiredViewAsType(source, R.id.user_phone_et, "field 'userPhoneEt'", EditText.class);
    target.codeEt = Utils.findRequiredViewAsType(source, R.id.code_et, "field 'codeEt'", EditText.class);
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    target.consumePwdEt = Utils.findRequiredViewAsType(source, R.id.consume_pwd_et, "field 'consumePwdEt'", EditText.class);
    target.xieyi = Utils.findRequiredViewAsType(source, R.id.xieyi, "field 'xieyi'", TextView.class);
    target.bar = Utils.findRequiredViewAsType(source, R.id.bar, "field 'bar'", TitleBar.class);
    view = Utils.findRequiredView(source, R.id.get_code, "method 'onViewClicked'");
    view2131230974 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.register_submit, "method 'onViewClicked'");
    view2131231181 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back_to_login, "method 'onViewClicked'");
    view2131230803 = view;
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
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userPhoneEt = null;
    target.codeEt = null;
    target.pwdEt = null;
    target.consumePwdEt = null;
    target.xieyi = null;
    target.bar = null;

    view2131230974.setOnClickListener(null);
    view2131230974 = null;
    view2131231181.setOnClickListener(null);
    view2131231181 = null;
    view2131230803.setOnClickListener(null);
    view2131230803 = null;
  }
}
