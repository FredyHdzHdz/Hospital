Create database hospital153991;

CREATE TABLE hospital153991.consultorio (
    idConsultorio INT AUTO_INCREMENT PRIMARY KEY,
    numeroConsultorio INT NOT NULL,
    piso INT NOT NULL,
    activo bit
);

CREATE TABLE hospital153991.especialidades (
    idEspecialidad INT AUTO_INCREMENT PRIMARY KEY,
    Especialidad varchar(50) NOT NULL,
    activo bit
);

CREATE TABLE hospital153991.doctores (
    idDoctor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50),
    idEspecialidad int,
    idConsultorio INT,
    FOREIGN KEY (idConsultorio) REFERENCES Consultorio(idConsultorio),
    FOREIGN KEY (idEspecialidad) REFERENCES especialidades(idEspecialidad),
    activo bit
    
);

CREATE TABLE hospital153991.horarios (
    idDoctor INT,
    FOREIGN KEY (idDoctor) REFERENCES doctores(idDoctor),
	dia INT,
    matutino_inicio time,
    matutino_fin time,
    vespertino_inicio time,
    vespertino_fin time,
    intervalo int,
    activo bit
);

create table hospital153991.agenda
(
IdAgenda INT AUTO_INCREMENT PRIMARY KEY,
fecha datetime,
idespecialidad int,
nombre varchar(50),
segundo_nombre varchar(50),
paterno varchar(50),
materno varchar(50),
email varchar(50),
telefono varchar(50),
observaciones text,
creado datetime
);

INSERT INTO hospital153991.consultorio (numeroConsultorio, piso,activo) VALUES
(1, 1,1),
(2, 2,1),
(3, 1,1),
(4, 3,1),
(5, 2,1);

select*from hospital153991.consultorio;

INSERT INTO hospital153991.especialidades (Especialidad,activo) VALUES
('Cardiología',1),
('Dermatología',1),
('Neurología',1),
('Pediatría',1),
('Gastroenterología',1);

select idEspecialidad,Especialidad,activo from hospital153991.especialidades where idespecialidad=1;

INSERT INTO hospital153991.doctores (nombre, apellido_paterno, apellido_materno, idEspecialidad, idConsultorio,activo) VALUES
('Juan', 'García', 'López', 1, 1,1),
('María', 'Martínez', 'Rodríguez', 2, 2,1),
('Carlos', 'López', 'Hernández', 3, 1,1),
('Ana', 'Gómez', 'Fernández', 4, 3,1),
('Pedro', 'Díaz', 'Sánchez', 5, 2,1);

select*from hospital153991.doctores;

INSERT INTO hospital153991.horarios (idDoctor, dia, matutino_inicio, matutino_fin, vespertino_inicio, vespertino_fin, intervalo, activo) VALUES
(1, 1, '08:00:00', '12:00:00', '14:00:00', '18:00:00', 15, 1),
(2, 2, '09:00:00', '13:00:00', '15:00:00', '19:00:00', 20, 1),
(3, 3, '10:00:00', '14:00:00', '16:00:00', '20:00:00', 30, 1),
(4, 4, '08:30:00', '12:30:00', '14:30:00', '18:30:00', 15, 1),
(5, 5, '09:30:00', '13:30:00', '15:30:00', '19:30:00', 20, 1),
(1, 2, '08:00:00', '13:00:00', '15:00:00', '19:00:00', 15, 1),
(1, 3, '08:00:00', '14:00:00', '16:00:00', '20:00:00', 15, 1),
(1, 4, '08:00:00', '12:30:00', '14:30:00', '18:30:00', 15, 1),
(1, 5, '08:00:00', '13:30:00', '15:30:00', '19:30:00', 15, 1);

select *from hospital153991.horarios;