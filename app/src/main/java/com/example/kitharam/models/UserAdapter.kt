package com.example.kitharam.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kitharam.R
import com.example.kitharam.models.User

class UserAdapter(private val userList: List<User>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtUsername)
        val txtDescription: TextView = itemView.findViewById(R.id.txtEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.txtTitle.text = user.username
        holder.txtDescription.text = user.email

        holder.itemView.setOnClickListener {
            onItemClick(user.username) // Call the function to navigate
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
