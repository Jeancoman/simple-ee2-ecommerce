package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO {

    private Connection connection;
    private final Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String SQL = "select * from producto";

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5));
                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;

    }
    
    
    public Producto listarId(int id){
        String SQL = "select * from producto where idProducto=" + id;
        Producto producto = new Producto();
        
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                producto.setId(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setFoto(resultSet.getString(3));
                producto.setDescripcion(resultSet.getString(4));
                producto.setPrecio(resultSet.getDouble(5));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return producto;
        
    }
}
