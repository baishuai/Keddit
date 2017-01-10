package baishuai.github.io.keddit.data.wrapper

import android.os.Parcel
import android.os.Parcelable
import baishuai.github.io.keddit.data.model.RedditNewsItem

/**
 * Created by Bai Shuai on 16/12/20.
 */
data class RedditNewsResponse(val data: RedditDataResponse)

data class RedditDataResponse(
        val children: List<RedditChildren>,
        val after: String?,
        val before: String?
)

data class RedditChildren(val data: RedditNewsItem)

data class RedditNewsWrapper(
        val before: String,
        val after: String,
        val news: List<RedditNewsItem>
) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR: Parcelable.Creator<RedditNewsWrapper> = object :
                Parcelable.Creator<RedditNewsWrapper> {
            override fun newArray(size: Int): Array<out RedditNewsWrapper?> = arrayOfNulls(size)

            override fun createFromParcel(source: Parcel) = RedditNewsWrapper(source)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.createTypedArrayList(RedditNewsItem.CREATOR))

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(after)
        dest.writeString(before)
        dest.writeTypedList(news)
    }

    override fun describeContents() = 0
}