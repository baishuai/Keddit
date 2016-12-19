package baishuai.github.io.keddit.ui.common

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Bai Shuai on 16/12/18.
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}