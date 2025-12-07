-- ===============================
-- EXPERIENCIAS DE SORIA
-- ===============================

INSERT INTO experiencias (id, titulo, descripcion, categoria, imagen_portada_url, ubicacion_lat, ubicacion_lng, direccion, visible)
VALUES

-- Aire libre
(UUID(), 'Yacimiento Arqueológico de Tiermes', 'Ruinas celtíberas y posteriormente romanas de una ciudad construida en la roca', 'AIRE_LIBRE', NULL, NULL, NULL, 'Venta de Tiermes s/n, 42344 Montejo de Tiermes, Soria', true),
(UUID(), 'Parque Natural Cañón del Río Lobos', 'Parque nacional desde 1985, con cañón de río boscoso de 19 km, conocido por la anidación de buitres.', 'AIRE_LIBRE', NULL, NULL, NULL, 'Entre Soria y Burgos', true),
(UUID(), 'Playa Pita', 'Disfruta de la playa de Soria', 'AIRE_LIBRE', NULL, NULL, NULL, 'V629+W6, 42005 Molinos de Duero, Soria', true),
(UUID(), 'Laguna Negra de Urbión', 'Lugar mágico cerca de los picos de Urbión que cuenta con unas vistas espectaculares', 'AIRE_LIBRE', NULL, NULL, NULL, 'SO-830, 42156 Vinuesa, Soria', true),
(UUID(), 'Ruta de las Icnitas de Soria', 'Conoce las rutas que te llevan a ver los fósiles de los dinosaurios que una vez poblaron la zona', 'AIRE_LIBRE', NULL, NULL, NULL, 'C/ La Plazuela 1, San Pedro Manrique, Soria', true),

-- Momunentos
(UUID(), 'Castillo de Caracena', 'Fortaleza medieval sobre un barranco, con vistas espectaculares. Bien de Interés Cultural; conserva torres, murallas y restos del patio de armas.', 'MONUMENTO', NULL, NULL, NULL, 'Unnamed Rd, 42311 Caracena, Soria', true),
(UUID(), 'Murallas del Burgo de Osma', 'Torre campanario gótica separada de la catedral, visitable, con vistas sobre todo el casco histórico y la muralla medieval.', 'MONUMENTO', NULL, NULL, NULL, 'Ctra. Rasa-Osma, 321, 42318 Cdad. de Osma, Soria', true),
(UUID(), 'Puente Romano sobre el Río Duero', 'Puente románico de piedra con arcos de medio punto; uno de los símbolos del pueblo y parte del Camino del Cid. Puedes aprovechar y visitar la fortaleza.', 'MONUMENTO', NULL, NULL, NULL, 'Ctra. de Madrid, 2, 42330 San Esteban de Gormaz, Soria', true),
(UUID(), 'Castillo de Berlanga del Duero', 'Impresionante fortaleza renacentista levantada sobre una antigua alcazaba árabe. Incluye restos del recinto amurallado y del palacio de los Tovar.', 'MONUMENTO', NULL, NULL, NULL, 'Plaza Ntra. Sra. Mercado, 42360 Berlanga de Duero, Soria', true);
(UUID(), 'Fortaleza Califal de Gormaz', 'Impresionante fortaleza islámica del siglo X, una de las mayores de Europa en su época. Desde su muralla hay vistas al Duero. Monumento Nacional.', 'MONUMENTO', NULL, NULL, NULL, 'Camino al Castillo, 42313 Gormaz, Soria', true),

-- Museos
(UUID(), 'Museo de Arte Contemporáneo de Ayllón', 'Pintores de renombre como Barjola, Genovés, Alcain, Alcorlo, Somoza y Amalia Avia donaron sus obras al Ayuntamiento para fomentar el interés por el arte.', 'MUSEO', NULL, NULL, NULL, 'Pl. Obispo Vellosillo, 1, 40520 Ayllón, Segovia', true),
(UUID(), 'Museo Numantino', 'Conoce la historia referente a la Soria antigua', 'MUSEO', NULL, NULL, NULL, 'P.º del Espolón, 8, 42001 Soria', true),
(UUID(), 'Museo del Tren de Aranda del Duero', 'Revive la época dorada del ferrocarril en un museo gratuito con maquetas de trenes de distintas épocas', 'MUSEO', NULL, NULL, NULL, 'Estación Chelva, s/n, 09400 Aranda de Duero, Burgos', true),
(UUID(), 'Museo Monográfico de Tiermes', 'Visita el museo que contiene las piezas halladas en el yacimiento', 'MUSEO', NULL, NULL, NULL, 'Paraje Venta De Tiermes, 0 Km 7 Por, 42344 Torresuso, Soria', true),
(UUID(), 'Bodegas Tradicionales', 'Museo del vino a la intemperie', 'MUSEO', NULL, NULL, NULL, 'Atauta, Soria, 42345', true),

-- Restaurantes
(UUID(), 'Terraza La Bombita', 'Parada en San Esteban de Gormaz con menú del día', 'RESTAURANTE', NULL, NULL, NULL, 'Av. Valladolid, 131, 42330 San Esteban de Gormaz, Soria', true),
(UUID(), 'Restaurante El Patio', 'Comida española variada de la zona', 'RESTAURANTE', NULL, NULL, NULL, 'Plaza Mayor, 7, 40520 Ayllón, Segovia', true),
(UUID(), 'Bar-Restaurante Caracena', 'Restaurante de pueblo conocido por sus vistas al monte.', 'RESTAURANTE', NULL, NULL, NULL, 'C. San Pedro, 18, 42311 Caracena, Soria', true),
(UUID(), 'Restaurante Antonio', 'Conocido por ostentar el título a mejor torrezno 2024', 'RESTAURANTE', NULL, NULL, NULL, 'Av. Valladolid, 98, 42330 San Esteban de Gormaz, Soria', true),
(UUID(), 'Hotel Restaurante Tiermes', 'Gastronomía típica de la zona con productos de la sierra Pela', 'RESTAURANTE', NULL, NULL, NULL, 'Paraje Venta de Tiermes, 0 s/n, 42344, Carrascosa de Arriba, Soria', true),
