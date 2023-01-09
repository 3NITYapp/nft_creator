package com.app.nftcreatorappz

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.app.nftcreatorappz.creator.activities.canvas.CanvasActivity
import com.app.nftcreatorappz.creator.database.AppData
import com.app.nftcreatorappz.data.SharedPref
import com.app.nftcreatorappz.data.StringConstants
import com.app.nftcreatorappz.databinding.ActivityHomeBinding
import com.app.nftcreatorappz.databinding.DialogCreateProjectBinding
import com.app.nftcreatorappz.fragment.FragmentExported
import com.app.nftcreatorappz.fragment.FragmentProject
import com.app.nftcreatorappz.fragment.FragmentSaved
import com.app.nftcreatorappz.fragment.FragmentSetting
import com.app.nftcreatorappz.model.MenuView
import com.app.nftcreatorappz.utils.Tools


class ActivityHome : AppCompatActivity() {

    private var menuViews = hashMapOf<Int, MenuView>()
    private var menuSelected: MenuView? = null

    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPref: SharedPref
    var myActionMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = SharedPref(this)

        initComponent()
        initToolbar()
        onNavigationMenuClick(findViewById(R.id.menuProjectActivityHome))

        if (sharedPref.getFirstLaunch()) {
            ActivityIntro().navigate(this)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbarActivityHome)
    }

    private fun initComponent() {

        val menuHome = MenuView(
            R.string.menu_home,
            R.id.menuProjectActivityHome,
            R.id.menuProjectImgActivityHome,
            R.id.frameContainerActivityHome,
            FragmentProject.instance()
        )
        val menuExported = MenuView(
            R.string.menu_exported,
            R.id.menuExportedActivityHome,
            R.id.menuExportedImgActivityHome,
            R.id.frameContainerExportedActivityHome,
            FragmentExported.instance()
        )
        val menuAdd = MenuView(R.string.menu_add, R.id.menuAddActivityHome, R.id.menuAddStripActivityHome, -1, null)
        val menuSaved = MenuView(
            R.string.menu_saved,
            R.id.menuSavedActivityHome,
            R.id.menuSavedImgActivityHome,
            R.id.frameContainerSavedActivityHome,
            FragmentSaved.instance()
        )
        val menuSetting = MenuView(
            R.string.menu_setting,
            R.id.menuSettingsActivityHome,
            R.id.menuSettingsImgActivityHome,
            R.id.frameContainerSettingActivityHome,
            FragmentSetting.instance()
        )

        menuViews[R.id.menuProjectActivityHome] = menuHome
        menuViews[R.id.menuExportedActivityHome] = menuExported
        menuViews[R.id.menuAddActivityHome] = menuAdd
        menuViews[R.id.menuSavedActivityHome] = menuSaved
        menuViews[R.id.menuSettingsActivityHome] = menuSetting

    }

    fun onNavigationMenuClick(view: View) {
        val menu = menuViews[view.id]
        if (menuSelected?.parent == menu?.parent) return

        if (menu?.parent == R.id.menuAddActivityHome) {
            showDialogCreate()
            return
        }

        val image = findViewById<ImageView>(menu?.image!!)
        image.colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(this, R.color.primary),
            PorterDuff.Mode.SRC_IN
        )
        val frameLayout = findViewById<FrameLayout>(menu.frame)
        frameLayout.visibility = View.VISIBLE

        binding.toolbarActivityHome.title = getString(menu.title)

        when {
            supportFragmentManager.findFragmentById(menu.frame) == null -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(menu.frame, menu.fragment!!)
                transaction.commit()
            }
            menu.parent == R.id.menuExportedActivityHome -> {
                (menu.fragment as FragmentExported?)!!.onResume()
            }
            menu.parent == R.id.menuSettingsActivityHome -> {
                (menu.fragment as FragmentSetting?)!!.onResume()
            }
        }
        if (menuSelected != null) {
            (findViewById<View>(menuSelected!!.image) as ImageView).setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.ic_soft
                ), PorterDuff.Mode.SRC_IN
            )
            findViewById<View>(menuSelected!!.frame).visibility = View.GONE
        }
        menuSelected = menu
        myActionMenuItem?.collapseActionView()
    }



    fun showDialogCreate() {
        var bindingView = DialogCreateProjectBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        var count: Int = AppData.pixelArtDB.pixelArtCreationsDao().countPixelArt()
        count++
        bindingView.name.setText("Untitled-" + count)
        bindingView.dimen.setText("12")
        dialog.setCancelable(true)
        dialog.setContentView(bindingView.root)
        bindingView.dimen.visibility = View.GONE
        val radios = arrayOf(
            bindingView.radio12x,
            bindingView.radio16x,
            bindingView.radio24x,
            bindingView.radio32x,
            bindingView.radio48x,
            bindingView.radio64x,
            bindingView.radioCustom
        )
        bindingView.radio12x.isChecked = true;
        var spanCount: Int = 12
        var selectedRadio = bindingView.radio12x
        for (r in radios) {
            r.setOnClickListener {
                selectedRadio.isChecked = false
                selectedRadio = r
                spanCount = Integer.parseInt(r.tag.toString())
                if (r == bindingView.radioCustom) {
                    bindingView.dimen.visibility = View.VISIBLE
                } else {
                    bindingView.dimen.visibility = View.GONE
                }
            }

        }
        bindingView.btnCreate.setOnClickListener {
            if (selectedRadio == bindingView.radioCustom) {
                var countStr: String = bindingView.dimen.text.toString()
                if (!Tools.isNumber(countStr)) return@setOnClickListener
                spanCount = Integer.parseInt(countStr)
            }
            dialog.dismiss()
            startActivity(
                Intent(this, CanvasActivity::class.java)
                    .putExtra(StringConstants.SPAN_COUNT_EXTRA, spanCount)
                    .putExtra(StringConstants.PROJECT_TITLE_EXTRA, bindingView.name.text.toString())
            )
        }
        dialog.show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)

        myActionMenuItem = menu?.findItem(R.id.action_search)
        val searchView = myActionMenuItem?.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                (menuSelected?.fragment as FragmentProject).initComponent(query)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        myActionMenuItem?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                if (menuSelected?.parent != R.id.menuProjectActivityHome) onNavigationMenuClick(findViewById(R.id.menuProjectActivityHome))
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                if (menuSelected?.parent == R.id.menuProjectActivityHome && myActionMenuItem!!.isActionViewExpanded) {
                    (menuSelected?.fragment as FragmentProject).initComponent("")
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    override fun onBackPressed() {
        doExitApp()
    }

    private var exitTime: Long = 0

    fun doExitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }
}