import java.util.Scanner;

public class Envasado extends Producto {
    private String type;
    private boolean importado;
    private String fechaVencimiento;
    private short calorias;

    public static void comprar(Tienda myTienda) {
        Scanner in = new Scanner(System.in);
        Envasado myEnvasado = new Envasado();
        System.out.println("Ingrese el id: ");
        myEnvasado.id = "AB" + in.nextInt();
        System.out.println("Ingrese la descripción: ");
        myEnvasado.descripcion = in.nextLine();
        System.out.println("Ingrese la cantidad que desea comprar: ");
        myEnvasado.cantidadEnStock = in.nextShort();
        System.out.println("Ingrese el precio por unidad: ");
        myEnvasado.precioPorUnidad = in.nextFloat();
        do {
            System.out.println("Ingrese el porcentaje de ganancia(No puede ser mayor al 20%): ");
            myEnvasado.porcentajeDeGanancia = in.nextShort();
        } while (myEnvasado.porcentajeDeGanancia > 20);
        myEnvasado.disponibleParaVender = true;
        System.out.println("Ingrese el tipo de envasado: ");
        myEnvasado.type = in.nextLine();
        byte op;
        do {
            System.out.println("¿El producto es importado? 1.Si/2.No");
            op = in.nextByte();
            myEnvasado.importado = op == 1;
        } while (op < 1 || op > 2);
        System.out.println("Ingrese la fecha de vencimiento: ");
        myEnvasado.fechaVencimiento = in.nextLine();
        System.out.println("Ingrese las calorías del producto: ");
        myEnvasado.calorias = in.nextShort();
        do {
            if (myEnvasado.importado) {
                myEnvasado.precioFinal = (int) ((int) (myEnvasado.precioPorUnidad * myEnvasado.cantidadEnStock) * 1.12);
            } else myEnvasado.precioFinal = (int) (myEnvasado.precioPorUnidad * myEnvasado.cantidadEnStock);
            if (myEnvasado.precioFinal < myTienda.saldoEnCaja && myEnvasado.cantidadEnStock < myTienda.cantidadMaxProductosStock) {
                myTienda.listaEnvasados.add(myEnvasado);
                myTienda.saldoEnCaja -= myEnvasado.precioFinal;
                myTienda.cantidadMaxProductosStock -= myEnvasado.cantidadEnStock;
            } else if (myEnvasado.precioFinal > myTienda.saldoEnCaja) {
                System.out.println("El saldo en caja es insuficiente, pruebe comprando menos unidades: ");
                myEnvasado.cantidadEnStock = in.nextShort();
            } else if (myEnvasado.cantidadEnStock > myTienda.cantidadMaxProductosStock) {
                System.out.println("La capacidad del almacen es insuficiente, pruebe comprando menos unidades: ");
                myEnvasado.cantidadEnStock = in.nextShort();
            }
        } while (myEnvasado.precioFinal > myTienda.saldoEnCaja || myEnvasado.cantidadEnStock > myTienda.cantidadMaxProductosStock);
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

    public static void vender() {

    }
}
