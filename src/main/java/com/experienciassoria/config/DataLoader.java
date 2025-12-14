package com.experienciassoria.config;

import com.experienciassoria.dto.auth.UsuarioDto;
import com.experienciassoria.model.Comentario;
import com.experienciassoria.model.Experiencia;
import com.experienciassoria.model.Experiencia.Categoria;
import com.experienciassoria.model.ExperienciaUID;
import com.experienciassoria.model.RegistroExperiencia;
import com.experienciassoria.model.Usuario;
import com.experienciassoria.repository.ComentarioRepository;
import com.experienciassoria.repository.ExperienciaRepository;
import com.experienciassoria.repository.ExperienciaUIDRepository;
import com.experienciassoria.repository.RegistroExperienciaRepository;
import com.experienciassoria.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

        private final ExperienciaRepository experienciaRepository;
        private final UsuarioRepository usuarioRepository;
        private final PasswordEncoder passwordEncoder;
        private final RegistroExperienciaRepository registroExperienciaRepository;
        private final ExperienciaUIDRepository experienciaUIDRepo;
        private final ComentarioRepository comentarioRepository;

        @PostConstruct
        public void init() {
                                List<String> comentariosSoria = Arrays.asList(
    "Una experiencia muy auténtica. Se agradece encontrar sitios así, lejos del turismo masificado.",
    "Todo fue perfecto, desde la organización hasta el entorno. Soria sorprende y mucho.",
    "Un plan diferente y muy bien pensado. Ideal para venir en pareja o con amigos.",
    "La calma del lugar es espectacular. Sales de allí con las pilas totalmente cargadas.",
    "No esperaba encontrar algo tan bonito y tan bien cuidado. Repetiremos seguro.",
    "Una experiencia sencilla pero muy especial. De esas que se recuerdan con una sonrisa.",
    "El entorno natural es impresionante y la experiencia está muy bien integrada en él.",
    "Trato cercano y muy profesional. Se nota que conocen y quieren la tierra.",
    "Perfecto para desconectar del ruido y disfrutar sin prisas. Muy recomendable.",
    "Una sorpresa muy agradable. Merece mucho la pena descubrir rincones así.",
    "Todo estaba muy bien preparado y el ambiente era inmejorable. Para volver sin dudarlo.",
    "Un lugar con encanto y una experiencia muy bien cuidada. Soria tiene mucho que ofrecer.",
    "Ideal para cambiar de aires y descubrir algo diferente. Nos encantó.",
    "Una experiencia tranquila, bonita y muy auténtica. Justo lo que buscábamos.",
    "De esas experiencias que no necesitan grandes lujos para ser especiales.",
    "El sitio es precioso y la experiencia está muy bien pensada. Totalmente recomendable.",
    "Una forma fantástica de conocer la provincia y disfrutarla con calma.",
    "Salimos encantados. Todo fue muy natural y agradable, sin agobios.",
    "Una experiencia diferente, muy bien organizada y en un entorno espectacular.",
    "Sencillamente genial. De lo mejor que hemos hecho por la zona."
);

                if (usuarioRepository.count() == 0) {
                        List<Usuario> usuarios = List.of(
                                        Usuario.builder().nombre("Marcos").email("marcos@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("12345678"))
                                                        .role(Usuario.Rol.USER).puntos(0)
                                                        .fotoPerfilUrl("https://img.freepik.com/foto-gratis/joven-hombre-barbudo-camisa-rayas_273609-5677.jpg?semt=ais_hybrid&w=740&q=80")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Carlos").email("carlos@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("12345678"))
                                                        .role(Usuario.Rol.USER).puntos(0)
                                                        .fotoPerfilUrl("https://www.clinicalondres.es/wp-content/uploads/2023/08/iStock-1158245623-1024x682.jpg")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Laura").email("laura@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("12345678"))
                                                        .role(Usuario.Rol.USER).puntos(0)
                                                        .fotoPerfilUrl("https://img.freepik.com/foto-gratis/retrato-alegre-joven-mujer-bonita-bailando-feliz-expresion-cara-sonriente-cabello-largo-estado-animo-positivo-emocional-traje-estilo-hipster-tendencia-moda-verano-jeans-camisa-rayas-aislado_285396-714.jpg")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Michelle").email("michelle@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("12345678"))
                                                        .role(Usuario.Rol.USER).puntos(0)
                                                        .fotoPerfilUrl("https://img.freepik.com/foto-gratis/estilo-vida-emociones-gente-concepto-casual-confiado-agradable-sonriente-mujer-asiatica-brazos-cruzados-pecho-seguro-listo-ayudar-escuchando-companeros-trabajo-participando-conversacion_1258-59335.jpg")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Miguel").email("miguel@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("12345678"))
                                                        .role(Usuario.Rol.USER).puntos(0)
                                                        .fotoPerfilUrl("https://media.istockphoto.com/id/1090878494/es/foto/retrato-de-joven-sonriente-a-hombre-guapo-en-camiseta-polo-azul-aislado-sobre-fondo-gris-de.jpg?s=612x612&w=0&k=20&c=dHFsDEJSZ1kuSO4wTDAEaGOJEF-HuToZ6Gt-E2odc6U=")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Sofía").email("sofia@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("12345678"))
                                                        .role(Usuario.Rol.USER).puntos(0)
                                                        .fotoPerfilUrl("https://cdn.sanity.io/images/5vm5yn1d/pro/5cb1f9400891d9da5a4926d7814bd1b89127ecba-1300x867.jpg?fm=webp&q=80")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("admin").email("admin@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("12345678"))
                                                        .role(Usuario.Rol.ADMIN).puntos(0)
                                                        .fotoPerfilUrl("https://media.istockphoto.com/id/1171169099/es/foto/hombre-con-brazos-cruzados-aislados-sobre-fondo-gris.jpg?s=612x612&w=0&k=20&c=8qDLKdLMm2i8DHXY6crX6a5omVh2IxqrOxJV2QGzgFg=")
                                                        .fechaCreacion(Instant.now()).activo(true).build());
                        usuarioRepository.saveAll(usuarios);
                        System.out.println("Usuarios insertados correctamente (" + usuarios.size() + ")");
                }

                if (experienciaRepository.count() == 0) {
                        List<Experiencia> experiencias = List.of(
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2025/03/Tiermes-2048x641.jpg")
                                                        .titulo("Yacimiento Arqueológico de Tiermes")
                                                        .descripcion("Ruinas celtíberas y posteriormente romanas de una ciudad construida en la roca")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("Venta de Tiermes s/n, 42344 Montejo de Tiermes, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3289))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1499))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2025/03/Panorama-Tiermes-2-2048x1047.jpg",
                                                                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPADscNh9i54QVvtrOmDTdR2-obe0i6u4lwreNSNeiCw&s=10",
                                                                        "https://www.turismocastillayleon.com/es/patrimonio-cultura/yacimiento-arqueologico-tiermes.ficheros/37134-37128_SX_0_bic.jpg/37134-37128_SX_0_bic.jpg")))
                                                        .puntosOtorgados(20)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://www.excursionesyrutasporcastillayleon.com/wp-content/uploads/2016/01/12485804_1128732050478771_7020561739176423781_o-1-2000x1333.jpg")
                                                        .titulo("Parque Natural Cañón del Río Lobos")
                                                        .descripcion("Parque nacional desde 1985, con cañón de río boscoso de 19 km, conocido por la anidación de buitres.")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("Entre Soria y Burgos")
                                                        .ubicacionLat(BigDecimal.valueOf(41.7833))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1167))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2021/12/Plantilla-FOTO-4.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2021/12/Plantilla-FOTO-4.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2021/12/Canon-del-Rio-Lobos.jpg")))
                                                        .puntosOtorgados(20)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://elige.soria.es/wp-content/uploads/2015/07/Playa-Pita_Soria_2015_h.jpg")
                                                        .titulo("Playa Pita")
                                                        .descripcion("Disfruta de la playa de Soria")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("V629+W6, 42005 Molinos de Duero, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.8759))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.7042))
                                                        .galeriaImagenes((List.of(
                                                                        "https://piscinas-naturales.es/wp-content/uploads/2025/01/Playa-Pita-en-el-Pantano-de-Cuerda-del-Pozo-Soria-Peter-Walker.jpg",
                                                                        "https://elige.soria.es/wp-content/uploads/2015/07/Playa-Pita_Soria_2015_e.jpg",
                                                                        "https://fotos.hoteles.net/articulos/playa-pita-soria-8407-1.jpg",
                                                                        "https://ecourbion.com/fotografias/ecourbion-cuerda-del-pozo.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/06/Laguna-Negra-primavera.jpg")
                                                        .titulo("Laguna Negra de Urbión")
                                                        .descripcion("Lugar mágico cerca de los picos de Urbión que cuenta con unas vistas espectaculares")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("SO-830, 42156 Vinuesa, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.9991))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.8472))
                                                        .galeriaImagenes((List.of(
                                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Laguna_negra_de_Urbi%C3%B3n_1.jpg/2560px-Laguna_negra_de_Urbi%C3%B3n_1.jpg",
                                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Laguna_Negra_de_Urbi%C3%B3n_-_Portillo.JPG/1280px-Laguna_Negra_de_Urbi%C3%B3n_-_Portillo.JPG",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/06/Laguna-negra-2.jpg",
                                                                        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Laguna_Negra_de_Urbi%C3%B3n_desde_su_lateral_meridional..jpg/960px-Laguna_Negra_de_Urbi%C3%B3n_desde_su_lateral_meridional..jpg")))
                                                        .puntosOtorgados(20)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "http://www.rutadelasicnitas.com/assets/img/yacimientos/Adoberas/Adoberas-Matasejun(1).jpg")
                                                        .titulo("Ruta de las Icnitas de Soria")
                                                        .descripcion("Conoce las rutas que te llevan a ver los fósiles de los dinosaurios que una vez poblaron la zona")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("C/ La Plazuela 1, San Pedro Manrique, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(42.0592))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.2281))
                                                        .galeriaImagenes((List.of(
                                                                        "http://www.rutadelasicnitas.com/assets/img/yacimientos/La-Matecasa/La_Matecasa-Bretun%20(1).jpg",
                                                                        "http://www.rutadelasicnitas.com/assets/img/yacimientos/San-Roque/San%20Roque%20(1).jpg",
                                                                        "https://destinocastillayleon.es/index/wp-content/uploads/2018/03/b9058c932cc7062557bd68ce04c34629.jpg")))
                                                        .puntosOtorgados(30)
                                                        .activo(true).build(),

                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://www.turismocastillayleon.com/es/patrimonio-cultura/castillo-caracena.ficheros/196141-Castillo%20de%20Caracena%20%286%29.jpg/g,196141-Castillo%20de%20Caracena%20%286%29.jpg")
                                                        .titulo("Castillo de Caracena")
                                                        .descripcion("Fortaleza medieval sobre un barranco, con vistas espectaculares. Bien de Interés Cultural; conserva torres, murallas y restos del patio de armas.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Unnamed Rd, 42311 Caracena, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3789))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.095))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/12/Casracena.jpg",
                                                                        "https://dondeelvira.com/wp-content/uploads/2023/03/Castillo-de-Caracena_Casa-Rural-Donde-Elvira-1-optimized.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/12/Castillo-caracena-desde-la-carretera.jpg",
                                                                        "https://i0.wp.com/www.soriaestademoda.org/wp-content/uploads/2020/04/Caracena-Castillo-Parapente-2.jpg?fit=1280%2C720&ssl=1")))
                                                        .puntosOtorgados(20)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://www.caminodelcid.org/sites/default/files/styles/532x532/public/2024-08/gal-destierro-soria-burgo-de-osma-rio-ucero-pueblos-muralla-catedral-alcjpg.jpg")
                                                        .titulo("Murallas del Burgo de Osma")
                                                        .descripcion("Torre campanario gótica separada de la catedral, visitable, con vistas sobre todo el casco histórico y la muralla medieval.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Ctra. Rasa-Osma, 321, 42318 Cdad. de Osma, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.5866))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.0673))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.caminodelcid.org/sites/default/files/styles/532x532/public/2024-08/gal-destierro-soria-burgo-de-osma-murallas-portal-alcjpg.jpg?itok=2e0QAcyY",
                                                                        "https://www.caminodelcid.org/sites/default/files/styles/532x532/public/2024-08/gal-destierro-soria-burgo-de-osma-rio-ucero-pueblos-muralla-catedral-alcjpg.jpg?itok=l3ktfxWd",
                                                                        "https://www.caminodelcid.org/sites/default/files/styles/532x532/public/2024-08/gal-destierro-soria-burgo-de-osma-muralla-castillo-catedral-rio-ucero-puente-alcjpg.jpg?itok=qp-dvsse",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/12/Burgo.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://www.vinuesa.es/sites/vinuesa.es/files/public/images/default/carousel_noticias/puentes.jpg")
                                                        .titulo("Puente Romano sobre el Río Duero")
                                                        .descripcion("Puente románico de piedra con arcos de medio punto; uno de los símbolos del pueblo y parte del Camino del Cid. Puedes aprovechar y visitar la fortaleza.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Ctra. de Madrid, 2, 42330 San Esteban de Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.9242))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.8703))
                                                        .galeriaImagenes((List.of(
                                                                        "https://miro.medium.com/1*qjco5duReM1hwSwz4Oi82Q.jpeg",
                                                                        "https://rutadelvinoriberadelduero.es/wp-content/uploads/2024/10/puentes_langa_soria_ribera-1024x535.jpg",
                                                                        "https://www.turismosoria.es/wp-content/uploads/2016/05/turismo-soria-puente-medieval-sobre-el-rio-duero-soria.jpg",
                                                                        "https://www.turismosoria.es/wp-content/uploads/2016/05/turismo-soria-puente-medieval-rio-duero-soria-3.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0c/c5/76/e4/vistas-desde-abajo-en.jpg")
                                                        .titulo("Castillo de Berlanga del Duero")
                                                        .descripcion("Impresionante fortaleza renacentista levantada sobre una antigua alcazaba árabe. Incluye restos del recinto amurallado y del palacio de los Tovar.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Plaza Ntra. Sra. Mercado, 42360 Berlanga de Duero, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.4642))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.855))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/11/Castillo-de-Berlanga.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/11/INterior-castillo-berlanga.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/11/Berlanga-interior.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/11/Berlanga-2.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://content-viajes.nationalgeographic.com.es/medio/2024/12/17/san-esteban-de-gormaz_34692c16_389509037_241217140837_1280x853.webp")
                                                        .titulo("Fortaleza Califal de Gormaz")
                                                        .descripcion("Impresionante fortaleza islámica del siglo X, una de las mayores de Europa en su época. Desde su muralla hay vistas al Duero. Monumento Nacional.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Camino al Castillo, 42313 Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.4936))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.0081))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.jcyl.es/jcyl/patrimoniocultural/GuiaLugaresArqueologicos/wp-content/uploads/2022/11/Gormaz-6-.jpeg",
                                                                        "https://www.hispanianostra.org/wp-content/uploads/2023/05/GORMAZ-VERDE-1.jpg",
                                                                        "https://www.hispanianostra.org/wp-content/uploads/2023/05/GORMAZ-VERDE-2.jpg",
                                                                        "https://www.jcyl.es/jcyl/patrimoniocultural/GuiaLugaresArqueologicos/wp-content/uploads/2022/11/Gormaz-2.jpeg")))
                                                        .puntosOtorgados(20)
                                                        .activo(true).build(),

                                        Experiencia.builder()
                                                        .titulo("Museo de Arte Contemporáneo de Ayllón")
                                                        .imagenPortadaUrl(
                                                                        "https://eladelantado.com/wp-content/uploads/2023/06/1-MAC-ayllon.jpg")
                                                        .descripcion("Pintores de renombre como Barjola, Genovés, Alcain, Alcorlo, Somoza y Amalia Avia donaron sus obras al Ayuntamiento para fomentar el interés por el arte.")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Pl. Obispo Vellosillo, 1, 40520 Ayllón, Segovia")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3309))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1489))
                                                        .galeriaImagenes((List.of(
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1d/88/0b/b0/palacio-del-obispo-vellosillo.jpg?w=900&h=500&s=1",
                                                                        "https://lh5.googleusercontent.com/proxy/2FHmdvT-BXBogb5ZP6LREILKw7Jlq8nGVORUv7U_UuZkTUEr7VqlAPEXw1YeMMAnFXuYM1GbTY1sVV5wkSjWAefBpPqhbQrmMWjOsF_eevrbYT1rcZOoRTdrMLD394TRMTp2gIPXvjrB",
                                                                        "https://masdearte.com/media/n_almoneda23_ifema1-1024x682.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Museo Numantino")
                                                        .imagenPortadaUrl(
                                                                        "https://www.turismosoria.es/wp-content/uploads/2016/05/turismo-soria-museo-numantino-soria.jpg")
                                                        .descripcion("Conoce la historia referente a la Soria antigua")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("P.º del Espolón, 8, 42001 Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.7647))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.4703))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.turismosoria.es/wp-content/uploads/2016/05/exterior-museo-numantino-soria-2.jpg",
                                                                        "https://www.turismosoria.es/wp-content/uploads/2016/05/interior-museo-numantino-soria-8.jpg",
                                                                        "https://www.turismosoria.es/wp-content/uploads/2016/05/interior-museo-numantino-soria-9.jpg",
                                                                        "https://www.turismosoria.es/wp-content/uploads/2016/05/interior-museo-numantino-soria-11.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Museo del Tren de Aranda del Duero")
                                                        .imagenPortadaUrl(
                                                                        "https://www.turismocastillayleon.com/es/patrimonio-cultura/museo-tren.ficheros/383954-museo-del-tren_02.jpg/g,383954-museo-del-tren_02.jpg")
                                                        .descripcion("Revive la época dorada del ferrocarril en un museo gratuito con maquetas de trenes de distintas épocas")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Estación Chelva, s/n, 09400 Aranda de Duero, Burgos")
                                                        .ubicacionLat(BigDecimal.valueOf(41.655))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.6877))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.topriberadelduero.com/sites/default/files/museo-tren-aranda-burgos.jpg",
                                                                        "https://www.topriberadelduero.com/sites/default/files/fachada-museo-tren-aranda.jpg",
                                                                        "https://www.topriberadelduero.com/sites/default/files/locomotora-ceramica-museo-tren.jpg",
                                                                        "https://saposyprincesas.elmundo.es/assets/2013/04/museo-1.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Museo Monográfico de Tiermes")
                                                        .descripcion("Visita el museo que contiene las piezas halladas en el yacimiento")
                                                        .imagenPortadaUrl(
                                                                        "https://www.eldiasoria.es/media/IMG/2019/BAA0F45A-9D36-CCCA-D666BB158CF5C82F.JPG")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Paraje Venta De Tiermes, 0 Km 7 Por, 42344 Torresuso, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3344))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1503))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2021/11/Museo-tiermes-2.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2021/11/Vitrina-tiermes.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2021/11/maqueta-excavaciones-museo-tiermes.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2021/11/Museo-tiermes-4.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Bodegas Tradicionales")
                                                        .imagenPortadaUrl(
                                                                        "https://www.alhambragranada.info/images/r11.jpg")
                                                        .descripcion("Museo del vino a la intemperie")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Atauta, Soria, 42345")
                                                        .ubicacionLat(BigDecimal.valueOf(41.5676))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.2180))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/09/bodegas-san-esteban-gormaz-soria-2-1.jpg",
                                                                        "https://i0.wp.com/www.campingriolobos.es/wp-content/uploads/2020/03/20190820_113129-1024x768.jpg",
                                                                        "https://i0.wp.com/www.campingriolobos.es/wp-content/uploads/2020/03/20190830_124908-1024x776.jpg",
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/09/bodegas-san-esteban-gormaz-soria-3.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),

                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/06/donde-comer-restaurantes-restaurante-el-bomba-san-esteban-soria-ni-te-la-imaginas-003.jpg")
                                                        .titulo("Terraza La Bombita")
                                                        .descripcion("Parada en San Esteban de Gormaz con menú del día")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Av. Valladolid, 131, 42330 San Esteban de Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.6126))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.2045))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.sorianitelaimaginas.com/wp-content/uploads/2020/06/donde-comer-restaurantes-restaurante-el-bomba-san-esteban-soria-ni-te-la-imaginas-001-1-300x231.jpg",
                                                                        "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiOHhIlpMfp2oTV592n7eTxAVoYVk3nBED8dyVWHtNJgxgaj7LZ9jGmLYc4cNRnLCmzx5k543xwyrmjQu-t9MS8OolWKSPtVmnIt9nE0-ffBFG4vbS6wpLD6oQutsOngrkw6Swho4i9560/s2048/20200802_165227.jpg",
                                                                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV_eWXyDkooWCb4PT763sRdYyufFrHm4GoDw&s",
                                                                        "https://lh3.googleusercontent.com/gps-cs-s/AG0ilSzWkS8EfQQ8ZcywVKmVSX5G5FU3l33z_aLHhI4EZd6-tr0qZdyIYGk6ioiitFK7xUYr2orLBAOllYJjyKIzJ_3BbqjLVPl6GL877_RKnyhS01EsV8mP4hHYxaG4YO5POK2PYNpU")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/27/21/d1/36/conecta-tu-alma-al-jardin.jpg")
                                                        .titulo("Restaurante El Patio")
                                                        .descripcion("Comida española variada de la zona")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Plaza Mayor, 7, 40520 Ayllón, Segovia")
                                                        .ubicacionLat(BigDecimal.valueOf(41.4239))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.3761))
                                                        .galeriaImagenes((List.of(
                                                                        "https://photo620x400.mnstatic.com/7bb4b3d1cdd82dde2657dfd90a4adf2b/restaurante-el-patio.jpg",
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1d/16/04/ce/caption.jpg?w=1400&h=800&s=1",
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2f/bb/36/df/terraza.jpg?w=1100&h=600&s=1",
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0a/c4/ea/44/img-20160331-wa0013-largejpg.jpg?w=2000&h=-1&s=1")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Bar-Restaurante Caracena")
                                                        .imagenPortadaUrl(
                                                                        "https://www.guiarepsol.com/content/dam/repsol-guia/contenidos-imagenes/comer/nuestros-favoritos/restaurante-nuestra-tierra-(caracena,-soria)/_10.NuestraTierra_Postre.jpg.transform/rp-rendition-xs/image.jpg")
                                                        .descripcion("Restaurante de pueblo conocido por sus vistas al monte.")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("C. San Pedro, 18, 42311 Caracena, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3863))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.0917))
                                                        .galeriaImagenes((List.of(
                                                                        "https://restaurantenuestratierra.es/wp-content/uploads/elementor/thumbs/1677155737676-scaled-q3zwvjan8ucl66rk260406l1eti7fe1g6973e7aeck.jpg",
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/44/59/c0/caption.jpg",
                                                                        "https://www.guiarepsol.com/content/dam/repsol-guia/contenidos-imagenes/comer/nuestros-favoritos/restaurante-nuestra-tierra-(caracena,-soria)/_00.NuestraTierra_Arroz.jpg.transform/rp-rendition-md/image.jpg",
                                                                        "https://img3.restaurantguru.com/ra0d-Bar-Restaurante-de-Caracena-interior-2025-08-3.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Restaurante Antonio")
                                                        .imagenPortadaUrl(
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/28/a5/8e/89/caption.jpg?w=1100&h=600&s=1")
                                                        .descripcion("Conocido por ostentar el título a mejor torrezno 2024")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Av. Valladolid, 98, 42330 San Esteban de Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.6037))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.2035))
                                                        .galeriaImagenes((List.of(
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2b/2c/c7/b7/caption.jpg?w=1400&h=800&s=1",
                                                                        "https://www.restaurante-antonio.com/media/cabeceras/8/3-restaurante-antonio-1.webp",
                                                                        "https://e01-expansion.uecdn.es/assets/multimedia/imagenes/2023/03/13/16787101943645.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/05/79/65/53/venta-de-tiermes.jpg")
                                                        .titulo("Hotel Restaurante Tiermes")
                                                        .descripcion("Gastronomía típica de la zona con productos de la sierra Pela")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Paraje Venta de Tiermes, 0 s/n, 42344, Carrascosa de Arriba, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3721))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1690))
                                                        .galeriaImagenes((List.of(
                                                                        "https://www.guiarepsol.com/content/dam/repsol-guia/contenidos-imagenes/comer/nuestros-favoritos/restaurante-nuestra-tierra-(caracena,-soria)/_11.NuestraTierra_Comedor.jpg.transform/rp-rendition-xs/image.jpg",
                                                                        "https://www.laterrazadelebro.es/img/900/bar-restaurante-de-caracena.jpg",
                                                                        "https://www.guiarepsol.com/content/dam/repsol-guia/contenidos-imagenes/comer/nuestros-favoritos/restaurante-nuestra-tierra-(caracena,-soria)/_07.NuestraTierra_Comedor.jpg.transform/rp-rendition-lg/image.jpg",
                                                                        "https://img3.restaurantguru.com/r571-Bar-Restaurante-de-Caracena-interior-2025-08-4.jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build());

                        experienciaRepository.saveAll(experiencias);
                        System.out.println("Experiencias insertadas correctamente (" + experiencias.size() + ")");
                }

                List<Experiencia> todasExperiencias = experienciaRepository.findAll();

                List<Usuario> usuariosNormales = usuarioRepository.findAll().stream()
                                .filter(u -> u.getRole() == Usuario.Rol.USER)
                                .toList();

                List<RegistroExperiencia> registrosUsuarios = new ArrayList<>();
                Random random = new Random();

                for (Usuario usuario : usuariosNormales) {

                        int cantidadExperiencias = random.nextInt(5) + 1; // 1 a 5
                        List<Experiencia> experienciasAleatorias = new ArrayList<>(todasExperiencias);
                        Collections.shuffle(experienciasAleatorias);

                        for (int i = 0; i < cantidadExperiencias; i++) {

                                Experiencia experienciaSeleccionada = experienciasAleatorias.get(i);

                                ExperienciaUID uidActivo = experienciaUIDRepo.findAll().stream()
                                                .filter(u -> u.getExperiencia().getId().equals(
                                                                experienciaSeleccionada.getId()) && u.isActivo())
                                                .findFirst()
                                                .orElseGet(() -> {
                                                        ExperienciaUID nuevoUid = ExperienciaUID.builder()
                                                                        .experiencia(experienciaSeleccionada)
                                                                        .uid(UUID.randomUUID().toString())
                                                                        .activo(true)
                                                                        .fechaGeneracion(Instant.now())
                                                                        .build();
                                                        experienciaUIDRepo.save(nuevoUid);
                                                        System.out.println("UID generado automáticamente: "
                                                                        + nuevoUid.getUid() + " para "
                                                                        + experienciaSeleccionada.getTitulo());
                                                        return nuevoUid;
                                                });

                                RegistroExperiencia registro = RegistroExperiencia.builder()
                                                .usuario(usuario)
                                                .experiencia(experienciaSeleccionada)
                                                .experienciaUID(uidActivo)
                                                .fechaRegistro(Instant.now())
                                                .opinion("Opinión automática del usuario " + usuario.getNombre())
                                                .puntosOtorgados(10)
                                                .imgPortada(experienciaSeleccionada.getImagenPortadaUrl())
                                                .build();

                                registrosUsuarios.add(registro);
                                usuario.setPuntos(usuario.getPuntos() + registro.getPuntosOtorgados());

                                Comentario comentario = Comentario.builder()
                                                .experiencia(experienciaSeleccionada)
                                                .usuario(usuario)
                                                .texto(comentariosSoria.get(new Random().nextInt(comentariosSoria.size())))
                                                .fecha(Instant.now())
                                                .build();

                                comentarioRepository.save(comentario);

                        }

                        System.out.println("Usuario " + usuario.getNombre()
                                        + " recibió " + cantidadExperiencias + " experiencias aleatorias.");
                }

                registroExperienciaRepository.saveAll(registrosUsuarios);
                usuarioRepository.saveAll(usuariosNormales);

        }
}
