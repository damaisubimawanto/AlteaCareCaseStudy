package com.damai.core

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
sealed class Resource<out T> {
    data class Success<T>(val model: T? = null, val source: DataSource) : Resource<T>()
    data class Error(val errorData: ErrorData) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}
