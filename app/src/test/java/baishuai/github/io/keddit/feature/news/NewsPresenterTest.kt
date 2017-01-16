package baishuai.github.io.keddit.feature.news

import baishuai.github.io.keddit.data.model.RedditNewsItem
import baishuai.github.io.keddit.data.remote.RedditService
import baishuai.github.io.keddit.data.repo.RedditRepo
import baishuai.github.io.keddit.data.wrapper.RedditChildren
import baishuai.github.io.keddit.data.wrapper.RedditDataResponse
import baishuai.github.io.keddit.data.wrapper.RedditNewsResponse
import baishuai.github.io.keddit.data.wrapper.RedditNewsWrapper
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * Unit Tests for NewsPresenter
 * Created by Bai Shuai on 17/1/10.
 */
class NewsPresenterTest {

    lateinit var testSub: TestSubscriber<RedditNewsWrapper>
    lateinit var apiMock: RedditRepo
    lateinit var callMock: Call<RedditNewsResponse>

    @Before
    fun setUp() {

        testSub = TestSubscriber<RedditNewsWrapper>()
        val serviceMock = mock<RedditService>()
        apiMock = RedditRepo(serviceMock)
        callMock = mock<Call<RedditNewsResponse>>()
        whenever(serviceMock.getTop(anyString(), anyString())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        // prepare
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)
        whenever(callMock.execute()).thenReturn(response)

        // call
        val newsPresenter = NewsPresenter(apiMock)
        testSub = newsPresenter.getNewsRx("").test()

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

    @Test
    fun testSuccess_checkOneNews() {
        // setup
        val newsData = RedditNewsItem("author", "title", 10, Date().time, "thumbnail", "url")
        val newsResponse = RedditNewsResponse(RedditDataResponse(listOf(RedditChildren(newsData)), null, null))
        val response = Response.success(newsResponse)
        whenever(callMock.execute()).thenReturn(response)

        // call
        val newsPresenter = NewsPresenter(apiMock)
        newsPresenter.getNewsRx("").subscribe(testSub)

        // assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()

        testSub.assertValue({
            it.news[0].author == newsData.author
                    && it.news[0].title == newsData.title
        })
    }

    @Test
    fun testError() {
        // prepare
        val responseError = Response.error<RedditNewsResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))
        whenever(callMock.execute()).thenReturn(responseError)
        // call
        val newsManager = NewsPresenter(apiMock)
        newsManager.getNewsRx("").subscribe(testSub)

        // assert
        assert(testSub.errorCount() == 1)
    }
}