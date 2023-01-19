package com.example.zad4_todolist.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.zad4_todolist.models.Task
import java.util.*

class SqliteDatabase internal constructor(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE_COL + " TEXT," +
                DESCRIPTION_COL + " TEXT)")
        db.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int,
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getTasks(): ArrayList<Task> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val taskList = ArrayList<Task>()
        with(cursor) {
            while (moveToNext()) {
                taskList.add(
                    Task(
                        getInt(getColumnIndex(ID_COL)),
                        getString(getColumnIndex(TITLE_COL)),
                        getString(getColumnIndex(DESCRIPTION_COL))
                    )
                )
            }
            close()
        }
        return taskList
    }

    fun deleteTask(position: Int) {
        val db = readableDatabase
        db.delete(TABLE_NAME, "$ID_COL = ?", arrayOf(position.toString()))
    }

    fun addTask(taskTitle: String): Long {
        val db = readableDatabase
        val contentValues = ContentValues().apply {
            put(TITLE_COL, taskTitle)
            put(DESCRIPTION_COL, "")
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "TODO_LIST"
        private const val TABLE_NAME = "tasks"
        private const val ID_COL = "id"
        private const val TITLE_COL = "title"
        private const val DESCRIPTION_COL = "description"
    }
}