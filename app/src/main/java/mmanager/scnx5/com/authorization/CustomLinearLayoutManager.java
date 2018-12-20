package mmanager.scnx5.com.authorization;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);

    }

    // it will always pass false to RecyclerView when calling "canScrollVertically()" method.
    @Override
    public boolean canScrollVertically() {
        return false;
    }

    // it will always pass false to RecyclerView when calling "canScrollVertically()" method.
    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
