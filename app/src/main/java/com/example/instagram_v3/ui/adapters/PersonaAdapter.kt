// PersonaAdapter.kt
package com.example.instagram_v3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram_v3.databinding.ItemPersonaBinding
import com.example.instagram_v3.models.Persona

class PersonaAdapter : ListAdapter<Persona, PersonaAdapter.PersonaViewHolder>(PersonaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding = ItemPersonaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = getItem(position)
        holder.bind(persona)
    }

    inner class PersonaViewHolder(private val binding: ItemPersonaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(persona: Persona) {
            binding.textViewNombre.text = persona.nombre
            // Cargar la primera foto con Glide
            if (persona.fotos.isNotEmpty()) {
                Glide.with(binding.imageView.context)
                    .load(persona.fotos[0]) // cargar la primera foto
                    .into(binding.imageView)
            }
        }
    }

    class PersonaDiffCallback : DiffUtil.ItemCallback<Persona>() {
        override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem == newItem
        }
    }
}
