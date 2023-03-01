package modelo;

import java.text.DecimalFormat;
import java.util.List;

public class Compra {

    private int id;
    private Cliente cliente;
    private int idCliente;
    private int idPago;
    private String fecha;
    private Double monto;
    private String estado;
    private List<ProductoEnCarrito> comprados;
    private DecimalFormat format;

    public Compra(Cliente cliente, int idPago, String fecha, Double monto, String estado, List<ProductoEnCarrito> comprados) {
        this.cliente = cliente;
        this.idPago = idPago;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.comprados = comprados;
    }

    public Compra(int id, int idCliente, int idPago, String fecha, Double monto, String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idPago = idPago;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.format = new DecimalFormat("#.##");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    public String getMontoFormateado(){
        return format.format(monto);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProductoEnCarrito> getComprados() {
        return comprados;
    }

    public void setComprados(List<ProductoEnCarrito> comprados) {
        this.comprados = comprados;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    

}
