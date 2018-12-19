// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LightDiskFragment_ViewBinding implements Unbinder {
  private LightDiskFragment target;

  @UiThread
  public LightDiskFragment_ViewBinding(LightDiskFragment target, View source) {
    this.target = target;

    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
    target.progressPosition = Utils.findRequiredViewAsType(source, R.id.progress_position, "field 'progressPosition'", TextView.class);
    target.start_archive = Utils.findRequiredViewAsType(source, R.id.start_archive, "field 'start_archive'", TextView.class);
    target.relativeLayout = Utils.findRequiredViewAsType(source, R.id.recording_rl, "field 'relativeLayout'", RelativeLayout.class);
    target.guidang = Utils.findRequiredViewAsType(source, R.id.statr, "field 'guidang'", RelativeLayout.class);
    target.xinxi = Utils.findRequiredViewAsType(source, R.id.disk, "field 'xinxi'", RelativeLayout.class);
    target.nothing = Utils.findRequiredViewAsType(source, R.id.image, "field 'nothing'", RelativeLayout.class);
    target.display = Utils.findRequiredViewAsType(source, R.id.display, "field 'display'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LightDiskFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressBar = null;
    target.progressPosition = null;
    target.start_archive = null;
    target.relativeLayout = null;
    target.guidang = null;
    target.xinxi = null;
    target.nothing = null;
    target.display = null;
    target.content = null;
  }
}
