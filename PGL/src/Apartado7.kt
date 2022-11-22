import kotlin.math.roundToInt

fun main(){
    val listaEmpleados =  arrayListOf<Empleados>()
    for (x in 1..6){
        println("Introduce los datos del empleado siguiente el siguiente formato: \nidentificador, nombre, direccion y salario")
        listaEmpleados.add(Empleados(getStringInput(),getStringInput(),getStringInput(),getSalario()))
    }
    //mostrar todos los datos de los empleados
    println("- - - DATOS DE LOS EMPLEADOS - - -")
    for (empleado in listaEmpleados) {
        println("Identificador: " + empleado.identificador +
                "  |  Nombre: " + empleado.nombre +
                "  |  Direccion: " + empleado.direccion +
                "  |  Salario: " + empleado.salario
        )
    }
}
private fun getStringInput() : String {
    var input: String
    do {
        input = readLine()!!.toString()
        if (input.isBlank()) println("No puedes dejarlo vacio")
    }while (input.isBlank())
    return input
}
private fun getSalario(): Double {
    //val decimalFormat = DecimalFormat("##.##")
    var salario = 0.00
    do {
        try {
            salario = readLine()!!.toDouble()
            //Para parsear a 2 decimales:
            salario = (salario * 100).roundToInt().toDouble() / 100
            //Tambien existe un decimalFormat.roundingMode para redondear
        } catch (e: java.lang.NumberFormatException) {
            println("Formato incorrecto, tiene que ser: 0.00")
        }
    } while (salario == 0.00)
    return salario
}


private class Empleados (var identificador : String, var nombre: String, var direccion: String, var salario : Double) {

}