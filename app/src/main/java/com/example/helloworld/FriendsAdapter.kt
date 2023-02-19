package com.example.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.databinding.ListItemFriendBinding

class FriendsAdapter(private val items : ArrayList<Friend>, private val context: Context) : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val binding = ListItemFriendBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        with(holder){
            with(items[position]) {
                binding.txtFriendName.text = name
                holder.itemView.setOnClickListener {
                    Toast.makeText(holder.itemView.context, "$phone\n$dob\n$hobby",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    inner class FriendsViewHolder(val binding: ListItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root)

}