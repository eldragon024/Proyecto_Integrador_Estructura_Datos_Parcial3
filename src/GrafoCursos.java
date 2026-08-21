import java.util.ArrayList;
import java.util.List;

public class GrafoCursos {
    private final List<Curso> vertices = new ArrayList<>();
    private int[][] matriz = new int[0][0];

    public void sincronizarCursos(List<Curso> cursos) {
        for (Curso curso : cursos) {
            if (indiceDe(curso.getIdCurso()) == -1) {
                vertices.add(curso);
            }
        }

        for (int i = vertices.size() - 1; i >= 0; i--) {
            if (!existeCurso(cursos, vertices.get(i).getIdCurso())) {
                vertices.remove(i);
            }
        }

        reconstruirMatriz();
    }

    private boolean existeCurso(List<Curso> cursos, int id) {
        for (Curso c : cursos) if (c.getIdCurso() == id) return true;
        return false;
    }

    private int indiceDe(int id) {
        for (int i = 0; i < vertices.size(); i++)
            if (vertices.get(i).getIdCurso() == id) return i;
        return -1;
    }

    private void reconstruirMatriz() {
        int n = vertices.size();
        int[][] nueva = new int[n][n];

        for (int i = 0; i < Math.min(n, matriz.length); i++) {
            for (int j = 0; j < Math.min(n, matriz.length); j++) {
                int oldIdI = vertices.get(i).getIdCurso();
                int oldIdJ = vertices.get(j).getIdCurso();
                // Los índices actuales pueden cambiar; las relaciones se vuelven a
                // conservar mediante la matriz anterior sólo cuando coinciden posiciones.
                if (i < matriz.length && j < matriz.length) nueva[i][j] = matriz[i][j];
            }
        }
        matriz = nueva;
    }

    public boolean agregarRelacion(int idOrigen, int idDestino) {
        int i = indiceDe(idOrigen);
        int j = indiceDe(idDestino);
        if (i == -1 || j == -1 || i == j) return false;
        matriz[i][j] = 1;
        return true;
    }

    public void mostrarMatriz() {
        if (vertices.isEmpty()) {
            System.out.println("No hay cursos para construir el grafo.");
            return;
        }

        System.out.println("\nMATRIZ DE ADYACENCIA (1 = relación)");
        System.out.print("        ");
        for (Curso c : vertices) System.out.printf("%-7d", c.getIdCurso());
        System.out.println();

        for (int i = 0; i < vertices.size(); i++) {
            System.out.printf("%-8d", vertices.get(i).getIdCurso());
            for (int j = 0; j < vertices.size(); j++)
                System.out.printf("%-7d", matriz[i][j]);
            System.out.println();
        }

        System.out.println("\nRelaciones: fila = curso origen, columna = curso destino.");
    }
}
