package modelo;

public class ProductoEnCarrito extends Producto {
    private int item;
    private int cantidad;
    private double subTotal;

    public ProductoEnCarrito(int item, int id, String nombre, String descripcion, double precio, int cantidad, double subTotal, String foto) {
        super(id, nombre, foto, descripcion, precio);
        this.item = item;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal * cantidad;
    }
    
    public String getSubTotalFormateado(){
        return format.format(getSubTotal());
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    public void aumentarCantidad(){
        this.cantidad++;
    }
    
    public void reducirCantidad(){
        if(this.cantidad - 1 > 0){
            this.cantidad--;
        }
    }
    

}
