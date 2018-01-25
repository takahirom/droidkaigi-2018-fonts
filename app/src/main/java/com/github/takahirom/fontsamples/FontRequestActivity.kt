package com.github.takahirom.fontsamples

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v4.provider.FontRequest
import android.support.v4.provider.FontsContractCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_font_request.*

class FontRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_request)
        val textView = font_text
        val request = FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Days One",
                R.array.com_google_android_gms_fonts_certs_prod
        )
        FontsContractCompat.requestFont(
                this,
                request,
                object : FontsContractCompat.FontRequestCallback() {
                    override fun onTypefaceRetrieved(typeface: Typeface?) {
                        Toast.makeText(this@FontRequestActivity, "we get typeface:" + typeface, Toast.LENGTH_SHORT).show()
                        textView.typeface = typeface
                    }

                    override fun onTypefaceRequestFailed(reason: Int) {
                        super.onTypefaceRequestFailed(reason)
                        Toast.makeText(this@FontRequestActivity, "fail :" + reason, Toast.LENGTH_SHORT).show()
                    }
                }, Handler()
        )
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FontRequestActivity::class.java))
        }
    }
}
