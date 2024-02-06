package dev.mihon.bitmap

import android.graphics.BitmapFactory
import kotlinx.io.Source
import kotlinx.io.asInputStream

actual interface BitmapFactory {

    actual fun decodeSource(source: Source): Bitmap

    actual fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap?

    actual companion object : dev.mihon.bitmap.BitmapFactory {
        private var instance: dev.mihon.bitmap.BitmapFactory = BitmapFactoryAndroidGraphicsImpl()

        override fun decodeSource(source: Source): Bitmap {
            return instance.decodeSource(source)
        }

        override fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap? {
            return instance.decodeByteArray(data, offset, length)
        }

        actual fun setInstance(bitmapFactory: dev.mihon.bitmap.BitmapFactory) {
            instance = bitmapFactory
        }
    }
}

private class BitmapFactoryAndroidGraphicsImpl : dev.mihon.bitmap.BitmapFactory {
    override fun decodeSource(source: Source): Bitmap {
        return Bitmap(BitmapFactory.decodeStream(source.asInputStream()))
    }

    override fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap? {
        return BitmapFactory.decodeByteArray(data, offset, length)?.let { Bitmap(it) }
    }
}