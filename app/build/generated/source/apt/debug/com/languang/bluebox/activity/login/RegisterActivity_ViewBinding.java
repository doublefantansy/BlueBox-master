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

public class RegisterActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private RegisterActivity target;

  private View view2131230871;

  private View view2131231027;

  private View view2131230764;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.userPhoneEt = Utils.findRequiredViewAsType(source, R.id.user_phone_et, "field 'userPhoneEt'", EditText.class);
    target.codeEt = Utils.findRequiredViewAsType(source, R.id.code_et, "field 'codeEt'", EditText.class);
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    target.consumePwdEt = Utils.findRequiredViewAsType(source, R.id.consume_pwd_et, "field 'consumePwdEt'", EditText.class);
    target.xieyi = Utils.findRequiredViewAsType(source, R.id.xieyi, "field 'xieyi'", TextView.class);
    view = Utils.findRequiredView(source, R.id.get_code, "method 'onViewClicked'");
    view2131230871 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.register_submit, "method 'onViewClicked'");
    view2131231027 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back_to_login, "method 'onViewClicked'");
    view2131230764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userPhoneEt = null;
    target.codeEt = null;
    target.pwdEt = null;
    target.consumePwdEt = null;
    target.xieyi = null;

    view2131230871.setOnClickListener(null);
    view2131230871 = null;
    view2131231027.setOnClickListener(null);
    view2131231027 = null;
    view2131230764.setOnClickListener(null);
    view2131230764 = null;

    super.unbind();
  }
}
