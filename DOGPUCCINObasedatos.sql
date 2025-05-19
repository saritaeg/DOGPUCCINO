/*Sara Espino Gallardo
Ruben Romero Aponte
Pablo Rodriguez Moray*/

CREATE TABLE Clientes (
    ID number primary key,
    Nombre varchar2(20),
    Apellido1 varchar2(20),
    Apellido2 varchar2(20),
    Fecha_Nacimiento date,
    Telefono char(9),
    Calle varchar2(50),
    Ciudad varchar2(50),
    Correo_Electronico varchar2(50) unique,
    Fecha_alta date,
    Fecha_modificacion date
);
CREATE TABLE Protectoras (
    CIF char(9) primary key,
    Nombre varchar2(100),
    Telefono char(9),
    Correo_Electronico varchar2(50) unique,
    Calle varchar2(50),
    Ciudad varchar2(50),
    Redes_Sociales varchar2(100),
    Fecha_alta date,
    Fecha_modificacion date
);

CREATE TABLE Usuarios (
    ID number primary key,
    ID_Clientes number unique,
    CIF_Protectoras char(9) unique,
    Contrasenia varchar2(60) not null,
    Rol varchar2(10) not null,
    Fecha_alta date,
    Fecha_modificacion date,
    foreign KEY (ID_Clientes) references Clientes(ID),
    foreign KEY (CIF_Protectoras) references Protectoras(CIF)
);
CREATE TABLE Razas (
    Tipo varchar2(50) primary key,
    Fecha_alta date,
    Fecha_modificacion date
);

CREATE TABLE Perros (
    ID number primary key,
    CIF char(9),
    Raza varchar2(50),
    Nombre varchar2(20),
    Fecha_Nacimiento date,
    Sexo char(1) check (Sexo in ('M', 'H')),
    Adoptado CHAR(1) DEFAULT 'N' CHECK (Adoptado IN ('S', 'N')),
    Fecha_alta date,
    Fecha_modificacion date,
    Foto varchar2(20),
    FOREIGN KEY (CIF) REFERENCES Protectoras(CIF),
    FOREIGN KEY (Raza) REFERENCES Razas(Tipo)  
);

CREATE TABLE Notificaciones (
    ID number primary key,
    Tipo varchar2(50),
    Mensaje varchar2(500),
    Fecha_Envio date,
    Usuario_ID number,
    Fecha_alta date,
    Fecha_modificacion date,
    FOREIGN KEY (Usuario_ID) REFERENCES Usuarios(ID)
);

CREATE TABLE Patologias (
     ID number primary key, 
    Nombre varchar2(50),
    Fecha_alta date,
    Fecha_modificacion date
);

CREATE TABLE Perros_Patologias (
    primary KEY (ID_Perros, ID_Patologia),
    ID_Perros number,
    ID_Patologia number,
    Descripcion varchar(100),
    Fecha_alta date,
    Fecha_modificacion date,
    FOREIGN KEY (ID_Perros) REFERENCES Perros(ID),
    FOREIGN KEY (ID_Patologia) REFERENCES Patologias(ID)
);

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
VALUES (perros_seq.NEXTVAL, '29551113T', 'Pastor Alemán', 'Nala', TO_DATE('2017-02-20', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'nala.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Pitbull', 'Toby', TO_DATE('2021-07-22', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'toby.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Labrador', 'Maya', TO_DATE('2022-01-05', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'maya.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Beagle', 'Simba', TO_DATE('2016-06-12', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'simba.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Golden Retriever', 'Coco', TO_DATE('2019-09-30', 'YYYY-MM-DD'), 'H', 'N', SYSDATE, SYSDATE, 'coco.jpg');

INSERT INTO Perros (ID, CIF, Raza, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Foto)
VALUES (perros_seq.NEXTVAL, '29551113T', 'Pastor Alemán', 'Zeus', TO_DATE('2020-12-11', 'YYYY-MM-DD'), 'M', 'N', SYSDATE, SYSDATE, 'zeus.jpg');

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
END;
/

INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Labrador', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Pitbull', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Golden Retriever', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Beagle', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Pastor Alemán', SYSDATE, SYSDATE);


INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (1, 'Displasia de cadera', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (2, 'Alergias cutáneas', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (3, 'Problemas cardíacos', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (4, 'Epilepsia', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (5, 'Leishmaniosis', SYSDATE, SYSDATE);



/*
   DROP TABLE solicitud_adopcion CASCADE CONSTRAINTS;
DROP TABLE reservan CASCADE CONSTRAINTS;
DROP TABLE perros_patologias CASCADE CONSTRAINTS;
DROP TABLE patologias CASCADE CONSTRAINTS;
DROP TABLE notificaciones CASCADE CONSTRAINTS;
DROP TABLE perros CASCADE CONSTRAINTS;
DROP TABLE razas CASCADE CONSTRAINTS;
DROP TABLE usuarios CASCADE CONSTRAINTS;
DROP TABLE protectoras CASCADE CONSTRAINTS;
DROP TABLE clientes CASCADE CONSTRAINTS;

*/
