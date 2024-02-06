package dev.mihon.bitmap

import kotlinx.io.Source
import kotlinx.io.asInputStream
import android.graphics.BitmapFactory as AndroidGraphicsBitmapFactory

actual interface BitmapFactory {

    actual fun decodeSource(source: Source): Bitmap

    actual fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap?

    actual companion object : BitmapFactory {
        private var instance: BitmapFactory = BitmapFactoryAndroidGraphicsImpl()

        override fun decodeSource(source: Source): Bitmap {
            return instance.decodeSource(source)
        }

        override fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap? {
            return instance.decodeByteArray(data, offset, length)
        }

        actual fun setInstance(bitmapFactory: BitmapFactory) {
            instance = bitmapFactory
        }
    }
}

private class BitmapFactoryAndroidGraphicsImpl : BitmapFactory {
    override fun decodeSource(source: Source): Bitmap {
        return Bitmap(AndroidGraphicsBitmapFactory.decodeStream(source.asInputStream()))
    }

    override fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap? {
        return AndroidGraphicsBitmapFactory.decodeByteArray(data, offset, length)?.let { Bitmap(it) }
    }
}