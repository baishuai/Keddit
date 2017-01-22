package baishuai.github.io.keddit.util

import android.content.Context
import android.graphics.Typeface
import java.util.*

/**
 * Created by Bai Shuai on 17/1/22.
 * Get fonts from assets
 */
object FontUtil {

    private val sTypefaceCache = HashMap<String, Typeface>()

    operator fun get(context: Context, font: String): Typeface {
        synchronized(sTypefaceCache) {
            if (!sTypefaceCache.containsKey(font)) {
                val tf = Typeface.createFromAsset(
                        context.applicationContext.assets, "fonts/$font.ttf")
                sTypefaceCache.put(font, tf)
            }
            return sTypefaceCache[font]!!
        }
    }

    fun getName(typeface: Typeface): String? {
        return sTypefaceCache.asIterable().find { it.value == typeface }?.key
    }
}