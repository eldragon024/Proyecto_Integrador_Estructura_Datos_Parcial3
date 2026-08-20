import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Curso> cursos = new ArrayList<>();
    static ArbolCursos arbol = new ArbolCursos();
    static GrafoCursos grafo = new GrafoCursos();
    static HistorialAcciones historial = new HistorialAcciones();

    public static void main(String[] args) {
        cargarCursosEjemplo();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> agregarCurso();
                case 2 -> mostrarCursos();
                case 3 -> eliminarCurso();
                case 4 -> inscribirEstudiante();
                case 5 -> bajaEstudiante();
                case 6 -> insertarArbol();
                case 7 -> buscarArbol();
                case 8 -> arbol.mostrarInorden();
                case 9 -> crearRelacion();
                case 10 -> grafo.mostrarMatriz();
                case 11 -> ordenar("Bubble Sort directo");
                case 12 -> ordenar("Bubble Sort inverso");
                case 13 -> ordenar("Inserción directa");
                case 14 -> ordenar("Selección directa");
                case 15 -> busquedaSecuencial();
                case 16 -> busquedaBinaria();
                case 17 -> historial.mostrar();
                case 18 -> compararBusquedas();
                case 19 -> mostrarCursosDisponibles();
                case 20 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 20);
    }

    static void mostrarMenu() {
        System.out.println("\n==================================================");
        System.out.println("       SISTEMA DE GESTIÓN DE CURSOS UTC 3.0");
        System.out.println("==================================================");
        System.out.println("1.  Agregar curso");
        System.out.println("2.  Mostrar cursos");
        System.out.println("3.  Eliminar curso");
        System.out.println("4.  Inscribir estudiante");
        System.out.println("5.  Dar de baja estudiante");
        System.out.println("6.  Insertar cursos en árbol binario");
        System.out.println("7.  Buscar curso en árbol binario");
        System.out.println("8.  Mostrar recorrido inorden del árbol");
        System.out.println("9.  Crear relación entre cursos (grafo)");
        System.out.println("10. Mostrar grafo / matriz de adyacencia");
        System.out.println("11. Ordenar con Bubble Sort directo");
        System.out.println("12. Ordenar con Bubble Sort inverso");
        System.out.println("13. Ordenar con inserción directa");
        System.out.println("14. Ordenar con selección directa");
        System.out.println("15. Búsqueda secuencial");
        System.out.println("16. Búsqueda binaria");
        System.out.println("17. Mostrar historial de acciones");
        System.out.println("18. Comparar pasos: secuencial vs binaria");
        System.out.println("19. Mostrar cursos con cupo disponible");
        System.out.println("20. Salir");
        System.out.println("==================================================");
    }

    static void cargarCursosEjemplo() {
        agregarCursoInterno(new Curso(105, "BD105", "Bases de Datos", "Mtra. Laura", 30));
        agregarCursoInterno(new Curso(102, "PR102", "Programación II", "Ing. Carlos", 25));
        agregarCursoInterno(new Curso(108, "ED108", "Estructura de Datos", "Ing. Ana", 30));
        agregarCursoInterno(new Curso(101, "POO101", "Programación Orientada a Objetos", "Ing. Miguel", 25));
        agregarCursoInterno(new Curso(110, "WEB110", "Desarrollo Web", "Mtra. Sofía", 20));

        cursos.get(0).inscribir();
        cursos.get(0).inscribir();
        cursos.get(1).inscribir();
        cursos.get(2).inscribir();
        cursos.get(2).inscribir();
        grafo.sincronizarCursos(cursos);
        historial.registrar("Sistema iniciado con 5 cursos de ejemplo.");
    }

    static void agregarCursoInterno(Curso curso) {
        cursos.add(curso);
    }

    static void agregarCurso() {
        int id = leerEntero("ID del curso: ");
        String clave = leerTexto("Clave: ");

        if (Busquedas.secuencialPorId(cursos, id) != null || buscarPorClave(clave) != null) {
            System.out.println("Error: el ID o la clave ya están registrados.");
            return;
        }

        String nombre = leerTexto("Nombre: ");
        String docente = leerTexto("Docente: ");
        int cupo = leerEntero("Cupo máximo: ");

        if (cupo <= 0) {
            System.out.println("El cupo debe ser mayor que cero.");
            return;
        }

        Curso nuevo = new Curso(id, clave, nombre, docente, cupo);
        cursos.add(nuevo);
        grafo.sincronizarCursos(cursos);
        historial.registrar("Se agregó el curso " + id + " - " + nombre);
        System.out.println("Curso agregado correctamente.");
    }

    static Curso buscarPorClave(String clave) {
        for (Curso c : cursos)
            if (c.getClave().equalsIgnoreCase(clave)) return c;
        return null;
    }

    static void mostrarCursos() {
        System.out.println("\n===== CURSOS REGISTRADOS =====");
        Ordenamientos.mostrar(cursos);
    }

    static void eliminarCurso() {
        int id = leerEntero("ID del curso a eliminar: ");
        Curso curso = Busquedas.secuencialPorId(cursos, id);

        if (curso == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        cursos.remove(curso);
        grafo.sincronizarCursos(cursos);
        historial.registrar("Se eliminó el curso " + id);
        System.out.println("Curso eliminado.");
        System.out.println("Nota: si estaba en el árbol, vuelve a insertar los cursos para reconstruirlo.");
    }

    static void inscribirEstudiante() {
        int id = leerEntero("ID del curso: ");
        Curso curso = Busquedas.secuencialPorId(cursos, id);

        if (curso == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        if (curso.inscribir()) {
            historial.registrar("Se inscribió un estudiante en el curso " + id);
            System.out.println("Inscripción realizada. Cupos disponibles: " + curso.getCupoDisponible());
        } else {
            System.out.println("No se puede inscribir: el curso está lleno.");
        }
    }

    static void bajaEstudiante() {
        int id = leerEntero("ID del curso: ");
        Curso curso = Busquedas.secuencialPorId(cursos, id);

        if (curso == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        if (curso.darDeBaja()) {
            historial.registrar("Se dio de baja un estudiante del curso " + id);
            System.out.println("Baja realizada.");
        } else {
            System.out.println("No se puede dar de baja: no hay inscritos.");
        }
    }

    static void insertarArbol() {
        if (cursos.isEmpty()) {
            System.out.println("No existen cursos registrados.");
            return;
        }

        arbol = new ArbolCursos();
        for (Curso c : cursos) arbol.insertar(c);
        historial.registrar("Se reconstruyó el árbol binario con los cursos actuales.");
        System.out.println("Cursos insertados correctamente en el árbol.");
    }

    static void buscarArbol() {
        int id = leerEntero("ID a buscar en el árbol: ");
        Curso curso = arbol.buscar(id);

        if (curso == null) System.out.println("Curso no encontrado en el árbol.");
        else System.out.println("Curso encontrado: " + curso);

        historial.registrar("Búsqueda en árbol del ID " + id);
    }

    static void crearRelacion() {
        if (cursos.size() < 2) {
            System.out.println("Se necesitan al menos 2 cursos.");
            return;
        }

        grafo.sincronizarCursos(cursos);
        int origen = leerEntero("ID del curso origen: ");
        int destino = leerEntero("ID del curso destino: ");

        if (grafo.agregarRelacion(origen, destino)) {
            historial.registrar("Se creó relación " + origen + " -> " + destino);
            System.out.println("Relación creada correctamente.");
        } else {
            System.out.println("No se pudo crear la relación. Verifique los IDs y que sean distintos.");
        }
    }

    static void ordenar(String metodo) {
        List<Curso> resultado;
        if (metodo.equals("Bubble Sort directo"))
            resultado = Ordenamientos.bubbleSortDirecto(cursos);
        else if (metodo.equals("Bubble Sort inverso"))
            resultado = Ordenamientos.bubbleSortInverso(cursos);
        else if (metodo.equals("Inserción directa"))
            resultado = Ordenamientos.insercionDirecta(cursos);
        else
            resultado = Ordenamientos.seleccionDirecta(cursos);

        System.out.println("\n===== " + metodo.toUpperCase() + " =====");
        Ordenamientos.mostrar(resultado);
        historial.registrar("Se ejecutó " + metodo + ".");
    }

    static void busquedaSecuencial() {
        int id = leerEntero("ID a buscar: ");
        Busquedas.ResultadoBusqueda r = Busquedas.secuencialConPasos(cursos, id);

        if (r.getCurso() == null)
            System.out.println("No se encontró el curso. Pasos realizados: " + r.getPasos());
        else
            System.out.println("Encontrado: " + r.getCurso() + "\nPasos: " + r.getPasos());

        historial.registrar("Búsqueda secuencial del ID " + id);
    }

    static void busquedaBinaria() {
        if (cursos.isEmpty()) {
            System.out.println("No existen cursos registrados.");
            return;
        }

        int id = leerEntero("ID a buscar: ");
        List<Curso> ordenados = Ordenamientos.seleccionDirecta(cursos);
        Busquedas.ResultadoBusqueda r = Busquedas.binariaConPasos(ordenados, id);

        System.out.println("Los datos se ordenaron por ID antes de realizar la búsqueda binaria.");
        if (r.getCurso() == null)
            System.out.println("No se encontró el curso. Pasos realizados: " + r.getPasos());
        else
            System.out.println("Encontrado: " + r.getCurso() + "\nPasos: " + r.getPasos());

        historial.registrar("Búsqueda binaria del ID " + id);
    }

    static void compararBusquedas() {
        if (cursos.isEmpty()) {
            System.out.println("No existen cursos registrados.");
            return;
        }

        int id = leerEntero("ID a buscar: ");
        List<Curso> ordenados = Ordenamientos.seleccionDirecta(cursos);

        Busquedas.ResultadoBusqueda sec = Busquedas.secuencialConPasos(cursos, id);
        Busquedas.ResultadoBusqueda bin = Busquedas.binariaConPasos(ordenados, id);

        System.out.println("\n===== COMPARACIÓN DE BÚSQUEDAS =====");
        System.out.println("Secuencial: " + sec.getPasos() + " pasos.");
        System.out.println("Binaria:    " + bin.getPasos() + " pasos.");
        if (sec.getCurso() != null) System.out.println("Resultado: curso encontrado.");
        else System.out.println("Resultado: curso no encontrado.");

        historial.registrar("Comparación de pasos secuencial vs binaria para ID " + id);
    }

    static void mostrarCursosDisponibles() {
        boolean hay = false;
        System.out.println("\n===== CURSOS CON CUPO DISPONIBLE =====");
        for (Curso c : cursos) {
            if (c.getCupoDisponible() > 0) {
                System.out.println(c);
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay cursos con cupo disponible.");
        historial.registrar("Se consultaron cursos con cupo disponible.");
    }

    static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}
