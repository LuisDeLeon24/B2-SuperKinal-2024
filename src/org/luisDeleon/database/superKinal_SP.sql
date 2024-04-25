use superKinal;

-- Compras
DELIMITER $$
	create procedure sp_AgregarCompra(fec date, tot decimal(10,2))
    begin
		insert into Clientes(fechaCompra.totalCompra) values
			(fec,tot);
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
		select* from DetalleCompra;
    end$$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarDetalleCompra(dtId int)
    begin
		delete from DetalleCompra
        where detalleCompraId = dtId;
    end$$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarDetalleCompra(dtId int)
	BEGIN
		select * 
			from DetalleCompra 
            where detalleCompraId = dtId;
	END $$
DELIMITER ;

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
		insert into Clientes(nombreCargo,descripcionCargo) values
			(nomCa, desCar);
    end $$
DELIMITER ;
 
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
		insert into CategoriaProductos(nombreCategoria, descripccionCategoria) values
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
		where categoriaProductoId = catId;
    end $$
delimiter ;
 
-- buscar
delimiter $$
create procedure sp_buscarCategoriaProducto(catId int)
	begin 
		select *from CategoriaProductos
        where categoriaProductoId = catId;
    end $$
delimiter ;
 
-- Editar
delimiter $$
create procedure sp_editarCategoriaProductos(catId int,nomCat varchar(30), desCat varchar(100))
	begin
        update CategoriaProductos
			set
            nombreCategoria = nomCat,
            descripccionCategoria = desCat
            where categoriaProductoId = catId;			
    end $$
delimiter ;

