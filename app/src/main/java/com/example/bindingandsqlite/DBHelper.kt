package com.example.bindingandsqlite

import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
	SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
	// Create SQLite DataBase method
	override fun onCreate(db: SQLiteDatabase) {
		// Query string for table creation if not exists
		val query:String = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KB + " TEXT, " +
				PRICE + " REAL)")
		// Execute query
		db.execSQL(query)
	}

	// Check if the Table already exists method
	override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME) // Execute DROP operation if exists
		onCreate(db)
	}

	// Add new Keyboard data to DataBase method
	fun addKB(kb: String, price:Float) {
		val values = ContentValues()
		values.put(KB, kb) // Set Values
		values.put(PRICE, price)
		// Writable variable to insert data into the DataBase
		val db = this.writableDatabase
		// Execute insert operation
		db.insert(TABLE_NAME, null, values)
		// Close DataBase writable variable
		db.close()
	}

	// Get all stored Keyboards method
	fun getKBs(): Cursor? {
		// Readable variable to read data from the DataBase
		val db = this.readableDatabase
		// Return a Cursor with all data from DataBase
		return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
	}

	// Get count of inserted data method
	fun getKBCount(): Int {
		// Readable variable
		val db = this.readableDatabase
		val cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null)
		cursor.moveToFirst() // Move to first (and only) returned value from query
		val count = cursor.getInt(0) // Get Integer value
		cursor.close() // Close readable variable
		return count
	}

	// DataBase variable definition -> This class is meant to store all needed elements to retrieve the DataBase information and connect to it
	companion object {
		private val DATABASE_NAME = "KEYBOARDS" // DB Name
		private val DATABASE_VERSION = 1 // DB Version
		val TABLE_NAME = "keyboards_inventory" // Table name
		val ID = "id" // Table's attribute
		val KB = "name" // Table's attribute
		val PRICE = "price" // Table's attribute
	}
}