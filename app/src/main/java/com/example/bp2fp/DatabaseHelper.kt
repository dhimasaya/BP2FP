package com.example.bp2fp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(var context:Context):SQLiteOpenHelper(
    context, DATABASE_NAME,null, DATABASE_VERSION) {



    companion object {
        //Account Table
        private val DATABASE_NAME = "breathe"
        private val DATABASE_VERSION = 3
        private val TABLE_ACCOUNT = "account"
        private val COLUMN_EMAIL = "email"
        private val COLUMN_NAME = "name"
        private val COLUMN_PASSWORD = "password"

        //News Table
        private val TABLE_NEWS = "news"
        private val COLUMN_ID_NEWS = "idNews"
        private val COLUMN_TITLE = "judulBerita"
        private val COLUMN_ISI = "isiBerita"
        private val COLUMN_IMGNEWS = "gambarBerita"
    }

    //CREATE & DROP ACCOUNT TABLE
    private val CREATE_ACCOUNT_TABLE = ("CREATE TABLE " + TABLE_ACCOUNT + "(" +
            COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT," +
            COLUMN_PASSWORD + " TEXT)")

    private val DROP_ACCOUNT_TABLE = "DROP TABLE IF EXISTS $TABLE_ACCOUNT"


    //CREATE NEWS TABLE
    private val CREATE_NEWS_TABLE = ("CREATE TABLE " + TABLE_NEWS + "("
            + COLUMN_ID_NEWS + " INT PRIMARY KEY, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_ISI + " TEXT, "
            + COLUMN_IMGNEWS + " BLOB)")

    private val DROP_NEWS_TABLE = "DROP TABLE IF EXISTS $TABLE_NEWS"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_ACCOUNT_TABLE)
        p0?.execSQL(CREATE_NEWS_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(DROP_ACCOUNT_TABLE)
        p0?.execSQL(DROP_NEWS_TABLE)
        onCreate(p0)
    }

    fun checkLogin(email: String, password: String): Boolean {
        val colums = arrayOf(COLUMN_EMAIL)
        val db = this.readableDatabase
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(TABLE_ACCOUNT, colums, selection, selectionArgs, null, null, null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true
        else
            return false
    }

    fun addAccount(email: String, name: String, password: String) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_PASSWORD, password)

        val result = db.insert(TABLE_ACCOUNT, null, values)

        if (result == (0).toLong()){
            Toast.makeText(context, "Resigter Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Register Success" + "Please Login using your new account!", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

//    fun addBerita(model: NewsModel){
//        val db = this.writableDatabase
//        val values = ContentValues()
//        values.put(COLUMN_ID_NEWS,model.id)
//        values.put(COLUMN_TITLE,model.title)
//        values.put(COLUMN_ISI,model.desc)
//
//        val byteOutputStream = ByteArrayOutputStream()
//        val imageByte:ByteArray
//
//        model.image.compress(CompressFormat.JPEG,100,byteOutputStream)
//        imageByte = byteOutputStream.toByteArray()
//        values.put(COLUMN_IMGNEWS,imageByte)
//
//        val result = db.insert(TABLE_NEWS,null, values)
//        db.close()
//    }

    @SuppressLint("Range")
    fun checkData(email: String): String {
        val columns = arrayOf(COLUMN_NAME)
        val db = this.readableDatabase
        val selection = "$COLUMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        var name: String = ""

        val cursor = db.query(TABLE_ACCOUNT, columns, selection, selectionArgs, null, null, null)
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
        }
        cursor.close()
        db.close()
        return name
    }
}