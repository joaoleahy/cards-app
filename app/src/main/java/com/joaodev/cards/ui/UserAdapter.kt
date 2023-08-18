package com.joaodev.cards.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
        private val userContainer: LinearLayout = itemView.findViewById(R.id.userContainer)

        fun bind(users: List<User>) {
            userContainer.removeAllViews()

            for (user in users) {
                val userCard = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.item_user_card, userContainer, false)

                val nameTextView: TextView = userCard.findViewById(R.id.nameTextView)
                val ageTextView: TextView = userCard.findViewById(R.id.ageTextView)
                val emailTextView: TextView = userCard.findViewById(R.id.emailTextView)

                nameTextView.text = user.name
                ageTextView.text = user.age.toString()
                emailTextView.text = user.email

                userContainer.addView(userCard)
            }
        }
    }
}


