package baishuai.github.io.keddit.feature.news

import baishuai.github.io.keddit.data.remote.RedditModule
import baishuai.github.io.keddit.injection.PerActivity
import dagger.Subcomponent

/**
 * Created by Bai Shuai on 16/12/21.
 */
@PerActivity
@Subcomponent(modules = arrayOf(
        RedditModule::class
))
interface NewsComponent {
    fun inject(newsFragment: NewsFragment)
}