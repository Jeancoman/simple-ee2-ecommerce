/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Compra;
import modelo.CompraDAO;
import modelo.Pago;
import modelo.ProductoEnCarrito;
import modelo.Producto;
import modelo.ProductoDAO;
import config.Fecha;
import logica.Ingreso;
import modelo.Carrito;
import modelo.ClienteDAO;
import modelo.Detalle;
import modelo.DetalleDAO;
import modelo.ProductoEnDetalle;

/**
 *
 * @author admin
 */
public class Controlador extends HttpServlet {

    private final ProductoDAO productoDAO = new ProductoDAO();
    private final CompraDAO compraDAO = new CompraDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final DetalleDAO detalleDAO = new DetalleDAO();
    private Producto producto = new Producto();
    private List<Producto> productos = new ArrayList<>();
    private List<Compra> compras = new ArrayList<>();
    private final List<Compra> comprasActuales = new ArrayList<>();
    private final List<ProductoEnDetalle> productosEnDetalle = new ArrayList<>();
    private List<Detalle> detalles = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private final Carrito carrito = new Carrito();
    private int item;
    private final int cantidad = 1;
    private int idProducto;
    private Ingreso ingreso;
    private Cliente clienteActual = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        productos = productoDAO.listarProductos();
        compras = compraDAO.listarCompras();
        clientes = clienteDAO.listarClientes();
        detalles = detalleDAO.listarDetalles();

        switch (accion) {
            case "AñadirCarrito":
                int id = Integer.valueOf(request.getParameter("id"));
                producto = productoDAO.listarId(id);
                item = item + 1;
                ProductoEnCarrito productoEnCarrito = new ProductoEnCarrito(item, producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), cantidad, cantidad * producto.getPrecio(), producto.getFoto());
                carrito.añadirProducto(productoEnCarrito);
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("contador", carrito.tamaño());
                request.setAttribute("carrito", carrito.getProductos());
                request.getRequestDispatcher("Controlador?accion=Home").forward(request, response);
                break;
            case "Carrito":
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.setAttribute("totalPagar", carrito.precioTotalFormateado());
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "Home":
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "Eliminar":
                idProducto = Integer.valueOf(request.getParameter("idProducto"));
                carrito.eliminarProducto(idProducto);
                request.setAttribute("contador", carrito.tamaño());
                request.setAttribute("clienteActual", clienteActual);
                request.getRequestDispatcher("Controlador?accion=Carrito").forward(request, response);
                break;
            case "Comprar":
                if (clienteActual != null) {
                    Pago pago = new Pago();
                    Compra compra = new Compra(clienteActual, pago.getId(), Fecha.fecha(), carrito.precioTotal(), "Completado", carrito.getProductos());
                    int r = compraDAO.comprar(compra);
                    if (r != 0 && carrito.precioTotal() > 0) {
                        request.setAttribute("clienteActual", clienteActual);
                        request.setAttribute("contador", carrito.tamaño());
                        request.setAttribute("carrito", carrito.getProductos());
                        request.getRequestDispatcher("exito.jsp").forward(request, response);
                    } else {
                        request.setAttribute("clienteActual", clienteActual);
                        request.setAttribute("contador", carrito.tamaño());
                        request.setAttribute("carrito", carrito.getProductos());
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("productos", productos);
                    request.setAttribute("carrito", carrito.getProductos());
                    request.setAttribute("contador", carrito.tamaño());
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                break;
            case "MisCompras":
                comprasActuales.clear();
                for (Compra compra : compras) {
                    if (compra.getIdCliente() == clienteActual.getId()) {
                        comprasActuales.add(compra);
                    }
                }
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("compras", comprasActuales);
                request.setAttribute("contador", carrito.tamaño());
                request.setAttribute("carrito", carrito.getProductos());
                request.getRequestDispatcher("compras.jsp").forward(request, response);
                break;
            case "Añadir":
                idProducto = Integer.valueOf(request.getParameter("idProducto"));
                carrito.aumentarCantidad(idProducto);
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("contador", carrito.tamaño());
                request.setAttribute("carrito", carrito.getProductos());
                request.getRequestDispatcher("Controlador?accion=Carrito").forward(request, response);
                break;
            case "Reducir":
                idProducto = Integer.valueOf(request.getParameter("idProducto"));
                carrito.reducirCantidad(idProducto);
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("Controlador?accion=Carrito").forward(request, response);
                break;
            case "Ingreso":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                ingreso = new Ingreso(clientes, email, password);
                clienteActual = ingreso.retornarCliente();
                if (clienteActual == null) {
                    request.setAttribute("carrito", carrito.getProductos());
                    request.setAttribute("contador", carrito.tamaño());
                    request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
                } else {
                    request.setAttribute("clienteActual", clienteActual);
                    request.setAttribute("productos", productos);
                    request.setAttribute("carrito", carrito.getProductos());
                    request.setAttribute("contador", carrito.tamaño());
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            case "FormularioIngreso":
                request.setAttribute("productos", productos);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "FormularioRegistro":
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                break;
            case "Registro":
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String cedula = request.getParameter("cedula");
                String correo = request.getParameter("email");
                String contraseña = request.getParameter("password");
                String direccion = request.getParameter("direccion");
                Cliente clienteRegistro = new Cliente(cedula, nombre, apellido, direccion, correo, contraseña);
                int res = clienteDAO.crearCliente(clienteRegistro);
                if (res != 0) {
                    request.setAttribute("productos", productos);
                    request.setAttribute("contador", carrito.tamaño());
                    request.setAttribute("carrito", carrito.getProductos());
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.setAttribute("contador", carrito.tamaño());
                    request.setAttribute("carrito", carrito.getProductos());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }

                break;
            case "Cuenta":
                if (clienteActual == null) {
                    request.setAttribute("carrito", carrito.getProductos());
                    request.setAttribute("contador", carrito.tamaño());
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    request.setAttribute("clienteActual", clienteActual);
                    request.setAttribute("carrito", carrito.getProductos());
                    request.setAttribute("contador", carrito.tamaño());
                    request.getRequestDispatcher("perfil.jsp").forward(request, response);
                }
                break;
            case "Detalles":
                productosEnDetalle.clear();
                int idCompra = Integer.valueOf(request.getParameter("idCompra"));
                double total = 0.0;
                int foo = 0;

                for (Detalle detalle : detalles) {
                    if (detalle.getIDCompra() == idCompra) {
                        for (Producto esteProducto : productos) {
                            if (esteProducto.getId() == detalle.getIDProducto()) {
                                ProductoEnDetalle productoEnDetalle = new ProductoEnDetalle(esteProducto.getId(), esteProducto.getNombre(), esteProducto.getFoto(), esteProducto.getDescripcion(), esteProducto.getPrecio(), detalle.getCantidad());
                                productosEnDetalle.add(productoEnDetalle);
                                foo++;

                            }
                        }

                    }

                }

                for (ProductoEnDetalle PED : productosEnDetalle) {
                    total += PED.getPrecioTotal();

                }

                request.setAttribute("detalles", productosEnDetalle);
                request.setAttribute("total", total);
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("detalles", productosEnDetalle);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("detalles.jsp").forward(request, response);

                break;
            case "Logout":
                clienteActual = null;
                request.setAttribute("productos", productos);
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "EditarCuenta":
                request.setAttribute("productos", productos);
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "Editar":
                String nuevoNombre = request.getParameter("nombre");
                String nuevoApellido = request.getParameter("apellido");
                String nuevaCedula = request.getParameter("cedula");
                String nuevoCorreo = request.getParameter("email");
                String nuevaContraseña = request.getParameter("password");
                String nuevaDireccion = request.getParameter("direccion");
                Cliente clienteEditado = new Cliente(nuevaCedula, nuevoNombre, nuevoApellido, nuevaDireccion, nuevoCorreo, nuevaContraseña);
                int re = clienteDAO.actualizarCliente(clienteActual.getId(), clienteEditado);
                if (re != 0) {
                    request.setAttribute("productos", productos);
                    request.setAttribute("clienteActual", clienteActual);
                    request.setAttribute("contador", carrito.tamaño());
                    request.setAttribute("carrito", carrito.getProductos());
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.setAttribute("productos", productos);
                    request.setAttribute("clienteActual", clienteActual);
                    request.setAttribute("contador", carrito.tamaño());
                    request.setAttribute("carrito", carrito.getProductos());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                break;
            case "EliminarCuenta":
                comprasActuales.clear();
                for (Compra compra : compras) {
                    if (compra.getIdCliente() == clienteActual.getId()) {
                        comprasActuales.add(compra);
                    }
                }
                for (Compra compraActual : comprasActuales) {
                    detalleDAO.eliminarDetalles(compraActual.getId());
                }
                for (Compra compraActual : comprasActuales) {
                    compraDAO.eliminarCompra(clienteActual.getId());
                }

                clienteDAO.eliminarCliente(clienteActual.getId());
                
                clienteActual = null;
                
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("productos", productos);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            default:
                request.setAttribute("clienteActual", clienteActual);
                request.setAttribute("productos", productos);
                request.setAttribute("carrito", carrito.getProductos());
                request.setAttribute("contador", carrito.tamaño());
                request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
