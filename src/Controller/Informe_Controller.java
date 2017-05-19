/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Prestamo_model;
import UI.InformeGeneral;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Informe_Controller extends Controllers{
    
    JTable pretamotable;
    JTable gastotable;

    public Informe_Controller(JTable pretamotable, JTable gastotable) {
        this.pretamotable = pretamotable;
        this.gastotable = gastotable;
    }         
    
    public void obtenerDatos(boolean metodo){        
        DefaultTableModel tmodelop = new TableModel().informeGeneral();
        pretamotable.setModel(tmodelop);
        Prestamo_model modelo = new Prestamo_model();
        String fechaini = InformeGeneral.general_fechaini.getDate() != null
                ? InformeGeneral.general_fechaini.getJCalendar().getYearChooser().getYear() + "-" + (InformeGeneral.general_fechaini.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + InformeGeneral.general_fechaini.getJCalendar().getDayChooser().getDay()
                : Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE);
        String fechafin = InformeGeneral.general_fechafin.getDate() != null
                ? InformeGeneral.general_fechafin.getJCalendar().getYearChooser().getYear() + "-" + (InformeGeneral.general_fechafin.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + InformeGeneral.general_fechafin.getJCalendar().getDayChooser().getDay()
                : Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE);
        List<Object> prueba = modelo.informePrestamo(fechaini,fechafin);
        Iterator itr = prueba.iterator();                
        Object[] f = new Object[8];
        boolean existe = false;
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();                        
            f[1] = obj[0];
            f[2] = obj[1];
            int prestado = obj[2] != null ? Integer.parseInt(String.valueOf(obj[2])) : 0;
            int pagado = obj[4] != null ? Integer.parseInt(String.valueOf(obj[4])) : 0;
            f[3] = prestado;
            f[4] = obj[3];
            f[5] = pagado;
            f[6] = prestado - pagado;
            if(obj[1]!=null){                
                tmodelop.addRow(f);
                existe = true;
            }            
         }
        if (!existe && metodo) {
            JOptionPane.showMessageDialog(null, "No existen Datos");
        }
        numerarTabla(tmodelop);
    }
}
