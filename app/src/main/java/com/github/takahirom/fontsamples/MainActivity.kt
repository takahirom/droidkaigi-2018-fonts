package com.github.takahirom.fontsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = GroupAdapter<ViewHolder>().apply {
            add(SampleItem("Platform Font") {
                PlatformFontActivity.start(this@MainActivity)
            })
            add(SampleItem("Platform Downloadable Font") {
                PlatformDownloadableFontActivity.start(this@MainActivity)
            })
            add(SampleItem("Support Library Font") {
                SupportLibraryFontActivity.start(this@MainActivity)
            })
            add(SampleItem("Support Library Downloadable Font") {
                SupportLibraryDownloadableFontActivity.start(this@MainActivity)
            })
            add(SampleItem("Font request with ResourcesCompat") {
                FontRequestWithResourcesCompatActivity.start(this@MainActivity)
            })
            add(SampleItem("Font request") {
                FontRequestActivity.start(this@MainActivity)
            })
            add(SampleItem("Font access via ContentProvider") {
                FontFetchContentProviderActivity.start(this@MainActivity)
            })
            add(SampleItem("Emoji") {
                EmojiActivity.start(this@MainActivity)
            })
            add(SampleItem("TextView Playground") {
                TextViewPlaygroundActivity.start(this@MainActivity)
            })


            setOnItemClickListener { item, _ ->
                (item as SampleItem).clickListener()
            }
        }
    }

    class SampleItem(val title: String, val clickListener: () -> Unit) : Item() {

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.title.text = title
        }

        override fun getLayout(): Int = R.layout.item
    }
}
