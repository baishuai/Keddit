package baishuai.github.io.keddit.injection

import baishuai.github.io.keddit.data.ApiModule
import baishuai.github.io.keddit.data.DataModule
import baishuai.github.io.keddit.feature.news.NewsComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Bai Shuai on 16/12/21.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiModule::class,
        DataModule::class))
interface AppComponent {
    fun newsComponent(): NewsComponent
}