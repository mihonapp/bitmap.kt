package dev.mihon.bitmap

import android.annotation.TargetApi
import android.os.Build
import kotlinx.io.Sink
import kotlinx.io.asOutputStream
import android.graphics.Bitmap as NativeBitmap

actual class Bitmap(val image: NativeBitmap) {
    actual val width: Int = image.width
    actual val height: Int = image.height

    actual enum class CompressFormat(val native: NativeBitmap.CompressFormat) {
        JPEG(NativeBitmap.CompressFormat.JPEG),
        PNG(NativeBitmap.CompressFormat.PNG),
        @TargetApi(Build.VERSION_CODES.R)
        WEBP_LOSSY(NativeBitmap.CompressFormat.WEBP_LOSSY),
        @TargetApi(Build.VERSION_CODES.R)
        WEBP_LOSSLESS(NativeBitmap.CompressFormat.WEBP_LOSSLESS)
    }

    actual enum class Config(val native: NativeBitmap.Config) {
        ALPHA_8(NativeBitmap.Config.ALPHA_8),
        RGB_565(NativeBitmap.Config.RGB_565),
        ARGB_8888(NativeBitmap.Config.ARGB_8888),
        @TargetApi(Build.VERSION_CODES.O)
        RGBA_F16(NativeBitmap.Config.RGBA_F16),
        @TargetApi(Build.VERSION_CODES.O)
        HARDWARE(NativeBitmap.Config.HARDWARE),
        @TargetApi(Build.VERSION_CODES.TIRAMISU)
        RGBA_1010102(NativeBitmap.Config.RGBA_1010102);
    }

    actual fun compress(format: CompressFormat, quality: Int, sink: Sink): Boolean {
        return image.compress(format.native, quality, sink.asOutputStream())
    }

    actual fun getPixels(
        /*@ColorInt*/ pixels: IntArray,
        offset: Int,
        stride: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        image.getPixels(pixels, offset, stride, x, y, width, height)
    }

    actual companion object {
        actual fun createBitmap(width: Int, height: Int, config: Config): Bitmap {
            return Bitmap(NativeBitmap.createBitmap(width, height, config.native))
        }

        actual fun createBitmap(source: Bitmap, x: Int, y: Int, width: Int, height: Int): Bitmap {
            return Bitmap(NativeBitmap.createBitmap(source.image, x, y, width, height))
        }
    }
}