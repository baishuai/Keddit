package baishuai.github.io.keddit.ui.base

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Android on 16/12/18.
 */
open class RxBaseFragment : Fragment() {

    protected val dispose = CompositeDisposable()


    override fun onPause() {
        super.onPause()
        dispose.dispose()
        dispose.clear()
    }

}