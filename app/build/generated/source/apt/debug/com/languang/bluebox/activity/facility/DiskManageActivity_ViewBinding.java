// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.facility;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.coustomview.CustomPercentView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DiskManageActivity_ViewBinding implements Unbinder {
  private DiskManageActivity target;

  private View view2131230867;

  @UiThread
  public DiskManageActivity_ViewBinding(DiskManageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DiskManageActivity_ViewBinding(final DiskManageActivity target, View source) {
    this.target = target;

    View view;
    target.customPercentView = Utils.findRequiredViewAsType(source, R.id.custom_percent_view, "field 'customPercentView'", CustomPercentView.class);
    target.diskName = Utils.findRequiredViewAsType(source, R.id.disk_name, "field 'diskName'", TextView.class);
    target.diskState = Utils.findRequiredViewAsType(source, R.id.disk_state, "field 'diskState'", TextView.class);
    target.diskReduce = Utils.findRequiredViewAsType(source, R.id.disk_reduce, "field 'diskReduce'", TextView.class);
    target.diskTotal = Utils.findRequiredViewAsType(source, R.id.disk_total, "field 'diskTotal'", TextView.class);
    view = Utils.findRequiredView(source, R.id.clear_cache, "method 'onViewClicked'");
    view2131230867 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DiskManageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.customPercentView = null;
    target.diskName = null;
    target.diskState = null;
    target.diskReduce = null;
    target.diskTotal = null;

    view2131230867.setOnClickListener(null);
    view2131230867 = null;
  }
}
