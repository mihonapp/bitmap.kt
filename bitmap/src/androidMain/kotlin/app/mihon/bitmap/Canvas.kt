package app.mihon.bitmap

import android.graphics.Canvas

class Canvas(bitmap: Bitmap) {
    val canvas = Canvas(bitmap.image)

    fun drawBitmap(sourceBitmap: Bitmap, src: Rect, dst: Rect, paint: Paint?) {
        canvas.drawBitmap(
            sourceBitmap.image,
            android.graphics.Rect(src.left, src.top, src.right, src.bottom),
            android.graphics.Rect(dst.left, dst.top, dst.right, dst.bottom),
            paint?.paint
        )
    }
}