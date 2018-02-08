package com.github.takahirom.fontsamples

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.text.emoji.getTypeFace
import android.support.text.emoji.showMetadata
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_text_view_playground.*

class TextViewPlaygroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_playground)
        textView.typeface = getTypeFace()
        textView.text = showMetadata()

        val face: CharArray = Character.toChars(0x1f468)
        val lightSkin: CharArray = Character.toChars(0x1f3fb)
        val zwj: CharArray = Character.toChars(0x200d)
        val computer: CharArray = Character.toChars(0x1f4bb)
        val programmer: CharArray = face + lightSkin + zwj + computer
        programmer.forEach {
            println(Integer.toHexString(it.toInt()))
        }
//        codePointTest.text = "\ud83d\udc68\ud83c\udffb\u200d\ud83d\udcbb"
        codePointTest.text = String(programmer)
    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, TextViewPlaygroundActivity::class.java))
        }
    }
}
