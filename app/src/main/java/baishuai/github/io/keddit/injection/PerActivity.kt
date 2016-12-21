package baishuai.github.io.keddit.injection

import javax.inject.Scope

/**
 * Created by Bai Shuai on 16/12/21.
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Activity to be memorised in the
 * correct component.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity
