import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Bebida extends Producto {
    private float graduacionAlcoholica;
    private boolean importado;
    private Date fechaVencimiento;
    private short calorias;

    public static void comprar(Tienda myTienda) {
        Scanner in = new Scanner(System.in);
        Bebida myBebida = new Bebida();
        System.out.println("Ingrese el id: ");
        //int leer = in.nextInt();
        myBebida.id = "AB" + in.nextInt();
        myTienda.listaBebidas.add(myBebida);
    }

    public static void vender() {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el id del producto que desea vender: ");
        String leer = "AB" + in.nextInt();
    }
}

