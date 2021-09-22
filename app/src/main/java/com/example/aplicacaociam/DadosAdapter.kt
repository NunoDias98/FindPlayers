package com.example.aplicacaociam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*

class DadosAdapter(
    private val exampleList: List<ExampleItem>
): RecyclerView.Adapter<DadosAdapter.DadosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DadosViewHolder {
        val view = LayoutInflater
            .from(parent .context)
            .inflate(R.layout.card, parent, false)
        return DadosViewHolder(view)

    }

    //override fun getItemCount(): Int = dados.size
    override fun getItemCount(): Int = exampleList.size

    override fun onBindViewHolder(holder: DadosAdapter.DadosViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description /*
        holder.title.text = dados.get(position).toString()
        holder.description.text = dados.get(position).toString()*/
    }


    class DadosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.tvTitle)
        val description : TextView = itemView.findViewById(R.id.tvDescription)
    }


}