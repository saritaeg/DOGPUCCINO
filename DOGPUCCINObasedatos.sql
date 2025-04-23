
CREATE TABLE Clientes (
    ID number primary key,
    Nombre varchar2(20),
    Apellido1 varchar2(20),
    Apellido2 varchar2(20),
    Fecha_Nacimiento date,
    Telefono char(9),
    Calle varchar2(50),
    Ciudad varchar2(50),
    Correo_Electronico varchar2(50)
);

CREATE TABLE Usuarios (
    ID number primary key,
    Contraseña varchar2(15) not null,
    Rol varchar2(10) not null,
    foreign KEY (ID) references Clientes(ID) 
);

CREATE TABLE Protectoras (
    CIF varchar2(20) primary key,
    Nombre varchar2(100),
    Telefono char(9),
    Correo_Electronico varchar2(50),
    Calle varchar2(50),
    Ciudad varchar2(50),
    Redes_Sociales varchar2(100),
    ID_Usuario number,  -- Agregamos la columna ID_Usuario
    foreign KEY (ID_Usuario) references Usuarios(ID)
);



CREATE TABLE Perros (
    ID number primary key,
    Nombre varchar2(20),
    Fecha_Nacimiento date,
    Sexo char(1) check (Sexo in ('M', 'H')),
    Calle varchar2(50),
    Ciudad varchar2(50),
    Adoptado char(1) check (Adoptado in ('S', 'N')),
    CIF varchar2(20), 
    FOREIGN KEY (CIF) REFERENCES Protectoras(CIF) 
);


CREATE TABLE Notificaciones (
    ID number primary key,
    Tipo varchar2(50),
    Mensaje varchar2(500),
    Fecha_Envio date,
    Usuario_ID number,
    FOREIGN KEY (Usuario_ID) REFERENCES Usuarios(ID)
);


CREATE TABLE Patologias (
     ID number primary key, 
    NOMBRE varchar2(50)
);


CREATE TABLE Perros_Patologias (
    ID_Perros number,
    ID_Patologia number,
    Descripcion varchar(100),
    primary KEY (ID_Perros, ID_Patologia),
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
    primary key (Cliente_ID, Perro_ID),
    foreign key (Cliente_ID) references Clientes(ID),
    foreign key (Perro_ID) references Perros(ID)
);


CREATE TABLE Solicitud_adopcion (
    Cliente_ID number,
    Perro_ID number,
    primary key (Cliente_ID, Perro_ID),
    foreign key (Cliente_ID) references Clientes(ID),
    foreign key (Perro_ID) references Perros(ID)
);


CREATE TABLE Razas (
    Tipo varchar2(50) primary key,
    ID_Perros number,
    FOREIGN KEY (ID_Perros) REFERENCES Perros(ID)  
);

/*
    DROP TABLE CLIENTES;
    DROP TABLE NOTIFICACIONES;
    DROP TABLE PERROS;
    DROP TABLE PROTECTORAS;
    DROP TABLE RESERVAN;
    DROP TABLE SOLICITUD_ADOPCION;
    DROP TABLE USUARIOS;
    DROP TABLE patologias;
    drop table perros_patologias;
    drop table razas;
*/





