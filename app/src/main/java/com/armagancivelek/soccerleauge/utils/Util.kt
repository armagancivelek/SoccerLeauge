package com.armagancivelek.soccerleauge.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.provider.Settings

fun isConnected(context: Context): Boolean {


    val cm = context.applicationContext.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = cm.activeNetwork ?: return false

        val capabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false


        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false

        }

    } else {
        cm.activeNetworkInfo?.run {
            return when (type) {
                TYPE_WIFI -> true
                TYPE_MOBILE -> true
                TYPE_ETHERNET -> true
                else -> false

            }
        }

    }

    return false


}

fun showInternetDialog(context: Context) {
    AlertDialog.Builder(context).apply {
        setMessage("Internet bağlantınızı aktif hale getiriniz")
        setCancelable(false)
        setPositiveButton("Bağlan", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                (context as Activity).finish()

            }

        })
        setNegativeButton("Geri", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                (context as Activity).finish()

            }


        })
        this.create().show()
    }


}