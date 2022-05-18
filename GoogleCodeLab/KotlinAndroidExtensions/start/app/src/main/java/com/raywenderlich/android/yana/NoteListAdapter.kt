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

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.note_item.*
import java.text.SimpleDateFormat
import java.util.*


class NoteListAdapter(private var noteList: List<Note>, private val listener: Listener) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(parent.inflate(R.layout.note_item))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(noteList[position], listener)

    override fun getItemCount() = noteList.size

    class NoteViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

//        private val noteTextView: TextView = itemView.findViewById(R.id.noteTextView)
//        private val noteDateView: TextView = itemView.findViewById(R.id.noteDateView)
//        private val noteCardView: CardView = itemView.findViewById(R.id.noteCardView)

        private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        fun bind(note: Note, listener: Listener) {
            noteTextView.text = note.text
            noteCardView.setCardBackgroundColor(
                ContextCompat.getColor(noteCardView.context, note.getPriorityColor())
            )
            noteCardView.setOnClickListener {
                listener.onNoteClick(noteCardView, note)
            }

            noteDateView.text = sdf.format(Date(note.lastModifed))
        }
    }

    fun update(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }

    interface Listener {
        fun onNoteClick(view: View, note: Note)
    }
}