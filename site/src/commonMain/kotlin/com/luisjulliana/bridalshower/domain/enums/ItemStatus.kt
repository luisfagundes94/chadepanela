package com.luisjulliana.bridalshower.domain.enums

enum class ItemStatus(val status: String) {
    AVAILABLE("Disponível"),
    TAKEN("Indisponível");

    companion object {
        fun fromName(name: String): ItemStatus {
            return values().firstOrNull { itemStatus ->
                itemStatus.status == name
            } ?: AVAILABLE
        }
    }
}