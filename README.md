## PMDM Tarea 3. 
### DESARROLLO DE UNA APLICACIÓN CON FIREBASE (Temática Pokémon)

## Introducción
Aplicación que accede a una API de motivo Pokémons. Las colecciones en esta API están
creadas en "json". De estas colecciones la app mostrará los elementos en un recyclerview
con la información de cada pokémon. Sobre esta APi capturamos cada pokémon al pulsar sobre
la lista que se crea llamada pokédex, mostrando los elementos capturados en otro fragmento de la 
aplicaciónn.

## Carácteristicas principales
* el usuario de la aplicación se autenticará para el iniciar en ella mediante correo eléctronico o 
cuenta de Google.
* Creamos en al aplicación una lista pokédex recogiendo la información de de la APi pokémons "https://pokeapi.co/api/v2/"
* Se crea una colección de pokémons en firestore al pulsar sobre un elemento de la lista pokédex, estos son tratados como
  pokémons capturados. La aplicación leerá de firestore los elementos y los mostrará en un recyclerview
* La pantalla ajustes tendrá el cambio de idioma de la app, cerrar sesión de usuario autenticado, información del desarrollador

## Tecnología utilizda
* ReciclerView para mostrar cada pokémon 
* Fragment para mostrar las distintas pantallas de navegación: Pokédex, Pokémons capturador y ajustes
* Firebase para la autenticación de usuario
* Firestore para la creacción de la colección de los pokémons capturados
* Retrofit para acceder y recoger elementos de la API (lista pokédex) simplificando el acceso a la información
* Usamos libreria Glide para mostrar imagen de una url, y la ya mencionada Retrofit para el acceso a las colecciones

## Conclusiones del desarrollador
### "Aprendizaje"
* La libreria retrofit, como acceder a los elementos del árbol de datos y como hacer según la  profundidad del dato en la colección.
* Firebase para la autenticación y la estadística que puedes monitorizar de tu aplicación desarrollada
* Firestore como base de datos externa al dispositivo, descargando de peso de alamacenamiento al dispositivo.

 ![image](https://github.com/user-attachments/assets/d351edbc-9935-457a-b914-75c75f017935)



