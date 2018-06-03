package ru.vstu.clustermonitor.data.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.vstu.clustermonitor.MonitorApplication
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.models.*

/**
 * Retrofit2 service description
 */
interface IMonitorApi {

    /**
     * JWT auth request
     * @param credentials Username and password
     * @return response with JWT token
     */
    @POST("auth")
    fun auth(@Body credentials: AuthRequest) : Call<AuthResponse>

    /**
     * Queue task list request
     */
    @GET("queue")
    fun queue(): Call<List<QueueTask>>

    /**
     * Sensors list request
     */
    @GET("sensors")
    fun sensors(): Call<List<Sensor>>
}