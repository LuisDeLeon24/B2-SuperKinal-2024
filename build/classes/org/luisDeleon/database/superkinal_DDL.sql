	-- drop database if exists superKinal;

create database if not exists superKinal;

use superKinal;

create table Clientes(
	clienteid int not null auto_increment,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    telefono varchar(15),
    direccion varchar(150) not null,
    nit varchar(30)not null,
    primary key PK_clienteid(clienteid)
);


create table Cargos(
	cargoId int not null auto_increment,
	nombreCargo varchar(30) not null,
    descripcionCargo varchar(100) not null,
    primary key PK_cargoId(cargoId)
);
 
create table Empleados(
	empleadoId int not null auto_increment,
    nombreEmpleado varchar(30) not null,
    apellidoEmpleado varchar(30) not null,
    sueldo decimal(10,2) not null,
    horaEntrada Time not null,
    horaSalida Time not null,
    cargoId int not null,
    encargadoId int,
    primary key PK_empleadoId(empleadoId),
    constraint FK_Empleados_Cargos foreign key(cargoId) references Cargos (cargoId),
    constraint FK_encargadoId foreign key(encargadoId) references Empleados(empleadoId)
);
 
create table Distribuidores(
	distribuidorId int not null auto_increment,
    nombreDistribuidor varchar(30) not null,
    direccionDistribuidor varchar(200) not null,
    nitDistribuidor varchar(15) not null,
    telefonoDistribuidor varchar(15) not null,
    web varchar(15),
    primary key PK_distribuidorId(distribuidorId)
);
 
create table CategoriaProductos(
	categoriaProductosId int not null auto_increment,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(100) not null,
    primary key PK_categoriaProductosId(categoriaProductosId)
);
 
create table Compras(
	compraId int not null auto_increment,
    fechaCompra date not null,
    totalCompra decimal(10,2),
    primary key PK_compraId(compraId)
);
 
 
create table Facturas(
	facturaId int not null auto_increment,
    fecha date not null,
    hora time not null,
    clienteId int(11) not null,
    empleadoId int(11) not null,
    total decimal(10,2),
    primary key PK_facturaId(facturaId),
    constraint FK_Facturas_Clientes foreign key (clienteId) references Clientes(clienteId),
    constraint FK_Facturas_Empleados foreign key (empleadoId) references Empleados(empleadoId)
);

create table TicketSoporte(
	ticketSoporteId int not null auto_increment,
    descripcionTicket varchar(250) not null,
    estatus varchar(30) not null,
    clienteId int(11) not null,
    facturaId int(11),
    primary key PK_ticketSoporteId(ticketSoporteId),
    constraint FK_TicketSoporte_Clientes foreign key (clienteId) references Clientes(clienteId)
);
 
create table Productos(
	productoId int not null auto_increment,
    nombreProducto varchar(50) not null,
    descripcionProducto varchar(100),
    cantidadStock int(11) not null,
    precioVentaUnitario decimal(10,2) not null,
    precioVentaMayor decimal(10,2) not null,
    precioCompra decimal(10,2) not null,
    imagenProducto blob,
    distribuidorId int(11) not null,
    categoriaProductosId int(11) not null,
    primary key PK_productoId(productoId),
    constraint FK_Productos_Distribuidores foreign key (distribuidorId) references Distribuidores(distribuidorId),
    constraint FK_Productos_CategoriaProductos foreign key(categoriaProductosId) references CategoriaProductos(categoriaProductosId)
);
 
create table Promociones(
	promocionId int(11) not null auto_increment,
    precioPromocion decimal(10,2) not null,
    descripcionPromocion varchar(100),
    fechaInicio date not null,
    fechaFinalizacion date not null,
    productoId int(11) not null,
    primary key PK_promocionId(promocionId),
    constraint FK_Promociones_Productos foreign key(productoId) references Productos(productoId)
);
 
create table DetalleCompra(
	detalleCompraId int(11) not null auto_increment,
    cantidadCompra int(11) not null,
    productoId int(11) not null,
    compraId int(11) not null,
    primary key PK_detalleCompraId(detalleCompraId),
    constraint FK_DetalleCompra_Productos foreign key(productoId) references Productos(productoId),
    constraint FK_DetalleCompra_Compras foreign key(compraId) references Compras(compraId)
);
 
create table DetalleFactura(
	detalleFacturaId int(11) not null auto_increment,
    facturaId int(11) not null,
    productoId int(11) not null,
    primary key PK_DetalleFacturaId(detalleFacturaId),
    constraint FK_DetalleFacturas_Facturas foreign key (facturaId) references Facturas(facturaId),
    constraint FK_DetalleFacturas_Productos foreign key (productoId) references Productos(productoId)
);

create table NivelesAcceso(
	nivelAccesoId int not null auto_increment,
    nivelAcceso varchar(40) not null,
    primary key PK_nivelAccesoId(nivelAccesoId)
);

create table Usuarios(
	usuarioId int not null auto_increment,
    usuario varchar(30) not null,
    contrasenia varchar(100) not null,
    nivelAccesoId int not null,
    empleadoId int not null,
    primary key PK_usuarioId(usuarioId),
    constraint FK_Usuarios_NivelesAcceso foreign key(nivelAccesoId)
		references NivelesAcceso(nivelAccesoId),
	constraint FK_Usuarios_Empleados foreign key(empleadoId)
		references Empleados(empleadoId)
);

INSERT INTO Clientes (nombre, apellido, telefono, direccion, nit) VALUES 
('Juan', 'Perez', '123456789', 'Calle 123, Ciudad', 'NIT123'),
('Maria', 'Lopez', '987654321', 'Avenida 456, Pueblo', 'NIT456'),
('Carlos', 'Gomez', '654987321', 'Ruta 789, Villa', 'NIT789'),
('Ana', 'Martinez', '789321654', 'Bulevar 012, Poblado', 'NIT012');


INSERT INTO Cargos (nombreCargo, descripcionCargo) VALUES 
('Gerente', 'Responsable de la gestión general'),
('Vendedor', 'Encargado de ventas y atención al cliente'),
('Contador', 'Encargado de la contabilidad y finanzas'),
('Recepcionista', 'Encargado de recibir a los clientes');

INSERT INTO Empleados (nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargadoId) VALUES 
('Pedro', 'Garcia', 2500.00, '08:00:00', '17:00:00', 1, NULL),
('Luisa', 'Ramirez', 1800.00, '09:00:00', '18:00:00', 2, 1),
('Sofia', 'Lopez', 2000.00, '08:30:00', '17:30:00', 2, 1),
('Alejandro', 'Diaz', 2200.00, '08:00:00', '16:30:00', 3, NULL);

INSERT INTO Distribuidores (nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web) VALUES 
('Distribuidor A', 'Calle Principal, Ciudad', 'NITDISTRIB1', '123456789', 'www.distr.com'),
('Distribuidor B', 'Avenida Central, Pueblo', 'NITDISTRIB2', '987654321', 'www.distr.com'),
('Distribuidor C', 'Bulevar Norte, Villa', 'NITDISTRIB3', '654987321', 'www.dir.net'),
('Distribuidor D', 'Ruta Sur, Poblado', 'NITDISTRIB4', '789321654', 'www.distrr.net');

INSERT INTO CategoriaProductos (nombreCategoria, descripcionCategoria) VALUES 
('Electrónicos', 'Productos electrónicos de última generación'),
('Ropa', 'Prendas de vestir para todas las edades'),
('Hogar', 'Artículos para el hogar y decoración'),
('Alimentos', 'Productos alimenticios y bebidas');

INSERT INTO Compras (fechaCompra, totalCompra) VALUES 
('2024-05-01', 150.50),
('2024-05-03', 200.00),
('2024-05-05', 350.75),
('2024-05-07', 180.25);

INSERT INTO Facturas (fecha, hora, clienteId, empleadoId, total) VALUES 
('2024-05-02', '10:30:00', 1, 1, 120.00),
('2024-05-04', '11:45:00', 2, 2, 180.50),
('2024-05-06', '09:15:00', 3, 1, 250.75),
('2024-05-08', '14:00:00', 4, 3, 300.25);

INSERT INTO Facturas (fecha, hora, clienteId, empleadoId) VALUES 
('2024-05-02', '10:30:00', 10, 1),
('2024-05-04', '11:45:00', 10, 2),
('2024-05-06', '09:15:00', 10, 1),
('2024-05-08', '14:00:00', 10, 4);

INSERT INTO TicketSoporte (descripcionTicket, estatus, clienteId, facturaId) VALUES 
('Problema con el producto X', 'Abierto', 1, 10, 1),
('Consulta sobre envío', 'En proceso', 2,10, 1),
('Reclamo por factura incorrecta', 'Resuelto', 10, 1),
('Solicitud de devolución', 'Abierto', 4, 10, 1);

INSERT INTO Productos (nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, distribuidorId, categoriaProductosId) VALUES 
('Televisor LED 55"', 'Resolución 4K', 15, 899.99, 850.00, 700.00, 1, 1),
('Camisa de vestir', 'Talla M, color blanco', 50, 29.99, 25.00, 15.00, 2, 2),
('Juego de sábanas', 'Para cama matrimonial', 30, 49.99, 45.00, 30.00, 3, 3),
('Cereal de chocolate', 'Paquete de 500g', 100, 5.99, 5.00, 3.50, 4, 4);

INSERT INTO Promociones (precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId) VALUES 
(799.99, 'Descuento especial por tiempo limitado', '2024-05-01', '2024-05-15', 1),
(19.99, 'Oferta por lanzamiento', '2024-05-05', '2024-05-20', 2),
(39.99, 'Promoción de temporada', '2024-05-10', '2024-05-25', 3),
(3.99, 'Oferta por cantidad', '2024-05-03', '2024-05-10', 4);

INSERT INTO DetalleCompra (cantidadCompra, productoId, compraId) VALUES 
(2, 1, 1),
(3, 2, 2),
(1, 3, 3),
(5, 4, 4);

INSERT INTO DetalleFactura (facturaId, productoId) VALUES 
(9, 1),
(9, 2),
(10, 3),
(11, 4);

INSERT INTO NivelesAcceso (nivelAcceso) VALUES
    ('Administrador'),
    ('Usuario'),
    ('Invitado'),
    ('Moderador');





