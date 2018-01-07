package com.github.takahirom.fontsamples

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class PlatformDownloadableFontActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platform_downloadable_font)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PlatformDownloadableFontActivity::class.java))
        }
    }
}
