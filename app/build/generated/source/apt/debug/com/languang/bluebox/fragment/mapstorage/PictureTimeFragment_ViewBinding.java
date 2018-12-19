// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.mapstorage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.coustomview.CustomEditText;
import com.luck.easyrecyclerview.EasyRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PictureTimeFragment_ViewBinding implements Unbinder {
  private PictureTimeFragment target;

  @UiThread
  public PictureTimeFragment_ViewBinding(PictureTimeFragment target, View source) {
    this.target = target;

    target.searchEt = Utils.findRequiredViewAsType(source, R.id.search_et, "field 'searchEt'", CustomEditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", EasyRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PictureTimeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.searchEt = null;
    target.recyclerView = null;
  }
}
