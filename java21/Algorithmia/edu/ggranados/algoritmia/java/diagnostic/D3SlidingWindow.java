package edu.ggranados.algoritmia.java.diagnostic;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;

import java.util.HashMap;
import java.util.function.Function;

/**
 * 📘 D3 · Sliding window · Media
 * <p>
 * Enunciado: Dado un String, devuelve la longitud de la subcadena más larga sin caracteres repetidos.
 * <p>
 * public static int longestUniqueSubstring(String s)
 * <p>
 * - "abcabcbb" → 3 ("abc")
 * - "pwwkew" → 3 ("wke")
 * <p>
 * Restricciones: 0 ≤ s.length() ≤ 5·10^4, ASCII. Casos borde: vacío, todos iguales ("bbbb" → 1).
 */
public class D3SlidingWindow {

    /* Óptima: O(n) tiempo, O(min(n, alfabeto)) espacio. */
    public static int getLongestSubstringLength(String string) {

        int l = 0;
        int r = 0;
        int best = 0;
        Map<Character, Integer> last = new HashMap<>();

        for (; r < string.length(); r++) {

            Character current = string.charAt(r);
            Integer lastAppIndx = last.get(current);
            if (lastAppIndx != null && lastAppIndx >= l) {
                l = lastAppIndx + 1;
            }

            best = Math.max(best, r + 1 - l);
            last.put(current, r);

        }


        return best;
    }


    /* Reinicia en outer + 1 y vuelve a llenar el Set. Es O(n·k), con k = tamaño máximo de ventana.
    En ASCII, k ≤ 128, así que con n = 5·10^4 corre en ~100 ms. Aceptable aquí,
    pero el peor caso crece a O(n²) si el alfabeto no está acotado. */
    public static int getLongestSubstringLengthOn2(String string) {

        int maxSize = 0;
        int inner = 0;
        int outer = 0;
        Set<Character> bag = new HashSet<>();

        for (int c = 0; c < string.length(); ) {
            if (bag.contains(string.charAt(c))) {
                outer++;
                c = outer;
                bag.clear();
                maxSize = Math.max(inner, maxSize);
                inner = 0;
            }
            bag.add(string.charAt(c));
            c++;
            inner++;
        }

        return Math.max(inner, maxSize);
    }

    public static void test(Function<String, Integer> impl) {
        long length;
        String abcabcbb = "abcabcbb";
        length = impl.apply(abcabcbb);
        assert length != 0;
        assert length == 3;
        System.out.println(abcabcbb + " -> " + length);

        String pwwkew = "pwwkew";
        length = impl.apply(pwwkew);
        assert length != 0;
        assert length == 3;
        System.out.println(pwwkew + " -> " + length);

        String a = "a";
        length = impl.apply(a);
        assert length != 0;
        assert length == 1;
        System.out.println(a + " -> " + length);

        String abc = "abc";
        length = impl.apply(abc);
        assert length != 0;
        assert length == 3;
        System.out.println(abc + " -> " + length);

        String ab = "ab";
        length = impl.apply(ab);
        assert length != 0;
        assert length == 2;
        System.out.println(ab + " -> " + length);

        String dvdf = "dvdf";
        length = impl.apply(dvdf);
        assert length != 0;
        assert length == 3;
        System.out.println(dvdf + " -> " + length);

        String aab = "aab";
        length = impl.apply(aab);
        assert length != 0;
        assert length == 2;
        System.out.println(aab + " -> " + length);

        String tmmzuxt = "tmmzuxt";
        length = impl.apply(tmmzuxt);
        assert length != 0;
        assert length == 5;
        System.out.println(tmmzuxt + " -> " + length);
    }

    public static void main(String[] args) {
        test(D3SlidingWindow::getLongestSubstringLengthOn2);
        test(D3SlidingWindow::getLongestSubstringLength);
    }
}

/**
 * 🧠 Idea clave: sliding window con left que solo avanza.
 * - Imagen: es un buffer deslizante sobre un stream. right consume, y cuando entra un duplicado,
 * left expulsa elementos hasta que vuelve a ser válido. Nunca retrocede ni recalcula lo ya visto.
 * - Cuándo reconocerlo: "subcadena/subarray más largo/corto" con una condición que se rompe y se repara moviendo el borde izquierdo.
 * <p>
 * s = "dvdf"      last[] = última posición de cada char
 * r=0 'd'  left=0  win=[d]      best=1
 * r=1 'v'  left=0  win=[d v]    best=2
 * r=2 'd'  last[d]=0 ≥ left → left=1   win=[v d]   best=2
 * r=3 'f'  left=1  win=[v d f]  best=3 ✅
 */
