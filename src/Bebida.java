import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Bebida extends Producto {
    protected float graduacionAlcoholica;
    protected boolean importado;
    protected Date fechaVencimiento;
    protected short calorias;

    public static void comprar(Tienda myTienda) throws ParseException {
        Scanner in = new Scanner(System.in);
        Scanner leerString = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Bebida myBebida = new Bebida();
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
        buscarBebida(myTienda, leer);
        boolean condicion;
        condicion = buscarBebida(myTienda, leer) == null;
        if (!condicion) {
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
        } else {
            myBebida.id = "AC" + leer;
            System.out.println("Ingrese la descripción: ");
            myBebida.descripcion = leerString.nextLine();
            System.out.println("Ingrese la cantidad que desea comprar: ");
            cantidadComprar = in.nextShort();
            System.out.println("Ingrese el precio por unidad: ");
            myBebida.precioPorUnidad = in.nextFloat();
            do {
                System.out.println("Ingrese el porcentaje de ganancia (Formato 0.X) No puede ser mayor al 20%: ");
                myBebida.porcentajeDeGanancia = in.nextShort();
                if (myBebida.porcentajeDeGanancia > 0.20) System.out.println("Valor no valido, vuelva a intentar");
            } while (myBebida.porcentajeDeGanancia > 0.20);
            do {
                System.out.println("Ingrese el descuento aplicable (Formato: 0.X) no puede ser mayor al 10%: ");
                myBebida.descuento = in.nextShort();
                if (myBebida.descuento > 0.10) System.out.println("Valor no valido, vuelva a intentar");
            } while (myBebida.descuento > 0.10);
            myBebida.disponibleParaVender = true;
            System.out.println("Ingrese la graduacion alcoholica: ");
            myBebida.graduacionAlcoholica = in.nextFloat();
            do {
                System.out.println("¿El producto es importado? 1.Si/2.No");
                op = in.nextByte();
                myBebida.importado = op == 1;
            } while (op < 1 || op > 2);
            System.out.println("Ingrese la fecha de vencimiento (Formato 'DD/MM/YYYY'): ");
            String fecha = leerString.nextLine();
            myBebida.fechaVencimiento = dateFormat.parse(fecha);
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
                precioFinal = (float) ((myBebida.precioPorUnidad * 1.12) - (myBebida.precioPorUnidad * myBebida.descuento));
            } else {
                precioFinal = myBebida.precioPorUnidad - (myBebida.precioPorUnidad * myBebida.descuento);
            }
            precioPagar = myBebida.precioPorUnidad * cantidadComprar;
            if (precioPagar < myTienda.saldoEnCaja && cantidadComprar < myTienda.cantidadMaxProductosStock) {
                myBebida.cantidadEnStock = (short) (myBebida.cantidadEnStock + cantidadComprar);
                myBebida.precioFinal = precioFinal + precioFinal * myBebida.porcentajeDeGanancia;
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
        return myTienda.listaBebidas.stream()
                        .filter(Bebida -> Bebida.id.equals(bebidaId))
                        .limit(1).findFirst().orElse(null);
    }
}
