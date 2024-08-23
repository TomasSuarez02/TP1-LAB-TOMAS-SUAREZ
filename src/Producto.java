import java.text.ParseException;
import java.util.Scanner;

public abstract class Producto {
    public String id;
    public String descripcion;
    public short cantidadEnStock;
    public float precioPorUnidad;
    public float porcentajeDeGanancia;
    public float descuento;
    public boolean disponibleParaVender;
    public float precioFinal;

    public static void elegirProductos (Tienda myTienda, byte opMenu) throws ParseException {
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

    public static void vender (Tienda myTienda, byte opProductos) throws ParseException {
        Scanner in = new Scanner(System.in);
        float totalPagar = 0;
        boolean cond = false;
        byte op;
        do {
            System.out.println("Ingrese el id del producto que desea vender: ");
            int leer = in.nextInt();
            switch (opProductos) {
                case 1:
                    Bebida myBebida = Bebida.buscarBebida(myTienda, leer);
                    if (myBebida == null) {
                        System.out.println("El id ingresado no existe");
                    } else if (myBebida.disponibleParaVender) {
                        short stock = myBebida.cantidadEnStock;
                        System.out.println("¿Cuantas unidades desea llevar? Máximo 12");
                        do {
                            myBebida.cantidadEnStock = in.nextShort();
                            if (myBebida.cantidadEnStock > 12) System.out.println("Cantidad no valida, vuelva a ingresar");
                        } while (myBebida.cantidadEnStock > 12);
                        if (stock > myBebida.cantidadEnStock) {
                            myTienda.venta.add(myBebida);
                            myBebida.cantidadEnStock = (short) (stock - myBebida.cantidadEnStock);
                        } else {
                            System.out.println("No hay Stock suficiente, se venderan solamente: " + stock + " unidades.");
                            myBebida.cantidadEnStock = stock;
                            myTienda.venta.add(myBebida);
                            myBebida.cantidadEnStock = 0;
                            myBebida.disponibleParaVender = false;
                        }
                    } else System.out.println("El Producto: " + myBebida.id + " - " + myBebida.descripcion + " no se encuentra disponible para la venta.");
                    break;
                case 2:
                    Envasado myEnvasado = Envasado.buscarEnvasado(myTienda, leer);
                    cond = myEnvasado == null;
                    if (myEnvasado == null) {
                        System.out.println("El id ingresado no existe");
                    } else if (myEnvasado.disponibleParaVender) {
                        short stock = myEnvasado.cantidadEnStock;
                        System.out.println("¿Cuantas unidades desea llevar? Máximo 12");
                        do {
                            myEnvasado.cantidadEnStock = in.nextShort();
                            if (myEnvasado.cantidadEnStock > 12) System.out.println("Cantidad no valida, vuelva a ingresar");
                        } while (myEnvasado.cantidadEnStock > 12);
                        if (stock > myEnvasado.cantidadEnStock) {
                            myTienda.venta.add(myEnvasado);
                            myEnvasado.cantidadEnStock = (short) (stock - myEnvasado.cantidadEnStock);
                        } else {
                            System.out.println("No hay Stock suficiente, se venderan solamente: " + stock + " unidades.");
                            myEnvasado.cantidadEnStock = stock;
                            myTienda.venta.add(myEnvasado);
                            myEnvasado.cantidadEnStock = 0;
                            myEnvasado.disponibleParaVender = false;
                        }
                    } else System.out.println("El Producto: " + myEnvasado.id + " - " + myEnvasado.descripcion + " no se encuentra disponible para la venta.");
                    break;
                case 3:
                    Limpieza myLimpieza = Limpieza.buscarLimpieza(myTienda, leer);
                    cond = myLimpieza == null;
                    if (myLimpieza == null) {
                        System.out.println("El id ingresado no existe");
                    } else if (myLimpieza.disponibleParaVender) {
                        short stock = myLimpieza.cantidadEnStock;
                        System.out.println("¿Cuantas unidades desea llevar? Máximo 12");
                        do {
                            myLimpieza.cantidadEnStock = in.nextShort();
                            if (myLimpieza.cantidadEnStock > 12) System.out.println("Cantidad no valida, vuelva a ingresar");
                        } while (myLimpieza.cantidadEnStock > 12);
                        if (stock > myLimpieza.cantidadEnStock) {
                            myTienda.venta.add(myLimpieza);
                            myLimpieza.cantidadEnStock = (short) (stock - myLimpieza.cantidadEnStock);
                        } else {
                            System.out.println("No hay Stock suficiente, se venderan solamente: " + stock + " unidades.");
                            myLimpieza.cantidadEnStock = stock;
                            myTienda.venta.add(myLimpieza);
                            myLimpieza.cantidadEnStock = 0;
                            myLimpieza.disponibleParaVender = false;
                        }
                    } else System.out.println("El Producto: " + myLimpieza.id + " - " + myLimpieza.descripcion + " no se encuentra disponible para la venta.");
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
                        for (int i = 0; i < myTienda.venta.size(); i++) {
                            Producto producto = myTienda.venta.get(i);
                            System.out.println("Id del producto: " + producto.id);
                            System.out.println("Descripcion: " + producto.descripcion);
                            System.out.println("Informacion: " + producto.cantidadEnStock + " x " + producto.precioPorUnidad);
                            totalPagar = (int) (totalPagar + (producto.cantidadEnStock * producto.precioFinal));
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
                default:
                    while (op < 1 || op > 2) {
                        System.out.println("Opción no válida, vuelva a Ingresar: ");
                        op = in.nextByte();
                    }
            }
        } else {
            System.out.println("Ya no se pueden agregar mas productos a esta venta");
            do {
                System.out.println("El ticket de su venta es: ");
                for (int i = 0; i < myTienda.venta.size(); i++) {
                    Producto myProducto = myTienda.venta.get(i);
                    System.out.println("Id del producto: " + myProducto.id);
                    System.out.println("Descripcion: " + myProducto.descripcion);
                    System.out.println("Informacion: " + myProducto.cantidadEnStock + " x " + myProducto.precioPorUnidad);
                    totalPagar = (int) (totalPagar + (myProducto.cantidadEnStock * myProducto.precioPorUnidad));
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

    public static void buscarPorDescuento (Tienda myTienda, short descuento) {
        for (int i = 0; i < myTienda.listaBebidas.size(); i++) {
            Bebida myBebida = myTienda.listaBebidas.get(i);
            if (myBebida.descuento < descuento && !myBebida.importado) myTienda.mostrarPorDescuento.add(myBebida);
        }
        for (int i = 0; i < myTienda.listaEnvasados.size(); i++) {
            Envasado myEnvasado = myTienda.listaEnvasados.get(i);
            if (myEnvasado.descuento < descuento && !myEnvasado.importado) myTienda.mostrarPorDescuento.add(myEnvasado);
        }
        for (int i = 0; i < myTienda.listaLimpieza.size(); i++) {
            Limpieza myLimpieza = myTienda.listaLimpieza.get(i);
            if (myLimpieza.descuento < descuento) myTienda.mostrarPorDescuento.add(myLimpieza);
        }
    }
}
