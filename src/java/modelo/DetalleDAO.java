package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleDAO {

    private Connection connection;
    private final Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Detalle> listarDetalles() {
        List<Detalle> detalles = new ArrayList<>();
        String SQL = "select * from detalle";

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Detalle detalle = new Detalle(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getDouble(5));
                detalles.add(detalle);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return detalles;

    }
    
    public void eliminarDetalles(int id){
        String SQL = "delete from detalle where idCompra=" + id;
        
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
            connection.commit();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
