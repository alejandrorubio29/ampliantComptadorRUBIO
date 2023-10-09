# ampliantComptadorRUBIO
2DAM-IESELJUST
Autor: Alejandro Rubio Rico


## 1. Anàlisi De L’estructura Del Projecte

La estructura del proyecto es como sigue:
![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/79a100de-c2fe-45ee-b5f3-919d40f80aea)
El proyecto se compone de dos actividades, la MainActivity y el MostraComptador. 

Las carpetas más importantes son:
- Manifest: contiene el fichero xml del manifest, que identifica la aplicación, componentes que forman parte de ella, establece permisos y almacena información esencial. 
![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/77280385-53bb-4010-b512-11d14b3a0f98)
- La carpeta java donde se almacenan en el paquete correspondiente las principales "Actividades"
- La carpeta res donde se almacenan los principales recursos. Pueden ser valores de colores a llamar en otras partes de la aplicación (subcarpeta values), pueden ser layouts (ficheros xml que establecen lo que se "muestra" en cada actividad), etc.
- La carpeta de Gradle, donde lo más importante es el build gradle del modulo, el fichero gradle que establece la configuración del proyecto y las dependencias y plugins, que para casos de proyectos Android es de gran importancia.
  ![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/3e4a7a78-5e16-4038-97ab-4fbc0200dab1)


En cuanto a la estructura de una actividad, esta se compone de clases, métodos y atributos de manera similar a las clases de java. Aqui por ejemplo vemos la actividad principal
![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/4d35fff0-e514-416c-94fe-f7dbc908bf9f)

Esta actividad vemos que se está definiendo una variable "contador" y se está sobreescribiendo el módulo OnCreate, de manera que:
- Se carga el fichero de layout mediante el método setContentView
- Se inicializan los distintos elementos del layout para controlar su comportamiento, como crear respuestas a callbacks o similar

Como se aprecia, una actividad está compuesta por un fichero Kotlin (El equivalente a la clase java) que contiene la clase y un fichero layout, como elementos más señalados:
- El fichero kotlin contiene la lógica
- El fichero XML contiene el diseño de la actividad
En estos términos, una actividad puede corresponderse con una pantalla que el sistema puede instanciar en cualquier momento.
Sin embargo, no es suficiente con simplemente crear esos dos archivos, porque una actividad deriva de dicha instanciación y por ende el sistema debe "reconocer" dicha actividad e iniciarla. 
![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/f78644d4-47a1-4400-8c85-bdfe97367f76)
Para el primer caso, necesitaremos darla de alta en el fichero manifest previamente descrito. En esta imagen apreciamos las dos actividades creadas, una de las cuales tiene un intent-filter y la otra no (El intent-filter define que debemos iniciar una actividad mediante una "intención" o intent únicamente explícito, es decir, haciendo un intent directo desde otra parte del programa) 
Para el segundo caso, el hecho de "iniciar" una actividad se define mediante los intents, como este por ejemplo:
val intent = Intent(baseContext, MostrarComptador::class.java) que proporciona a la aplicacion los recursos necesarios para acceder a la clase (en este caso, actividad) MostraComptador




## 2. Anàlisi Del Cicle De Vida I El Problema De La Pèrdua D’estat

Siguiendo las indicaciones del ejercicio, implementamos el control de estados mediante tags con estas dos clases. 

override fun onResume() {
        super.onResume()
        Log.d("MostraComptador", "Al mètode onResume")
    }

En la siguiente captura se aprecia como se muestran los estados nada mas iniciar la app:
![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/fd72597f-e217-42f5-9c21-8841af13f28b)
Como vemos, la secuencia de estados es la esperable, pero comenzando en un estado incorrecto. Según contexto del problema propuesto, esta pérdida de estado puede deberse a un problema de restauración de los datos de la aplicación ante cambios como el giro de pantalla, tal y como se señala en el enunciado. 

Algunos ejemplos de la documentación oficial en que se habla del concepto de guardar las variables o los estados del sistema ante cambios
https://developer.android.com/topic/libraries/architecture/saving-states?hl=es-419

https://developer.android.com/jetpack/androidx/releases/savedstate?hl=es-419#kts

Tal y como nos sugiere la rúbrica, debemos hacer uso de Bundle. Al final, Bundle es un tipo de objeto que define a savedInstanceState y que permitiría almacenar un valor ante pérdida, ya que es información que el sistema recibe cuando se restaura. 

tal y como vemos aqui: https://stackoverflow.com/questions/6525698/how-to-use-onsavedinstancestate-example-please podemos interactuar con ese Bundle mediante funciones como put.
Siguiendo el ejemplo, debemos sobreescribir la función asociada al onsavedinstancestate:

![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/63dfab26-3972-407f-8eb1-4f2a4c390cca)

De esta manera, debería solucionarse el problema de la pérdida de datos y restaurar correctamente el valor del contador ante el savedInstanceState

Comprobarmos el giro y como se mantiene, habiendo añadido el código

<img width="1060" alt="image" src="https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/ee73234e-773b-462e-9bb4-5a662a23cda9">


## 3. Ampliant La Funcionalitat Amb Decrements I Resets

Agregando los botones correspondientes al layout principal
![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/b6df359a-d433-4b80-b854-721731ee9c6b)

En la actividad principal simplemente tendremos que añadir las referencias a resta y reset
//Referencias resta y reset

        val btResta = findViewById<Button>(R.id.btResta)
        val btReset = findViewById<Button>(R.id.btReset)

y añadir los callbacks. La lógica es sencilla, simplemente se actúa sobre la variable contador
//Callbacks de resta y reseteo

        btResta.setOnClickListener {
            comptador--
            textViewContador.setText(comptador.toString())
        }

        btReset.setOnClickListener {
            comptador = 0
            textViewContador.setText(comptador.toString())
        }
        Nota: deliberadamente dejo la posibilidad de contar "en negativo" para que se vea mejor la capacidad de restar. Si hubiera
        que limitar a restar hasta cero, sería sencillo comprobando que contador != 0
        
Como vemos, es efectivo:

<img width="886" alt="image" src="https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/c8039525-57c6-4256-a21f-ace2f32d25d1">

<img width="889" alt="image" src="https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/b996cbaf-e3a5-44ac-b61e-8ebadf4f8841">

## 4. Aplica El View Binding
Lo primero activamos el View Building
<img width="689" alt="image" src="https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/2f54cae3-58c4-4c85-9ca7-36bf7d2a8bde">
Despues sincronizamos el proyecto con Gradle File
![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/22f467c1-39c0-4ce8-9310-9049017165dd)

Tras activarlo, debemos importar la clase de vinculación que se habrá generado, en nuestra actividad principal
import com.ieseljust.pmdm.comptador.databinding.ActivityMainBinding

Lo primero, definiremos una nueva variable binding y añadiremos a onCreate las modificaciones necesarias y el método inflate

![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/61793762-60a5-4c79-9815-43fd82c8dd64)

Tras eso, tenemos que modificar toda la lógica para, en lugar de acceder mediante variables e ids de xml correspondiente, acceder mediante la variable binding (Nota: he mantenido el código anterior comentado para no perderlo)

![image](https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/5c17fd0e-0ae3-40af-8158-3347bb83ca10)

Comprobamos que compila correctamente y funciona con el view building, lo cual es indicativo de que está implementado correctamente
<img width="1116" alt="image" src="https://github.com/alejandrorubio29/ampliantComptadorRUBIO/assets/145864071/ae8f42ca-3154-4b0e-aba2-232d626340dd">

