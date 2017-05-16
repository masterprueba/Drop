/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCuota;
import Model.Prestamo_model;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Informe_Controller extends Controllers{
    
    public void CargarTablas(JTable prestamos, JTable gastos){
        DefaultTableModel tmodelop = (DefaultTableModel) prestamos.getModel();
        DefaultTableModel tmodelog = (DefaultTableModel) gastos.getModel();
        Prestamo_model modelo = new Prestamo_model();
        List<Object> prueba = modelo.informePrestamo();
        Iterator itr = prueba.iterator();
        Object[] f = new Object[11];
        int i = 1;
        while(itr.hasNext()){
            f[0] = i;
            Object[] obj = (Object[]) itr.next();
            f[1] = obj[0];
            f[2] = obj[1];
            f[3] = obj[2];
            f[4] = obj[3];
            f[5] = obj[4];
            f[6] = Integer.parseInt(String.valueOf(obj[4])) - Integer.parseInt(String.valueOf(obj[2]));
            i++;
            tmodelop.addRow(f);
         }
       prestamos.setModel(tmodelop);
    }
    
}
