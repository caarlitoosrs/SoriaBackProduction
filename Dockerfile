# Stage 1: Build stage
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar el pom.xml primero para aprovechar la caché de Docker
COPY pom.xml .

# Descargar dependencias (esto se cacheará si el pom.xml no cambia)
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Crear un usuario no-root para seguridad
RUN addgroup -S spring && adduser -S spring -G spring

# Copiar el JAR desde el stage de build
COPY --from=build /app/target/soria-0.0.1-SNAPSHOT.jar app.jar

# Cambiar el propietario del archivo
RUN chown spring:spring app.jar

# Cambiar al usuario no-root
USER spring:spring

# Exponer el puerto de la aplicación (Railway usa la variable PORT)
EXPOSE 8080

# Healthcheck opcional (Railway tiene su propio sistema de health checks)
# Si tu aplicación tiene un endpoint de health, descomenta y ajusta:
# HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
#   CMD wget --no-verbose --tries=1 --spider http://localhost:${PORT:-8080}/actuator/health || exit 1

# Ejecutar la aplicación
# Railway inyecta automáticamente la variable PORT
ENTRYPOINT ["java", "-jar", "app.jar"]

