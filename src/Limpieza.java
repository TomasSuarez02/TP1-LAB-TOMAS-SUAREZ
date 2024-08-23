import java.text.ParseException;
import java.util.Scanner;

public class Limpieza extends Producto {
    protected String tipoDeAplicacion;

    public static void comprar(Tienda myTienda) throws ParseException {
        Scanner in = new Scanner(System.in);
        Scanner leerString = new Scanner(System.in);
        Limpieza myLimpieza = new Limpieza();
        short leer;
        byte op;
        do {
            System.out.println("Ingrese el id (3 digitos): ");
            leer = in.nextShort();
            if (leer > 999) System.out.println("Valor no válido, vuelva a intentar.");
        } while (leer > 999);
        buscarLimpieza(myTienda, leer);
        boolean condicion;
        condicion = buscarLimpieza(myTienda, leer) == null;
        short cantidadComprar;
        int precioPagar;
        if (!condicion) {
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
        } else {
            myLimpieza.id = "AZ" + leer;
            System.out.println("Ingrese la descripción: ");
            myLimpieza.descripcion = leerString.nextLine();
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
            System.out.println("Ingrese el precio por unidad: ");
            myLimpieza.precioPorUnidad = in.nextFloat();
            do {
                System.out.println("Ingrese el porcentaje de ganancia(No puede ser mayor al 25% ni menor al 10%): ");
                myLimpieza.porcentajeDeGanancia = in.nextShort();
            } while (myLimpieza.porcentajeDeGanancia < 10 || myLimpieza.porcentajeDeGanancia > 25);
            System.out.println("Ingrese el descuento aplicable: ");
            myLimpieza.descuento = in.nextShort();
            myLimpieza.disponibleParaVender = true;
            System.out.println("Ingrese el tipo de aplicacion del producto: ");
            String tipoAplicacion = leerString.nextLine();
            do {
                if (tipoAplicacion.equals("COCINA")
                        || tipoAplicacion.equals("BAÑO")
                        || tipoAplicacion.equals("ROPA")
                        || tipoAplicacion.equals("MULTIUSO")) {
                    myLimpieza.tipoDeAplicacion = tipoAplicacion;
                } else {
                    System.out.println("Tipo de aplicación no válido, vuelva  intentar: ");
                    tipoAplicacion = leerString.nextLine();
                }
            } while (tipoAplicacion.equals("COCINA")
                    || tipoAplicacion.equals("BAÑO")
                    || tipoAplicacion.equals("ROPA")
                    || tipoAplicacion.equals("MULTIUSO"));
        }
        do {
            precioPagar = (int) (myLimpieza.precioPorUnidad * cantidadComprar);
            if (precioPagar < myTienda.saldoEnCaja && cantidadComprar < myTienda.cantidadMaxProductosStock) {
                myLimpieza.cantidadEnStock = (short) (myLimpieza.cantidadEnStock + cantidadComprar);
                myLimpieza.precioFinal = (int) (myLimpieza.cantidadEnStock * myLimpieza.precioPorUnidad);
                myTienda.listaLimpieza.add(myLimpieza);
                myTienda.saldoEnCaja -= precioPagar;
                myTienda.cantidadMaxProductosStock -= cantidadComprar;
            } else if (precioPagar > myTienda.saldoEnCaja) {
                System.out.println("El saldo en caja es insuficiente, pruebe comprando menos unidades: ");
                cantidadComprar = in.nextShort();
            } else if (cantidadComprar > myTienda.cantidadMaxProductosStock) {
                System.out.println("La capacidad del almacen es insuficiente, pruebe comprando menos unidades: ");
                cantidadComprar = in.nextShort();
            }
        } while (precioPagar > myTienda.saldoEnCaja || cantidadComprar > myTienda.cantidadMaxProductosStock);
        System.out.println("¿Desea seguir comprando? 1.Si/2.No");
        op = in.nextByte();
        switch (op){
            case 1: Producto.elegirProductos(myTienda, op);
            case 2: Tienda.menu(myTienda);
            default:
                while (op < 1 || op > 2) {
                    System.out.println("Opción no válida, vuelva a Ingresar: ");
                    op = in.nextByte();
                }
        }
    }

    protected static Limpieza buscarLimpieza(Tienda myTienda, int leer) {
        String limpiezaId = "AZ" + leer;
        return myTienda.listaLimpieza.stream()
                            .filter(Limpieza -> Limpieza.id.equals(limpiezaId))
                            .limit(1).findFirst().orElse(null);
    }
}
