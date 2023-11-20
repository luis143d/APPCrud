use dbAlumnos2
create table dbAlumnos(
	codigo int identity (1,1) primary key not null,
	dni nvarchar(8),
	nombres nvarchar(50),
	apellidos nvarchar(50),
	edad int
)
select*from dbAlumnos;
insert into dbALUMNOS (dni,nombres,apellidos,edad)
values('77820920','luis','sinticala',21)
select*from dbAlumnos