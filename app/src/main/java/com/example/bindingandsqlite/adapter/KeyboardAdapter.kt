package com.example.bindingandsqlite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bindingandsqlite.Keyboard
import com.example.bindingandsqlite.R

class KeyboardAdapter(private val keyboardList: MutableList<Keyboard>) : RecyclerView.Adapter<KeyboardViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyboardViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		return KeyboardViewHolder(layoutInflater.inflate(R.layout.keyboard_card, parent, false))
	}

	override fun getItemCount(): Int = keyboardList.size // Return the list size

	override fun onBindViewHolder(holder: KeyboardViewHolder, position: Int) {
		val item = keyboardList[position]
		holder.render(item)
	}

	fun removeItem(index: Int) {
		keyboardList.removeAt(index)
		notifyItemRemoved(index)
	}
}