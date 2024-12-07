package com.example.list_view

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),Removable {
    val entry:MutableList<String> = mutableListOf()
    private var adapter:ArrayAdapter<String>? = null
    private lateinit var  editTextName:EditText
    private lateinit var editTextAge:EditText
    private lateinit var buttonSaveBTN:Button
    private lateinit var buttonExitBTN:Button
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editTextAge = findViewById(R.id.editTextAge)
        editTextName = findViewById(R.id.editTextName)
        buttonExitBTN = findViewById(R.id.buttonExitBTN)
        buttonSaveBTN = findViewById(R.id.buttonSaveBTN)
        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, entry )
        listView.adapter = adapter

        buttonSaveBTN.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()
            if(name.isNotEmpty() && age.isNotEmpty()){
                entry.add("$name, $age")
                adapter!!.notifyDataSetChanged()

                editTextName.text.clear()
                editTextAge.text.clear()
            }
        }
            listView.onItemClickListener =
                AdapterView.OnItemClickListener{parent, v, position, id ->
                    val note = adapter!!.getItem(position)
                    val dialog = MyDialog()
                    val args = Bundle()
                    args.putString("note", note)
                    dialog.arguments = args
                    dialog.show(supportFragmentManager, "custom")

                }

        buttonExitBTN.setOnClickListener {
            finish()
        }
    }


    override fun remove(note: String?) {
        adapter?.remove(note)
    }
}