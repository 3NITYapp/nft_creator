package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.R
import com.app.nftcreatorappz.creator.extensions.showDialog
import com.app.nftcreatorappz.data.StringConstants

fun CanvasActivity.extendedOnToolTapped(toolName: String) {
    when (toolName) {
        StringConstants.PENCIL_TOOL_IDENTIFIER -> currentTool = Tools.PENCIL_TOOL

        StringConstants.FILL_TOOL_IDENTIFIER  -> currentTool = Tools.FILL_TOOL

        StringConstants.VERTICAL_MIRROR_TOOL_IDENTIFIER  -> currentTool = Tools.VERTICAL_MIRROR_TOOL

        StringConstants.HORIZONTAL_MIRROR_TOOL_IDENTIFIER -> currentTool = Tools.HORIZONTAL_MIRROR_TOOL

        StringConstants.LINE_TOOL_IDENTIFIER -> currentTool = Tools.LINE_TOOL

        StringConstants.RECTANGLE_TOOL_IDENTIFIER -> currentTool = Tools.RECTANGLE_TOOL

        StringConstants.CLEAR_CANVAS_TOOL_IDENTIFIER  -> {
            showDialog(
                this.getString(R.string.clear_canvas_tlt),
                this.getString(R.string.clear_canvas_msg),
                StringConstants.DIALOG_POSITIVE_BUTTON_TEXT,
                { _, _ ->
                    clearCanvas()
                }, this.getString(R.string.cancel), { _, _ -> }, null)
        }

        StringConstants.COLOR_PICKER_TOOL_IDENTIFIER -> currentTool = Tools.COLOR_PICKER_TOOL

        StringConstants.ERASE_TOOL_IDENTIFIER -> currentTool = Tools.ERASE_TOOL

        StringConstants.PALETTE_TOOL_IDENTIFIER -> {
            currentTool = Tools.PALETTE_TOOL
            selectedColorPalette?.let { extendedOnAddColorTapped(it) }
        }
    }
}