package com.example.everydayexamdemo.designmode.chapter_13_memento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.everydayexamdemo.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(inflate.root)
        inflate.et.requestFocus()
        val caretaker = Caretaker()
        inflate.btnUndo.setOnClickListener {
            inflate.et.restoreMemoto(caretaker.getPrevMemoto())
        }

        inflate.btnRedo.setOnClickListener {
            inflate.et.restoreMemoto(caretaker.getNextMemoto())
        }

        inflate.btnSave.setOnClickListener {
            caretaker.saveMemoto(inflate.et.createMemoto())
        }
    }
}