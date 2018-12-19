// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.fragment.mapstorage;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PictureDetailFragment_ViewBinding implements Unbinder {
  private PictureDetailFragment target;

  @UiThread
  public PictureDetailFragment_ViewBinding(PictureDetailFragment target, View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.image_view, "field 'imageView'", ImageView.class);
    target.picTag = Utils.findRequiredViewAsType(source, R.id.pic_tag, "field 'picTag'", TextView.class);
    target.fileName = Utils.findRequiredViewAsType(source, R.id.file_name, "field 'fileName'", TextView.class);
    target.createTime = Utils.findRequiredViewAsType(source, R.id.create_time, "field 'createTime'", TextView.class);
    target.measureSize = Utils.findRequiredViewAsType(source, R.id.measure_size, "field 'measureSize'", TextView.class);
    target.roomSize = Utils.findRequiredViewAsType(source, R.id.room_size, "field 'roomSize'", TextView.class);
    target.format = Utils.findRequiredViewAsType(source, R.id.format, "field 'format'", TextView.class);
    target.path = Utils.findRequiredViewAsType(source, R.id.path, "field 'path'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PictureDetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.picTag = null;
    target.fileName = null;
    target.createTime = null;
    target.measureSize = null;
    target.roomSize = null;
    target.format = null;
    target.path = null;
  }
}
