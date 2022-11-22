import java.util.*

fun main(){
    println("La isla seleccionada aleatoriamente es: ${island()}")
}

private fun island(): String{
    val island = listOf("El Hierro", "La Gomera", "La Palma", "Tenerife", "Gran Canaria", "Fuerteventura", "Lanzarote", "La Graciosa")
    return (island[Random().nextInt(8)])
}