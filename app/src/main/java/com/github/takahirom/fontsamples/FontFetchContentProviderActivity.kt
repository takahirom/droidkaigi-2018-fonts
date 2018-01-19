package com.github.takahirom.fontsamples

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.provider.FontsContractCompat
import android.widget.Toast

class FontFetchContentProviderActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_fetch)

        val uri = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
                .authority("com.google.android.gms.fonts")
                .build()

        val fileBaseUri = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
                .authority("com.google.android.gms.fonts")
                .appendPath("file")
                .build()

        var cursor = contentResolver.query(uri, arrayOf<String>(FontsContractCompat.Columns._ID, FontsContractCompat.Columns.FILE_ID, FontsContractCompat.Columns.TTC_INDEX, FontsContractCompat.Columns.VARIATION_SETTINGS, FontsContractCompat.Columns.WEIGHT, FontsContractCompat.Columns.ITALIC, FontsContractCompat.Columns.RESULT_CODE),
                "query = ?", arrayOf("Asset"), null)

        val result = mutableListOf<FontsContractCompat.FontInfo>()
        if (cursor != null && cursor.count > 0) {
            val resultCodeColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.RESULT_CODE)
            val idColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns._ID)
            val fileIdColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.FILE_ID)
            val ttcIndexColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.TTC_INDEX)
            val weightColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.WEIGHT)
            val italicColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.ITALIC)
            while (cursor.moveToNext()) {
                val resultCode = if (resultCodeColumnIndex != -1)
                    cursor.getInt(resultCodeColumnIndex)
                else
                    FontsContractCompat.Columns.RESULT_CODE_OK
                val ttcIndex = if (ttcIndexColumnIndex != -1)
                    cursor.getInt(ttcIndexColumnIndex)
                else
                    0
                val fileUri: Uri
                if (fileIdColumnIndex == -1) {
                    val id = cursor.getLong(idColumnIndex)
                    fileUri = ContentUris.withAppendedId(uri, id)
                } else {
                    val id = cursor.getLong(fileIdColumnIndex)
                    fileUri = ContentUris.withAppendedId(fileBaseUri, id)
                }

                val weight = if (weightColumnIndex != -1) cursor.getInt(weightColumnIndex) else 400
                val italic = italicColumnIndex != -1 && cursor.getInt(italicColumnIndex) == 1
                result.add(FontsContractCompat.FontInfo(fileUri, ttcIndex, weight, italic, resultCode))
            }
        }

        result.forEach {
            Toast.makeText(this, it.weight.toString(), Toast.LENGTH_LONG).show()
            Toast.makeText(this, it.ttcIndex.toString(), Toast.LENGTH_LONG).show()
        }
    }
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FontFetchContentProviderActivity::class.java))
        }
    }
}
