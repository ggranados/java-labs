package edu.ggranados.algoritmia.java.diagnostic;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;

/**
 * 📘 D4 · Stack · Fácil/Media
 * <p>
 * Enunciado: Dado un String con solo ()[]{}, di si está balanceado: cada apertura se cierra con su par y en el orden correcto.
 * <p>
 * public static boolean isBalanced(String s)
 * <p>
 * - "{[()]}" → true
 * - "([)]" → false
 * <p>
 * Restricciones: 0 ≤ s.length() ≤ 10^5. Casos borde: vacío → true, "(" → false, ")" → false.
 */
public class D4Stack {

    public static boolean isBalanced(String string) {

        Map<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');

        Deque<Character> stack = new ArrayDeque<>();

        for (int c = 0; c < string.length(); c++) {
            Character chr = string.charAt(c);

            if (pairs.containsKey(chr)) {
                stack.push(chr);
                continue;
            }

            if (stack.isEmpty()) return false;

            if (!chr.equals(pairs.get(stack.pop()))) {
                return false;
            }

        }

        return stack.isEmpty();

    }

    public static void test() {
        String str = "{[()]}";
        boolean balanced = isBalanced(str);
        assert balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "";
        balanced = isBalanced(str);
        assert balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "([)]";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "]([)";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "(";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = ")";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "))";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "((";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "(()";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "())";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);

        str = "}{";
        balanced = isBalanced(str);
        assert !balanced;
        System.out.println(str + " is balanced " + balanced);
    }

    public static void main(String[] args) {
        test();
    }
}


/**
 * 🧠 Idea clave: stack para anidación (LIFO).
 * - Imagen: es una pila de transacciones anidadas. Cada BEGIN abre una; cada COMMIT debe cerrar la más reciente. Si llega un COMMIT sin transacción abierta, o queda una abierta al
 * terminar, el log es inválido.
 * - Cuándo reconocerlo: "el último que abrí es el primero que debo cerrar", pares anidados, deshacer, parsers.
 * <p>
 * s = "())"
 * (   push        stack: [ ( ]
 * )   pop → (     stack: [ ]       ok
 * )   pila vacía  → false ✅     (tu código aquí seguía)
 * <p>
 * s = "(()"
 * (   push   [ ( ]
 * (   push   [ ( ( ]
 * )   pop    [ ( ]
 * fin: stack no vacío → false ✅   (tu código devolvía true)
 */