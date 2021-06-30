# bichico
Proyecto de la asignatura de Sistemas de Información (30224) - Grado en Ingeniería Informática

Escuela de Ingeniería y Arquitectura - Universidad de Zaragoza

**Ver `manual-usuario.pdf`** para funcionalidad y utilización.

## Despliegue e instalación de la aplicación
Se ha optado por utilizar `docker-compose` para el despliegue de la aplicación.
Para realizar el despliegue se debe lanzar el comando docker-compose up en la carpeta `/docker`. El fichero `docker-compose.yml` establece tres servicios:

1. `tomcat`: Se expone el puerto 8080 en la red interna. Es el primer servicio que se inicia.
2. `nginx`: Depende del servicio `tomcat` (se inicia una vez `tomcat` está activo). Su imagen preestablecida es `nginx:alpine`. Está configurado para que reenvíe todas las peticiones del puerto 80 al puerto 8080 (`tomcat`).
3. `calculador`: Lanza el proceso de obtención de datos y cálculo del factor que se ejecutan cada 2 horas.
