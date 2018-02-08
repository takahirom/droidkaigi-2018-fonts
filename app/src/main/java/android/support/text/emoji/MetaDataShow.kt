package android.support.text.emoji

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.SparseArray

@SuppressLint("RestrictedApi")
@TargetApi(Build.VERSION_CODES.KITKAT)
fun showMetadata(): String {

    val repo: MetadataRepo = getRepo()
//    return String(getRepo().emojiCharArray)

    return retriveNode(repo.rootNode, 0)
}

private fun getRepo(): MetadataRepo {
    val internalHelper = EmojiCompat.get().javaClass.getDeclaredField("mHelper").apply { isAccessible = true }
            .get(EmojiCompat.get())
    val emojiInternalClass = Class.forName("android.support.text.emoji.EmojiCompat\$CompatInternal19")
    val repo: MetadataRepo = emojiInternalClass.getDeclaredField("mMetadataRepo").apply { isAccessible = true }.get(internalHelper) as MetadataRepo
    return repo
}

private fun MetadataRepo.Node.getChildlen(): SparseArray<MetadataRepo.Node> {
    return MetadataRepo.Node::class.java
            .getDeclaredField("mChildren").apply { isAccessible = true }
            .get(this) as SparseArray<MetadataRepo.Node>
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
@SuppressLint("RestrictedApi")
private fun retriveNode(parent: MetadataRepo.Node, depth: Int, isZWJ: Boolean = false): String {
    var text = ""
    val children = parent.getChildlen()
    val parentChar = parent.data?.getString() ?: if (depth == 0) "root" else "node"
    (0 until children.size()).map {
        val plusChar = children.keyAt(it)
        val node = children.get(plusChar)
        val nodeChar = node.data?.getString() ?: "node"

        var space = ""
        (0 until depth).forEach { space += "　" }

        val zwjCodePoint = 0x200d
        val textPresentationCodePoint = 0xFE0E
        val emojiPresentationCodePoint = 0xFE0F
        val plus = run {
            when (plusChar) {
                zwjCodePoint -> "ZWJ"
                textPresentationCodePoint -> "TEXT PRESENTATION"
                emojiPresentationCodePoint -> "EMOJI PRESENTATION"
                else ->
                    String(Character.toChars(plusChar))
            }
        }
        text += space + parentChar + "+" + plus + /*"(" + plusChar + ")" + */"->" + nodeChar + "\n"


        text += retriveNode(node, depth + 1, plus == "ZWJ")
    }
    return text
}

@SuppressLint("RestrictedApi")
@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun EmojiMetadata.getString(): String {
    return String(charArrayOf(getRepo().emojiCharArray[getIndex() * 2], getRepo().emojiCharArray[getIndex() * 2 + 1]))/* + "(" + getCodepointAt(0) + ")"*/
}

private fun EmojiMetadata.getIndex(): Int {
    return javaClass.getDeclaredField("mIndex").apply { isAccessible = true }.get(this) as Int
}

@SuppressLint("RestrictedApi")
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun getTypeFace(): Typeface {
    return getRepo().typeface
}
