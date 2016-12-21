package baishuai.github.io.keddit.feature.news

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import baishuai.github.io.keddit.R
import baishuai.github.io.keddit.data.model.RedditNewsItem
import baishuai.github.io.keddit.feature.common.ViewType
import baishuai.github.io.keddit.feature.common.ViewTypeDelegateAdapter
import baishuai.github.io.keddit.util.getFriendlyTime
import baishuai.github.io.keddit.util.inflate
import baishuai.github.io.keddit.util.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by Bai Shuai on 16/12/18.
 */
class NewsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = NewsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
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
}