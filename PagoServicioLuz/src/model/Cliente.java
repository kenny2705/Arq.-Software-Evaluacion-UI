package model;

/**
 *
 * @author Acer
 */
public class Cliente {
    private int id;
    private String nombreCompleto;
    private String direccion;
    private String email;

    public Cliente(int id, String nombreCompleto, String direccion, String email) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombreCompleto=" + nombreCompleto + ", direccion=" + direccion + ", email=" + email + '}';
    }
    
    
}
