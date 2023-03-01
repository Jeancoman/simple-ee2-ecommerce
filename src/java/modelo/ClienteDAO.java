package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    private Connection connection;
    private final Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private int r = 0;

    public int crearCliente(Cliente cliente) {

        String SQL = "insert into cliente(Cedula,Nombre,Apellido,Direccion,Email,Password)values(?,?,?,?,?,?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, cliente.getCedula());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getPassword());
            r = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String SQL = "select * from cliente";

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public int eliminarCliente(int id) {
        String SQL = "delete from cliente where idCliente=" + id;

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            r = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return r;
    }

    public int actualizarCliente(int id, Cliente cliente) {
        String SQL = "UPDATE cliente SET Nombre = ?, Apellido = ?, Cedula = ?, Direccion = ?, Email = ?, Password = ? where idCliente = " + id;

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getCedula());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getPassword());
            r = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return r;

    }


}
