/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.*;
import Model.Bitacora_Model;
import Model.DatosBasicosPersona_Model;
import Model.Multa_Model;
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
import java.util.Objects;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ITERIA
 */
public class Bitacora_Controller extends Controllers {

    private final Bitacora_Model mBitacora = new Bitacora_Model();
    public static Bitacora_UI vistaBitacora;
    public static List<TBitacora> lBitacora;
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
                            fila[1] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                            fila[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                            fila[3] = lBitacora.get(i).getTbitIdentificador();
                            fila[4] = prestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido();
                            fila[5] = "" + prestamo.getTpreValorTotal();
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
                                    fila[3] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                    fila[4] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                    fila[5] = lBitacora.get(i).getTbitIdentificador();
                                    fila[6] = cliente.getTperId().toString();
                                    vistaBitacora.modeloTabla1.addRow(fila);
                                }
                            }
                        }
                    }
                    lBitacora = mBitacora.consultarAllModulo("CLIENTE");
                    for (int i = 0; i < lBitacora.size(); i++) {
                        switch (lBitacora.get(i).getTbitClassname()) {
                            case "Entity.TPersona":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPersona.class));
                                break;
                            case "Entity.TDatosBasicosPersona":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TDatosBasicosPersona.class));
                                break;
                            case "Entity.TReferencia":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TReferencia.class));
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "GASTOS":
                    listObject.clear();
                    if (!lBitacora.isEmpty()) {
                        for (int i = 0; i < lBitacora.size(); i++) {
                            TGasto gasto = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TGasto.class);
                            String[] fila = new String[8];
                            fila[1] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(gasto.getTgasFecha());
                            fila[2] = gasto.getTgasDesc();
                            fila[3] = gasto.getTgasCosto().toString();
                            fila[4] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                            fila[5] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                            fila[6] = lBitacora.get(i).getTbitIdentificador();
                            fila[7] = gasto.getTgasId().toString();
                            vistaBitacora.modeloTabla1.addRow(fila);
                        }
                    }
                    lBitacora = mBitacora.consultarAllModulo("GASTOS");
                    for (int i = 0; i < lBitacora.size(); i++) {
                        listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TGasto.class));
                    }
                    break;
                case "ABONO":
                    listObject.clear();
                    if (!lBitacora.isEmpty()) {
                        for (int i = 0; i < lBitacora.size(); i++) {
                            if (lBitacora.get(i).getTbitClassname().equals("Entity.TCuota")) {
                                TCuota cuota = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TCuota.class);
                                String[] fila = new String[11];
                                fila[1] = cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido();
                                fila[2] = lBitacora.get(i).getTbitIdentificador();
                                fila[3] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cuota.getTcuoFecha());
                                fila[4] = "" + cuota.getTcuoNuevoSaldo();
                                fila[5] = "" + cuota.getTcuoCuotasPagadas();
                                fila[6] = cuota.getTPago().getTipo();
                                fila[7] = cuota.getTCobrador().getTcobNombre();
                                fila[9] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                fila[10] = "" + cuota.getTcuoId();
                                fila[8] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();

                                vistaBitacora.modeloTabla1.addRow(fila);
                            }
                        }
                    }
                    lBitacora = mBitacora.consultarAllModulo("ABONO");
                    for (int i = 0; i < lBitacora.size(); i++) {
                        switch (lBitacora.get(i).getTbitClassname()) {
                            case "Entity.TCuota":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TCuota.class));
                                break;
                            case "Entity.TPrestamo":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPrestamo.class));
                                break;
                            case "Entity.TPago":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPago.class));
                                break;
                            case "Entity.TCobrador":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TCobrador.class));
                                break;
                            case "Entity.TMulta":
                                listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TMulta.class));
                                break;
                            default:
                                JOptionPane.showMessageDialog(DesktopPaneMain, "Se agrego una nueva clase al modulo de prestamo  -" + lBitacora.get(i).getTbitClassname() + ", informar a su programador ");
                                break;
                        }
                    }
                    break;
                case "MULTA":
                    listObject.clear();
                    if (!lBitacora.isEmpty()) {
                        for (int i = 0; i < lBitacora.size(); i++) {
                            TMulta multa = gson.fromJson(lBitacora.get(i).getTbitRegistro(), TMulta.class);
                            String[] fila = new String[12];
                            fila[0] = multa.getTmulId().toString();
                            fila[1] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                            fila[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                            fila[3] = lBitacora.get(i).getTbitIdentificador();
                            fila[4] = multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpCedula();
                            fila[5] = multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido();
                            fila[6] = "" + multa.getTmulValor();
                            fila[7] = "" + multa.getTPrestamo().getTpreValorPrestamo();
                            fila[8] = multa.getTmulDescripcion();
                            fila[9] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(multa.getTmulFecha());
                            fila[10] = multa.getTmulEstado();
                            fila[11] = "" + multa.getTmulId();

                            vistaBitacora.modeloTabla1.addRow(fila);
                        }
                    }
                    lBitacora = mBitacora.consultarAllModulo("MULTA");
                    for (int i = 0; i < lBitacora.size(); i++) {
                        if (lBitacora.get(i).getTbitClassname().equals("Entity.TMulta")) {
                            listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TMulta.class));
                        } else {
                            listObject.add(i, gson.fromJson(lBitacora.get(i).getTbitRegistro(), TPrestamo.class));
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

    public void verPersonas(int v) {
        List<TDatosBasicosPersona> personas = v == 1 ? new DatosBasicosPersona_Model().ConsultarPersonasConLogin() : new Multa_Model().personasConMulta();
        vistaBitacora.modeloTabla2.setNumRows(0);
        if (!personas.isEmpty()) {
            for (int i = 0; i < personas.size(); i++) {
                String[] fila = new String[4];
                fila[1] = personas.get(i).getTdbpCedula();
                fila[2] = personas.get(i).getTdbpNombre() + " " + personas.get(i).getTdbpApellido();
                fila[3] = personas.get(i).getTdbpId().toString();
                vistaBitacora.modeloTabla2.addRow(fila);
            }
            numerarTabla(vistaBitacora.modeloTabla2);
        }
    }

    public void bitacoraGeneralIndividual(MouseEvent evt, int flag) {
        if (evt.getClickCount() == 2) {
            int fila = flag == 1 ? Bitacora_UI.jTable1.rowAtPoint(evt.getPoint()) : flag == 3 ? Bitacora_Individual.jTable1.rowAtPoint(evt.getPoint()) : Bitacora_UI.jTable2.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                DefaultTableModel model = null;
                String cadena = "";
                if (flag == 1 || flag == 3) {
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
                                            filas[0] = prestamo.getTpreId().toString();
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
                            int idcliente = Integer.parseInt(Bitacora_UI.jTable1.getModel().getValueAt(fila, 6).toString());
                            cadena = "La primera fila resaltada de color verde es el estado actual del cliente";
                            model = new TableModel().bitacoraIndividualClientesCodeudores();
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
                        case "GASTOS":
                            int idgasto = Integer.parseInt(Bitacora_UI.jTable1.getModel().getValueAt(fila, 7).toString());
                            cadena = "La primera fila resaltada de color verde es el estado actual del gasto";
                            model = new TableModel().bitacoraGeneralGastos();
                            for (int i = 0; i < listObject.size(); i++) {
                                TGasto gasto = (TGasto) listObject.get(i);
                                if (gasto.getTgasId() == idgasto) {
                                    String[] filagasto = new String[8];
                                    filagasto[1] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(gasto.getTgasFecha());
                                    filagasto[2] = gasto.getTgasDesc();
                                    filagasto[3] = gasto.getTgasCosto().toString();
                                    filagasto[4] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                    filagasto[5] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                    filagasto[6] = lBitacora.get(i).getTbitIdentificador();
                                    filagasto[7] = gasto.getTgasId().toString();
                                    model.addRow(filagasto);
                                }
                            }
                            break;
                        case "ABONO":
                            int idcuota = Integer.parseInt(Bitacora_UI.jTable1.getModel().getValueAt(fila, 10).toString());
                            model = new TableModel().bitacoraGeneralAbonos();
                            TPrestamo prestamo = null;
                            boolean True = false;
                            for (int i = 0; i < listObject.size(); i++) {
                                if (lBitacora.get(i).getTbitClassname().equals("Entity.TCuota")) {
                                    TCuota cuota = (TCuota) listObject.get(i);
                                    if (cuota.getTcuoId() == idcuota) {
                                        if (!True) {
                                            prestamo = cuota.getTPrestamo();
                                            True = true;
                                        }
                                        String[] filasgasto = new String[11];
                                        filasgasto[0] = cuota.getTcuoId().toString();
                                        filasgasto[1] = cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + cuota.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido();
                                        filasgasto[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cuota.getTcuoFecha());
                                        filasgasto[3] = "" + cuota.getTcuoAbono();
                                        filasgasto[4] = "" + cuota.getTcuoNuevoSaldo();
                                        filasgasto[5] = "" + cuota.getTcuoCuotasPagadas();
                                        filasgasto[6] = cuota.getTPago().getTipo();
                                        filasgasto[7] = cuota.getTCobrador().getTcobNombre();
                                        filasgasto[8] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                        filasgasto[9] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                        filasgasto[10] = "" + cuota.getTcuoId();
                                        model.addRow(filasgasto);
                                    }
                                }
                            }
                            cadena = prestamo != null ? "<html>Informacion General del prestamo <br><table cellspacing=\"1\">"
                                    + "<tr><td>Cliente:</td><td>" + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido() + "</td>"
                                    + "<td>N° Cuotas:</td><td>" + prestamo.getTpreNumCuotas() + "</td></tr> "
                                    + "<tr><td>Valor Prestamo:</td><td>" + prestamo.getTpreValorPrestamo() + "</td><td>Intereses:</td><td>" + prestamo.getTpreIntereses() + "</td></tr>"
                                    + "<tr><td>Metodo pago:</td><td>" + prestamo.getTpreMetodPago() + "</td><td>Fecha de entrega:</td><td>" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(prestamo.getTpreFechaEntrega()) + "</td></tr>"
                                    + "<tr><td>Valor Total:</td><td>" + prestamo.getTpreValorTotal() + "</td><td>Valor Cuotas:</td><td>" + prestamo.getTpreValorCuota() + "</td></tr>"
                                    + "</table></html>" : "";
                            break;
                        case "MULTA":
                            int idmulta = Integer.parseInt(Bitacora_UI.jTable1.getModel().getValueAt(fila, 11).toString());
                            model = new TableModel().bitacoraGeneralMulta();
                            for (int i = 0; i < listObject.size(); i++) {
                                if (lBitacora.get(i).getTbitClassname().equals("Entity.TMulta")) {
                                    TMulta multa = (TMulta) listObject.get(i);
                                    if (multa.getTmulId() == idmulta) {
                                        for (int j = 0; j < listObject.size(); j++) {
                                            if (lBitacora.get(j).getTbitClassname().equals("Entity.TPrestamo")) {
                                                TPrestamo prestaMulta = (TPrestamo) listObject.get(j);
                                                if (Objects.equals(prestaMulta.getTpreId(), multa.getTPrestamo().getTpreId())) {
                                                    cadena = "<html>Informacion General del prestamo <br><table cellspacing=\"1\">"
                                                            + "<tr><td>Cliente:</td><td>" + prestaMulta.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + prestaMulta.getTPersona().getTDatosBasicosPersona().getTdbpApellido() + "</td>"
                                                            + "<td>N° Cuotas:</td><td>" + prestaMulta.getTpreNumCuotas() + "</td></tr> "
                                                            + "<tr><td>Valor Prestamo:</td><td>" + prestaMulta.getTpreValorPrestamo() + "</td><td>Intereses:</td><td>" + prestaMulta.getTpreIntereses() + "</td></tr>"
                                                            + "<tr><td>Metodo pago:</td><td>" + prestaMulta.getTpreMetodPago() + "</td><td>Fecha de entrega:</td><td>" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(prestaMulta.getTpreFechaEntrega()) + "</td></tr>"
                                                            + "<tr><td>Valor Total:</td><td>" + prestaMulta.getTpreValorTotal() + "</td><td>Valor Cuotas:</td><td>" + prestaMulta.getTpreValorCuota() + "</td></tr>"
                                                            + "</table></html>";
                                                }
                                            }
                                        }
                                        String[] filamulta = new String[12];
                                        filamulta[0] = multa.getTmulId().toString();
                                        filamulta[1] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                        filamulta[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                        filamulta[3] = lBitacora.get(i).getTbitIdentificador();
                                        filamulta[4] = multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpCedula();
                                        filamulta[5] = multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido();
                                        filamulta[6] = "" + multa.getTmulValor();
                                        filamulta[7] = "" + multa.getTPrestamo().getTpreValorPrestamo();
                                        filamulta[8] = multa.getTmulDescripcion();
                                        filamulta[9] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(multa.getTmulFecha());
                                        filamulta[10] = multa.getTmulEstado();
                                        filamulta[11] = "" + multa.getTmulId();
                                        model.addRow(filamulta);
                                    }
                                }
                            }
                            break;

                        default:
                            break;
                    }
                } else if (!Bitacora_UI.bitacora.equals("MULTA")) {
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
                } else {
                    String cedula = Bitacora_UI.jTable2.getModel().getValueAt(fila, 1).toString();
                    model = new TableModel().bitacoraHistorialMultas();
                    ArrayList<Integer> multasAñadidas = new ArrayList();
                    boolean datosbasicos = true;
                    for (int i = 0; i < lBitacora.size(); i++) {
                        if (lBitacora.get(i).getTbitClassname().equals("Entity.TPrestamo") && datosbasicos) {
                            TPrestamo prestaMulta = (TPrestamo) listObject.get(i);
                            if (prestaMulta.getTPersona().getTDatosBasicosPersona().getTdbpCedula().equals(cedula)) {
                                cadena = "<html>Informacion General del Cliente<br><table cellspacing=\"1\">"
                                        + "<tr><td>Cedula:</td><td>" + prestaMulta.getTPersona().getTDatosBasicosPersona().getTdbpCedula() + "</td>"
                                        + "<td>Nombre:</td><td>" + prestaMulta.getTPersona().getTDatosBasicosPersona().getTdbpNombre().toUpperCase() + " " + prestaMulta.getTPersona().getTDatosBasicosPersona().getTdbpApellido().toUpperCase() + "</td></tr> "
                                        + "<tr><td>Telefono:</td><td>" + prestaMulta.getTPersona().getTDatosBasicosPersona().getTdbpTel() + "</td>"
                                        + "<td>Direccion:</td><td>" + prestaMulta.getTPersona().getTperCasDir() + "</td></tr>"
                                        + "</table></html>";
                                datosbasicos = false;
                            }
                        }
                        if (lBitacora.get(i).getTbitClassname().equals("Entity.TMulta") && lBitacora.get(i).getTbitIdentificador().equals("AGREGO")) {
                            TMulta multa = (TMulta) listObject.get(i);
                            if (multa.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpCedula().equals(cedula)) {
                                boolean multas = true;
                                for (int j = 0; j < multasAñadidas.size(); j++) {
                                    if (Objects.equals(multa.getTmulId(), multasAñadidas.get(j))) {
                                        multas = false;
                                    }
                                }
                                if (multas) {
                                    String[] filamulta = new String[12];
                                    filamulta[0] = multa.getTmulId().toString();
                                    filamulta[1] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                                    filamulta[2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                                    filamulta[3] = "" + multa.getTmulValor();
                                    filamulta[4] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(multa.getTmulFecha());
                                    filamulta[5] = multa.getTmulEstado();
                                    filamulta[6] = "" + multa.getTPrestamo().getTpreValorPrestamo();
                                    filamulta[7] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(multa.getTPrestamo().getTpreFechaEntrega());
                                    filamulta[8] = "" + multa.getTPrestamo().getTpreNumCuotas();
                                    filamulta[9] = "" + multa.getTPrestamo().getTpreValorCuota();
                                    filamulta[10] = "" + multa.getTPrestamo().getTpreValorTotal();
                                    filamulta[11] = "" + multa.getTmulId();

                                    multasAñadidas.add(multa.getTmulId());
                                    model.addRow(filamulta);
                                }
                            }
                        }
                    }
                }
                if (model != null) {
                    if (Bitacora_UI.bitacora.equals("PRESTAMO") || Bitacora_UI.bitacora.equals("ABONO") || Bitacora_UI.bitacora.equals("MULTA")) {
                    } else {
                        numerarTabla(model);
                    }
                    JInternalFrame in = new Bitacora_Individual(model, cadena, 1, Bitacora_UI.bitController);
                    in.moveToFront();
                    checkInstance(in);
                }
            }
        }
    }

    public static void detalleBitacoraIndividual(int id, String persona) {
        String cadena = "";
        DefaultTableModel model = null;
        int flag = 0;
        switch (persona) {
            case "CODEUDOR":
                cadena = "La primera fila resaltada de color verde es el estado actual del codeudor";
                model = new TableModel().bitacoraIndividualClientesCodeudores();
                flag = 2;
                int idcodeudor = id;
                for (int i = 0; i < listObject.size(); i++) {
                    if (lBitacora.get(i).getTbitClassname().equals("Entity.TPersona")) {
                        TPersona cliente = (TPersona) listObject.get(i);
                        if (cliente.getTperId() == idcodeudor) {
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
            case "REFERENCIA":
                cadena = "La primera fila resaltada de color verde es el estado actual de esta referencia";
                model = new TableModel().bitacoraIndividualReferencia();
                flag = 3;
                int idreferencia = id;
                for (int i = 0; i < listObject.size(); i++) {
                    if (lBitacora.get(i).getTbitClassname().equals("Entity.TReferencia")) {
                        TReferencia referencia = (TReferencia) listObject.get(i);
                        if (referencia.getTrefId() == idreferencia) {
                            String[] filas = new String[14];
                            filas[1] = referencia.getTrefNombre() + " " + referencia.getTrefApellido();
                            filas[2] = referencia.getTrefTelefono();
                            filas[3] = lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpNombre() + " " + lBitacora.get(i).getTLogin().getTDatosBasicosPersona().getTdbpApellido();
                            filas[4] = lBitacora.get(i).getTbitIdentificador();
                            filas[5] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lBitacora.get(i).getTbitFecha());
                            model.addRow(filas);
                        }
                    }
                }
                break;
        }
        numerarTabla(model);
        JInternalFrame in = new Bitacora_Individual(model, cadena, flag, Bitacora_UI.bitController);
        in.moveToFront();
        DesktopPaneMain.add(in);
        calcWidthHeight(in);
        in.show();
    }
}
