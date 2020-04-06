package com.example.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ResourcesProvider @Inject constructor(
    val context: Context
) {

    fun getString(stringResId: Int) = context.getString(stringResId)

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable = context.getDrawable(drawableRes)!!

    fun getBitmapFromDrawable(@DrawableRes drawableRes: Int) : Bitmap {
        val drawable = getDrawable(drawableRes)
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun getMarkerIcon(@DrawableRes drawableRes: Int): BitmapDescriptor {
        return BitmapDescriptorFactory.fromBitmap(getBitmapFromDrawable(drawableRes))
    }

    fun getCurrentDateForRequest(): String {
        val sdf = SimpleDateFormat("yyyyMMdd")
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sdf.format(Calendar.getInstance().time)
        } else {
            sdf.format(Date())
        }

    }
}