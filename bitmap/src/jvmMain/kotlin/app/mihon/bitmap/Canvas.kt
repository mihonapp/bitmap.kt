package app.mihon.bitmap

import java.awt.Graphics2D
import java.awt.Paint
import java.awt.image.BufferedImage

class Canvas(bitmap: Bitmap) {
    private val canvasImage: BufferedImage
    private val canvas: Graphics2D

    init {
        canvasImage = bitmap.image
        canvas = canvasImage.createGraphics()
    }

    /**
     * TODO implement [paint]
     */
    fun drawBitmap(sourceBitmap: Bitmap, src: Rect, dst: Rect, paint: Paint?) {
        val sourceImage = sourceBitmap.image
        val sourceImageCropped =
            sourceImage.getSubimage(src.left, src.top, src.width, src.height)
        canvas.drawImage(sourceImageCropped, null, dst.left, dst.top)
    }
}