package com.app.nftcreatorappz.creator.activities.canvas

import android.graphics.Color
import com.app.nftcreatorappz.creator.models.Coordinates

fun CanvasActivity.colorPickerToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val color = outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)

    if (color == Color.TRANSPARENT) setPixelColor(Color.WHITE)
    else setPixelColor(color)
}