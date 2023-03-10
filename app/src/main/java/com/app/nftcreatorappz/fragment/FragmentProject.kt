package com.app.nftcreatorappz.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.app.nftcreatorappz.ActivityHome
import com.app.nftcreatorappz.R
import com.app.nftcreatorappz.adapter.AdapterProject
import com.app.nftcreatorappz.creator.activities.canvas.CanvasActivity
import com.app.nftcreatorappz.creator.database.AppData
import com.app.nftcreatorappz.creator.models.PixelArt
import com.app.nftcreatorappz.data.StringConstants
import com.app.nftcreatorappz.databinding.FragmentProjectBinding
import com.app.nftcreatorappz.utils.Tools.Companion.showDialog

class FragmentProject : Fragment() {

    lateinit var binding: FragmentProjectBinding
    var query: String = ""

    companion object {
        @JvmStatic
        fun instance() = FragmentProject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProjectBinding.inflate(inflater, container, false)

        initComponent("")
        return binding.root
    }
    fun initComponent(q:String) {
        showProgress(true)
        query = q
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        }
        var data: MutableList<PixelArt?> = ArrayList()
        data.add(null)
        AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations(q).observe(viewLifecycleOwner) {
            var items = data
            if (it.isNotEmpty()) {
                items = it.toMutableList()
            }
            binding.recyclerView.adapter =
                AdapterProject(items, object : AdapterProject.AdapterListener {
                    override fun onClicked(creationTapped: PixelArt, pos: Int) {
                        onCreationTappedOverride(pos, creationTapped)
                    }

                    override fun onLongClicked(creationTapped: PixelArt, pos: Int) {
                        onCreationLongTappedOverride(pos, creationTapped)
                    }

                    override fun onNewClicked() {
                        (requireActivity() as ActivityHome).showDialogCreate()
                    }

                })

            showProgress(false)
        }
    }


    private fun onCreationTappedOverride(index:Int, creationTapped: PixelArt) {
        try {
//            if((activity as ActivityHome).showInterstitialAd()) return
        } catch (e: Exception) { }

        startActivity(
            Intent(context, CanvasActivity::class.java)
                .putExtra(StringConstants.INDEX_EXTRA, index)
                .putExtra(StringConstants.PROJECT_TITLE_EXTRA, creationTapped.title))

    }


    private fun onCreationLongTappedOverride(index:Int, creationTapped: PixelArt) {
        (binding.recyclerView.adapter as AdapterProject).userHasLongPressed = true
        showDialog(
            activity,
            this.getString(R.string.delete_tlt),
            this.getString(R.string.delete_msg) + creationTapped.title + "?",
            StringConstants.DIALOG_POSITIVE_BUTTON_TEXT,
            { _, _ ->
                AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations(query).observe(this) {
                    AppData.pixelArtDB.pixelArtCreationsDao()
                        .deletePixelArtCreation(creationTapped.objId)
                    binding.recyclerView.adapter!!.notifyItemRemoved(it.indexOf(creationTapped))
                }
            }, this.getString(R.string.cancel), { _, _ -> }, null)

    }

    private fun showProgress(show: Boolean) {
        binding.progressLoading.visibility = if (show) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }
}