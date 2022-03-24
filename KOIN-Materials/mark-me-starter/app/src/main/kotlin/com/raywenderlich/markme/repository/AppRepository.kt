/*
 * Copyright (c) 2019 Razeware LLC
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
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.markme.repository

import com.raywenderlich.markme.feature.FeatureContract
import com.raywenderlich.markme.model.Student
import com.raywenderlich.markme.model.database.AppDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

object AppRepository : FeatureContract.Model<Student> {
    private val database: AppDatabase by inject()

    private const val MSG_DATA_SAVED_TO_DB = "Data saved to DB"
    private const val MSG_DATA_SAVED_TO_PREFS = "Data saved to prefs"

    override fun add2Db(data: List<Student>, callback: (String) -> Unit) {
        doAsync {
            database.userDao().insertStudentList(data)
            uiThread {
                callback(MSG_DATA_SAVED_TO_DB)
            }
        }
    }
    override fun add2Prefs(data: List<Student>, callback: (String) -> Unit) {
        doAsync {
            data.forEach {
                with(sharedPreferences.edit()) {
                    val jsonString = Gson().toJson(it)
                    putString(it.name, jsonString).commit()
                }
            }
            uiThread {
                callback(MSG_DATA_SAVED_TO_PREFS)
            }
        }
    }

    override fun fetchFromDb(data: List<Student>, callback: (List<Student>) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchFromPrefs(data: List<Student>): List<Student> {
        return listOf()
    }
    object AppRepository : FeatureContract.Model<Student>, KoinComponent {
}