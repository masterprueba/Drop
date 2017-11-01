/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Entity.TCuota;
import Entity.TMulta;
import Entity.TPago;
import Entity.TPrestamo;
import Model.Prestamo_model;
import UI.PrestamosyAbonos_x_fecha_UI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class Prestamos_Abonos_x_fecha_Controller extends Controllers {

    private final Prestamo_model mPrestamo = new Prestamo_model();
    private final PrestamosyAbonos_x_fecha_UI vistaPrestamo_Abonos;
    private List<TPrestamo> listPrestamo;
    private List<TCuota> listCuota;
    private List<TMulta> listMulta;
    private String fechaInicio = "";
    private String fechaFin = "";

    public Prestamos_Abonos_x_fecha_Controller(PrestamosyAbonos_x_fecha_UI vista) {
        vistaPrestamo_Abonos = vista;
        mPrestamo.findAll(TCobrador.class).stream().filter((t) -> {
            return !((TCobrador) t).getTcobNombre().equals("REFINANCIACION");
        }).forEach((c) -> {

            vistaPrestamo_Abonos.jComboCobrador.addItem(((TCobrador) c).getTcobNombre());
        });
        mPrestamo.findAll(TPago.class).stream().
                filter((t) -> {
                    return !((TPago) t).getTipo().equals("AJUSTE-.");
                }).forEach((c) -> {
            vistaPrestamo_Abonos.jComboMetodoPago.addItem(((TPago) c).getTipo());
        });
    }

    public void verPrestamos() {
        obtenerFechas();
        vistaPrestamo_Abonos.modelo.setNumRows(0);
        listPrestamo = mPrestamo.prestamoPorFecha(fechaInicio, fechaFin);
        listPrestamo.stream().map((prestamo) -> {
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
            return filas;
        }).forEach((filas) -> {
            vistaPrestamo_Abonos.modelo.addRow(filas);
        });
        vistaPrestamo_Abonos.jTextField1.setText("" + Math.round(totalDeUnaTabla(vistaPrestamo_Abonos.modelo, 2)));
    }

    public void verAbonos() {
        obtenerFechas();
        vistaPrestamo_Abonos.modelo.setNumRows(0);
        String sql = (vistaPrestamo_Abonos.jComboCobrador.getSelectedIndex() != 0 ? "and cobrador.tcobNombre = '" + vistaPrestamo_Abonos.jComboCobrador.getSelectedItem().toString() + "'" : "") + (vistaPrestamo_Abonos.jComboMetodoPago.getSelectedIndex() != 0 ? " and pago.tipo ='" + vistaPrestamo_Abonos.jComboMetodoPago.getSelectedItem().toString() + "'" : "");
        listCuota = mPrestamo.abonoPorFecha(fechaInicio, fechaFin, sql);
        listCuota.stream().map((abono) -> {
            String[] filas = new String[12];
            filas[0] = abono.getTcuoId().toString();
            filas[1] = abono.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + abono.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido();
            filas[2] = "" + abono.getTcuoAbono();
            filas[3] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(abono.getTcuoFecha());
            filas[4] = abono.getTCobrador().getTcobNombre();
            filas[5] = abono.getTPago().getTipo();
            filas[6] = "" + abono.getTcuoNuevoSaldo();
            filas[7] = "" + abono.getTcuoCuotasPagadas();
            return filas;
        }).forEach((filas) -> {
            vistaPrestamo_Abonos.modelo.addRow(filas);
        });
        vistaPrestamo_Abonos.jTextField1.setText("" + Math.round(totalDeUnaTabla(vistaPrestamo_Abonos.modelo, 2)));
        vistaPrestamo_Abonos.jTextField2.setText("" + Math.round(mPrestamo.remanenteFecha(fechaInicio, fechaFin)));
    }

    public void verMultas() {
        obtenerFechas();
        vistaPrestamo_Abonos.modelo.setNumRows(0);
        listMulta = mPrestamo.multaPorFecha(fechaInicio, fechaFin);
        listMulta.stream().filter(a -> a.getTmulEstado().equals("eliminado")).map((multa) -> {
            String[] filas = new String[12];
            filas[0] = multa.getTmulId().toString();
            filas[1] = multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido();
            filas[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(multa.getTmulFecha());
            filas[3] = multa.getTmulDescripcion();
            filas[4] = "" + multa.getTmulValor();
            filas[5] = "" + multa.getTPrestamo().getTpreValorPrestamo();
            return filas;
        }).forEach((filas) -> {
            vistaPrestamo_Abonos.modelo.addRow(filas);
        });
        vistaPrestamo_Abonos.jTextField1.setText("" + Math.round(totalDeUnaTabla(vistaPrestamo_Abonos.modelo, 4)));
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

    public void imprimirTabla(JTable jTable, String header, String footer, boolean showPrintDialog) {
        utilJTablePrint(jTable, header, footer, showPrintDialog);
    }

}
