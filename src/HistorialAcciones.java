import java.util.ArrayList;
import java.util.List;

public class HistorialAcciones {
    private final List<String> historial = new ArrayList<>();

    public void registrar(String accion) {
        historial.add(accion);
    }

    public void mostrar() {
        if (historial.isEmpty()) {
            System.out.println("No hay acciones registradas.");
            return;
        }
        System.out.println("\n===== HISTORIAL DE ACCIONES =====");
        for (int i = 0; i < historial.size(); i++)
            System.out.println((i + 1) + ". " + historial.get(i));
    }
}
