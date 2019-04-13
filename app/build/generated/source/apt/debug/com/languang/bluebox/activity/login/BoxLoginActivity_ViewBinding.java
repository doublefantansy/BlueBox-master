// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.xuexiang.xui.widget.button.ButtonView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BoxLoginActivity_ViewBinding implements Unbinder {
  private BoxLoginActivity target;

  @UiThread
  public BoxLoginActivity_ViewBinding(BoxLoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BoxLoginActivity_ViewBinding(BoxLoginActivity target, View source) {
    this.target = target;

    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pass1, "field 'pwdEt'", EditText.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.next, "field 'login'", ButtonView.class);
    target.forget = Utils.findRequiredViewAsType(source, R.id.forget, "field 'forget'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BoxLoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pwdEt = null;
    target.login = null;
    target.forget = null;
  }
}
