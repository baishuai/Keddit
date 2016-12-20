package baishuai.github.io.keddit.feture.news

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baishuai.github.io.keddit.R
import baishuai.github.io.keddit.customized.InfiniteScrollListener
import baishuai.github.io.keddit.data.wrapper.RedditNewsWrapper
import baishuai.github.io.keddit.feture.base.RxBaseFragment
import baishuai.github.io.keddit.util.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.*

/**
 * kotlin android extensions is fucking great
 * Created by Bai Shuai on 16/12/18.
 */
class NewsFragment : RxBaseFragment() {

    private val newsList by lazy { news_list }
    private val newsPresenter by lazy { NewsPresenter() }
    private var newsWrapper: RedditNewsWrapper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        newsList.layoutManager = layoutManager
        newsList.clearOnScrollListeners()
        newsList.addOnScrollListener(InfiniteScrollListener(layoutManager) { requestNews() })

        initAdapter()

        if (savedInstanceState == null) {
            requestNews()
        }
    }

    private fun requestNews() {
        /**
         * first time will send empty string for after parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the after param.
         */
        val subscription = newsPresenter.getNews(newsWrapper?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            newsWrapper = retrievedNews.copy(news = emptyList())
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        dispose.add(subscription)
    }

    private fun initAdapter() {
        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }


}