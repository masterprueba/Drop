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

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
import java.awt.Color;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

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
        Locale locale = new Locale("es","AR"); // elegimos Argentina
        
        Set temp = ((TBanco) banUI.jtBanco.getValueAt(banUI.jtBanco.getSelectedRow(), 4)).getTMovimientoBancos();
        List<TMovimientoBanco> tmb = new ArrayList<>();

        tmb.addAll(temp);

        setDtm((DefaultTableModel) jt.getModel());

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }
        
        Object[] f = new Object[6];
        for (int i = 0; i < tmb.size(); i++) {
            f[1] = tmb.get(i).getTmovTipo();
            f[2] = tmb.get(i).getTmovSaldo();
            f[3] = tmb.get(i).getTmovFecha();
            f[4] = tmb.get(i).getTmovConcepto();
            getDtm().addRow(f);

            banUI.jtTotal.setText("" + (Integer.parseInt(banUI.jtTotal.getText()) + tmb.get(i).getTmovSaldo()));
        }
        numerarTabla(getDtm());
    }

    public void prepareInsert() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(getBanUI().jtfFecha.getDate() + "");
            TBanco tb = new TBanco();
            //tb.setTbanDinero(Long.parseLong(getBanUI().jtfDinero.getText()));
            //tb.setTbanFecha(date);
            //tb.setTbanConcepto(getBanUI().jtaConcepto.getText());

            if (insert(tb) > 0) {
                initTable(getBanUI().jtMovimientos);

            }
        } catch (ParseException ex) {
            Logger.getLogger(Banco_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//<editor-fold defaultstate="collapsed" desc="SELECT all WHERE">
    public void selectWhere() {
        listBanco = banModel.SelectAllWhere();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public int insert(TBanco objPer) {
        return Integer.parseInt("" + banModel.insertar(objPer, "CLIENTE"));
    }
//</editor-fold>  

//<editor-fold defaultstate="collapsed" desc="TABLE filter Cliente">
    public void filter(JTable jt) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(getDtm());
        jt.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(getBanUI().jtxSearchMovimientos.getText().toLowerCase()));
    }
//</editor-fold>    

}
