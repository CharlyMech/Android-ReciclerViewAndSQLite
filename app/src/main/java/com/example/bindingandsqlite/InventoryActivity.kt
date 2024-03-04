package com.example.bindingandsqlite

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bindingandsqlite.DBHelper.Companion.ID
import com.example.bindingandsqlite.DBHelper.Companion.KB
import com.example.bindingandsqlite.DBHelper.Companion.PRICE
import com.example.bindingandsqlite.KeyboardProvider.Companion.keyboards
import com.example.bindingandsqlite.adapter.KeyboardAdapter

class InventoryActivity : AppCompatActivity() {
	// Class variables
	private lateinit var toolbar: Toolbar
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_inventory)
		// Load all content from DataBase and render all Recycler
		loadData()
		initRecyclerView()

		// Get all elements from activity layout file
		this.toolbar = findViewById(R.id.toolbar)

		// Set ActionBar properties
		setSupportActionBar(this.toolbar)
		supportActionBar!!.setDisplayHomeAsUpEnabled(true)
	}

	@SuppressLint("Range") //??
	private fun loadData() {
		val db = DBHelper(this, null)
		val cursor = db.getKBs()

		cursor.use {
			while (it!!.moveToNext()) {
				val id = it.getInt(it!!.getColumnIndex(ID))
				val kb = it.getString(it!!.getColumnIndex(KB))
				val price = it.getFloat(it!!.getColumnIndex(PRICE))
				// Retrieve other columns as needed
				// Example: val columnName = it.getString(it.getColumnIndex("columnName"))

				// Create a data model object and add it to the list
				val item: Keyboard = Keyboard(id, kb, price)
				keyboards.add(item)
			}
		}
	}

	private fun initRecyclerView() {
		val recyclerView = findViewById<RecyclerView>(R.id.recycler)
		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.adapter = KeyboardAdapter(KeyboardProvider.keyboards)
	}
}