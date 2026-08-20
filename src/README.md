# Sistema de Gestión de Cursos UTC 3.0

Proyecto Integrador del Parcial 3 de Estructura de Datos.

## Tecnologías
- Java
- Programación Orientada a Objetos
- ArrayList
- Árbol binario de búsqueda
- Grafo con matriz de adyacencia
- Bubble Sort directo e inverso
- Inserción directa
- Selección directa
- Búsqueda secuencial
- Búsqueda binaria

## Clases
- `Main.java`: menú principal y flujo del sistema.
- `Curso.java`: entidad central.
- `NodoArbolCurso.java`: nodo del árbol.
- `ArbolCursos.java`: inserción, búsqueda y recorrido inorden.
- `GrafoCursos.java`: relaciones entre cursos y matriz de adyacencia.
- `Ordenamientos.java`: métodos de ordenamiento.
- `Busquedas.java`: búsquedas secuencial y binaria.
- `HistorialAcciones.java`: historial de operaciones.

## Funcionalidades adicionales
Se agregaron:
1. Comparación del número de pasos entre búsqueda secuencial y binaria.
2. Consulta de cursos con cupo disponible.

## Ejecutar

Desde la carpeta `src`:

```bash
javac *.java
java Main
```

El programa funciona completamente desde consola.

## Casos de prueba sugeridos
1. Mostrar los 5 cursos de ejemplo.
2. Buscar un ID existente y uno inexistente.
3. Insertar cursos en el árbol y mostrar inorden.
4. Buscar un curso desde el árbol.
5. Crear 4 relaciones entre cursos.
6. Mostrar la matriz de adyacencia.
7. Ejecutar Bubble Sort directo e inverso.
8. Ejecutar inserción y selección.
9. Probar búsqueda secuencial y binaria.
10. Comparar los pasos de ambas búsquedas.
11. Mostrar el historial.
12. Mostrar cursos con cupo disponible.
