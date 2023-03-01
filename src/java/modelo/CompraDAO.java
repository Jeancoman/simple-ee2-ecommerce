package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    Connection connection;
    Conexion conexion = new Conexion();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    int response = 0;

    public int comprar(Compra compra) {
        String SQL = "insert into compra(idCliente,idPago,FechaCompra,Monto,Estado)values(?,?,?,?,?)";
        int idCompra;

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, compra.getCliente().getId());
            preparedStatement.setInt(2, compra.getIdPago());
            preparedStatement.setString(3, compra.getFecha());
            preparedStatement.setDouble(4, compra.getMonto());
            preparedStatement.setString(5, compra.getEstado());
            response = preparedStatement.executeUpdate();

            SQL = "select @@IDENTITY AS idCompra";
            resultSet = preparedStatement.executeQuery(SQL);
            resultSet.next();
            idCompra = resultSet.getInt("idCompra");
            resultSet.close();
            System.out.println("EXITOS BRO");

            for (ProductoEnCarrito producto : compra.getComprados()) {
                SQL = "insert into detalle(idProducto,idCompra,Cantidad,PrecioCompra)values(?,?,?,?)";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setInt(1, producto.getId());
                preparedStatement.setInt(2, idCompra);
                preparedStatement.setInt(3, producto.getCantidad());
                preparedStatement.setDouble(4, producto.getPrecio());
                response = preparedStatement.executeUpdate();
                System.out.println("Ejecutandose");
            }
            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public List listarCompras() {
        List<Compra> compras = new ArrayList<>();
        String SQL = "select * from compra";

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Compra compra = new Compra(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6));
                compras.add(compra);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return compras;

    }

    public void eliminarCompra(int id) {
        String SQL = "delete from compra where idCliente=" + id;

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
