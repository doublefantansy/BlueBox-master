// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.coustomview.tabview.TabContainerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.tabContainer = Utils.findRequiredViewAsType(source, R.id.tab_container, "field 'tabContainer'", TabContainerView.class);
    target.ss = Utils.findRequiredViewAsType(source, R.id.ss, "field 'ss'", LinearLayout.class);
    target.ss1 = Utils.findRequiredViewAsType(source, R.id.ss1, "field 'ss1'", LinearLayout.class);
    target.bottomTv = Utils.findRequiredViewAsType(source, R.id.bottom_tv, "field 'bottomTv'", TextView.class);
    target.biaozhus = Utils.findRequiredViewAsType(source, R.id.biaozhus, "field 'biaozhus'", LinearLayout.class);
    target.del = Utils.findRequiredViewAsType(source, R.id.del, "field 'del'", LinearLayout.class);
    target.share = Utils.findRequiredViewAsType(source, R.id.share, "field 'share'", LinearLayout.class);
    target.voice = Utils.findRequiredViewAsType(source, R.id.voice, "field 'voice'", LinearLayout.class);
    target.meihua = Utils.findRequiredViewAsType(source, R.id.meihua, "field 'meihua'", LinearLayout.class);
    target.ss2 = Utils.findRequiredViewAsType(source, R.id.ss2, "field 'ss2'", LinearLayout.class);
    target.anim = Utils.findRequiredViewAsType(source, R.id.ani, "field 'anim'", ImageView.class);
    target.anim1 = Utils.findRequiredViewAsType(source, R.id.ani1, "field 'anim1'", ImageView.class);
    target.out = Utils.findRequiredViewAsType(source, R.id.tianjiadaochu, "field 'out'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabContainer = null;
    target.ss = null;
    target.ss1 = null;
    target.bottomTv = null;
    target.biaozhus = null;
    target.del = null;
    target.share = null;
    target.voice = null;
    target.meihua = null;
    target.ss2 = null;
    target.anim = null;
    target.anim1 = null;
    target.out = null;
  }
}
