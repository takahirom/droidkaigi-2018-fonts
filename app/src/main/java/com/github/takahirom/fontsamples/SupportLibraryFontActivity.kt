package com.github.takahirom.fontsamples

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SupportLibraryFontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platform_font)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SupportLibraryFontActivity::class.java))
        }
    }
}
