package com.mosaleh.sparkbanking.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.mosaleh.sparkbanking.R
import com.mosaleh.sparkbanking.model.User

class UserAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.AccountNumber == newItem.AccountNumber
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<User>) {
        differ.submitList(list)
    }

    class UserViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
        private val userName = itemView.findViewById<TextView>(R.id.user_name_item)
        private val userBalance = itemView.findViewById<TextView>(R.id.balance)
        fun bind(item: User): Any = with(itemView) {
            userName.text = item.userName
            userBalance.text = item.balance.toString()
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: User)
    }
}