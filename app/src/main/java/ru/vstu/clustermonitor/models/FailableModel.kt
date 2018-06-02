package ru.vstu.clustermonitor.models

/**
 * Container for some data with error status
 */
class FailableModel<T>  {

    val error: String?
    val data: T?
    val isOk : Boolean
        get() = data != null

    constructor(data: T) {
        this.data = data
        error = null
    }

    constructor(error: String){
        this.error = error
        data = null
    }
}
