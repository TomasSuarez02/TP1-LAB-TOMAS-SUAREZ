import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Envasado extends Producto {
    protected String type;
    protected boolean importado;
    protected Date fechaVencimiento;
    protected short calorias;

    public static void comprar(Tienda myTienda) throws ParseException {
        Scanner in = new Scanner(System.in);
        Scanner leerString = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Envasado myEnvasado = new Envasado();
        short cantidadComprar;
        float precioFinal;
        float precioPagar;
        short leer;
        byte op;
        do {
            System.out.println("Ingrese el id (3 digitos): ");
            leer = in.nextShort();
            if (leer > 999) System.out.println("Valor no válido, vuelva a intentar.");
        } while (leer > 999);
        buscarEnvasado(myTienda, leer);
        boolean condicion;
        condicion = buscarEnvasado(myTienda, leer) == null;
        if (!condicion) {
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
        } else {
            myEnvasado.id = "AB" + leer;
            System.out.println("Ingrese la descripción: ");
            myEnvasado.descripcion = leerString.nextLine();
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
            System.out.println("Ingrese el precio por unidad: ");
            myEnvasado.precioPorUnidad = in.nextFloat();
            do {
                System.out.println("Ingrese el porcentaje de ganancia (Formato 0.X) No puede ser mayor al 20%: ");
                myEnvasado.porcentajeDeGanancia = in.nextShort();
                if (myEnvasado.porcentajeDeGanancia > 0.20) System.out.println("Valor no valido, vuelva a intentar");
            } while (myEnvasado.porcentajeDeGanancia > 0.20);
            do {
                System.out.println("Ingrese el descuento aplicable (Formato: 0.X) no puede ser mayor al 15%: ");
                myEnvasado.descuento = in.nextShort();
                if (myEnvasado.descuento > 0.15) System.out.println("Valor no valido, vuelva a intentar");
            } while (myEnvasado.descuento > 0.15);
            myEnvasado.disponibleParaVender = true;
            System.out.println("Ingrese el tipo de envasado: ");
            myEnvasado.type = in.nextLine();
            do {
                System.out.println("¿El producto es importado? 1.Si/2.No");
                op = in.nextByte();
                myEnvasado.importado = op == 1;
            } while (op < 1 || op > 2);
            System.out.println("Ingrese la fecha de vencimiento (Formato 'DD/MM/YYYY'): ");
            String fecha = leerString.nextLine();
            myEnvasado.fechaVencimiento = dateFormat.parse(fecha);
            System.out.println("Ingrese las calorías del producto: ");
            myEnvasado.calorias = in.nextShort();
        }
        do {
            if (myEnvasado.importado) {
                precioFinal = (float) ((myEnvasado.precioPorUnidad * 1.12) - (myEnvasado.precioPorUnidad * myEnvasado.descuento));
            } else {
                precioFinal = myEnvasado.precioPorUnidad - (myEnvasado.precioPorUnidad * myEnvasado.descuento);
            }
            precioPagar = myEnvasado.precioPorUnidad * cantidadComprar;
            if (precioPagar < myTienda.saldoEnCaja && cantidadComprar < myTienda.cantidadMaxProductosStock) {
                myEnvasado.cantidadEnStock = (short) (myEnvasado.cantidadEnStock + cantidadComprar);
                myEnvasado.precioFinal = precioFinal + precioFinal * myEnvasado.porcentajeDeGanancia;
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
        return myTienda.listaEnvasados.stream()
                .filter(Envasado -> Envasado.id.equals(envasadoId))
                .limit(1).findFirst().orElse(null);
    }
}
