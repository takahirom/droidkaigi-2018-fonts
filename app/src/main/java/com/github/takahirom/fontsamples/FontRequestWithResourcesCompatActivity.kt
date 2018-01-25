package com.github.takahirom.fontsamples

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_font_request_with_resources_compat.*

class FontRequestWithResourcesCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_request_with_resources_compat)
        val fontText = font_text

        fontText.typeface = ResourcesCompat.getFont(this, R.font.alegreya_regular)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FontRequestWithResourcesCompatActivity::class.java))
        }
    }
}
