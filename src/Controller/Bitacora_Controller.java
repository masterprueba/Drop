/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBitacora;
import Entity.TDatosBasicosPersona;
import Entity.*;
import Model.Bitacora_Model;
import Model.DatosBasicosPersona_Model;
import UI.Bitacora_UI;
import UI.Bitacora_Usuario;
import UI.MainDesktop;
import static UI.MainDesktop.checkInstance;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ITERIA
 */
public class Bitacora_Controller extends Controllers {

    private final Bitacora_Model mBitacora = new Bitacora_Model();
    private final Bitacora_UI vistaBitacora;
    private List<TBitacora> lBitacora;
    private final DatosBasicosPersona_Model mLogin = new DatosBasicosPersona_Model();
    protected String fechaInicio = "";
    protected String fechaFin = "";

    public Bitacora_Controller(Bitacora_UI vistaBitacora) {
        this.vistaBitacora = vistaBitacora;
    }

    public void verBitacoraGeneral() {
        try {
            final Gson gson = new Gson();
            obtenerFechas();
            //CASOS PARA LOS MODULOS
            switch (vistaBitacora.bitacora) {
                //PARA INICIO DE SESION
                case "INICIO":
                    lBitacora = mBitacora.consultarFechaBitsesion(fechaInicio, fechaFin, 1);
                    if (!lBitacora.isEmpty()) {
                        for (int i = 0; i < lBitacora.size(); i++) {
                            TLogin login = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TLogin.class);
                            String[] fila = new String[6];
                            fila[1] = login.getTDatosBasicosPersona().getTdbpCedula();
                            fila[2] = login.getTDatosBasicosPersona().getTdbpNombre() + " " + login.getTDatosBasicosPersona().getTdbpApellido();
                            fila[3] = new SimpleDateFormat("dd/MM/yyyy").format(lBitacora.get(i).getTbitFecha());
                            fila[4] = new SimpleDateFormat("HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                            fila[5] = lBitacora.get(i).getTbitIdentificador();
                            vistaBitacora.modeloTabla1.addRow(fila);
                        }
                    }
                    break;
                case "PRESTAMO":
                    lBitacora = mBitacora.consultarFechaBitsesion(fechaInicio, fechaFin, 2);
                    if (!lBitacora.isEmpty()) {
                        for (int i = 0; i < lBitacora.size(); i++) {
                            TPrestamo prestamo = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPrestamo.class);
                            String[] fila = new String[6];
                            //DATOS
                            vistaBitacora.modeloTabla1.addRow(fila);
                        }
                    }
                    break;
            }
            numerarTabla(vistaBitacora.modeloTabla1);
        } catch (JsonSyntaxException ex) {
            JOptionPane.showMessageDialog(vistaBitacora, "Error parseando a Json" + ex);
        }
    }

    private void obtenerFechas() {
        switch (vistaBitacora.jComboBox1.getSelectedIndex()) {
            case 0:
                fechaInicio = Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE);
                fechaFin = fechaInicio;
                break;
            case 1:
                fechaInicio = Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-01";
                //OBTENER LA CANTIDAD DE DIAS EN EL MES -- ESTO ES PARA SABER HASTA DONDE DEBE BUSCAR EL QUERY UN EJEMPLO ES FEBRERO QUE SOLO TIENE 28
                Calendar cal = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 1);
                int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                fechaFin = Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + days;
                break;
            case 2:
                if (vistaBitacora.Comp_Fecha_Desde1.getDate() != null && vistaBitacora.Comp_Fecha_Desde2.getDate() != null) {
                    fechaInicio = vistaBitacora.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "-" + (vistaBitacora.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + vistaBitacora.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                    fechaFin = vistaBitacora.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "-" + (vistaBitacora.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + vistaBitacora.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione la fecha de inicio y la fecha final");
                }
                break;
        }
    }

    public void desactivarFechas(int V) {
        if (V == 1) {
            vistaBitacora.jLabel7.setEnabled(false);
            vistaBitacora.jLabel8.setEnabled(false);
            vistaBitacora.Comp_Fecha_Desde1.setEnabled(false);
            vistaBitacora.Comp_Fecha_Desde2.setEnabled(false);
            vistaBitacora.jButton3.setEnabled(false);
        } else {
            vistaBitacora.jLabel7.setEnabled(true);
            vistaBitacora.jLabel8.setEnabled(true);
            vistaBitacora.Comp_Fecha_Desde1.setEnabled(true);
            vistaBitacora.Comp_Fecha_Desde2.setEnabled(true);
            vistaBitacora.jButton3.setEnabled(true);
        }
    }

    public void verIndependiente() {
        List<TDatosBasicosPersona> listLogin = mLogin.ConsultarPersonasConLogin();
        vistaBitacora.modeloTabla2.setNumRows(0);
        if (!listLogin.isEmpty()) {
            for (int i = 0; i < listLogin.size(); i++) {
                String[] fila = new String[6];
                fila[1] = listLogin.get(i).getTdbpCedula();
                fila[2] = listLogin.get(i).getTdbpNombre() + " " + listLogin.get(i).getTdbpApellido();
                fila[3] = listLogin.get(i).getTdbpId().toString();
                vistaBitacora.modeloTabla2.addRow(fila);
            }
            numerarTabla(vistaBitacora.modeloTabla2);
        }
    }

    public void bitacoraUsuario(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int fila = vistaBitacora.jTable2.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                DefaultTableModel model = null;
                switch (vistaBitacora.bitacora) {
                    case "INICIO":
                        new TableModel().historialUsuarioInicioSession();
                        lBitacora = mBitacora.consultarInicioUsuario(vistaBitacora.modeloTabla2.getValueAt(fila, 3).toString());
                        if (!lBitacora.isEmpty()) {
                            for (int i = 0; i < lBitacora.size(); i++) {
                                String[] filas = new String[6];
                                filas[1] = new SimpleDateFormat("yyyy").format(lBitacora.get(i).getTbitFecha());
                                filas[2] = new SimpleDateFormat("MM").format(lBitacora.get(i).getTbitFecha());
                                filas[3] = new SimpleDateFormat("dd").format(lBitacora.get(i).getTbitFecha());
                                filas[4] = new SimpleDateFormat("HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                model.addRow(filas);
                            }
                        }
                }
                numerarTabla(model);
                Bitacora_Usuario run = new Bitacora_Usuario(model, vistaBitacora.modeloTabla2.getValueAt(fila, 2).toString());
                JInternalFrame in = run.cargarInternal();
                MainDesktop.DesktopPaneMain.add(in);
                in.moveToFront();
                checkInstance(in);
            }
        }
    }
}
