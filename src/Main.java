import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner lectura = new Scanner(System.in);
        List<RegistroCambio> registros= new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while(true) {
            ConsultaMoneda consulta = new ConsultaMoneda();
            System.out.println("""
                *******************************************
                Sea bienvenido/a al Conversor de Moneda =]"
                
                1) Dolar =>> Peso argentino
                2) Peso argentino =>> Dolar
                3) Dolar =>> Real brasileno
                4) Real brasileno =>> Dolar
                5 Dolar =>> Peso colombiano
                6) Peso colombiano =>> Dolar
                7) Salir
                Elija una opcion valida
                *******************************************
                """);
            try {
                int numeroMenu = lectura.nextInt();
                if(numeroMenu == 7){
                    break;
                }
                if(numeroMenu <= 0 || numeroMenu >=8){
                    throw new NumberFormatException("Ingrese un numero valido ");
                }
                System.out.println("Ingrese el valor que deseas convertir: ");
                float numero = lectura.nextFloat();
                Moneda moneda = consulta.buscaMoneda(numeroMenu);

                System.out.println("El valor " + numero + " [" + moneda.base_code + "] " + "corresponde al valor final de =>>> " + moneda.convertir(numero) + " [" + moneda.monedaFinal + "]");
                RegistroCambio registro= new RegistroCambio(moneda.base_code, numero, moneda.monedaFinal, moneda.convertir(numero));
                registros.add(registro);
            } catch (NumberFormatException e) {
                System.out.println( e.getMessage());
                lectura.nextLine();

            }catch(InputMismatchException e){
                System.out.println("Ingrese el numero correspondiente a la opcion que se desea");
                lectura.nextLine();
            }
        }
        FileWriter escritura = new FileWriter("Registro.txt");
        escritura.write(registros.toString());
        escritura.close();
        System.out.println("Gracia por utilizar este conversor de monedas");
    }
}
