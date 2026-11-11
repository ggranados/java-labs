---
name: simulacro
description: Simulacro de entrevista técnica de algoritmos en Java. Entrega 2 problemas de dificultad media con cronómetro de 40 minutos y evalúa estilo entrevista. Usar cuando el usuario escriba "simulacro".
disable-model-invocation: true
---

# Simulacro (estilo entrevista)

Reglas generales y formato de evaluación: ver `CLAUDE.md`.

## Entrega

- **2 problemas medios**, originales, sin ambigüedad, en un solo mensaje.
- Elegir temas de lo ya visto según `progreso.md`, **priorizando puntos débiles** (fallas abiertas y repasos pendientes). Mezclar patrones distintos.
- Formato de ENUNCIADO de `CLAUDE.md` en cada uno, **sin Tip** y sin revelar el patrón.
- Cronómetro total: **40 min** para ambos (~20 c/u). Sin IDE ni IA. Pedirle que avise cuándo empieza y que envíe su código al terminar o al agotar el tiempo.

## Evaluación (estilo entrevista)

Por problema, formato de EVALUACIÓN de `CLAUDE.md` más:

- **Comunicación/enfoque**: ¿reconoció el patrón? ¿planteó fuerza bruta → óptimo? ¿consideró casos borde antes de codear?
- **Tiempo usado** vs objetivo.
- **Veredicto de entrevista**: pasa / borderline / no pasa, con una línea de razón. Sin suavizar.

Probar con casos borde (vacío, un elemento, duplicados, negativos, overflow, null). Verificar la solución de referencia ejecutándola con `javac`/`java` antes de enviarla.

## Cierre

- Resumen: 2 resultados, tiempo total, fallas (máx. 3 en total).
- Bloques 📌 ESTADO (uno por problema), marcando `Simulacro` como tema, con `repasar en: Día X, Y`.
- No dar la solución antes de que el usuario envíe su intento, salvo que escriba "solución".
