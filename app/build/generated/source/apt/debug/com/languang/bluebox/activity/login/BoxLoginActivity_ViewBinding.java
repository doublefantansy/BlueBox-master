// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.login;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BoxLoginActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private BoxLoginActivity target;

  @UiThread
  public BoxLoginActivity_ViewBinding(BoxLoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BoxLoginActivity_ViewBinding(BoxLoginActivity target, View source) {
    super(target, source);

    this.target = target;

    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.login_submit, "field 'login'", Button.class);
  }

  @Override
  public void unbind() {
    BoxLoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pwdEt = null;
    target.login = null;

    super.unbind();
  }
}
