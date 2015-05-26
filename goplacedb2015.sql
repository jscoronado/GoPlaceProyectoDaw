-- phpMyAdmin SQL Dump
-- version 4.3.11.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tiempo de generación: 26-05-2015 a las 14:11:15
-- Versión del servidor: 5.5.42
-- Versión de PHP: 5.4.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `goplacedb2015`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amistad`
--

CREATE TABLE IF NOT EXISTS `amistad` (
  `id` int(11) NOT NULL COMMENT 'id Amistad',
  `id_usuario_1` int(11) DEFAULT NULL COMMENT 'Usuario',
  `id_usuario_2` int(11) DEFAULT NULL COMMENT 'Usuario Amigo'
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `amistad`
--

INSERT INTO `amistad` (`id`, `id_usuario_1`, `id_usuario_2`) VALUES
(1, 1, 1),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 2, 1),
(7, 2, 2),
(8, 2, 3),
(9, 3, 1),
(10, 3, 3),
(12, 4, 1),
(13, 4, 4),
(14, 4, 5),
(15, 5, 1),
(16, 5, 5),
(17, 5, 4),
(21, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

CREATE TABLE IF NOT EXISTS `asistencia` (
  `id` int(11) NOT NULL COMMENT 'id Asistencia',
  `id_usuario` int(11) DEFAULT NULL COMMENT 'Usuario',
  `id_publicacion` int(11) DEFAULT NULL COMMENT 'Evento'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `asistencia`
--

INSERT INTO `asistencia` (`id`, `id_usuario`, `id_publicacion`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 2, 1),
(6, 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE IF NOT EXISTS `ciudad` (
  `id` int(11) NOT NULL COMMENT 'Id',
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Nombre Ciudad'
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id`, `nombre`) VALUES
(15, 'A Coruña'),
(1, 'Álava'),
(2, 'Albacete'),
(3, 'Alicante'),
(4, 'Almería'),
(33, 'Asturias'),
(5, 'Ávila'),
(6, 'Badajoz'),
(7, 'Baleares'),
(8, 'Barcelona'),
(48, 'Bizkaia'),
(9, 'Burgos'),
(10, 'Cáceres'),
(11, 'Cádiz'),
(39, 'Cantabria'),
(12, 'Castellón'),
(51, 'Ceuta'),
(13, 'Ciudad Real'),
(14, 'Córdoba'),
(16, 'Cuenca'),
(20, 'Gipuzkoa'),
(17, 'Girona'),
(18, 'Granada'),
(19, 'Guadalajara'),
(21, 'Huelva'),
(22, 'Huesca'),
(23, 'Jaén'),
(26, 'La Rioja'),
(35, 'Las Palmas'),
(24, 'León'),
(25, 'Lleida'),
(27, 'Lugo'),
(28, 'Madrid'),
(29, 'Málaga'),
(52, 'Melilla'),
(30, 'Murcia'),
(31, 'Navarra'),
(32, 'Ourense'),
(34, 'Palencia'),
(36, 'Pontevedra'),
(37, 'Salamanca'),
(38, 'Santa Cruz de Tenerife'),
(40, 'Segovia'),
(41, 'Sevilla'),
(42, 'Soria'),
(43, 'Tarragona'),
(44, 'Teruel'),
(45, 'Toledo'),
(46, 'Valencia'),
(47, 'Valladolid'),
(49, 'Zamora'),
(50, 'Zaragoza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE IF NOT EXISTS `comentario` (
  `id` int(11) NOT NULL COMMENT 'Id Comentario',
  `coment` varchar(255) DEFAULT NULL COMMENT 'Comentario',
  `id_usuario` int(11) DEFAULT NULL COMMENT 'Id Usuario',
  `id_publicacion` int(11) DEFAULT NULL COMMENT 'Id Evento',
  `fechacomentario` datetime DEFAULT NULL COMMENT 'Fecha Comentario'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`id`, `coment`, `id_usuario`, `id_publicacion`, `fechacomentario`) VALUES
(1, 'Bienvenidos a GoPlace! :)', 1, 1, '2015-04-01 00:00:00'),
(2, 'Descubrir los planes que crean vuestros amigos y los que hayan en tu ciudad!', 1, 1, '2015-04-02 00:00:00'),
(3, 'Como me gusta GoPlace :)', 2, 1, '2015-05-15 11:40:25'),
(4, 'No me lo pierdo :)', 3, 1, '2015-05-02 08:00:00'),
(5, 'Que guapo!', 4, 2, '2015-05-15 00:00:00'),
(6, 'Me apunto! jejeje', 2, 4, '2015-05-31 10:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objeto`
--

CREATE TABLE IF NOT EXISTS `objeto` (
  `id` int(6) NOT NULL COMMENT 'ID Objeto',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripción'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `objeto`
--

INSERT INTO `objeto` (`id`, `descripcion`) VALUES
(1, 'amistad'),
(2, 'ciudad'),
(3, 'objeto'),
(4, 'operacion'),
(5, 'permiso'),
(6, 'publicacion'),
(7, 'tipooperacion'),
(8, 'tipopublicacion'),
(9, 'tipousuario'),
(10, 'usuario'),
(11, 'comentario'),
(12, 'asistencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operacion`
--

CREATE TABLE IF NOT EXISTS `operacion` (
  `id` int(6) NOT NULL COMMENT 'ID Operación',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripción',
  `id_objeto` int(6) DEFAULT NULL COMMENT 'ID Objeto',
  `id_tipooperacion` int(6) DEFAULT NULL COMMENT 'ID Tipo Operación'
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `operacion`
--

INSERT INTO `operacion` (`id`, `descripcion`, `id_objeto`, `id_tipooperacion`) VALUES
(1, 'get', 1, 1),
(2, 'getaggregateviewone', 1, 1),
(3, 'getprettycolumns', 1, 1),
(4, 'getcolumns', 1, 1),
(5, 'getpage', 1, 1),
(6, 'getpages', 1, 1),
(7, 'getregisters', 1, 1),
(8, 'getaggregateviewsome', 1, 1),
(9, 'remove', 1, 2),
(10, 'set', 1, 2),
(11, 'updateOne', 1, 2),
(23, 'get', 2, 1),
(24, 'getaggregateviewone', 2, 1),
(25, 'getprettycolumns', 2, 1),
(26, 'getcolumns', 2, 1),
(27, 'getpage', 2, 1),
(28, 'getpages', 2, 1),
(29, 'getregisters', 2, 1),
(30, 'getaggregateviewsome', 2, 1),
(31, 'remove', 2, 2),
(32, 'set', 2, 2),
(33, 'updateOne', 2, 2),
(34, 'get', 3, 1),
(35, 'getaggregateviewone', 3, 1),
(36, 'getprettycolumns', 3, 1),
(37, 'getcolumns', 3, 1),
(38, 'getpage', 3, 1),
(39, 'getpages', 3, 1),
(40, 'getregisters', 3, 1),
(41, 'getaggregateviewsome', 3, 1),
(42, 'remove', 3, 2),
(43, 'set', 3, 2),
(44, 'updateOne', 3, 2),
(45, 'get', 4, 1),
(46, 'getaggregateviewone', 4, 1),
(47, 'getprettycolumns', 4, 1),
(48, 'getcolumns', 4, 1),
(49, 'getpage', 4, 1),
(50, 'getpages', 4, 1),
(51, 'getregisters', 4, 1),
(52, 'getaggregateviewsome', 4, 1),
(53, 'remove', 4, 2),
(54, 'set', 4, 2),
(55, 'updateOne', 4, 2),
(56, 'get', 5, 1),
(57, 'getaggregateviewone', 5, 1),
(58, 'getprettycolumns', 5, 1),
(59, 'getcolumns', 5, 1),
(60, 'getpage', 5, 1),
(61, 'getpages', 5, 1),
(62, 'getregisters', 5, 1),
(63, 'getaggregateviewsome', 5, 1),
(64, 'remove', 5, 2),
(65, 'set', 5, 2),
(66, 'updateOne', 5, 2),
(67, 'get', 6, 1),
(68, 'getaggregateviewone', 6, 1),
(69, 'getprettycolumns', 6, 1),
(70, 'getcolumns', 6, 1),
(71, 'getpage', 6, 1),
(72, 'getpages', 6, 1),
(73, 'getregisters', 6, 1),
(74, 'getaggregateviewsome', 6, 1),
(75, 'remove', 6, 2),
(76, 'set', 6, 2),
(77, 'updateOne', 6, 2),
(78, 'get', 7, 1),
(79, 'getaggregateviewone', 7, 1),
(80, 'getprettycolumns', 7, 1),
(81, 'getcolumns', 7, 1),
(82, 'getpage', 7, 1),
(83, 'getpages', 7, 1),
(84, 'getregisters', 7, 1),
(85, 'getaggregateviewsome', 7, 1),
(86, 'remove', 7, 2),
(87, 'set', 7, 2),
(88, 'updateOne', 7, 2),
(89, 'get', 8, 1),
(90, 'getaggregateviewone', 8, 1),
(91, 'getprettycolumns', 8, 1),
(92, 'getcolumns', 8, 1),
(93, 'getpage', 8, 1),
(94, 'getpages', 8, 1),
(95, 'getregisters', 8, 1),
(96, 'getaggregateviewsome', 8, 1),
(97, 'remove', 8, 2),
(98, 'set', 8, 2),
(99, 'updateOne', 8, 2),
(100, 'get', 9, 1),
(101, 'getaggregateviewone', 9, 1),
(102, 'getprettycolumns', 9, 1),
(103, 'getcolumns', 9, 1),
(104, 'getpage', 9, 1),
(105, 'getpages', 9, 1),
(106, 'getregisters', 9, 1),
(107, 'getaggregateviewsome', 9, 1),
(108, 'remove', 9, 2),
(109, 'set', 9, 2),
(110, 'updateOne', 9, 2),
(111, 'get', 10, 1),
(112, 'getaggregateviewone', 10, 1),
(113, 'getprettycolumns', 10, 1),
(114, 'getcolumns', 10, 1),
(115, 'getpage', 10, 1),
(116, 'getpages', 10, 1),
(117, 'getregisters', 10, 1),
(118, 'getaggregateviewsome', 10, 1),
(119, 'remove', 10, 2),
(120, 'set', 10, 2),
(121, 'updateOne', 10, 2),
(122, 'get', 11, 1),
(123, 'getaggregateviewone', 11, 1),
(124, 'getprettycolumns', 11, 1),
(125, 'getcolumns', 11, 1),
(126, 'getpage', 11, 1),
(127, 'getpages', 11, 1),
(128, 'getregisters', 11, 1),
(129, 'getaggregateviewsome', 11, 1),
(130, 'remove', 11, 2),
(131, 'set', 11, 2),
(132, 'updateOne', 11, 2),
(133, 'get', 12, 1),
(134, 'getaggregateviewone', 12, 1),
(135, 'getprettycolumns', 12, 1),
(136, 'getcolumns', 12, 1),
(137, 'getpage', 12, 1),
(138, 'getpages', 12, 1),
(139, 'getregisters', 12, 1),
(140, 'getaggregateviewsome', 12, 1),
(141, 'remove', 12, 2),
(142, 'set', 12, 2),
(143, 'updateOne', 12, 2),
(200, 'agregaramigo', 1, 2),
(201, 'removeamigo', 1, 2),
(202, 'existeamigo', 1, 1),
(203, 'seguirevento', 12, 2),
(204, 'dejarevento', 12, 2),
(205, 'existeasistencia', 12, 1),
(206, 'getcomentarios', 11, 1),
(207, 'getpagescomentarios', 11, 1),
(208, 'geteventos', 6, 1),
(209, 'getpageseventos', 6, 1),
(210, 'duplicate', 6, 2),
(211, 'getpage', 12, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permiso`
--

CREATE TABLE IF NOT EXISTS `permiso` (
  `id` int(6) NOT NULL COMMENT 'ID Permiso',
  `id_tipousuario` int(6) DEFAULT NULL COMMENT 'ID Tipo de usuario',
  `id_tipooperacion` int(6) DEFAULT NULL COMMENT 'ID Tipo Operación',
  `permitido` tinyint(1) DEFAULT NULL COMMENT 'Permitido'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `permiso`
--

INSERT INTO `permiso` (`id`, `id_tipousuario`, `id_tipooperacion`, `permitido`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 2, 1, 1),
(4, 2, 2, 1),
(6, 2, 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicacion`
--

CREATE TABLE IF NOT EXISTS `publicacion` (
  `id` int(11) NOT NULL COMMENT 'Id Publicacion',
  `titulo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Titulo',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripcion',
  `id_usuario` int(11) DEFAULT NULL COMMENT 'Id Usuario',
  `id_ciudad` int(11) DEFAULT NULL COMMENT 'Id Ciudad',
  `id_tipopublicacion` int(11) DEFAULT NULL COMMENT 'Id Tipo Publicacion',
  `fechapub` datetime DEFAULT NULL COMMENT 'Fecha Publicacion'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `publicacion`
--

INSERT INTO `publicacion` (`id`, `titulo`, `descripcion`, `id_usuario`, `id_ciudad`, `id_tipopublicacion`, `fechapub`) VALUES
(1, 'Bienvenidos a GoPlace !', 'Una red social donde poder buscar, crear y comentar con tus amigos los planes en tu ciudad!', 1, 46, 2, '2015-02-10 00:00:00'),
(2, 'Fiestas Tavernes Blanques', '¡Que empiece las fiestas de Tavernes! En esta sección encontrarás toda la información actualizada sobre las Fallas de Tavernes Blanques 2015. Información programación de actos, premios, noticias de actualidad, reportajes sobre la fiesta…', 2, 46, 1, '2015-06-01 10:40:13'),
(3, 'Vamos a echar chismes al salir', 'Si es que soy un escandalo!', 4, 37, 2, '2015-02-10 19:57:29'),
(4, 'Cada vez somos mas en GoPlace', 'Uniros!', 1, 46, 1, '2015-02-11 01:11:36'),
(5, 'Yee un lol o que?', 'Venga animaros jeje', 5, 46, 3, '2015-02-11 10:15:26'),
(6, 'Pero esto que eeeees?', 'Si solo estaba de paso aisse', 3, 12, 3, '2015-02-11 16:46:56'),
(7, 'Fiestas Tavernes Blanques 2015', 'oidfhsokdjf', 1, 15, 1, '2015-05-13 23:30:41'),
(8, 'sdfsdf', 'sdfsdf', 2, 11, 1, '2015-05-18 23:31:40'),
(9, 'asdasd', 'asdasd', 1, 15, 1, '2015-05-25 17:30:29'),
(10, 'asdasdd', 'asd', 1, 15, 1, '2015-05-26 21:43:01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipooperacion`
--

CREATE TABLE IF NOT EXISTS `tipooperacion` (
  `id` int(6) NOT NULL COMMENT 'Identificador',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripción'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipooperacion`
--

INSERT INTO `tipooperacion` (`id`, `descripcion`) VALUES
(1, 'lectura'),
(2, 'escritura'),
(3, 'alta'),
(4, 'modificación'),
(5, 'borrado'),
(6, 'rellenar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipopublicacion`
--

CREATE TABLE IF NOT EXISTS `tipopublicacion` (
  `id` int(11) NOT NULL COMMENT 'Id',
  `tipo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Tipo Publicacion'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipopublicacion`
--

INSERT INTO `tipopublicacion` (`id`, `tipo`) VALUES
(1, 'Informativo'),
(2, 'Eventos'),
(3, 'Ofertas'),
(4, 'Actividades');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE IF NOT EXISTS `tipousuario` (
  `id` int(11) NOT NULL COMMENT 'Id Tipo Usuario',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripcion'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `descripcion`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL COMMENT 'Id Usuario',
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Nombre',
  `apellidos` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Apellidos',
  `correo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Correo',
  `login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Usuario',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Password',
  `fecha` date DEFAULT NULL COMMENT 'Fecha Nacimiento',
  `id_ciudad` int(11) DEFAULT NULL COMMENT 'Id Ciudad',
  `genero` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Genero',
  `estado` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Estado',
  `id_tipousuario` int(11) DEFAULT NULL COMMENT 'Tipo Usuario',
  `imagen` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Imagen Usuario'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Usuarios de GoPlace';

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `correo`, `login`, `password`, `fecha`, `id_ciudad`, `genero`, `estado`, `id_tipousuario`, `imagen`) VALUES
(1, 'Jose Miguel', 'Coronado Aroca', 'joshco@gmail.com', 'josh', 'coronado', '1994-09-29', 46, 'M', 'Soy el admin de GoPlace!', 1, '<img src="/images/flecha-negra.png" width="150" />'),
(2, 'Aida', 'Sanchez Martinez', 'aidasm@gmail.com', 'aida', 'sanchez', '2015-02-22', 46, 'M', '', 2, NULL),
(3, 'Alejandro', 'Perez Vidal', 'kelo@gmail.com', 'kelo', 'kelo', '2015-02-04', 46, 'H', 'In da nigga Go Place', 2, NULL),
(4, 'Cristina', 'Sanchez Picado', 'crisloka21@hotmail.com', 'cris', 'cris', '2015-02-02', 37, 'M', '', 2, NULL),
(5, 'Javi', 'Alonso', 'javalonaso@gmail.com', 'javi', 'javi', '2015-02-02', 46, 'H', '', 2, NULL),
(6, 'Carlos', 'Cuenca', 'carlos34@gmail.com', 'carlos', 'carlos', '2015-05-28', 2, 'H', '', 2, NULL),
(7, 'Arlette', 'Vidal', 'info@arlettevidal.com', 'arlette', 'vidal', '2015-01-01', 2, 'M', '', 2, NULL),
(8, 'David', 'Domingo', 'domingo@gmail.com', 'david', 'domingo', '2015-05-26', 29, 'H', '', 2, '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `amistad`
--
ALTER TABLE `amistad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `operacion`
--
ALTER TABLE `operacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `permiso`
--
ALTER TABLE `permiso`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipooperacion`
--
ALTER TABLE `tipooperacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipopublicacion`
--
ALTER TABLE `tipopublicacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `descripcion` (`descripcion`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `correo` (`correo`,`login`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `amistad`
--
ALTER TABLE `amistad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id Amistad',AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id Asistencia',AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id Comentario',AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `operacion`
--
ALTER TABLE `operacion`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'ID Operación',AUTO_INCREMENT=212;
--
-- AUTO_INCREMENT de la tabla `permiso`
--
ALTER TABLE `permiso`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'ID Permiso',AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id Publicacion',AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `tipooperacion`
--
ALTER TABLE `tipooperacion`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Identificador',AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `tipopublicacion`
--
ALTER TABLE `tipopublicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id Tipo Usuario',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id Usuario',AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
