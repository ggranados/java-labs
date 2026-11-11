package edu.ggranados.algoritmia.java.diagnostic;

import java.util.List;
import java.util.function.Function;

/**
 * 📘 D5 · Linked list · Fácil/Media
 * <p>
 * Enunciado: Invierte una lista simplemente enlazada y devuelve la nueva cabeza.
 * <p>
 * public class ListNode { int val; ListNode next; ListNode(int v) { val = v; } }
 * public static ListNode reverse(ListNode head)
 * <p>
 * - 1 → 2 → 3 → null → 3 → 2 → 1 → null
 * - 5 → null → 5 → null
 * <p>
 * Restricciones: 0 ≤ n ≤ 5000. head == null → null. Ideal: iterativo, O(1) de espacio.
 */
public class D5LinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }

        public String toString() {
            return val + (next != null ? " → " + next : " → null");
        }

    }

    /* O(n) tiempo, O(n) espacio (copia nodos) */
    public static ListNode reverseOn(ListNode head) {
        ListNode newHead = null;

        for (ListNode c = head; c != null; c = c.next) {
            ListNode n = new ListNode(c.val);
            n.next = newHead;
            newHead = n;
        }

        return newHead;

    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void test(Function<ListNode, ListNode> impl, String implName) {
        System.out.println("Testing: " + implName);
        ListNode head = populate(1, 2, 3);
        System.out.print(head + " => ");
        ListNode newHead = impl.apply(head);
        assert newHead.val == 3;
        assert newHead.next.val == (2);
        assert newHead.next.next.val == (1);
        assert newHead.next.next.next == (null);
        System.out.println(newHead);

        head = populate(5);
        System.out.print(head + " => ");
        newHead = impl.apply(head);
        assert newHead.val == (head.val);
        assert newHead.next == (null);
        System.out.println(newHead);

        head = populate(null);
        System.out.print(head + " => ");
        newHead = impl.apply(head);
        assert newHead == null;
        System.out.println(newHead);

    }

    private static ListNode populate(Integer... vals) {
        if (vals == null || vals.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0), tail = dummy;
        for (Integer v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;

    }


    public static void main(String[] args) {
        test(D5LinkedList::reverseOn, "reverseOn");
        test(D5LinkedList::reverse, "reverse");
    }

}

/**
 * 🧠 Idea clave: in-place pointer reversal (3 punteros: prev, curr, next).
 * <p>
 * - Imagen: lista de reproducción donde cada canción apunta a la siguiente. No reordenas las canciones.
 * Cambias a cada una el "siguiente" por el "anterior". O(1) de espacio.
 * - Cuándo reconocerlo: "invierte", "reverse", palabras clave "in-place" / "O(1) espacio" en listas enlazadas.
 * Es la base de palíndromo en lista, reverse k-group y reorder list.
 * - Invariante: prev es la cabeza de la parte ya invertida. curr es la cabeza de la parte pendiente.
 * - Orden crítico: guardar next antes de cortar curr.next. Si no, pierdes el resto de la lista.
 * <p>
 * Inicio:  prev=null   curr=1
 * null        1 → 2 → 3 → null
 * <p>
 * Paso 1:  next=2; 1.next=null; prev=1; curr=2
 * null ← 1    2 → 3 → null
 * prev curr
 * <p>
 * Paso 2:  next=3; 2.next=1; prev=2; curr=3
 * null ← 1 ← 2    3 → null
 * prev  curr
 * <p>
 * Paso 3:  next=null; 3.next=2; prev=3; curr=null
 * null ← 1 ← 2 ← 3        curr=null → fin
 * prev  → return prev
 * <p>
 * - head == null: el while no corre y prev queda en null. No necesita caso especial.
 * - Un nodo: 1.next=null, devuelve 1.
 * - Complejidad: O(n) tiempo, O(1) espacio.
 */


/**
 * 🧠 Idea clave: in-place pointer reversal (3 punteros: prev, curr, next).
 * - Imagen: lista de reproducción donde cada canción apunta a la siguiente. No reordenas las canciones;
 * a cada una le cambias el "siguiente" por el "anterior". O(1) de espacio.
 * - Cuándo reconocerlo: "invierte", "reverse", "in-place", "O(1) espacio" en listas enlazadas.
 * Base de: palíndromo en lista, reverse k-group, reorder list.
 * - Invariante: prev = cabeza de la parte ya invertida; curr = cabeza de la parte pendiente.
 * - Orden crítico: guardar next ANTES de cortar curr.next, si no pierdes el resto.
 * <p>
 * Inicio:  prev=null   curr=1
 * null        1 → 2 → 3 → null
 * <p>
 * Paso 1:  next=2; 1.next=null; prev=1; curr=2
 * null ← 1    2 → 3 → null
 * <p>
 * Paso 2:  next=3; 2.next=1; prev=2; curr=3
 * null ← 1 ← 2    3 → null
 * <p>
 * Paso 3:  next=null; 3.next=2; prev=3; curr=null → return prev
 * null ← 1 ← 2 ← 3
 * <p>
 * 🔧 Referencia:
 * ListNode prev = null, curr = head;
 * while (curr != null) {
 * ListNode next = curr.next;
 * curr.next = prev;
 * prev = curr;
 * curr = next;
 * }
 * return prev;
 */
