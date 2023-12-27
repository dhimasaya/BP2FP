package com.example.bp2fp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date

class DatabaseHelper(var context:Context):SQLiteOpenHelper(
    context, DATABASE_NAME,null, DATABASE_VERSION) {



    companion object {
        private val DATABASE_NAME = "breathe"
        private val DATABASE_VERSION = 7

        //Account Table
        private val TABLE_ACCOUNT = "account"
        private val COLUMN_EMAIL = "email"
        private val COLUMN_NAME = "name"
        private val COLUMN_PASSWORD = "password"

        //News Table
        private val TABLE_NEWS = "news"
        private val COLUMN_ID_NEWS = "idNews"
        private val COLUMN_TITLE = "judulBerita"
        private val COLUMN_ISI = "isiBerita"

        //Journal Table
        private val TABLE_JOURNAL = "journal"
        private val COLUMN_ID_JOURNAL = "idJournal"
        private val COLUMN_TITLE_JOURNAL = "juduLJournal"
        private val COLUMN_ISI_JOURNAL = "isiJournal"

        //Doctor Table
        private val TABLE_DOCTOR = "doctor"
        private val COLUMN_ID_DOCTOR = "idDoctor"
        private val COLUMN_NAME_DOCTOR = "namaDoctor"
        private val COLUMN_DESC_DOCTOR  = "descDoctor"
        private val COLUMN_HARGA_SESSION = "harga"

        //Transaction Table
        private val TABLE_TRANSACTION = "transaksi"
        private val COLUMN_ID_TRANSACTION = "idTransaksi"
        private val COLUMN_ID_DOCT = "idDoctor"
        private val COLUMN_NAMA_DOCT = "namaDoctor"
        private val COLUMN_TANGGAL = "tanggal"

    }

    //CREATE & DROP ACCOUNT TABLE
    private val CREATE_ACCOUNT_TABLE = ("CREATE TABLE " + TABLE_ACCOUNT + "(" +
            COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
            COLUMN_NAME + " TEXT," +
            COLUMN_PASSWORD + " TEXT)")

    private val DROP_ACCOUNT_TABLE = "DROP TABLE IF EXISTS $TABLE_ACCOUNT"

    //CREATE, iNSERT & DROP NEWS TABLE
    private val CREATE_NEWS_TABLE = ("CREATE TABLE " + TABLE_NEWS + "("
            + COLUMN_ID_NEWS + " INT PRIMARY KEY, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_ISI + " TEXT)")

    private val DROP_NEWS_TABLE = "DROP TABLE IF EXISTS $TABLE_NEWS"

    //CREATE, INSERT & DROP JOURNAL TABLE
    private val CREATE_JOURNAL_TABLE = ("CREATE TABLE " + TABLE_JOURNAL + "("
            + COLUMN_ID_JOURNAL + " INT PRIMARY KEY, "
            + COLUMN_TITLE_JOURNAL + " TEXT, "
            + COLUMN_ISI_JOURNAL + " TEXT)")

    private val DROP_JOURNAL_TABLE = "DROP TABLE IF EXISTS $TABLE_JOURNAL"

    //CREATE & DROP DOCTOR TABLE
    private val CREATE_DOCTOR_TABLE = ("CREATE TABLE " + TABLE_DOCTOR + "("
            + COLUMN_ID_DOCTOR + " INT PRIMARY KEY, "
            + COLUMN_NAME_DOCTOR + " TEXT, "
            + COLUMN_DESC_DOCTOR + " TEXT, "
            + COLUMN_HARGA_SESSION + " INT)")

    private val DROP_DOCTOR_TABLE = "DROP TABLE IF EXISTS $TABLE_DOCTOR"

    //CREATE & DROP TRANSACTION TABLE
    private val CREATE_TRANSACTION_TABLE = ("CREATE TABLE " + TABLE_TRANSACTION + "("
            + COLUMN_ID_TRANSACTION + " INT PRIMARY KEY, "
            + COLUMN_ID_DOCT + " INT, "
            + COLUMN_NAMA_DOCT + " TEXT, "
            + COLUMN_TANGGAL + " DATE)")

    private val DROP_TRANSACTION_TABLE = "DROP TABLE IF EXISTS $TABLE_TRANSACTION"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_ACCOUNT_TABLE)
        db?.execSQL(CREATE_NEWS_TABLE)
        db?.execSQL(CREATE_JOURNAL_TABLE)
        db?.execSQL(CREATE_DOCTOR_TABLE)
        db?.execSQL(CREATE_TRANSACTION_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_ACCOUNT_TABLE)
        db?.execSQL(DROP_NEWS_TABLE)
        db?.execSQL(DROP_JOURNAL_TABLE)
        db?.execSQL(DROP_DOCTOR_TABLE)
        db?.execSQL(DROP_TRANSACTION_TABLE)
        onCreate(db)
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

    //DATA DOKTER
    data class Doctor(val id: Int, val name: String, val description: String, val harga:Int)

    val doctorData = listOf(
        Doctor(1, "Anna Freud", "Msc in Arkham Asylum", 4000),
        Doctor(2, "John Doe", "PhD in Psychiatry", 7000),
        Doctor(3, "Jane Smith", "MD in Neurology", 9000),
        Doctor(4, "Jamal Adidi", "PhD in Psychiatry", 7000),
    )

    fun insertDoctorData() {
        for ((id, name, description, harga) in doctorData) {
            val values = ContentValues().apply {
                put(COLUMN_ID_DOCTOR, id)
                put(COLUMN_NAME_DOCTOR, name)
                put(COLUMN_DESC_DOCTOR, description)
                put(COLUMN_HARGA_SESSION, harga)
            }

            writableDatabase.insert(TABLE_DOCTOR, null, values)
        }
    }

    @SuppressLint("Range")
    fun getDoctors(): List<Doctor> {
        val doctorList = mutableListOf<Doctor>()
        val query = "SELECT * FROM $TABLE_DOCTOR"

        val db = readableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_DOCTOR))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DOCTOR))
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESC_DOCTOR))
                val harga = cursor.getInt(cursor.getColumnIndex(COLUMN_HARGA_SESSION))

                val doctor = Doctor(id, name, description, harga)
                doctorList.add(doctor)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return doctorList
    }

    fun addTransaction(doctorId: Int) {
        val dbInsert = this.writableDatabase
        val dbSelect = this.readableDatabase

        // Declare variables
        var lastIdTrans = 0
        var newIdTrans = 0
        val values = ContentValues()

        val cursorTrans: Cursor = dbSelect.rawQuery(
            "SELECT * FROM $TABLE_TRANSACTION", null
        )

        if (cursorTrans.moveToLast()) {
            lastIdTrans = cursorTrans.getInt(0) // To get id, 0 is the column index
        }

        newIdTrans = lastIdTrans + 1
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val tanggal = sdf.format(Date())

            // Insert data transaksi
        values.put(COLUMN_ID_TRANSACTION, newIdTrans)
        values.put(COLUMN_TANGGAL, tanggal)
        values.put(COLUMN_ID_DOCT, doctorId)

        val result = dbInsert.insert(TABLE_TRANSACTION, null, values)

        // Show message
        if (result == (0).toLong()) {
            Toast.makeText(context, "Add transaction Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Add transaction Success", Toast.LENGTH_SHORT).show()
        }

        // Close the cursor and database
        cursorTrans.close()
        dbInsert.close()
        dbSelect.close()
    }



}