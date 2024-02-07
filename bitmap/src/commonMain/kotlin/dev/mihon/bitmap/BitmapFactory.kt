package dev.mihon.bitmap

import kotlinx.io.Source

expect interface BitmapFactory {
    fun decodeSource(source: Source): Bitmap

    fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap?

    companion object : BitmapFactory {
        fun setInstance(bitmapFactory: BitmapFactory)
    }
}
