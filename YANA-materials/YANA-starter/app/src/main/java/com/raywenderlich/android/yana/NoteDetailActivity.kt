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


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.EditText
import java.util.*

class NoteDetailActivity : AppCompatActivity() {

  companion object {

    const val EXTRA_NOTE = "EXTRA_NOTE"

    fun newIntent(context: Context) = Intent(context, NoteDetailActivity::class.java)

    fun newIntent(context: Context, note: Note): Intent {
      val intent = newIntent(context)
      intent.putExtra(EXTRA_NOTE, note)
      return intent
    }

  }

  private lateinit var note: Note

  private lateinit var noteRepository: NoteRepository

  private lateinit var editNoteView: EditText
  private lateinit var lowPriorityView: View
  private lateinit var normalPriorityView: View
  private lateinit var highPriorityView: View
  private lateinit var urgentPriorityView: View
  private lateinit var noteCardView: CardView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_note_detail)

    editNoteView = findViewById(R.id.editNoteView)
    lowPriorityView = findViewById(R.id.lowPriorityView)
    normalPriorityView = findViewById(R.id.normalPriorityView)
    highPriorityView = findViewById(R.id.highPriorityView)
    urgentPriorityView = findViewById(R.id.urgentPriorityView)
    noteCardView = findViewById(R.id.noteCardView)

    noteRepository = (application as NoteApplication).noteRepository

    note = intent.getParcelableExtra(EXTRA_NOTE) ?: Note("", 0)

    editNoteView.onTextChanged {
      note.text = it
      note.lastModifed = Date().time
    }

    lowPriorityView.setOnClickListener { onPriorityChange(0, it) }
    normalPriorityView.setOnClickListener { onPriorityChange(1, it) }
    highPriorityView.setOnClickListener { onPriorityChange(2, it) }
    urgentPriorityView.setOnClickListener { onPriorityChange(3, it) }

    showNote(note)
  }

  private fun onPriorityChange(priority: Int, view: View) {
    note.priority = priority
    showNote(note)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val centerX = (view.x + view.width / 2).toInt()
      showRevealAnimation(centerX)
    }
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  private fun showRevealAnimation(centerX: Int) {
    val w = noteCardView.width.toDouble()
    val h = noteCardView.height.toDouble()
    val endRadius = Math.hypot(w, h).toFloat()
    val revealAnimator = ViewAnimationUtils.createCircularReveal(
        noteCardView,
        centerX,
        0,
        0f,
        endRadius
    )

    noteCardView.visibility = View.VISIBLE
    revealAnimator.duration = 800
    revealAnimator.start()
  }

  private fun showNote(note: Note) {
    editNoteView.setText(note.text)
    noteCardView.setCardBackgroundColor(
        ContextCompat.getColor(editNoteView.context, note.getPriorityColor())
    )
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  override fun onBackPressed() {
    if (note.text.isEmpty()) {
      delete()
    } else {
      noteRepository.saveNote(note)
    }
    super.onBackPressed()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_note_detail, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_delete) {
      delete()
      supportFinishAfterTransition()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  private fun delete() {
    noteRepository.deleteNote(note)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      cancelTransitionAnimation()
    }
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  private fun cancelTransitionAnimation() {
    noteCardView.transitionName = null
  }
}