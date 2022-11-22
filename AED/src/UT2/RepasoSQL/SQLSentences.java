package UT2.RepasoSQL;

public class SQLSentences {

    private SQLSentences(){}

    //Tablas empleados y departamentos

    //(a) Mostrar el nombre y la localidad de todos los departamentos
    protected static String SENTENCE_1 = "SELECT nombreDeptno AS NOMBRE, localidad AS LOCALIDAD FROM departamentos;";

    //(b) Mostrar los departamentos que no se encuentren en 'Madrid'
    protected static String SENTENCE_2 = "SELECT nombreDeptno AS NOMBRE FROM departamentos WHERE localidad != 'madrid';";

    //(c) Mostrar los datos de los empleados que no pertenezcan a departamentos localizados en
    //'Madrid' y que cobren de sueldo más de 2000 €
    protected static String SENTENCE_3 = "SELECT empno AS IDENTIFICADOR, nombreEmpno AS NOMBRE, cargo AS CARGO, fechalng AS FECHA_INGRESO, " +
            "salario AS SALARIO, comision AS COMISION, nombreDeptno as DEPARTAMENTO FROM empleados INNER JOIN departamentos " +
            "WHERE departamentos.deptno = empleados.deptno AND localidad != 'madrid' AND salario > 2000;";

    //(d) Mostrar los datos de los empleados cuyo salario mas comisión superan los 1500€ y que
    //trabajen en cualquier departamento que no sea 'Administración', o bien, que no cobra comisión y
    //pertenece a 'Administración'
    protected static String SENTENCE_4 = "SELECT empno AS IDENTIFICADOR, nombreEmpno AS NOMBRE, cargo AS CARGO, fechalng AS FECHA_INGRESO, " +
            "salario AS SALARIO, comision AS COMISION, nombreDeptno as DEPARTAMENTO FROM empleados INNER JOIN departamentos " +
            "WHERE departamentos.deptno = empleados.deptno AND (salario + comision) > 1500 AND nombreDeptno != 'Administracion' " +
            "OR comision = 0 AND nombreDeptno == 'Administracion';";

    //Productos y proveedores

    //a. Obtener los Nombres de los productos y el proveedor que lo suministra, cuyo precio es superior a 2000, ordenados por precio descendente.
    protected static String SENTENCE_5 = "SELECT nombre_prod AS PRODUCTO, nombre_prov AS PROVEEDOR, precio AS PRECIO FROM proveedores " +
            "INNER JOIN productos WHERE proveedores.cod_prov = productos.cod_prov AND precio > 2000 ORDER BY precio DESC;";


//    b. Obtener el nombre del proveedor y el teléfono para los productos que contengan en su nombre "ordenador"
//    protected static String SENTENCE_6 = "SELECT nombre_prov AS PROVEEDOR, telefono AS TELEFONO FROM proveedores " +
//        "INNER JOIN productos WHERE proveedores.cod_prov = productos.cod_prov AND nombre_prod IN ('ordenador');";
    protected static String SENTENCE_6 = "SELECT nombre_prov AS PROVEEDOR, telefono AS TELEFONO FROM proveedores " +
        "INNER JOIN productos WHERE proveedores.cod_prov = productos.cod_prov AND nombre_prod LIKE '%ordenador%';";


//    c. Obtener el nombre de los productos cuyo stock está por debajo de 20.
    protected static String SENTENCE_7 = "SELECT nombre_prod AS PRODUCTOS FROM productos WHERE stock < 20;";

//    d. Decrementar el precio de los productos en un 5% para aquellos productos cuyo proveedor no tenga ninguna bonificación.
    protected static String SENTENCE_8 = "SELECT precio AS PRECIO FROM productos INNER JOIN proveedores WHERE productos.cod_prov = proveedores.cod_prov AND bonifica = 0;";

//    e. Mostrar el nombre del proveedor, el número de productos y la media de los precios de sus productos (con Group by).
    protected static String SENTENCE_9 = "SELECT nombre_prov AS PROVEEDOR, SUM(stock) AS STOCK, AVG(precio) AS PRECIO " +
        "FROM productos INNER JOIN proveedores WHERE productos.cod_prov = proveedores.cod_prov GROUP BY proveedores.cod_prov;";

//    f. Obtener el nombre del proveedor, la dirección y el teléfono, del proveedor cuyo producto tenga el mayor stock
    protected static String SENTENCE_10 = "SELECT nombre_prov AS PROVEEDOR, direccion AS DIRECCION, telefono AS TELEFONO " +
        "FROM proveedores WHERE cod_prov AND (SELECT MAX(stock) FROM productos);";



}
