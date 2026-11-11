package edu.ggranados.algoritmia.java.diagnostic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 📘 D1 · Hash map · Fácil
 * <p>
 * Enunciado: Dado un array de enteros y un target, devuelve los índices [i, j] (i < j) de dos elementos que sumen target.
 * Hay exactamente una solución.
 * No uses el mismo elemento dos veces.
 * <p>
 * public static int[] twoSum(int[] nums, int target)
 * <p>
 * - [2, 7, 11, 15], 9 → [0, 1]
 * - [3, 2, 4], 6 → [1, 2]
 * <p>
 * Restricciones: 2 ≤ n ≤ 10^5, valores entre -10^9 y 10^9. Cuidado con overflow y duplicados ([3, 3], 6).
 */
public class D1HashMap {

    /* Óptima: O(n) tiempo, O(n) espacio.
    * 🧠 Idea clave: hash lookup del complemento.
      - Imagen: es un índice de BD. En vez de escanear toda la tabla (full scan) por cada fila, guardas valor → índice y preguntas "¿ya vi target - x?" en O(1).
      - Cuándo reconocerlo: "encuentra dos elementos que cumplan una relación" sobre un array sin ordenar.

      nums = [3, 2, 4], target = 6      map: valor → índice
      i=0  x=3  need=3  map={}          no está  → put(3→0)
      i=1  x=2  need=4  map={3→0}       no está  → put(2→1)
      i=2  x=4  need=2  map={3→0,2→1}   ¡está!   → [1, 2]

      Consultas antes de insertar: así un elemento nunca se empareja consigo mismo y [3,3] funciona.
    * */
    public static int[] getPair(int[] arr, int target) {
        int[] pair = {-1, -1};

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int currentVal = arr[i];
            int needed = target - currentVal;
            Integer neededIndx = map.get(needed);
            if (neededIndx != null) {
                pair[0] = neededIndx;
                pair[1] = i;
                return pair;
            }
            map.put(currentVal, i);
        }


        return pair;
    }

    /*  O(n²) tiempo, O(1) espacio. Con n = 10^5 son ~5·10^9 comparaciones: timeout. */
    public static int[] getPairOn2(int[] arr, int target) {

        if (arr == null || arr.length == 0) return null;

        for (int i = 0; i < arr.length - 1; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] + arr[j] == target) return new int[]{i, j};

        return new int[]{-1, -1};
    }

    public static void test(BiFunction<int[], Integer, int[]> impl, String implName) {
        System.out.println("Testing: " + implName);
        int[] arr = {2, 7, 11, 15};
        int[] pair = impl.apply(arr, 9);
        assert pair != null;
        assert pair.length == 2;
        assert pair[0] == 0;
        assert pair[1] == 1;
        System.out.println("{ 2, 7, 11, 15 } -> " + pair[0] + " + " + pair[1] + " = 9");

        arr = new int[]{3, 2, 4};
        pair = impl.apply(arr, 6);
        assert pair != null;
        assert pair.length == 2;
        assert pair[0] == 1;
        assert pair[1] == 2;
        System.out.println("{ 3, 2, 4 } -> " + pair[0] + " + " + pair[1] + " = 6");

        arr = new int[]{1, 2, 3, 4};
        pair = impl.apply(arr, 7);
        assert pair != null;
        assert pair.length == 2;
        assert pair[0] == 2;
        assert pair[1] == 3;
        System.out.println("{ 1,2,3,4 } -> " + pair[0] + " + " + pair[1] + " = 7");
    }

    public static void main(String[] args) {
        test(D1HashMap::getPairOn2, "impl0n2");
        test(D1HashMap::getPair, "impl");
    }
}

/**
 * 🧠 Idea clave: hash lookup del complemento.
 * - Imagen: es un índice de BD. En vez de escanear toda la tabla (full scan) por cada fila, guardas valor → índice y preguntas "¿ya vi target - x?" en O(1).
 * - Cuándo reconocerlo: "encuentra dos elementos que cumplan una relación" sobre un array sin ordenar.
 */