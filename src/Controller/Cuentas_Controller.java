/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBanco;
import Entity.TCuentaPagar;
import Entity.TMovimientoBanco;
import Entity.TMovimientoCuenta;
import Model.Banco_Model;
import Model.Cuentas_Model;
import UI.Cuentas_UI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author afvc2203
 */
public class Cuentas_Controller extends Controllers {

    private Cuentas_UI cueUI;
    private Cuentas_Model cueModel;
    private List<TCuentaPagar> listCuentas;

    private DefaultTableModel dtm;
    private TableRowSorter<TableModel> tr;

    private SimpleDateFormat dformat;

    public Cuentas_Controller(Cuentas_UI cueUI) {
        this.cueUI = cueUI;
        cueModel = new Cuentas_Model();
    }

    public Cuentas_UI getCueUI() {
        return cueUI;
    }

    public void setCueUI(Cuentas_UI cueUI) {
        this.cueUI = cueUI;
    }

    public Cuentas_Model getCueModel() {
        return cueModel;
    }

    public void setCueModel(Cuentas_Model cueModel) {
        this.cueModel = cueModel;
    }

    public List<TCuentaPagar> getListCuentas() {
        return listCuentas;
    }

    public void setListCuentas(List<TCuentaPagar> listCuentas) {
        this.listCuentas = listCuentas;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public TableRowSorter<TableModel> getTr() {
        return tr;
    }

    public void setTr(TableRowSorter<TableModel> tr) {
        this.tr = tr;
    }

    public SimpleDateFormat getDformat() {
        return dformat;
    }

    public void setDformat(SimpleDateFormat dformat) {
        this.dformat = dformat;
    }

    
 

    //<editor-fold defaultstate="collapsed" desc="TABLE init">
    public void initTable(JTable jt) {

        //dformat = new SimpleDateFormat("yyyy/MM/dd");
        selectWhere();

        setDtm((DefaultTableModel) jt.getModel());

        getListCuentas().sort(Comparator.comparing(TCuentaPagar::getTcueNombre));

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }
        Object[] f = new Object[6];
        for (int i = 0; i < getListCuentas().size(); i++) {
            f[1] = getListCuentas().get(i).getTcueNombre();
            f[2] = getListCuentas().get(i).getTcueSaldo();
            f[3] = getListCuentas().get(i);
            getDtm().addRow(f);
        }
        numerarTabla(getDtm());
    }
//</editor-fold> 

    public void initTableMovimientoCuenta(JTable jt) {

        Set temp = ((TCuentaPagar) getCueUI().jtCuenta.getValueAt(getCueUI().jtCuenta.getSelectedRow(), 3)).getTMovimientoCuentas();
        List<TMovimientoCuenta> tmb = new ArrayList<>();

        tmb.addAll(temp);

        tmb.sort(Comparator.comparing(TMovimientoCuenta::getTmocFecha));

        setDtm((DefaultTableModel) jt.getModel());

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }

        Object[] f = new Object[6];
        for (int i = 0; i < tmb.size(); i++) {
            f[1] = tmb.get(i).getTmocFecha();
            f[2] = tmb.get(i).getTmocTipo();
            f[3] = tmb.get(i).getTmocSaldo();
            getDtm().addRow(f);

            getCueUI().jtTotal.setText("" + (Integer.parseInt(getCueUI().jtTotal.getText()) + tmb.get(i).getTmocSaldo()));
        }
        numerarTabla(getDtm());
    }

    public void prepareInsertCuenta() {
        if (validar("CUENTA")) {
            TCuentaPagar tb = new TCuentaPagar();
            tb.setTcueNombre(getCueUI().jtfNombreCuenta.getText());
            tb.setTcueSaldo(Long.parseLong(getCueUI().jtfSaldoBase.getText()));

            if (insertCuenta(tb) > 0) {
                initTable(getCueUI().jtCuenta);
                JOptionPane.showMessageDialog(null, "Cuenta por Pagar Registrada");

                getCueUI().jtfNombreCuenta.setText("");
                getCueUI().jtfSaldoBase.setText("");
            }
        }
    } 

    public void prepareInsertMovimientoCuenta() {

        if (validar("MOVIMIENTO")) {

            TCuentaPagar tb = new TCuentaPagar();
            tb.setTcueId(Integer.parseInt(getCueUI().jtfid.getText()));

            TMovimientoCuenta tm = new TMovimientoCuenta();
            tm.setTCuentaPagar(tb);
            tm.setTmocTipo("" + getCueUI().jcbTipoMovimiento.getSelectedItem());
            tm.setTmocSaldo((getCueUI().jcbTipoMovimiento.getSelectedIndex() == 0 ? Long.parseLong(getCueUI().jtfSaldo.getText()) : Long.parseLong("-" + getCueUI().jtfSaldo.getText())));
            tm.setTmocFecha(getCueUI().jtfFecha.getDate());
            
            //JOptionPane.showMessageDialog(null, (getCueUI().jcbTipoMovimiento.getSelectedIndex() == 0 ? Long.parseLong(getCueUI().jtfSaldo.getText()) : Long.parseLong("-" + getCueUI().jtfSaldo.getText())));

            if (insertMovimientoCuenta(tm) > 0) {
                initTable(getCueUI().jtCuenta);
                limpiaTabla(getCueUI().jtMovimientosCuenta);
            }
        }
    }

    void limpiaTabla(JTable jt) {
        try {
            DefaultTableModel temp = (DefaultTableModel) jt.getModel();
            int a = temp.getRowCount();
            for (int i = 0; i < a; i++) {
                temp.removeRow(0); //aquí estaba el error, antes pasaba la i como parametro.... soy un bacín  XD
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//<editor-fold defaultstate="collapsed" desc="SELECT all WHERE">
    public void selectWhere() {
        setListCuentas(getCueModel().SelectAllWhere());
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public int insertCuenta(TCuentaPagar obj) {
        return Integer.parseInt("" + getCueModel().insertar(obj, "CUENTAS POR PAGAR"));
    }
//</editor-fold>  

//<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public int insertMovimientoCuenta(TMovimientoCuenta obj) {
        System.out.println("Entro aqui");
        return Integer.parseInt("" + getCueModel().insertar(obj, "MOVIMIENTO CUENTAS"));
    }
//</editor-fold>  

//<editor-fold defaultstate="collapsed" desc="TABLE filter Cliente">
    public void filter(JTable jt, String textBuscar, int columna) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jt.getModel());

        tr.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscar, columna));

        jt.setRowSorter(tr);
    }
//</editor-fold>   

    public void jtMouseClicked() {

        getCueUI().jcbMovimientos.setEnabled(true);
        getCueUI().jtxSearchMovimientos.setEnabled(true);
        getCueUI().jtfid.setText("" + ((TCuentaPagar) getCueUI().jtCuenta.getValueAt(getCueUI().jtCuenta.getSelectedRow(), 3)).getTcueId());
        getCueUI().jtfCuenta.setText("" + getCueUI().jtCuenta.getValueAt(getCueUI().jtCuenta.getSelectedRow(), 1));
        getCueUI().jtSaldoBase.setText("" + getCueUI().jtCuenta.getValueAt(getCueUI().jtCuenta.getSelectedRow(), 2));

        getCueUI().jcbTipoMovimiento.setEnabled(true);
        getCueUI().jtfSaldo.setEnabled(true);
        getCueUI().jtfFecha.setEnabled(true);
        getCueUI().btnGuardarMovimiento.setEnabled(true);
    }

    //<editor-fold defaultstate="collapsed" desc="validar">
    public boolean validar(String v) {
        String mensaje = "";

        if (v == "CUENTA") {

            if (getCueUI().jtfNombreCuenta.getText().trim().equals("")) {
                mensaje += "[- Nombre de Cuenta vacío -] \n";
            }

            if (getCueUI().jtfSaldoBase.getText().trim().equals("")) {
                mensaje += "[- Saldo Base vacío -] \n";
            }
        } else {

            if (getCueUI().jtfSaldo.getText().trim().equals("")) {
                mensaje += "[- Saldo vacío -] \n";
            }

            if (("" + getCueUI().jtfFecha.getDate()).length() < 28) {
                mensaje += "[- Fecha vacía -] \n";
            }
        }

        //JOptionPane.showMessageDialog(null, (""+getCueUI().jtfFecha.getDate()).length());
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n \n" + mensaje, "Error!!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
//</editor-fold>  
}
