# Patrones de algoritmos: guía mental del diagnóstico (D1–D6)

## Mapa rápido

Cada enunciado trae una señal; la señal elige el patrón. Primero identifica la señal, luego la estructura.

| Señal en el enunciado | Patrón | Estructura Java | Costo típico |
| --- | --- | --- | --- |
| "dos elementos que sumen", "¿ya lo vi?", contar | Hash lookup | HashMap / HashSet | O(n) tiempo, O(n) espacio |
| Palíndromo, extremos, array ordenado, "in-place" | Two pointers | dos índices `i`, `j` | O(n), O(1) |
| Subcadena o subarray contiguo, "más largo" o "más corto" con condición | Sliding window | `l`, `r` + Map/Set/array | O(n) |
| Anidación, "último en abrir, primero en cerrar", deshacer | Stack | `ArrayDeque` | O(n) |
| Lista: invertir, ciclo, mitad, "O(1) de espacio" | Pointer reversal / fast-slow | `prev`, `curr`, `next` | O(n), O(1) |
| Árbol: validar, profundidad, propiedad por subárbol | DFS recursivo con estado | recursión + parámetros | O(n), O(h) |

## 1. Hash lookup (D1 · Two Sum)

Cambia un escaneo por una consulta O(1): guarda lo que ya viste y pregunta por lo que necesitas. Es un índice de BD frente a un full scan.

- **Cuándo:** "dos elementos que cumplan una relación" en un array sin ordenar, contar frecuencias, detectar duplicados, "¿ya lo vi?".
- **Cómo:** una pasada. Por cada `x`, calcula lo que le falta (`target - x`), consulta el mapa y *después* inserta `x`. Consultar antes de insertar evita emparejar un elemento consigo mismo.
- **Java:** `HashMap<Integer,Integer>` valor→índice. `map.get` devuelve `Integer` (`null` si no existe). Para contar: `merge(k, 1, Integer::sum)` o `getOrDefault`. Si solo importa la existencia, `HashSet`.
- **Trampas:** duplicados (`[3,3]`), overflow en `a + b` cuando los valores se acercan al límite de `int`, comparar `Integer` con `==` en vez de `equals`.
- **Costo:** O(n) tiempo, O(n) espacio. Compras tiempo con memoria.
- **Extiende a:** Subarray Sum = K (prefix sum + hash), Group Anagrams, Longest Consecutive Sequence, 3Sum (ordenar + two pointers), LRU Cache (HashMap + lista doble).

## 2. Two pointers (D2 · Palíndromo)

Dos índices recorren la estructura con una regla de avance, en lugar de un doble `for` O(n²). Cada paso descarta al menos un elemento, por eso es O(n). Hay dos variantes: extremos hacia el centro, o dos punteros en el mismo sentido (lento y rápido).

- **Cuándo:** palíndromo, array ordenado con suma objetivo, filtrar o compactar in-place, merge de dos arrays ordenados.
- **Cómo:** `i = 0, j = n - 1; while (i < j)`. En cada lado salta lo irrelevante, compara y avanza ambos.
- **Java:** `charAt` es O(1). `Character.isLetterOrDigit` y `Character.toLowerCase` evitan crear Strings intermedios. Un stream o `replaceAll` para "limpiar" primero cuesta O(n) de espacio extra.
- **Trampas:** los `while` internos también necesitan `i < j`, o los punteros se cruzan. String vacío o solo símbolos devuelve `true`. En longitud impar el elemento central no se compara consigo mismo. `char` no es un code point Unicode completo.
- **Costo:** O(n) tiempo, O(1) espacio.
- **Extiende a:** Valid Palindrome II, Two Sum II (array ordenado), Container With Most Water, 3Sum, Remove Duplicates, Merge Sorted Array, Dutch National Flag.

## 3. Sliding window (D3 · Subcadena sin repetidos)

Una ventana contigua `[l, r]`: `r` se expande y `l` se contrae para restaurar un invariante. Cada índice entra y sale una sola vez, por eso es O(n). Es como una ventana de tiempo en métricas: lo que cae fuera deja de contar.

- **Cuándo:** "subcadena o subarray *contiguo*", "más largo" o "más corto", con una condición sobre el contenido (sin repetidos, a lo sumo k distintos, suma ≥ s).
- **Cómo:** 1) define el invariante; 2) `for r`: incorpora `s[r]`; 3) si se viola, mueve `l`; 4) actualiza el mejor con `r - l + 1`.
- **Variante con salto:** guarda el último índice de cada carácter y haz `l = last + 1` en vez de avanzar paso a paso.
- **Java:** `Map<Character,Integer>` para último índice, `Set<Character>` para pertenencia, `int[128]` o `int[256]` si el alfabeto está acotado (más rápido que un HashMap).
- **Trampas:** olvidar `last >= l` al saltar (un índice viejo ya fuera de la ventana), longitud off-by-one, string vacío. Reiniciar la ventana desde cero en cada fallo degrada a O(n·k).
- **Costo:** O(n) tiempo, O(min(n, alfabeto)) espacio.
- **Extiende a:** Minimum Window Substring, Longest Substring con a lo sumo K distintos, Permutation in String, Longest Repeating Character Replacement, Sliding Window Maximum (deque monótono). Con números negativos la ventana deja de funcionar y entra prefix sum.

## 4. Stack (D4 · Paréntesis balanceados)

LIFO: lo último que abriste es lo primero que debes cerrar. Es una pila de transacciones anidadas, o el call stack de tu programa.

- **Cuándo:** pares anidados, parsers y expresiones, deshacer, "siguiente elemento mayor" (stack monótono), simular recursión.
- **Cómo:** al abrir, `push`. Al cerrar, verifica que la pila no esté vacía, haz `pop` y compara el par. Al final la pila debe quedar vacía. Hay tres puntos de fallo: cierre sin apertura, par incorrecto y aperturas sobrantes.
- **Java:** `Deque<Character> stack = new ArrayDeque<>()` con `push`, `pop`, `peek`. No uses `Stack`: es legacy, sincronizado y extiende `Vector`. `pop()` en una pila vacía lanza `NoSuchElementException`; `poll()` devuelve `null`. Un `Map` de apertura→cierre evita la cadena de `if`.
- **Trampas:** olvidar el chequeo de vacío antes del `pop`, olvidar `stack.isEmpty()` al final, `ArrayDeque` no admite `null`. Longitud impar es `false` sin recorrer.
- **Costo:** O(n) tiempo, O(n) espacio.
- **Extiende a:** Min Stack, Evaluate Reverse Polish Notation, Daily Temperatures y Next Greater Element (stack monótono), Largest Rectangle in Histogram, Decode String, DFS iterativo.

## 5. In-place en listas (D5 · Invertir lista)

*In-place* significa O(1) de espacio extra: modificas la estructura existente en vez de copiarla. En una lista, eso es reencajar los `next` del mismo nodo. No mueves las canciones de la playlist; cambias a cada una el "siguiente" por el "anterior".

- **Cuándo:** invertir, palíndromo, reorder, ciclo, punto medio, o cualquier enunciado que pida "O(1) de espacio".
- **Cómo:** tres punteros `prev`, `curr`, `next`. Invariante: `prev` es la cabeza de lo ya invertido y `curr` la de lo pendiente. Guarda `next` *antes* de cortar `curr.next`, o pierdes el resto de la lista.
- **Otros patrones de lista:** fast/slow (ciclo, mitad), dummy head (merge, borrar nodos), invertir por tramos.
- **Java:** una referencia copia el puntero, no el objeto. `ListNode` es una clase propia; el `LinkedList` del JDK es otra cosa. La versión recursiva usa O(n) de pila: con listas muy largas puede dar `StackOverflowError`.
- **Trampas:** `head == null` (con `prev = null` y `while (curr != null)` ya queda cubierto), un solo nodo, perder `next`, dejar un `next` viejo que cree un ciclo, mutar la entrada sin que esté permitido.
- **Costo:** O(n) tiempo, O(1) espacio. Copiar nodos también es O(n) tiempo, pero O(n) espacio.
- **Extiende a:** Linked List Cycle (Floyd), Middle of the Linked List, Merge Two Sorted Lists (dummy), Palindrome Linked List, Reverse Nodes in k-Group, Reorder List, Remove Nth From End, LRU Cache.

## 6. DFS con estado (D6 · Validar BST)

DFS recursivo: resuelves un nodo delegando a sus subárboles y pasas hacia abajo el contexto que heredan. Es como los permisos heredados en un árbol de carpetas, o los rangos de un índice particionado: cada hoja debe cumplir las reglas de *todos* sus ancestros.

- **Dos modos:** *top-down* pasa información hacia abajo (rango `lo/hi`, profundidad, suma acumulada). *Bottom-up* devuelve información hacia arriba (altura, diámetro, ¿balanceado?). Validar BST es top-down.
- **Cuándo:** "para cada nodo...", propiedades que dependen de ancestros o del subárbol. Pregúntate dos cosas: ¿qué necesito saber de mis ancestros?, ¿qué debo devolverle a mi padre?
- **Cómo:** caso base `null`; chequeo del nodo; recursión a izquierda y derecha con el estado actualizado; combinar con `&&`, `max` o suma. El cortocircuito de `&&` corta ramas inútiles.
- **Java:** un método privado auxiliar con parámetros extra (mejor que lambdas que capturan estado). Cada llamada ocupa pila: O(h) de espacio, y un árbol degenerado de 10⁴ nodos todavía cabe. Con tamaños mayores, DFS iterativo con `Deque`. BFS por niveles usa `Queue` (`ArrayDeque`).
- **Centinela:** un valor de relleno que representa "sin límite". Debe quedar fuera del dominio de los datos. Con `int` los extremos son datos reales, por eso se usa `long` (o `Integer` con `null`).
- **Trampas:** comparar solo con el padre y no con los ancestros, centinela dentro del dominio, duplicados (estricto o no), olvidar el caso `null`.
- **Costo:** O(n) tiempo, O(h) espacio.
- **Extiende a:** Maximum Depth, Balanced Binary Tree, Diameter, Lowest Common Ancestor, Path Sum, Kth Smallest en BST (inorder), BFS por niveles, backtracking (DFS que deshace), DFS en grafos con `visited`.

## Checklist antes de entregar

La falla más repetida del diagnóstico fue el caso borde. Escribe los asserts desde el enunciado, antes del código.

- [ ] `null` y vacío
- [ ] Un solo elemento
- [ ] Duplicados y todos iguales
- [ ] Extremos: `Integer.MIN_VALUE` y `MAX_VALUE`
- [ ] Orden inverso o ya ordenado
- [ ] Corrí con `java -ea` (sin `-ea` los `assert` no se ejecutan)

**Trampas de Java transversales**

- Overflow de `int`: `a + b`; usa `l + (r - l) / 2` en vez de `(l + r) / 2`; `Math.abs(Integer.MIN_VALUE)` sigue negativo.
- `Integer` con `==` falla fuera de -128..127: usa `equals`.
- `String` es inmutable: concatenar en un bucle es O(n²). Usa `StringBuilder`.
- Costo oculto de la librería: `ArrayList.remove(0)` es O(n), `substring` copia, `List.contains` es O(n) y `Set.contains` O(1).
- `ArrayDeque` en vez de `Stack`; `ArrayDeque` no admite `null`.
- Recursión profunda: unas 10⁴ llamadas suelen caber; más allá puede dar `StackOverflowError`.
