package com.example.notekeeper2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.notekeeper2.databinding.ActivityNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            val activityIntent = Intent(this,MainActivity::class.java)
            startActivity(activityIntent)
        }

        var list = findViewById<ListView>(R.id.listNotes)
        list.adapter = ArrayAdapter(this , android.R.layout.simple_list_item_1,DataManager.notes)

        list.setOnItemClickListener{parent ,view ,position ,id ->
            val activityIntent = Intent(this,MainActivity::class.java)
            activityIntent.putExtra(NOTE_POSITION,position)
            startActivity(activityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        var list = findViewById<ListView>(R.id.listNotes)
        (list.adapter as ArrayAdapter<NotesInfo>).notifyDataSetChanged()

    }
}