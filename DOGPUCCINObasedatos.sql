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
    ID_Usuario number,
    Fecha_alta date,
    Fecha_modificacion date
);

CREATE TABLE Usuarios (
    ID number primary key,
    ID_Clientes number unique,
    CIF_Protectoras char(9) unique,
    Contrasenia varchar2(15) unique not null,
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
    Nombre varchar2(20),
    Fecha_Nacimiento date,
    Sexo char(1) check (Sexo in ('M', 'H')),
    Calle varchar2(50),
    Ciudad varchar2(50),
    Adoptado char(1) check (Adoptado in ('S', 'N')),
    CIF char(9), 
    Fecha_alta date,
    Fecha_modificacion date,
    Foto varchar2(20),
    FOREIGN KEY (CIF) REFERENCES Protectoras(CIF),
    Raza varchar2(50),
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

CREATE SEQUENCE cliente_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

SELECT trigger_name, status FROM user_triggers WHERE trigger_name = 'CONTROLCLIENTES';
ALTER TRIGGER CONTROLCLIENTES COMPILE;
SHOW ERRORS TRIGGER CONTROLCLIENTES;
SELECT * 
FROM all_triggers 
WHERE trigger_name = 'CONTROLCLIENTES';

CREATE OR REPLACE TRIGGER CONTROLCLIENTES
BEFORE INSERT ON CLIENTES
FOR EACH ROW
BEGIN
    -- Asignar la fecha actual a los campos de fecha de alta y fecha de modificación
    :NEW.Fecha_Alta := SYSDATE;
    :NEW.Fecha_Modificacion := SYSDATE;
    
    -- Otros posibles controles, como establecer valores predeterminados
    -- :NEW.CampoX := 'valor predeterminado';
END;
/
SELECT sequence_name
FROM user_sequences
WHERE sequence_name = 'USUARIO_SEQ';

CREATE SEQUENCE usuario_seq
START WITH 1  -- Inicia la secuencia desde 1
INCREMENT BY 1;  -- Incrementa de 1 en 1

SELECT cliente_seq.CURRVAL FROM DUAL;
SELECT cliente_seq.NEXTVAL FROM DUAL;

INSERT INTO Clientes (
    ID, Nombre, Apellido1, Apellido2, Fecha_Nacimiento, Telefono,
    Calle, Ciudad, Correo_Electronico, Fecha_Alta, Fecha_Modificacion
) VALUES (
    cliente_seq.NEXTVAL, :1, :2, :3, :4, :5, :6, :7, :8, :9, :10
);

CREATE OR REPLACE TRIGGER trg_usuarios_rol
BEFORE INSERT ON USUARIOS
FOR EACH ROW
BEGIN
  IF :NEW.ID_CLIENTES IS NOT NULL THEN
    :NEW.ROL := 'CLIENTE';
  ELSIF :NEW.CIF_PROTECTORAS IS NOT NULL THEN
    :NEW.ROL := 'PROTECTORA';
  ELSE
    RAISE_APPLICATION_ERROR(-20001, 'Debe especificar ID_CLIENTES o CIF_PROTECTORAS para asignar el rol.');
  END IF;
END;
/


/*
    DROP TABLE SOLICITUD_ADOPCION;
    DROP TABLE RESERVAN;
    DROP TABLE perros_patologias;
    DROP TABLE patologias;
    DROP TABLE notificaciones;
    DROP TABLE perros;
    DROP TABLE razas;
    DROP TABLE usuarios;
    drop table protectoras;
    drop table clientes;
    */
