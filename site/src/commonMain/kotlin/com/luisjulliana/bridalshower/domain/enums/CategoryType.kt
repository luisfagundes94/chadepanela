package com.luisjulliana.bridalshower.domain.enums
enum class CategoryType(val type: String) {
    BEDROOM("Quarto"),
    LIVING_ROOM("Sala"),
    KITCHEN("Cozinha"),
    BATHROOM("Banheiro"),
    OUTDOOR("Externo"),
    PIX("PIX"),
    OTHER("Outros");
    companion object {
        fun fromName(name: String): CategoryType {
            return values().firstOrNull { roomType ->
                roomType.type == name
            } ?: OTHER
        }
    }
}