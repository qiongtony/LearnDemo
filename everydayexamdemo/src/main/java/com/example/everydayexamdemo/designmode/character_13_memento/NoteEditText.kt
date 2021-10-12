package com.example.everydayexamdemo.designmode.character_13_memento

import android.content.Context
import android.util.AttributeSet

class NoteEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, dfs: Int = 0) : androidx.appcompat.widget.AppCompatEditText(context, attrs, dfs) {


    /**
     * 创建记录
     */
    fun createMemoto(): Memoto {
        val memoto = Memoto(text.toString(), selectionStart)
        return memoto
    }

    /**
     * 恢复数据
     */
    fun restoreMemoto(memoto: Memoto) {
        setText(memoto.content)
        setSelection(memoto.cursor)
    }
}