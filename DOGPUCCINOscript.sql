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
    Foto varchar2(600),
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
    Cliente_ID NUMBER,
    Perro_ID NUMBER,
    Donacion VARCHAR2(50),
    Hora NUMBER,
    Fecha_cita DATE,
    Estado VARCHAR2(50)check (Estado in ('Aceptada', 'Denegada','Pendiente')),
    Fecha_alta DATE,
    Fecha_modificacion DATE,
    CONSTRAINT pk_reservan PRIMARY KEY (Cliente_ID, Perro_ID, Fecha_cita),
    CONSTRAINT fk_reservan_cliente FOREIGN KEY (Cliente_ID) REFERENCES Clientes(ID),
    CONSTRAINT fk_reservan_perro FOREIGN KEY (Perro_ID) REFERENCES Perros(ID)
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
    start with 1  
    increment by 1;  
CREATE SEQUENCE perros_seq
    START WITH 1
    INCREMENT BY 1;
    
create sequence patologias_seq
 START WITH 1
    INCREMENT BY 1;
    
    create sequence notificaciones_seq
 START WITH 1
    INCREMENT BY 1;




INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Labrador', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Pitbull', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Golden Retriever', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Beagle', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Pastor Aleman', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Bulldog Frances', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Pug', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Chihuahua', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Rottweiler', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Dalmata', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Doberman', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Border Collie', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Boxer', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Yorkshire Terrier', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Shih Tzu', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Cocker Spaniel', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Husky Siberiano', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Mastin Napolitano', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('San Bernardo', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Shar Pei', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Caniche', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Akita Inu', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Setter Irlandes', SYSDATE, SYSDATE);
INSERT INTO Razas (Tipo, Fecha_alta, Fecha_modificacion) VALUES ('Galgo Espa�ol', SYSDATE, SYSDATE);



INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (1, 'Displasia', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (2, 'Alergias', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (4, 'Epilepsia', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (5, 'Leishmaniosis', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (3, 'Otitis', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (6, 'Sarna', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (7, 'Parvovirus', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (8, 'Moquillo', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (9, 'Cataratas', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (10, 'Artritis', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (11, 'Obesidad', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (12, 'Dermatitis', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (13, 'Gastroenteritis', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (14, 'Problemas renales', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (15, 'Problemas cardiacos', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (16, 'Hipotiroidismo', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (17, 'Otitis cronica', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (18, 'Tumores', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (19, 'Anemia', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (20, 'Tos de las perreras', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (21, 'Conjuntivitis', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (22, 'Infeccion urinaria', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (23, 'Problemas dentales', SYSDATE, SYSDATE);
INSERT INTO Patologias (ID, Nombre, Fecha_alta, Fecha_modificacion) VALUES (24, 'Enfermedad periodontal', SYSDATE, SYSDATE);



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

create or replace trigger Notificacion_Adopcion
after insert on Solicitud_Adopcion
for each row
declare
    mensajeAdopcion varchar2(100);
    nombreCliente varchar2(20);
    nombrePerro varchar2(20);
    idUsuarioProtectora number;
    cifProtectora varchar2(20);
    tipoNotificacion varchar2(50) := 'Solicitud Adopción';
begin
    begin 
        select cif into cifProtectora
        from perros
        where id= : new.perro_id;
    end;
    begin 
        select id into idUsuarioProtectora
        from usuarios
        where cif_protectoras =: cifProtectora;
    end;
    begin
        select nombre into nombrePerro
        from perros
        where id= :new.perro_id;
    end;   
    begin
        select nombre into nombreCliente
        from clientes
        where id = :new.cliente_id;
    end;

    mensajeAdopcion := 'Nueva solicitud de adopcion: Perro: ' || nombrePerro || ', cliente: ' || nombreCliente;
    
    if idUsuarioProtectora is not null then
        insert into Notificaciones (id,tipo,mensaje,fecha_envio,usuario_id,fecha_alta,fecha_modificacion)
        values(notificaciones_seq.nextval,tipoNotificacion,mensajeAdopcion,SYSDATE,idUsuarioProtectora,SYSDATE,SYSDATE);
        
    end if;
end;
/


create or replace trigger Notificacion_Cita
AFTER INSERT ON Reservan
FOR EACH ROW
DECLARE
    mensajeCita        VARCHAR2(100);
    nombreCliente      VARCHAR2(20);
    nombrePerro        VARCHAR2(20);
    idUsuarioProtectora NUMBER;
    cifProtectora      VARCHAR2(20);
    tipoNotificacion   VARCHAR2(50) := 'Solicitud Cita';
begin
    begin
        select cif into cifProtectora
        from perros
        WHERE id = :NEW.perro_id;
    end;
    begin
        select id into idUsuarioProtectora
        from usuarios
        where cif_protectoras = cifProtectora;
    end;
    begin
        select nombre into nombrePerro
        from perros
        WHERE id = :NEW.perro_id;
    end;
    begin
        select nombre into nombreCliente
        from clientes
        WHERE id = :NEW.cliente_id;
    end;

    mensajeCita := 'Nueva solicitud de cita: Perro: ' || nombrePerro || ', Cliente: ' || nombreCliente;

    IF idUsuarioProtectora IS NOT NULL THEN
        INSERT INTO Notificaciones (id,tipo,mensaje,fecha_envio,usuario_id,fecha_alta,fecha_modificacion) 
        VALUES (notificaciones_seq.NEXTVAL,tipoNotificacion,mensajeCita,SYSDATE,idUsuarioProtectora,SYSDATE,SYSDATE);
    end IF;
end;
/


create or replace trigger trgNotificacionSolicitudAdopcion
after update on solicitud_adopcion
for each row
declare
    idUsuarioCliente usuarios.id%type;
    nombrePerro perros.nombre%type;
    tipo varchar2(50) := 'Respuesta Solicitud Adopcion';
    mensaje varchar2(500);
begin
    if :old.estado <> :new.estado and 
       (:new.estado = 'Aceptada' or :new.estado = 'Denegada') then

        select nombre into nombrePerro
        from perros
        where id = :new.perro_id;

        select id into idUsuarioCliente
        from usuarios
        where id_clientes = :new.cliente_id;

        mensaje := 'Tu solicitud de adopcion del perro "' || nombrePerro || '" ha sido ' || lower(:new.estado) || '.';

        insert into notificaciones (id, tipo, mensaje, fecha_Envio, usuario_Id, fecha_Alta, fecha_Modificacion)
        values (notificaciones_seq.nextval, tipo, mensaje, sysdate, idUsuarioCliente, sysdate, sysdate);

        if :new.estado = 'Aceptada' then
            update perros
            set adoptado = 'S',
                fecha_Modificacion = sysdate
            where id = :new.perro_id;
        end if;
    end if;
end;
/
create or replace trigger trgNotificacionReservan
after update on reservan
for each row
declare
    idUsuarioCliente usuarios.id%type;
    nombrePerro perros.nombre%type;
    tipo varchar2(50) := 'Respuesta Reserva Cita';
    mensaje varchar2(500);
begin
    if :old.estado <> :new.estado and 
       (:new.estado = 'Aceptada' or :new.estado = 'Denegada') then

        select nombre into nombrePerro
        from perros
        where id = :new.perro_id;

        select id into idUsuarioCliente
        from usuarios
        where id_clientes = :new.cliente_id;

        mensaje := 'Tu reserva para el perro "' || nombrePerro || '" ha sido ' || lower(:new.estado) || '.';

        insert into notificaciones (id, tipo, mensaje, fecha_Envio, usuario_Id, fecha_Alta, fecha_Modificacion)
        values (notificaciones_seq.nextval, tipo, mensaje, sysdate, idUsuarioCliente, sysdate, sysdate);

        if :new.estado = 'Aceptada' then
            update perros
            set adoptado = 'S',
                fecha_Modificacion = sysdate
            where id = :new.perro_id;
        end if;
    end if;
end;
/
















CREATE OR REPLACE PROCEDURE rechazar_solicitudes (
    idPerro IN NUMBER
)
IS
BEGIN
    UPDATE Solicitud_Adopcion
    SET Estado = 'Denegada',
        Fecha_modificacion = SYSDATE
    WHERE Perro_ID = idPerro
      AND Estado = 'Pendiente';

    COMMIT;
END;
/


CREATE OR REPLACE FUNCTION aceptar_solicitud (
    idCliente IN NUMBER,
    idPerro   IN NUMBER
) RETURN VARCHAR2
IS
BEGIN

    UPDATE Solicitud_Adopcion
    SET Estado = 'Aceptada',
        Fecha_modificacion = SYSDATE
    WHERE Cliente_ID = idCliente
      AND Perro_ID = idPerro;

    UPDATE Perros
    SET Adoptado = 'S',
        Fecha_modificacion = SYSDATE
    WHERE ID = idPerro;

    rechazar_solicitudes (idPerro);

    COMMIT;

    RETURN 'Solicitud aceptada y otras rechazadas correctamente';
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RETURN 'Error: ' || SQLERRM;
END;
/
 set SERVEROUTPUT on;
 
 DECLARE
    resultado VARCHAR2(1000); 
BEGIN
    resultado := aceptar_solicitud(241, 122);  
    DBMS_OUTPUT.PUT_LINE(resultado);     
END;
/




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
DROP TABLE Usuarios CASCADE CONSTRAINTS;

SELECT Fecha_envio, Mensaje
FROM Notificaciones
WHERE Usuario_id = 55 AND Tipo = 'Respuesta Reserva Cita'
ORDER BY Fecha_envio DESC;
