/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCuota;
import Entity.TPrestamo;
import Model.Prestamo_model;
import UI.Prestamos_x_fecha;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Prestamos_Abonos_Xfecha {

    private final Prestamo_model mPrestamo = new Prestamo_model();
    private static Prestamos_x_fecha vistaPrestamo_Abonos;
    private List<TPrestamo> listPrestamo;
    private List<TCuota> listCuota;
    private String fechaInicio = "";
    private String fechaFin = "";

    public Prestamos_Abonos_Xfecha(Prestamos_x_fecha vista) {
        vistaPrestamo_Abonos = vista;
    }

    public void verPrestamos() {
        obtenerFechas();
        vistaPrestamo_Abonos.modelo.setNumRows(0);
        listPrestamo = mPrestamo.prestamoPorFecha(fechaInicio, fechaFin);
        for (TPrestamo prestamo : listPrestamo) {
            String[] filas = new String[12];
            filas[0] = prestamo.getTpreId().toString();
            filas[1] = prestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido();
            filas[2] = "" + prestamo.getTpreValorPrestamo();
            filas[3] = "" + prestamo.getTpreNumCuotas();
            filas[4] = "" + prestamo.getTpreIntereses();
            filas[5] = prestamo.getTpreMetodPago();
            filas[6] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(prestamo.getTpreFechaEntrega());
            filas[7] = "" + prestamo.getTpreValorTotal();
            filas[8] = "" + prestamo.getTpreValorCuota();
            vistaPrestamo_Abonos.modelo.addRow(filas);
        }
    }

    private void obtenerFechas() {
        switch (vistaPrestamo_Abonos.jComboBox1.getSelectedIndex()) {
            case 0:
                fechaInicio = Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE);
                fechaFin = fechaInicio;
                break;
            case 1:
                fechaInicio = Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-01";
                Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 1);
                int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                fechaFin = Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + days;
                break;
            case 2:
                if (vistaPrestamo_Abonos.Comp_Fecha_Desde1.getDate() != null && vistaPrestamo_Abonos.Comp_Fecha_Desde2.getDate() != null) {
                    fechaInicio = vistaPrestamo_Abonos.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "-" + (vistaPrestamo_Abonos.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + vistaPrestamo_Abonos.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                    fechaFin = vistaPrestamo_Abonos.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "-" + (vistaPrestamo_Abonos.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + vistaPrestamo_Abonos.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione la fecha de inicio y la fecha final");
                }
                break;
        }
    }

    public void desactivarFechas(int V) {
        if (V == 1) {
            vistaPrestamo_Abonos.jLabel7.setEnabled(false);
            vistaPrestamo_Abonos.jLabel8.setEnabled(false);
            vistaPrestamo_Abonos.Comp_Fecha_Desde1.setEnabled(false);
            vistaPrestamo_Abonos.Comp_Fecha_Desde2.setEnabled(false);
            vistaPrestamo_Abonos.jButton3.setEnabled(false);
        } else {
            vistaPrestamo_Abonos.jLabel7.setEnabled(true);
            vistaPrestamo_Abonos.jLabel8.setEnabled(true);
            vistaPrestamo_Abonos.Comp_Fecha_Desde1.setEnabled(true);
            vistaPrestamo_Abonos.Comp_Fecha_Desde2.setEnabled(true);
            vistaPrestamo_Abonos.jButton3.setEnabled(true);
        }
    }

}
