# CITYTRAVEL
## Actividad Aprendizaje (1EVA - 2EVA ) - 2ºDAM - ACCESO A DATOS

![Java](https://img.shields.io/badge/Java-red?style=for-the-badge&logo=Java&logoColor=white)
![Spring](https://img.shields.io/badge/SpringBoot-green?style=for-the-badge&logo=spring&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-orange?style=for-the-badge&logo=postman&logoColor=white)
![Github](https://img.shields.io/badge/github-black?style=for-the-badge&logo=github&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-green?style=for-the-badge&logo=swagger&logoColor=black)

***
![Swagger](https://img.shields.io/badge/swagger-green?style=for-the-badge&logo=swagger&logoColor=black)
## DOCUMENTACION API https://jsenen.github.io/CityTravel/#/

***
Se ha creado una API para la gestión y consulta de lineas de tren.

### Requisitos  2EVA
1. escribe el fichero OpenAPI 3.0 de la API. Incluye, al menos, los casos de éxito (20X), 400, 404 y los 500.
   https://jsenen.github.io/CityTravel/#/ ✅

2. Diseña una API Virtual de forma que existan, al menos, 3 Casos de Uso para cada operación (tanto de OK como para KO).
   https://github.com/JSenen/APIMock-CityTravel ✅

3. Prepara una colección Postman de prueba para la API diseñada y otra que permita probar todos los Casos de Uso de la API virtual
   Se encuentran dentro de cada repositorio ✅

4. Diseña, al menos, 3 operaciones para que funcionen de forma reactiva con WebFlux.
   https://github.com/JSenen/CityTravel_REACTIVE ✅

5. Ajusta el desarrollo de tu proyecto para que cumpla todas las decisiones de diseño adoptadas en los puntos anteriores ✅


6. Si tu API está securizada, añade la información necesaria al fichero OpenAPI 3.0 ✅

7. Añade alguna operación en la que se envien o reciban ficheros
8. Parametriza ambas colecciones Postman de forma que sea fácil cambiar el host, puerto o basePath de la API ✅

   Dentro del fichero POSTMAN, se encuentra los ENVIOREMENT y se ha incluido los host en una variable global
9. Añade al fichero de especificación de la API (OpenAPI 3.0) un par de ejemplos para cada operación ✅

10. Utiliza las herramientas Git y GitHub durante todo el desarrollo de la aplicación. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo.
✅


### Requisitos (1 pto cada uno, obligatorios) 1EVA
1. El modelo de datos estará compuesto de, al menos, 5 clases y tendrán que existir relaciones entre ellas. Cada clase tendrá, al menos, 6 atributos (String, int, float, boolean y algún tipo para almacenar fechas). Cada clase tendrá, al menos, 2 atributos obligatorios y algún otro con algún tipo de restricción de formato/validación. ✅
   El modelo relacional entre las diferentes clases es el siguiente.

   ![Imagen](https://github.com/JSenen/CityTravel/blob/master/ER_DB.png)
    
     Las clases y validaciones/restricciones son las siguientes.
    | Clase | Uso | Validación - Restriccion |
    | ----------- | -------------- | ---------------------- |
    | Line        | Lineas de tren | Código y Horarios      |
    | LineAccess  | Accesos        | Nombre de la calle     |
    | LineGarage  | Garages trenes | Codigo del garage      |
    | LineStation | Estaciones     | Nombre y horarios      |
    | LineTrain   | Trenes         | Código del tren        |

2. Se tendrá que poder realizar, el menos, las operaciones CRUD sobre cada una de las clases. Se controlarán, al menos, los errores 400, 404 y 500  ✅

   Sobre cada clase se puede realizar un CRUD completo con los metodos RESTweb.
   ![GET](https://img.shields.io/static/v1?label=GET&message=Obtener&color=blue)
   ![GET](https://img.shields.io/static/v1?label=GET&message=Filtrar&color=blue)
   ![POST](https://img.shields.io/static/v1?label=POST&message=Crear&color=green)
   ![PUT](https://img.shields.io/static/v1?label=PUT&message=Actualizar&color=orange)
   ![PATCH](https://img.shields.io/static/v1?label=PATCH&message=Modificar&color=white)
   ![DELETE](https://img.shields.io/static/v1?label=DELETE&message=Borrar&color=red)

   El error 500 se ha controlado por medio del handler en la clase LineControler con el siguiente código
   ```
   @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException (Exception exc){
        logger.error(exc.getMessage(), exc);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        logger.error("Finish 500 Internal Server error");
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }
   ```
   El error 400 han sido controlados mediante otro handler.
   ```
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException (MethodArgumentNotValidException manve){
        logger.error(manve.getMessage(), manve);
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request",errors);
        logger.error("Finish 4000 Bad Request exception");
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
   ```
   Y por último, el 404 se ha controlado por medio de.
   ```
   @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException nfexc){
        logger.error(nfexc.getMessage(), nfexc);
        ErrorMessage errorMessage = new ErrorMessage(404, nfexc.getMessage());
        logger.error("Finish NotFoundException");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
   ```
   Siendo este último válido para todas las clases. Al invocarlo, recoge el nombre de la clase donde se ha producido el error y por
   medio del constructor nos devuelve el nombre de la clase concatenado al string NOT FOUND.
   ```
   public class NotFoundException extends Exception{

    //Excepción genérica para recoger todas. Nos plasma la clase que causa la excepción más el texto que asignamos
    public NotFoundException(Object obj){
        super(obj.getClass().getSimpleName() + " NOT FOUND");
    }
   ```

3. Añade opciones de filtrado para al menos una operación en cada clase en donde se puedan indicar hasta 3 campos diferentes (solo aplicable para operaciones GET)  ✅

   En todas las clases se ha introducido una función de filtrado en la operación GET, la cual recoge los parámetros indicados en la petición al servidor para realizarla,
   y en caso de no introducirse ninguno nos devuelve la totalidad del listado.
   El código a continuación es un **ejemplo** de la clase Line.
   ```
   @GetMapping("/line")
    public ResponseEntity<List<outLineDTO>> getAll(@RequestParam(name = "firstTime", defaultValue = "00:00",required = false) String start,
                                                   @RequestParam(name = "lastTime", defaultValue = "00:00", required = false) String close){
        logger.info("Begin getLine");
        //por defecto se le asigna 00:00, para que cuente ese valor en caso de que solo se selccione una de las posibilidades
        if (start.equals("00:00") && close.equals("00:00")){
            logger.info("Finish getLine");
            return ResponseEntity.ok(lineService.findAll());
        }
        logger.debug("Begin getLine by Hours");
        LocalTime hstart = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime hclose = LocalTime.parse(close, DateTimeFormatter.ofPattern("HH:mm"));
        logger.info("Finish getLine by Hours");
        return ResponseEntity.ok(lineService.searchByHourStartAndHourClose(hstart, hclose));

    }
   ```
4. Prepara una colección Postman que permita probar todas las operaciones desarrolladas✅

   En el repositorio, dentro de la carpeta POSTMAN se puede encontrar la colección en formato JSON desarrollada para la prueba.

5. Configura en el proyecto la librería logback para que la aplicación web cuente con un log. Añade trazas en el código de forma que permita seguir el rastro de ejecución en el log (para todas las operaciones que se puedan realizar y también para los casos en los que se recojan errores)✅

   Se ha añadido la librería ***logback** para el control por medio de log en la aplicación. Cuya linrería ya viene incluida en SpringBoot por medio de la dependencia Maven ***log4s***

### Otras funcionalidades (1 pto cada una)

6. Añade una operación PATCH para cada una de las clases del modelo ✅

   A todas las clases se ha incluido una operación ![PATCH](https://img.shields.io/static/v1?label=PATCH&message=Modificar&color=white) , para realizar ***modificaciones parciales del registro***

7. Utiliza la herramienta Git (y GitHub) durante todo el desarrollo de la aplicación. Escribe el fichero README.md para explicar cómo poner en marcha el proyecto. Utiliza el gestor de Issues para los problemas/fallos que vayan surgiendo ✅

   Durante el desarrollo de la actividad se ha usado este control de verisiones GITHUB

8. Añade 3 nuevos endpoints a la aplicación (sin repetir método) que realicen nuevas operaciones con los datos y que requieran el uso de DTOs y/o utilizar las relaciones entre las clases ✅

   Se han usado DTOs en todas las clases para mostrar datos parciales así como para modificaciones parciales

9. Securiza algunas de tus operaciones de la API con un token JWT ✅

    Para poder interaccionar con la API, es necesario usar el metodo ![POST](https://img.shields.io/static/v1?label=POST&message=Registerr&color=yellow), para registrarse y ![POST](https://img.shields.io/static/v1?label=POST&message=Token&color=yellow) para obtner un token que nos permita interactuar con todos los endpoints
    Por el momento no se han establecido roles.

10. Añade 3 operaciones que utilicen consultas JPQL para extraer la información de la base de datos

11. Añade 3 operaciones que utilicen consultas SQL nativas para extraer la información de la base de datos ✅

    Para el filtrado de elementos en los metodos ![GET](https://img.shields.io/static/v1?label=GET&message=Filtrar&color=blue) , se han usado SQL nativas.
    Pudiendose encontrar todas ellas dentro del directorio ***repository** de cada una.
    ***Ejemplo en clase LineRepository***
    ```
    @Query(value = "SELECT * FROM line WHERE first_time = ? OR last_time = ?",nativeQuery = true)
    List<Line> findAllByHourStartOrHourClose(LocalTime start, LocalTime close);
    ```
12. Añade 2 clases más al modelo de datos con sus respectivas operaciones CRUD (inclúyelas también en la colección Postman)

12. Parametriza la colección Postman para que pueda ser ejecutada con el Runner de Postman y realizar una prueba completa de la API ✅

    Dentro del archivo JSON de POSTMAN, se incluye un subdirectorio TEST_CityTravel en el que se ha parametrizado la colección completa y sus métodos para
    realizar un test completo de su correcto desarrollo automáticamente.


### USO y FUNCIONAMIENTO

Desde Intellij, una vez abierto el proyecto. Podemos arrancar SpringBoot mediante el comando en consola 
```
mvn spring-boot:run
```
Abrimos POSTMAN y cargamos e iomportamos el archivo CITYTRAVEL.postman_collection.json

El primer paso es registrarnos como usuarios por medio del ![POST](https://img.shields.io/static/v1?label=POST&message=Registerr&color=yellow).

Y una vez nos hemos registrado, deberemos obtener un token por medio de ![POST](https://img.shields.io/static/v1?label=POST&message=Token&color=yellow).

Igualmentem deberemos comprobar en las cabeceras de postman, que se haya habiltado la ***Authorization token***

En la parte izquierda de la aplicación, podedemos encontrar 5 carpetas correspondientes a cada clase, y una ultima denominada TEST_CityTravel.
Cada directorio correspondiente a cada clase puede realizar un CRUD completo de los siguientes métodos por clase. Estando todos ellos automatizados para realizar las pruebas por medio de datos ***random***

| Directorio  | Método  | Path                         | Operación                                                 |
|-------------|---------|------------------------------|-----------------------------------------------------------|
| Line        | POST    | /line                        | Crear una nueva linea                                     |
|             | PATCH   | /line/{id}                   | Modificacion codigo linea                                 |
|             | GET     | /line/{id}                   | Obtener linea por id                                      |
|             | GET     | /line//line?firstTime=04:30  | Obtención de lineas con filtrado por horarios             |
|             | PUT     | /line/{id}                   | Modificación linea por id                                 |
|             | DEL     | /line/{id}                   | Eliminacion de linea por {id}                             |


| Directorio | Método | Path                      | Operación                                                                       |
|------------|--------|---------------------------|---------------------------------------------------------------------------------|
| Station    | POST   | /station/{lineId}/station | Crear nueva estacion en una linea                                               |
|            | PATCH  | /station/{lineId}/station | Modificacion horarios estación                                                  |
|            | GET    | /station/                 | Listado estaciones                                                              |
|            | GET    | /station/{id}             | Listar estacion por Id                                                          |
|            | GET    | /station?wifi=....        | Listar filtrando por wifi, si dispone de autobuses o si dispone de parada taxis |
|            | PUT    | /station/{id}             | Modificación estación                                                           |
|            | DEL    | /station/{id}             | Eliminacion de estacion por {id}                                                |


| Directorio | Método | Path                  | Operación                                                     |
|------------|--------|-----------------------|---------------------------------------------------------------|
| Train      | POST   | /train/{lineid}/train | Crear tren asociado a linea                                   |
|            | PATCH  | /train/{trainId}      | Modificacion vagones, asientos y plazas de pie tren           |
|            | GET    | /train                | Listado trenes                                                |
|            | GET    | /line/{lineId}/trains | Listar trenes de una linea por id linea                       |
|            | GET    | /train?numWagons=.    | Listar filtrando por número vagones, asientos o plazas de pie |
|            | PUT    | /train/{id}           | Modificación tren                                             |
|            | DEL    | /train/{id}           | Eliminacion de tren por {id}                                  |



| Directorio | Método | Path                       | Operación                                   |
|------------|--------|----------------------------|---------------------------------------------|
| Access     | POST   | /access/{stationId}/access | Crear acceso a estacion                     |
|            | PATCH  | /access/{stationId}/access | Modificacion valor ascensor acceso estacion |
|            | GET    | /access                    | Listado accesos                             |
|            | GET    | /access/{id}               | Listar accesos por {id}                     |
|            | GET    | /access?elevator=..        | Listar filtrando por ascensores             |
|            | PUT    | /access/{id}               | Modificación acceso estacion                |
|            | DEL    | /access/{id}               | Eliminacion de acceso por {id}              |


| Directorio | Método | Path                       | Operación                                |
|------------|--------|----------------------------|------------------------------------------|
| Garage     | POST   | /garage/{stationId}/garage | Crear garage trenes en estacion          |
|            | PATCH  | /garage/{garageId}/garage  | Modificacion servicios en garage         |
|            | GET    | /garage                    | Listado garages                          |
|            | GET    | /garage/{id}               | Listar garages por {id}                  |
|            | GET    | /garage?taller=tr...       | Listar filtrando por distintos servicios |
|            | PUT    | /garage/{id}               | Modificación garage                      |
|            | DEL    | /garage/{id}               | Eliminacion de garage por {id}           |


En el direcorio TEST CityTravel, se encuentran una secuencia de todos los métodos, los cuales se han configurado por medio de ***javascript*** para realizar un test completo de la API con el 
***run folder*** de POSTMAN.

=======
