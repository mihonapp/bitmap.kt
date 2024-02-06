package app.mihon.bitmap

import kotlinx.io.Sink

expect class Bitmap {
    val width: Int
    val height: Int

    enum class CompressFormat {
        JPEG,
        PNG,
        WEBP_LOSSY,
        WEBP_LOSSLESS
    }

    enum class Config {
        ALPHA_8,
        RGB_565,
        ARGB_8888,
        RGBA_F16,
        HARDWARE,
        RGBA_1010102
    }

    fun compress(format: CompressFormat, quality: Int, sink: Sink): Boolean

    fun getPixels(
        /*@ColorInt*/ pixels: IntArray,
        offset: Int,
        stride: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    )

    companion object {

        fun createBitmap(width: Int, height: Int, config: Config): Bitmap

        fun createBitmap(source: Bitmap, x: Int, y: Int, width: Int, height: Int): Bitmap
    }
}