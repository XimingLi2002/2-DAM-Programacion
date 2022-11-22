SELECT nombre_prod AS PRODUCTO, nombre_prov AS PROVEEDOR, precio AS PRECIO FROM proveedores INNER JOIN productos ON proveedores.cod_prov = productos.cod_prov WHERE precio > 2000 ORDER BY precio DESC
SELECT nombre_prov AS PROVEEDOR, telefono AS TELEFONO FROM proveedores INNER JOIN productos ON proveedores.cod_prov = productos.cod_prov WHERE nombre_prod LIKE '%ordenador%'
SELECT nombre_prod AS PRODUCTOS FROM productos WHERE stock < 20
SELECT nombre_prod AS PRODUCTOS, precio * 0.95 AS PRECIO_DECREMENTADO FROM productos INNER JOIN proveedores ON proveedores.cod_prov = productos.cod_prov WHERE bonifica = 0
SELECT nombre_prov AS PROVEEDOR, SUM(stock) AS STOCK, AVG(precio) AS PRECIO FROM productos INNER JOIN proveedores ON proveedores.cod_prov = productos.cod_prov GROUP BY (nombre_prov)
SELECT nombre_prov AS PROVEEDOR, direccion, telefono FROM proveedores INNER JOIN productos ON proveedores.cod_prov = productos.cod_prov WHERE stock = (SELECT MAX(stock) FROM productos)