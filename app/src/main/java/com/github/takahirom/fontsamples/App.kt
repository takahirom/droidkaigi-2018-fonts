package com.github.takahirom.fontsamples

import android.app.Application
import android.graphics.Color
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.v4.provider.FontRequest
import android.util.Log

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val fontRequest = FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs)
        val config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
                .setReplaceAll(true)
//                .setEmojiSpanIndicatorEnabled(true)
//                .setEmojiSpanIndicatorColor(Color.GREEN)
                .registerInitCallback(object : EmojiCompat.InitCallback() {
                    override fun onInitialized() {
                        Log.i("Emoji","initialized")
                    }

                    override fun onFailed(throwable: Throwable?) {
                        Log.e("Emoji","failed", throwable)
                    }
                })
        EmojiCompat.init(config)
    }
}
