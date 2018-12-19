// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.mapstorage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.coustomview.HorizontalListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareHomeFragment_ViewBinding implements Unbinder {
  private ShareHomeFragment target;

  @UiThread
  public ShareHomeFragment_ViewBinding(ShareHomeFragment target, View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.image_view, "field 'imageView'", ImageView.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.list_view, "field 'listView'", HorizontalListView.class);
    target.pictureState = Utils.findRequiredViewAsType(source, R.id.picture_state, "field 'pictureState'", TextView.class);
    target.speak = Utils.findRequiredViewAsType(source, R.id.speak, "field 'speak'", ImageView.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShareHomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.listView = null;
    target.pictureState = null;
    target.speak = null;
    target.viewPager = null;
  }
}
