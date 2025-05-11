import java.util.Map;

public class Moneda {
    String base_code;
    Map<String, Double> conversion_rates;

    String monedaFinal;
    Double conversionRate;


    public void setMonedaFinal(String monedaFinal) {
        this.monedaFinal = monedaFinal;
        this.conversionRate = conversion_rates != null ? conversion_rates.get(monedaFinal) : null;
    }

    public float convertir(float numero){
        return (float) (numero* conversionRate);
    }

}
