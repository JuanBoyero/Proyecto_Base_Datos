USE EmpresaEnergiaCC;
-- luego ejecutás el script completo desde cero

INSERT INTO usuario(DIRECCION)VALUES
	("Costa Rica 961 PB"),
    ("Buenos Aires 645 P 8,D A"),
    ("Cervantes 522"),
    ("Jose Manuel Estrada 1170"),
    ("Los Granaderos 719"),
    ("San Martin 425");
SELECT * FROM usuario;    
    
INSERT INTO mtel(NRO_IDENTIFICATORIO, TELEFONO) VALUES
	(1, 1150505547 ),
    (1, 1163874530 ),
    (2, 3585412490 ),
    (2, 3585546280 ),
    (2, 3553689127 ),
    (3, 1129673198 ),
    (4, 2665236743 ),
    (5, 3485321876 ),
    (5, 3585764312 );
SELECT * FROM mtel;
    
INSERT INTO persona(NRO_IDENTIFICATORIO,DNI) VALUES
	(1, 40904759),
    (2, 42519027),
    (5, 37969369);
SELECT * FROM persona;    
    
INSERT INTO empresa(NRO_IDENTIFICATORIO,CUIT,CAP_INSTALADA) VALUES
    (3, 21312198290, 20000),
    (4, 23258901614, 10000);
SELECT * FROM empresa;

INSERT INTO empleado(NRO_IDENTIFICATORIO,NOMBRE,APELLIDO,SUELDO) VALUES
	(1,'Jose','Suarez', 1000000),
    (2,'Flavio','Mendoza',1200000);
SELECT * FROM empleado; 
 
INSERT INTO motivo(CODIGO,DESCRIPCION) VALUES
	(1, 'CORTE DE SERVICIO'),
    (2, 'BAJA TENSION'),
    (3, 'GARANTIA'),
    (4, 'PRECIO EXCESIVO'),
    (5, 'FALLA DE EQUIPO');
SELECT * FROM motivo; 
   
INSERT INTO material(CODIGO,DESCRIPCION) VALUES
	(1,"Acero"),
    (2,"Madera"),
    (3,"Cemento"),
    (4,"Vidrio"),
    (5,"Arena"),
    (6,"Ladrillos"),
    (7,"Metal");
SELECT * FROM material;
    
INSERT INTO reclamo(FECHA_RESOLUCION,FECHA_Y_HORA, NRO_IDENTIFICATORIO,CODIGO) VALUES
	('2025-10-14', '2025-10-14 08:21:00', 1, 2),
    ('2025-10-12', '2025-10-12 11:38:00' , 4,1),
    ('2025-09-17', '2025-09-17 12:54:00', 3, 3),
    ('2025-09-02', '2025-09-02 13:14:00', 3, 5),
    ('2025-08-17', '2025-08-17 09:00:00', 5, 4),
    ('2025-07-18', '2025-07-18 10:15:00', 1, 2),
    ('2025-07-15', '2025-07-13 11:24:00', 2, 1);
SELECT * FROM reclamo;
    
INSERT INTO llamado(NRO_RECLAMO,NRO_LLAMADO,FECHA_Y_HORA) VALUES
	(1,1,'2025-10-14 08:21:00'),
    (2,1,'2025-10-12 11:38:00'),
    (3,1,'2025-09-17 12:54:00'),
    (4,1,'2025-09-02 13:14:00'),
    (5,1,'2025-08-17 09:00:00'),
    (6,1,'2025-07-18 10:15:00'),
    (7,1,'2025-07-13 11:24:00');
SELECT * FROM llamado;

INSERT INTO derivado(NRO_IDENTIFICATORIO,NRO_RECLAMO) VALUES
	(1,1),
    (1,2),
    (1,3),
    (1,4),
    (2,5),
    (2,6),
    (2,7);    
SELECT * FROM derivado;

INSERT INTO requirio(NRO_RECLAMO,CODIGO,CANTIDAD) VALUES
	(1,7,200),
    (2,6,5000),
    (3,5,10000),
    (4,4,300),
    (5,3,20000),
    (6,2,500),
    (7,1,1000);	

SELECT * FROM requirio;

DELETE FROM reclamo
	WHERE NRO_RECLAMO=3;

SELECT * FROM auditoria_borrado_motivo;