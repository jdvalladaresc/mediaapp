# mediaapp
===
Proyecto backend en SpringBoot para serivicios médicos

## Directorio de Carpetas
El proyecto WS con Spring Boot consta de los siguientes directorios principales

* **Controller:** En este directorio se encontrara todo lo relacionado a los WS Rest
* **Dto:** Este directorio como su nombre lo indica por sus siglas en inges DATA OBJECT TRANSFER es para transportar los objetos entre los procesos
* **Excepcion:** Este directorio es para el manejo de los errores de los WS
* **Model:** Este directorio es para la capa de persistencia que hace referencia a las tablas de la BD
* **Repo:** Hace referencia a todo las consultas de la BD

### Observación
Se tiene que añadir en el archivo **_application.properties_** los 
accesos a la BD que estan en el directorio resources

    spring.datasource.username=(AQUI PONER TU USUARIO DE BD)
    spring.datasource.password=(AQUI PONER CONTRASEÑA PARA CONECTARTE A TU BD)
