package baishuai.github.io.keddit.feature.base

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
class BasePresenter<T : MvpView> : Presenter<T> {
    var mMvpView: T? = null
        private set

    override fun attachView(mvpView: T) {
        mMvpView = mvpView
    }

    override fun detachView() {
        mMvpView = null
    }

    val viewAttached: Boolean
        get() = mMvpView != null

    fun checkViewAttached() {
        if (!viewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException(
            "Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter"
    )

}