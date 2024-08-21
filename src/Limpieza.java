import java.util.Scanner;

public class Limpieza extends Producto {
    protected String tipoDeAplicacion;

    public static void comprar(Tienda myTienda) {
        Scanner in = new Scanner(System.in);
        Limpieza myLimpieza = new Limpieza();
        System.out.println("Ingrese el id: ");
        myLimpieza.id = "AZ" + in.nextInt();
        System.out.println("Ingrese la descripción: ");
        myLimpieza.descripcion = in.nextLine();
        System.out.println("Ingrese la cantidad que desea comprar: ");
        myLimpieza.cantidadEnStock = in.nextShort();
        System.out.println("Ingrese el precio por unidad: ");
        myLimpieza.precioPorUnidad = in.nextFloat();
        do {
            System.out.println("Ingrese el porcentaje de ganancia(No puede ser mayor al 25% ni menor al 10%): ");
            myLimpieza.porcentajeDeGanancia = in.nextShort();
        } while (myLimpieza.porcentajeDeGanancia < 10 || myLimpieza.porcentajeDeGanancia > 25);
        myLimpieza.disponibleParaVender = true;
        System.out.println("Ingrese el tipo de aplicacion del producto: ");
        String tipoAplicacion = in.nextLine();
        do {
            if (tipoAplicacion.equals("COCINA")
                    || tipoAplicacion.equals("BAÑO")
                    || tipoAplicacion.equals("ROPA")
                    || tipoAplicacion.equals("MULTIUSO")) {
                myLimpieza.tipoDeAplicacion = in.nextLine();
            } else {
                System.out.println("Tipo de aplicación no válido, vuelva  intentar: ");
                tipoAplicacion = in.nextLine();
            }
        } while (tipoAplicacion.equals("COCINA")
                || tipoAplicacion.equals("BAÑO")
                || tipoAplicacion.equals("ROPA")
                || tipoAplicacion.equals("MULTIUSO"));
        do {
            myLimpieza.precioFinal = (int) (myLimpieza.precioPorUnidad * myLimpieza.cantidadEnStock);
            if (myLimpieza.precioFinal < myTienda.saldoEnCaja && myLimpieza.cantidadEnStock < myTienda.cantidadMaxProductosStock) {
                myTienda.listaLimpieza.add(myLimpieza);
                myTienda.saldoEnCaja -= myLimpieza.precioFinal;
                myTienda.cantidadMaxProductosStock -= myLimpieza.cantidadEnStock;
            }else if (myLimpieza.precioFinal > myTienda.saldoEnCaja) {
                System.out.println("El saldo en caja es insuficiente, pruebe comprando menos unidades: ");
                myLimpieza.cantidadEnStock = in.nextShort();
            } else if (myLimpieza.cantidadEnStock > myTienda.cantidadMaxProductosStock) {
                System.out.println("La capacidad del almacen es insuficiente, pruebe comprando menos unidades: ");
                myLimpieza.cantidadEnStock = in.nextShort();
            }
        } while (myLimpieza.precioFinal > myTienda.saldoEnCaja || myLimpieza.cantidadEnStock > myTienda.cantidadMaxProductosStock);
        byte op;
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
        Limpieza myLimpieza;
        return myLimpieza = myTienda.listaLimpieza.stream()
                .filter(Limpieza -> Limpieza.id.equals(limpiezaId))
                .limit(1).findFirst().orElse(null);
    }
}
