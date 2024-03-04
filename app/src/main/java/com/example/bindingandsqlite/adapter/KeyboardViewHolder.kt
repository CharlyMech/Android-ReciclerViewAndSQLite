package com.example.bindingandsqlite.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bindingandsqlite.Keyboard
import com.example.bindingandsqlite.R

class KeyboardViewHolder(view: View) : ViewHolder(view) {
	// Class variables
	val id = view.findViewById<TextView>(R.id.id)
	val kb = view.findViewById<TextView>(R.id.kb)
	val price = view.findViewById<TextView>(R.id.price)
	val kb_img = view.findViewById<ImageView>(R.id.kb_img)
	val delete = view.findViewById<ImageButton>(R.id.delete)
	val edit = view.findViewById<ImageButton>(R.id.edit)
	fun render(keyboard: Keyboard) {
		id.text = keyboard.id.toString()
		kb.text = keyboard.kb
		price.text = keyboard.price.toString()
		kb_img.setImageResource(R.drawable.switch_icon) // Just to display an image
		delete.setOnClickListener {
			Toast.makeText(delete.context, "DELETE", Toast.LENGTH_SHORT).show()
		}
		edit.setOnClickListener {
			Toast.makeText(edit.context, "EDIT", Toast.LENGTH_SHORT).show()
		}
	}
}