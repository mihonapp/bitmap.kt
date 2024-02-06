package app.mihon.bitmap

import android.annotation.TargetApi
import android.os.Build
import kotlinx.io.Sink
import kotlinx.io.asOutputStream

actual class Bitmap(val image: android.graphics.Bitmap) {
    actual val width: Int = image.width
    actual val height: Int = image.height

    actual enum class CompressFormat(val native: android.graphics.Bitmap.CompressFormat) {
        JPEG(android.graphics.Bitmap.CompressFormat.JPEG),
        PNG(android.graphics.Bitmap.CompressFormat.PNG),
        @TargetApi(Build.VERSION_CODES.R)
        WEBP_LOSSY(android.graphics.Bitmap.CompressFormat.WEBP_LOSSY),
        @TargetApi(Build.VERSION_CODES.R)
        WEBP_LOSSLESS(android.graphics.Bitmap.CompressFormat.WEBP_LOSSLESS)
    }

    actual enum class Config(val native: android.graphics.Bitmap.Config) {
        ALPHA_8(android.graphics.Bitmap.Config.ALPHA_8),
        RGB_565(android.graphics.Bitmap.Config.RGB_565),
        ARGB_8888(android.graphics.Bitmap.Config.ARGB_8888),
        @TargetApi(Build.VERSION_CODES.O)
        RGBA_F16(android.graphics.Bitmap.Config.RGBA_F16),
        @TargetApi(Build.VERSION_CODES.O)
        HARDWARE(android.graphics.Bitmap.Config.HARDWARE),
        @TargetApi(Build.VERSION_CODES.TIRAMISU)
        RGBA_1010102(android.graphics.Bitmap.Config.RGBA_1010102);
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
            return Bitmap(android.graphics.Bitmap.createBitmap(width, height, config.native))
        }

        actual fun createBitmap(source: Bitmap, x: Int, y: Int, width: Int, height: Int): Bitmap {
            return Bitmap(android.graphics.Bitmap.createBitmap(source.image, x, y, width, height))
        }
    }
}