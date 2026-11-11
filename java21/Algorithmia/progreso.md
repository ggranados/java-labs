# Progreso

## 📌 ESTADO

- **Día actual:** 7 (pendiente: `siguiente` → D7, repaso de D5: lista in-place)
- **Fallas abiertas:** casos borde (null/vacío/extremos), patrón in-place (listas), DFS con estado heredado (árboles), tests escritos desde el código y no desde el enunciado
- **Repasos pendientes:** D5 → Día 7, 10 · D6 → Día 8, 11 · D4 (fin de pila) → Día 12

## Mapa de habilidades (Diagnóstico, D1–D6)

| Tema | Nivel | Falla principal |
|---|---|---|
| Hash map (D1) | 3 | Ninguna visible. Fuerza bruta → hash lookup. |
| Strings / two pointers (D2) | 3 | Primera versión con O(n) de espacio (streams). |
| Sliding window (D3) | 2 | Primera versión O(n·k) con reinicio de ventana. |
| Stack (D4) | 2 | Fin de pila (`"(()"` → `true`) y pila vacía. |
| Linked list (D5) | 1–2 | No reconoce in-place. `null` → NPE. |
| Árbol / DFS (D6) | 1–2 | Compara con el padre, no con ancestros. Centinela `int`. |

Niveles: 0 olvidado · 1 reconoce · 2 resuelve con esfuerzo · 3 fluido.
Nota: niveles de D1–D4 inferidos de los archivos y commits (sin evaluaciones originales).

## Patrones de falla recurrentes

1. `[caso borde]` (4 de 6): null, vacío, fin de estructura, extremos de `int`.
2. `[patrón no reconocido]`: primer intento ingenuo (copia, reinicio, fuerza bruta); el óptimo llega tras feedback.
3. Tests que validan el bug (D5 `populate` invertido, D6 `assert !impl.apply(min)`). Escribir asserts desde el enunciado, antes del código.

Checklist previo a entregar: `null`, vacío, 1 elemento, duplicados, extremos.

## Plan de 4 semanas (ajustado)

| Semana | Contenido |
|---|---|
| 1 (actual) | D7: repaso D5 (ciclo o merge de 2 listas, in-place). Two pointers sobre array ordenado, intervalos. |
| 2 | Queue, binary search (y sobre respuesta), sorting aplicado. Repaso D6 en Día 8 y 11. Stack monótono. |
| 3 | Árboles: BFS por niveles, LCA, altura/diámetro (DFS con estado). Backtracking, heaps/top-K. |
| 4 | Grafos (BFS/DFS, componentes, topological sort, Dijkstra), DP 1D/2D, bits. Cierre con `simulacro`. |

## Bitácora (formato: Día N | tema | resultado | tiempo | fallas | repasar en)

```
Día 1 | hash map twoSum        | óptimo       | ?  | -                           | -
Día 2 | strings palíndromo     | óptimo       | ?  | espacio O(n) (resuelta)     | -
Día 3 | sliding window         | óptimo       | ?  | O(n·k) (resuelta)           | -
Día 4 | stack paréntesis       | óptimo       | ?  | caso borde fin de pila      | Día 12
Día 5 | linked list reverse    | correcto     | 30 | null en copia, in-place     | Día 7, 10
Día 6 | BST validate           | correcto     | 25 | patrón ancestros, centinela | Día 8, 11
```
