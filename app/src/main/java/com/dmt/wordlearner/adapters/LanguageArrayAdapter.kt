package com.dmt.wordlearner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.dmt.wordlearner.R
import com.dmt.wordlearner.db.tables.Language
import kotlinx.android.synthetic.main.language_list_item.view.*


class LanguageArrayAdapter(private val mContext: Context, items: MutableList<Language>)
    : ArrayAdapter<Language>(mContext, 0, items) {

    private var itemsAll = items.toList()
    private val suggestions = mutableListOf<Language>()

    fun updatedData(newItems: List<Language>) {
        itemsAll = newItems.toList()
        suggestions.clear()
        clear()
        addAll(newItems)
        notifyDataSetChanged()
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {

        val language = getItem(position)!!
        val view = recycledView ?: LayoutInflater.from(mContext).inflate(
            R.layout.language_list_item,
            parent,
            false
        )

        view.language_text.text = language.languageCode
        return view
    }

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)!!.id
    }

    override fun getFilter(): Filter {
        return nameFilter
    }

    private var nameFilter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            if (constraint.isNullOrBlank()) {
                filterResults.values = itemsAll
                filterResults.count = itemsAll.size
            } else {
                suggestions.clear()
                for (language in itemsAll) {
                    if(language.languageCode.contains(constraint, true)){
                        suggestions.add(language)
                    }
                }
                filterResults.values = suggestions
                filterResults.count = suggestions.size
            }
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if(results != null && results.count != 0) {
                clear()
                addAll(results.values as MutableList<Language>)
                notifyDataSetChanged()
            }
        }
    }
}