INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ADMIN');
INSERT INTO carrera (nombre) VALUES ('Ingeniería en Sistemas');
INSERT INTO profesor (nombre, apellido) VALUES ('Juan', 'Pérez');
INSERT INTO materia (nombre, anio, cuatrimestre, profesor_id, carrera_id) VALUES ('Algoritmos', 1, 1, 1, 1);
INSERT INTO materia (nombre, anio, cuatrimestre, profesor_id, carrera_id) VALUES ('Estructuras de Datos', 1, 2, 1, 1);
INSERT INTO materia_correlativa (materia_id, correlativa_id) VALUES (2, 1);
INSERT INTO alumno (nombre, apellido) VALUES ('Ana', 'García');
INSERT INTO asignatura (estado, alumno_id, materia_id) VALUES ('LIBRE', 1, 1);
INSERT INTO asignatura (estado, alumno_id, materia_id) VALUES ('LIBRE', 1, 2);
