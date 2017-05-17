/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBitacora;
import Entity.TDatosBasicosPersona;
import Entity.TLogin;
import Entity.TPersona;
import Entity.TPrestamo;
import Entity.TReferencia;
import Model.Bitacora_Model;
import Model.DatosBasicosPersona_Model;
import UI.Bitacora_UI;
import UI.Bitacora_Individual;
import static UI.MainDesktop.DesktopPaneMain;
import static UI.MainDesktop.calcWidthHeight;
import static UI.MainDesktop.checkInstance;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public static List<TBitacora> lBitacora;
    private final DatosBasicosPersona_Model mLogin = new DatosBasicosPersona_Model();
    protected String fechaInicio = "";
    protected String fechaFin = "";
    public static final List<Object> listObject = new ArrayList<>();

    public Bitacora_Controller(Bitacora_UI vistaBitacora) {
        this.vistaBitacora = vistaBitacora;
    }

    public void verBitacoraGeneral() {
        try {
            final Gson gson = new Gson();
            obtenerFechas();
            vistaBitacora.modeloTabla1.setNumRows(0);
            lBitacora = mBitacora.consultarBitacora(fechaInicio, fechaFin, Bitacora_UI.bitacora);
            switch (Bitacora_UI.bitacora) {
                case "INICIO":
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
                    listObject.clear();
                    if (!lBitacora.isEmpty()) {
                        for (int i = 0; i < lBitacora.size(); i++) {
                            TPrestamo prestamo = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPrestamo.class);
                            String[] fila = new String[7];
                            fila[1] = lBitacora.get(i).getTLogin().getTlogUserLogin();
                            fila[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                            fila[3] = lBitacora.get(i).getTbitIdentificador();
                            fila[4] = prestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido();
                            fila[5] = "" + prestamo.getTpreValorPrestamo();
                            fila[6] = "" + prestamo.getTpreId();
                            vistaBitacora.modeloTabla1.addRow(fila);
                        }
                    }
                    lBitacora = mBitacora.consultarAllModulo("PRESTAMO");
                    for (int i = 0; i < lBitacora.size(); i++) {
                        TPrestamo prestamo = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPrestamo.class);
                        listObject.add(i, prestamo);
                    }
                    break;
                case "CLIENTE":
                    listObject.clear();
                    if (!lBitacora.isEmpty()) {
                        for (int i = 0; i < lBitacora.size(); i++) {
                            if (lBitacora.get(i).getTbitClassname().equals("Entity.TPersona")) {
                                TPersona cliente = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPersona.class);
                                if (cliente.getTperTipo().equals("CLIENTE")) {
                                    String[] fila = new String[8];
                                    fila[1] = cliente.getTDatosBasicosPersona().getTdbpCedula();
                                    fila[2] = cliente.getTDatosBasicosPersona().getTdbpNombre();
                                    fila[3] = "asdfasdsad";
                                    fila[4] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                    fila[5] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                    fila[6] = lBitacora.get(i).getTbitIdentificador();
                                    fila[7] = cliente.getTperId().toString();
                                    vistaBitacora.modeloTabla1.addRow(fila);
                                }
                            }
                        }
                    }
                    lBitacora = mBitacora.consultarAllModulo("CLIENTE");
                    for (int i = 0; i < lBitacora.size(); i++) {
                        if (lBitacora.get(i).getTbitClassname().equals("Entity.TPersona")) {
                            TPersona cliente = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPersona.class);
                            listObject.add(i, cliente);
                        } else if (lBitacora.get(i).getTbitClassname().equals("Entity.TDatosBasicosPersona")) {
                            TDatosBasicosPersona datosBasicos = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TDatosBasicosPersona.class);
                            listObject.add(i, datosBasicos);
                        } else if (lBitacora.get(i).getTbitClassname().equals("Entity.TReferencia")) {
                            TReferencia referencia = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TReferencia.class);
                            listObject.add(i, referencia);
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
        switch (Bitacora_UI.jComboBox1.getSelectedIndex()) {
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
                if (Bitacora_UI.Comp_Fecha_Desde1.getDate() != null && Bitacora_UI.Comp_Fecha_Desde2.getDate() != null) {
                    fechaInicio = Bitacora_UI.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "-" + (Bitacora_UI.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + Bitacora_UI.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                    fechaFin = Bitacora_UI.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "-" + (Bitacora_UI.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + Bitacora_UI.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione la fecha de inicio y la fecha final");
                }
                break;
        }
    }

    public void desactivarFechas(int V) {
        if (V == 1) {
            Bitacora_UI.jLabel7.setEnabled(false);
            Bitacora_UI.jLabel8.setEnabled(false);
            Bitacora_UI.Comp_Fecha_Desde1.setEnabled(false);
            Bitacora_UI.Comp_Fecha_Desde2.setEnabled(false);
            Bitacora_UI.jButton3.setEnabled(false);
        } else {
            Bitacora_UI.jLabel7.setEnabled(true);
            Bitacora_UI.jLabel8.setEnabled(true);
            Bitacora_UI.Comp_Fecha_Desde1.setEnabled(true);
            Bitacora_UI.Comp_Fecha_Desde2.setEnabled(true);
            Bitacora_UI.jButton3.setEnabled(true);
        }
    }

    public void verUsuarios() {
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

    public void bitacoraGeneralIndividual(MouseEvent evt, int flag) {
        if (evt.getClickCount() == 2) {
            int fila = flag == 1 ? Bitacora_UI.jTable1.rowAtPoint(evt.getPoint()) : Bitacora_UI.jTable2.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                DefaultTableModel model = null;
                String cadena = "";
                if (flag == 1) {
                    switch (Bitacora_UI.bitacora) {
                        case "PRESTAMO":
                            int idPrestamo = Integer.parseInt(Bitacora_UI.jTable1.getModel().getValueAt(fila, 6).toString());
                            model = new TableModel().bitacoraIndividualPrestamo();
                            cadena = "La primera fila resaltada de color verde es el estado actual del prestamo";
                            if (!listObject.isEmpty()) {
                                for (int i = 0; i < listObject.size(); i++) {
                                    if (lBitacora.get(i).getTbitClassname().equals("Entity.TPrestamo")) {
                                        TPrestamo prestamo = (TPrestamo) listObject.get(i);
                                        if (prestamo.getTpreId() == idPrestamo) {
                                            String[] filas = new String[12];
                                            filas[1] = prestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + "" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido();
                                            filas[2] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + "" + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                            filas[3] = prestamo.getTpreMetodPago();
                                            filas[4] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(prestamo.getTpreFechaEntrega());
                                            filas[5] = "" + prestamo.getTpreNumCuotas();
                                            filas[6] = "" + prestamo.getTpreIntereses();
                                            filas[7] = "" + prestamo.getTpreValorPrestamo();
                                            filas[8] = "" + prestamo.getTpreValorCuota();
                                            filas[9] = "" + prestamo.getTpreValorTotal();
                                            filas[10] = lBitacora.get(i).getTbitIdentificador();
                                            filas[11] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                            model.addRow(filas);
                                        }
                                    }
                                }
                            }
                            break;
                        case "CLIENTE":
                            int idcliente = Integer.parseInt(Bitacora_UI.jTable1.getModel().getValueAt(fila, 7).toString());
                            cadena = "La primera fila resaltada de color verde es el estado actual del cliente";
                            model = new TableModel().bitacoraIndividualClientes();
                            for (int i = 0; i < listObject.size(); i++) {
                                if (lBitacora.get(i).getTbitClassname().equals("Entity.TPersona")) {
                                    TPersona cliente = (TPersona) listObject.get(i);
                                    if (cliente.getTperId() == idcliente) {
                                        String[] filas = new String[14];
                                        filas[1] = cliente.getTDatosBasicosPersona().getTdbpCedula();
                                        filas[2] = cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido();
                                        filas[3] = cliente.getTDatosBasicosPersona().getTdbpTel();
                                        filas[4] = cliente.getTperCasDir();
                                        filas[5] = cliente.getTperCasTipo();
                                        filas[6] = cliente.getTperEmpNom();
                                        filas[7] = cliente.getTperEmpDir();
                                        filas[8] = cliente.getTperEmpTel();
                                        filas[9] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                        filas[10] = lBitacora.get(i).getTbitIdentificador();
                                        filas[11] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                        filas[12] = cliente.getTperCodeudor();
                                        filas[13] = cliente.getTDatosBasicosPersona().getTdbpId().toString();
                                        model.addRow(filas);
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    model = new TableModel().historialUsuarioInicioSession();
                    cadena = vistaBitacora.modeloTabla2.getValueAt(fila, 2).toString();
                    lBitacora = mBitacora.consultarInicioUsuario(Bitacora_UI.jTable2.getModel().getValueAt(fila, 3).toString());
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
                if (model != null) {
                    numerarTabla(model);
                    JInternalFrame in = new Bitacora_Individual(model, cadena, 1);
                    in.moveToFront();
                    checkInstance(in);
                }
            }
        }
    }

    public static void detalleBitacoraIndividual(int posicion, String persona) {
        String cadena = "";
        DefaultTableModel model = null;
        int flag = 0;
        switch (persona) {
            case "CODEUDOR":
                cadena = "La primera fila resaltada de color verde es el estado actual del codeudor";
                model = new TableModel().bitacoraIndividualClientes();
                flag = 2;
                if (lBitacora.get(posicion).getTbitClassname().equals("Entity.TPersona")) {
                    TPersona cliente = (TPersona) listObject.get(posicion);
                    String[] filas = new String[14];
                    filas[1] = cliente.getTDatosBasicosPersona().getTdbpCedula();
                    filas[2] = cliente.getTDatosBasicosPersona().getTdbpNombre() + " " + cliente.getTDatosBasicosPersona().getTdbpApellido();
                    filas[3] = cliente.getTDatosBasicosPersona().getTdbpTel();
                    filas[4] = cliente.getTperCasDir();
                    filas[5] = cliente.getTperCasTipo();
                    filas[6] = cliente.getTperEmpNom();
                    filas[7] = cliente.getTperEmpDir();
                    filas[8] = cliente.getTperEmpTel();
                    filas[9] = lBitacora.get(posicion).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(posicion).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                    filas[10] = lBitacora.get(posicion).getTbitIdentificador();
                    filas[11] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(posicion).getTbitFecha());
                    filas[12] = cliente.getTperCodeudor();
                    filas[13] = cliente.getTDatosBasicosPersona().getTdbpId().toString();
                    model.addRow(filas);
                }
                break;
        }
        JInternalFrame in = new Bitacora_Individual(model, cadena, flag);
        in.moveToFront();
        DesktopPaneMain.add(in);
        calcWidthHeight(in);
        in.show();
    }
}
