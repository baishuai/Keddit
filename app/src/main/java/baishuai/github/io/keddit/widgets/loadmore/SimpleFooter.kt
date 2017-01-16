package baishuai.github.io.keddit.widgets.loadmore

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baishuai.github.io.keddit.R
import baishuai.github.io.keddit.databinding.LoadmoreFooterBinding
import timber.log.Timber

/**
 * Created by Bai Shuai on 17/1/16.
 */
class SimpleFooter : ILoadMoreFooter {

    var binding: LoadmoreFooterBinding? = null

    override fun getView(parent: ViewGroup): View {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.loadmore_footer, parent, false)
        return binding!!.root
    }

    override fun setNoMore() {
        Timber.d("view noMore")
        binding?.mLoadMore?.visibility = View.GONE
        binding?.mNoMore?.visibility = View.VISIBLE

    }

    override fun setLoadMore() {
        binding?.mLoadMore?.visibility = View.VISIBLE
        binding?.mNoMore?.visibility = View.GONE
    }
}