/*
MySQL Data Transfer
Source Host: localhost
Source Database: db_epp_app_example
Target Host: localhost
Target Database: db_epp_app_example
Date: 23/06/2022 05:33:57 p. m.
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sge_classification
-- ----------------------------
CREATE TABLE `sge_classification` (
  `id_classification` int(11) NOT NULL AUTO_INCREMENT,
  `id_enterprise` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id_classification`),
  KEY `fk_sge_clasificacion_sge_empresa1_idx` (`id_enterprise`),
  CONSTRAINT `fk_sge_clasificacion_sge_empresa1` FOREIGN KEY (`id_enterprise`) REFERENCES `sge_enterprise` (`id_enterprise`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sge_employee
-- ----------------------------
CREATE TABLE `sge_employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `id_enterprise` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `names` varchar(100) NOT NULL,
  `last_names` varchar(100) NOT NULL,
  `id_type_identity` int(11) NOT NULL,
  `document_number` varchar(20) NOT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `area` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `signature_url` varchar(400) DEFAULT NULL,
  `fingerprint` varchar(200) DEFAULT NULL,
  `state` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_employee`),
  UNIQUE KEY `nro_documento_UNIQUE` (`document_number`,`id_enterprise`),
  KEY `fk_sge_trabajador_sge_tipo_identidad1_idx` (`id_type_identity`),
  KEY `fk_sge_trabajador_sge_empresa1_idx` (`id_enterprise`),
  CONSTRAINT `fk_sge_trabajador_sge_tipo_identidad1` FOREIGN KEY (`id_type_identity`) REFERENCES `sge_type_identity` (`id_type_identity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sge_trabajador_sge_empresa1` FOREIGN KEY (`id_enterprise`) REFERENCES `sge_enterprise` (`id_enterprise`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sge_enterprise
-- ----------------------------
CREATE TABLE `sge_enterprise` (
  `id_enterprise` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `name` varchar(200) NOT NULL,
  `id_type_identity` int(11) NOT NULL,
  `document_number` varchar(20) NOT NULL,
  `image_url` varchar(300) DEFAULT NULL,
  `address` varchar(400) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id_enterprise`),
  UNIQUE KEY `nro_documento_UNIQUE` (`document_number`),
  UNIQUE KEY `codigo_UNIQUE` (`code`),
  KEY `fk_sge_empresa_sge_tipo_identificacion_idx` (`id_type_identity`),
  CONSTRAINT `fk_sge_empresa_sge_tipo_identificacion` FOREIGN KEY (`id_type_identity`) REFERENCES `sge_type_identity` (`id_type_identity`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sge_equipment
-- ----------------------------
CREATE TABLE `sge_equipment` (
  `id_equipment` int(11) NOT NULL AUTO_INCREMENT,
  `id_classification` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `features` varchar(400) DEFAULT NULL,
  `image_url` varchar(400) DEFAULT NULL,
  `cost` decimal(11,2) NOT NULL,
  `minimum_stock` decimal(11,2) NOT NULL,
  `current_stock` decimal(11,2) NOT NULL,
  `state_stock` enum('A','M','S') NOT NULL DEFAULT 'A',
  `state` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_equipment`),
  KEY `fk_sge_equipo_sge_clasificacion1_idx` (`id_classification`),
  CONSTRAINT `fk_sge_equipo_sge_clasificacion1` FOREIGN KEY (`id_classification`) REFERENCES `sge_classification` (`id_classification`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sge_profile
-- ----------------------------
CREATE TABLE `sge_profile` (
  `id_profile` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `state` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_profile`),
  UNIQUE KEY `codigo_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sge_type_identity
-- ----------------------------
CREATE TABLE `sge_type_identity` (
  `id_type_identity` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` enum('N','J','A') NOT NULL DEFAULT 'N',
  `state` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_type_identity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sge_user
-- ----------------------------
CREATE TABLE `sge_user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_enterprise` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `id_profile` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pass` varchar(200) NOT NULL,
  `id_employee` int(11) DEFAULT NULL,
  `state` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_user`),
  KEY `fk_sge_usuario_sge_empresa1_idx` (`id_enterprise`),
  KEY `fk_sge_usuario_sge_perfil1_idx` (`id_profile`),
  KEY `fk_sge_usuario_sge_trabajador1_idx` (`id_employee`),
  CONSTRAINT `fk_sge_usuario_sge_empresa1` FOREIGN KEY (`id_enterprise`) REFERENCES `sge_enterprise` (`id_enterprise`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sge_usuario_sge_perfil1` FOREIGN KEY (`id_profile`) REFERENCES `sge_profile` (`id_profile`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sge_usuario_sge_trabajador1` FOREIGN KEY (`id_employee`) REFERENCES `sge_employee` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sge_classification` VALUES ('1', '1', 'CLS-000001', 'CABEZA', '');
INSERT INTO `sge_classification` VALUES ('2', '1', 'CLS-000002', 'SISTEMA AUDITIVO', '');
INSERT INTO `sge_classification` VALUES ('3', '1', 'CLS-000003', 'SISTEMA VISUAL', '');
INSERT INTO `sge_classification` VALUES ('4', '1', 'CLS-000004', 'SISTEMA RESPIRATORIO', '');
INSERT INTO `sge_classification` VALUES ('5', '1', 'CLS-000005', 'MANOS Y ANTEBRAZOS', '');
INSERT INTO `sge_classification` VALUES ('6', '1', 'CLS-000006', 'EXTREMIDADES SUPERIORES 1', '');
INSERT INTO `sge_classification` VALUES ('7', '1', 'CLS-000007', 'TRONCO Y CUERPO ENTERO', '');
INSERT INTO `sge_classification` VALUES ('8', '1', 'CLS-000008', 'PROTECCIÓN COLECTIVA', '');
INSERT INTO `sge_classification` VALUES ('9', '1', 'CLS-000009', 'OTROS', '');
INSERT INTO `sge_employee` VALUES ('1', '1', 'TRB-000001', 'Jose Paolo', 'Guerrero Gonzales', '1', '40399795', 'Operario', 'Construcción', '', '', '', '', '');
INSERT INTO `sge_employee` VALUES ('2', '1', 'TRB-000002', 'Renato Fabricio', 'Tapia Cortijo', '1', '40050425', 'Gerente', 'Sostenibilidad', '', '', '', '', '');
INSERT INTO `sge_employee` VALUES ('3', '1', 'TRB-000003', 'Edison Michael', 'Flores Peralta', '1', '43089376', 'Op. Rastrillero', 'Asfalto', '', '', '', '', '');
INSERT INTO `sge_enterprise` VALUES ('1', '20123456789', 'COMPANY TEST S.A.C.', '3', '20123456789', null, null, null, null, '');
INSERT INTO `sge_equipment` VALUES ('1', '1', 'EPP-000001', 'GUANTES', null, null, '2.50', '100.00', '100.00', 'M', '');
INSERT INTO `sge_profile` VALUES ('1', 'SAD', 'Super Administrador', 'Super Administrador con acceso total.', '');
INSERT INTO `sge_profile` VALUES ('2', 'ADM', 'Administrador', 'Administrador General', '');
INSERT INTO `sge_profile` VALUES ('3', 'JEF', 'Jefatura', 'Jefe de Área', '');
INSERT INTO `sge_profile` VALUES ('4', 'USR', 'Usuario Final', 'Usuario final de Aplicación', '');
INSERT INTO `sge_type_identity` VALUES ('1', 'DNI', 'Documento Nacional de Identidad', 'A', '');
INSERT INTO `sge_type_identity` VALUES ('2', 'CEX', 'Carnet de Extranjería', 'A', '');
INSERT INTO `sge_type_identity` VALUES ('3', 'RUC', 'Registro Único de Contribuyente', 'J', '');
INSERT INTO `sge_user` VALUES ('1', '1', 'USER-000001', '1', 'ADMIN', '$2a$10$/Ia/eMcjzyRqFiSk2jUZ6e0revqHD.wtU062gVJHs2QVf3YwGxQKO', null, '');
INSERT INTO `sge_user` VALUES ('2', '1', 'USER-000002', '2', 'JGUERRERO', '$2a$10$/Ia/eMcjzyRqFiSk2jUZ6e0revqHD.wtU062gVJHs2QVf3YwGxQKO', '1', '');
