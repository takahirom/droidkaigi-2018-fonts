package com.github.takahirom.fontsamples

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class PlatformFontActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platform_font)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PlatformFontActivity::class.java))
        }
    }
}
