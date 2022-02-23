package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class mySQLiteHelper (context: Context) : SQLiteOpenHelper (
    context, "friends.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val orderCreation = "CREATE TABLE friends " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",name TEXT" + ",email TEXT)"
        db!!.execSQL(orderCreation)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val orderDelete = "DROP TABLE IF EXISTS friends"
        db!!.execSQL(orderDelete)
        onCreate(db)
    }

    fun addData(name: String, email: String){
        val data = ContentValues()
        data.put("name", name)
        data.put("email", email)

        val db = this.writableDatabase

        db.insert("friends", null, data)
        db.close()
    }

    fun showData(): Cursor? {
        val db : SQLiteDatabase = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM friends",  null)

        return cursor
    }

    fun deleteData(id: Int): Int{
        var args = arrayOf(id.toString())
        val db : SQLiteDatabase = this.writableDatabase
        val cursor = db.delete("friends", "id=?", args)

        db.close()

        return cursor
    }

    fun updateData(id: Int, newName: String, newEmail: String): Int{
        println("--- id: " + id)
        println("--- newName: " + newName)
        println("--- newEmail: " + newEmail)
        if (id.equals(null) || newName == "" || newEmail == ""){
            return 0
        }
        var values = ContentValues()
        values.put("name", newName)
        values.put("email", newEmail)
        var db : SQLiteDatabase = this.writableDatabase
        val success = db.update("friends", values,"id=?", arrayOf(id.toString()))

        return success
    }

}