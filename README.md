# ILLO

Aqui te voy a dejar las pautas para el algoritmo:

# CONTEXTO

Esto es para que sepas de qué leches va esto y lo que quiero hacer.
EL programa comienza con una ventana simple preguntando por el nombre del usuario, la cantidad de personas, siendo que sólo se pueden meter entre 6 y 50 y el tamaño de los grupos. El nombre será guardadocomo parámetro en un futuro XML, que de eso se encarga la clase Informe, y lo de los grupos y su cantidad se encarga de dividir el XML en distintos grupos dentro de dicho objeto.
Tras darle al botón Siguiente, esa ventana se cierra, se guardan todos los parámetros anteriores en un nuevo objeto Informe y se abre la ventana principal, la cual contiene una tabla como la de la imagen que hay.
Las columnas del medio no son modificables, y las que si sólo pueden meter números del 0 al 4. Luego te digo lo que hacen. Una vez le de al botón y se asegure que hay datos en todas las celdas, se abrirá otra ventana que mostrará los grupos que se han formado y creará un archivo XML con dichos grupos.

# FUNCIONAMIENTO
Para empezar, se formarán los grupos del número que se indique por parte del usuario. Si es una división entera (hay resto), el último grupo simplemente se hace con los miembros restantes.
En la tabla, cada persona tiene asignada un "nivel de tensión con la otra", siendo: 
 - 0: Indiferencia, no se conocen o ni fu ni fa
 - 1: Son extremadamente cercanos y quieren estar juntos
 - 2: Se caen bien, pero no les importa moverse
 - 3: No se caen bien, esperan no estar juntos
 - 4: NO PUEDEN ESTAR JUNTOS SE MATAN

Las reglas para realizar los grupos son:
 - No pueden haber grupos con medias superiores a 3
 
Se busca que, al empezar a hacer grupos, se vayan juntando para que formen grupos con niveles bajos de tensión, aunque como vamos de tiempo simplemente con que no superen a 3 en ningun grupome vale, ya me pondré después del hito 2. Haz que se formen grupos de esta manera. También te pasaré mi programa para hacer XML.

Pásame tu código para que le haga las cosicas, anda
Gracias de antemano, amigo.