package baishuai.github.io.keddit.data.model

import android.os.Parcel
import android.os.Parcelable
import baishuai.github.io.keddit.feature.common.ViewType
import baishuai.github.io.keddit.feature.news.AdapterConstants

/**
 * Created by Bai Shuai on 16/12/18.
 */
data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : ViewType, Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR: Parcelable.Creator<RedditNewsItem> = object : Parcelable.Creator<RedditNewsItem> {
            override fun createFromParcel(source: Parcel): RedditNewsItem = RedditNewsItem(source)
            override fun newArray(size: Int): Array<RedditNewsItem?> = arrayOfNulls(size)
        }
    }


    constructor(parcelIn: Parcel) : this(
            parcelIn.readString(),
            parcelIn.readString(),
            parcelIn.readInt(),
            parcelIn.readLong(),
            parcelIn.readString(),
            parcelIn.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(author)
        dest.writeString(title)
        dest.writeInt(numComments)
        dest.writeLong(created)
        dest.writeString(thumbnail)
        dest.writeString(url)
    }

    override fun describeContents() = 0

    override fun getViewType() = AdapterConstants.NEWS

}