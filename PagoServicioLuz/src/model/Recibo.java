package model;

import java.time.LocalDate;

/**
 *
 * @author Acer
 */
public class Recibo {
    private int id;
    private String periodoConsumo;
    private LocalDate fechaVencimiento;
    private double total;
    private EstadoRecibo estado;
    private Servicio servicio;
    private Pago pago;

    public Recibo(int id, String periodoConsumo, LocalDate fechaVencimiento, double total, EstadoRecibo estado, Servicio servicio, Pago pago) {
        this.id = id;
        this.periodoConsumo = periodoConsumo;
        this.fechaVencimiento = fechaVencimiento;
        this.total = total;
        this.estado = estado;
        this.servicio = servicio;
        this.pago = pago;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriodoConsumo() {
        return periodoConsumo;
    }

    public void setPeriodoConsumo(String periodoConsumo) {
        this.periodoConsumo = periodoConsumo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EstadoRecibo getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecibo estado) {
        this.estado = estado;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Recibo{" + "id=" + id + ", periodoConsumo=" + periodoConsumo + ", fechaVencimiento=" + fechaVencimiento + ", total=" + total + ", estado=" + estado + ", servicio=" + servicio + ", pago=" + pago + '}';
    }

     public void registrarPago(Pago nuevoPago) {
        if (this.estado == EstadoRecibo.PENDIENTE) {
            this.pago = nuevoPago;
            this.estado = EstadoRecibo.PAGADO;
        }
    }
    
}
