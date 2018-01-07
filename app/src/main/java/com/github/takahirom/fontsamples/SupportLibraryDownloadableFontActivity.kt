package com.github.takahirom.fontsamples

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SupportLibraryDownloadableFontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platform_downloadable_font)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SupportLibraryDownloadableFontActivity::class.java))
        }
    }
}
