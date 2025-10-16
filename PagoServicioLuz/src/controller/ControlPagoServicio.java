package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import model.Cliente;
import model.EstadoRecibo;
import model.Pago;
import model.Recibo;
import model.Servicio;
import view.PagoServicio;

/**
 *
 * @author Acer
 */
public class ControlPagoServicio {
    private final PagoServicio vista;
    private List<Cliente> clientes;
    private List<Servicio> servicios;
    private List<Recibo> recibos;
    private List<Pago> pagos;
    private Recibo reciboActual;

    public ControlPagoServicio(PagoServicio vista) {
        this.vista = vista;
        this.cargarDatosIniciales();
    }

   
    public void buscarServicios(String texto) {
        if (texto == null || texto.isEmpty()) {
        vista.actualizarTabla(new Object[0][3]);
        return;
    }

    List<Servicio> serviciosEncontrados = servicios.stream()
        .filter(s -> s.getNumeroServicio().startsWith(texto))
        .collect(Collectors.toList());

    Object[][] datosParaTabla = new Object[serviciosEncontrados.size()][3];
    for (int i = 0; i < serviciosEncontrados.size(); i++) {
        Servicio s = serviciosEncontrados.get(i);
        datosParaTabla[i][0] = s.getNumeroServicio();
        datosParaTabla[i][1] = s.getCliente().getNombreCompleto();

        Recibo recibo = recibos.stream()
            .filter(rec -> rec.getServicio() == s)
            .findFirst().orElse(null);
        
        datosParaTabla[i][2] = (recibo!= null) ? recibo.getEstado().toString() : "Al Corriente";
    }
    
    // El Controlador envia a la Vista los datos ya listos para mostrar.
    vista.actualizarTabla(datosParaTabla);
    }

    // Metodo llamado por la vista cuando se selecciona una fila de la tabla
    public void seleccionarServicio(String numeroServicio) {
        this.reciboActual = recibos.stream()
            .filter(r -> r.getServicio().getNumeroServicio().equals(numeroServicio) && r.getEstado() == EstadoRecibo.PENDIENTE)
            .findFirst()
            .orElse(null);
        vista.mostrarDetallesRecibo(reciboActual);
    }
    
    // Metodo llamado por el boton "Pagar"
    public void realizarPago(String numTarjeta, String montoStr) {
        // 1. Validar que hay un recibo seleccionado
        if (reciboActual == null) {
            vista.mostrarMensaje("Error: Por favor, seleccione un servicio con un recibo pendiente.", "Error de Pago", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Validar numero de tarjeta (16 a 19 digitos numericos)
        if (!numTarjeta.matches("\\d{16,19}")) {
            vista.mostrarMensaje("Error: El numero de tarjeta debe contener entre 16 y 19 digitos.", "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Validar monto
        double montoPagado;
        try {
            montoPagado = Double.parseDouble(montoStr);
            if (montoPagado <= 0) {
                vista.mostrarMensaje("Error: El monto a pagar debe ser un numero positivo.", "Error de Validacion", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (montoPagado < reciboActual.getTotal()) {
                 vista.mostrarMensaje("Error: El monto no es suficiente para pagar el total del recibo.", "Error de Validacion", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Error: El monto a pagar debe ser un valor numerico valido.", "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Si todo es valido, procesar el pago
        String autorizacion = "AUT" + ThreadLocalRandom.current().nextInt(100000, 999999);
        String ultimos4Digitos = numTarjeta.substring(numTarjeta.length() - 4);

        Pago nuevoPago = new Pago(
            pagos.size() + 1,LocalDateTime.now(),
            reciboActual.getTotal(),
            autorizacion,
            "**** **** **** " + ultimos4Digitos
        );
        pagos.add(nuevoPago);
        
        reciboActual.registrarPago(nuevoPago); // Esto cambia el estado a PAGADO
        
        vista.mostrarMensaje("EL PAGO SE HA REALIZADO\nNumero de Autorizacion: " + autorizacion, "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE);
        vista.mostrarDetallesRecibo(reciboActual); // Actualiza la vista para mostrar el estado "PAGADO"
        vista.limpiarCamposPago();
    }
    
    // Metodo llamado por el boton "Cancelar"
    public void cancelarPago() {
        vista.limpiarCamposPago();
    }

    private void cargarDatosIniciales() {
        this.clientes = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.recibos = new ArrayList<>();
        this.pagos = new ArrayList<>();

        Cliente c1 = new Cliente(101, "Kevin Daniel Soto Mariscal", "Olivos 338, Colonia Mexico", "kevin@gmail.com");
        Cliente c2 = new Cliente(102, "Maria Lopez Zazueta", "Av. Antonio Caso 742, Itson", "maria@gmail.com");
        clientes.add(c1);
        clientes.add(c2);

        Servicio s1 = new Servicio("1234567890", c1.getDireccion(), c1);
        Servicio s2 = new Servicio("0987654321", "Kino 234", c1);
        Servicio s3 = new Servicio("0123456789", c2.getDireccion(), c2);
        servicios.add(s1);
        servicios.add(s2);
        servicios.add(s3);

        recibos.add(new Recibo(1, "Enero-Febrero 2025", LocalDate.of(2025, 10, 25), 350.50, EstadoRecibo.PAGADO, s1, null));
        recibos.add(new Recibo(2, "Enero 2025", LocalDate.of(2025, 10, 28), 150.00, EstadoRecibo.PENDIENTE ,s2, null));
        recibos.add(new Recibo(3, "Octubre 2025", LocalDate.of(2025, 10, 22), 480.75,EstadoRecibo.PENDIENTE ,s3, null));
    }
}
