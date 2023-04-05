package com.luisjulliana.bridalshower.domain.enums
enum class RoomType(val type: String) {
    BEDROOM("Quarto"),
    LIVING_ROOM("Sala"),
    KITCHEN("Cozinha"),
    BATHROOM("Banheiro"),
    OUTDOOR("Externo"),
    OTHER("Outros");
    companion object {
        fun fromName(name: String): RoomType {
            return values().firstOrNull { roomType ->
                roomType.type == name
            } ?: OTHER
        }
    }
}