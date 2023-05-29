package com.dicoding.noideaapp.di

import com.dicoding.noideaapp.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}