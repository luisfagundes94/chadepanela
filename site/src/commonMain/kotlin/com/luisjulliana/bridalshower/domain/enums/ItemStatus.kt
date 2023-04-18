package com.luisjulliana.bridalshower.domain.enums

enum class ItemStatus(val status: String) {
    AVAILABLE("Disponível"),
    TAKEN("Indisponível"),
    ALL("Todos");

    companion object {
        fun getFromName(name: String) = values().first { it.status == name }
    }
}