// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.mapstorage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.coustomview.CustomViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MapStorageFragment_ViewBinding implements Unbinder {
  private MapStorageFragment target;

  @UiThread
  public MapStorageFragment_ViewBinding(MapStorageFragment target, View source) {
    this.target = target;

    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.radio_group, "field 'radioGroup'", RadioGroup.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", CustomViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MapStorageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radioGroup = null;
    target.viewPager = null;
  }
}
