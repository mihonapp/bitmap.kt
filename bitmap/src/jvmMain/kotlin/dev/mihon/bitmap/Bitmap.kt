package dev.mihon.bitmap

import kotlinx.io.Sink
import kotlinx.io.asOutputStream
import java.awt.image.BufferedImage
import java.io.IOException
import java.io.OutputStream
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.stream.ImageOutputStream

actual class Bitmap(val image: BufferedImage) {
    actual val width: Int = image.width
    actual val height: Int = image.height

    actual enum class CompressFormat {
        JPEG,
        PNG,
        WEBP_LOSSY,
        WEBP_LOSSLESS
    }

    actual enum class Config {
        ALPHA_8,
        RGB_565,
        ARGB_8888,
        RGBA_F16,
        HARDWARE,
        RGBA_1010102;
    }

    actual fun compress(format: CompressFormat, quality: Int, sink: Sink): Boolean {
        require(quality in 0..100) { "quality must be 0..100" }
        val qualityFloat = quality.toFloat() / 100
        val formatString = when (format) {
            CompressFormat.PNG -> "png"
            CompressFormat.JPEG -> "jpg"
            else -> throw IllegalArgumentException("unsupported compression format!")
        }
        val writers = ImageIO.getImageWritersByFormatName(formatString)
        check(writers.hasNext()) { "no image writers found for this format!" }
        val writer = writers.next()
        val ios: ImageOutputStream = try {
            ImageIO.createImageOutputStream(sink.asOutputStream())
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }
        writer.output = ios
        val param = writer.defaultWriteParam
        if ("jpg" == formatString) {
            param.compressionMode = ImageWriteParam.MODE_EXPLICIT
            param.compressionQuality = qualityFloat
        }
        try {
            writer.write(null, IIOImage(image, null, null), param)
            ios.close()
            writer.dispose()
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }
        return true
    }

    /**
     * Shared code to check for illegal arguments passed to getPixels()
     * or setPixels()
     *
     * @param x      left edge of the area of pixels to access
     * @param y      top edge of the area of pixels to access
     * @param width  width of the area of pixels to access
     * @param height height of the area of pixels to access
     * @param offset offset into pixels[] array
     * @param stride number of elements in pixels[] between each logical row
     * @param pixels array to hold the area of pixels being accessed
     */
    private fun checkPixelsAccess(
        x: Int, y: Int, width: Int, height: Int,
        offset: Int, stride: Int, pixels: IntArray
    ) {
        checkXYSign(x, y)
        require(width >= 0) { "width must be >= 0" }
        if (height < 0) {
            throw IllegalArgumentException("height must be >= 0")
        }
        if (x + width > this.width) {
            throw IllegalArgumentException(
                "x + width must be <= bitmap.width()"
            )
        }
        if (y + height > this.height) {
            throw IllegalArgumentException(
                "y + height must be <= bitmap.height()"
            )
        }
        if (Math.abs(stride) < width) {
            throw IllegalArgumentException("abs(stride) must be >= width")
        }
        val lastScanline = offset + (height - 1) * stride
        val length = pixels.size
        if (offset < 0 || offset + width > length || lastScanline < 0 || lastScanline + width > length) {
            throw ArrayIndexOutOfBoundsException()
        }
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
        checkPixelsAccess(x, y, width, height, offset, stride, pixels)
        val raster = image.data
        val rasterPixels = raster.getPixels(x, y, width, height, null as IntArray?)
        for (ht in 0 until height) {
            val rowOffset = offset + stride * ht
            System.arraycopy(rasterPixels, ht * width, pixels, rowOffset, width)
        }
    }

    actual companion object {
        /**
         * Common code for checking that x and y are >= 0
         *
         * @param x x coordinate to ensure is >= 0
         * @param y y coordinate to ensure is >= 0
         */
        private fun checkXYSign(x: Int, y: Int) {
            if (x < 0) {
                throw IllegalArgumentException("x must be >= 0")
            }
            if (y < 0) {
                throw IllegalArgumentException("y must be >= 0")
            }
        }

        /**
         * Common code for checking that width and height are > 0
         *
         * @param width  width to ensure is > 0
         * @param height height to ensure is > 0
         */
        private fun checkWidthHeight(width: Int, height: Int) {
            if (width <= 0) {
                throw IllegalArgumentException("width must be > 0")
            }
            if (height <= 0) {
                throw IllegalArgumentException("height must be > 0")
            }
        }

        actual fun createBitmap(width: Int, height: Int, config: Config): Bitmap {
            val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            return Bitmap(image)
        }

        actual fun createBitmap(source: Bitmap, x: Int, y: Int, width: Int, height: Int): Bitmap {
            checkXYSign(x, y)
            checkWidthHeight(width, height)
            if (x + width > source.width) {
                throw IllegalArgumentException("x + width must be <= bitmap.width()")
            }
            if (y + height > source.height) {
                throw IllegalArgumentException("y + height must be <= bitmap.height()")
            }

            // Android will make a copy when creating a sub image,
            // so we do the same here
            val subImage = source.image.getSubimage(x, y, width, height)
            val newImage = BufferedImage(subImage.width, subImage.height, subImage.type)
            newImage.data = subImage.data
            return Bitmap(newImage)
        }
    }
}