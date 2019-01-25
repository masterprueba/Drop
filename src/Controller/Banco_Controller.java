/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBanco;
import Entity.TMovimientoBanco;
import Model.Banco_Model;
import UI.Banco_EditarMovimientoCredito;
import UI.Banco_EditarMovimientoDebito;
import UI.Banco_UI;
import UI.RegistrarBanco_UI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author afvc2203
 */
public class Banco_Controller extends Controllers {

    private Banco_UI banUI;
    private RegistrarBanco_UI reBUI;
    private Banco_Model banModel;
    private Banco_EditarMovimientoDebito editMovUI;
    private Banco_EditarMovimientoCredito editMovCre;
    private List<TBanco> listBanco;

    private DefaultTableModel dtm;
    private TableRowSorter<TableModel> tr;

    private SimpleDateFormat dformat;

    public Banco_Controller() {
        banModel = new Banco_Model();
    }

    public Banco_Controller(Banco_EditarMovimientoDebito editMovUI) {
        this.editMovUI = editMovUI;
        banModel = new Banco_Model();
    }

    public Banco_Controller(Banco_EditarMovimientoCredito editMovCre) {
        this.editMovCre = editMovCre;
        banModel = new Banco_Model();
    }

    public Banco_Controller(RegistrarBanco_UI banUI) {
        this.reBUI = banUI;
        banModel = new Banco_Model();
    }

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

    public RegistrarBanco_UI getReBUI() {
        return reBUI;
    }

    public void setReBUI(RegistrarBanco_UI reBUI) {
        this.reBUI = reBUI;
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
    public void initTable(JTable jt, char tipoBanco) {

        //dformat = new SimpleDateFormat("yyyy/MM/dd");
        selectWhere(tipoBanco);

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

        Set temp = ((TBanco) banUI.jtBancoD.getValueAt(banUI.jtBancoD.getSelectedRow(), 4)).getTMovimientoBancos();
        List<TMovimientoBanco> tmb = new ArrayList<>();

        tmb.addAll(temp);

        tmb.sort(Comparator.comparing(TMovimientoBanco::getTmovFecha));

        setDtm((DefaultTableModel) jt.getModel());

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }

        Double total = 0.0;

        Object[] f = new Object[7];
        for (TMovimientoBanco tmb1 : tmb) {
            switch (tmb1.getTmovTipo()) {
                case "Deposito":
                    total = total + tmb1.getTmovSaldo();
                    break;
                default:
                    total = total - tmb1.getTmovSaldo();
                    break;
            }
            f[1] = tmb1.getTmovId();
            f[2] = tmb1.getTmovFecha();
            f[3] = tmb1.getTmovTipo();
            f[4] = tmb1.getTmovSaldo();
            f[5] = tmb1.getTmovConcepto();
            getDtm().addRow(f);
        }

        banUI.jtTotalD.setText("" + total);
        numerarTabla(getDtm());
    }

    @SuppressWarnings("empty-statement")
    public void initTableMovimientoC(JTable jt) {

        Set temp = ((TBanco) banUI.jtBancoC.getValueAt(banUI.jtBancoC.getSelectedRow(), 4)).getTMovimientoBancos();
        List<TMovimientoBanco> tmb = new ArrayList<>();

        Double total = 0.0;
        Double totalPagar = 0.0;
        Double cupoUtilizado = 0.0;
        Double saldoDisponible = Double.parseDouble("" + ((TBanco) banUI.jtBancoC.getValueAt(banUI.jtBancoC.getSelectedRow(), 4)).getTbanSaldo());

        tmb.addAll(temp);

        tmb.sort(Comparator.comparing(TMovimientoBanco::getTmovFecha));

        setDtm((DefaultTableModel) jt.getModel());

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }

        Object[] f = new Object[8];
        for (TMovimientoBanco tmb1 : tmb) {
            switch (tmb1.getTmovTipo()) {
                case "Pago Deuda":
                    cupoUtilizado = cupoUtilizado - tmb1.getTmovSaldo();
                    saldoDisponible = saldoDisponible + tmb1.getTmovSaldo();
                    totalPagar = totalPagar - ((tmb1.getTmovPorcentaje() > 0) ? ((tmb1.getTmovSaldo() * tmb1.getTmovPorcentaje()) / 100) + tmb1.getTmovSaldo() : tmb1.getTmovSaldo());
                    break;
                default:
                    cupoUtilizado = cupoUtilizado + tmb1.getTmovSaldo();
                    saldoDisponible = saldoDisponible - tmb1.getTmovSaldo();
                    totalPagar = totalPagar + ((tmb1.getTmovPorcentaje() > 0) ? ((tmb1.getTmovSaldo() * tmb1.getTmovPorcentaje()) / 100) + tmb1.getTmovSaldo() : tmb1.getTmovSaldo());
                    break;
            }
            f[1] = tmb1.getTmovId();
            f[2] = tmb1.getTmovFecha();
            f[3] = tmb1.getTmovTipo();
            f[4] = tmb1.getTmovSaldo();
            f[5] = (tmb1.getTmovPorcentaje() > 0) ? tmb1.getTmovPorcentaje() : "-";
            f[6] = ((tmb1.getTmovPorcentaje() > 0) ? ((tmb1.getTmovSaldo() * tmb1.getTmovPorcentaje()) / 100) + tmb1.getTmovSaldo() : tmb1.getTmovSaldo());
            f[7] = tmb1.getTmovConcepto();
            getDtm().addRow(f);
        }

        banUI.jtCupoUtilizadoC.setText("" + cupoUtilizado.intValue());
        banUI.jtSaldoDisponibleC.setText("" + saldoDisponible.intValue());
        banUI.jtTotalC.setText("" + totalPagar.intValue());

        numerarTabla(getDtm());
    }

    public void prepareInsertBanco(char tipoBanco) {
        if (validar("BANCO", tipoBanco)) {
            TBanco tb = new TBanco();
            tb.setTbanCuenta(getReBUI().jtfNumeroCuenta.getText());
            tb.setTbanNombre(getReBUI().jtfNombreBanco.getText());
            tb.setTbanTipo(tipoBanco);
            tb.setTbanSaldo(Long.parseLong(getReBUI().jtfSaldoBase.getText()));

            int r = JOptionPane.showConfirmDialog(null, "Compruebe los siguientes datos…\n \n Número de Cuenta : " + getReBUI().jtfNumeroCuenta.getText() + "\n Nombre del Banco : " + getReBUI().jtfNombreBanco.getText() + "\n Saldo Base : " + Long.parseLong(getReBUI().jtfSaldoBase.getText()) + "\n \n Presione “Si” en caso que sea correcto.", "Comprobar datos", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                if (insertBanco(tb) > 0) {

                    JOptionPane.showMessageDialog(null, "Banco Registrado");

                    getReBUI().jtfNumeroCuenta.setText("");
                    getReBUI().jtfNombreBanco.setText("");
                    getReBUI().jtfSaldoBase.setText("");
                }
            }
        }
    }

    public void prepareInsertMovimientoBanco(char tipoBanco) {

        if (validar("MOVIMIENTO", tipoBanco)) {
            TBanco tb = new TBanco();
            TMovimientoBanco tm = new TMovimientoBanco();

            if (tipoBanco == 'C') {
                tb.setTbanCuenta(getBanUI().jtfCuentaC.getText());
                tm.setTBanco(tb);
                tm.setTmovTipo("" + getBanUI().jcbTipoMovimientoC.getSelectedItem());
                tm.setTmovSaldo(Long.parseLong(getBanUI().jtfSaldoC.getText()));
                tm.setTmovPorcentaje(Double.parseDouble(getBanUI().jtfPorcentajeC.getText()));
                tm.setTmovFecha(getBanUI().jtfFechaC.getDate());
                tm.setTmovConcepto(getBanUI().jtaConceptoC.getText());

            } else {
                tb.setTbanCuenta(getBanUI().jtfCuentaD.getText());
                tm.setTBanco(tb);
                tm.setTmovTipo("" + getBanUI().jcbTipoMovimientoD.getSelectedItem());
                tm.setTmovSaldo(Long.parseLong(getBanUI().jtfSaldoD.getText()));
                tm.setTmovFecha(getBanUI().jtfFechaD.getDate());
                tm.setTmovConcepto(getBanUI().jtaConceptoD.getText());
            }

            //JOptionPane.showMessageDialog(null, (getBanUI().jcbTipoMovimiento.getSelectedIndex() == 0 ? Long.parseLong(getBanUI().jtfSaldo.getText()) : Long.parseLong("-" + getBanUI().jtfSaldo.getText())));
            int r;
            if (tipoBanco == 'C') {
//                r = JOptionPane.showConfirmDialog(null, "Compruebe los siguientes datos…\n \n Tipo : " + getBanUI().jcbTipoMovimientoC.getSelectedItem() + "\n Saldo : " + Long.parseLong(getBanUI().jtfSaldoC.getText()) + "\n Interés :" + getBanUI().jtfPorcentajeC.getText() + "\n \n Presione “Si” en caso que sea correcto.", "Comprobar datos", JOptionPane.YES_NO_OPTION);
                r = JOptionPane.showConfirmDialog(null, "Compruebe los siguientes datos…\n \n Tipo : " + getBanUI().jcbTipoMovimientoC.getSelectedItem() + "\n Saldo : " + ((getBanUI().jcbTipoMovimientoC.getSelectedIndex() == 1 || getBanUI().jcbTipoMovimientoC.getSelectedIndex() == 2) ? Long.parseLong("-" + getBanUI().jtfSaldoC.getText()) : Long.parseLong(getBanUI().jtfSaldoC.getText())) + "\n Interés :" + getBanUI().jtfPorcentajeC.getText() + "\n \n Presione “Si” en caso que sea correcto.", "Comprobar datos", JOptionPane.YES_NO_OPTION);
            } else {
                //System.out.println("Index" + getBanUI().jcbTipoMovimientoD.getSelectedIndex());
                r = JOptionPane.showConfirmDialog(null, "Compruebe los siguientes datos…\n \n Tipo : " + getBanUI().jcbTipoMovimientoD.getSelectedItem() + "\n Saldo : " + ((getBanUI().jcbTipoMovimientoD.getSelectedIndex() == 1 || getBanUI().jcbTipoMovimientoD.getSelectedIndex() == 2) ? Long.parseLong("-" + getBanUI().jtfSaldoD.getText()) : Long.parseLong(getBanUI().jtfSaldoD.getText())) + "\n \n Presione “Si” en caso que sea correcto.", "Comprobar datos", JOptionPane.YES_NO_OPTION);
            }

            if (r == JOptionPane.YES_OPTION) {
                if (insertMovimientoBanco(tm) > 0) {

                    if (tipoBanco == 'C') {
                        initTable(getBanUI().jtBancoC, tipoBanco);
                        getBanUI().jtfSaldoC.setText("");
                        getBanUI().jtaConceptoC.setText("");
                        getBanUI().jtfPorcentajeC.setText("0");
                        getBanUI().btnGuardarMovimientoC.setText("");
                        limpiaTabla(getBanUI().jtMovimientosC);
                    } else {
                        initTable(getBanUI().jtBancoD, tipoBanco);
                        limpiaTabla(getBanUI().jtMovimientosD);

                        getBanUI().jtfSaldoD.setText("");
                        getBanUI().jtaConceptoD.setText("");
                        getBanUI().btnGuardarMovimientoD.setText("");

                    }

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
    public void selectWhere(char tipoBanco) {
        listBanco = banModel.SelectAllWhere(tipoBanco);
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
        if (Cierre_Controller.consutarCierre(obj.getTmovFecha())) {
            return Integer.parseInt("" + banModel.insertar(obj, "MOVIMIENTO BANCO"));
        } else {
            JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            return 0;
        }

    }
//</editor-fold>  

//<editor-fold defaultstate="collapsed" desc="TABLE filter Cliente">
    public void filter(JTable jt, String textBuscar, int columna) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jt.getModel());

        tr.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscar, columna));

        jt.setRowSorter(tr);
    }
//</editor-fold>   

    public void jtMouseClicked(char tipoBanco) {

        if (tipoBanco == 'C') {
            getBanUI().jcbMovimientos1.setEnabled(true);
            getBanUI().jtxSearchMovimientosC.setEnabled(true);
            getBanUI().jtfBanco1.setText("" + getBanUI().jtBancoC.getValueAt(getBanUI().jtBancoC.getSelectedRow(), 2));
            getBanUI().jtfCuentaC.setText("" + getBanUI().jtBancoC.getValueAt(getBanUI().jtBancoC.getSelectedRow(), 1));
            getBanUI().jtSaldoBaseC.setText("" + getBanUI().jtBancoC.getValueAt(getBanUI().jtBancoC.getSelectedRow(), 3));

            getBanUI().jcbTipoMovimientoC.setEnabled(true);
            getBanUI().jtfSaldoC.setEnabled(true);
            getBanUI().jtfFechaC.setEnabled(true);
            getBanUI().jtaConceptoC.setEnabled(true);
            getBanUI().jtfPorcentajeC.setEnabled(true);
            getBanUI().btnGuardarMovimientoC.setEnabled(true);
        } else {
            getBanUI().jcbMovimientosD.setEnabled(true);
            getBanUI().jtxSearchMovimientosD.setEnabled(true);
            getBanUI().jtfBancoD.setText("" + getBanUI().jtBancoD.getValueAt(getBanUI().jtBancoD.getSelectedRow(), 2));
            getBanUI().jtfCuentaD.setText("" + getBanUI().jtBancoD.getValueAt(getBanUI().jtBancoD.getSelectedRow(), 1));
            getBanUI().jtSaldoBaseD.setText("" + getBanUI().jtBancoD.getValueAt(getBanUI().jtBancoD.getSelectedRow(), 3));

            getBanUI().jcbTipoMovimientoD.setEnabled(true);
            getBanUI().jtfSaldoD.setEnabled(true);
            getBanUI().jtfFechaD.setEnabled(true);
            getBanUI().jtaConceptoD.setEnabled(true);
            getBanUI().btnGuardarMovimientoD.setEnabled(true);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="validar">
    public boolean validar(String v, char tipoBanco) {
        String mensaje = "";

        if ("BANCO".equals(v)) {
            if (getReBUI().jtfNumeroCuenta.getText().trim().equals("")) {
                mensaje += "[- Numero de Cuenta vacío -] \n";
            }

            if (getReBUI().jtfNombreBanco.getText().trim().equals("")) {
                mensaje += "[- Nombre de Banco vacío -] \n";
            }

            if (getReBUI().jtfSaldoBase.getText().trim().equals("")) {
                mensaje += "[- Saldo Base vacío -] \n";
            }
        } else {

            if (tipoBanco == 'C') {
                if (getBanUI().jtfPorcentajeC.getText().trim().equals("")) {
                    mensaje += "[- Interés vacío -] \n";
                }

                if (getBanUI().jtfSaldoC.getText().trim().equals("")) {
                    mensaje += "[- Saldo vacío -] \n";
                }

                if (("" + getBanUI().jtfFechaC.getDate()).length() < 28) {
                    mensaje += "[- Fecha vacía -] \n";
                }
            } else {
                if (getBanUI().jtfSaldoD.getText().trim().equals("")) {
                    mensaje += "[- Saldo vacío -] \n";
                }

                if (("" + getBanUI().jtfFechaD.getDate()).length() < 28) {
                    mensaje += "[- Fecha vacía -] \n";
                }
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

    public void cargarMovimiento(char ind) {
        if (ind == 'D') {
            TMovimientoBanco movimiento = (TMovimientoBanco) banModel.consultar(TMovimientoBanco.class, editMovUI.idMovimiento);
            switch (movimiento.getTmovTipo()) {
                case "Deposito":
                    editMovUI.jcbTipoMovimientoD.setSelectedIndex(0);
                    break;
                case "Retiro":
                    editMovUI.jcbTipoMovimientoD.setSelectedIndex(1);
                    break;
                case "Compra":
                    editMovUI.jcbTipoMovimientoD.setSelectedIndex(2);
                    break;
                default:
                    editMovUI.jcbTipoMovimientoD.setSelectedIndex(0);
            }
            editMovUI.jtfSaldoD.setText("" + movimiento.getTmovSaldo());
            editMovUI.jtfFechaD.setDate(movimiento.getTmovFecha());
            editMovUI.jtaConceptoD.setText(movimiento.getTmovConcepto());
        } else if (ind == 'C') {
            TMovimientoBanco movimiento = (TMovimientoBanco) banModel.consultar(TMovimientoBanco.class, editMovCre.idMovimiento);
            switch (movimiento.getTmovTipo()) {
                case "Pago Deuda":
                    editMovCre.jcbTipoMovimientoC.setSelectedIndex(0);
                    break;
                case "Adelanto":
                    editMovCre.jcbTipoMovimientoC.setSelectedIndex(1);
                    break;
                case "Compra":
                    editMovCre.jcbTipoMovimientoC.setSelectedIndex(2);
                    break;
                default:
                    editMovCre.jcbTipoMovimientoC.setSelectedIndex(0);
            }
            editMovCre.jtfSaldoC.setText("" + movimiento.getTmovSaldo());
            editMovCre.jtfPorcentajeC.setText("" + movimiento.getTmovPorcentaje());
            editMovCre.jtfFechaC.setDate(movimiento.getTmovFecha());
            editMovCre.jtaConceptoC.setText(movimiento.getTmovConcepto());
        }
    }

    public void editarMovimiento() {

        String mensaje = "";
        TMovimientoBanco movimiento = (TMovimientoBanco) banModel.consultar(TMovimientoBanco.class, editMovUI.idMovimiento);

        if (editMovUI.jtfSaldoD.getText().trim().equals("")) {
            mensaje += "[- Saldo vacío -] \n";
        }

        if (("" + editMovUI.jtfFechaD.getDate()).length() < 28) {
            mensaje += "[- Fecha vacía -] \n";
        }

        if (mensaje.equals("")) {
            int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro?…\n Desea modificar el movimiento bancario?\n \n Presione “Si” en caso que sea correcto.", "Comprobar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                movimiento.setTmovTipo("" + editMovUI.jcbTipoMovimientoD.getSelectedItem());
                movimiento.setTmovSaldo(Long.parseLong(editMovUI.jtfSaldoD.getText()));
                movimiento.setTmovFecha(editMovUI.jtfFechaD.getDate());
                movimiento.setTmovConcepto(editMovUI.jtaConceptoD.getText());
                if (banModel.editar(movimiento, "MOVIMIENTO BANCO")) {
                    JOptionPane.showMessageDialog(null, "Se edito el movimiento correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    try { //misma funcion que dar clic para cargar la primera tabla en bancos*/
                        int filaSeleccionada = editMovUI.vistaBanco.jtBancoD.getSelectedRow();
                        editMovUI.vistaBanco.banco_controller.initTable(editMovUI.vistaBanco.jtBancoD, 'D');
                        ListSelectionModel selectionModel = editMovUI.vistaBanco.jtBancoD.getSelectionModel();
                        selectionModel.setSelectionInterval(filaSeleccionada, filaSeleccionada);
                        editMovUI.vistaBanco.jtTotalD.setText(String.valueOf(editMovUI.vistaBanco.jtBancoD.getValueAt(filaSeleccionada, 3)));
                        editMovUI.vistaBanco.banco_controller.initTableMovimiento(editMovUI.vistaBanco.jtMovimientosD);
                        editMovUI.vistaBanco.banco_controller.filter(editMovUI.vistaBanco.jtMovimientosD, editMovUI.vistaBanco.jtxSearchMovimientosD.getText(), editMovUI.vistaBanco.jcbMovimientosD.getSelectedIndex());
                        editMovUI.vistaBanco.banco_controller.jtMouseClicked('D');
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Pero ocurrio un error al actualizar los valores de la tabla en pantalla, vuelva a seleccionar el banco", "Información", JOptionPane.INFORMATION_MESSAGE);
                        //e.printStackTrace();
                    }
                    editMovUI.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n \n" + mensaje, "Error!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean eliminarMovimiento(Integer idMovimiento) {
        TMovimientoBanco movimiento = (TMovimientoBanco) banModel.consultar(TMovimientoBanco.class, idMovimiento);
        return movimiento != null ? banModel.eliminar(movimiento, "MOVIMIENTO BANCO") : false;
    }

    public void editarMovimientoCredito() {
        String mensaje = "";
        TMovimientoBanco movimiento = (TMovimientoBanco) banModel.consultar(TMovimientoBanco.class, editMovCre.idMovimiento);

        if (editMovCre.jtfSaldoC.getText().trim().equals("")) {
            mensaje += "[- Saldo vacío -] \n";
        }
        if (editMovCre.jtfPorcentajeC.getText().trim().equals("")) {
            mensaje += "[- Interés vacío -] \n";
        }
        if (("" + editMovCre.jtfFechaC.getDate()).length() < 28) {
            mensaje += "[- Fecha vacía -] \n";
        }

        if (mensaje.equals("")) {
            int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro?…\n Desea modificar el movimiento bancario?\n \n Presione “Si” en caso que sea correcto.", "Comprobar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                movimiento.setTmovTipo("" + editMovCre.jcbTipoMovimientoC.getSelectedItem());
                movimiento.setTmovPorcentaje(Double.parseDouble(editMovCre.jtfPorcentajeC.getText()));
                movimiento.setTmovSaldo(Long.parseLong(editMovCre.jtfSaldoC.getText()));
                movimiento.setTmovFecha(editMovCre.jtfFechaC.getDate());
                movimiento.setTmovConcepto(editMovCre.jtaConceptoC.getText());
                if (banModel.editar(movimiento, "MOVIMIENTO BANCO")) {
                    JOptionPane.showMessageDialog(null, "Se edito el movimiento correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    try { //misma funcion que dar clic para cargar la primera tabla en bancos*/
                        int filaSeleccionada = editMovCre.vistaBanco.jtBancoC.getSelectedRow();
                        editMovCre.vistaBanco.banco_controller.initTable(editMovCre.vistaBanco.jtBancoC, 'C');
                        ListSelectionModel selectionModel = editMovCre.vistaBanco.jtBancoC.getSelectionModel();
                        selectionModel.setSelectionInterval(filaSeleccionada, filaSeleccionada);
                        editMovCre.vistaBanco.jtTotalC.setText(String.valueOf(editMovCre.vistaBanco.jtBancoC.getValueAt(filaSeleccionada, 3)));
                        editMovCre.vistaBanco.banco_controller.initTableMovimientoC(editMovCre.vistaBanco.jtMovimientosC);
                        editMovCre.vistaBanco.banco_controller.filter(editMovCre.vistaBanco.jtMovimientosC, editMovCre.vistaBanco.jtxSearchMovimientosC.getText(), editMovCre.vistaBanco.jcbMovimientos1.getSelectedIndex());
                        editMovCre.vistaBanco.banco_controller.jtMouseClicked('C');
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Pero ocurrio un error al actualizar los valores de la tabla en pantalla, vuelva a seleccionar el banco", "Información", JOptionPane.INFORMATION_MESSAGE);
                        //e.printStackTrace();
                    }
                    editMovCre.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n \n" + mensaje, "Error!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean editarBanco(char ind) {
        TBanco banco = null;
        if (ind == 'D') {
            banco = (TBanco) getBanUI().jtBancoD.getValueAt(getBanUI().jtBancoD.getSelectedRow(), 4);
            banco.setTbanNombre(getBanUI().jtfBancoD.getText());
            banco.setTbanSaldo(Long.parseLong(getBanUI().jtSaldoBaseD.getText()));
            //banco.setTbanCuenta(getBanUI().jtfCuentaD.getText());
        } else if (ind == 'C') {
            banco = (TBanco) getBanUI().jtBancoC.getValueAt(getBanUI().jtBancoC.getSelectedRow(), 4);
            banco.setTbanNombre(getBanUI().jtfBanco1.getText());
            banco.setTbanSaldo(Long.parseLong(getBanUI().jtSaldoBaseC.getText()));
            //banco.setTbanCuenta(getBanUI().jtfCuentaC.getText());
        }
        return banco != null ? banModel.editar(banco, "BANCO") : false;
    }
}
