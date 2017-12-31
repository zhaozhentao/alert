package com.wanico.app.ui.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.TextView

import com.wanico.app.BuildConfig
import com.wanico.app.R
import com.wanico.app.ui.activity.BaseActivity
import com.wanico.app.ui.dialog.CustomDialog

/**
 * Created by zhaotao on 2017/12/27.
 */

class AboutActivity : BaseActivity(), View.OnClickListener {

    override fun getLayoutResourceId(): Int {
        return R.layout.about_activity
    }

    override fun onActivityCreate() {
        setOnClickListeners(intArrayOf(R.id.call), this)

        (findViewById<View>(R.id.version) as TextView).text = BuildConfig.VERSION_NAME
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.call -> 打电话()
            R.id.back -> {
            }
        }
    }

    private fun 打电话() {
        val dialog = CustomDialog(this)
        dialog.setTitle("title")
        dialog.setContent("拨打电话 020-23328999")
        dialog.setOK("拨打") { v ->
            dialog.dismiss()
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:020-23328999"))
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return@setOK
            }
            startActivity(intent)
        }
        dialog.show()
    }
}
