-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tiempo de generación: 12-02-2015 a las 11:01:20
-- Versión del servidor: 5.5.39
-- Versión de PHP: 5.4.32

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
  `id_usuario1` int(11) DEFAULT NULL COMMENT 'Usuario',
  `id_usuario2` int(11) DEFAULT NULL COMMENT 'Usuario Amigo'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=18 ;

--
-- Volcado de datos para la tabla `amistad`
--

INSERT INTO `amistad` (`id`, `id_usuario1`, `id_usuario2`) VALUES
(1, 1, 1),
(2, 1, 2),
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
(17, 5, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE IF NOT EXISTS `ciudad` (
`id` int(11) NOT NULL COMMENT 'Id',
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Nombre Ciudad'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=53 ;

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
(10, 'usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operacion`
--

CREATE TABLE IF NOT EXISTS `operacion` (
`id` int(6) NOT NULL COMMENT 'ID Operación',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripción',
  `id_objeto` int(6) DEFAULT NULL COMMENT 'ID Objeto',
  `id_tipooperacion` int(6) DEFAULT NULL COMMENT 'ID Tipo Operación'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=460 ;

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
(144, 'get', 13, 1),
(145, 'getaggregateviewone', 13, 1),
(146, 'getprettycolumns', 13, 1),
(147, 'getcolumns', 13, 1),
(148, 'getpage', 13, 1),
(149, 'getpages', 13, 1),
(150, 'getregisters', 13, 1),
(151, 'getaggregateviewsome', 13, 1),
(152, 'remove', 13, 2),
(153, 'set', 13, 2),
(154, 'updateOne', 13, 2),
(155, 'get', 14, 1),
(156, 'getaggregateviewone', 14, 1),
(157, 'getprettycolumns', 14, 1),
(158, 'getcolumns', 14, 1),
(159, 'getpage', 14, 1),
(160, 'getpages', 14, 1),
(161, 'getregisters', 14, 1),
(162, 'getaggregateviewsome', 14, 1),
(163, 'remove', 14, 2),
(164, 'set', 14, 2),
(165, 'updateOne', 14, 2),
(166, 'get', 15, 1),
(167, 'getaggregateviewone', 15, 1),
(168, 'getprettycolumns', 15, 1),
(169, 'getcolumns', 15, 1),
(170, 'getpage', 15, 1),
(171, 'getpages', 15, 1),
(172, 'getregisters', 15, 1),
(173, 'getaggregateviewsome', 15, 1),
(174, 'remove', 15, 2),
(175, 'set', 15, 2),
(176, 'updateOne', 15, 2),
(177, 'get', 16, 1),
(178, 'getaggregateviewone', 16, 1),
(179, 'getprettycolumns', 16, 1),
(180, 'getcolumns', 16, 1),
(181, 'getpage', 16, 1),
(182, 'getpages', 16, 1),
(183, 'getregisters', 16, 1),
(184, 'getaggregateviewsome', 16, 1),
(185, 'remove', 16, 2),
(186, 'set', 16, 2),
(187, 'updateOne', 16, 2),
(188, 'get', 17, 1),
(189, 'getaggregateviewone', 17, 1),
(190, 'getprettycolumns', 17, 1),
(191, 'getcolumns', 17, 1),
(192, 'getpage', 17, 1),
(193, 'getpages', 17, 1),
(194, 'getregisters', 17, 1),
(195, 'getaggregateviewsome', 17, 1),
(196, 'remove', 17, 2),
(197, 'set', 17, 2),
(198, 'updateOne', 17, 2),
(199, 'get', 18, 1),
(200, 'getaggregateviewone', 18, 1),
(201, 'getprettycolumns', 18, 1),
(202, 'getcolumns', 18, 1),
(203, 'getpage', 18, 1),
(204, 'getpages', 18, 1),
(205, 'getregisters', 18, 1),
(206, 'getaggregateviewsome', 18, 1),
(207, 'remove', 18, 2),
(208, 'set', 18, 2),
(209, 'updateOne', 18, 2),
(210, 'get', 19, 1),
(211, 'getaggregateviewone', 19, 1),
(212, 'getprettycolumns', 19, 1),
(213, 'getcolumns', 19, 1),
(214, 'getpage', 19, 1),
(215, 'getpages', 19, 1),
(216, 'getregisters', 19, 1),
(217, 'getaggregateviewsome', 19, 1),
(218, 'remove', 19, 2),
(219, 'set', 19, 2),
(220, 'updateOne', 19, 2),
(221, 'get', 20, 1),
(222, 'getaggregateviewone', 20, 1),
(223, 'getprettycolumns', 20, 1),
(224, 'getcolumns', 20, 1),
(225, 'getpage', 20, 1),
(226, 'getpages', 20, 1),
(227, 'getregisters', 20, 1),
(228, 'getaggregateviewsome', 20, 1),
(229, 'remove', 20, 2),
(230, 'set', 20, 2),
(231, 'updateOne', 20, 2),
(232, 'get', 21, 1),
(233, 'getaggregateviewone', 21, 1),
(234, 'getprettycolumns', 21, 1),
(235, 'getcolumns', 21, 1),
(236, 'getpage', 21, 1),
(237, 'getpages', 21, 1),
(238, 'getregisters', 21, 1),
(239, 'getaggregateviewsome', 21, 1),
(240, 'remove', 21, 2),
(241, 'set', 21, 2),
(242, 'updateOne', 21, 2),
(243, 'get', 22, 1),
(244, 'getaggregateviewone', 22, 1),
(245, 'getprettycolumns', 22, 1),
(246, 'getcolumns', 22, 1),
(247, 'getpage', 22, 1),
(248, 'getpages', 22, 1),
(249, 'getregisters', 22, 1),
(250, 'getaggregateviewsome', 22, 1),
(251, 'remove', 22, 2),
(252, 'set', 22, 2),
(253, 'updateOne', 22, 2),
(254, 'get', 23, 1),
(255, 'getaggregateviewone', 23, 1),
(256, 'getprettycolumns', 23, 1),
(257, 'getcolumns', 23, 1),
(258, 'getpage', 23, 1),
(259, 'getpages', 23, 1),
(260, 'getregisters', 23, 1),
(261, 'getaggregateviewsome', 23, 1),
(262, 'remove', 23, 2),
(263, 'set', 23, 2),
(264, 'updateOne', 23, 2),
(265, 'get', 24, 1),
(266, 'getaggregateviewone', 24, 1),
(267, 'getprettycolumns', 24, 1),
(268, 'getcolumns', 24, 1),
(269, 'getpage', 24, 1),
(270, 'getpages', 24, 1),
(271, 'getregisters', 24, 1),
(272, 'getaggregateviewsome', 24, 1),
(273, 'remove', 24, 2),
(274, 'set', 24, 2),
(275, 'updateOne', 24, 2),
(276, 'get', 25, 1),
(277, 'getaggregateviewone', 25, 1),
(278, 'getprettycolumns', 25, 1),
(279, 'getcolumns', 25, 1),
(280, 'getpage', 25, 1),
(281, 'getpages', 25, 1),
(282, 'getregisters', 25, 1),
(283, 'getaggregateviewsome', 25, 1),
(284, 'remove', 25, 2),
(285, 'set', 25, 2),
(286, 'updateOne', 25, 2),
(287, 'get', 26, 1),
(288, 'getaggregateviewone', 26, 1),
(289, 'getprettycolumns', 26, 1),
(290, 'getcolumns', 26, 1),
(291, 'getpage', 26, 1),
(292, 'getpages', 26, 1),
(293, 'getregisters', 26, 1),
(294, 'getaggregateviewsome', 26, 1),
(295, 'remove', 26, 2),
(296, 'set', 26, 2),
(297, 'updateOne', 26, 2),
(298, 'get', 27, 1),
(299, 'getaggregateviewone', 27, 1),
(300, 'getprettycolumns', 27, 1),
(301, 'getcolumns', 27, 1),
(302, 'getpage', 27, 1),
(303, 'getpages', 27, 1),
(304, 'getregisters', 27, 1),
(305, 'getaggregateviewsome', 27, 1),
(306, 'remove', 27, 2),
(307, 'set', 27, 2),
(308, 'updateOne', 27, 2),
(309, 'get', 28, 1),
(310, 'getaggregateviewone', 28, 1),
(311, 'getprettycolumns', 28, 1),
(312, 'getcolumns', 28, 1),
(313, 'getpage', 28, 1),
(314, 'getpages', 28, 1),
(315, 'getregisters', 28, 1),
(316, 'getaggregateviewsome', 28, 1),
(317, 'remove', 28, 2),
(318, 'set', 28, 2),
(319, 'updateOne', 28, 2),
(320, 'get', 29, 1),
(321, 'getaggregateviewone', 29, 1),
(322, 'getprettycolumns', 29, 1),
(323, 'getcolumns', 29, 1),
(324, 'getpage', 29, 1),
(325, 'getpages', 29, 1),
(326, 'getregisters', 29, 1),
(327, 'getaggregateviewsome', 29, 1),
(328, 'remove', 29, 2),
(329, 'set', 29, 2),
(330, 'updateOne', 29, 2),
(331, 'get', 30, 1),
(332, 'getaggregateviewone', 30, 1),
(333, 'getprettycolumns', 30, 1),
(334, 'getcolumns', 30, 1),
(335, 'getpage', 30, 1),
(336, 'getpages', 30, 1),
(337, 'getregisters', 30, 1),
(338, 'getaggregateviewsome', 30, 1),
(339, 'remove', 30, 2),
(340, 'set', 30, 2),
(341, 'updateOne', 30, 2),
(342, 'get', 31, 1),
(343, 'getaggregateviewone', 31, 1),
(344, 'getprettycolumns', 31, 1),
(345, 'getcolumns', 31, 1),
(346, 'getpage', 31, 1),
(347, 'getpages', 31, 1),
(348, 'getregisters', 31, 1),
(349, 'getaggregateviewsome', 31, 1),
(350, 'remove', 31, 2),
(351, 'set', 31, 2),
(352, 'updateOne', 31, 2),
(353, 'get', 32, 1),
(354, 'getaggregateviewone', 32, 1),
(355, 'getprettycolumns', 32, 1),
(356, 'getcolumns', 32, 1),
(357, 'getpage', 32, 1),
(358, 'getpages', 32, 1),
(359, 'getregisters', 32, 1),
(360, 'getaggregateviewsome', 32, 1),
(361, 'remove', 32, 2),
(362, 'set', 32, 2),
(363, 'updateOne', 32, 2),
(364, 'get', 33, 1),
(365, 'getaggregateviewone', 33, 1),
(366, 'getprettycolumns', 33, 1),
(367, 'getcolumns', 33, 1),
(368, 'getpage', 33, 1),
(369, 'getpages', 33, 1),
(370, 'getregisters', 33, 1),
(371, 'getaggregateviewsome', 33, 1),
(372, 'remove', 33, 2),
(373, 'set', 33, 2),
(374, 'updateOne', 33, 2),
(375, 'get', 34, 1),
(376, 'getaggregateviewone', 34, 1),
(377, 'getprettycolumns', 34, 1),
(378, 'getcolumns', 34, 1),
(379, 'getpage', 34, 1),
(380, 'getpages', 34, 1),
(381, 'getregisters', 34, 1),
(382, 'getaggregateviewsome', 34, 1),
(383, 'remove', 34, 2),
(384, 'set', 34, 2),
(385, 'updateOne', 34, 2),
(386, 'get', 35, 1),
(387, 'getaggregateviewone', 35, 1),
(388, 'getprettycolumns', 35, 1),
(389, 'getcolumns', 35, 1),
(390, 'getpage', 35, 1),
(391, 'getpages', 35, 1),
(392, 'getregisters', 35, 1),
(393, 'getaggregateviewsome', 35, 1),
(394, 'remove', 35, 2),
(395, 'set', 35, 2),
(396, 'updateOne', 35, 2),
(397, 'get', 36, 1),
(398, 'getaggregateviewone', 36, 1),
(399, 'getprettycolumns', 36, 1),
(400, 'getcolumns', 36, 1),
(401, 'getpage', 36, 1),
(402, 'getpages', 36, 1),
(403, 'getregisters', 36, 1),
(404, 'getaggregateviewsome', 36, 1),
(405, 'remove', 36, 2),
(406, 'set', 36, 2),
(407, 'updateOne', 36, 2),
(408, 'get', 37, 1),
(409, 'getaggregateviewone', 37, 1),
(410, 'getprettycolumns', 37, 1),
(411, 'getcolumns', 37, 1),
(412, 'getpage', 37, 1),
(413, 'getpages', 37, 1),
(414, 'getregisters', 37, 1),
(415, 'getaggregateviewsome', 37, 1),
(416, 'remove', 37, 2),
(417, 'set', 37, 2),
(418, 'updateOne', 37, 2),
(419, 'get', 38, 1),
(420, 'getaggregateviewone', 38, 1),
(421, 'getprettycolumns', 38, 1),
(422, 'getcolumns', 38, 1),
(423, 'getpage', 38, 1),
(424, 'getpages', 38, 1),
(425, 'getregisters', 38, 1),
(426, 'getaggregateviewsome', 38, 1),
(427, 'remove', 38, 2),
(428, 'set', 38, 2),
(429, 'updateOne', 38, 2),
(430, 'get', 39, 1),
(431, 'getaggregateviewone', 39, 1),
(432, 'getprettycolumns', 39, 1),
(433, 'getcolumns', 39, 1),
(434, 'getpage', 39, 1),
(435, 'getpages', 39, 1),
(436, 'getregisters', 39, 1),
(437, 'getaggregateviewsome', 39, 1),
(438, 'remove', 39, 2),
(439, 'set', 39, 2),
(440, 'updateOne', 39, 2),
(441, 'get', 40, 1),
(442, 'getaggregateviewone', 40, 1),
(443, 'getprettycolumns', 40, 1),
(444, 'getcolumns', 40, 1),
(445, 'getpage', 40, 1),
(446, 'getpages', 40, 1),
(447, 'getregisters', 40, 1),
(448, 'getaggregateviewsome', 40, 1),
(449, 'remove', 40, 2),
(450, 'set', 40, 2),
(451, 'updateOne', 40, 2),
(452, 'getAllPreguntas', 6, 1),
(453, 'setForm', 27, 2),
(454, 'agregaramigo', 2, 2),
(455, 'removeamigo', 2, 2),
(456, 'existeamigo', 2, 1),
(457, 'duplicate', 26, 2),
(458, 'getcomentarios', 26, 1),
(459, 'getpagescomentarios', 26, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permiso`
--

CREATE TABLE IF NOT EXISTS `permiso` (
`id` int(6) NOT NULL COMMENT 'ID Permiso',
  `id_tipousuario` int(6) DEFAULT NULL COMMENT 'ID Tipo de usuario',
  `id_tipooperacion` int(6) DEFAULT NULL COMMENT 'ID Tipo Operación',
  `permitido` tinyint(1) DEFAULT NULL COMMENT 'Permitido'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

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
  `titulo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Titulo',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripcion',
  `id_usuario` int(11) DEFAULT NULL COMMENT 'Id Usuario',
  `id_ciudad` int(11) DEFAULT NULL COMMENT 'Id Ciudad',
  `id_tipopublicacion` int(11) DEFAULT NULL COMMENT 'Id Tipo Publicacion',
  `fechapub` datetime DEFAULT NULL COMMENT 'Fecha Publicacion'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `publicacion`
--

INSERT INTO `publicacion` (`id`, `titulo`, `descripcion`, `id_usuario`, `id_ciudad`, `id_tipopublicacion`, `fechapub`) VALUES
(1, 'Bienvenidos a GoPlace !', 'Una red social donde poder buscar, crear y comentar con tus amigos los planes en tu ciudad!', 1, 46, 1, '2015-02-10 00:00:00'),
(2, 'Me encanta GoPlace!', 'Puedo buscar cualquier plan para quedar con mis amigos :)', 2, 46, 1, '2015-02-10 10:40:13'),
(3, 'Vamos a echar chismes al salir de clase', 'Si es que soy un escandalo!', 4, 37, 2, '2015-02-10 19:57:29'),
(4, 'Cada vez somos mas en GoPlace', 'Uniros!', 1, 46, 1, '2015-02-11 01:11:36'),
(5, 'Yee un lol o que?', 'Venga animaros jeje', 5, 46, 3, '2015-02-11 10:15:26'),
(6, 'Pero esto que eeeees?', 'Si solo estaba de paso aiss', 3, 12, 3, '2015-02-11 16:46:56');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipooperacion`
--

CREATE TABLE IF NOT EXISTS `tipooperacion` (
`id` int(6) NOT NULL COMMENT 'Identificador',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripción'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

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
  `id_tipousuario` int(11) DEFAULT NULL COMMENT 'Tipo Usuario'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Usuarios de GoPlace' AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `correo`, `login`, `password`, `fecha`, `id_ciudad`, `genero`, `estado`, `id_tipousuario`) VALUES
(1, 'Jose M', 'Coronado Aroca', 'joshco@gmail.com', 'josh', 'coronado', '1994-09-29', 0, 'M', 'Soy el admin de GoPlace!', 1),
(2, 'Aida', 'Sanchez Martinez', 'aidasm@gmail.com', 'aida', 'sanchez', '2015-02-22', 46, 'M', '', 2),
(3, 'Alejandro', 'Perez Vidal', 'kelo@gmail.com', 'kelo', 'kelo', '2015-02-04', 46, 'H', '', 2),
(4, 'Cristina', 'Sanchez Picado', 'crisloka21@hotmail.com', 'cris', 'cris', '2015-02-02', 37, 'M', '', 2),
(5, 'Javi', 'Alonso', 'javalonaso@gmail.com', 'javi', 'javi', '2015-02-02', 46, 'H', '', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `amistad`
--
ALTER TABLE `amistad`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `nombre` (`nombre`);

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
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id Amistad',AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT de la tabla `operacion`
--
ALTER TABLE `operacion`
MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'ID Operación',AUTO_INCREMENT=460;
--
-- AUTO_INCREMENT de la tabla `permiso`
--
ALTER TABLE `permiso`
MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'ID Permiso',AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id Publicacion',AUTO_INCREMENT=7;
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
