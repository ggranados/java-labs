package edu.ggranados.algoritmia.java.diagnostic;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 📘 D2 · Strings · Fácil
 * <p>
 * Enunciado: Dado un String, di si es palíndromo considerando solo caracteres alfanuméricos e ignorando mayúsculas/minúsculas.
 * <p>
 * public static boolean isPalindrome(String s)
 * <p>
 * - "A man, a plan, a canal: Panama" → true
 * - "race a car" → false
 * <p>
 * Restricciones: 0 ≤ s.length() ≤ 2·10^5. Cadena vacía o solo símbolos (".,") → true. Espacio extra O(1) es lo ideal.
 */
public class D2Strings {

    /* O(n) / O(1), la óptima */
    public static boolean isPalindrome(String string) {

        int i = 0, j = string.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(string.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(string.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(string.charAt(i)) != Character.toLowerCase(string.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /* O(n) tiempo, pero O(n) espacio. El stream crea un String por cada char, los une y luego toCharArray() copia todo otra vez.*/
    public static boolean isPalindromeOnTiempoOnEspacio(String string) {

        String normalized = string.chars().filter(Character::isLetterOrDigit).mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining()).toLowerCase();

        if (normalized.isBlank()) return true;

        char[] chars = normalized.toCharArray();

        int i = 0;
        int d = chars.length - 1;
        while (i <= d) {
            if (chars[i] != chars[d]) {
                return false;
            }
            i++;
            d--;
        }
        return true;
    }

    public static void test(Function<String, Boolean> impl) {
        String amarama = "amarama";
        boolean res = impl.apply(amarama);
        assert res;
        System.out.println(amarama + " is palindrome: " + res);

        String number = "123321";
        res = impl.apply(number);
        assert res;
        System.out.println(number + " is palindrome: " + res);

        String roma = "roma";
        res = impl.apply(roma);
        assert !res;
        System.out.println(roma + " is palindrome: " + res);

        String symbol = "#$%&";
        res = impl.apply(symbol);
        assert res;
        System.out.println(symbol + " is palindrome: " + res);

        String anita = "anitalavalatina";
        res = impl.apply(anita);
        assert res;
        System.out.println(anita + " is palindrome: " + res);

        String anitaSymbol = "&anitalavalatina#";
        res = impl.apply(anitaSymbol);
        assert res;
        System.out.println(anitaSymbol + " is palindrome: " + res);

        String empty = "";
        res = impl.apply(empty);
        assert res;
        System.out.println(empty + " is palindrome: " + res);
    }

    public static void main(String[] args) {
        test(D2Strings::isPalindromeOnTiempoOnEspacio);
        test(D2Strings::isPalindrome);
    }

}

/**
 * 🧠 Idea clave: two pointers desde los extremos, saltando basura.
 * - Imagen: es leer un log desde ambos lados a la vez ignorando las líneas de ruido. No copias el log, solo mueves dos cursores.
 * - Cuándo reconocerlo: "simétrico", "comparar extremos", "ignorar ciertos elementos".
 * <p>
 * s = "A man, a plan"      (i desde izq, j desde der)
 * i                j
 * A  ' ' m ...  n  a  l  p
 * i→'A'  j→'n'  distintos → false?  (no: comparar lowercase 'a' vs 'n' → false)
 * <p>
 * s = "a,b a"
 * i     j      'a' == 'a' → i++, j--
 * i j        ',' no es alfanumérico → i++
 * i,j      'b' == 'b' → fin → true
 * <p>
 * 🔧 Referencia (la tuya, corregida con O(1) espacio):
 * public static boolean isPalindrome(String s) {
 * int i = 0, j = s.length() - 1;
 * while (i < j) {
 * while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
 * while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
 * if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
 * return false;
 * i++; j--;
 * }
 * return true;
 * }
 * Con "", ".," o "#$%&" los punteros se cruzan y devuelve true sin casos especiales.
 */

