package dev.mihon.bitmap

import android.graphics.Canvas

actual class Canvas actual constructor(bitmap: Bitmap) {
    val canvas = Canvas(bitmap.image)

    actual fun drawBitmap(sourceBitmap: Bitmap, src: Rect, dst: Rect) {
        canvas.drawBitmap(
            sourceBitmap.image,
            android.graphics.Rect(src.left, src.top, src.right, src.bottom),
            android.graphics.Rect(dst.left, dst.top, dst.right, dst.bottom),
            null
        )
    }
}