/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBanco;
import Entity.TMovimientoBanco;
import Model.Banco_Model;
import UI.Banco_UI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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
public class Banco_Controller extends Controllers {

    private Banco_UI banUI;
    private Banco_Model banModel;
    private List<TBanco> listBanco;

    private DefaultTableModel dtm;
    private TableRowSorter<TableModel> tr;

    private SimpleDateFormat dformat;

    public Banco_Controller(Banco_UI banUI) {
        this.banUI = banUI;
        banModel = new Banco_Model();
    }

    public Banco_UI getBanUI() {
        return banUI;
    }

    public void setBanUI(Banco_UI banUI) {
        this.banUI = banUI;
    }

    public Banco_Model getBanModel() {
        return banModel;
    }

    public void setBanModel(Banco_Model banModel) {
        this.banModel = banModel;
    }

    public List<TBanco> getListBanco() {
        return listBanco;
    }

    public void setListBanco(List<TBanco> listBanco) {
        this.listBanco = listBanco;
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

    //<editor-fold defaultstate="collapsed" desc="TABLE init Cliente">
    public void initTable(JTable jt) {

        //dformat = new SimpleDateFormat("yyyy/MM/dd");
        selectWhere();

        setDtm((DefaultTableModel) jt.getModel());

        getListBanco().sort(Comparator.comparing(TBanco::getTbanNombre));

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }
        Object[] f = new Object[6];
        for (int i = 0; i < getListBanco().size(); i++) {
            f[1] = getListBanco().get(i).getTbanCuenta();
            f[2] = getListBanco().get(i).getTbanNombre();
            f[3] = getListBanco().get(i).getTbanSaldo();
            f[4] = getListBanco().get(i);
            getDtm().addRow(f);
        }
        numerarTabla(getDtm());
    }
//</editor-fold> 

    public void initTableMovimiento(JTable jt) {
        Locale locale = new Locale("es", "AR"); // elegimos Argentina

        Set temp = ((TBanco) banUI.jtBanco.getValueAt(banUI.jtBanco.getSelectedRow(), 4)).getTMovimientoBancos();
        List<TMovimientoBanco> tmb = new ArrayList<>();

        tmb.addAll(temp);

        tmb.sort(Comparator.comparing(TMovimientoBanco::getTmovFecha));

        setDtm((DefaultTableModel) jt.getModel());

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }

        Object[] f = new Object[6];
        for (int i = 0; i < tmb.size(); i++) {
            f[1] = tmb.get(i).getTmovFecha();
            f[2] = tmb.get(i).getTmovTipo();
            f[3] = tmb.get(i).getTmovSaldo();
            f[4] = tmb.get(i).getTmovConcepto();
            getDtm().addRow(f);

            banUI.jtTotal.setText("" + (Integer.parseInt(banUI.jtTotal.getText()) + tmb.get(i).getTmovSaldo()));
        }
        numerarTabla(getDtm());
    }

    public void prepareInsertBanco() {
        if (validar("BANCO")) {
            TBanco tb = new TBanco();
            tb.setTbanCuenta(getBanUI().jtfNumeroCuenta.getText());
            tb.setTbanNombre(getBanUI().jtfNombreBanco.getText());
            tb.setTbanSaldo(Long.parseLong(getBanUI().jtfSaldoBase.getText()));

            int r = JOptionPane.showConfirmDialog(null, "Compruebe los siguientes datos…\n \n Número de Cuenta : " + getBanUI().jtfNumeroCuenta.getText() + "\n Nombre del Banco : " + getBanUI().jtfNombreBanco.getText() + "\n Saldo Base : "+ Long.parseLong(getBanUI().jtfSaldoBase.getText()) + "\n \n Presione “Si” en caso que sea correcto.", "Comprobar datos", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                if (insertBanco(tb) > 0) {
                    initTable(getBanUI().jtBanco);
                    JOptionPane.showMessageDialog(null, "Banco Registrado");

                    getBanUI().jtfNumeroCuenta.setText("");
                    getBanUI().jtfNombreBanco.setText("");
                    getBanUI().jtfSaldoBase.setText("");
                }
            }
        }
    }

    public void prepareInsertMovimientoBanco() {

        if (validar("MOVIMIENTO")) {

            TBanco tb = new TBanco();
            tb.setTbanCuenta(getBanUI().jtfCuenta.getText());

            TMovimientoBanco tm = new TMovimientoBanco();
            tm.setTBanco(tb);
            tm.setTmovTipo("" + getBanUI().jcbTipoMovimiento.getSelectedItem());
            tm.setTmovSaldo((getBanUI().jcbTipoMovimiento.getSelectedIndex() == 0 ? Long.parseLong(getBanUI().jtfSaldo.getText()) : Long.parseLong("-" + getBanUI().jtfSaldo.getText())));
            tm.setTmovFecha(getBanUI().jtfFecha.getDate());
            tm.setTmovConcepto(getBanUI().jtaConcepto.getText());

            //JOptionPane.showMessageDialog(null, (getBanUI().jcbTipoMovimiento.getSelectedIndex() == 0 ? Long.parseLong(getBanUI().jtfSaldo.getText()) : Long.parseLong("-" + getBanUI().jtfSaldo.getText())));
            int r = JOptionPane.showConfirmDialog(null, "Compruebe los siguientes datos…\n \n Tipo : " + getBanUI().jcbTipoMovimiento.getSelectedItem() + "\n Saldo : " + (getBanUI().jcbTipoMovimiento.getSelectedIndex() == 0 ? Long.parseLong(getBanUI().jtfSaldo.getText()) : Long.parseLong("-" + getBanUI().jtfSaldo.getText())) + "\n \n Presione “Si” en caso que sea correcto.", "Comprobar datos", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {
                if (insertMovimientoBanco(tm) > 0) {
                    initTable(getBanUI().jtBanco);
                    limpiaTabla(getBanUI().jtMovimientos);
                    getBanUI().jtfSaldo.setText("");
                    getBanUI().jtaConcepto.setText("");
                    getBanUI().btnGuardarMovimiento.setText("");
                }
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
        listBanco = banModel.SelectAllWhere();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public int insertBanco(TBanco obj) {
        return Integer.parseInt("" + banModel.insertar(obj, "BANCO"));
    }
//</editor-fold>  

//<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public int insertMovimientoBanco(TMovimientoBanco obj) {
        System.out.println("Entro aqui");
        return Integer.parseInt("" + banModel.insertar(obj, "MOVIMIENTO BANCO"));
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

        getBanUI().jcbMovimientos.setEnabled(true);
        getBanUI().jtxSearchMovimientos.setEnabled(true);
        getBanUI().jtfBanco.setText("" + getBanUI().jtBanco.getValueAt(getBanUI().jtBanco.getSelectedRow(), 2));
        getBanUI().jtfCuenta.setText("" + getBanUI().jtBanco.getValueAt(getBanUI().jtBanco.getSelectedRow(), 1));
        getBanUI().jtSaldoBase.setText("" + getBanUI().jtBanco.getValueAt(getBanUI().jtBanco.getSelectedRow(), 3));

        getBanUI().jcbTipoMovimiento.setEnabled(true);
        getBanUI().jtfSaldo.setEnabled(true);
        getBanUI().jtfFecha.setEnabled(true);
        getBanUI().jtaConcepto.setEnabled(true);
        getBanUI().btnGuardarMovimiento.setEnabled(true);
    }

    //<editor-fold defaultstate="collapsed" desc="validar">
    public boolean validar(String v) {
        String mensaje = "";

        if (v == "BANCO") {
            if (getBanUI().jtfNumeroCuenta.getText().trim().equals("")) {
                mensaje += "[- Numero de Cuenta vacío -] \n";
            }

            if (getBanUI().jtfNombreBanco.getText().trim().equals("")) {
                mensaje += "[- Nombre de Banco vacío -] \n";
            }

            if (getBanUI().jtfSaldoBase.getText().trim().equals("")) {
                mensaje += "[- Saldo Base vacío -] \n";
            }
        } else {

            if (getBanUI().jtfSaldo.getText().trim().equals("")) {
                mensaje += "[- Saldo vacío -] \n";
            }

            if (("" + getBanUI().jtfFecha.getDate()).length() < 28) {
                mensaje += "[- Fecha vacía -] \n";
            }
        }

        //JOptionPane.showMessageDialog(null, (""+getBanUI().jtfFecha.getDate()).length());
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n \n" + mensaje, "Error!!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
//</editor-fold>  
}
