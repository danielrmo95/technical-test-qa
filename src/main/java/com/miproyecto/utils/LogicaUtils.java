package com.miproyecto.utils;

import java.util.*;

/**
 * Utilidades para operaciones de lógica y algoritmos.
 *
 * @author technical-test-qa
 * @version 1.0
 */
public class LogicaUtils {

    /**
     * Indica si el número es primo.
     *
     * @param numero entero positivo a evaluar
     * @return "Verdadero" si es primo, "Falso" en caso contrario
     * @throws IllegalArgumentException si numero &lt;= 0
     */
    public static String esNumeroPrimo(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        if (numero == 1) {
            return "Falso";
        }
        if (numero == 2) {
            return "Verdadero";
        }
        if (numero % 2 == 0) {
            return "Falso";
        }
        int limite = (int) Math.sqrt(numero);
        for (int i = 3; i <= limite; i += 2) {
            if (numero % i == 0) {
                return "Falso";
            }
        }
        return "Verdadero";
    }

    /**
     * Ordena las palabras de la cadena alfabéticamente.
     *
     * @param cadena palabras separadas por espacios
     * @return cadena con las palabras ordenadas, o el mismo valor si null/vacía
     */
    public static String ordenarPalabras(String cadena) {
        if (cadena == null || cadena.trim().isEmpty()) {
            return cadena;
        }
        String[] palabras = cadena.trim().split("\\s+");
        Arrays.sort(palabras, String.CASE_INSENSITIVE_ORDER);
        String resultado = String.join(" ", palabras);
        System.out.println("Palabras ordenadas: " + resultado);
        return resultado;
    }

    /**
     * Indica si la cadena es un palíndromo (se lee igual en ambos sentidos).
     *
     * @param cadena texto a evaluar
     * @return "Verdadero" si es palíndromo, "Falso" en caso contrario
     */
    public static String esPalindromo(String cadena) {
        if (cadena == null) {
            return "Falso";
        }
        String cadenaLimpia = cadena.replaceAll("\\s+", "").toLowerCase();
        if (cadenaLimpia.isEmpty()) {
            return "Falso";
        }
        int inicio = 0;
        int fin = cadenaLimpia.length() - 1;
        while (inicio < fin) {
            if (cadenaLimpia.charAt(inicio) != cadenaLimpia.charAt(fin)) {
                return "Falso";
            }
            inicio++;
            fin--;
        }
        return "Verdadero";
    }

    /**
     * Genera los primeros n términos de la secuencia de Fibonacci (0, 1, 1, 2, 3, ...).
     *
     * @param n cantidad de términos a generar
     * @return array con los primeros n números de Fibonacci
     * @throws IllegalArgumentException si n &lt;= 0
     */
    public static int[] generarFibonacci(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n debe ser mayor que 0");
        }
        int[] resultado = new int[n];
        if (n >= 1) {
            resultado[0] = 0;
        }
        if (n >= 2) {
            resultado[1] = 1;
        }
        for (int i = 2; i < n; i++) {
            resultado[i] = resultado[i - 1] + resultado[i - 2];
        }
        return resultado;
    }

    /**
     * Devuelve dos elementos del array cuya suma sea igual al objetivo.
     *
     * @param numeros  array de enteros
     * @param objetivo suma buscada
     * @return par de valores que suman objetivo, o null si no existe
     */
    public static int[] encontrarParSuma(int[] numeros, int objetivo) {
        if (numeros == null || numeros.length < 2) {
            return null;
        }
        Map<Integer, Integer> numerosVistos = new HashMap<>();
        for (int i = 0; i < numeros.length; i++) {
            int complemento = objetivo - numeros[i];
            if (numerosVistos.containsKey(complemento)) {
                return new int[]{complemento, numeros[i]};
            }
            numerosVistos.put(numeros[i], i);
        }
        return null;
    }
}
