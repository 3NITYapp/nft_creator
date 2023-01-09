package com.app.nftcreatorappz.creator.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.nftcreatorappz.R
import com.app.nftcreatorappz.creator.converters.JsonConverter
import com.app.nftcreatorappz.creator.listeners.ColorPickerListener
import com.app.nftcreatorappz.creator.models.ColorPalette
import com.app.nftcreatorappz.creator.viewholders.ColorPickerViewHolder

class ColorPickerAdapter(private val data: ColorPalette, private val caller: ColorPickerListener?, private val isPaletteMode: Boolean = true, private val isPreviewMode: Boolean = false) : RecyclerView.Adapter<ColorPickerViewHolder>() {
    private var colorData = listOf<Int>()

    init {
        colorData = JsonConverter.convertJsonStringToListOfInt(data.colorPaletteColorData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        return ColorPickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_color_picker, parent, false) as View)
    }

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.colorView.backgroundTintList = ColorStateList.valueOf(colorData[position])

        if (isPaletteMode && !isPreviewMode) {
            if (colorData[position] == Color.TRANSPARENT) {
                holder.colorView.setBackgroundResource(R.drawable.ic_add_2)
                holder.colorView.background.setColorFilter(
                    Color.TRANSPARENT,
                    PorterDuff.Mode.DST_OVER
                )
            }
        }

        holder.colorView.setOnClickListener {
            if (colorData[position] != Color.TRANSPARENT) {
                caller?.onColorTapped(colorData[position], it)
            } else {
                caller?.onColorAdded(data)
            }
        }

        holder.colorView.setOnLongClickListener {
            if (colorData[position] != Color.TRANSPARENT) {
                caller?.onColorLongTapped(data.objId, colorData[position], it)
            }
            true
        }


//        if (position == 0) {
//            caller?.onColorTapped(colorData[position], holder.colorView)
//        }
    }

    override fun getItemCount() = colorData.size
}