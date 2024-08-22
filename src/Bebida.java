import java.util.Scanner;

public class Bebida extends Producto {
    protected float graduacionAlcoholica;
    protected boolean importado;
    protected String fechaVencimiento;
    protected short calorias;

    public static void comprar(Tienda myTienda) {
        Scanner in = new Scanner(System.in);
        Bebida myBebida = new Bebida();
        short leer;
        byte op;
        do {
            System.out.println("Ingrese el id (3 digitos): ");
            leer = in.nextShort();
            if (leer > 999) System.out.println("Valor no válido, vuelva a intentar.");
        } while (leer > 999);
        buscarBebida(myTienda, leer);
        boolean condicion;
        condicion = buscarBebida(myTienda, leer) == null;
        short cantidadComprar;
        int precioPagar;
        if (!condicion) {
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
        } else {
            myBebida.id = "AC" + leer;
            System.out.println("Ingrese la descripción: ");
            myBebida.descripcion = in.nextLine();
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
            System.out.println("Ingrese el precio por unidad: ");
            myBebida.precioPorUnidad = in.nextFloat();
            do {
                System.out.println("Ingrese el porcentaje de ganancia(No puede ser mayor al 20%): ");
                myBebida.porcentajeDeGanancia = in.nextShort();
            } while (myBebida.porcentajeDeGanancia > 20);
            System.out.println("Ingrese el descuento aplicable: ");
            myBebida.descuento = in.nextShort();
            myBebida.disponibleParaVender = true;
            System.out.println("Ingrese la graduacion alcoholica: ");
            myBebida.graduacionAlcoholica = in.nextFloat();
            do {
                System.out.println("¿El producto es importado? 1.Si/2.No");
                op = in.nextByte();
                myBebida.importado = op == 1;
            } while (op < 1 || op > 2);
            System.out.println("Ingrese la fecha de vencimiento: ");
            myBebida.fechaVencimiento = in.nextLine();
            System.out.println("Ingrese las calorías del producto: ");
            short calorias = in.nextShort();
            if (myBebida.graduacionAlcoholica > 0 && myBebida.graduacionAlcoholica < 2) {
                myBebida.calorias = calorias;
            } else if (myBebida.graduacionAlcoholica > 2 && myBebida.graduacionAlcoholica < 4.5f) {
                myBebida.calorias = (short) (calorias * 1.25f);
            } else if (myBebida.graduacionAlcoholica > 4.5) {
                myBebida.calorias = (short) (calorias * 1.5f);
            }
        }
        do {
            if (myBebida.importado) {
                precioPagar = (int) ((int) (myBebida.precioPorUnidad * cantidadComprar) * 1.12);
            } else precioPagar = (int) (myBebida.precioPorUnidad * cantidadComprar);
            if (precioPagar < myTienda.saldoEnCaja && cantidadComprar < myTienda.cantidadMaxProductosStock) {
                myBebida.cantidadEnStock = (short) (myBebida.cantidadEnStock + cantidadComprar);
                myBebida.precioFinal = (int) (myBebida.cantidadEnStock * myBebida.precioPorUnidad);
                myTienda.listaBebidas.add(myBebida);
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

    protected static Bebida buscarBebida(Tienda myTienda, int leer) {
        String bebidaId = "AC" + leer;
        Bebida myBebida = myTienda.listaBebidas.stream()
                        .filter(Bebida -> Bebida.id.equals(bebidaId))
                        .limit(1).findFirst().orElse(null);
        return myBebida;
    }
}
