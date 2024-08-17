public class Tienda {
    private String nombreTienda;
    private short cantidadMaxProductosStock;
    private int saldoEnCaja;

    static void newTienda (){
        Tienda myTienda = new Tienda();
        myTienda.nombreTienda = "Carrefour";
        myTienda.cantidadMaxProductosStock = 200;
        myTienda.saldoEnCaja = 100000;
    }
    public static void mostrar (Tienda myTienda){
        newTienda();
        System.out.println(myTienda.nombreTienda);
        System.out.println(myTienda.cantidadMaxProductosStock);
        System.out.println(myTienda.saldoEnCaja);
    }
 }
