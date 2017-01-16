package baishuai.github.io.keddit.widgets.loadmore

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import timber.log.Timber

/**
 * ref TODO {@url https://github.com/Aspsine/IRecyclerView/blob/master/library/src/main/java/com/aspsine/irecyclerview/IRecyclerView.java}
 * Created by Bai Shuai on 17/1/16.
 * Add footer loadmoreView, emptyView, endView
 */
class LoadMoreRecyclerView : RecyclerView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    val mScrollListener: LoadMoreScrollListener

    private var mLoadMoreListener: LoadMoreListener? = null
    private var mAdapter: WrapperAdapter? = null

    var mHasMore: Boolean = true
        set(value) {
            field = value
            mScrollListener.noMore = true
            mAdapter?.mNoMore = true
        }


    fun setLoadMoreListener(listener: LoadMoreListener) {
        mLoadMoreListener = listener
    }

    init {
        initView(context)
        Timber.d("add listener")
        mScrollListener = LoadMoreScrollListener({ mLoadMoreListener?.onLoadMore() })
        addOnScrollListener(mScrollListener)
    }


    fun initView(contex: Context) {
    }

    override fun setAdapter(adapter: Adapter<RecyclerView.ViewHolder>) {
        mAdapter = WrapperAdapter(adapter, SimpleFooter())
        super.setAdapter(mAdapter)
    }

    interface LoadMoreListener {
        fun onLoadMore()
    }
}