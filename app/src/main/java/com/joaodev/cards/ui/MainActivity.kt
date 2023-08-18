package com.joaodev.cards.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joaodev.cards.R
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
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetchUsers()
    }

    private fun fetchUsers() {
        val call = ApiClient.apiService.getUsers()
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val users = response.body()?.users ?: emptyList()

                    val userGroups = users.chunked(10)

                    userAdapter = UserAdapter(userGroups)
                    recyclerView.adapter = userAdapter
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }
        })
    }
}
