// Generated code from Butter Knife. Do not modify!
package com.languang.bluebox.activity.picturestorege;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.languang.bluebox.R;
import com.languang.bluebox.basework.base.BaseFragmentActivity_ViewBinding;
import com.luck.easyrecyclerview.EasyRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhotoAlbumActivity_ViewBinding extends BaseFragmentActivity_ViewBinding {
  private PhotoAlbumActivity target;

  private View view2131230897;

  private View view2131230900;

  @UiThread
  public PhotoAlbumActivity_ViewBinding(PhotoAlbumActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoAlbumActivity_ViewBinding(final PhotoAlbumActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", EasyRecyclerView.class);
    view = Utils.findRequiredView(source, R.id.item_title_left_tv, "method 'onViewClicked'");
    view2131230897 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_title_right_tv, "method 'onViewClicked'");
    view2131230900 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    PhotoAlbumActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;

    view2131230897.setOnClickListener(null);
    view2131230897 = null;
    view2131230900.setOnClickListener(null);
    view2131230900 = null;

    super.unbind();
  }
}
