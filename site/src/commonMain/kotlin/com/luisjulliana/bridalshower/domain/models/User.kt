package com.luisjulliana.bridalshower.domain.models

import kotlinx.serialization.Serializable
import kotlin.js.JsName

@Serializable
data class User(
    @JsName("name") val name: String,
    @JsName("email") val email: String
)
