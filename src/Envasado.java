import java.util.Scanner;

public class Envasado extends Producto {
    protected String type;
    protected boolean importado;
    protected String fechaVencimiento;
    protected short calorias;

    public static void comprar(Tienda myTienda) {
        Scanner in = new Scanner(System.in);
        Envasado myEnvasado = new Envasado();
        byte op;
        System.out.println("Ingrese el id (3 digitos): ");
        short leer = in.nextShort();
        buscarEnvasado(myTienda, leer);
        boolean condicion;
        condicion = buscarEnvasado(myTienda, leer) == null;
        short cantidadComprar;
        int precioPagar;
        if (!condicion) {
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
        } else {
            myEnvasado.id = "AB" + leer;
            System.out.println("Ingrese la descripción: ");
            myEnvasado.descripcion = in.nextLine();
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
            System.out.println("Ingrese el precio por unidad: ");
            myEnvasado.precioPorUnidad = in.nextFloat();
            do {
                System.out.println("Ingrese el porcentaje de ganancia(No puede ser mayor al 20%): ");
                myEnvasado.porcentajeDeGanancia = in.nextShort();
            } while (myEnvasado.porcentajeDeGanancia > 20);
            myEnvasado.disponibleParaVender = true;
            System.out.println("Ingrese el tipo de envasado: ");
            myEnvasado.type = in.nextLine();
            do {
                System.out.println("¿El producto es importado? 1.Si/2.No");
                op = in.nextByte();
                myEnvasado.importado = op == 1;
            } while (op < 1 || op > 2);
            System.out.println("Ingrese la fecha de vencimiento: ");
            myEnvasado.fechaVencimiento = in.nextLine();
            System.out.println("Ingrese las calorías del producto: ");
            myEnvasado.calorias = in.nextShort();
        }
        do {
            if (myEnvasado.importado) {
                precioPagar = (int) ((int) (myEnvasado.precioPorUnidad * cantidadComprar) * 1.12);
            } else precioPagar = (int) (myEnvasado.precioPorUnidad * cantidadComprar);
            if (precioPagar < myTienda.saldoEnCaja && cantidadComprar < myTienda.cantidadMaxProductosStock) {
                myEnvasado.cantidadEnStock = (short) (myEnvasado.cantidadEnStock + cantidadComprar);
                myEnvasado.precioFinal = (int) (myEnvasado.cantidadEnStock * myEnvasado.precioPorUnidad);
                myTienda.listaEnvasados.add(myEnvasado);
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
            case 1: Producto.elegirProductos(myTienda, op); //Devuelve 'op' para seguir en 'Compra de Productos'
            case 2: Tienda.menu(myTienda);
            default:
                while (op < 1 || op > 2) {
                    System.out.println("Opción no válida, vuelva a Ingresar: ");
                    op = in.nextByte();
                }
        }
    }

    protected static Envasado buscarEnvasado(Tienda myTienda, int leer) {
        String envasadoId = "AB" + leer;
        Envasado myEnvasado = myTienda.listaEnvasados.stream()
                .filter(Envasado -> Envasado.id.equals(envasadoId))
                .limit(1).findFirst().orElse(null);
        return myEnvasado;
    }
}
