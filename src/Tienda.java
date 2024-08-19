import java.util.Scanner;

public class Tienda {
    private String nombreTienda;
    private short cantidadMaxProductosStock;
    private int saldoEnCaja;

    public static void menu(Tienda myTienda){
        Scanner in = new Scanner(System.in);
        byte op = 0;
        do {
            System.out.println("Bienvenido a: " + myTienda.nombreTienda);
            System.out.println("1. Compra de Productos");
            System.out.println("2. Venta de Productos");
            System.out.println("0. Salir");
            System.out.println("Ingrese una opción: ");
            op = in.nextByte();
        } while (op < 0 || op > 2);
        switch (op){
            case 0:
                System.exit(0);
            case 1:

                break;
            case 2:

                break;
        }
    }

    public static void main (String args[]){
        Scanner in = new Scanner(System.in);
        Tienda myTienda = new Tienda();
        System.out.println("Ingrese el nombre de su tienda");
            myTienda.nombreTienda = in.nextLine();
        System.out.println("Ingrese la cantidad máxima de productos a almacenar: ");
            myTienda.cantidadMaxProductosStock = in.nextShort();
        System.out.println("Ingrese el saldo actual en caja: ");
            myTienda.saldoEnCaja = in.nextInt();
        menu(myTienda);
    }
}
