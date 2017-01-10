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
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.jetbrains.spek.api.Spek
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * Unit Tests for NewsPresenter using Spek
 * Created by Bai Shuai on 17/1/10.
 */
class NewsPresenterSpecTest : Spek({

    given("a NewsPresenter") {
        var testSub = TestObserver<RedditNewsWrapper>()
        var apiMock = RedditRepo(mock())
        var callMock = mock<Call<RedditNewsResponse>>()

        beforeEach {

            testSub = TestObserver<RedditNewsWrapper>()
            val serviceMock = mock<RedditService>()
            apiMock = RedditRepo(serviceMock)
            callMock = mock<Call<RedditNewsResponse>>()
            whenever(serviceMock.getTop(anyString(), anyString())).thenReturn(callMock)
        }

        on("service returns something") {
            beforeEach {
                val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
                val response = Response.success(redditNewsResponse)
                whenever(callMock.execute()).thenReturn(response)

                // call
                val newsPresenter = NewsPresenter(apiMock)
                testSub = newsPresenter.getNews("").test()
            }

            it("should receive something and no errors") {
                testSub.assertNoErrors()
                testSub.assertValueCount(1)
                testSub.assertComplete()
            }
        }

        on("service returns just one news") {
            val newsData = RedditNewsItem("author", "title", 10, Date().time, "thumbnail", "url")
            beforeEach {
                // prepare
                val newsResponse = RedditNewsResponse(RedditDataResponse(listOf(RedditChildren(newsData)), null, null))
                val response = Response.success(newsResponse)

                whenever(callMock.execute()).thenReturn(response)

                // call
                val newsManager = NewsPresenter(apiMock)
                newsManager.getNews("").subscribe(testSub)
            }

            it("should process only one news successfully") {
                testSub.assertNoErrors()
                testSub.assertValueCount(1)
                testSub.assertComplete()

                testSub.assertValue({
                    it.news[0].author == newsData.author
                            && it.news[0].title == newsData.title
                })
            }
        }

        on("service returns a 500 error") {
            beforeEach {
                // prepare
                val responseError = Response.error<RedditNewsResponse>(500,
                        ResponseBody.create(MediaType.parse("application/json"), ""))

                `when`(callMock.execute()).thenReturn(responseError)

                // call
                val newsManager = NewsPresenter(apiMock)
                newsManager.getNews("").subscribe(testSub)
            }

            it("should receive an onError message") {
                assert(testSub.errorCount() == 1)
            }
        }
    }

})


