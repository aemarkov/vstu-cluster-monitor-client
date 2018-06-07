package ru.vstu.clustermonitor.models
import java.util.*

data class AuthRequest(
    var username: String,
    var password: String
)

data class AuthResponse
(
    var access_token: String
)

/**
 * Task in queue
 */
data class QueueTask
(
        var job_id: Int,
        var elapsed_time: Int,
        var time_left: Int,
        var partition: String,
        var name: String,
        var user: String,
        var state: String,
        var nodes: List<String>
)

/**
 * Sensor data
 * This structure unites data from three types of sensors:
 *  - smoke (temperature, humidity, smoke)
 *  - move (temperature, humidity, move)
 *  - supply (supply, time_left)
 */
data class Sensor
(
    var type: String,
    var last_update: Date,
    var temperature: Int?,
    var humidity: Int?,
    var smoke: Boolean?,
    var move: Boolean?,
    var supply: Int?,
    var time_left: Int?
)