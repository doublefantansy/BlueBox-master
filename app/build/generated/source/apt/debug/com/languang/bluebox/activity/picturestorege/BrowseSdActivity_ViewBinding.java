// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.picturestorege;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.luck.easyrecyclerview.EasyRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BrowseSdActivity_ViewBinding implements Unbinder {
  private BrowseSdActivity target;

  @UiThread
  public BrowseSdActivity_ViewBinding(BrowseSdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BrowseSdActivity_ViewBinding(BrowseSdActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", EasyRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BrowseSdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
