// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FacilityInfoActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private FacilityInfoActivity target;

  @UiThread
  public FacilityInfoActivity_ViewBinding(FacilityInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FacilityInfoActivity_ViewBinding(FacilityInfoActivity target, View source) {
    super(target, source);

    this.target = target;

    target.listView = Utils.findRequiredViewAsType(source, R.id.list_view, "field 'listView'", ListView.class);
  }

  @Override
  public void unbind() {
    FacilityInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listView = null;

    super.unbind();
  }
}
