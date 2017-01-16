package baishuai.github.io.keddit.feature.base

import easymvp.AbstractPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class RxPresenter<V> : AbstractPresenter<V>() {

    private val dispose = CompositeDisposable()

    override fun onDestroyed() {
        super.onDestroyed()
        dispose.dispose()
        dispose.clear()
    }

    fun addSubscription(dis: Disposable) {
        dispose.add(dis)
    }

    fun removeSubscription(dis: Disposable) {
        dispose.remove(dis)
    }
}
