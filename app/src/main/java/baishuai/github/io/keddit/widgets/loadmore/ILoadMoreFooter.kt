package baishuai.github.io.keddit.widgets.loadmore

import android.view.View
import android.view.ViewGroup

/**
 * Created by Bai Shuai on 17/1/16.
 */
interface ILoadMoreFooter {
    fun getView(parent: ViewGroup): View
    fun setNoMore()
    fun setLoadMore()
}