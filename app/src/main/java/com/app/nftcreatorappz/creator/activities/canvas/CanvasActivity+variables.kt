package com.app.nftcreatorappz.creator.activities.canvas

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.view.Menu
import androidx.fragment.app.Fragment
import com.app.nftcreatorappz.R
import com.app.nftcreatorappz.creator.adapters.ColorPickerAdapter
import com.app.nftcreatorappz.databinding.ActivityCanvasBinding
import com.app.nftcreatorappz.creator.fragments.colorpicker.ColorPickerFragment
import com.app.nftcreatorappz.creator.fragments.outercanvas.OuterCanvasFragment
import com.app.nftcreatorappz.creator.fragments.tools.ToolsFragment
import com.app.nftcreatorappz.creator.models.ColorPalette
import com.app.nftcreatorappz.creator.models.PixelArt
import com.app.nftcreatorappz.data.SharedPref

lateinit var binding: ActivityCanvasBinding
var index: Int? = null

var primaryColor: Int = R.color.bg_bottom_tool_bar
var secondaryColor: Int = R.color.bg_bottom_tool_bar
var spanCount = 5
var isPrimaryColorSelected = true

var isSelected = false
var background: Drawable? = null

var currentFragmentInstance: Fragment? = null

var sharedPref: SharedPref? = null

lateinit var currentPixelArtObj: PixelArt

enum class Tools {
    PENCIL_TOOL,
    FILL_TOOL,
    HORIZONTAL_MIRROR_TOOL,
    VERTICAL_MIRROR_TOOL,
    LINE_TOOL,
    RECTANGLE_TOOL,
    OUTLINED_RECTANGLE_TOOL,
    COLOR_PICKER_TOOL,
    ERASE_TOOL,
    PALETTE_TOOL
}

var currentTool: Tools = Tools.PENCIL_TOOL

var saved = true

lateinit var colorPickerFragmentInstance: ColorPickerFragment
lateinit var outerCanvasInstance: OuterCanvasFragment

var colorPickerAdapter : ColorPickerAdapter? = null
var selectedColorPalette: ColorPalette? = null

lateinit var menu: Menu

var toolsFragmentInstance: ToolsFragment? = null

var lineMode_hasLetGo = false
var rectangleMode_hasLetGo = false

var projectTitle: String? = null

lateinit var sharedPreferenceObject: SharedPreferences


