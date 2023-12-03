create schema GestionBanco;

use GestionBanco;

create table usuarios
(
idUsuario VARCHAR(25) NOT NULL PRIMARY KEY,
pass VARCHAR(25),
esAdmin BOOL,
estado BOOL
);

create table clientes
(
idUsuario VARCHAR(25) NOT NULL PRIMARY KEY,
dni VARCHAR(25),
cuil VARCHAR(25),
nombre VARCHAR(25),
apellido VARCHAR(25),
sexo VARCHAR(25),
nacionalidad VARCHAR(30),
fechaNacimiento DATE,
direccion VARCHAR(50),
localidad VARCHAR(50),
provincia VARCHAR(50),
correoElectronico VARCHAR(50),
telefono VARCHAR(25),
estado BOOL
);

create table tiposCuentas
(
idTipoCuenta INT PRIMARY KEY NOT NULL,
descripcion VARCHAR(50)
);

create table cuentas
(
cbu VARCHAR(25) NOT NULL PRIMARY KEY,
idUsuario VARCHAR(25),
nroCuenta VARCHAR(25),
idTipoCuenta INT NOT NULL,
fechaAlta DATE,
saldo DECIMAL(10,2),
estado BOOL,
FOREIGN KEY(idTipoCuenta) REFERENCES tiposCuentas(idTipoCuenta),
FOREIGN KEY(idUsuario) REFERENCES clientes(idUsuario)
);

create table tiposMovimientos
(
idTipoMovimiento INT PRIMARY KEY NOT NULL,
descripcion VARCHAR(50)
);

create table movimientos
(
idMovimiento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
cbu VARCHAR(25),
fechaOperacion DATE,
concepto VARCHAR(40),
importe DECIMAL(10,2),
idTipoMovimiento INT NOT NULL,
FOREIGN KEY(idTipoMovimiento) REFERENCES tiposMovimientos(idTipoMovimiento)
);

create table prestamos
(
idPrestamo INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
idUsuario VARCHAR(25) NOT NULL,
cbu VARCHAR(25),
fechaSolicitud DATE,
montoCouta DECIMAL(10,2),
montoTotal DECIMAL(10,2),
plazoPago INT,
aprobado BOOL,
pendiente BOOL,
FOREIGN KEY(cbu) REFERENCES cuentas(cbu),
FOREIGN KEY(idUsuario) REFERENCES clientes(idUsuario)
);

create table pagoPrestamos
(
idPago INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
idPrestamo INT NOT NULL,
fechaPago DATE,
nroPago INT NOT NULL,
FOREIGN KEY(idPrestamo) REFERENCES prestamos(idPrestamo)
);

INSERT INTO tiposCuentas (idTipoCuenta, descripcion)
VALUES
    (1, 'Cuenta Corriente'),
    (2, 'Cuenta de Ahorro');
    
INSERT INTO tiposMovimientos (idTipoMovimiento, descripcion)
VALUES
    (1, 'Alta de cuenta'),
    (2, 'Alta de préstamo'),
    (3, 'Pago de préstamo'),
    (4, 'Transferencia');

INSERT INTO usuarios (idUsuario, pass, esAdmin, estado) 
VALUES 
	('root', 'root', 1, 1),
    ('admin', 'admin', 1, 1),
    ('cliente', 'cliente', 0, 1),
    ('juanPerez', 'juanPerez', 0, 1),
    ('mariaLopez', 'mariaLopez', 0, 1),
    ('luisRodriguez', 'luisRodriguez', 0, 1);

INSERT INTO clientes (idUsuario, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, correoElectronico, telefono, estado)
VALUES
    ('juanPerez', '32345678', '27-32345678-5', 'Juan', 'Pérez', 'Masculino', 'Argentina', '1987-01-15', 'Calle 123', 'Tigre', 'Buenos Aires', 'juan@gmail.com', '123456789', 1),
    ('mariaLopez', '44654321', '32-44654321-1', 'María', 'López', 'Femenino', 'Argentina', '2003-05-20', 'Calle 456', 'Catamarca', 'Catamarca', 'maria@gmail.com', '987654321', 1),
    ('luisRodriguez', '37757001', '26-37757001-2', 'Luis', 'Rodríguez', 'Masculino', 'Argentina', '1994-07-07', 'Calle 1213', 'CABA', 'Buenos Aires', 'luis@gmail.com', '023655891', 1);

INSERT INTO cuentas (cbu, idUsuario, nroCuenta, idTipoCuenta, fechaAlta, saldo, estado)
VALUES
    ('CBU001', 'juanPerez', '12345', 2, '2023-08-01', 45020.80, 1),
    ('CBU002', 'mariaLopez', '54321', 1, '2023-08-12', 80000.00, 1),
    ('CBU003', 'mariaLopez', '10101', 2, '2023-08-12', 702201.87, 1),
    ('CBU004', 'luisRodriguez', '98765', 1, '2023-08-18', 10000.00, 1),
    ('CBU005', 'luisRodriguez', '85698', 2, '2023-08-18', 98256.70, 1);
    
INSERT INTO prestamos (idUsuario, cbu, fechaSolicitud, montoCouta, montoTotal, plazoPago, aprobado, pendiente)
VALUES
    ('mariaLopez', 'CBU003', '2023-09-20', 125000.00, 1000000.00, 12, 0, 1),
    ('mariaLopez', 'CBU003', '2023-10-06', 62500.00, 500000.00, 12, 0, 1),
    ('mariaLopez', 'CBU003', '2023-09-01', 125000.00, 2000000.00, 24, 1, 0),
    ('juanPerez', 'CBU001', '2023-08-22', 62500.00, 500000.00, 12, 1, 0),
    ('luisRodriguez', 'CBU005', '2023-08-25', 125000.00, 2000000.00, 24, 0, 1);

INSERT INTO movimientos (cbu, fechaOperacion, concepto, importe, idTipoMovimiento)
VALUES
    ('CBU001', '2023-08-01', 'Depósito inicial', 10000.00, 1),
    ('CBU001', '2023-08-05', 'Transferencia recibida', 7000.00, 4),
    ('CBU001', '2023-08-12', 'Transferencia recibida', 13000.00, 4),
    ('CBU001', '2023-08-19', 'Transferencia recibida', 10000.00, 4),
    ('CBU001', '2023-08-20', 'Transferencia realizada', -25080.20, 4),
    ('CBU001', '2023-08-25', 'Transferencia recibida', 5000.00, 4),
    ('CBU001', '2023-09-08', 'Transferencia recibida', 15000.00, 4),
    ('CBU001', '2023-09-20', 'Transferencia recibida', 40000.00, 4),
    ('CBU001', '2023-10-25', 'Transferencia realizada', -29899.00, 4),
    ('CBU002', '2023-08-12', 'Depósito inicial', 10000.00, 1),
    ('CBU002', '2023-09-05', 'Transferencia recibida', 70000.00, 4),
    ('CBU003', '2023-08-12', 'Depósito inicial', 10000.00, 1),
    ('CBU003', '2023-08-29', 'Transferencia recibida', 200000.00, 4),
	('CBU003', '2023-09-01', 'Transferencia recibida', 500000.00, 4),
    ('CBU003', '2023-09-01', 'Transferencia realizada', -25000.00, 4),
    ('CBU003', '2023-09-30', 'Capital Préstamo aprobado', 1000000.00, 2),
    ('CBU003', '2023-10-01', 'Transferencia realizada', -57798.13, 4),
    ('CBU003', '2023-10-07', 'Transferencia realizada', -800000.00, 4),
    ('CBU003', '2023-10-18', 'Capital Préstamo aprobado', 500000.00, 2),
    ('CBU003', '2023-10-20', 'Transferencia realizada', -500000.00, 4),
    ('CBU003', '2023-10-31', 'Cuota Préstamo', -125000.00, 3),    
    ('CBU004', '2023-08-18', 'Depósito inicial', 10000.00, 1),
    ('CBU005', '2023-08-18', 'Depósito inicial', 10000.00, 1),
    ('CBU005', '2023-08-29', 'Transferencia recibida', 801743.30, 4),
    ('CBU005', '2023-09-01', 'Transferencia realizada', -150000.00, 4),
    ('CBU005', '2023-09-05', 'Capital Préstamo aprobado', 2000000.00, 2),
    ('CBU005', '2023-09-06', 'Transferencia realizada', -2000000.70, 4),
    ('CBU005', '2023-09-08', 'Transferencia realizada', -50057.70, 4),
    ('CBU005', '2023-09-14', 'Transferencia realizada', -400685.60, 4),
    ('CBU005', '2023-09-20', 'Transferencia realizada', -20000.00, 4),
    ('CBU005', '2023-09-29', 'Transferencia recibida', 901059.00, 4),
    ('CBU005', '2023-10-01', 'Transferencia realizada', -150000.00, 4),
    ('CBU005', '2023-10-05', 'Cuota Préstamo', -125000.00, 3),
    ('CBU005', '2023-10-10', 'Transferencia realizada', -37000.00, 4),
    ('CBU005', '2023-10-13', 'Transferencia realizada', -200000.00, 4),
    ('CBU005', '2023-10-16', 'Transferencia realizada', -50000.00, 4),
    ('CBU005', '2023-10-16', 'Transferencia realizada', -150000.00, 4),
    ('CBU005', '2023-10-20', 'Transferencia realizada', -11802.30, 4),
    ('CBU005', '2023-10-26', 'Transferencia realizada', -240000.00, 4);
