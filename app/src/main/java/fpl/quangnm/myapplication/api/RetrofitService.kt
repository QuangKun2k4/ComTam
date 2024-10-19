package fpl.quangnm.myapplication.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface RetrofitService {

    @POST("/users/reg")
    fun registerUser(@Body user: User): Call<User>


    @POST("/users/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>


    @GET("/users/{id}")
    fun getUser(@Path("id") userId: String): Call<User>

    @GET("/products")
    fun getProducts(): Call<List<Product>>

}