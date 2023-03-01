package modelo;

public class Detalle {
    private int ID;
    private int IDProducto;
    private int IDCompra;
    private int cantidad;
    private double precio;

    public Detalle(int ID, int IDProducto, int IDCompra, int cantidad, double precio) {
        this.ID = ID;
        this.IDProducto = IDProducto;
        this.IDCompra = IDCompra;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public int getIDCompra() {
        return IDCompra;
    }

    public void setIDCompra(int IDCompra) {
        this.IDCompra = IDCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
