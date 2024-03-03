package com.example.bindingandsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class InventoryActivity : AppCompatActivity() {
	// Class variables
	private lateinit var toolbar: Toolbar
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_inventory)

		// Get all elements from activity layout file
		this.toolbar = findViewById(R.id.toolbar)

		// Set ActionBar properties
		setSupportActionBar(this.toolbar)
		supportActionBar!!.setDisplayHomeAsUpEnabled(true)
	}
}