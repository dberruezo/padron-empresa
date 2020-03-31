# Padrón Empresa

Ejercicio de desarrollo de una aplicación `API REST` utilizando `Spring Boot`.

## Utilización

Ejecutar el comando `mvn spring-boot:run`.

Para terminar la ejecución aplicar combinación de teclas `CTRL + C`.

El proyecto esta configurado para utilizar una instancia de base de datos `H2` y aplicar las migraciones correspondientes mediante `FlywayDB` previo a montar el servidor `Tomcat Embed`. No es necesaria configuración extra.

### Uso de base de datos PostgreSQL

Si se desea utilizar una instancia de `PostgreSQL` se debe ejecutar el comando `mvn -P prod spring-boot:run`. Tener en cuenta que se debe disponer de un servidor de `PostgreSQL`.

Para configurar las credenciales de acceso y parámetros de conexión se debe editar el archivo `application-prod.properties`.
