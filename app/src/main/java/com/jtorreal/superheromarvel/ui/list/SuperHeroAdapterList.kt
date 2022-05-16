package com.jtorreal.superheromarvel.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.jtorreal.superheromarvel.databinding.ViewRowSuperheroBinding
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.ui.common.loadUrl

class SuperHeroAdapterList(
    private var items: ArrayList<SuperHeroDomain>,
    private val listener: (SuperHeroDomain) -> Unit
) :
    RecyclerView.Adapter<SuperHeroAdapterList.ViewHolder>() {

    private lateinit var itemBinding: ViewRowSuperheroBinding

    private lateinit var superHeroListAll: ArrayList<SuperHeroDomain>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemBinding =
            ViewRowSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        superHeroListAll = ArrayList(items)

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

    fun applyFilter(): Filter {
        return resultFilter
    }

    private val resultFilter = object : Filter() {

        override fun performFiltering(charsequence: CharSequence?): FilterResults {

            var filteredSuperHeroList = ArrayList<SuperHeroDomain>()

            if (charsequence.isNullOrEmpty()) {

                filteredSuperHeroList.addAll(superHeroListAll)

            } else {

                for (item: SuperHeroDomain in superHeroListAll) {

                    if (item.name?.contains(charsequence) == true) {
                        filteredSuperHeroList.add(item)
                    }
                }
            }

            val filterResults = FilterResults()

            filterResults.values = filteredSuperHeroList

            return filterResults

        }

        override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
            items.clear()
            items.addAll((filterResults?.values as Collection<SuperHeroDomain>))
            notifyDataSetChanged()
        }

    }

}
