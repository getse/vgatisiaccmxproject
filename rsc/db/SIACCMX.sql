DROP SCHEMA INFRA;

CREATE SCHEMA INFRA AUTHORIZATION INFRA;

---

CREATE TABLE INFRA.USUARIOS (ID_USUARIO INT NOT NULL,
CVE_USUARIO VARCHAR(100) UNIQUE NOT NULL, 
PASSWORD VARCHAR(50) NOT NULL,
ESTATUS BOOLEAN);

ALTER TABLE INFRA.USUARIOS ALTER COLUMN  ID_USUARIO INT AUTO_INCREMENT;
ALTER TABLE INFRA.USUARIOS ADD CONSTRAINT USUARIOS_PK PRIMARY KEY (ID_USUARIO);

---

CREATE TABLE INFRA.REL_ROLES (
CVE_ROL VARCHAR(25) NOT NULL,
CVE_USUARIO VARCHAR(100) UNIQUE NOT NULL);

ALTER TABLE INFRA.REL_ROLES ADD CONSTRAINT CVEUSU_FK FOREIGN KEY (CVE_USUARIO) REFERENCES INFRA.USUARIOS (CVE_USUARIO);

---

CREATE TABLE INFRA.ADMINISTRACION_CCMX( ID_USUARIO INT NOT NULL);

ALTER TABLE INFRA.ADMINISTRACION_CCMX ADD CONSTRAINT ADMON_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS (ID_USUARIO);

---

CREATE TABLE INFRA.TRACTORAS (
ID_USUARIO INT NOT NULL, 
ID_TRACTORA_PADRE INT NOT NULL,
ID_USUARIO_PADRE INT NOT NULL,
EMPRESA VARCHAR(100) NOT NULL,
NOMBRE_CONTACTO VARCHAR(60) NOT NULL,
APP_PATERNO VARCHAR(60) NOT NULL,
APP_MATERNO VARCHAR(60),
CORREO_ELECTRONICO VARCHAR(100) NOT NULL,
PUESTO VARCHAR(100));

ALTER TABLE INFRA.TRACTORAS ADD CONSTRAINT TRACTUSU_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS(ID_USUARIO);
ALTER TABLE INFRA.TRACTORAS ADD CONSTRAINT TRACTORAADMON_FK FOREIGN KEY (ID_USUARIO_PADRE) REFERENCES INFRA.ADMINISTRACION_CCMX(ID_USUARIO);

---

CREATE TABLE INFRA.PYMES (
ID_USUARIO INT NOT NULL,
ID_USUARIO_PADRE INT NOT NULL,
PERSONALIDAD_JURIDICA VARCHAR(50),
RFC VARCHAR(20),
CORREO_ELECTRONICO VARCHAR(100),
NOMBRE_COMERCIAL VARCHAR(150),
NOMBRE_FISCAL VARCHAR(100),
NUMERO_EMPLEADOS INT,
MENSAJE_VENTAS VARCHAR(500),
PAGINA_WEB VARCHAR(100),
VENTAS_ANUALES VARCHAR(50),
B_PRIMER_NIVEL BOOLEAN,
B_SEGUNDO_NIVEL BOOLEAN,
B_TERCER_NIVEL BOOLEAN,
B_DIPLOMADO_CCMX_UNO BOOLEAN,
B_DIPLOMADO_CCMX_DOS BOOLEAN,
B_DIPLOMADO_CCMX_TRES BOOLEAN,
B_DIPLOMADO_CCMX_CUATRO BOOLEAN,
B_RECIBE_REQUERIMIENTOS_COMPRA BOOLEAN,
CALIFICACION INT,
COMENTARIO VARCHAR(150),
B_SERVICIOS_CCMX_DIPLOMADOS BOOLEAN,
B_SERVICIOS_CCMX_CONSULTORIA BOOLEAN,
CEDULA VARCHAR(10));

ALTER TABLE INFRA.PYMES ADD CONSTRAINT PYMEUSU_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS(ID_USUARIO);
ALTER TABLE INFRA.PYMES ADD CONSTRAINT PYMEADMON_FK FOREIGN KEY (ID_USUARIO_PADRE) REFERENCES INFRA.ADMINISTRACION_CCMX(ID_USUARIO);
 
 ---
 
CREATE TABLE INFRA.PRODUCTOS (
ID_PRODUCTO INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL,
PRODUCTO VARCHAR(100));
 
ALTER TABLE INFRA.PRODUCTOS ADD CONSTRAINT PRODUCTOS_PK PRIMARY KEY (ID_PRODUCTO);
ALTER TABLE INFRA.PRODUCTOS ADD CONSTRAINT PRODUCPYME_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.PYMES(ID_USUARIO);
 
---
 
CREATE TABLE INFRA.TELEFONOS (
ID_TELEFONO INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL,
TELEFONO VARCHAR(30));

ALTER TABLE INFRA.TELEFONOS ADD CONSTRAINT TELEFONOS_PK PRIMARY KEY (ID_TELEFONO);

---

CREATE TABLE INFRA.CONTACTOS (
ID_CONTACTO INT AUTO_INCREMENT NOT NULL, 
ID_USUARIO INT NOT NULL,
TIPO VARCHAR(100),
NOMBRE VARCHAR(60),
APELLIDO_PATERNO VARCHAR(60),
APELLIDO_MATERNO VARCHAR(60),
CORREO_ELECTRONICO VARCHAR(100),
B_PRINCIPAL BOOLEAN,
TELEFONO VARCHAR(30));

ALTER TABLE INFRA.CONTACTOS ADD CONSTRAINT CONTACTOS_PK PRIMARY KEY (ID_CONTACTO);
ALTER TABLE INFRA.CONTACTOS ADD CONSTRAINT CONTACTPYME_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.PYMES(ID_USUARIO);

---

CREATE TABLE INFRA.REL_PYMES_TRACTORAS (
ID_PYME_TRACTORA INT NOT NULL,
ID_USUARIO_TRACTORA INT NOT NULL,
ID_USUARIO_PYME INT NOT NULL,
CALIFICACION INT,
COMENTARIO VARCHAR(150));

ALTER TABLE INFRA.REL_PYMES_TRACTORAS ALTER COLUMN ID_PYME_TRACTORA INT AUTO_INCREMENT;
ALTER TABLE INFRA.REL_PYMES_TRACTORAS ADD CONSTRAINT RPYTR_FK FOREIGN KEY (ID_USUARIO_TRACTORA) REFERENCES INFRA.TRACTORAS (ID_USUARIO);
ALTER TABLE INFRA.REL_PYMES_TRACTORAS ADD CONSTRAINT RPYPY_FK FOREIGN KEY (ID_USUARIO_PYME) REFERENCES INFRA.PYMES (ID_USUARIO);

---

CREATE TABLE INFRA.CAT_INDICADORES_TRACTORA (
ID_INDICADOR INT NOT NULL,
INDICADOR VARCHAR(100) NOT NULL,
DESCRIPCION VARCHAR(150) NOT NULL,
FRECUENCIA VARCHAR(100) NOT NULL);

ALTER TABLE INFRA.CAT_INDICADORES_TRACTORA ALTER COLUMN ID_INDICADOR INT AUTO_INCREMENT;

---

CREATE TABLE INFRA.INDICADORES (
ID_INDICADOR INT AUTO_INCREMENT NOT NULL,
ID_PYME INT,
ID_PYME_TRACTORA INT,
INGRESOS_ANTES INT,
INGRESOS_DESPUES INT,
CLIENTES_ANTES INT,
CLIENTES_DESPUES INT,
EMPLEADOS_ANTES INT,
EMPLEADOS_DESPUES INT,
EGRESOS_ANTES INT,
EGRESOS_DESPUES INT,
ID_INDICADOR_TRACTORA INT, 
RESULTADO_CALCULO VARCHAR(100),
PERIODO_REF_MES VARCHAR(20),
PERIODO_REF_ANIO INT);

ALTER TABLE INFRA.INDICADORES ADD CONSTRAINT INDICADORES_PK PRIMARY KEY (ID_INDICADOR);
ALTER TABLE INFRA.INDICADORES ADD CONSTRAINT INDICADORESPYME_FK FOREIGN KEY (ID_PYME) REFERENCES INFRA.PYMES(ID_USUARIO);
ALTER TABLE INFRA.INDICADORES ADD CONSTRAINT INDICADORESRELPYMETRACTORA_FK FOREIGN KEY (ID_PYME_TRACTORA) REFERENCES INFRA.REL_PYMES_TRACTORAS(ID_PYME_TRACTORA);
ALTER TABLE INFRA.INDICADORES ADD CONSTRAINT INDICADORESCATALOGOTRACTORA_FK FOREIGN KEY (ID_INDICADOR_TRACTORA) REFERENCES INFRA.CAT_INDICADORES_TRACTORA(ID_INDICADOR);

---
 
CREATE TABLE INFRA.REQUERIMIENTOS (
ID_REQUERIMIENTO INT AUTO_INCREMENT NOT NULL,
ID_TRACTORA INT NOT NULL,
PRODUCTO VARCHAR(500) NOT NULL,
TIPO_PRODUCTO VARCHAR(500) NOT NULL,
CVE_SCIAN INT NOT NULL,
DESCRIPCION VARCHAR(4000),
FECHA_SUMINISTRO DATE,
B_INDEFINIDO BOOLEAN,
B_VARIAS_FECHAS BOOLEAN,
B_CONTINUO_F_SUMINISTRO BOOLEAN,
DETALLE_VARIAS_FECHAS VARCHAR(100),
LUGAR_SUMINISTRO VARCHAR(400),
B_CONTADO BOOLEAN,
B_CREDITO BOOLEAN,
B_QUINCE BOOLEAN,
B_TREINTA BOOLEAN,
B_SESENTA BOOLEAN,
B_NOVENTA BOOLEAN,
B_OTRO BOOLEAN,
OTRAS_CONDICIONES VARCHAR(500),
REQUISITOS_ADICIONALES VARCHAR(500),
FECHA_EXPIRA DATE,
B_CONTINUO_F_EXPIRA BOOLEAN);

ALTER TABLE INFRA.REQUERIMIENTOS ADD CONSTRAINT REQPK PRIMARY KEY (ID_REQUERIMIENTO);
ALTER TABLE INFRA.REQUERIMIENTOS ADD CONSTRAINT REQTRACT_FK FOREIGN KEY (ID_TRACTORA) REFERENCES INFRA.TRACTORAS (ID_USUARIO);

---

CREATE TABLE INFRA.DOMICILIOS (
ID_DOMICILIO INT AUTO_INCREMENT NOT NULL,
CALLE VARCHAR(50) NOT NULL,
NUM_EXT VARCHAR(20) NOT NULL,
NUM_INT VARCHAR(20),
PISO VARCHAR(20),
COLONIA VARCHAR(50),
DELEGACION VARCHAR(50),
ESTADO VARCHAR(25),
CODIGO_POSTAL VARCHAR(5));

ALTER TABLE INFRA.DOMICILIOS ADD CONSTRAINT DOMI_PK PRIMARY KEY (ID_DOMICILIO);

---

CREATE TABLE INFRA.COORDINADORES_DIPLOMADOS (
ID_USUARIO INT NOT NULL,
ID_USUARIO_PADRE INT NOT NULL);

ALTER TABLE INFRA.COORDINADORES_DIPLOMADOS ADD CONSTRAINT COO_ADMOCCMX_FK FOREIGN KEY (ID_USUARIO_PADRE) REFERENCES INFRA.ADMINISTRACION_CCMX(ID_USUARIO);
ALTER TABLE INFRA.COORDINADORES_DIPLOMADOS ADD CONSTRAINT COO_USU_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS (ID_USUARIO);

--- 

CREATE TABLE INFRA.DIPLOMADOS (
ID_DIPLOMADO INT AUTO_INCREMENT NOT NULL,
TEMA VARCHAR(100),
GENERACION INT,
YEAR INT);

ALTER TABLE INFRA.DIPLOMADOS ADD CONSTRAINT DIPLOM_PK PRIMARY KEY (ID_DIPLOMADO);

---

CREATE TABLE INFRA.SERVICIOS_DIPLOMADO (
ID_SERVICIOS_DIPLOMADO INT AUTO_INCREMENT NOT NULL,
ID_DIPLOMADO INT NOT NULL,
ID_USUARIO INT NOT NULL);

ALTER TABLE INFRA.SERVICIOS_DIPLOMADO ADD CONSTRAINT SERV_DIPL_UN UNIQUE(ID_USUARIO,ID_DIPLOMADO);
ALTER TABLE INFRA.SERVICIOS_DIPLOMADO ADD CONSTRAINT SERVD_DIP_FK FOREIGN KEY (ID_DIPLOMADO) REFERENCES INFRA.DIPLOMADOS (ID_DIPLOMADO);
ALTER TABLE INFRA.SERVICIOS_DIPLOMADO ADD CONSTRAINT SERVD_PYM_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.PYMES (ID_USUARIO);

---

CREATE TABLE INFRA.ASISTENTES (
ID_ASISTENTE INT AUTO_INCREMENT NOT NULL,
ID_SERVICIOS_DIPLOMADO INT NOT NULL,
NOMBRE VARCHAR(60) NOT NULL,
APP_PATERNO VARCHAR(60) NOT NULL,
APP_MATERNO VARCHAR(60),
FACTURA BOOLEAN,
PAGO BOOLEAN,
CARGO VARCHAR(50),
NUMERO_PAGO VARCHAR(50),
TELEFONO VARCHAR(50),
CORREO_ELECTRONICO VARCHAR(50),
C1 BOOLEAN,
C2 BOOLEAN,
C3 BOOLEAN,
C4 BOOLEAN);

ALTER TABLE INFRA.ASISTENTES ADD CONSTRAINT ASIST_PK PRIMARY KEY (ID_ASISTENTE);
ALTER TABLE INFRA.ASISTENTES ADD CONSTRAINT ASIST_SERVD_FK FOREIGN KEY (ID_SERVICIOS_DIPLOMADO) REFERENCES SERVICIOS_DIPLOMADO(ID_SERVICIOS_DIPLOMADO);

---

CREATE TABLE INFRA.ENCUESTAS(
ID_ASISTENTE INT NOT NULL,
RESPUESTA1 FLOAT,
RESPUESTA2 FLOAT,
RESPUESTA3 VARCHAR(400),
RESPUESTA4 VARCHAR(400),
RESPUESTA5 FLOAT,
RESPUESTA6 FLOAT,
RESPUESTA7 VARCHAR(400),
RESPUESTA8 VARCHAR(400),
RESPUESTA9 VARCHAR(400),
RETROALIMENTACION VARCHAR(400));

ALTER TABLE INFRA.ENCUESTAS ADD CONSTRAINT ENCUE_PK PRIMARY KEY (ID_ASISTENTE);
ALTER TABLE INFRA.ENCUESTAS ADD CONSTRAINT ENC_ASIS_FK FOREIGN KEY (ID_ASISTENTE) REFERENCES INFRA.ASISTENTES(ID_ASISTENTE);

---

CREATE TABLE INFRA.SESIONES(
ID_SESION INT AUTO_INCREMENT NOT NULL ,
ID_DIPLOMADO INT NOT NULL,
SESION INT,
EXPOSITOR VARCHAR(50),
ID_DOMICILIO INT,
SALA VARCHAR(40),
FECHA DATETIME,
INSTRUCTOR VARCHAR (80),
INFORMACION VARCHAR (1000));

ALTER TABLE INFRA.SESIONES ADD CONSTRAINT SESION_PK PRIMARY KEY (ID_SESION);
ALTER TABLE INFRA.SESIONES ADD CONSTRAINT SE_DI_FK FOREIGN KEY (ID_DIPLOMADO ) REFERENCES INFRA.DIPLOMADOS(ID_DIPLOMADO );
ALTER TABLE INFRA.SESIONES ADD CONSTRAINT SE_DOM_FK FOREIGN KEY (ID_DOMICILIO) REFERENCES INFRA.DOMICILIOS(ID_DOMICILIO );

---

CREATE TABLE INFRA.ASISTENCIAS(
ID_SESION INT NOT NULL,
ID_ASISTENTE INT NOT NULL,
ASISTENCIA BOOLEAN);

ALTER TABLE INFRA.ASISTENCIAS ADD CONSTRAINT AS_ASISTNT_FK FOREIGN KEY (ID_ASISTENTE) REFERENCES INFRA.ASISTENTES(ID_ASISTENTE);
ALTER TABLE INFRA.ASISTENCIAS ADD CONSTRAINT AS_SESION_FK FOREIGN KEY (ID_SESION) REFERENCES INFRA.SESIONES(ID_SESION);
ALTER TABLE INFRA.ASISTENCIAS ADD CONSTRAINT AS_UN UNIQUE(ID_SESION ,ID_ASISTENTE);

---

CREATE TABLE INFRA.SERVICIOS_CONSULTORIA (
ID_CONSULTORIA INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL,
B_CONSULTORIA_20 BOOLEAN,
B_CONSULTORIA_40 BOOLEAN,
B_CONSULTORIA_60 BOOLEAN,
B_CONSULTORIA_80 BOOLEAN,
MENSAJE VARCHAR(500),
ESTATUS VARCHAR(40),
RECURSOS_HUMANOS_ANTES INT,
MERCADEO_ANTES INT,
FINANZAS_ANTES INT,
ADMINISTRACION_ANTES INT,
PROCESOS_ANTES INT,
RECURSOS_HUMANOS_DESPUES INT,
MERCADEO_DESPUES INT,
FINANZAS_DESPUES INT,
ADMINISTRACION_DESPUES INT,
PROCESOS_DESPUES INT,
DIPLOMADO_RECOMENDADO_1 INT,
DIPLOMADO_RECOMENDADO_2 INT,
FECHA_INICIO DATE,
FECHA_TERMINO DATE,
B_DIPLOMA BOOLEAN);

ALTER TABLE INFRA.SERVICIOS_CONSULTORIA ADD CONSTRAINT SERVC_PK PRIMARY KEY (ID_CONSULTORIA);
ALTER TABLE INFRA.SERVICIOS_CONSULTORIA ADD CONSTRAINT SERVC_PYM_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.PYMES (ID_USUARIO);

---

CREATE TABLE INFRA.CONSULTORAS (
ID_CONSULTORA INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL, 
ID_USUARIO_PADRE INT NOT NULL,
ID_CONSULTORA_PADRE INT NOT NULL,
EMPRESA VARCHAR(100),
NOMBRE_CONTACTO VARCHAR(60),
APP_PATERNO_CONTACTO VARCHAR(60),
APP_MATERNO_CONTACTO VARCHAR(60),
CORREO_ELECTRONICO_CONTACTO VARCHAR(100),
COSTO_ANTICIPO FLOAT,
COSTO_ABONO1 FLOAT,
COSTO_ABONO2 FLOAT,
COSTO_FINIQUITO FLOAT);

ALTER TABLE INFRA.CONSULTORAS ADD CONSTRAINT CONSULT_PK PRIMARY KEY (ID_CONSULTORA);
ALTER TABLE INFRA.CONSULTORAS ADD CONSTRAINT CONSULT_USU_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS (ID_USUARIO);
ALTER TABLE INFRA.CONSULTORAS ADD CONSTRAINT CONSULTADMON_FK FOREIGN KEY (ID_USUARIO_PADRE) REFERENCES INFRA.ADMINISTRACION_CCMX(ID_USUARIO);

---

CREATE TABLE INFRA.REL_CONSULTORIAS_CONSULTORA (
ID_CONSULTORIA INT NOT NULL,
ID_CONSULTORA INT NOT NULL);

ALTER TABLE INFRA.REL_CONSULTORIAS_CONSULTORA ADD CONSTRAINT RCC_SERVC_FK FOREIGN KEY (ID_CONSULTORIA) REFERENCES INFRA.SERVICIOS_CONSULTORIA (ID_CONSULTORIA);
ALTER TABLE INFRA.REL_CONSULTORIAS_CONSULTORA ADD CONSTRAINT RCC_CONSULT_FK FOREIGN KEY (ID_CONSULTORA) REFERENCES INFRA.CONSULTORAS (ID_CONSULTORA);

---

CREATE TABLE INFRA.REL_CONSULTORAS_PYME (
ID_USUARIO_PYME INT NOT NULL ,
ID_USUARIO_CONSULTOR INT NOT NULL);

ALTER TABLE INFRA.REL_CONSULTORAS_PYME ADD CONSTRAINT RCP_PYME_FK FOREIGN KEY (ID_USUARIO_PYME) REFERENCES INFRA.PYMES (ID_USUARIO);
ALTER TABLE INFRA.REL_CONSULTORAS_PYME ADD CONSTRAINT RCP_CONSULT_FK FOREIGN KEY (ID_USUARIO_CONSULTOR) REFERENCES INFRA.CONSULTORAS (ID_USUARIO);
ALTER TABLE INFRA.REL_CONSULTORAS_PYME ADD CONSTRAINT INFRA.REL_CONS_PYME_UNIQUE UNIQUE(ID_USUARIO_PYME, ID_USUARIO_CONSULTOR);

---

CREATE TABLE INFRA.CERTIFICACIONES (
ID_CERTIFICADO INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL,
CERTIFICACION VARCHAR(150),
INSTITUTO_CERTIFICADOR VARCHAR(100),
FECHA_CERTIFICACION DATE);

ALTER TABLE INFRA.CERTIFICACIONES ADD CONSTRAINT CERT_PK PRIMARY KEY (ID_CERTIFICADO);
ALTER TABLE INFRA.CERTIFICACIONES ADD CONSTRAINT CERT_PYM_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.PYMES (ID_USUARIO);

---

CREATE TABLE INFRA.FACTURAS (
ID_FACTURA VARCHAR(40) NOT NULL,
ID_USUARIO INT,
ESTATUS VARCHAR(20),
FECHA_PAGO DATE,
IMPORTE_TOTAL VARCHAR(50));

ALTER TABLE INFRA.FACTURAS ADD CONSTRAINT FACTURA_PK PRIMARY KEY(ID_FACTURA);
ALTER TABLE INFRA.FACTURAS ADD CONSTRAINT FACT_CONSULT_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.CONSULTORAS(ID_USUARIO);

---

CREATE TABLE INFRA.PAGOS (
ID_PAGO INT AUTO_INCREMENT NOT NULL,
ID_SERVICO_CONSULTORIA INT NOT NULL,
NUMERO VARCHAR(40) NOT NULL,
TIPO VARCHAR(40));

ALTER TABLE INFRA.PAGOS ADD CONSTRAINT PAGO_PK PRIMARY KEY (ID_PAGO);
ALTER TABLE INFRA.PAGOS ADD CONSTRAINT PAGO_SERV_FK FOREIGN KEY (ID_SERVICO_CONSULTORIA) REFERENCES INFRA.SERVICIOS_CONSULTORIA (ID_CONSULTORIA);
ALTER TABLE INFRA.PAGOS ADD CONSTRAINT PAGO_FACT_FK FOREIGN KEY (NUMERO) REFERENCES INFRA.FACTURAS(ID_FACTURA);
ALTER TABLE INFRA.PAGOS ADD CONSTRAINT PAGO_TIPO_SERV_UNIQUE UNIQUE(TIPO, ID_SERVICO_CONSULTORIA);

---

CREATE TABLE INFRA.RESPUESTAS (
ID_RESPUESTA INT AUTO_INCREMENT NOT NULL,
ID_PYME INT NOT NULL,
ID_REQUERIMIENTO INT NOT NULL,
INFORMACION VARCHAR(150),
MENSAJE_ENVIO VARCHAR(500));

ALTER TABLE INFRA.RESPUESTAS ADD CONSTRAINT RESP_PK PRIMARY KEY (ID_RESPUESTA);
ALTER TABLE INFRA.RESPUESTAS ADD CONSTRAINT RESP_REQ_FK FOREIGN KEY (ID_REQUERIMIENTO) REFERENCES INFRA.REQUERIMIENTOS (ID_REQUERIMIENTO);
ALTER TABLE INFRA.RESPUESTAS ADD CONSTRAINT RESP_PYM_FK FOREIGN KEY (ID_PYME) REFERENCES INFRA.PYMES (ID_USUARIO);

---

CREATE TABLE INFRA.ARCHIVOS (
ID_ARCHIVO INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT,
ID_REQUERIMIENTO INT,
ID_RESPUESTA INT,
ID_INDICADOR INT,
NOMBRE VARCHAR(250),
DESCRIPCION_ARCHIVO VARCHAR(100),
MIME VARCHAR(30),
TIPO VARCHAR(5),
CONTENIDO BLOB);

ALTER TABLE INFRA.ARCHIVOS ADD CONSTRAINT ARCH_PK PRIMARY KEY (ID_ARCHIVO);

---

CREATE TABLE INFRA.COORDINADORES_CONSULTORA (
ID_USUARIO INT NOT NULL,
ID_USUARIO_PADRE INT NOT NULL);

ALTER TABLE INFRA.COORDINADORES_CONSULTORA ADD CONSTRAINT COOC_ADMONCCMX_FK FOREIGN KEY (ID_USUARIO_PADRE) REFERENCES INFRA.ADMINISTRACION_CCMX (ID_USUARIO);
ALTER TABLE INFRA.COORDINADORES_CONSULTORA ADD CONSTRAINT COOC_USU_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS (ID_USUARIO);

---

CREATE TABLE INFRA.CLIENTES (
ID_CLIENTE INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL,
CLIENTE VARCHAR(100) NOT NULL,
PRODUCTOS_COMPRA VARCHAR(500),
ANIOS_PROVEEDOR VARCHAR(50),
MESES_PROVEEDOR VARCHAR(50));

ALTER TABLE INFRA.CLIENTES ADD CONSTRAINT CLT_PK PRIMARY KEY (ID_CLIENTE);
ALTER TABLE INFRA.CLIENTES ADD CONSTRAINT CLT_PYM_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.PYMES (ID_USUARIO);

---

CREATE TABLE INFRA.ESTADOS_VENTA (
ID_ESTADO_VENTA INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL,
ID_REQUERIMIENTO INT,
ESTADO_VENTA VARCHAR(25),
DESCRIPCION VARCHAR(250));

ALTER TABLE INFRA.ESTADOS_VENTA ADD CONSTRAINT EDO_CTA_PK PRIMARY KEY (ID_ESTADO_VENTA);
ALTER TABLE INFRA.ESTADOS_VENTA ADD CONSTRAINT EDOREQ_PYM_FK FOREIGN KEY (ID_REQUERIMIENTO) REFERENCES INFRA.REQUERIMIENTOS (ID_REQUERIMIENTO);
ALTER TABLE INFRA.ESTADOS_VENTA ADD CONSTRAINT EDOCTA_PYM_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS (ID_USUARIO);

---

CREATE TABLE INFRA.REL_DOMICILIOS_USUARIO (
ID_USUARIO INT NOT NULL,
ID_DOMICILIO INT NOT NULL);

ALTER TABLE INFRA.REL_DOMICILIOS_USUARIO ADD CONSTRAINT RDU_USU_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.USUARIOS (ID_USUARIO);
ALTER TABLE INFRA.REL_DOMICILIOS_USUARIO ADD CONSTRAINT RDU_DOMI_FK FOREIGN KEY (ID_DOMICILIO) REFERENCES INFRA.DOMICILIOS (ID_DOMICILIO);

---

CREATE TABLE INFRA.CAT_SCIAN_CCMX (
CVE_SCIAN INT PRIMARY KEY NOT NULL,
DESC_SCIAN VARCHAR(200)  NOT NULL,
BUSQUEDA VARCHAR(200),
CVE_NIVEL INT NOT NULL);

CREATE INDEX IDX_CAT_SCIAN_CCMX_DESC ON INFRA.CAT_SCIAN_CCMX(CVE_SCIAN, DESC_SCIAN, CVE_NIVEL);
CREATE INDEX IDX_CAT_SCIAN_CCMX_BUSQ ON INFRA.CAT_SCIAN_CCMX(CVE_SCIAN, DESC_SCIAN, BUSQUEDA, CVE_NIVEL);

---

CREATE TABLE INFRA.CATEGORIAS (
ID_CATEGORIA INT AUTO_INCREMENT NOT NULL,
ID_USUARIO INT NOT NULL,
CVE_SCIAN INT NOT NULL,
DESC_SCIAN VARCHAR(200));
 
ALTER TABLE INFRA.CATEGORIAS ADD CONSTRAINT CATEGORIAS_PK PRIMARY KEY (ID_CATEGORIA);
ALTER TABLE INFRA.CATEGORIAS ADD CONSTRAINT CATEGORIACAT_SCIAN_CCMX_FK FOREIGN KEY (CVE_SCIAN) REFERENCES INFRA.CAT_SCIAN_CCMX(CVE_SCIAN);
ALTER TABLE INFRA.CATEGORIAS ADD CONSTRAINT CATEGORIAPYME_FK FOREIGN KEY (ID_USUARIO) REFERENCES INFRA.PYMES(ID_USUARIO);

---

--- USUARIOS
INSERT INTO INFRA.USUARIOS (CVE_USUARIO, PASSWORD) VALUES ('admin.ccmx@mail.com', 'password');
INSERT INTO INFRA.ADMINISTRACION_CCMX VALUES (1);
INSERT INTO INFRA.USUARIOS (CVE_USUARIO, PASSWORD) VALUES ('coordinador.diplomado@mail.com', 'password');
INSERT INTO INFRA.COORDINADORES_DIPLOMADOS VALUES ( (SELECT MAX(ID_USUARIO) FROM INFRA.USUARIOS ),1);
INSERT INTO INFRA.USUARIOS (CVE_USUARIO, PASSWORD) VALUES ('coordinador.consultoria@mail.com', 'password');
INSERT INTO INFRA.COORDINADORES_CONSULTORA VALUES ( (SELECT MAX(ID_USUARIO) FROM INFRA.USUARIOS ),1);

--- ROLES
INSERT INTO INFRA.REL_ROLES (CVE_ROL, CVE_USUARIO) VALUES ('AdministradorCCMX', 'admin.ccmx@mail.com');
INSERT INTO INFRA.REL_ROLES (CVE_ROL, CVE_USUARIO) VALUES ('CoordinadorDiplomados', 'coordinador.diplomado@mail.com');
INSERT INTO INFRA.REL_ROLES (CVE_ROL, CVE_USUARIO) VALUES ('CoordinadorConsultoras', 'coordinador.consultoria@mail.com');

---

DROP USER VGATI;
CREATE USER VGATI PASSWORD 'passwordVGATI';

GRANT SELECT ON INFRA.USUARIOS TO VGATI;
GRANT SELECT ON INFRA.REL_ROLES TO VGATI;
GRANT SELECT ON INFRA.ADMINISTRACION_CCMX TO VGATI;
GRANT SELECT ON INFRA.TRACTORAS TO VGATI;
GRANT SELECT ON INFRA.PYMES TO VGATI;
GRANT SELECT ON INFRA.PRODUCTOS TO VGATI;
GRANT SELECT ON INFRA.TELEFONOS TO VGATI;
GRANT SELECT ON INFRA.CONTACTOS TO VGATI;
GRANT SELECT ON INFRA.REL_PYMES_TRACTORAS TO VGATI;
GRANT SELECT ON INFRA.CAT_INDICADORES_TRACTORA TO VGATI;
GRANT SELECT ON INFRA.INDICADORES TO VGATI;
GRANT SELECT ON INFRA.REQUERIMIENTOS TO VGATI;
GRANT SELECT ON INFRA.DOMICILIOS TO VGATI;
GRANT SELECT ON INFRA.COORDINADORES_DIPLOMADOS TO VGATI;
GRANT SELECT ON INFRA.DIPLOMADOS TO VGATI;
GRANT SELECT ON INFRA.SERVICIOS_DIPLOMADO TO VGATI;
GRANT SELECT ON INFRA.ASISTENTES TO VGATI;
GRANT SELECT ON INFRA.ENCUESTAS TO VGATI;
GRANT SELECT ON INFRA.SESIONES TO VGATI;
GRANT SELECT ON INFRA.ASISTENCIAS TO VGATI;
GRANT SELECT ON INFRA.SERVICIOS_CONSULTORIA TO VGATI;
GRANT SELECT ON INFRA.CONSULTORAS TO VGATI;
GRANT SELECT ON INFRA.REL_CONSULTORIAS_CONSULTORA TO VGATI;
GRANT SELECT ON INFRA.REL_CONSULTORAS_PYME TO VGATI;
GRANT SELECT ON INFRA.CERTIFICACIONES TO VGATI;
GRANT SELECT ON INFRA.FACTURAS TO VGATI;
GRANT SELECT ON INFRA.PAGOS TO VGATI;
GRANT SELECT ON INFRA.RESPUESTAS TO VGATI;
GRANT SELECT ON INFRA.ARCHIVOS TO VGATI;
GRANT SELECT ON INFRA.COORDINADORES_CONSULTORA TO VGATI;
GRANT SELECT ON INFRA.CLIENTES TO VGATI;
GRANT SELECT ON INFRA.ESTADOS_VENTA TO VGATI;
GRANT SELECT ON INFRA.REL_DOMICILIOS_USUARIO TO VGATI;
GRANT SELECT ON INFRA.CAT_SCIAN_CCMX TO VGATI;
GRANT SELECT ON INFRA.CATEGORIAS TO VGATI;