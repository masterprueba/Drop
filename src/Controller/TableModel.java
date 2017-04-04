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

    @Override
    public boolean isCellEditable(int row, int column) {

        return false;
    }

    public DefaultTableModel VerGastos() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Fecha");
        modelo.addColumn("Detalle");
        modelo.addColumn("Costo");
        return modelo;
    }

    public DefaultTableModel VerUsuarios() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        return modelo;
    }

    public DefaultTableModel bitacoraGeneral() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        return modelo;
    }

    public DefaultTableModel bitacoraIndividual() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel historialInicio() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Año");
        modelo.addColumn("Mes");
        modelo.addColumn("Dia");
        modelo.addColumn("Hora");
        return modelo;
    }
}
