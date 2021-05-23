package api

import api.entity.ResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/{email}/directions")
    fun getDirections(
        @Path("email") email: String
    ): Call<ResponseEntity>

    @GET("/api/{email}/location/{x}/{y}")
    fun checkLocation(
        @Path("email") email: String,
        @Path("x") x: Int,
        @Path("y") y: Int
    ): Call<ResponseEntity>
}