package com.example.whoisthat.service

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.whoisthat.R
import com.example.whoisthat.databinding.ItemCharacterBinding

interface CharacterActionListener{
    fun changeChosen(view: View,imageView:ImageView)
}

class CharacterAdapter(private val characterActionListener: CharacterActionListener)
    : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){


    var characters:List<com.example.whoisthat.model.Character> = emptyList()
        set(newValue) {
            field=newValue
            notifyDataSetChanged()
        }

    class CharacterViewHolder(
        val binding:ItemCharacterBinding
    ):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemCharacterBinding.inflate(inflater,parent,false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.tag=characters[position]
        holder.binding.imageView.setImageResource(characters[position].resource)
        holder.itemView.setOnClickListener{characterActionListener.changeChosen(holder.itemView,holder.binding.imageView)}
        //holder.binding.imageView.setOnClickListener { onClick(holder.itemView) }
    }

//    override fun onClick(v: View) {
//        val character=v.tag as com.example.whoisthat.model.Character
//        characterActionListener.changeChosen(character)
//    }
}