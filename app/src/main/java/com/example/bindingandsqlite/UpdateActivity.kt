package com.example.bindingandsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.example.bindingandsqlite.KeyboardProvider.Companion.keyboards
import kotlinx.coroutines.processNextEventInCurrentThread

class UpdateActivity : AppCompatActivity() {
	// Class variables
	private lateinit var toolbar:Toolbar
	private lateinit var kb:EditText
	private lateinit var price:EditText
	private lateinit var updateKBBtn:Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_keyboard)

		// Get data from intent
		val kbId: Int = intent.getIntExtra("ID", 0)
		val kbName: String = intent.getStringExtra("KB").toString()
		val kbPrice: Float = intent.getFloatExtra("PRICE", 0.0F)

		// Clear the keyboards companion to get resources free
		keyboards.clear()

		// Get all elements from activity layout file
		this.toolbar = findViewById(R.id.toolbar)
		this.kb = findViewById(R.id.kb)
		this.price = findViewById(R.id.price)
		this.updateKBBtn = findViewById(R.id.updateKBBtn)

		// Set ActionBar properties
		setSupportActionBar(this.toolbar)
		supportActionBar!!.setDisplayHomeAsUpEnabled(true)

		// Add listeners to the buttons
		this.updateKBBtn.setOnClickListener{
			// Get both fields text
			val kbText:String = this.kb.text.toString()
			val priceText:String = this.price.text.toString()

			// Updated information
			val updatedKb:String
			if (kbText.isEmpty())
				updatedKb = kbName
			else
				updatedKb = kbText
			val updatedPrice:Float
			if (priceText.isEmpty())
				updatedPrice = kbPrice
			else
				updatedPrice = priceText.toFloat()

			val db = DBHelper(this, null)
			db.updateKB(kbId, updatedKb, updatedPrice)

			finish()
		}
	}
}