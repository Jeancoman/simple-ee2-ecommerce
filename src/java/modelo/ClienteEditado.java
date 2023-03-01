package modelo;

public class ClienteEditado extends Cliente {
    
    public ClienteEditado(String nombre, String apellido, String cedula, String direccion, String email, String contraseña) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCedula(cedula);
        this.setDireccion(direccion);
        this.setEmail(email);
        this.setPassword(contraseña);
    }
}
