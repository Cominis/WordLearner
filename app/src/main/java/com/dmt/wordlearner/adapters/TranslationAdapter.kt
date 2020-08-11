package com.dmt.wordlearner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmt.wordlearner.R
import com.dmt.wordlearner.databinding.WordListItemBinding
import com.dmt.wordlearner.domain.Translation
import com.dmt.wordlearner.utils.ItemClickListener


class TranslationAdapter(private val itemClickListener: ItemClickListener<Long>) :
    ListAdapter<Translation, RecyclerView.ViewHolder>(TranslationDiffCallback()), Filterable {

    lateinit var itemsAll : List<Translation>
    private val suggestions = mutableListOf<Translation>()

    fun submit(list: List<Translation>){
        itemsAll = list.toList()
        submitList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TranslationViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val translation = getItem(position)
        when(holder){
            is TranslationViewHolder -> holder.bind(translation, itemClickListener)
        }
    }

    class TranslationViewHolder(private val binding: WordListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            translation: Translation,
            itemClickListener: ItemClickListener<Long>
        ){
            binding.translation = translation
            binding.clickListener = itemClickListener
        }

        companion object {
            fun from(parent: ViewGroup) : TranslationViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: WordListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.word_list_item, parent, false)
                return TranslationViewHolder(binding)
            }
        }
    }

    override fun getFilter(): Filter {
        return filter
    }

    private var filter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            if (constraint == null || constraint.isEmpty()) {
                filterResults.values = itemsAll
                filterResults.count = itemsAll.size
            } else {
                suggestions.clear()
                for (translation in itemsAll) {
                    if(translation.word.contains(constraint, true)){
                        suggestions.add(translation)
                    }
                }
                filterResults.values = suggestions
                filterResults.count = suggestions.size
            }
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if(results != null && results.count != 0) {
                val list = results.values as List<Translation>
                submitList(list)
                notifyDataSetChanged()
            }
        }
    }
}

class TranslationDiffCallback : DiffUtil.ItemCallback<Translation>() {

    override fun areItemsTheSame(oldItem: Translation, newItem: Translation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Translation, newItem: Translation): Boolean {
        return oldItem == newItem
    }

}