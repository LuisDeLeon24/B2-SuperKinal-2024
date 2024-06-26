use superKinal;

-- Compras
DELIMITER $$
create procedure sp_agregarCompra(in fecCom date, in can int, in proId int)
	begin 
		declare nuevaCompraId int;
		insert into Compras (fechaCompra) values
			(fecCom);
            
		set nuevaCompraId = last_insert_id();
        call sp_agregarDetalleCompra(can,proId,nuevaCompraId);
    end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_ListarCompra()
    begin
		select* from Compras;
    end$$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarCompra(comId int)
    begin
		delete from Comprars
        where compraId = comId;
    end$$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarCompras(comId int)
	BEGIN
		select * 
			from Compras
            where compraId = comId;
	END $$
DELIMITER ;

DELIMITER $$
create procedure sp_editarCompra(comId int, fec date, tot decimal(10,2))
	BEGIN
		update Compras
			set
				fechaCompra = fec,
                totalCompra = tot
					where compraId = comId;
	END $$
DELIMITER ;

-- detalleCompra

DELIMITER $$
	create procedure sp_AgregarDetalleCompra(cantC int, proId int,comId int)
    begin
		insert into DetalleCompra(cantidadCompra,productoId,compraId) values
			(cantC,proId,comId);
    end $$
DELIMITER ;

DELIMITER $$
create procedure sp_ListarDetalleCompra()
    begin 
		select C.compraId, C.fechaCompra, DE.cantidadCompra,
		concat(P.nombreProducto, ' | ', P.precioCompra) as 'producto',
        C.totalCompra from DetalleCompra DE
        join Compras C on C.compraId = DE.compraId
        left join Productos P on P.productoId = DE.productoId;
        
    end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarDetalleCompra(dtId int)
    begin
		delete from DetalleCompra
        where detalleCompraId = dtId;
    end$$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarDetalleCompra(in detCId int)
	BEGIN
		select CO.compraId, CO.fechaCompra,CO.totalCompra, DE.cantidadCompra, 
        DE.productoId as 'Producto' from DetalleCompra DE
        join Compras CO on DE.compraId = CO.compraId
		where CO.compraId = detCId;
    END $$
DELIMITER ;

call sp_buscarDetalleCompra(1);

DELIMITER $$
create procedure sp_editarDetalleCompra(dtId int,cantC int, proId int,comId int)
	BEGIN
		update DetalleCompra
			set
				cantidadCompra = cantC,
				productoId = proId,
				compraId = comId
					where dtId = detalleCompraId;
	END $$
DELIMITER ;


-- Clientes

DELIMITER $$
	create procedure sp_AgregarCliente(nom varchar(30),ape varchar(30),tel varchar(15),dir varchar(150),n varchar(30))
    begin
		insert into Clientes(nombre,apellido,telefono,direccion,nit) values
			(nom,ape,tel,dir,n);
    end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_ListarClientes()
    begin
		select* from Clientes;
    end$$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarCLiente(cliID int)
    begin
		delete from CLientes
        where ClienteID =cliID;
    end$$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarCliente(cliId int)
	BEGIN
		select * 
			from Clientes 
            where cliId = clienteId ;
	END $$
DELIMITER ;

call sp_buscarCliente(1);

DELIMITER $$
create procedure sp_editarCliente(cliId int, nom varchar(30), ape varchar(30), tel varchar(15), dir varchar(150),n varchar(30))
	BEGIN
		update Clientes
			set
				nombre = nom,
				apellido = ape,
				telefono = tel,
				direccion = dir,
                nit =n
					where cliId = clienteId ;
	END $$
DELIMITER ;

-- crud de cargos
-- agregar
DELIMITER $$
	create procedure sp_agregarCargo(nomCar varchar(30),desCar varchar(100))
    begin
		insert into Cargos(nombreCargo,descripcionCargo) values
			(nomCar, desCar);
    end $$
DELIMITER ;

call sp_agregarCargo('Cajero','Atender en la caja');
 
-- listar
delimiter $$
create procedure sp_listarCargos()
	begin
		select * from Cargos;
    end $$
delimiter ;
 
-- elimiar
delimiter $$
create procedure sp_eliminarCargo(carId int)
	begin
		delete from Cargos
		where cargoId = carId;
    end $$
delimiter ;
 
-- buscar
delimiter $$
create procedure sp_buscarCargo(carId int)
	begin 
		select *from Cargos
        where cargoId = carId;
    end $$
delimiter ;
 
-- editar
delimiter $$
create procedure sp_editarCargos(carId int, nomCar varchar(30), desCar varchar(100)  )
	begin
        update Cargos
			set
            nombreCargo = nomCar,
            descripcionCargo = desCar
            where cargoId = carId;			
    end $$
delimiter ;
 
-- crud de Distribuidores
-- agregar
DELIMITER $$
	create procedure sp_agregarDistribuidores(nomDis varchar(30), dirDis varchar(200), nitDis varchar(15), telDis varchar(15), web varchar(50))
    begin
		insert into Distribuidores(nombreDistribuidor,direccionDistribuidor,nitDistribuidor,telefonoDistribuidor,web) values
			(nomDis, dirDis, nitDis, telDis, web);
    end $$
DELIMITER ;
 
-- listar
delimiter $$
create procedure sp_listarDistribuidores()
	begin
		select * from Distribuidores;
    end $$
delimiter ;
 
-- eliminar
delimiter $$
create procedure sp_eliminarDistribuidores(disId int)
	begin
		delete from Distribuidores
		where distribuidorId = disId;
    end $$
delimiter ;
 
-- buscar
delimiter $$
create procedure sp_buscarDistribuidores(disId int)
	begin 
		select *from Distribuidores
        where distribuidorId = disId;
    end $$
delimiter ;
 
-- editar
delimiter $$
create procedure sp_editarDistribuidores(disId int,nomDis varchar(30), dirDis varchar(200), nitDis varchar(15), telDis varchar(15), web varchar(50))
	begin
        update Distribuidores
			set
            nombreDistribuidor = nomDis,
            direccionDistribuidor = dirDis,
            nitDistribuidor = nitDis,
            telefonoDistribuidor = telDis,
            web = web
            where distribuidorId = disId;			
    end $$
delimiter ;
 
-- crud de CategoriaProductos
-- agregar
DELIMITER $$
	create procedure sp_agregarCategoriaProducto(nomCat varchar(30), desCat varchar(100))
    begin
		insert into CategoriaProductos(nombreCategoria, descripcionCategoria) values
			(nomCat, desCat);
    end $$
DELIMITER ;
 
-- listar
delimiter $$
create procedure sp_listarCategoriaProductos()
	begin
		select * from CategoriaProductos;
    end $$
delimiter ;
 
-- eliminar
delimiter $$
create procedure sp_eliminarCategoriaProducto(catId int)
	begin
		delete from CategoriaProductos
		where categoriaProductosId = catId;
    end $$
delimiter ;
 
-- buscar
delimiter $$
create procedure sp_buscarCategoriaProducto(catId int)
	begin 
		select *from CategoriaProductos
        where categoriaProductosId = catId;
    end $$
delimiter ;
 
-- Editar
delimiter $$
create procedure sp_editarCategoriaProductos(catId int,nomCat varchar(30), desCat varchar(100))
	begin
        update CategoriaProductos
			set
            nombreCategoria = nomCat,
            descripcionCategoria = desCat
            where categoriaProductoId = catId;			
    end $$
delimiter ;

-- Agregar
DELIMITER $$
create procedure sp_AgregarProducto(in nomP varchar(50),in desP varchar(100),in canS int(11),in preVU decimal(10,2), in preVM decimal(10,2), in preC decimal(10,2),in imgP blob,in disId int(11),in catPId int(11))
begin
insert into Productos(nombreProducto,descripcionProducto,cantidadStock,precioVentaUnitario,precioVentaMayor,precioCompra,imagenProducto,distribuidorId,categoriaProductosId) values
(nom,desP,canS,preVU,preVM,preC,imgP,disId,catPId);
end $$
DELIMITER ;
 
-- Listar
DELIMITER $$
create procedure sp_ListarProducto()
begin
select * from Productos;
end $$
DELIMITER ;
 
 
-- Eliminar
DELIMITER $$
create procedure sp_EliminarProducto(in proId int)
begin
delete from Productos where productoId = proId;
end $$
DELIMITER ;
 
-- Buscar
DELIMITER $$
create procedure sp_BuscarProducto(proId int)
begin
select * from Productos where productoId = proId;
end $$
DELIMITER ;
 
--  Editar
DELIMITER $$
create procedure sp_EditarProducto(proId int,in nomP varchar(50),in desP varchar(100),in canS int(11),in preVU decimal(10,2), in preVM decimal(10,2), in preC decimal(10,2),in imgP blob,in disId int(11),in catPId int(11))
begin
Update Productos
set
nombreProducto = nomP,
            descripcionProducto = desP,
            cantidadStock = canS,
            precioVentaUnitario = preVU,
            precioVentaMayor = preVM,
            precioCompra = preC,
            imagenProducto = imaP,
            distribuidorId = disId,
            categoriaProductoId = catPId
where productoId = proId;
end $$
DELIMITER ;
 
 
-- ----------- Facturas ----------------------
-- Agregar
DELIMITER $$
create procedure sp_agregarFacturas(in cliId int, in empId int, in proId int)
	begin
		declare nuevaFacturaId int;
        
		insert into Facturas (fecha, hora, total, clienteId, empleadoId) values
			(current_date(), now(), 0.0, cliId, empId);
            
            set nuevaFacturaId = last_insert_id();
             call sp_agregarDetalleFactura(nuevaFacturaId,proId);
    end $$
DELIMITER ;


-- Listar
DELIMITER $$
create procedure sp_ListarFacturas()
begin
select F.facturaId, F.fecha, F.hora, F.total,
		concat('ID ',Clientes.clienteId,' | ',Clientes.nombre,' ',Clientes.apellido)as 'nombreCliente',
		CONCAT('ID ',Empleados.empleadoId,' | ',Empleados.nombreEmpleado,' ',apellidoEmpleado) as 'nombreEmpleado'
		from Facturas F
join Clientes on F.clienteId = Clientes.clienteId
join Empleados on F.empleadoId = Empleados.empleadoId;
end $$
DELIMITER ;

select *from Empleados;

call sp_ListarFacturas();
 
-- Eliminar
DELIMITER $$
create procedure sp_EliminarFactura(facId int)
begin
delete from Facturas where facturaId = facId;
end $$
DELIMITER ;
 
-- Buscar
DELIMITER $$
create procedure sp_BuscarFactura(facId int)
begin
select F.facturaId, F.fecha, F.hora, F.total,
		concat('ID ',Clientes.clienteId,' | ',Clientes.nombre,' ',Clientes.apellido)as 'nombreCliente',
		CONCAT('ID ',Empleados.empleadoId,' | ',Empleados.nombreEmpleado,' ',apellidoEmpleado) as 'nombreEmpleado'
		from Facturas F
join Clientes on F.clienteId = Clientes.clienteId
join Empleados on F.empleadoId = Empleados.empleadoId
where facId = F.facturaId;
 
end $$
DELIMITER ;


 
-- Editar
DELIMITER $$
create procedure sp_EditarFactura(facId int,fech date, hor time, cliId int(11), empId int(11), tot decimal(10,2))
begin
Update Facturas
set
fecha = fech,
            hora = hor,
            clienteId = cliId,
            empleadoId = empId,
            total = tot
where facturaId = facId;
end $$
DELIMITER ;
 
-- ------------------ DetalleFacturas
-- Agregar
DELIMITER $$
create procedure sp_AgregarDetalleFactura(facId int(11), proId int(11))
begin
insert into DetalleFactura(facturaId, productoId) values (facId, proId);
end $$
DELIMITER ;
 
 
-- Listar
DELIMITER $$
create procedure sp_ListarDetalleFactura()
begin
	select DT.facturaId, P.nombreProducto, CONCAT('ID ',C.clienteid,' | ',C.nombre, ' ', C.apellido) As 'cliente', F.fecha from DetalleFactura DT
    join Facturas F on DT.facturaId = F.facturaId
    join Productos P on DT.productoId = P.productoId
    join Clientes C on F.clienteId = C.clienteid
    order by DT.facturaId;
end $$
DELIMITER ;
 
call  sp_ListarDetalleFactura();
 
-- Eliminar
DELIMITER $$
create procedure sp_EliminarDetalleFactura(detFId int(11))
begin
delete from DetalleFactura where detalleFacturaId = detFId;
end $$
DELIMITER ;
 
-- Buscar
DELIMITER $$
create procedure sp_BuscarDetalleFactura(detFId int(11))
begin
select * from DetalleFactura where detalleFacturaId = detFId;
end $$
DELIMITER ;
 
-- Editar
DELIMITER $$
create procedure sp_EditarDetalleFactura(detFId int(11),facId int(11), proId int(11))
begin
Update DetalleFactura
set
facturaId = facId,
            productoId = proId
where detalleFacturaId = detFId;
end $$
DELIMITER ;
 
-- call sp_AgregarDetalleFactura(1,1);

DELIMITER $$
	create procedure sp_AgregarEmpleado(nom varchar(30), ape varchar(30), sue decimal (10,2), horE Time, horS time, carId int)
		begin
			insert into Empleados (nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId) values 
				(nom, ape, sue, horE, horS, carId);
        end $$
DELIMITER ;
 
call  sp_AgregarEmpleado('Luis', 'De Leon', 2.5, '10:10:10', '10:10:10', 1);
 
DELIMITER $$
create procedure sp_ListarEmpleados()
	begin 
<<<<<<< HEAD
        select *from Empleados;
=======
        select e.empleadoId, e.nombreEmpleado, e.apellidoEmpleado, e.sueldo, e.horaEntrada, e.horaSalida, e.encargadoId,
        CONCAT('ID: ', e.cargoId, ' | ', 'Cargo: ', c.nombreCargo, ' | ', 'Descripcion: ', c.descripcionCargo) as 'Cargo'
		from Empleados e
		join Cargos c ON e.cargoId = c.cargoId;
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
    end $$
DELIMITER ;
call sp_ListarEmpleados();

 
DELIMITER $$
create procedure sp_EditarEmpleado(empId int, nom varchar(30), ape varchar(30), sue decimal (10,2), horE Time, horS time, carId int)
	begin
		update Empleados
			set nombreEmpleado = nom,
				apellidoEmpleado = ape,
                sueldo = sue,
                horaEntrada = horE,
                horaSalida = horS,
                cargoId = carId
                where empId = empleadoId;
    end $$
DELIMITER ;
 
call  sp_EditarEmpleado(1,'1', '2', 2.5, '10:10:10', '10:10:10', 1);
 
DELIMITER $$
create procedure sp_EliminarEmpleado(empId int)
	begin
		delete from Empleados
			where empId = empleadoId;
    end $$
DELIMITER ;
 
call sp_EliminarEmpleado(2);
 
DELIMITER $$
create procedure sp_BuscarEmpleado(empId int)
	begin
		select *
			from Empleados
            where empleadoId = empId;
    end $$
DELIMITER ;
 
call sp_BuscarEmpleado(1);
 
DELIMITER $$
create procedure sp_AsignarEncargado(empId int, encId int)
	begin
    Update Empleados
		set encargadoId = encId 
			where empId = empleadoId;
    end $$
DELIMITER ;
 
call sp_AsignarEncargado(1,1);
 
-- ticketSoporte
 
DELIMITER $$
	create procedure sp_AgregarTicketSoporte(des varchar(250), cliId int, facId int)
		begin
<<<<<<< HEAD
			insert into TicketSoporte (descripcionTicket, estatus, clienteId, facturaId) values 
				(des, 'Recién Creado', cliId, facId);
=======
			insert into TicketSoporte (descripcionTicket, estatus, clienteId,facturaId) values 
				(des, 'Recién Creado', cliId,factId);
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
        end $$
DELIMITER ;
call sp_AgregarTicketSoporte('error',1,1);
 
DELIMITER $$
create procedure sp_ListarTicketSoporte()
	begin 
		select TS.ticketSoporteId, TS.descripcionTicket, TS.estatus, 
				Concat(C.clienteId, C.nombre, C.apellido) AS 'Clientes', 
                TS.facturaId from TicketSoporte TS
        join Clientes C on TS.clienteId = C.clienteId;

    end $$
DELIMITER ;
 
 
DELIMITER $$
create procedure sp_EditarTicketSoporte(ticId int, des varchar(250), est varchar(30), cliId int, facId int)
	begin
		update TicketSoporte
			set descripcionTicket = des,
				estatus = est,
                clienteId = cliId,
                facturaId = facId
                where ticId = ticketSoporteId;
    end $$
DELIMITER ;
 
 
DELIMITER $$
create procedure sp_EliminarTicketSoporte(ticId int)
	begin
		delete from TicketSoporte
			where ticId = ticketSoporteId;
    end $$
DELIMITER ;
 
 
DELIMITER $$
create procedure sp_BuscarTicketSoporte(ticId int)
	begin
		select *
			from TicketSoporte
            where ticId = ticketSoporteId;
    end $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_verificarPromocion()
	begin
		select *
			from TicketSoporte
            where ticId = ticketSoporteId;
    end $$
DELIMITER ;
<<<<<<< HEAD

-- Agregar
delimiter $$
create procedure sp_agregarPromociones(in prePro decimal(10, 2), in descPro varchar(100), in feIni date, in feFina date, in proId int)
	begin
		insert into Promociones (precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId) values
			(prePro, descPro, feIni, feFina, proId);
    end $$
delimiter ;
 
 
-- listar
delimiter $$
create procedure sp_listarPromociones()
	begin
		select * from Promociones;
    end $$
delimiter ;
 
 
-- buscar
delimiter $$
create procedure sp_buscarPromociones(in promoId int)
	begin
		select * from Promociones
			where promocionId = promoId;
    end $$
delimiter ;
 
 
-- eliminar
delimiter $$
create procedure sp_eliminarPromociones(in promoId int)
	begin
		delete 
			from Promociones
				where promocionId = promoId;
    end $$
delimiter ;
 
-- editar
delimiter $$
create procedure sp_editarPromociones(in promoId int, in prePro decimal(10, 2), in descPro varchar(100), in feIni date, in feFina date, in proId int)
	begin
		update Promociones
			set 
            precioPromocion = prePro,
            descripcionPromocion = descPro,
            fechaInicio = feIni,
            fechaFinalizacion = feFina,
            productoId = proId
            where promocionId = promoId;
    end $$
delimiter ;

delimiter $$
	create procedure sp_AgregarUsuario(us varchar(30), con varchar(100), naId int,empId int)
    begin
		insert into Usuarios(usuario,contrasenia,nivelAccesoId,EmpleadoId) values
			(us,con,naId,empId);
    end $$
delimiter ;

call sp_agregarUsuario('ldeleon','1234',1,5);

select *from usuarios;

delimiter $$
	create procedure sp_buscarUsuario(us varchar(30))
    begin
		select * from Usuarios
			where usuario = us;
    end $$
delimiter ;

delimiter $$
	create procedure sp_listarNivelAcceso()
    begin
		select * from nivelesAcceso;
    end $$
delimiter ;

call sp_listarNivelAcceso();

=======
 
>>>>>>> e655c3bd9dcb247a2192055c970e37cff7fc78db
