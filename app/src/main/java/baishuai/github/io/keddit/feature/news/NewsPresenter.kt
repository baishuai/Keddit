package baishuai.github.io.keddit.feature.news

import baishuai.github.io.keddit.data.repo.RedditRepo
import baishuai.github.io.keddit.data.wrapper.RedditChildren
import baishuai.github.io.keddit.data.wrapper.RedditNewsWrapper
import baishuai.github.io.keddit.feature.base.RxPresenter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Bai Shuai on 16/12/18.
 */
class NewsPresenter @Inject constructor(private val repo: RedditRepo) : RxPresenter<NewsView>() {

    fun getNews(after: String, limit: Int = 10) {
        addSubscription(getNewsRx(after, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ view?.showNews(it) },
                        { view?.showError(it) }))
    }


    fun getNewsRx(after: String, limit: Int = 10): Flowable<RedditNewsWrapper> {
        return Flowable.create({ e: FlowableEmitter<RedditNewsWrapper> ->
            val callResponse = repo.getNews(after, limit)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataResponse = response.body().data

                val news = dataResponse.children.map(RedditChildren::data)
                val newsWrapper = RedditNewsWrapper(
                        dataResponse.before ?: "",
                        dataResponse.after ?: "",
                        news
                )
                e.onNext(newsWrapper)
                e.onComplete()
            } else {
                e.onError(Throwable(response.message()))
            }
        }, BackpressureStrategy.DROP)
    }
}