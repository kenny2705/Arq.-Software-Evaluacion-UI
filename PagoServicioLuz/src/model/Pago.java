package model;

import java.time.LocalDateTime;

/**
 *
 * @author Acer
 */
public class Pago {
    private int id;
    private LocalDateTime fechaHora;
    private double totalPagado;
    private String numroAutorizacion;
    private String datosTarjeta;

    public Pago(int id, LocalDateTime fechaHora, double totalPagado, String numroAutorizacion, String datosTarjeta) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.totalPagado = totalPagado;
        this.numroAutorizacion = numroAutorizacion;
        this.datosTarjeta = datosTarjeta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public String getNumroAutorizacion() {
        return numroAutorizacion;
    }

    public void setNumroAutorizacion(String numroAutorizacion) {
        this.numroAutorizacion = numroAutorizacion;
    }

    public String getDatosTarjeta() {
        return datosTarjeta;
    }

    public void setDatosTarjeta(String datosTarjeta) {
        this.datosTarjeta = datosTarjeta;
    }

    @Override
    public String toString() {
        return "Pago{" + "id=" + id + ", fechaHora=" + fechaHora + ", totalPagado=" + totalPagado + ", numroAutorizacion=" + numroAutorizacion + ", datosTarjeta=" + datosTarjeta + '}';
    }
    
    
}
