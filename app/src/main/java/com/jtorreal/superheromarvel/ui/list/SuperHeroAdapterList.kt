package com.jtorreal.superheromarvel.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jtorreal.superheromarvel.databinding.ViewRowSuperheroBinding
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.ui.common.loadUrl

class SuperHeroAdapterList(
    private var items: List<SuperHeroDomain>,
    private val listener: (SuperHeroDomain) -> Unit
) :
    RecyclerView.Adapter<SuperHeroAdapterList.ViewHolder>() {

    private lateinit var itemBinding: ViewRowSuperheroBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemBinding =
            ViewRowSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val itemBinding: ViewRowSuperheroBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: SuperHeroDomain, listener: (SuperHeroDomain) -> Unit) = with(itemView) {

            itemBinding.txtTitleCharacter.text = item.name

            itemBinding.txtDescriptionCharacter.text = item.description

            itemBinding.imgBgCharacter.loadUrl(item.thumbnail?.path + "." + item.thumbnail?.extension)

            itemBinding.cvMain.setOnClickListener {
                listener(item)
            }
        }
    }
}
