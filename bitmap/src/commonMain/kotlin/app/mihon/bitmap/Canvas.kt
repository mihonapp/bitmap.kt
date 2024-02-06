package app.mihon.bitmap


expect class Canvas(bitmap: Bitmap) {
    fun drawBitmap(sourceBitmap: Bitmap, src: Rect, dst: Rect)
}