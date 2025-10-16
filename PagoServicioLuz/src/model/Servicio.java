package model;

/**
 *
 * @author Acer
 */
public class Servicio {
    private String numeroServicio;
    private String direccionServicio;
    private Cliente cliente;

    public Servicio(String numeroServicio, String direccionServicio, Cliente cliente) {
        this.numeroServicio = numeroServicio;
        this.direccionServicio = direccionServicio;
        this.cliente = cliente;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(String numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public String getDireccionServicio() {
        return direccionServicio;
    }

    public void setDireccionServicio(String direccionServicio) {
        this.direccionServicio = direccionServicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Servicio{" + "numeroServicio=" + numeroServicio + ", direccionServicio=" + direccionServicio + ", cliente=" + cliente + '}';
    }
    
    
   
    
    
}
