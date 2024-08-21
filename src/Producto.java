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
                } else vender(myTienda, opProductos);
                break;
            case 2:
                if (opMenu == 1) {
                    Envasado.comprar(myTienda);
                } else vender(myTienda, opProductos);
                break;
            case 3:
                if (opMenu == 1) {
                    Limpieza.comprar(myTienda);
                } else vender(myTienda, opProductos);
                break;
            default:
                while (opProductos < 0 || opProductos > 2) {
                    System.out.println("Opción no válida, vuelva a Ingresar: ");
                    opProductos = in.nextByte();
                }
        } while (opProductos != 0);
    }

    public static void vender (Tienda myTienda, byte opProductos){
        Scanner in = new Scanner(System.in);
        int totalPagar = 0;
        boolean cond = false;
        byte op;
        do {
            System.out.println("Ingrese el id del producto que desea vender: ");
            int leer = in.nextInt();
            switch (opProductos) {
                case 1:
                    Bebida myBebida = Bebida.buscarBebida(myTienda, leer);
                    cond = myBebida == null;
                    if (myBebida == null) {
                        System.out.println("El id ingresado no existe");
                    } else if (myTienda.venta.size() < 3){
                        myTienda.venta.add(myBebida);
                        System.out.println("aaa");
                    } else System.out.println("Ya no se pueden agregar mas productos a esta venta");
                    break;
                case 2:
                    Limpieza myLimpieza = Limpieza.buscarLimpieza(myTienda, leer);
                    cond = myLimpieza == null;
                    if (myLimpieza == null) {
                        System.out.println("El id ingresado no existe");
                    } else if (myTienda.venta.size() < 3){
                        myTienda.venta.add(myLimpieza);
                        System.out.println("aaa");
                    } else System.out.println("Ya no se pueden agregar mas productos a esta venta");
                    break;
                case 3:
                    Envasado myEnvasado = Envasado.buscarEnvasado(myTienda, leer);
                    cond = myEnvasado == null;
                    if (myEnvasado == null) {
                        System.out.println("El id ingresado no existe");
                    } else if (myTienda.venta.size() < 3){
                        myTienda.venta.add(myEnvasado);
                        System.out.println("aaa");
                    } else System.out.println("Ya no se pueden agregar mas productos a esta venta");
                    break;
            }
        } while (cond);
        if (myTienda.venta.size() < 3) {
            System.out.println("¿Desea seguir vendiendo? 1.Si/2.No");
            op = in.nextByte();
            switch (op) {
                case 1: {
                    op = 2;
                    Producto.elegirProductos(myTienda, op);
                }
                case 2: {
                    do {
                        System.out.println("El ticket de su venta es: ");
                        System.out.println("Total a abonar: " + totalPagar);
                        System.out.println("¿Venta finalizada? 1.Si/2.No");
                        op = in.nextByte();
                        if (op == 1) {
                            myTienda.venta.clear();
                            Tienda.menu(myTienda);
                        }
                    } while (op != 2);
                }
                default:
                    while (op < 1 || op > 2) {
                        System.out.println("Opción no válida, vuelva a Ingresar: ");
                        op = in.nextByte();
                    }
            }
        } else {
            do {
                System.out.println("El ticket de su venta es: ");
                for (int i = 0; i < myTienda.venta.size(); i++) {
                    Producto producto = myTienda.venta.get(i);
                    System.out.println("El id del producto es: " + producto.id);
                }
                System.out.println("Total a abonar: " + totalPagar);
                System.out.println("¿Venta finalizada? 1.Si/2.No");
                op = in.nextByte();
                if (op == 1) {
                    myTienda.venta.clear();
                    Tienda.menu(myTienda);
                }
            } while (op != 2);
        }
    }
}
