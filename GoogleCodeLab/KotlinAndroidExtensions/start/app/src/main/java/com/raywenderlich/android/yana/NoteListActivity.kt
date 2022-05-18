/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.yana

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

  private lateinit var noteRepository: NoteRepository

  private lateinit var adapter: NoteListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_note_list)

    noteRepository = (application as NoteApplication).noteRepository

    adapter = NoteListAdapter(
        noteRepository.getNotes(),
        object : NoteListAdapter.Listener {
          override fun onNoteClick(view: View, note: Note) {
            editNote(view, note)
          }
        }
    )
//    val noteListView: RecyclerView = findViewById(R.id.noteListView)
//    val addNoteView: View = findViewById(R.id.addNoteView)


    noteListView.adapter = adapter

    addNoteView.setOnClickListener {
      addNote()
    }
  }

  override fun onStart() {
    super.onStart()

    adapter.update(noteRepository.getNotes())
  }

  private fun editNote(view: View, note: Note) {
    val intent = NoteDetailActivity.newIntent(this, note)
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
        view,
        ViewCompat.getTransitionName(view))
    ActivityCompat.startActivity(this, intent, options.toBundle())
  }

  private fun addNote() {
    val intent = NoteDetailActivity.newIntent(this)
    startActivity(intent)
  }
}
