package com.joaodev.cards.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joaodev.cards.R
import com.joaodev.cards.data.models.User
import com.joaodev.cards.data.services.UserResponse
import com.joaodev.cards.data.services.clients.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 5)

        fetchUsers()
    }

    private fun fetchUsers() {
        val call: Call<UserResponse> = ApiClient.apiService.getUsers()
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val users: List<User> = response.body()?.users ?: emptyList()

                    val orderedUsers = mutableListOf<User>()

                    for (i in 1..10) {
                        for (j in 0 until 5) {
                            val position = j * 10 + i - 1
                            if (position < users.size) {
                                orderedUsers.add(users[position])
                            }
                        }
                    }

                    userAdapter = UserAdapter(orderedUsers)
                    recyclerView.adapter = userAdapter
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }
        })
    }
}