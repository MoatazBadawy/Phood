package com.moataz.phood.identity.domain.entities

data class User(
    val id: Long,
    val email: String,
    val password: String,
)
