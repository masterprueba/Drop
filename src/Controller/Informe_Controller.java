/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TGasto;
import Model.Gastos_Model;
import Model.Prestamo_model;
import UI.InformeGeneral;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

/**
 *
 * @author Usuario
 */
public class Informe_Controller extends Controllers {

    JTable pretamotable;
    JTable gastotable;
    TableRowSorter trs = null;

    public Informe_Controller(JTable pretamotable, JTable gastotable) {
        this.pretamotable = pretamotable;
        this.gastotable = gastotable;
    }

    public void cargarDatos(boolean metodo) {
        String fechaini = InformeGeneral.general_fechaini.getDate() != null
                ? InformeGeneral.general_fechaini.getJCalendar().getYearChooser().getYear() + "-" + (InformeGeneral.general_fechaini.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + InformeGeneral.general_fechaini.getJCalendar().getDayChooser().getDay()
                : Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE);
        String fechafin = InformeGeneral.general_fechafin.getDate() != null
                ? InformeGeneral.general_fechafin.getJCalendar().getYearChooser().getYear() + "-" + (InformeGeneral.general_fechafin.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + InformeGeneral.general_fechafin.getJCalendar().getDayChooser().getDay()
                : Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE);
        boolean p = obtenerPrestamos(fechaini, fechafin);
        boolean g = obtenerGastos(fechaini, fechafin);
        if (!p && !g && metodo) {
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

    public boolean obtenerPrestamos(String fechaini, String fechafin) {
        DefaultTableModel tmodelop = new TableModel().informeGeneral();
        pretamotable.setModel(tmodelop);
        pretamotable.setRowSorter(filtrarTabla(tmodelop));
        Prestamo_model modelo = new Prestamo_model();
        String nombre = InformeGeneral.txt_nombre.getText();
        List<Object> prestamos = modelo.informePrestamoXn(fechaini, fechafin, nombre);
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
            f[8] = total - pagado;
            f[9] = extra;
            f[10] = pagado - prestado;
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
}
