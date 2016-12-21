package baishuai.github.io.keddit.injection

import android.content.Context
import android.content.res.Resources
import baishuai.github.io.keddit.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Bai Shuai on 16/12/21.
 */
@Module
class AppModule(val app: KedditApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = app


    @Provides
    @Singleton
    fun provideApplication(): KedditApp = app

    @Provides
    @Singleton
    fun provideResources(): Resources = app.resources
}