package edu.ggranados.algoritmia.java.diagnostic;

import java.util.function.Function;

/**
 * 📘 D6 · Árbol binario / DFS · Media
 *
 *   Enunciado: Dada la raíz de un árbol binario, di si es un BST válido: para cada nodo, todo el subárbol izquierdo es < y todo el derecho es > (estricto).
 *
 *   public class TreeNode { int val; TreeNode left, right; TreeNode(int v) { val = v; } }
 *   public static boolean isValidBST(TreeNode root)
 *
 *       2          5
 *      / \        / \
 *     1   3      1   4      → false (4 < 5 en el subárbol derecho)
 *    → true         / \
 *                  3   6
 *
 *   - Árbol [2, 1, 3] → true
 *   - Árbol [5, 1, 4, null, null, 3, 6] → false
 *
 *
 *   Restricciones: 0 ≤ n ≤ 10^4, valores int completos (incluye Integer.MIN_VALUE / MAX_VALUE). Árbol vacío → true.
 */
public class D6BinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            buildString(this, sb, "", "");
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb, String prefix, String childrenPrefix) {
            if (node == null) {
                sb.append(prefix).append("null\n");
                return;
            }
            sb.append(prefix).append(node.val).append("\n");
            buildString(node.left, sb, childrenPrefix + "├── ", childrenPrefix + "│   ");
            buildString(node.right, sb, childrenPrefix + "└── ", childrenPrefix + "    ");
        }
    }

    public static boolean isValidBST(TreeNode root) {
        return visit(root,Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /* ⏱ Complejidad: O(n) tiempo, O(h) espacio. Es la óptima. */
    private static boolean visit(TreeNode root, long low, long high) {
        if (root == null) return true;

        if (root.val <= low || root.val >= high) {
            return false;
        }

        return visit(root.left, low, root.val) &&
                visit(root.right, root.val, high);
    }

    public static void main(String[] args) {
        test(D6BinaryTree::isValidBST);
    }

    public static void test(Function<TreeNode, Boolean> impl) {
        TreeNode uno = new TreeNode(1);
        TreeNode dos = new TreeNode(2);
        TreeNode tres = new TreeNode(3);
        dos.left = uno;
        dos.right = tres;
        System.out.println(dos);
        assert impl.apply(dos);

        uno = new TreeNode(1);
        tres = new TreeNode(3);
        TreeNode cuatro = new TreeNode(4);
        TreeNode cinco = new TreeNode(5);
        TreeNode seis = new TreeNode(6);
        cinco.left = uno;
        cinco.right = cuatro;
        cuatro.left = tres;
        cuatro.right = seis;
        System.out.println(cinco);
        assert !impl.apply(cinco);

        cinco = new TreeNode(5);
        TreeNode siete = new TreeNode(7);
        cuatro = new TreeNode(4);
        cinco.right = siete;
        siete.left = cuatro;
        System.out.println(cinco);
        assert !impl.apply(cinco);

        TreeNode min = new TreeNode(Integer.MIN_VALUE);
        System.out.println(min);
        assert impl.apply(min);

        TreeNode max = new TreeNode(Integer.MAX_VALUE);
        System.out.println(max);
        assert impl.apply(max);

        TreeNode cero = new TreeNode(0);
        cero.left = min;
        cero.right = max;
        System.out.println(cero);
        assert impl.apply(cero);

        TreeNode null_ = null;
        System.out.println(null_);
        assert impl.apply(null_);
    }

}

/**
 *   🧠 Idea clave: DFS con rango acumulado (lo, hi). Cada nodo hereda un rango válido de sus ancestros y lo estrecha al bajar.
 *   - Imagen: particiones de un índice de BD. Cada hoja debe caer dentro del rango de todos los separadores por encima, no solo del último.
 *   - Reconocerlo: "BST válido", "propiedad que depende de ancestros" → bajar bounds. No comparar con el hijo directo.
 *   - Alternativa: inorder debe salir estrictamente creciente.
 */