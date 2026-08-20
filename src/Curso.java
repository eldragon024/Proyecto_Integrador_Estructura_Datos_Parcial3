public class Curso {
    private int idCurso;
    private String clave;
    private String nombre;
    private String docente;
    private int cupoMaximo;
    private int numeroInscritos;

    public Curso(int idCurso, String clave, String nombre, String docente, int cupoMaximo) {
        this.idCurso = idCurso;
        this.clave = clave;
        this.nombre = nombre;
        this.docente = docente;
        this.cupoMaximo = cupoMaximo;
        this.numeroInscritos = 0;
    }

    public int getIdCurso() { return idCurso; }
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public String getDocente() { return docente; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getNumeroInscritos() { return numeroInscritos; }

    public boolean inscribir() {
        if (numeroInscritos >= cupoMaximo) return false;
        numeroInscritos++;
        return true;
    }

    public boolean darDeBaja() {
        if (numeroInscritos <= 0) return false;
        numeroInscritos--;
        return true;
    }

    public int getCupoDisponible() {
        return cupoMaximo - numeroInscritos;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | Clave: %s | Nombre: %s | Docente: %s | Cupo: %d | Inscritos: %d | Disponibles: %d",
            idCurso, clave, nombre, docente, cupoMaximo, numeroInscritos, getCupoDisponible()
        );
    }
}
