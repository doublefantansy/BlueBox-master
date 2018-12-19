// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PictureStoreageFragment_ViewBinding implements Unbinder {
  private PictureStoreageFragment target;

  @UiThread
  public PictureStoreageFragment_ViewBinding(PictureStoreageFragment target, View source) {
    this.target = target;

    target.none = Utils.findRequiredViewAsType(source, R.id.none, "field 'none'", TextView.class);
    target.add = Utils.findRequiredViewAsType(source, R.id.add, "field 'add'", ImageView.class);
    target.choose = Utils.findRequiredViewAsType(source, R.id.choose, "field 'choose'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PictureStoreageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.none = null;
    target.add = null;
    target.choose = null;
    target.recyclerView = null;
    target.title = null;
  }
}
