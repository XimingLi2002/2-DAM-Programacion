import java.util.*

fun main(){
    do {
        var island = occidentalIslands()
        if (island != "No quedan plazas") {
            println("Me voy de vacaciones a: $island")
        }else{
            println(island)
        }
    }while (island != "La Graciosa")
}

fun occidentalIslands(): String {
    val islands = listOf("El Hierro", "La Gomera", "La Palma", "Tenerife", "Gran Canaria", "Fuerteventura", "Lanzarote", "La Graciosa")
    return when (val number = Random().nextInt(8)) {
        in 0..3 -> "No quedan plazas"
        else -> islands[number]
    }
}