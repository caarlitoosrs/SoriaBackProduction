
package com.experienciassoria.config;

import com.experienciassoria.dto.auth.UsuarioDto;
import com.experienciassoria.model.Experiencia;
import com.experienciassoria.model.Experiencia.Categoria;
import com.experienciassoria.model.ExperienciaUID;
import com.experienciassoria.model.RegistroExperiencia;
import com.experienciassoria.model.Usuario;
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
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

        private final ExperienciaRepository experienciaRepository;
        private final UsuarioRepository usuarioRepository;
        private final PasswordEncoder passwordEncoder;
        private final RegistroExperienciaRepository registroExperienciaRepository;
        private final ExperienciaUIDRepository experienciaUIDRepo;

        @PostConstruct
        public void init() {

                // 🔹 Crear usuarios si no existen
                if (usuarioRepository.count() == 0) {
                        List<Usuario> usuarios = List.of(
                                        Usuario.builder().nombre("Marcos").email("marcos@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("11111111"))
                                                        .role(Usuario.Rol.USER).puntos(50)
                                                        .fotoPerfilUrl("https://img.freepik.com/foto-gratis/joven-hombre-barbudo-camisa-rayas_273609-5677.jpg?semt=ais_hybrid&w=740&q=80")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Carlos").email("carlos@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("11111111"))
                                                        .role(Usuario.Rol.USER).puntos(30)
                                                        .fotoPerfilUrl("https://plus.unsplash.com/premium_photo-1690407617542-2f210cf20d7e?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGVyc29uYXxlbnwwfHwwfHx8MA%3D%3D")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Laura").email("laura@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("11111111"))
                                                        .role(Usuario.Rol.USER).puntos(100)
                                                        .fotoPerfilUrl("https://plus.unsplash.com/premium_photo-1689568158814-3b8e9c1a9618?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8JTIzcGVyc29uYXxlbnwwfHwwfHx8MA%3D%3D")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Michelle").email("michelle@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("11111111"))
                                                        .role(Usuario.Rol.USER).puntos(75)
                                                        .fotoPerfilUrl("https://img.freepik.com/foto-gratis/estilo-vida-emociones-gente-concepto-casual-confiado-agradable-sonriente-mujer-asiatica-brazos-cruzados-pecho-seguro-listo-ayudar-escuchando-companeros-trabajo-participando-conversacion_1258-59335.jpg")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Miguel").email("miguel@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("11111111"))
                                                        .role(Usuario.Rol.USER).puntos(25)
                                                        .fotoPerfilUrl("https://media.istockphoto.com/id/1090878494/es/foto/retrato-de-joven-sonriente-a-hombre-guapo-en-camiseta-polo-azul-aislado-sobre-fondo-gris-de.jpg?s=612x612&w=0&k=20&c=dHFsDEJSZ1kuSO4wTDAEaGOJEF-HuToZ6Gt-E2odc6U=")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("Sofía").email("sofia@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("11111111"))
                                                        .role(Usuario.Rol.USER).puntos(200)
                                                        .fotoPerfilUrl("https://cdn.sanity.io/images/5vm5yn1d/pro/5cb1f9400891d9da5a4926d7814bd1b89127ecba-1300x867.jpg?fm=webp&q=80")
                                                        .fechaCreacion(Instant.now()).activo(true).build(),
                                        Usuario.builder().nombre("admin").email("admin@gmail.com")
                                                        .passwordHash(passwordEncoder.encode("11111111"))
                                                        .role(Usuario.Rol.ADMIN).puntos(0)
                                                        .fotoPerfilUrl("https://media.istockphoto.com/id/1171169099/es/foto/hombre-con-brazos-cruzados-aislados-sobre-fondo-gris.jpg?s=612x612&w=0&k=20&c=8qDLKdLMm2i8DHXY6crX6a5omVh2IxqrOxJV2QGzgFg=")
                                                        .fechaCreacion(Instant.now()).activo(true).build());
                        usuarioRepository.saveAll(usuarios);
                        System.out.println("✅ Usuarios insertados correctamente (" + usuarios.size() + ")");
                }

                // 🔹 Crear experiencias si no existen
                if (experienciaRepository.count() == 0) {
                        List<Experiencia> experiencias = List.of(
                                        // Aquí pones todas las experiencias que ya tienes
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Yacimiento Arqueológico de Tiermes")
                                                        .descripcion("Ruinas celtíberas y posteriormente romanas de una ciudad construida en la roca")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("Venta de Tiermes s/n, 42344 Montejo de Tiermes, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3289))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1499))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Parque Natural Cañón del Río Lobos")
                                                        .descripcion("Parque nacional desde 1985, con cañón de río boscoso de 19 km, conocido por la anidación de buitres.")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("Entre Soria y Burgos")
                                                        .ubicacionLat(BigDecimal.valueOf(41.7833))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1167))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Playa Pita")
                                                        .descripcion("Disfruta de la playa de Soria")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("V629+W6, 42005 Molinos de Duero, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.8759))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.7042))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Laguna Negra de Urbión")
                                                        .descripcion("Lugar mágico cerca de los picos de Urbión que cuenta con unas vistas espectaculares")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("SO-830, 42156 Vinuesa, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.9991))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.8472))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Ruta de las Icnitas de Soria")
                                                        .descripcion("Conoce las rutas que te llevan a ver los fósiles de los dinosaurios que una vez poblaron la zona")
                                                        .categoria(Categoria.AIRE_LIBRE)
                                                        .direccion("C/ La Plazuela 1, San Pedro Manrique, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(42.0592))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.2281))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),

                                        // 🏰 Monumentos
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Castillo de Caracena")
                                                        .descripcion("Fortaleza medieval sobre un barranco, con vistas espectaculares. Bien de Interés Cultural; conserva torres, murallas y restos del patio de armas.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Unnamed Rd, 42311 Caracena, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3789))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.095))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Murallas del Burgo de Osma")
                                                        .descripcion("Torre campanario gótica separada de la catedral, visitable, con vistas sobre todo el casco histórico y la muralla medieval.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Ctra. Rasa-Osma, 321, 42318 Cdad. de Osma, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.5866))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.0673))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Puente Romano sobre el Río Duero")
                                                        .descripcion("Puente románico de piedra con arcos de medio punto; uno de los símbolos del pueblo y parte del Camino del Cid. Puedes aprovechar y visitar la fortaleza.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Ctra. de Madrid, 2, 42330 San Esteban de Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.9242))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.8703))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Castillo de Berlanga del Duero")
                                                        .descripcion("Impresionante fortaleza renacentista levantada sobre una antigua alcazaba árabe. Incluye restos del recinto amurallado y del palacio de los Tovar.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Plaza Ntra. Sra. Mercado, 42360 Berlanga de Duero, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.4642))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.855))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Fortaleza Califal de Gormaz")
                                                        .descripcion("Impresionante fortaleza islámica del siglo X, una de las mayores de Europa en su época. Desde su muralla hay vistas al Duero. Monumento Nacional.")
                                                        .categoria(Categoria.MONUMENTO)
                                                        .direccion("Camino al Castillo, 42313 Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.4936))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.0081))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),

                                        // 🏛️ Museos
                                        Experiencia.builder()
                                                        .titulo("Museo de Arte Contemporáneo de Ayllón")
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .descripcion("Pintores de renombre como Barjola, Genovés, Alcain, Alcorlo, Somoza y Amalia Avia donaron sus obras al Ayuntamiento para fomentar el interés por el arte.")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Pl. Obispo Vellosillo, 1, 40520 Ayllón, Segovia")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3309))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1489))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Museo Numantino")
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .descripcion("Conoce la historia referente a la Soria antigua")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("P.º del Espolón, 8, 42001 Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.7647))
                                                        .ubicacionLng(BigDecimal.valueOf(-2.4703))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Museo del Tren de Aranda del Duero")
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .descripcion("Revive la época dorada del ferrocarril en un museo gratuito con maquetas de trenes de distintas épocas")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Estación Chelva, s/n, 09400 Aranda de Duero, Burgos")
                                                        .ubicacionLat(BigDecimal.valueOf(41.655))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.6877))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Museo Monográfico de Tiermes")
                                                        .descripcion("Visita el museo que contiene las piezas halladas en el yacimiento")
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Paraje Venta De Tiermes, 0 Km 7 Por, 42344 Torresuso, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3344))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1503))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Bodegas Tradicionales")
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .descripcion("Museo del vino a la intemperie")
                                                        .categoria(Categoria.MUSEO)
                                                        .direccion("Atauta, Soria, 42345")
                                                        .ubicacionLat(BigDecimal.valueOf(41.5676))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.2180))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),

                                        // 🍽️ Restaurantes
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Terraza La Bombita")
                                                        .descripcion("Parada en San Esteban de Gormaz con menú del día")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Av. Valladolid, 131, 42330 San Esteban de Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.6126))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.2045))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Restaurante El Patio")
                                                        .descripcion("Comida española variada de la zona")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Plaza Mayor, 7, 40520 Ayllón, Segovia")
                                                        .ubicacionLat(BigDecimal.valueOf(41.4239))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.3761))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Bar-Restaurante Caracena")
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .descripcion("Restaurante de pueblo conocido por sus vistas al monte.")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("C. San Pedro, 18, 42311 Caracena, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3863))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.0917))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .titulo("Restaurante Antonio")
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .descripcion("Conocido por ostentar el título a mejor torrezno 2024")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Av. Valladolid, 98, 42330 San Esteban de Gormaz, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.6037))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.2035))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build(),
                                        Experiencia.builder()
                                                        .imagenPortadaUrl(
                                                                        "https://media.istockphoto.com/id/1412282625/es/foto/vista-a%C3%A9rea-de-la-ciudad-de-soria-espa%C3%B1a.jpg?s=612x612&w=0&k=20&c=V0xfZkeEstJrQEU9x43k00OnQZyRQliavYOdwWI-8G0=")
                                                        .titulo("Hotel Restaurante Tiermes")
                                                        .descripcion("Gastronomía típica de la zona con productos de la sierra Pela")
                                                        .categoria(Categoria.RESTAURANTE)
                                                        .direccion("Paraje Venta de Tiermes, 0 s/n, 42344, Carrascosa de Arriba, Soria")
                                                        .ubicacionLat(BigDecimal.valueOf(41.3721))
                                                        .ubicacionLng(BigDecimal.valueOf(-3.1690))
                                                        .galeriaImagenes((List.of(
                                                                        "https://media.istockphoto.com/id/543212762/es/foto/tractor-en-el-campo-de-primavera-relaciones-sean.jpg?s=612x612&w=0&k=20&c=ua9ZJb046xHKUDsRW2okFfKYJyNd12RMXZ8vESdjUHc=",
                                                                        "https://www.shutterstock.com/image-photo/wide-open-field-stretching-towards-600nw-2476599713.jpg",
                                                                        "https://images.unsplash.com/photo-1500382017468-9049fed747ef?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2FtcG98ZW58MHx8MHx8fDA%3D",
                                                                        "https://images.pexels.com/photos/64102/pexels-photo-64102.jpeg?cs=srgb&dl=pexels-ksusha-semakina-12855-64102.jpg&fm=jpg")))
                                                        .puntosOtorgados(10)
                                                        .activo(true).build());

                        experienciaRepository.saveAll(experiencias);
                        System.out.println("✅ Experiencias insertadas correctamente (" + experiencias.size() + ")");
                }

                // 🔹 Crear UIDs para cada experiencia si no existen
                List<Experiencia> todasExperiencias = experienciaRepository.findAll();
                // 🔹 Crear registros de experiencia para el admin usando UIDs activos
                
                Usuario admin = usuarioRepository.findByEmail("admin@gmail.com").orElseThrow();
                List<RegistroExperiencia> registros = new ArrayList<>();

                int contador = 0;

                for (Experiencia exp : todasExperiencias) {

                        ExperienciaUID uidActivo = experienciaUIDRepo.findAll().stream()
                                        .filter(u -> u.getExperiencia().getId().equals(exp.getId()) && u.isActivo())
                                        .findFirst()
                                        .orElseGet(() -> {
                                                ExperienciaUID nuevoUid = ExperienciaUID.builder()
                                                                .experiencia(exp)
                                                                .uid(UUID.randomUUID().toString())
                                                                .activo(true)
                                                                .fechaGeneracion(Instant.now())
                                                                .build();
                                                experienciaUIDRepo.save(nuevoUid);
                                                System.out.println("✅ UID generado automáticamente: "
                                                                + nuevoUid.getUid() + " para " + exp.getTitulo());
                                                return nuevoUid;
                                        });

                                

                        RegistroExperiencia registro = RegistroExperiencia.builder()
                                        .usuario(admin)
                                        .experiencia(exp)
                                        .experienciaUID(uidActivo)
                                        .fechaRegistro(Instant.now())
                                        .opinion("Muy Buena experiencia " + contador )
                                        .puntosOtorgados(10)
                                        .imgPortada("https://img.freepik.com/foto-gratis/paisaje-verano_1398-161.jpg?semt=ais_hybrid&w=740&q=80")
                                        .build();
                        registros.add(registro);

                        admin.setPuntos(admin.getPuntos() + registro.getPuntosOtorgados());

                        contador++;
                                
                }

                registroExperienciaRepository.saveAll(registros);
                usuarioRepository.save(admin);

                System.out.println("✅ Registros de experiencia del admin creados correctamente (" + registros.size()
                                + ")");

        }
}
