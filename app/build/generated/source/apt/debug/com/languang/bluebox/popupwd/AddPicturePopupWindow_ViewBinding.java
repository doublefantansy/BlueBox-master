// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.popupwd;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddPicturePopupWindow_ViewBinding implements Unbinder {
  private AddPicturePopupWindow target;

  private View view2131230856;

  private View view2131231180;

  private View view2131230755;

  @UiThread
  public AddPicturePopupWindow_ViewBinding(final AddPicturePopupWindow target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.file_ll, "method 'onViewClicked'");
    view2131230856 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.usb_ll, "method 'onViewClicked'");
    view2131231180 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.album_ll, "method 'onViewClicked'");
    view2131230755 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230856.setOnClickListener(null);
    view2131230856 = null;
    view2131231180.setOnClickListener(null);
    view2131231180 = null;
    view2131230755.setOnClickListener(null);
    view2131230755 = null;
  }
}
