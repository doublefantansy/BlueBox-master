// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.facility;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MobileActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private MobileActivity target;

  @UiThread
  public MobileActivity_ViewBinding(MobileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MobileActivity_ViewBinding(MobileActivity target, View source) {
    super(target, source);

    this.target = target;

    target.next = Utils.findRequiredViewAsType(source, R.id.next, "field 'next'", TextView.class);
    target.mobile = Utils.findRequiredViewAsType(source, R.id.mobile, "field 'mobile'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
  }

  @Override
  public void unbind() {
    MobileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.next = null;
    target.mobile = null;
    target.name = null;

    super.unbind();
  }
}
