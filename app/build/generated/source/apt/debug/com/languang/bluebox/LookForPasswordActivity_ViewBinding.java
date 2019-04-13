// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LookForPasswordActivity_ViewBinding implements Unbinder {
  private LookForPasswordActivity target;

  @UiThread
  public LookForPasswordActivity_ViewBinding(LookForPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LookForPasswordActivity_ViewBinding(LookForPasswordActivity target, View source) {
    this.target = target;

    target.bar = Utils.findRequiredViewAsType(source, R.id.bar, "field 'bar'", TitleBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LookForPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bar = null;
  }
}
