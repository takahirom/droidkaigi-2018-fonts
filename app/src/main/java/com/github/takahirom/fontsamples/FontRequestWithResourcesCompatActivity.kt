package com.github.takahirom.fontsamples

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.res.ResourcesCompat
import android.widget.TextView
import android.widget.Toast

class FontRequestWithResourcesCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_request_with_resources_compat)
        ResourcesCompat.getFont(this, R.font.orbitron, object: ResourcesCompat.FontCallback() {

            override fun onFontRetrieved(typeface: Typeface) {
                findViewById<TextView>(R.id.font_text).typeface = typeface
            }

            override fun onFontRetrievalFailed(reason: Int) {
                Toast.makeText(this@FontRequestWithResourcesCompatActivity, "fail :"+reason, Toast.LENGTH_SHORT).show()
            }
        }, Handler())
    }
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FontRequestWithResourcesCompatActivity::class.java))
        }
    }
}
