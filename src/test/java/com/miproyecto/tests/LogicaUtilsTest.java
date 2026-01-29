package com.miproyecto.tests;

import com.miproyecto.utils.LogicaUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicaUtilsTest {

    // Tests para esNumeroPrimo
    @Test
    @DisplayName("Número primo - Debe retornar Verdadero para números primos")
    public void testEsNumeroPrimo_Verdadero() {
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(2));
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(3));
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(5));
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(7));
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(11));
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(13));
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(17));
        assertEquals("Verdadero", LogicaUtils.esNumeroPrimo(97));
    }

    @Test
    @DisplayName("Número primo - Debe retornar Falso para números no primos")
    public void testEsNumeroPrimo_Falso() {
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(1));
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(4));
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(6));
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(8));
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(9));
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(10));
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(15));
        assertEquals("Falso", LogicaUtils.esNumeroPrimo(100));
    }

    @Test
    @DisplayName("Número primo - Debe lanzar excepción para números no positivos")
    public void testEsNumeroPrimo_Excepcion() {
        assertThrows(IllegalArgumentException.class, () -> LogicaUtils.esNumeroPrimo(0));
        assertThrows(IllegalArgumentException.class, () -> LogicaUtils.esNumeroPrimo(-1));
        assertThrows(IllegalArgumentException.class, () -> LogicaUtils.esNumeroPrimo(-10));
    }

    // Tests para ordenarPalabras
    @Test
    @DisplayName("Ordenar palabras - Debe ordenar correctamente")
    public void testOrdenarPalabras() {
        assertEquals("casa gato perro", LogicaUtils.ordenarPalabras("perro gato casa"));
        assertEquals("banana manzana naranja", LogicaUtils.ordenarPalabras("naranja manzana banana"));
        assertEquals("dos tres uno", LogicaUtils.ordenarPalabras("tres dos uno"));
    }

    @Test
    @DisplayName("Ordenar palabras - Debe manejar mayúsculas y minúsculas")
    public void testOrdenarPalabras_MayusculasMinusculas() {
        assertEquals("Casa Gato Perro", LogicaUtils.ordenarPalabras("Perro Gato Casa"));
        assertEquals("banana Manzana naranja", LogicaUtils.ordenarPalabras("naranja Manzana banana"));
    }

    @Test
    @DisplayName("Ordenar palabras - Debe manejar cadenas vacías y null")
    public void testOrdenarPalabras_CasosEspeciales() {
        assertEquals("", LogicaUtils.ordenarPalabras(""));
        assertNull(LogicaUtils.ordenarPalabras(null));
    }

    // Tests para esPalindromo
    @Test
    @DisplayName("Palíndromo - Debe retornar Verdadero para palíndromos")
    public void testEsPalindromo_Verdadero() {
        assertEquals("Verdadero", LogicaUtils.esPalindromo("radar"));
        assertEquals("Verdadero", LogicaUtils.esPalindromo("ana"));
        assertEquals("Verdadero", LogicaUtils.esPalindromo("oso"));
        assertEquals("Verdadero", LogicaUtils.esPalindromo("reconocer"));
        assertEquals("Verdadero", LogicaUtils.esPalindromo("A man a plan a canal Panama"));
        assertEquals("Verdadero", LogicaUtils.esPalindromo("Anita lava la tina"));
    }

    @Test
    @DisplayName("Palíndromo - Debe retornar Falso para no palíndromos")
    public void testEsPalindromo_Falso() {
        assertEquals("Falso", LogicaUtils.esPalindromo("hola"));
        assertEquals("Falso", LogicaUtils.esPalindromo("mundo"));
        assertEquals("Falso", LogicaUtils.esPalindromo("java"));
        assertEquals("Falso", LogicaUtils.esPalindromo("programacion"));
    }

    @Test
    @DisplayName("Palíndromo - Debe manejar casos especiales")
    public void testEsPalindromo_CasosEspeciales() {
        assertEquals("Falso", LogicaUtils.esPalindromo(null));
        assertEquals("Falso", LogicaUtils.esPalindromo(""));
        assertEquals("Verdadero", LogicaUtils.esPalindromo("a"));
        assertEquals("Verdadero", LogicaUtils.esPalindromo("aa"));
    }

    // Tests para generarFibonacci
    @Test
    @DisplayName("Fibonacci - Debe generar la secuencia correcta")
    public void testGenerarFibonacci() {
        int[] resultado1 = LogicaUtils.generarFibonacci(5);
        assertArrayEquals(new int[]{0, 1, 1, 2, 3}, resultado1);

        int[] resultado2 = LogicaUtils.generarFibonacci(10);
        assertArrayEquals(new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34}, resultado2);

        int[] resultado3 = LogicaUtils.generarFibonacci(1);
        assertArrayEquals(new int[]{0}, resultado3);

        int[] resultado4 = LogicaUtils.generarFibonacci(2);
        assertArrayEquals(new int[]{0, 1}, resultado4);
    }

    @Test
    @DisplayName("Fibonacci - Debe lanzar excepción para n <= 0")
    public void testGenerarFibonacci_Excepcion() {
        assertThrows(IllegalArgumentException.class, () -> LogicaUtils.generarFibonacci(0));
        assertThrows(IllegalArgumentException.class, () -> LogicaUtils.generarFibonacci(-1));
        assertThrows(IllegalArgumentException.class, () -> LogicaUtils.generarFibonacci(-10));
    }

    // Tests para encontrarParSuma
    @Test
    @DisplayName("Suma de dos números - Debe encontrar el par correcto")
    public void testEncontrarParSuma() {
        int[] numeros = {1, 2, 3, 4, 5};
        int[] resultado = LogicaUtils.encontrarParSuma(numeros, 9);
        assertNotNull(resultado);
        assertEquals(2, resultado.length);
        // El par debe ser (4, 5) o (5, 4)
        assertTrue((resultado[0] == 4 && resultado[1] == 5) || 
                   (resultado[0] == 5 && resultado[1] == 4));
    }

    @Test
    @DisplayName("Suma de dos números - Debe retornar null si no encuentra par")
    public void testEncontrarParSuma_NoEncontrado() {
        int[] numeros = {1, 2, 3, 4, 5};
        assertNull(LogicaUtils.encontrarParSuma(numeros, 100));
        
        int[] numeros2 = {1, 2};
        assertNull(LogicaUtils.encontrarParSuma(numeros2, 10));
    }

    @Test
    @DisplayName("Suma de dos números - Debe manejar casos especiales")
    public void testEncontrarParSuma_CasosEspeciales() {
        assertNull(LogicaUtils.encontrarParSuma(null, 10));
        assertNull(LogicaUtils.encontrarParSuma(new int[]{1}, 10));
        assertNull(LogicaUtils.encontrarParSuma(new int[]{}, 10));
    }

    @Test
    @DisplayName("Suma de dos números - Debe encontrar par con números negativos")
    public void testEncontrarParSuma_NumerosNegativos() {
        int[] numeros = {-1, 2, 3, 4, 5};
        int[] resultado = LogicaUtils.encontrarParSuma(numeros, 1);
        assertNotNull(resultado);
        assertEquals(2, resultado.length);
        // El par debe ser (-1, 2) o (2, -1)
        assertTrue((resultado[0] == -1 && resultado[1] == 2) || 
                   (resultado[0] == 2 && resultado[1] == -1));
    }
}
