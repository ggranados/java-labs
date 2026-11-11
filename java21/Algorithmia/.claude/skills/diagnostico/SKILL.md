---
name: diagnostico
description: Fase 1 del plan de algoritmos en Java. Entrega los 6 ejercicios de diagnóstico, evalúa cada solución muy breve y cierra con el MAPA DE HABILIDADES y el plan de 4 semanas. Usar solo en la primera sesión o cuando el usuario diga "rehacer diagnóstico".
disable-model-invocation: true
---

# Diagnóstico (Fase 1)

Solo mide. **No enseñar teoría.** Sin tips. Estilo y reglas generales: ver `CLAUDE.md`.

## Paso 1: entregar ejercicios

Un solo mensaje con 6 ejercicios Fácil/Media. Cada uno con enunciado + firma Java + 2 ejemplos entrada → salida. Originales, sin ambigüedad.

| ID | Tema |
|---|---|
| D1 | Hash map (conteo/lookup, tipo Two Sum) |
| D2 | Strings (palíndromo / anagramas / parsing) |
| D3 | Two pointers o sliding window |
| D4 | Stack (paréntesis balanceados o similar) |
| D5 | Linked list (invertir o detectar ciclo) |
| D6 | Árbol binario (DFS: profundidad / validar BST) |

Indicar: 15–20 min por ejercicio, sin IDE ni IA. Puede enviar todos juntos o de a uno.

## Paso 2: evaluar

Al recibir cada solución, usar el formato de EVALUACIÓN de `CLAUDE.md` pero **muy breve**: veredicto, caso que falla (si hay), complejidad, fallas (máx. 3), línea 📌 ESTADO. Solución de referencia solo en 1–3 líneas clave, no completa salvo que sea necesaria para corregir.

Verificar la corrección (compilar/ejecutar con `javac`/`java` si es posible) antes de dar veredicto.

## Paso 3: cierre (cuando estén los 6)

1. **MAPA DE HABILIDADES**:

   | Tema | Nivel | Falla principal |
   |---|---|---|

   Niveles: 0 olvidado · 1 reconoce · 2 resuelve con esfuerzo · 3 fluido.

2. **Plan de 4 semanas ajustado**: qué se acelera (nivel 3), qué se refuerza (nivel 0–1). Base: Sem 1 arrays/strings/hash/two pointers/sliding window; Sem 2 stack/queue/binary search/linked lists/sorting; Sem 3 árboles/backtracking/heaps; Sem 4 grafos/DP/bits + repaso mixto.

3. Bloques 📌 ESTADO consolidados para copiar a `progreso.md`. Crear `progreso.md` en la raíz si no existe y el usuario lo acepta.
