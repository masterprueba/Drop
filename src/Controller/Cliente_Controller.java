/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCuota;
import Entity.TDatosBasicosPersona;
import Entity.TPersona;
import Entity.TPrestamo;
import Entity.TReferencia;
import UI.Cliente_UI;
import UI.InformeCliente;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Andres
 */
public class Cliente_Controller extends Persona_Controller {

    private Codeudor_Controller cod_Controller;
    TableRowSorter trs = null;

    public Cliente_Controller(Cliente_UI cli_UI) {
        super(cli_UI);
    }

    public Cliente_Controller(InformeCliente infCli) {
        super(infCli);
    }

//<editor-fold defaultstate="collapsed" desc="prepare INSERT">
    public void prepareInsert(TPersona codeudor) {
        if (validar()) {
            createObjectClienteTo();
            setDbpCliente(getDbp_Controller().insert(getDbpCliente()));

            if (getDbpCliente().getTdbpId() != null) {
                getpCliente().getTDatosBasicosPersona().setTdbpId(getDbpCliente().getTdbpId());
                getpCliente().setTperCodeudor(codeudor.getTDatosBasicosPersona().getTdbpCedula());

                if (insert(getpCliente())) {

                    if (getCli_UI().objectRefeCod.size() > 0) {
                        for (int i = 0; i < getCli_UI().objectRefeCli.size(); i++) {
                            getCli_UI().objectRefeCli.get(i).setTDatosBasicosPersona(getDbpCliente());

                            getRef__Controller().prepareInsert(getCli_UI().objectRefeCli.get(i));

                        }
                    }
                }
                JOptionPane.showMessageDialog(getCli_UI(), "Cliente registrado con exito", "Datos Guardados", JOptionPane.INFORMATION_MESSAGE);
                initTable(getCli_UI().jtbClientes);
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="prepare SELECT">
    public void prepareSelect(String cc) {
        setDbpCliente(new TDatosBasicosPersona());
        getDbpCliente().setTdbpCedula(cc);

        setpCliente(new TPersona());
        getpCliente().setTDatosBasicosPersona(getDbpCliente());
        getpCliente().setTperTipo("CLIENTE");

        if (selectOne(getpCliente())) {
            getCli_UI().btnEditar.setEnabled(true);
            getCli_UI().btnEditar.setEnabled(true);
            setDataJTextCliente();
            cod_Controller = new Codeudor_Controller(getCli_UI());
            cod_Controller.prepareSelect(getCli_UI().objectCliente.getTperCodeudor());
            getRef__Controller().prepareSelect(getpCliente().getTDatosBasicosPersona().getTdbpCedula(), "CLIENTE");
            //getRef__Controller().prepareSelect(getpCliente().getTDatosBasicosPersona().getTdbpCedula(), "CLIENTE");
        } else {
            int r = JOptionPane.showConfirmDialog(null, "Cliente no Registrado... ¿Desea registrar?", "Cliente NO registrado", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {
                enabledForEdit(true);
                enabledForEdit(true);
                colorJText(new java.awt.Color(255, 255, 255));
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="prepare UPDATE">
    public void prepareUpdate(TPersona codeudor) {
        if (validar()) {
            createObjectClienteTo();
            getDbpCliente().setTdbpId(getCli_UI().objectCliente.getTDatosBasicosPersona().getTdbpId());
            getpCliente().setTperId(getCli_UI().objectCliente.getTperId());
            getpCliente().setTperCodeudor(codeudor.getTDatosBasicosPersona().getTdbpCedula());

            if (getDbp_Controller().update(getDbpCliente())) {
                if (update(getpCliente())) {

                    if (getCli_UI().objectRefeCli.size() > 0) {

                        if (getRef__Controller().prepareSelect(getpCliente().getTDatosBasicosPersona().getTdbpCedula(), "")) {
                            List<TReferencia> temp = new ArrayList<>();
                            temp = getRef__Controller().getListRef();
                            for (int j = 0; j < temp.size(); j++) {
                                getRef__Controller().prepareDelete(temp.get(j));

                                //System.out.println(temp.get(j).getTrefNombre());
                            }
                        }

                        for (int i = 0; i < getCli_UI().objectRefeCli.size(); i++) {
                            getCli_UI().objectRefeCli.get(i).setTDatosBasicosPersona(getDbpCliente());

                            getRef__Controller().prepareInsert(getCli_UI().objectRefeCli.get(i));

                        }
                    }

                    JOptionPane.showMessageDialog(getCli_UI(), "Datos Actualizados con exito", "Datos Guardados", JOptionPane.INFORMATION_MESSAGE);
                    initTable(getCli_UI().jtbClientes);
                }
            }
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="enabledForEdit">
    public void enabledForEdit(boolean b) {
        getCli_UI().jtfNombreCliente1.setFocusable(b);
        getCli_UI().jtfApellidoCliente1.setFocusable(b);
        getCli_UI().jtfTelefonoCliente1.setFocusable(b);
        getCli_UI().jtfCasaDirCliente.setFocusable(b);
        getCli_UI().jtfCasaPropieCliente.setFocusable(b);
        getCli_UI().jcbTipoCasaCliente.setEnabled(b);
        getCli_UI().jtfRazonSocialEmpresaCliente.setFocusable(b);
        getCli_UI().jtfDireccionEmpresaCliente.setFocusable(b);
        getCli_UI().jtfTelefonoEmpresaCliente.setFocusable(b);

        getCli_UI().jtfCedulaCodeudor.setFocusable(b);
        getCli_UI().jtfNombreCodeudor.setFocusable(b);
        getCli_UI().jtfApellidoCodeudor.setFocusable(b);
        getCli_UI().jtfTelefonoCodeudor.setFocusable(b);
        getCli_UI().jtfCasaDirCodeudor.setFocusable(b);
        getCli_UI().jtfCasaPropieCodeudor.setFocusable(b);
        getCli_UI().jcbTipoCasaCodeudor.setEnabled(b);
        getCli_UI().jtfRazonSocialEmpresaCodeudor.setFocusable(b);
        getCli_UI().jtfDireccionEmpresaCodeudor.setFocusable(b);
        getCli_UI().jtfTelefonolEmpresaCodeudor.setFocusable(b);

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="init TABLE">
    public void initTable(JTable jt) {
        TPersona temp = new TPersona();
        temp.setTperTipo("CLIENTE");

        SelectAll(temp);

        DefaultTableModel dtm = new TableModel().VerUsuarios();        
        jt.setModel(dtm);

        Object[] f = new Object[4];
        for (int i = 0; i < getListPer().size(); i++) {
            f[1] = getListPer().get(i).getTDatosBasicosPersona().getTdbpCedula();
            f[2] = getListPer().get(i).getTDatosBasicosPersona().getTdbpNombre() + " " + getListPer().get(i).getTDatosBasicosPersona().getTdbpApellido();
            f[3] = getListPer().get(i).getTDatosBasicosPersona().getTdbpTel();
            dtm.addRow(f);
        }
        numerarTabla(dtm);
    }
//</editor-fold>  

    public void initTablePrestamo(JTable jt) {

        setDbpCliente(new TDatosBasicosPersona());
        getDbpCliente().setTdbpCedula(String.valueOf(getInfCli().jtbClientes.getValueAt(getInfCli().jtbClientes.getSelectedRow(), 1)));

        setpCliente(new TPersona());
        getpCliente().setTDatosBasicosPersona(getDbpCliente());
        getpCliente().setTperTipo("CLIENTE");

        selectOne(getpCliente());

        Set temp = getP().getTPrestamos();
        List<TPrestamo> tp = new ArrayList<>();
        tp.addAll(temp);

        DefaultTableModel dtm = new TableModel().historialPrestamo();
        jt.setModel(dtm);

        Object[] f = new Object[10];
        for (int i = 0; i < tp.size(); i++) {
            f[1] = tp.get(i).getTpreId();
            f[2] = tp.get(i).getTpreValorPrestamo();
            f[3] = tp.get(i).getTpreNumCuotas();
            f[4] = tp.get(i).getTpreIntereses();
            f[5] = tp.get(i).getTpreMetodPago();
            f[6] = tp.get(i).getTpreFechaEntrega();
            f[7] = tp.get(i).getTpreValorTotal();
            f[8] = tp.get(i).getTpreValorCuota();
            f[9] = tp.get(i);
            dtm.addRow(f);

        }
        numerarTabla(dtm);

        int[] position = {1, 9};
        setVisibleColumnTable(jt, position);
    }

    public void initTableCuotas(JTable jtbCuota, JTable jtbPrestamo) {
        Set temp = ((TPrestamo) jtbPrestamo.getValueAt(jtbPrestamo.getSelectedRow(), 9)).getTCuotas();
        List<TCuota> tc = new ArrayList<>();

        tc.addAll(temp);

        DefaultTableModel dtm = new TableModel().historialCuota();
        jtbCuota.setModel(dtm);

        Object[] f = new Object[9];
        for (int i = 0; i < tc.size(); i++) {
            f[1] = tc.get(i).getTcuoId();
            f[2] = tc.get(i).getTcuoFecha();
            f[3] = tc.get(i).getTcuoAbono();
            f[4] = tc.get(i).getTcuoNuevoSaldo();
            f[5] = tc.get(i).getTcuoCuotasPagadas();
            f[6] = tc.get(i).getTPago().getTipo();
            f[7] = tc.get(i).getTCobrador().getTcobNombre();
            f[8] = tc.get(i);
            dtm.addRow(f);

        }
        numerarTabla(dtm);
        int[] position = {1, 8};
        setVisibleColumnTable(jtbCuota, position);
    }

//<editor-fold defaultstate="collapsed" desc="Clean">
    public void clean() {
        getCli_UI().objectCliente = new TPersona();
        getCli_UI().jtfCedulaCliente.setText("");
        getCli_UI().jtfNombreCliente1.setText("");
        getCli_UI().jtfApellidoCliente1.setText("");
        getCli_UI().jtfTelefonoCliente1.setText("");
        getCli_UI().jtfCasaDirCliente.setText("");
        getCli_UI().jtfCasaPropieCliente.setText("");
        getCli_UI().jcbTipoCasaCliente.setSelectedIndex(0);
        getCli_UI().jtfRazonSocialEmpresaCliente.setText("");
        getCli_UI().jtfDireccionEmpresaCliente.setText("");
        getCli_UI().jtfTelefonoEmpresaCliente.setText("");
        getCli_UI().objectRefeCli = new ArrayList<>();

        getCli_UI().objectCodeudor = new TPersona();
        getCli_UI().jtfCedulaCodeudor.setText("");
        getCli_UI().jtfNombreCodeudor.setText("");
        getCli_UI().jtfApellidoCodeudor.setText("");
        getCli_UI().jtfTelefonoCodeudor.setText("");
        getCli_UI().jtfCasaDirCodeudor.setText("");
        getCli_UI().jtfCasaPropieCodeudor.setText("");
        getCli_UI().jcbTipoCasaCodeudor.setSelectedIndex(0);
        getCli_UI().jtfRazonSocialEmpresaCodeudor.setText("");
        getCli_UI().jtfDireccionEmpresaCodeudor.setText("");
        getCli_UI().jtfTelefonolEmpresaCodeudor.setText("");
        getCli_UI().objectRefeCod = new ArrayList<>();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="colorJText">
    public void colorJText(java.awt.Color c) {
        getCli_UI().jtfNombreCliente1.setBackground(c);
        getCli_UI().jtfApellidoCliente1.setBackground(c);
        getCli_UI().jtfTelefonoCliente1.setBackground(c);
        getCli_UI().jtfCasaDirCliente.setBackground(c);
        getCli_UI().jtfCasaPropieCliente.setBackground(c);
        getCli_UI().jtfRazonSocialEmpresaCliente.setBackground(c);
        getCli_UI().jtfDireccionEmpresaCliente.setBackground(c);
        getCli_UI().jtfTelefonoEmpresaCliente.setBackground(c);

        getCli_UI().jtfCedulaCodeudor.setBackground(c);
        getCli_UI().jtfNombreCodeudor.setBackground(c);
        getCli_UI().jtfApellidoCodeudor.setBackground(c);
        getCli_UI().jtfTelefonoCodeudor.setBackground(c);
        getCli_UI().jtfCasaDirCodeudor.setBackground(c);
        getCli_UI().jtfCasaPropieCodeudor.setBackground(c);
        getCli_UI().jtfRazonSocialEmpresaCodeudor.setBackground(c);
        getCli_UI().jtfDireccionEmpresaCodeudor.setBackground(c);
        getCli_UI().jtfTelefonolEmpresaCodeudor.setBackground(c);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="validar">
    public boolean validar() {
        String mensaje = "";

        if (getCli_UI().jtfCedulaCliente.getText().trim().equals("")) {
            mensaje += "-Identificación de Cliente esta vacío \n";
        }

        if (getCli_UI().jtfNombreCliente1.getText().trim().equals("")) {
            mensaje += "-Nombre de Cliente esta vacío \n";
        }
        if (getCli_UI().jtfApellidoCliente1.getText().trim().equals("")) {
            mensaje += "-Apellido de Cliente esta vacío \n";
        }
        if (getCli_UI().jtfCasaDirCliente.getText().trim().equals("")) {
            mensaje += "-Direccion de Cliente esta vacío \n";
        }

        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n \n" + mensaje, "Error!!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
//</editor-fold>
    
    public void filter(JTextField jtf, JTable jtb){
        
        
        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke){
                trs.setRowFilter(RowFilter.regexFilter("(?i)"+jtf.getText(), 1));
            }
        });
        
        trs = new TableRowSorter(jtb.getModel());
        jtb.setRowSorter(trs);
    }

}
