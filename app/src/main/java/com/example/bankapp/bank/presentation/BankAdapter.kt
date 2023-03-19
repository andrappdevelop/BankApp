package com.example.bankapp.bank.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R

class BankAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<BankViewHolder>(), Mapper.Unit<List<BankUi>> {

    private val list = mutableListOf<BankUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BankViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.bin_item, parent, false),
        clickListener
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun map(source: List<BankUi>) {
        val diff = DiffUtilCallback(list, source)
        val result = DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }
}

class BankViewHolder(
    view: View,
    private val clickListener: ClickListener
) : RecyclerView.ViewHolder(view) {

    private val title = itemView.findViewById<TextView>(R.id.binItemNumberTextView)
    private val subtitle = itemView.findViewById<TextView>(R.id.binItemInfoTextView)
    private val mapper = ListItemUi(title, subtitle)

    fun bind(model: BankUi) {
        model.map(mapper)
        itemView.setOnClickListener { clickListener.click(model) }
    }

}

interface ClickListener {
    fun click(item: BankUi)
}

class DiffUtilCallback(
    private val oldList: List<BankUi>,
    private val newList: List<BankUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].map(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}