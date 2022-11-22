import java.util.*

val islandList = listOf("El Hierro", "La Gomera", "La Palma", "Tenerife", "Gran Canaria", "Fuerteventura", "Lanzarote", "La Graciosa")

fun main() {
    val discountIslandList = getDiscountIslandList()
    val island = getIsland()
    var discount = false
    for (element in discountIslandList) {
        if (island == element) {
            discount = true
            println("Tienes un 10% de descuento, la isla seleccionada es: $island")
        }
    }
    if (!discount) {
        println("La isla seleccionada es: $island")
    }
}

private fun getIsland(): String {
    return (islandList[Random().nextInt(8)])
}

private fun getDiscountIslandList(): List<String> {
    return islandList.filter { it[0] == 'L' }
}