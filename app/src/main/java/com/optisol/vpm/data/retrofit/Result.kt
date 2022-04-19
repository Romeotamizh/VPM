package com.optisol.vpm.data.retrofit

data class Result<out T>(val status: Status, val data :T?, val message : String?) {
    companion object{
        fun <T> success(data: T) =
            Result(Status.SUCCESS,data, null)
        fun <T> failure(data: T,message: String?) =
            Result(Status.FAILURE,data, message)
        fun <T> loading(data: T) =
            Result(Status.LOADING,data, null)
    }
}
enum class Status {
    SUCCESS,
    FAILURE,
    LOADING
}
