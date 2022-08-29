package com.example.notekeeper2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.notekeeper2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Spinner Code //
        //val dm = DataManager()
        val adapterCourses = ArrayAdapter<CourseInfo>(this,android.R.layout.simple_spinner_item,
        DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //way to access component by id//
        val spin = findViewById<Spinner>(R.id.SpinnerCourses)
        spin.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET)?:
            intent.getIntExtra(NOTE_POSITION , POSITION_NOT_SET)

        if(notePosition != POSITION_NOT_SET)
            displayNote()
        else{
            DataManager.notes.add(NotesInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION,notePosition)
    }
    private fun displayNote() {
        val note = DataManager.notes[notePosition]

        val note_title = findViewById<EditText>(R.id.TextNoteTitle)
        note_title.setText(note.title)

        val note_text = findViewById<EditText>(R.id.TextNoteText)
        note_text.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        val spin = findViewById<Spinner>(R.id.SpinnerCourses)
        spin.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
//        invalidateOptionsMenu()
    }

    override open fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if(notePosition >= DataManager.notes.lastIndex){
            val menuitem = menu.findItem(R.id.action_next)
            if (menuitem != null){
                menuitem.icon = getDrawable(R.drawable.ic_block_white_24)
                menuitem.isEnabled = false
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]

        val note_title_1 = findViewById<EditText>(R.id.TextNoteTitle)
        note.title  = note_title_1.text.toString()

        val note_text_2 = findViewById<EditText>(R.id.TextNoteText)
        note.title = note_text_2.text.toString()

        val spin_1 = findViewById<Spinner>(R.id.SpinnerCourses)
        note.course = spin_1.selectedItem as CourseInfo


    }
}
