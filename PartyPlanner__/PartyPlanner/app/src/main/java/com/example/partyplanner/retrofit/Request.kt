package com.example.partyplanner.retrofit
import com.example.partyplanner.model.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



interface Request {
    @POST("signin")
    suspend fun Login2(@Body User: User): Response<ResponseBody>

    @POST("signup")
    suspend fun Signup(@Body requestBody: RequestBody): Response<ResponseBody>
}