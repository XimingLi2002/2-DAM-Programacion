DELETE from empleados
insert into empleados values ('12345678Z', 'Pedro', 'Aguimes', 'director', 500)
insert into empleados values ('12343210Y', 'Luis', 'Vecindario', 'vendedor', 202)
insert into empleados values ('98765432X', 'Carla', 'San Fernando', 'comercial', 1)
insert into empleados values ('11122233U', 'Juan', 'San Agustin', 'tecnico', 2)
insert into empleados values ('99999999S', 'Alfonso', 'San Agustin', 'vendedor', 4)
insert into empleados values ('88888888S', 'Santiago', 'San Agustin', 'vendedor', 4)
insert into empleados values ('77777777W', 'Lara', 'San Fernando', 'comercial', 4)
insert into empleados values ('11111111A', 'Juan', 'Vecindario', 'tecnico', 2)
select empleados.nombre, empleados.direccion from empleados INNER JOIN oficinas ON empleados.cod_ofi = oficinas.cod_ofi WHERE cargo = 'vendedor' AND localidad = 'Telde' ORDER BY empleados.nombre DESC
select COUNT(empleados.cod_ofi) AS EMPLEADOS, oficinas.cod_ofi, oficinas.nombre FROM oficinas INNER JOIN empleados ON empleados.cod_ofi = oficinas.cod_ofi GROUP BY oficinas.cod_ofi, oficinas.nombre
select  MAX(COUNT(empleados.cod_ofi)) AS MAX_EMPLEADOS, MIN(COUNT(empleados.cod_ofi)) AS MIN_EMPLEADOS from empleados INNER JOIN oficinas ON empleados.cod_ofi = oficinas.cod_ofi group by cargo, localidad