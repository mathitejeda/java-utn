# TP N°2
## Ejercicio 1:
Crear un paquete llamado ejercicio1, dentro de él realizar lo siguiente:
1. Utilizar la clase empleado creada en el TP N°1. Crear una clase Profesor que herede de la clase empleado. Datos de la clase profesor:
```java
    String cargo;
    int antiguedadDocente;
```
2. Aplicar el concepto de encapsulamiento dentro de la clase profesor.
3. Crear los respectivos constructores
4. Sobreescribir el método `toString()`, en este caso debe devolver la siguiente información: `id, nombre, edad, cargo y antiguedadDocente`.
5. Crear una clase llamada `mainEjercicio1_a`, donde se creen 5 profesores y se guarden dentro de un arraylist. Mostrar la información utilizando uniterador.
6. Crear una clase llamada `mainEjercicio1_b`, donde se creen 5 profesores y se guarden dentro de un treeset. Mostrar la información utilizando un iterador.
7. Crear dos objetos de tipo profesor con la misma información en su interior, luego compararlos dentro de un IF y si contienen la misma información mostrar por consola `Es el mismo profesor.`
## Ejercicio 2
Crear un paquete llamado ejercicio2, dentro de él realizar lo siguiente:
Se plantea desarrollar un programa Java que permita la gestión de una empresa agroalimentaria que trabaja con tres tipos de productos:
- productos frescos 
- productos refrigerados
- productos congelados.

Todos los productos llevan esta información común: fecha de caducidad y número de lote. A su vez, cada tipo de producto lleva alguna información específica. Los productos frescos deben llevar la fecha de envasado y el país de origen. Los productos refrigerados deben llevar el código del organismo de supervisión alimentaria. Los productos congelados deben llevar la temperatura de congelación recomendada. 
1. Crear el código de las clases Java implementando una relación de herencia desde la superclase Producto hasta las subclases `ProductoFresco, ProductoRefrigerado y ProductoCongelado`. 
2. Cada clase debe estar encapsulada, es decir debe permitir establecer (set) y recuperar (get) el valor de sus atributos, debe disponer de un constructor y tener un método que permita mostrar la información del objeto. 
3. Crear una clase mainEjercicio2 donde se cree un objeto de cada tipo y se muestren los datos de cada uno de los objetos creados.
## Ejercicio 3
Crear un paquete llamado ejercicio3, dentro de él realizar lo siguiente:
Se plantea desarrollar un programa Java que permita representar la siguiente situación. 
- Una instalación deportiva es un recinto delimitado donde se practican deportes, en Java interesa disponer de un método `int getTipoDeInstalacion()`. 
- Un edificio es una construcción cubierta y en Java interesa disponer de un método double `getSuperficieEdificio()`. 
- Un polideportivo es al mismo tiempo una instalación deportiva y un edificio; 
en Java interesa conocer la superficie que tiene y el nombre que tiene. 
- Un edificio de oficinas es un edificio; en Java interesa conocer el número de oficinas que tiene.
1. Crear una clase llamada mainEjercicio3, con el método main. Dentro del mismo crear un ArrayList que contenga tres polideportivos y dos edificios de oficinas y utilizando un iterator, recorrer la colección y mostrar los atributos de cada elemento.
