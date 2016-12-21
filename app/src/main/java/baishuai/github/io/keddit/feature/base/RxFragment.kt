package baishuai.github.io.keddit.feature.base

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Bai Shuai on 16/12/18.
 */
class RxFragment : RxBaseFragment() {

    var dis2Stop: CompositeDisposable? = null
    var dis2Destroy: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dis2Destroy = CompositeDisposable()
    }

    override fun onStart() {
        super.onStart()
        dis2Stop = CompositeDisposable()
    }

    override fun onStop() {
        super.onStop()
        dis2Stop!!.dispose()
        dis2Stop = null
    }

    override fun onDestroy() {
        super.onDestroy()
        dis2Destroy!!.dispose()
        dis2Destroy = null
    }

//    fun addUntilStop(dis: Disposable) {
//        dis2Stop?.add(dis)
//    }
//
//    fun addUntilDestroy(dis: Disposable) {
//        dis2Destroy?.add(dis)
//    }
}