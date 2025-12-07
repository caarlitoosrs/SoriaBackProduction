# Guía de Despliegue en Railway

Esta guía explica cómo desplegar la aplicación Spring Boot en Railway con MySQL.

## Variables de Entorno Requeridas en Railway

Configura las siguientes variables de entorno en tu proyecto de Railway:

### Base de Datos (MySQL)
- `DATABASE_URL`: URL completa de conexión a MySQL
  - Formato: `jdbc:mysql://HOST:PORT/DATABASE?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
  - Ejemplo: `jdbc:mysql://mysql.railway.internal:3306/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
- `DATABASE_USERNAME`: Usuario de la base de datos
- `DATABASE_PASSWORD`: Contraseña de la base de datos

### JWT
- `JWT_SECRET`: Clave secreta para firmar los tokens JWT (debe ser larga y segura)
- `JWT_EXPIRATION`: Tiempo de expiración en milisegundos (por defecto: 3600000 = 1 hora)

### CORS
- `CORS_ALLOWED_ORIGINS`: Orígenes permitidos separados por comas
  - Ejemplo: `https://tu-frontend.railway.app,https://otro-dominio.com`

### Opcionales
- `PORT`: Puerto donde corre la aplicación (Railway lo asigna automáticamente)
- `JPA_DDL_AUTO`: Modo de Hibernate (`update`, `validate`, `none`) - Recomendado: `update` para producción
- `JPA_SHOW_SQL`: Mostrar SQL en logs (`true`/`false`) - Recomendado: `false` para producción
- `LOG_LEVEL`: Nivel de logging (`DEBUG`, `INFO`, `WARN`, `ERROR`)

## Pasos para Desplegar

### 1. Crear Servicio MySQL en Railway

1. En tu proyecto de Railway, crea un nuevo servicio
2. Selecciona "Database" → "MySQL"
3. Railway creará automáticamente las variables de entorno:
   - `MYSQLHOST`
   - `MYSQLPORT`
   - `MYSQLDATABASE`
   - `MYSQLUSER`
   - `MYSQLPASSWORD`

### 2. Configurar Variables de Entorno para la Aplicación

En el servicio de tu aplicación Spring Boot, configura:

```bash
# Base de datos (ajusta según las variables de tu MySQL en Railway)
DATABASE_URL=jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DATABASE_USERNAME=${MYSQLUSER}
DATABASE_PASSWORD=${MYSQLPASSWORD}

# JWT (genera una clave segura)
JWT_SECRET=tu-clave-secreta-muy-larga-y-segura-aqui

# CORS (ajusta con tu dominio de frontend)
CORS_ALLOWED_ORIGINS=https://tu-frontend.railway.app

# Producción
JPA_DDL_AUTO=update
JPA_SHOW_SQL=false
LOG_LEVEL=INFO
```

### 3. Conectar el Servicio MySQL con la Aplicación

Railway permite conectar servicios mediante variables de entorno compartidas. Asegúrate de que tu aplicación pueda acceder a las variables del servicio MySQL.

### 4. Desplegar la Aplicación

1. Conecta tu repositorio Git a Railway
2. Railway detectará automáticamente el Dockerfile
3. El build se ejecutará automáticamente
4. Una vez desplegado, Railway asignará una URL pública

## Notas Importantes

- **No hardcodees credenciales**: Todas las credenciales deben estar en variables de entorno
- **JWT_SECRET**: Debe ser una cadena larga y aleatoria. Puedes generarla con:
  ```bash
  openssl rand -base64 32
  ```
- **CORS**: Asegúrate de incluir todos los dominios desde los que se accederá al backend
- **Base de datos**: Railway crea automáticamente las variables de entorno para MySQL, úsalas en lugar de hardcodear valores

## Desarrollo Local

Para desarrollo local, puedes usar `docker-compose.yml`:

```bash
docker-compose up -d
```

Esto levantará MySQL y la aplicación con las configuraciones por defecto. Puedes crear un archivo `.env` para personalizar:

```env
DATABASE_PASSWORD=rootpassword
DATABASE_NAME=soria
JWT_SECRET=tu-clave-local
CORS_ALLOWED_ORIGINS=http://localhost:8081,http://localhost:8082
```

