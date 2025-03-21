# tdd-tp1
TP individual de Técnicas de Diseño (75.10), FIUBA

El siguiente proyecto consiste en crear una API de backend tipo REST-like con el fin de cumplir un contrato definido en un archivo `Swagger`. La api debe ser montada en un contenedor de Docker y configurada para ser accedidad desde `localhost`. La base de datos utlizada es una instancia de h2 para correr en memoria, configurada en el archivo `aaplication.properties`.

El proceso de buildeo de la aplicación, creación y lanzamiento del contenedor se encuentra automtizado en el ejecutable `launch.sh`

ejecucion: 

```bash
$ ./launch.sh
```

En caso de querer levantar la aplicación localmente sin instanciar un contenedor, ejecutar

```bash
$ ./gradlew clean build
```

El proyecto fue desarrollado en su totalidad usando Java 17 y para los tests se utilizó el framework [Junit 5](http://example.com](https://junit.org/junit5/docs/current/user-guide/))


**Importante**: al forzar un error de tipo `Course not found`, al correr el proyecto localmente la respuesta de error se instancia correctamente, pero no ocurre lo mismo al correr la aplicación desde el contenedor de Docker. En este último caso, la respuesta devuelve una pantalla en blanco. Me encuentro investigando el motivo actualmente.
