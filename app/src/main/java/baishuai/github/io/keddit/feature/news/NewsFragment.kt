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
import baishuai.github.io.keddit.feature.base.BaseFragment
import baishuai.github.io.keddit.util.inflate
import easymvp.annotation.FragmentView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

/**
 * kotlin android extensions is fucking great
 * Created by Bai Shuai on 16/12/18.
 */
@FragmentView(presenter = NewsPresenter::class)
class NewsFragment : BaseFragment(), NewsView {

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    @Inject
    @Presenter
    lateinit var newsPresenter: NewsPresenter

    private var newsWrapper: RedditNewsWrapper? = null

    override fun daggerInject() {
        KedditApp.appComponent.newsComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsList.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()

            addOnScrollListener(InfiniteScrollListener(linearLayout) { requestNews() })
        }

        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }

        if (savedInstanceState != null &&
                savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            newsWrapper = savedInstanceState.getParcelable(KEY_REDDIT_NEWS)
            (newsList.adapter as NewsAdapter).clearAndAddNews(newsWrapper!!.news)
        }
    }

    override fun onResume() {
        super.onResume()
        if (newsWrapper == null) {
            newsPresenter.getNews(newsWrapper?.after ?: "")
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (newsList.adapter as NewsAdapter).getNews()
        if (newsWrapper != null && news.isNotEmpty()) {
            outState.putParcelable(KEY_REDDIT_NEWS, newsWrapper?.copy(news = news))
        }
    }

    override fun showNews(news: RedditNewsWrapper) {
        newsWrapper = news.copy(news = emptyList())
        (newsList.adapter as NewsAdapter).addNews(news.news)
    }

    override fun showError(err: Throwable) {
        Snackbar.make(newsList, err.message ?: "Something Wrong!", Snackbar.LENGTH_SHORT).show()
    }

    private fun requestNews() {
        /**
         * first time will send empty string for after parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the after param.
         */
        newsPresenter.getNews(newsWrapper?.after ?: "")
    }

}