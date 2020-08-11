package com.dmt.wordlearner.storage

interface IStorage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}