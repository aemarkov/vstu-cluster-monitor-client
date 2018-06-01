package ru.vstu.clustermonitor.Models

/**
 * Represents task in queue
 */
class QueueTask {
    public var JobId: Int? = null
    public var Name: String? = null
    public var State: String? = null
    public var ElapsedTime: Int? = null
    public var TimeLeft: Int? = null
    public var User: String? = null
    public var Nodes: List<String>? = null

    public constructor(name: String) {
        Name = name
    }
}