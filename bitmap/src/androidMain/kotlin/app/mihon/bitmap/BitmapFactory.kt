package app.mihon.bitmap

import android.graphics.BitmapFactory
import kotlinx.io.Source
import kotlinx.io.asInputStream

actual object BitmapFactory {
    actual fun decodeStream(source: Source): Bitmap {
        return Bitmap(BitmapFactory.decodeStream(source.asInputStream()))
    }

    actual fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap? {
        return BitmapFactory.decodeByteArray(data, offset, length)?.let { Bitmap(it) }
    }
}