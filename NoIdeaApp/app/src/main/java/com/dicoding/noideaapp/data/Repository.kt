package com.dicoding.noideaapp.data

import com.dicoding.noideaapp.model.PowerRangers
import com.dicoding.noideaapp.model.PowerRangersData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val powerRangers = mutableListOf<PowerRangers>()

    init {
        if(powerRangers.isEmpty()) {
            PowerRangersData.rangers.forEach {
                powerRangers.add(it)
            }
        }
    }

    fun getAllRangers(): Flow<List<PowerRangers>> {
        return flowOf(powerRangers)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}