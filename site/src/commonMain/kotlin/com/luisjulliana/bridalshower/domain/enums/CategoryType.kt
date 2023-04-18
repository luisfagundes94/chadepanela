package com.luisjulliana.bridalshower.domain.enums
enum class CategoryType(val type: String) {
    LIVING_ROOM("Sala"),
    KITCHEN("Cozinha"),
    BATHROOM("Banheiro"),
    PIX("PIX"),
    ALL("Todos");
    companion object {
        fun fromName(name: String): CategoryType {
            return values().firstOrNull { roomType ->
                roomType.type == name
            } ?: ALL
        }
    }
}