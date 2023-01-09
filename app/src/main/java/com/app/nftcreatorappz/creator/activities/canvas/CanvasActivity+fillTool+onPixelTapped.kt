package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.creator.algorithms.AlgorithmInfoParameter
import com.app.nftcreatorappz.creator.algorithms.FloodFillAlgorithm
import com.app.nftcreatorappz.creator.models.Coordinates

fun CanvasActivity.fillToolOnPixelTapped(coordinatesTapped: Coordinates) {
    val floodFillAlgorithmInstance = FloodFillAlgorithm(AlgorithmInfoParameter(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap, outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBitmapAction!!, getSelectedColor()))
    floodFillAlgorithmInstance.compute(Coordinates(coordinatesTapped.x, coordinatesTapped.y))
}