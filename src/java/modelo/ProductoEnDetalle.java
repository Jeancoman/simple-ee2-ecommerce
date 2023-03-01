package modelo;

public class ProductoEnDetalle extends Producto {
    private int cantidad;

    public ProductoEnDetalle(int id, String nombre, String foto, String descripcion, double precio, int cantidad) {
        super(id, nombre, foto, descripcion, precio);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getPrecioTotal(){
        return this.cantidad * this.getPrecio();
    }
    
    public String getPrecioTotalFormateado(){
        return this.format.format(getPrecioTotal());
    }
    
}
