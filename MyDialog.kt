package com.example.list_view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialog : DialogFragment(){
    private var removable:Removable? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable = context as Removable?
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val note = requireArguments().getString("note")
        val builder  = AlertDialog.Builder(
            requireActivity()
        )
        return builder
            .setTitle("Внимание")

            .setMessage("Удалить заметку $note?")
            .setPositiveButton("Да"){dialog, which ->
                removable?.remove(note)
            }
            .setNegativeButton("Отмена", null)
            .create()
    }
}