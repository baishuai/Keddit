package baishuai.github.io.keddit.widgets.loadmore

import android.support.v7.widget.RecyclerView
import timber.log.Timber

/**
 * Created by Bai Shuai on 17/1/16.
 */
class LoadMoreScrollListener(val loadMore: () -> Unit) : RecyclerView.OnScrollListener() {

    var noMore = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (noMore)
            return

        val visibleItemCount = recyclerView.layoutManager.childCount

        val triggerCondition = visibleItemCount > 0
                && newState === RecyclerView.SCROLL_STATE_IDLE
                && canTriggerLoadMore(recyclerView)

        if (triggerCondition) {
            loadMore()
        }
    }

    fun canTriggerLoadMore(recyclerView: RecyclerView): Boolean {
        val lastChild = recyclerView.getChildAt(recyclerView.childCount - 1)
        val position = recyclerView.getChildLayoutPosition(lastChild)
        val totalItemCount = recyclerView.layoutManager.itemCount
        Timber.d("totalItemCount - 1 == position %d, %d", totalItemCount - 1, position)
        return totalItemCount - 1 == position
    }

}