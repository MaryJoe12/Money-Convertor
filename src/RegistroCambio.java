import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record RegistroCambio(String monedaOriginal, float cantidad, String monedaFinal, float cantidadFinal, String horaFormato) {
    private static final DateTimeFormatter Formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public RegistroCambio(String monedaOriginal, float cantidad, String monedaFinal, float cantidadFinal) {
        this(monedaOriginal, cantidad, monedaFinal, cantidadFinal, LocalDateTime.now().format(Formato));
    }

    @Override
    public String toString() {

        return "\n Registro de Cambio: " +
                "\n Codigo de moneda original= " + monedaOriginal +
                "\n Cantidad a convertir= " + cantidad +
                "\n Codigo de moneda final= " + monedaFinal +
                "\n Cantidad final= " + cantidadFinal +
                "\n Marca de Tiempo = "+ horaFormato+
                "\n";

    }
}
