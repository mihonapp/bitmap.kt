package dev.mihon.bitmap

fun Bitmap.applyCanvas(block: Canvas.() -> Unit): Bitmap {
    Canvas(this).apply(block)
    return this
}
