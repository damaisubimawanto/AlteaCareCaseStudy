package com.damai.core

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
abstract class BaseMapper<in T, out R> {
    abstract fun map(value: T): R
}