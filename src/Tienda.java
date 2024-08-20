import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tienda {
    private String nombreTienda;
    private short cantidadMaxProductosStock;
    private int saldoEnCaja;

    List<Bebida> listaBebidas = new ArrayList<Bebida>();

    public static void menu(Tienda myTienda){
        Scanner in = new Scanner(System.in);
        System.out.println("Bienvenido a: " + myTienda.nombreTienda);
        System.out.println("1. Compra de Productos");
        System.out.println("2. Venta de Productos");
        System.out.println("0. Salir");
        System.out.println("Ingrese una opción: ");
        byte opMenu = in.nextByte();
        do switch (opMenu) {
            case 0:
                System.exit(0);
            case 1:
                System.out.println("Usted ingreso a Compra de Productos");
                Producto.elegirProductos(myTienda, opMenu);
                break;
            case 2:
                System.out.println("Usted ingreso a Venta de Productos");
                break;
            default:
                while (opMenu < 0 || opMenu > 2) {
                    System.out.println("Opción no válida, vuelva a Ingresar: ");
                    opMenu = in.nextByte();
                }
        } while (opMenu != 0);
    }

    public static void main (String[] args){
        Scanner in = new Scanner(System.in);
        Tienda myTienda = new Tienda();
        System.out.println("Ingrese el nombre de su tienda: ");
            myTienda.nombreTienda = in.nextLine();
        System.out.println("Ingrese la cantidad máxima de productos a almacenar: ");
            myTienda.cantidadMaxProductosStock = in.nextShort();
        System.out.println("Ingrese el saldo actual en caja: ");
            myTienda.saldoEnCaja = in.nextInt();
        menu(myTienda);
    }
}
