package com.eka.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eka.listadapter.databinding.ItemBinding

class ItemListAdapter(private val vm: MainViewModel) : ListAdapter<Item, ItemListAdapter.ViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item = item
        holder.itemView.setOnClickListener {
            vm.onItemClick(item, position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemBinding = DataBindingUtil.bind(view)!!
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem // uuid 등 아이템 식별용 e.g oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem // 원래 내용까지 같은지 확인 -> equalsTo
            }
        }
    }
}