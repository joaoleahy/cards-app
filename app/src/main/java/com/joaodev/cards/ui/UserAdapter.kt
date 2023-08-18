package com.joaodev.cards.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joaodev.cards.R
import com.joaodev.cards.data.models.User

class UserAdapter(private val userGroups: List<List<User>>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_card, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val usersInGroup = userGroups[position]
        holder.bind(usersInGroup)
    }

    override fun getItemCount(): Int {
        return userGroups.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val ageTextView: TextView = itemView.findViewById(R.id.ageTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)

        fun bind(users: List<User>) {
            for (i in 0 until users.size) {
                val user = users[i]
                nameTextView.text = user.name
                ageTextView.text = user.age.toString()
                emailTextView.text = user.email
            }
        }
    }
}
