/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCierre;
import Entity.TGasto;
import Model.Gastos_Model;
import Model.Models;
import Model.Prestamo_model;
import UI.Fechas_UI;
import UI.InformeGeneral;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class Informe_Controller extends Controllers {

    JTable pretamotable;
    JTable gastotable;
    TableRowSorter trs = null;
    private final List<TCierre> mesesCombo;
    private final Models modelo;
    private String fechaini;
    private String fechafin;

    public Informe_Controller() {
        this.mesesCombo = new ArrayList<>();        
        this.modelo = new Models();
    }
    
    public void setTablas(){
        this.pretamotable = InformeGeneral.jtable_infprestamo;
        this.gastotable = InformeGeneral.jtable_infgasto;
    }

    public void cargarDatos() {
        boolean p = obtenerPrestamos(fechaini, fechafin);
        boolean g = obtenerGastos(fechaini, fechafin);
        if (!p && !g) {
            JOptionPane.showMessageDialog(null, "No existen datos");
        }
        InformeGeneral.text_prestado.setText(Math.round(totalDeUnaTabla(((DefaultTableModel) pretamotable.getModel()), 4)) + "");
        InformeGeneral.txt_entregado.setText(Math.round(totalDeUnaTabla(((DefaultTableModel) pretamotable.getModel()), 5)) + "");
        InformeGeneral.txt_total.setText(Math.round(totalDeUnaTabla(((DefaultTableModel) pretamotable.getModel()), 6)) + "");
        InformeGeneral.text_recaudado.setText(Math.round(totalDeUnaTabla(((DefaultTableModel) pretamotable.getModel()), 7)) + "");
        InformeGeneral.txt_deuda.setText(Math.round(totalDeUnaTabla(((DefaultTableModel) pretamotable.getModel()), 8)) + "");
        InformeGeneral.txt_extra.setText(Math.round(totalDeUnaTabla(((DefaultTableModel) pretamotable.getModel()), 9)) + "");
        int ganacias = Math.round(totalDeUnaTabla(((DefaultTableModel) pretamotable.getModel()), 10));
        //gasto
        InformeGeneral.text_gasto.setText(Math.round(totalDeUnaTabla(((DefaultTableModel) gastotable.getModel()), 3)) + "");
        InformeGeneral.text_ganacia.setText("" + (ganacias - Integer.parseInt(InformeGeneral.text_gasto.getText())));
    }

    public void obtenerfechas() {
        if(Fechas_UI.r_fechaini.getDate() == null || Fechas_UI.r_fechafin.getDate() == null){
             JOptionPane.showMessageDialog(null, "DEBES LLENAR LOS CAMPOS");
         }else{
            fechaini = Fechas_UI.r_fechaini.getJCalendar().getYearChooser().getYear() + "/" + (Fechas_UI.r_fechaini.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + Fechas_UI.r_fechaini.getJCalendar().getDayChooser().getDay();
            fechafin = Fechas_UI.r_fechafin.getJCalendar().getYearChooser().getYear() + "/" + (Fechas_UI.r_fechafin.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + Fechas_UI.r_fechafin.getJCalendar().getDayChooser().getDay();
        }                
    }

    public boolean obtenerPrestamos(String fechaini, String fechafin) {
        DefaultTableModel tmodelop = new TableModel().informeGeneral();
        pretamotable.setModel(tmodelop);
        pretamotable.setRowSorter(filtrarTabla(tmodelop));
        Prestamo_model modelo = new Prestamo_model();        
        List<Object> prestamos = modelo.informePrestamo(fechaini, fechafin);
        Iterator itr = prestamos.iterator();
        Object[] f = new Object[11];
        boolean existe = false;
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            f[1] = obj[0];
            f[2] = obj[1];
            f[3] = obj[2];
            int prestado = obj[3] != null ? Integer.parseInt(String.valueOf(obj[3])) : 0;
            int invertido = obj[4] != null ? Integer.parseInt(String.valueOf(obj[4])) : 0;
            int total = obj[5] != null ? Integer.parseInt(String.valueOf(obj[5])) : 0;
            int pagado = obj[6] != null ? Integer.parseInt(String.valueOf(obj[6])) : 0;
            int deuda = obj[7] != null ? Integer.parseInt(String.valueOf(obj[7])) : 0;
            int extra = obj[8] != null ? Integer.parseInt(String.valueOf(obj[8])) : 0;
            f[4] = prestado;
            f[5] = invertido;
            f[6] = total;
            f[7] = pagado;
            f[8] = deuda;
            f[9] = extra;
            f[10] = (total - invertido)-deuda;
            if (obj[1] != null) {
                tmodelop.addRow(f);
                existe = true;
            }
        }
        numerarTabla(tmodelop);
        return existe;

    }

    public boolean obtenerGastos(String fecha1, String fecha2) {
        List<TGasto> gastos = new ArrayList();
        boolean exist = false;
        Gastos_Model MGastos = new Gastos_Model();
        gastos = MGastos.ConsultarPorFechas(fecha1, fecha2, "");
        DefaultTableModel tmodelog = new TableModel().VerGastos();
        gastotable.setModel(tmodelog);
        gastotable.setRowSorter(filtrarTabla(tmodelog));
        tmodelog.setNumRows(0);
        for (int i = 0; i < gastos.size(); i++) {
            String[] fila = new String[6];
            fila[1] = gastos.get(i).getTgasFecha().toString();
            fila[2] = gastos.get(i).getTgasDesc();
            fila[3] = gastos.get(i).getTgasCosto().toString();
            tmodelog.addRow(fila);
            exist = true;
        }
        numerarTabla(tmodelog);
        return exist;
    }

    public void filter(JTextField jtf, JTable jtb) {

        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jtf.getText(), 2));
            }
        });

        trs = new TableRowSorter(jtb.getModel());
        jtb.setRowSorter(trs);
    }

    public void cargarUltimosMeses() {
        InformeGeneral.jComboBox1.removeAllItems();
        mesesCombo.clear();
        int mes = LocalDate.now().getMonthValue();
        int año = LocalDate.now().getYear();

        for (int i = 0; i < 12; i++) {
            if (mes - 1 != 0) {
                try {
                    if (Cierre_Controller.consutarCierre((new SimpleDateFormat("yyyy-MM")).parse(año + "-" + mes))) {
                        InformeGeneral.jComboBox1.addItem(mes(mes) + "-" + año);
                        mesesCombo.add(new TCierre(mes, año));
                    }
                    mes--;
                } catch (ParseException e) {
                }
            } else {
                try {
                    if (Cierre_Controller.consutarCierre((new SimpleDateFormat("yyyy-MM")).parse(año + "-" + mes))) {
                        InformeGeneral.jComboBox1.addItem(mes(mes) + "-" + año);
                        mesesCombo.add(new TCierre(mes, año));
                    }
                    año--;
                    mes = 12;
                } catch (ParseException e) {
                }
            }
        }
    }

    private String mes(int mes) {
        switch (mes) {
            case 1:
                return "ENERO";
            case 2:
                return "FEBRERO";
            case 3:
                return "MARZO";
            case 4:
                return "ABRIL";
            case 5:
                return "MAYO";
            case 6:
                return "JUNIO";
            case 7:
                return "JULIO";
            case 8:
                return "AGOSTO";
            case 9:
                return "SEPTIEMBRE";
            case 10:
                return "OCTUBRE";
            case 11:
                return "NOVIEMBRE";
            case 12:
                return "DICIEMBRE";
            default:
                return "Mes no encontrado";
        }
    }

    public void consultarMes() {
        if (mesesCombo.size() > 0) {
            fechaini = mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()).getTciAno() + "-" + mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()).getTciMes() + "-01";
            Calendar cal = new GregorianCalendar(mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()).getTciAno(), mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()).getTciMes() - 1, 1);
            int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            fechafin = mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()).getTciAno() + "-" + mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()).getTciMes() + "-" + days;
            try {
                Calendar c = Calendar.getInstance();
                c.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(fechaini));
               // InformeGeneral.general_fechaini.setCalendar(c);
                Calendar c2 = Calendar.getInstance();
                c2.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse(fechafin));
                //InformeGeneral.general_fechafin.setCalendar(c2);
            } catch (ParseException e) {
                System.err.println("Error parseando fecha");
            }
             //cargarDatos(true);
        }
    }

    public void cerrarMes() {
        if (modelo.insertar(mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()), "CIERRE") != null) {
            consultarMes();
            JOptionPane.showMessageDialog(null, "Se cerro el mes de " + mes(mesesCombo.get(InformeGeneral.jComboBox1.getSelectedIndex()).getTciMes()));
            Cierre_Controller.consultarCierre();
            cargarUltimosMeses();
        }
    }

    public void listarMesesCerrados(DefaultTableModel model) {
        model.setRowCount(0);
        modelo.findAll(TCierre.class).stream().forEach(a -> {
            TCierre x = (TCierre) a;
            model.addRow(new Object[]{"", mes(x.getTciMes()), x.getTciAno(), x.getTciId()});
        });
        numerarTabla(model);
    }

    public void eliminarMes(Integer valueOf) {
        TCierre mes = new TCierre();
        mes.setTciId(valueOf);
        if (modelo.eliminar(mes, "CIERRE")) {
            JOptionPane.showMessageDialog(null, "Se cancelo el mes seleccionado");
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al cancelar cierre");
        }
        Cierre_Controller.consultarCierre();
        cargarUltimosMeses();
    }
    
    public void filter(JTable jt, String textBuscar, int columna) {
         TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jt.getModel());
         tr.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscar, columna));
         jt.setRowSorter(tr);
     }
}
