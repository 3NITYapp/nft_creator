package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.creator.algorithms.LineAlgorithm
import com.app.nftcreatorappz.creator.algorithms.AlgorithmInfoParameter
import com.app.nftcreatorappz.creator.models.BitmapAction
import com.app.nftcreatorappz.creator.models.BitmapActionData
import com.app.nftcreatorappz.creator.models.Coordinates

fun CanvasActivity.horizontalMirrorToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction != null) {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(Coordinates(coordinatesTapped.x, (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1), outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x,(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1)))
    } else {
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction = BitmapAction(mutableListOf())
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(coordinatesTapped, outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x, coordinatesTapped.y)))
        outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!.actionData.add(BitmapActionData(Coordinates(coordinatesTapped.x, (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1), outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped.x,(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1)))
    }

    if (outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewX != null && outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewY != null) {
        val lineAlgorithmInstance = LineAlgorithm(AlgorithmInfoParameter(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap, outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor()))

        lineAlgorithmInstance.compute(Coordinates(outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewX!!, outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewY!!), coordinatesTapped)
        lineAlgorithmInstance.compute(Coordinates(outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewX!!, (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.height - outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewY!!) - 1), Coordinates(coordinatesTapped.x, (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1))
    }

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, coordinatesTapped.y, getSelectedColor())
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.overrideSetPixel(coordinatesTapped.x, (outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap.height - coordinatesTapped.y) - 1, getSelectedColor())

    outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewX = coordinatesTapped.x
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.previewY = coordinatesTapped.y
}
