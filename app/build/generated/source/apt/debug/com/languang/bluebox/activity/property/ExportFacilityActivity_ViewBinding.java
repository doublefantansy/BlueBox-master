// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.property;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExportFacilityActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private ExportFacilityActivity target;

  private View view2131230773;

  private View view2131230774;

  @UiThread
  public ExportFacilityActivity_ViewBinding(ExportFacilityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExportFacilityActivity_ViewBinding(final ExportFacilityActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.browse_1, "method 'onViewClicked'");
    view2131230773 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.browse_2, "method 'onViewClicked'");
    view2131230774 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230773.setOnClickListener(null);
    view2131230773 = null;
    view2131230774.setOnClickListener(null);
    view2131230774 = null;

    super.unbind();
  }
}
