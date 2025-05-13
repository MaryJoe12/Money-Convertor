import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    public Moneda buscaMoneda(int numeroOpcion) {
        var monedaOriginal = "";
        var monedaFinal = "";
        try {
            switch (numeroOpcion) {
                case 1:
                    monedaOriginal = "USD";
                    monedaFinal = "ARS";
                    break;
                case 2:
                    monedaOriginal = "ARS";
                    monedaFinal = "USD";
                    break;
                case 3:
                    monedaOriginal = "USD";
                    monedaFinal = "BRL";
                    break;
                case 4:
                    monedaOriginal = "BRL";
                    monedaFinal = "USD";
                    break;
                case 5:
                    monedaOriginal = "USD";
                    monedaFinal = "COP";
                    break;
                case 6:
                    monedaOriginal = "COP";
                    monedaFinal = "USD";
                    break;
                default:
                    throw new NumberFormatException("Ingrese un numero valido ");

            }
            Dotenv dotenv = Dotenv.load();
            var apiKey = dotenv.get("API_KEY");
            //se necesito kotlin y dotenv y gson library

            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/" + monedaOriginal);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Moneda moneda = new Gson().fromJson(response.body(), Moneda.class);
            moneda.setMonedaFinal(monedaFinal);
            return moneda;

        } catch (Exception e) {
            throw new RuntimeException("No encontre esa moneda");
        }
    }
}
