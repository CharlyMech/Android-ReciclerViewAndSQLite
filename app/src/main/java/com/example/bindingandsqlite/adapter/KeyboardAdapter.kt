package com.example.bindingandsqlite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bindingandsqlite.Keyboard
import com.example.bindingandsqlite.R

class KeyboardAdapter(private val keyboards: List<Keyboard>) : RecyclerView.Adapter<KeyboardViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyboardViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		return KeyboardViewHolder(layoutInflater.inflate(R.layout.keyboard_card, parent, false))
	}

	override fun getItemCount(): Int = keyboards.size // Return the list size

	override fun onBindViewHolder(holder: KeyboardViewHolder, position: Int) {
		val item = keyboards[position]
		holder.render(item)
	}
}