/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Prestamo_model;
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
        
    }
    
}
