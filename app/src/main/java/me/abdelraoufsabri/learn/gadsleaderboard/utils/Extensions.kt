package me.abdelraoufsabri.learn.gadsleaderboard.utils

import android.content.Context
import android.content.res.Resources.getSystem
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import me.abdelraoufsabri.learn.gadsleaderboard.R

object Extensions {

    var EditText.value: String
        get() = text.toString()
        set(value) {
            setText(value)
        }

    val Uri.extension: String
        get() = MimeTypeMap.getFileExtensionFromUrl(toString())

    val Int.px: Int get() = (this / getSystem().displayMetrics.density).toInt()

    val Int.dp: Int get() = (this * getSystem().displayMetrics.density).toInt()

    val Int.toDp: Int get() = (this / getSystem().displayMetrics.density).toInt()

    val Int.toPx: Int get() = (this * getSystem().displayMetrics.density).toInt()

    fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

    fun Context.getResourceDrawable(name: String): Drawable? {
        return ActivityCompat.getDrawable(this, getResourceId(name))
    }

    fun Context.getResourceId(name: String) =
        resources.getIdentifier(name, "drawable", this.packageName)


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun AppCompatActivity.setupAppBackground() {
        with(window) {
            decorView.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            statusBarColor = Color.TRANSPARENT
            navigationBarColor = ContextCompat.getColor(this@setupAppBackground, R.color.secondary_color)
        }

    }
}
