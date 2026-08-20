import java.util.ArrayList;
import java.util.List;

public class Ordenamientos {

    public static List<Curso> bubbleSortDirecto(List<Curso> original) {
        List<Curso> a = new ArrayList<>(original);
        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = 0; j < a.size() - 1 - i; j++) {
                if (a.get(j).getIdCurso() > a.get(j + 1).getIdCurso()) {
                    intercambiar(a, j, j + 1);
                }
            }
        }
        return a;
    }

    public static List<Curso> bubbleSortInverso(List<Curso> original) {
        List<Curso> a = new ArrayList<>(original);
        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = 0; j < a.size() - 1 - i; j++) {
                if (a.get(j).getIdCurso() < a.get(j + 1).getIdCurso()) {
                    intercambiar(a, j, j + 1);
                }
            }
        }
        return a;
    }

    public static List<Curso> insercionDirecta(List<Curso> original) {
        List<Curso> a = new ArrayList<>(original);
        for (int i = 1; i < a.size(); i++) {
            Curso actual = a.get(i);
            int j = i - 1;
            while (j >= 0 && a.get(j).getIdCurso() > actual.getIdCurso()) {
                a.set(j + 1, a.get(j));
                j--;
            }
            a.set(j + 1, actual);
        }
        return a;
    }

    public static List<Curso> seleccionDirecta(List<Curso> original) {
        List<Curso> a = new ArrayList<>(original);
        for (int i = 0; i < a.size() - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(j).getIdCurso() < a.get(menor).getIdCurso())
                    menor = j;
            }
            intercambiar(a, i, menor);
        }
        return a;
    }

    private static void intercambiar(List<Curso> a, int i, int j) {
        Curso temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    public static void mostrar(List<Curso> cursos) {
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        for (Curso c : cursos) System.out.println(c);
    }
}
