package baishuai.github.io.keddit.feture.news

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import baishuai.github.io.keddit.BuildConfig
import baishuai.github.io.keddit.data.model.RedditNewsItem
import baishuai.github.io.keddit.feture.common.ViewType
import baishuai.github.io.keddit.feture.common.ViewTypeDelegateAdapter
import timber.log.Timber
import java.util.*

/**
 * Created by Bai Shuai on 16/12/18.
 * Adapter use Delegate pattern
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }

    fun addNews(news: List<RedditNewsItem>) {
        val initPosition = items.size - 1
        items.addAll(initPosition, news)
        notifyItemRangeChanged(initPosition, itemCount)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        items.addAll(news)
        items.add(loadingItem)
        notifyDataSetChanged()
    }

    fun getNews(): List<RedditNewsItem> {
        return items
                .filter { it.getViewType() == AdapterConstants.NEWS }
                .map { it as RedditNewsItem }
    }
}