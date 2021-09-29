package com.example.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KFunction1

class UserAdapter(
    private val context: Context,
    private var userList: ArrayList<User>,
    private val onItemLongClick: (User?) -> Unit,
    private val onItemClick: (User?) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(
        itemView: View,
        userList: ArrayList<User>,
    ) : RecyclerView.ViewHolder(itemView) {


        var name: TextView = itemView.findViewById(R.id.name)
        var age: TextView = itemView.findViewById(R.id.age)
        val cardId = itemView.findViewById<CardView>(R.id.id_card)
        val id =itemView.findViewById<TextView>(R.id.id_id)


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
        holder.id.text = user.id.toString()

        holder.cardId.setOnLongClickListener {
            onItemLongClick(user)
            return@setOnLongClickListener true
        }
        holder.cardId.setOnClickListener {
            onItemClick(user)
        }
    }

    fun setData(userList: ArrayList<User>) {
        this.userList = userList
        notifyDataSetChanged()

    }

    interface RowClickListener{
        fun onItemClickListener(user: User)
    }
}


