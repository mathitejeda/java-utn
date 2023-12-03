# TP N°3
Crear un paquete llamado ejercicio1, dentro de él realizar lo siguiente:
A. Crear una excepción de tipo IOException llamada DniInvalido. Luego crear una función llamada verificarDniInvalido que verifique que los dni contengan números y no letras, en el caso de tener letras se arrojará la excepción DniInvalido.
B. Leer el archivo llamado Personas.txt, que está compuesto por:
- Nombre 
– Apellido
– Dni

y pasar los datos que se encuentran en el archivo a una lista que no acepte datos duplicados, que permita ordenar los datos según el Apellido desde la A – Z y además no se agregará a la lista Dni que no cumplan el requisito de solo contener números (utilizar la función verificarDniInvalido para validar que el dni sea correcto)
C. Leer la lista creada en el punto 2 y pasar la información a un archivo llamado Resultado.txt (cada persona ocupará un renglón dentro del 
archivo)
IMPORTANTE: Utilizar clases y funciones. Deben estar creadas al menos la clase Persona y la clase Archivo
