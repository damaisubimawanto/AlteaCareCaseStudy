package com.damai.core

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
object ErrorCodesMapper {
    fun getMessage(errorCode: Int) = when (errorCode) {
        NetworkCodes.CONNECTION_ERROR,
        NetworkCodes.TIMEOUT_ERROR -> "Failed to connect to server please check you network!"
        else -> "Something went wrong please try again!"
    }
}