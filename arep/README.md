# AREP Taller 1

Aplicación para consultar la información de películas de cine, la aplicación recibe una frase de búsqueda del título y muestra los datos de la película correspondiente utilizando la API de https://www.omdbapi.com/. 

## Iniciando

### Prerequisites

- Maven - Administrador de dependencias y administrador del ciclo de vida del proyecto
- Java - Ambiente de desarrollo
- Git - Sistema de control de versiones y descarga del repositorio

### Instalando el entorno

Para correr el programa primero descargue el repositorio con el siguiente comando
```
git clone https://github.com/cattus09/AREP_lab1.git
```

Una vez clonado el repositorio ingrese en la carpeta descargada y corra el siguiente comando para ejecutar el programa

```
mvn clean package exec:java -D "exec.mainClass"="arep.HttpServer"
```

Finalmente ingrese al navegador de su preferencia con el siguiente link:
http://localhost:35000

## Documentación
Se encontrar la documentación en la carpeta nombrada "javadoc", para generar nueva documentación puede correr el siguiente comando
```
mvn javadoc:javadoc
```
La nueva documentación generada puede encontrarla en la ruta /target/site/apidocs

## Corriendo Tests unitatios

Para correr los test ubiquese en la carpeta principal de repositorio y corra el siguiente comando desde la consola

```
mvn test
```

## Despliegue

Add additional notes about how to deploy this on a live system

## Construido con

* [Maven](https://maven.apache.org/) - Dependency Management

## Versonamiento

Versión 1.0

## Autor

* Sergio Andres Gonzalez Vargas

## Explicaciones tecnicas

Se hace una arquitectura enfocada en API Rest. Se implementa el patrón de diseño SINGLETON para la creación de caché, puesto que este es el único caché que debe existir dentro del servidor

- Extensibilidad: podemos hacer consultas a otras APIs modificando solo el URL del atributo.
- Patrones usados: Se usa el patrón de Fachada y el patrón de Singleton.
- Modularización: Todas clases implementan metodos los cuales singuen el principio de unica responsabilidad.