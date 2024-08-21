import java.util.Scanner;

public abstract class Producto {
    public String id;
    public String descripcion;
    public short cantidadEnStock;
    public float precioPorUnidad;
    public short porcentajeDeGanancia;
    public short descuento;
    public boolean disponibleParaVender;
    public int precioFinal;

    public static void elegirProductos (Tienda myTienda, byte opMenu){
        Scanner in = new Scanner(System.in);
        System.out.println("Elija que tipo de productos va a escoger: ");
        System.out.println("1. Bebidas");
        System.out.println("2. Envasados");
        System.out.println("3. Limpieza");
        System.out.println("0. Volver al menu");
        byte opProductos = in.nextByte();
        do switch (opProductos) {
            case 0:
                Tienda.menu(myTienda);
                break;
            case 1:
                if (opMenu == 1) {
                    Bebida.comprar(myTienda);
                } else Bebida.vender(myTienda);
                break;
            case 2:
                if (opMenu == 1) {
                    Envasado.comprar(myTienda);
                } else Envasado.vender();
                break;
            case 3:
                if (opMenu == 1) {
                    Limpieza.comprar(myTienda);
                } else Limpieza.vender();
                break;
            default:
                while (opProductos < 0 || opProductos > 2) {
                    System.out.println("Opción no válida, vuelva a Ingresar: ");
                    opProductos = in.nextByte();
                }
        } while (opProductos != 0);
    }
}
