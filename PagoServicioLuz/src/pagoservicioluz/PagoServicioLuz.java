package pagoservicioluz;

import controller.ControlPagoServicio;
import view.PagoServicio;

/**
 *
 * @author Acer
 */
public class PagoServicioLuz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PagoServicio vista = new PagoServicio();
        
        ControlPagoServicio controlador = new ControlPagoServicio(vista);
        
        vista.setController(controlador);
        vista.setVisible(true);
 
    }
}
