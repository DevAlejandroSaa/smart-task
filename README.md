# SmartTask

Aplicación de consola desarrollada en **Java 8** para la gestión de tareas personales.

El proyecto corresponde a la evaluación del **Módulo 4: Fundamentos de Programación en Java**.

## Descripción

SmartTask permite gestionar tareas desde la consola, aplicando fundamentos de programación en Java, programación orientada a objetos, estructuras de control, interfaces, modularidad, pruebas unitarias y documentación técnica.

La aplicación permite:

- Agregar tareas con nombre y prioridad.
- Listar tareas.
- Buscar tareas por ID.
- Actualizar la prioridad y el estado de una tarea.
- Marcar tareas como completadas.
- Eliminar tareas por ID.
- Evitar tareas con nombres duplicados.

## Tecnologías

- Java 8
- Maven
- JUnit 5
- Maven Surefire
- JaCoCo
- JavaDoc

## Requisitos

Para ejecutar el proyecto se requiere:

- JDK 8 o superior.
- Maven.
- Terminal o consola.

Verificar la instalación:

    java -version
    mvn -version

## Estructura del proyecto

    smart-task/
    ├── app/
    │   └── smart-task/
    │       ├── src/
    │       │   ├── main/
    │       │   └── test/
    │       └── pom.xml
    ├── scripts/
    │   └── build.sh
    ├── .gitignore
    ├── LICENSE
    └── README.md

## Ejecución

Ingresar al directorio de la aplicación:

    cd app/smart-task

Ejecutar la aplicación mediante Maven:

    mvn exec:java

## Generar el JAR

Para compilar el proyecto, ejecutar las pruebas y generar el archivo ejecutable:

    mvn package

El JAR se genera en:

    target/smart-task-1.0-SNAPSHOT.jar

Ejecutar la aplicación:

    java -jar target/smart-task-1.0-SNAPSHOT.jar

## Pruebas unitarias

Las pruebas unitarias están desarrolladas utilizando **JUnit 5**.

Para ejecutar las pruebas:

    mvn test

Los resultados generados por Maven Surefire se encuentran en:

    target/surefire-reports/

El proyecto debe alcanzar una cobertura mínima del **80%**.

## Cobertura de código

La cobertura se obtiene mediante **JaCoCo**.

Ejecutar:

    mvn test

El informe HTML se genera en:

    target/site/jacoco/index.html

El informe permite revisar la cobertura de:

- Clases.
- Métodos.
- Líneas.
- Instrucciones.
- Ramas.

## JavaDoc

La documentación técnica del proyecto se genera mediante JavaDoc.

Ejecutar:

    mvn javadoc:javadoc

El resultado se encuentra en:

    target/site/apidocs/index.html

## Script de construcción

El proyecto incluye un script para automatizar las tareas principales:

    scripts/build.sh

Dar permisos de ejecución:

    chmod +x scripts/build.sh

Ejecutar desde la raíz del proyecto:

    ./scripts/build.sh

El script ejecuta:

1. Las pruebas unitarias.
2. La generación del reporte de cobertura.
3. La generación del JavaDoc.
4. La generación del JAR.

## Entregables

El proyecto contempla los siguientes entregables solicitados:

- Código fuente completo de SmartTask.
- Archivo JAR ejecutable desde consola.
- JavaDoc generado.
- Pruebas unitarias con JUnit 5.
- Capturas de pantalla del funcionamiento de la aplicación.
- Informe de cobertura de código.

## Licencia

Este proyecto se distribuye bajo los términos establecidos en el archivo `LICENSE`.