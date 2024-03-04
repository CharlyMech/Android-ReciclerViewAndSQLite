package com.example.bindingandsqlite.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bindingandsqlite.DBHelper
import com.example.bindingandsqlite.Keyboard
import com.example.bindingandsqlite.KeyboardProvider.Companion.keyboards
import com.example.bindingandsqlite.R
import com.example.bindingandsqlite.UpdateActivity

class KeyboardViewHolder(view: View) : ViewHolder(view) {
	// Class variables
	val id = view.findViewById<TextView>(R.id.id)
	val kb = view.findViewById<TextView>(R.id.kb)
	val price = view.findViewById<TextView>(R.id.price)
	val kb_img = view.findViewById<ImageView>(R.id.kb_img)
	val delete = view.findViewById<ImageButton>(R.id.delete)
	val edit = view.findViewById<ImageButton>(R.id.edit)
	val adapter:KeyboardAdapter = KeyboardAdapter(keyboards)
	fun render(keyboard: Keyboard) {
		id.text = keyboard.id.toString()
		kb.text = keyboard.kb
		price.text = keyboard.price.toString()
		kb_img.setImageResource(R.drawable.switch_icon) // Just to display an image
		delete.setOnClickListener {
			val alertDialog = AlertDialog.Builder(delete.context)
			alertDialog.setTitle("Remove Keyboard from Inventory")
			alertDialog.setMessage("Are you sure you want to delete this keyboard from the inventory?")
			alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
				// Remove Keyboard from DataBase
				val db = DBHelper(delete.context, null)
				db.removeKB(keyboard.id)
				// Remove Keyboard from companion object
				keyboards.remove(keyboard)
				val updatedInventory = keyboards
				adapter.run {
					setData(updatedInventory)
					notifyDataSetChanged(); // notify changed
				}
				
				Toast.makeText(delete.context,
					"Keyboard removed!", Toast.LENGTH_SHORT).show()

			}
			alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
			}
			alertDialog.show()
		}
		edit.setOnClickListener {
			val intent = Intent(edit.context, UpdateActivity::class.java)
			// Pass data to UpdateActivity
			intent.putExtra("ID", keyboard.id)
			intent.putExtra("KB", keyboard.kb)
			intent.putExtra("PRICE", keyboard.price)

			edit.context.startActivity(intent)
		}
	}

	private fun setData(updatedInventory: MutableList<Keyboard>) {
		keyboards.run {
			clear()
			addAll(updatedInventory)
		}
	}
}