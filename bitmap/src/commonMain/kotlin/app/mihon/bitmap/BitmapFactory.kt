package app.mihon.bitmap

import kotlinx.io.Source
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import javax.imageio.ImageIO
import javax.imageio.ImageReader

expect object BitmapFactory {
    fun decodeStream(source: Source): Bitmap

    fun decodeByteArray(data: ByteArray, offset: Int, length: Int): Bitmap?
}