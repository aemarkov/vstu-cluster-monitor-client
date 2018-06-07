package ru.vstu.clustermonitor.utils

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

/**
 * Set of methods to convert data for databinding to the
 * correct form
 */
class BindingConversions {
    companion object {
        val format = SimpleDateFormat("dd.mm.yyyy HH:mm")
        /**
         * Convert time span in seconds to readable format
         */
        @JvmStatic
        fun convertSecondsToTime(seconds: Int): String
        {
            return when
            {
                seconds < 60 -> "$seconds с."                                           // <1 мин   S с.
                seconds < 3600 -> "${seconds / 60} мин. ${seconds % 60} с."             // <1 час   M мин. S с.
                seconds < 3600*24 -> "${seconds / 3600} ч. ${(seconds % 3600)/60} мин." // <1 дня   H ч. M мин.
                else -> "${seconds / (24*3600)} д. ${(seconds % (24 * 3600))/3600} ч."  //          D д. H ч.
            }
        }

        /**
         * Convert java.util.Date to string (dd.mm.yyyy HH:mm)
         */
        @JvmStatic
        fun conertDataToString(data: Date): String  = format.format(data)
    }
}