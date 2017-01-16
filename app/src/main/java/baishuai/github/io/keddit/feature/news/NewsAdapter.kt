package baishuai.github.io.keddit.feature.news

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import baishuai.github.io.keddit.R
import baishuai.github.io.keddit.data.model.RedditNewsItem
import baishuai.github.io.keddit.util.getFriendlyTime
import baishuai.github.io.keddit.util.inflate
import baishuai.github.io.keddit.util.loadImg
import kotlinx.android.synthetic.main.news_item.view.*
import java.util.*

/**
 * Created by Bai Shuai on 16/12/18.
 * Adapter use Delegate pattern
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<RedditNewsItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as NewsViewHolder
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {
        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }

    fun addNews(news: List<RedditNewsItem>) {
        val initPosition = items.size
        items.addAll(initPosition, news)
        //notifyItemRangeInserted(initPosition, itemCount)
        if (initPosition == 0) {
            notifyDataSetChanged()
        } else
            notifyItemRangeChanged(initPosition, itemCount)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        items.addAll(news)
        notifyDataSetChanged()
    }

    fun getNews(): List<RedditNewsItem> {
        return items
    }
}