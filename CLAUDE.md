# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repo shape

Not one app: a monorepo of independent Java self-study/practice projects (IntelliJ `.iml` per module, no root build file, no parent POM). Modules live under `javaNN/` folders (by Java version) and each is standalone; work from inside the module dir.

| Module | Build | Notes |
|---|---|---|
| `java17/Algorithms` | Maven, Java 17, JUnit 5 + log4j, Spring Boot parent | sort algorithms (`insert`, `merge`) behind `Sorter` interface; each has an `.md` explainer; `SortPerformanceTest` compares them |
| `java17/JavaExercises`, `java17/RemoveDuplicates`, `java17/Stack` | Maven (17 / 17 / 11), JUnit 5 | small exercises; `Stack` pom targets 11 despite folder |
| `java8/jersey-basic-rest` | Maven, WAR, Java 8, Jersey + Lombok + javafaker | needs servlet container |
| `java9/FunctionalProgramming`, `java17/Streams`, `java17/DesignPatterns`, `java17/AnnotatiosSample`, `java11/OCPJava11StudyGuide` | none (plain `src/`, IntelliJ only) | compile with `javac`/IntelliJ; OCP code under `cert.java11.ocp.chapterNN`; Streams code under `org.java17.streams.*` (has `.vscode/launch.json`); `AnnotatiosSample` has no branch, version assumed 17 |
| `java21/Algorithmia` | none (plain dirs, JDK 21, branch `java21/algorithmia`) | **different purpose** - see below |

## Commands

Maven modules (run in module dir):

```
mvn test
mvn test -Dtest=MergeSorterTest
mvn test -Dtest=MergeSorterTest#methodName
```

Non-Maven modules: `javac -d out $(find src -name '*.java')` then `java -cp out <fqcn>`; or single-file `java path/File.java`.

No lint config. Module Java versions differ (8–21); check the module's POM/`.iml` before using newer syntax.

## Algorithmia (tutor mode)

`java21/Algorithmia/` is an algorithm-interview practice sandbox where Claude acts as a tutor. It has its own `java21/Algorithmia/CLAUDE.md` (Spanish, strict rules: no solutions before user attempts, one exercise per day, fixed ENUNCIADO/EVALUACIÓN formats) and `java21/Algorithmia/progreso.md` (progress state). When working under `java21/Algorithmia/`, follow that file and read `progreso.md` first. Its exercises (`edu/ggranados/algoritmia/java/diagnostic/DN*.java`) self-check via `assert`; run with `java -ea`.

## Conventions

- Package roots: `edu.ggranados.java.*` (own code), `org.linkedinlearning.*` (course follow-along), `udemy.*`, `cert.java11.ocp.*`.
- Branches follow `javaNN/topic[/sub]` (e.g. `java21/algorithmia`, `java17/exercises`), merged to `master`.
- Folder layout mirrors the branch's version prefix: a module from branch `javaNN/...` lives at `javaNN/<ModuleName>/`. New module: create it under the matching `javaNN/` folder, never at repo root.
- `.gitignore` uses `**/out` and `**/target`, so build output is ignored at any depth.
