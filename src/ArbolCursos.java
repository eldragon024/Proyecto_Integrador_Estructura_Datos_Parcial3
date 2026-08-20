public class ArbolCursos {
    private NodoArbolCurso raiz;

    public void insertar(Curso curso) {
        raiz = insertarRec(raiz, curso);
    }
    
    private NodoArbolCurso insertarRec(NodoArbolCurso nodo, Curso curso) {
        if (nodo == null) return new NodoArbolCurso(curso);

        if (curso.getIdCurso() < nodo.curso.getIdCurso()) {
            nodo.izquierda = insertarRec(nodo.izquierda, curso);
        } else if (curso.getIdCurso() > nodo.curso.getIdCurso()) {
            nodo.derecha = insertarRec(nodo.derecha, curso);
        }
        return nodo;
    }

    public Curso buscar(int id) {
        NodoArbolCurso nodo = buscarRec(raiz, id);
        return nodo == null ? null : nodo.curso;
    }

    private NodoArbolCurso buscarRec(NodoArbolCurso nodo, int id) {
        if (nodo == null || nodo.curso.getIdCurso() == id) return nodo;

        if (id < nodo.curso.getIdCurso())
            return buscarRec(nodo.izquierda, id);

        return buscarRec(nodo.derecha, id);
    }

    public void mostrarInorden() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        inorden(raiz);
    }

    private void inorden(NodoArbolCurso nodo) {
        if (nodo != null) {
            inorden(nodo.izquierda);
            System.out.println(nodo.curso);
            inorden(nodo.derecha);
        }
    }
}
