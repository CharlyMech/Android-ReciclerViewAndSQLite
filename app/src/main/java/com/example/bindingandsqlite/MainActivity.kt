package com.example.bindingandsqlite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
	// Class variables
	private lateinit var toolbar:Toolbar
	private lateinit var kb:EditText
	private lateinit var price:EditText
	private lateinit var addKBBtn:Button
	private lateinit var showInventoryBtn:Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// Get all elements from activity layout file
		this.toolbar = findViewById(R.id.toolbar)
		this.kb = findViewById(R.id.kb)
		this.price = findViewById(R.id.price)
		this.addKBBtn = findViewById(R.id.addKBBtn)
		this.showInventoryBtn = findViewById(R.id.showInventoryBtn)

		// Set ActionBar properties
		setSupportActionBar(this.toolbar)

		// Add listeners to the buttons
		this.addKBBtn.setOnClickListener{
			// Get both fields text
			val kbText:String = this.kb.text.toString()
			val priceText:String = this.price.text.toString()
			print(kbText)
			print(priceText)
			// Check if anyone of those is empty
			if(kbText.isEmpty() || priceText.isEmpty()) {
				this.makeToast("Please fill all inputs to add new keyboard")
			} else {
				this.addNewKeyboard(kbText, priceText.toFloat())
				this.makeToast("ADDED")
			}
		}
		this.showInventoryBtn.setOnClickListener{
			// Get the current number of stored values
			val db = DBHelper(this, null)
			val count: Int = db.getKBCount()
			// Check if the returning count is different from 0
			if (count != 0) {
				this.showInventory()
			} else {
				this.makeToast("There are not stored values\r\nPlease first insert a value")
			}
		}
	}

	private fun addNewKeyboard(name:String, price:Float) {
	}

	private fun showInventory() {
		val inventory:Intent = Intent(this, InventoryActivity::class.java)
		startActivity(inventory)
	}

	fun makeToast(msg:String) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
	}
}