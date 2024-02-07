package dev.mihon.bitmap

import java.awt.Graphics2D
import java.awt.image.BufferedImage

actual class Canvas actual constructor(bitmap: Bitmap) {
    private val canvasImage: BufferedImage
    private val canvas: Graphics2D

    init {
        canvasImage = bitmap.image
        canvas = canvasImage.createGraphics()
    }

    /**
     * TODO implement [paint]
     */
    actual fun drawBitmap(sourceBitmap: Bitmap, src: Rect, dst: Rect) {
        val sourceImage = sourceBitmap.image
        val sourceImageCropped =
            sourceImage.getSubimage(src.left, src.top, src.width, src.height)
        canvas.drawImage(sourceImageCropped, null, dst.left, dst.top)
    }
}
