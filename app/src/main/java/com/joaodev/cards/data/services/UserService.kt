package com.joaodev.cards.data.services

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("v3/ce47ee53-6531-4821-a6f6-71a188eaaee0")
    fun getUsers(): Call<UserResponse>
}
