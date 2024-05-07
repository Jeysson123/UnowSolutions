
# Unow Solutions

## Descripción ✍️ 

¡Proyecto que maneja transacciones de vehiculos!.

## Tabla de contenido  👈 

-[Instalación](#installation)
-[Versiones](#versiones)



## Instalación  🔧 

- Modulo Backend

Este módulo es un web service montado con spring-boot, manejando una robusta arquitectura de microservices.

A - Modulo common

Este módulo es el encargado de manejar todo el core de la aplicacion, deben seguirse los siguientes pasos.

1 - Desplegar este modulo en su IDE de preferencia & ejecutar el comando mvn clean install, de ser necesario mvn clean install -DskipTests
en ocasiones de que existan cambios en la data, de esta manera podra inyectar en su proyecto local las dependencias.

B - Modulo vehicles

Este módulo es el endpoint final para las operaciones de los vehiculos (CRUD).

*Nota : para la correcta ejecucion del servicio debe agregarse un compound con los modulos anteriores*

- Modulo Frontend

Este modulo es una aplicacion react, que sirve como una interfaz interactiva con el usuario final y nuestro servicio spring-boot.

1 - Desplegar este modulo con su Editor o IDE favorito, luego ejecute el comando -npm install, para instalar las dependencias de manera local en su proyecto.

2 - En la ruta /app, levantar el servidor node ejectuando el comando -npm start.


## Versiones ✅ 

1 - Maven 3.8.5
2 - Java 8
3 - Spring Boot 2.5.5
5 - Hibernate
6 - MySql
7 - React 18
8 - Swagger 2.0
9 - Lombok




