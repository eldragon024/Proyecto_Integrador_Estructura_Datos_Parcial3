import java.util.List;

public class Busquedas {

    public static Curso secuencialPorId(List<Curso> cursos, int id) {
        for (Curso c : cursos)
            if (c.getIdCurso() == id) return c;
        return null;
    }

    public static ResultadoBusqueda secuencialConPasos(List<Curso> cursos, int id) {
        int pasos = 0;
        for (Curso c : cursos) {
            pasos++;
            if (c.getIdCurso() == id)
                return new ResultadoBusqueda(c, pasos);
        }
        return new ResultadoBusqueda(null, pasos);
    }

    public static Curso binariaPorId(List<Curso> cursosOrdenados, int id) {
        int inicio = 0, fin = cursosOrdenados.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int valor = cursosOrdenados.get(medio).getIdCurso();

            if (valor == id) return cursosOrdenados.get(medio);
            if (id < valor) fin = medio - 1;
            else inicio = medio + 1;
        }
        return null;
    }

    public static ResultadoBusqueda binariaConPasos(List<Curso> cursosOrdenados, int id) {
        int inicio = 0, fin = cursosOrdenados.size() - 1, pasos = 0;
        while (inicio <= fin) {
            pasos++;
            int medio = (inicio + fin) / 2;
            int valor = cursosOrdenados.get(medio).getIdCurso();

            if (valor == id) return new ResultadoBusqueda(cursosOrdenados.get(medio), pasos);
            if (id < valor) fin = medio - 1;
            else inicio = medio + 1;
        }
        return new ResultadoBusqueda(null, pasos);
    }

    public static class ResultadoBusqueda {
        private final Curso curso;
        private final int pasos;

        public ResultadoBusqueda(Curso curso, int pasos) {
            this.curso = curso;
            this.pasos = pasos;
        }

        public Curso getCurso() { return curso; }
        public int getPasos() { return pasos; }
    }
}
