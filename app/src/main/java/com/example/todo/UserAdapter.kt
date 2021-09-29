package com.example.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val context: Context,
    private var userList: ArrayList<User>
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(
        itemView: View,
        userList: ArrayList<User>,
    ) : RecyclerView.ViewHolder(itemView) {


        var name: TextView = itemView.findViewById(R.id.name)
        var age: TextView = itemView.findViewById(R.id.age)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = (LayoutInflater.from(context)
            .inflate(R.layout.each_row, parent, false))
        return UserViewHolder(view, userList)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = userList[position]
        holder.name.text = user.name
        holder.age.text = user.age.toString()
    }

    fun setData(userList: ArrayList<User>) {
        this.userList = userList
        notifyDataSetChanged()

    }
}


