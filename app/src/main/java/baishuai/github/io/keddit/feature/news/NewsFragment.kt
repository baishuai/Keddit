package baishuai.github.io.keddit.feature.news

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baishuai.github.io.keddit.KedditApp
import baishuai.github.io.keddit.R
import baishuai.github.io.keddit.customized.InfiniteScrollListener
import baishuai.github.io.keddit.data.wrapper.RedditNewsWrapper
import baishuai.github.io.keddit.feature.base.RxBaseFragment
import baishuai.github.io.keddit.util.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

/**
 * kotlin android extensions is fucking great
 * Created by Bai Shuai on 16/12/18.
 */
class NewsFragment : RxBaseFragment() {

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    @Inject lateinit var newsPresenter: NewsPresenter
    private var newsWrapper: RedditNewsWrapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KedditApp.appComponent.newsComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsList.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()

            addOnScrollListener(InfiniteScrollListener(linearLayout) { requestNews() })
        }

        initAdapter()

        if (savedInstanceState != null &&
                savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            newsWrapper = savedInstanceState.getParcelable(KEY_REDDIT_NEWS)
            (newsList.adapter as NewsAdapter).clearAndAddNews(newsWrapper!!.news)
        } else {
            requestNews()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (newsList.adapter as NewsAdapter).getNews()
        if (newsWrapper != null && news.isNotEmpty()) {
            outState.putParcelable(KEY_REDDIT_NEWS, newsWrapper?.copy(news = news))
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
                            (newsList.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(newsList, e.message ?: "", Snackbar.LENGTH_LONG).show()
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