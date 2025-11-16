# Proyecto: Compresión de Texto con Huffman

Este proyecto implementa el algoritmo de compresión de Huffman para un texto dado. Permite:
- Construir el árbol de Huffman a partir de las frecuencias de los caracteres de una cadena.
- Generar los códigos binarios Huffman para cada símbolo.
- Comprimir el texto original convirtiéndolo en una secuencia de bits.
- Decodificar la secuencia comprimida para reconstruir el texto original.

## Estructura del Proyecto
```
src/
  main/
    java/
      org/example/compresion/
        Main.java        // Punto de entrada. Declara el texto y lanza el proceso.
        Huffman.java     // Métodos estáticos: construcción, codificación y decodificación.
        Nodo.java        // Representa un nodo del árbol de Huffman.
pom.xml
```

## Clases Principales
### Nodo
Representa un nodo del árbol de Huffman.
Atributos:
- `Character caracter`: símbolo (null en nodos internos).
- `Integer frecuencia`: frecuencia total del símbolo o suma en nodos internos.
- `Nodo izq`, `Nodo der`: referencias a hijos izquierdo y derecho.

Constructores:
- `(Character caracter, Integer frecuencia)`
- `(Character caracter, Integer frecuencia, Nodo izq, Nodo der)`

### Huffman
Contiene la lógica del algoritmo mediante métodos estáticos:
- `construirArbolHuffman(String texto)`: realiza todo el flujo de compresión y verificación.
- `codifica(Nodo raiz, String str, Map<Character,String> codigoHuffman)`: genera recursivamente los códigos.
- `decodifica(Nodo raiz, int index, StringBuilder sb)`: recorre el árbol para reconstruir el texto.

### Main
Ejemplo de uso. Declara una cadena:
```java
String texto = "WWXWYZWX";
Huffman.construirArbolHuffman(texto);
```
La salida incluye:
- Códigos Huffman generados.
- Texto comprimido (binario).
- Texto decodificado (igual al original).
- Estadísticas de compresión.

## Requisitos
- Java 25 (configurado en el `pom.xml`).
- Maven instalado.

## Compilación y Ejecución
Desde la raíz del proyecto:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.compresion.Main"
```
Si `exec:java` no está configurado, puede ejecutarse directamente:
```bash
javac -d target/classes src/main/java/org/example/compresion/*.java
java -cp target/classes org.example.compresion.Main
```

## Ejemplo de Salida (parcial)
```
Códigos de Huffman generados:
'W' : 00
'X' : 010
'Y' : 110
'Z' : 111

Texto original: WWXWYZWX
Longitud original: 8 caracteres

Texto comprimido (binario): 00100110111010
Longitud comprimida: 14 bits

Texto decodificado: WWXWYZWX
Bits originales (8 bits/carácter): 64
Bits comprimidos: 14
Tasa de compresión: 78.12%
```
(NOTA: Los códigos pueden variar según el orden interno de construcción del árbol para frecuencias iguales.)

## Posibles Mejoras
- Manejo de archivos de entrada/salida.
- Serialización del árbol/códigos para reutilizar en otra ejecución.
- Validación y pruebas unitarias (ej. JUnit) para cada método.
- Optimización de decodificación para grandes volúmenes.

## Licencia
Uso educativo. Puedes extenderlo libremente.

