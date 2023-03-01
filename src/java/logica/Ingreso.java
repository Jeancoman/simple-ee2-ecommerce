package logica;

import java.util.List;
import modelo.Cliente;


public class Ingreso {
    private List<Cliente> clientes;
    private String email;
    private String password;

    public Ingreso(List<Cliente> clientes, String email, String password) {
        this.clientes = clientes;
        this.email = email;
        this.password = password;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Cliente retornarCliente(){
        Cliente retorno = null;
        
        for(Cliente cliente : clientes){
            if(cliente.getEmail().equals(email) && cliente.getPassword().equals(password)){
                retorno = cliente;
            }
        }
        
        return retorno; 
    }
    
    
    
}
