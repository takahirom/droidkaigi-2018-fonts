package com.github.takahirom.fontsamples

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_emoji.*

class EmojiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji)
        val text = "this is a test:"+EMOJI
        button.text = text
        textView.text = text
        editText.text = Editable.Factory.getInstance().newEditable(text)

        button.setOnClickListener {
            button.text
        }
    }

    companion object {
        // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F4BB] (PERSONAL COMPUTER)
        private const val WOMAN_TECHNOLOGIST = "\uD83D\uDC69\u200D\uD83D\uDCBB"

        // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F3A4] (MICROPHONE)
        private const val WOMAN_SINGER = "\uD83D\uDC69\u200D\uD83C\uDFA4"

        internal const val EMOJI = WOMAN_TECHNOLOGIST + WOMAN_SINGER+WOMAN_TECHNOLOGIST + WOMAN_SINGER

        fun start(context: Context) {
            context.startActivity(Intent(context, EmojiActivity::class.java))
        }
    }
}
