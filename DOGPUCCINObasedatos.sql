-- Oracle SQL version adapted from MySQL

-- CREACIÓN DE TABLAS

CREATE TABLE clientes (
  NIF VARCHAR2(9) PRIMARY KEY,
  NyA VARCHAR2(50) NOT NULL,
  Direcion VARCHAR2(50) NOT NULL,
  Poblacion VARCHAR2(50) NOT NULL
);

CREATE TABLE vehiculos (
  matricula VARCHAR2(7) PRIMARY KEY,
  descripcion VARCHAR2(50) NOT NULL,
  marca VARCHAR2(50) NOT NULL,
  kilometros NUMBER NOT NULL,
  precio NUMBER NOT NULL
);

CREATE TABLE servicios (
  id_servicio NUMBER PRIMARY KEY,
  matricula_vehiculo VARCHAR2(7) NOT NULL,
  nif_cliente VARCHAR2(9) NOT NULL,
  fecha_alquiler DATE NOT NULL,
  fecha_entrega DATE NOT NULL,
  Total NUMBER NOT NULL,
  FOREIGN KEY (matricula_vehiculo) REFERENCES vehiculos(matricula),
  FOREIGN KEY (nif_cliente) REFERENCES clientes(NIF)
);

-- SECUENCIA PARA ID_AUTOINCREMENT EN SERVICIOS

CREATE SEQUENCE seq_servicios START WITH 2 INCREMENT BY 1;

-- TRIGGER PARA AUTOINCREMENTAR ID_SERVICIO

CREATE OR REPLACE TRIGGER trg_servicios_ai
BEFORE INSERT ON servicios
FOR EACH ROW
WHEN (NEW.id_servicio IS NULL)
BEGIN
  SELECT seq_servicios.NEXTVAL INTO :NEW.id_servicio FROM dual;
END;
/

-- INSERTS EN CLIENTES

INSERT INTO clientes (NIF, NyA, Direcion, Poblacion) VALUES
('11111111A', 'Fernando Ureña', 'Calle falsa 123', 'Ciudad real');

INSERT INTO clientes (NIF, NyA, Direcion, Poblacion) VALUES
('22222222B', 'Alfredo Arteaga', 'Calle monescillo', 'Ciudad real');

INSERT INTO clientes (NIF, NyA, Direcion, Poblacion) VALUES
('33333333C', 'Roberto Ciudad', 'Calle Misterio', 'Ciudad real');

-- INSERTS EN VEHICULOS

INSERT INTO vehiculos (matricula, descripcion, marca, kilometros, precio) VALUES
('1111ABC', 'Vehiculo a motor', 'BMW', 25032, 85);

INSERT INTO vehiculos (matricula, descripcion, marca, kilometros, precio) VALUES
('2222DEF', 'Vehiculo a propulsion', 'Seat', 10000, 90);

INSERT INTO vehiculos (matricula, descripcion, marca, kilometros, precio) VALUES
('3333GHI', 'Vehiculo nuevo', 'Audi', 0, 100);

-- COMMIT FINAL

COMMIT;
