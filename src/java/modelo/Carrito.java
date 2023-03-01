package modelo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carrito {
    
    private final List<ProductoEnCarrito> productos;
    private final DecimalFormat format;
    
    public Carrito() {
        this.productos = new ArrayList<>();
        this.format = new DecimalFormat("#.##");
    }
    
    public List<ProductoEnCarrito> getProductos() {
        return productos;
    }
    
    public void añadirProducto(ProductoEnCarrito producto) {
        if (this.contiene(producto)) {
            aumentarCantidad(producto);
        } else {
            this.productos.add(producto);
        }
    }
    
    public void aumentarCantidad(int id) {
        for (ProductoEnCarrito producto : productos) {
            if (producto.getItem() == id) {
                producto.aumentarCantidad();
            }
        }
    }
    
    private void aumentarCantidad(ProductoEnCarrito variable) {
        for (ProductoEnCarrito producto : productos) {
            if (producto.equals(variable)) {
                producto.aumentarCantidad();
            }
        }
    }
    
    public void reducirCantidad(int id) {
        for (ProductoEnCarrito producto : productos) {
            if (producto.getItem() == id) {
                producto.reducirCantidad();
            }
        }
    }
    
    public void eliminarProducto(int id) {
        Iterator<ProductoEnCarrito> iterador = productos.iterator();
        
        while(iterador.hasNext()){
            ProductoEnCarrito producto = iterador.next();
            if (producto.getItem() == id) {
                iterador.remove();
            }
        }
        
    }
    
    public double precioTotal() {
        double precioTotal = 0.0;
        
        for (ProductoEnCarrito producto : productos) {
            precioTotal += producto.getSubTotal();
        }
        
        return precioTotal;
        
    }
    
    public int tamaño() {
        return productos.size();
    }
    
    public boolean contiene(ProductoEnCarrito producto) {
        return productos.contains(producto);
    }
    
    public String precioTotalFormateado() {
        return this.format.format(precioTotal());
    }
    
}
