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
import java.util.*
import kotlin.collections.ArrayList

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


    fun generateFixtureForDual(teamCount: Int) {


        //kaç round sonra lig tamamlanacak
        val roundCount = teamCount - 1
        //bir round da kaç maç var
        val matchCountPerRound = teamCount / 2


        var list = ArrayList<Int>()

        //Takım listesi oluşturuluyor
        for (i in 0 until teamCount) {
            list.add(i)
        }
        Collections.shuffle(list)



        for (i in 0 until roundCount) {
            println("${i + 1}. round \n")


            for (j in 0 until matchCountPerRound) {
                val firstIndex = j
                val secondIndex = (teamCount - 1) - j
                println("${list.get(firstIndex)} - ${list.get(secondIndex)} \n")

            }


            val newList = ArrayList<Int>()


            newList.add(list.get(0))
            newList.add(list.get(list.size - 1))


            for (k in 1 until list.size - 1) {
                newList.add(list.get(k))
            }

            list = newList

        }
    }

    fun generateFixtureForSingle(teamCount: Int) {
        val bayList = ArrayList<Int>()
        val teamCount = teamCount + 1
        //kaç round sonra lig tamamlanacak
        val roundCount = teamCount - 1
        //bir round da kaç maç var
        val matchCountPerRound = teamCount / 2

        var list = ArrayList<Int>()
        //Takım listesi oluşturuluyor
        for (i in 0 until teamCount) {
            list.add(i)
        }
        Collections.shuffle(list)// karıştırıyoruz

        val temp = teamCount - 1// eklediğimiz temp sayısını değişkende tutuyoruz

        for (i in 0 until roundCount) {
            println("${i + 1}. round \n")


            for (j in 0 until matchCountPerRound) {
                val firstIndex = j
                val secondIndex = (teamCount - 1) - j
                if (temp == list.get(firstIndex)) {
                    bayList.add(list.get(secondIndex))
                    continue
                }

                if (temp == list.get(secondIndex)) {
                    bayList.add(list.get(firstIndex))
                    continue
                }

                println("${list.get(firstIndex)} - ${list.get(secondIndex)} \n")

            }


            val newList = ArrayList<Int>()


            newList.add(list.get(0))
            newList.add(list.get(list.size - 1))


            for (k in 1 until list.size - 1) {
                newList.add(list.get(k))
            }

            list = newList

        }


    }


}