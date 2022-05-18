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

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NoteRepositoryImpl(private val sharedPreferences: SharedPreferences) : NoteRepository {

  companion object {
    const val NOTES_KEY = "NOTES_KEY"
  }

  private val gson = Gson()

  private inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

  override fun getNotes(): List<Note> {
    val notesString = sharedPreferences.getString(NOTES_KEY, null)
    if (notesString != null) {
      return gson.fromJson(notesString)
    }

    return emptyList()
  }

  override fun saveNote(note: Note) {
    val index = getNotes().indexOfFirst { n -> n.id == note.id }

    val notes: List<Note>
    if (index > -1) {
      notes = getNotes().toMutableList()
      notes[index] = note
    } else {
      notes = listOf(note) + getNotes()
    }
    save(notes)
  }

  override fun deleteNote(note: Note) {
    val notes = getNotes().filter { n -> n.id != note.id }
    save(notes)
  }

  private fun save(notes: List<Note>) {
    val editor = sharedPreferences.edit()
    editor.putString(NOTES_KEY, gson.toJson(notes))
    editor.apply()
  }
}