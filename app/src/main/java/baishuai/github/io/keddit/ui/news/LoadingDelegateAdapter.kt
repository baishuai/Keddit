package baishuai.github.io.keddit.ui.news

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import baishuai.github.io.keddit.R
import baishuai.github.io.keddit.ui.common.ViewType
import baishuai.github.io.keddit.ui.common.ViewTypeDelegateAdapter
import baishuai.github.io.keddit.util.inflate

/**
 * Created by Bai Shuai on 16/12/18.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        //empty
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading)
    )
}