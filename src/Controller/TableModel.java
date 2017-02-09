/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ITERIA
 */
public class TableModel extends DefaultTableModel {

    public DefaultTableModel VerGastos() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Fecha");
        modelo.addColumn("Detalle");
        modelo.addColumn("Costo");
        return modelo;
    }
}
