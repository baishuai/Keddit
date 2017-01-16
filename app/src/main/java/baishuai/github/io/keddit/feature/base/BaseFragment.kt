package baishuai.github.io.keddit.feature.base

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by Bai Shuai on 16/12/18.
 */
abstract class BaseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        daggerInject()
    }

    abstract fun daggerInject()

}