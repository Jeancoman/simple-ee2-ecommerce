package modelo;

import java.text.DecimalFormat;
import java.util.Objects;

public class Producto {
    private int id;
    private String nombre;
    private String foto;
    private String descripcion;
    private double precio;
    protected DecimalFormat format;

    public Producto(int id, String nombre, String foto, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.format = new DecimalFormat("#.##");
    }

    public Producto() {
  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getPrecioFormateado(){
        return format.format(precio);
    }
    
        @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.getId();
        hash = 43 * hash + Objects.hashCode(this.getNombre());
        hash = 43 * hash + Objects.hashCode(this.getDescripcion());
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.getPrecio()) ^ (Double.doubleToLongBits(this.getPrecio()) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.getFoto());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductoEnCarrito other = (ProductoEnCarrito) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (Double.doubleToLongBits(this.getPrecio()) != Double.doubleToLongBits(other.getPrecio())) {
            return false;
        }
        if (!Objects.equals(this.getNombre(), other.getNombre())) {
            return false;
        }
        if (!Objects.equals(this.getDescripcion(), other.getDescripcion())) {
            return false;
        }
        return Objects.equals(this.getFoto(), other.getFoto());
    }
     
}
