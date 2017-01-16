package baishuai.github.io.keddit.widgets.loadmore

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.ViewGroup

/**
 * Created by Bai Shuai on 17/1/16.
 */
class WrapperAdapter(val adapter: Adapter<RecyclerView.ViewHolder>, val footer: ILoadMoreFooter) : Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val FOOTER_VIEWTYPE = -2
    }

    var mNoMore: Boolean = false
        set(value) {
            field = value
            notifyItemChanged(adapter.itemCount)
        }


    init {

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                this@WrapperAdapter.notifyDataSetChanged()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                this@WrapperAdapter.notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                this@WrapperAdapter.notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                this@WrapperAdapter.notifyItemRangeRemoved(positionStart, itemCount)
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                this@WrapperAdapter.notifyDataSetChanged()
            }

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == WrapperAdapter.FOOTER_VIEWTYPE) {
            LoadMoreFooterContainerViewHolder(footer, parent)
        } else {
            adapter.onCreateViewHolder(parent, viewType)
        }
    }

    override fun getItemCount(): Int {
        return if (adapter.itemCount == 0)
            adapter.itemCount
        else
            adapter.itemCount + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == adapter.itemCount) {
            holder as LoadMoreFooterContainerViewHolder
            if (mNoMore)
                holder.footer.setNoMore()
            else
                holder.footer.setLoadMore()
        } else {
            adapter.onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == adapter.itemCount) {
            WrapperAdapter.FOOTER_VIEWTYPE
        } else {
            adapter.getItemViewType(position)
        }
    }

    class LoadMoreFooterContainerViewHolder(val footer: ILoadMoreFooter, parent: ViewGroup)
        : RecyclerView.ViewHolder(footer.getView(parent))
}