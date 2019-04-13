// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FacilityInfoActivity_ViewBinding implements Unbinder {
  private FacilityInfoActivity target;

  @UiThread
  public FacilityInfoActivity_ViewBinding(FacilityInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FacilityInfoActivity_ViewBinding(FacilityInfoActivity target, View source) {
    this.target = target;

    target.listView = Utils.findRequiredViewAsType(source, R.id.list_view, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FacilityInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listView = null;
  }
}
