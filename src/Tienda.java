import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tienda {
    protected String nombreTienda;
    protected short cantidadMaxProductosStock;
    protected float saldoEnCaja;

    List<Bebida> listaBebidas = new ArrayList<>();
    List<Envasado> listaEnvasados = new ArrayList<>();
    List<Limpieza> listaLimpieza = new ArrayList<>();
    List<Producto> venta = new ArrayList<>();
    List<Producto> mostrarPorDescuento = new ArrayList<>();

    public static void menu(Tienda myTienda) throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Bienvenido a: " + myTienda.nombreTienda);
        System.out.println("Saldo en caja: " + myTienda.saldoEnCaja);
        System.out.println("Capacidad de almacenaje disponible: " + myTienda.cantidadMaxProductosStock);
        System.out.println("1. Compra de Productos");
        System.out.println("2. Venta de Productos");
        System.out.println("3. Mostrar productos por descuento");
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
                Producto.elegirProductos(myTienda, opMenu);
                break;
            case 3:
                System.out.println("Usted ingreso a Mostrar productos por descuento");
                System.out.println("Ingrese el valor de descuento para filtrar: ");
                short descuento = in.nextShort();
                Producto.buscarPorDescuento(myTienda, descuento);
                for (int i = 0; i < myTienda.mostrarPorDescuento.size(); i++) {
                    Producto myProducto = myTienda.mostrarPorDescuento.get(i);
                    System.out.println("Id: " + myProducto.id + "Descripción: " + myProducto.descripcion);
                }
                break;
            default:
                while (opMenu < 0 || opMenu > 3) {
                    System.out.println("Opción no válida, vuelva a Ingresar: ");
                    opMenu = in.nextByte();
                }
        } while (opMenu != 0);
    }

    public static void main (String[] args) throws ParseException {
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
