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

<<<<<<< HEAD
CREATE OR REPLACE TRIGGER trg_servicios_ai
BEFORE INSERT ON servicios
FOR EACH ROW
WHEN (NEW.id_servicio IS NULL)
BEGIN
  SELECT seq_servicios.NEXTVAL INTO :NEW.id_servicio FROM dual;
=======
CREATE TABLE Reservan (
    Cliente_ID number,
    Perro_ID number,
    Donacion varchar2(50),
    Hora number,
    Fecha_cita date,
    Estado varchar2(50),
    Fecha_alta date,
    Fecha_modificacion date,
    primary key (Cliente_ID, Perro_ID),
    foreign key (Cliente_ID) references Clientes(ID),
    foreign key (Perro_ID) references Perros(ID)
);


CREATE TABLE Solicitud_adopcion (
    Cliente_ID number,
    Perro_ID number,
    Fecha_alta date,
    Fecha_modificacion date,
    Estado varchar(20) check (Estado in ('Aceptada', 'Denegada','Pendiente')),
    primary key (Cliente_ID, Perro_ID),
    foreign key (Cliente_ID) references Clientes(ID),
    foreign key (Perro_ID) references Perros(ID)
);




create sequence cliente_seq
    start with 1
    increment by 1;

create sequence usuario_seq
    start with 1  -- Inicia la secuencia desde 1
    increment by 1;  -- Incrementa de 1 en 1
    
    CREATE SEQUENCE perros_seq
START WITH 1
INCREMENT BY 1;

INSERT INTO Protectoras (CIF, Nombre, Telefono, Correo_Electronico, Calle, Ciudad, Redes_Sociales, Fecha_alta, Fecha_modificacion)
VALUES ('P12345678', 'Protectora Amigo Fiel', '123456789', 'amigo@fiel.com', 'Calle Luna 10', 'Sevilla', 'instagram.com/amigofiel', SYSDATE, SYSDATE);


INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Labrador', 'Max', TO_DATE('2019-05-01', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'max.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Beagle', 'Luna', TO_DATE('2020-03-15', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'luna.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Golden Retriever', 'Rocky', TO_DATE('2018-11-10', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'rocky.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Pastor AlemÃ¡n', 'Nala', TO_DATE('2017-02-20', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'nala.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Pitbull', 'Toby', TO_DATE('2021-07-22', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'toby.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Labrador', 'Maya', TO_DATE('2022-01-05', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'maya.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Beagle', 'Simba', TO_DATE('2016-06-12', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'simba.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Golden Retriever', 'Coco', TO_DATE('2019-09-30', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'coco.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Pastor AlemÃ¡n', 'Zeus', TO_DATE('2020-12-11', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'zeus.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Pitbull', 'Duna', TO_DATE('2021-08-08', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'duna.jpg');



create or replace trigger rolUsuarios
before insert on USUARIOS
for each row
begin
  if :NEW.ID_CLIENTES is not null then
    :NEW.ROL := 'CLIENTE';
  elsif :NEW.CIF_PROTECTORAS is not null then
    :NEW.ROL := 'PROTECTORA';
  else
    RAISE_APPLICATION_ERROR(-20001, 'Tiene que indicar or el idCLiente o el CIF de la protectora');
  end if;
>>>>>>> aba3533609a0ee3018d7f0bf12092f76f44dece6
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
