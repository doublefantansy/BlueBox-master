// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.facility;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisitorPwdActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private VisitorPwdActivity target;

  private View view2131230846;

  @UiThread
  public VisitorPwdActivity_ViewBinding(VisitorPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisitorPwdActivity_ViewBinding(final VisitorPwdActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.pwd_et, "field 'pwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.eye, "field 'eye' and method 'onViewClicked'");
    target.eye = Utils.castView(view, R.id.eye, "field 'eye'", ImageView.class);
    view2131230846 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  public void unbind() {
    VisitorPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pwdEt = null;
    target.eye = null;

    view2131230846.setOnClickListener(null);
    view2131230846 = null;

    super.unbind();
  }
}
