package org.example.compresion;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Huffman {

    public static void construirArbolHuffman(String texto) {
        // Paso 1: Verificar si el texto está vacío
        if (texto == null || texto.isEmpty()) {
            System.out.println("El texto está vacío. No hay nada que comprimir.");
            return;
        }

        // Paso 2: Crear un mapa de frecuencias
        HashMap<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // Paso 3: Crear una cola de prioridad que ordene los nodos por frecuencia
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(
            Comparator.comparingInt(Nodo::getFrecuencia)
        );

        // Paso 4: Crear nodos hoja con cada carácter y frecuencia, y agregarlos a la cola
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            colaPrioridad.add(new Nodo(entry.getKey(), entry.getValue()));
        }

        // Paso 5: Combinar repetidamente los nodos con menor frecuencia
        while (colaPrioridad.size() > 1) {
            // Sacar los dos primeros nodos de la cola
            Nodo izquierdo = colaPrioridad.poll();
            Nodo derecho = colaPrioridad.poll();

            // Crear un nuevo nodo interno cuya frecuencia sea la suma de ambos
            int sumaFrecuencias = izquierdo.getFrecuencia() + derecho.getFrecuencia();
            Nodo nodoInterno = new Nodo(null, sumaFrecuencias, izquierdo, derecho);

            // Volver a agregar el nuevo nodo a la cola
            colaPrioridad.add(nodoInterno);
        }

        // Paso 6: Obtener la raíz del árbol de Huffman
        Nodo raiz = colaPrioridad.poll();

        // Paso 7: Crear un mapa para almacenar los códigos binarios de Huffman
        HashMap<Character, String> codigoHuffman = new HashMap<>();

        // Paso 8: Llamar a la función codifica()
        codifica(raiz, "", codigoHuffman);

        // Paso 9: Mostrar los códigos generados en consola
        System.out.println("Códigos de Huffman generados:");
        for (Map.Entry<Character, String> entry : codigoHuffman.entrySet()) {
            System.out.println("'" + entry.getKey() + "' : " + entry.getValue());
        }
        System.out.println();

        // Paso 10: Construir el texto comprimido
        StringBuilder textoComprimido = new StringBuilder();
        for (char c : texto.toCharArray()) {
            textoComprimido.append(codigoHuffman.get(c));
        }

        // Paso 11: Mostrar el texto original, el código generado y decodificar
        System.out.println("Texto original: " + texto);
        System.out.println("Longitud original: " + texto.length() + " caracteres");
        System.out.println();

        System.out.println("Texto comprimido (binario): " + textoComprimido.toString());
        System.out.println("Longitud comprimida: " + textoComprimido.length() + " bits");
        System.out.println();

        // Decodificar para verificar
        System.out.print("Texto decodificado: ");
        int index = -1;
        while (index < textoComprimido.length() - 1) {
            index = decodifica(raiz, index, textoComprimido);
        }
        System.out.println();
    }

    public static void codifica(Nodo raiz, String str, Map<Character, String> codigoHuffman) {
        if (raiz == null) {
            return;
        }

        if (raiz.getIzq() == null && raiz.getDer() == null) {
            String codigo = str.length() > 0 ? str : "1";
            codigoHuffman.put(raiz.getCaracter(), codigo);
            return;
        }

        codifica(raiz.getIzq(), str + "0", codigoHuffman);
        codifica(raiz.getDer(), str + "1", codigoHuffman);
    }

    public static int decodifica(Nodo raiz, int index, StringBuilder sb) {
        if (raiz == null) {
            return index;
        }

        if (raiz.getIzq() == null && raiz.getDer() == null) {
            System.out.print(raiz.getCaracter());
            return index;
        }

        index++;

        if (sb.charAt(index) == '0') {
            raiz = raiz.getIzq();
        } else {
            raiz = raiz.getDer();
        }

        return decodifica(raiz, index, sb);
    }

}

