import java.util.Scanner;

public class Bebida extends Producto {
    private float graduacionAlcoholica;
    private boolean importado;
    private String fechaVencimiento;
    private short calorias;

    public static void comprar(Tienda myTienda) {
        Scanner in = new Scanner(System.in);
        Bebida myBebida = new Bebida();
        System.out.println("Ingrese el id: ");
        myBebida.id = "AB" + in.nextInt();
        System.out.println("Ingrese la descripción: ");
        myBebida.descripcion = in.nextLine();
        System.out.println("Ingrese la cantidad que desea comprar: ");
        myBebida.cantidadEnStock = in.nextShort();
        System.out.println("Ingrese el precio por unidad: ");
        myBebida.precioPorUnidad = in.nextFloat();
        do {
            System.out.println("Ingrese el porcentaje de ganancia(No puede ser mayor al 20%): ");
            myBebida.porcentajeDeGanancia = in.nextShort();
        } while (myBebida.porcentajeDeGanancia > 20);
        myBebida.disponibleParaVender = true;
        System.out.println("Ingrese la graduacion alcoholica: ");
        myBebida.graduacionAlcoholica = in.nextFloat();
        byte op;
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
        if (myBebida.importado) {
            myBebida.precioFinal = (int) ((int) (myBebida.precioPorUnidad * myBebida.cantidadEnStock) * 1.12);
        } else myBebida.precioFinal = (int) (myBebida.precioPorUnidad * myBebida.cantidadEnStock);
        if (myBebida.precioFinal < myTienda.saldoEnCaja && myBebida.cantidadEnStock < myTienda.cantidadMaxProductosStock) {
            myTienda.listaBebidas.add(myBebida);
            myTienda.saldoEnCaja -= myBebida.precioFinal;
            myTienda.cantidadMaxProductosStock -= myBebida.cantidadEnStock;
        }
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

    public static void vender() {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el id del producto que desea vender: ");
        String leer = "AB" + in.nextInt();
    }
}
