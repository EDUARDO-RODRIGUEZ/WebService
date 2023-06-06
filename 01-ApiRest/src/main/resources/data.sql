--Load Data Client
insert into cliente(ci,nombre,apellido) values('12345678SC','eduardo','rodriguez');
insert into cliente(ci,nombre,apellido) values('87654321SC','jose','diaz');
insert into cliente(ci,nombre,apellido) values('87651234LP','david','hernandez');

--load Data Banco
insert into banco (nombre) values('Banco Mercantil Santa Cruz');
insert into banco (nombre) values('Banco Economico');
insert into banco (nombre) values('Banco Nacional Bolivia');
insert into banco (nombre) values('Banco Bisa');

--load Data TipoCuenta
insert into tipo_cuenta (nombre,descripcion) values('Cuenta de Ahorro','Descripcion');
insert into tipo_cuenta (nombre,descripcion) values('Cuenta Corriente','Descripcion');

--load Data TipoMoneda
insert into tipo_movimiento (nombre) values('Deposito');
insert into tipo_movimiento (nombre) values('Retiro');

--load data Cuenta
--bcrypt:123 hash
insert into cuenta (nro,password,banco_id,cliente_ci,tipo_cuenta,saldo) values('1234567899876543','$2a$10$uS7OMvE1jpFtFeYNADvSvun1Ovb2SZYP//4qKJfNcbww078CGl3P6',1,'12345678SC',1,500);
insert into cuenta (nro,password,banco_id,cliente_ci,tipo_cuenta,saldo) values('2132139883123123','$2a$10$uS7OMvE1jpFtFeYNADvSvun1Ovb2SZYP//4qKJfNcbww078CGl3P6',1,'87654321SC',1,2000);
insert into cuenta (nro,password,banco_id,cliente_ci,tipo_cuenta,saldo) values('1234567887654321','$2a$10$uS7OMvE1jpFtFeYNADvSvun1Ovb2SZYP//4qKJfNcbww078CGl3P6',2,'87654321SC',1,500);

--load Data Movimiento
insert into movimiento (cuenta_nro,fecha,monto,tipo_movimiento) values('1234567899876543','2021-01-01 19:10',500,1);
insert into movimiento (cuenta_nro,fecha,monto,tipo_movimiento) values('2132139883123123','2021-02-01 12:00',2000,1);
insert into movimiento (cuenta_nro,fecha,monto,tipo_movimiento) values('1234567887654321','2021-03-01 08:15',1000,1);
insert into movimiento (cuenta_nro,fecha,monto,tipo_movimiento) values('1234567887654321','2021-04-01 08:15',500,2);

-- Begin Trigger
 create or replace function sp_updateSaldo()  returns  trigger
 as
 '
 declare
 deposito int=1;
 retiro int=2;
 begin
	 if (new.tipo_movimiento=deposito) then
	    update cuenta set saldo=saldo+new.monto where nro=new.cuenta_nro;
	 elsif (new.tipo_movimiento=retiro) then
	 	update cuenta set saldo=saldo-new.monto  where nro=new.cuenta_nro;
   	 end if;
	 return new;

 end
'language plpgsql;

 create trigger tr_updateSaldo after insert on movimiento
 for each row
 execute procedure sp_updateSaldo();
-- End Trigger

