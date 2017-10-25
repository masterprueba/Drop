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
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Detalle");
        modelo.addColumn("Costo");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel VerMultas() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Fecha");
        modelo.addColumn("Detalle");
        modelo.addColumn("Costo");
        modelo.addColumn("ID prestamo");
        modelo.addColumn("ID multa");
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

    public DefaultTableModel bitacoraGeneralInicioSession() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        return modelo;
    }

    public DefaultTableModel bitacoraIndividualInicioSession() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel historialUsuarioInicioSession() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Año");
        modelo.addColumn("Mes");
        modelo.addColumn("Dia");
        modelo.addColumn("Hora");
        return modelo;
    }

    public DefaultTableModel bitacoraGeneralPrestamo() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Usuario sistema");
        modelo.addColumn("Fecha accion");
        modelo.addColumn("Tipo de accion");
        modelo.addColumn("Cliente");
        modelo.addColumn("Valor del prestamo");
        modelo.addColumn("Object");
        return modelo;
    }

    public DefaultTableModel historialPrestamo() {
        TableModel modelo = new TableModel();
        modelo.addColumn("# de cuota");
        modelo.addColumn("ID");
        modelo.addColumn("Valor prestado");
        modelo.addColumn("N° Cuotas");
        modelo.addColumn("Intereses");
        modelo.addColumn("Metodo Pago");
        modelo.addColumn("Fecha Entrega");
        modelo.addColumn("Valor Total");
        modelo.addColumn("Valor Cuota");
        modelo.addColumn("Object");
        modelo.addColumn("Object_persona");
        return modelo;
    }

    public DefaultTableModel historialCuota() {
        TableModel modelo = new TableModel();
        modelo.addColumn("#");
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Abono");
        modelo.addColumn("Valor Pagado");
        modelo.addColumn("Cuota");
        modelo.addColumn("Metodo Pago");
        modelo.addColumn("Cobrador");
        modelo.addColumn("Object");
        modelo.addColumn("Object_pago");
        modelo.addColumn("Object_cobrador");
        modelo.addColumn("Object_prestamo");
        return modelo;
    }

    public DefaultTableModel bitacoraIndividualPrestamo() {
        TableModel modelo = new TableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Nombre Usuario");
        modelo.addColumn("Metodo pago");
        modelo.addColumn("Fecha entrega");
        modelo.addColumn("Numero cuotas");
        modelo.addColumn("Intereses");
        modelo.addColumn("Valor prestamo");
        modelo.addColumn("Valor cuota");
        modelo.addColumn("Valor total");
        modelo.addColumn("Tipo accion");
        modelo.addColumn("Fecha accion");
        return modelo;
    }

    public DefaultTableModel bitacoraGeneralClientes() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Cedula del Cliente");
        modelo.addColumn("Nombre del Cliente");
        modelo.addColumn("Usuario del sistema");
        modelo.addColumn("Fecha accion");
        modelo.addColumn("Tipo accion");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel bitacoraIndividualClientesCodeudores() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Dirección de vivienda");
        modelo.addColumn("Tipo de vivienda");
        modelo.addColumn("Nombre de empresa");
        modelo.addColumn("Direccion de empresa");
        modelo.addColumn("Telefono de empresa");
        modelo.addColumn("Usuario de la accion");
        modelo.addColumn("Tipo  de accion");
        modelo.addColumn("Fecha de accion");
        modelo.addColumn("cedula");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel bitacoraIndividualReferencia() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Usuario del sistema");
        modelo.addColumn("Fecha accion");
        modelo.addColumn("Tipo accion");
        return modelo;
    }

    public DefaultTableModel bitacoraGeneralGastos() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Fecha del gasto");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Costo");
        modelo.addColumn("Usuario del sistema");
        modelo.addColumn("Fecha accion");
        modelo.addColumn("Tipo accion");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel bitacoraGeneralAbonos() {
        TableModel modelo = new TableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Cliente prestamo");
        modelo.addColumn("Tipo de accion");
        modelo.addColumn("Fecha abono");
        modelo.addColumn("Valor pagado del prestamo");
        modelo.addColumn("Cuotas pagadas");
        modelo.addColumn("Tipo pago");
        modelo.addColumn("Cobrador");
        modelo.addColumn("Usuario sistema");
        modelo.addColumn("Fecha accion");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel informeGeneral() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Prestado");
        modelo.addColumn("Entregado");
        modelo.addColumn("Prestamo total");
        modelo.addColumn("Pagado");
        modelo.addColumn("Debe");
        modelo.addColumn("Extra");
        modelo.addColumn("Ganancia");
        return modelo;
    }

    public DefaultTableModel listaPersonas() {
        TableModel modelo = new TableModel();
        modelo.addColumn("Nombre y Apellido");
        modelo.addColumn("Cedula");
        return modelo;
    }

    public DefaultTableModel bitacoraGeneralMulta() {
        TableModel modelo = new TableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Usuario sistema");
        modelo.addColumn("Fecha evento");
        modelo.addColumn("Tipo evento");
        modelo.addColumn("Cedula Cliente");
        modelo.addColumn("Nombre cliente");
        modelo.addColumn("Valor multa");
        modelo.addColumn("Valor prestamo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel bitacoraHistorialMultas() {
        TableModel modelo = new TableModel();
        modelo.addColumn("");
        modelo.addColumn("Usuario sistema");
        modelo.addColumn("Fecha evento");
        modelo.addColumn("Valor multa");
        modelo.addColumn("Fecha multa");
        modelo.addColumn("Estado Multa");
        modelo.addColumn("Valor prestamo");
        modelo.addColumn("Fecha Prestamo");
        modelo.addColumn("Numero cuotas");
        modelo.addColumn("Valor cuotas");
        modelo.addColumn("Valor total");
        modelo.addColumn("id");
        return modelo;
    }

    public DefaultTableModel listaClientesRefinancia() {
        TableModel modelo = new TableModel() {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                true, false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        modelo.addColumn("*");
        modelo.addColumn("Nombre y Apellido");
        modelo.addColumn("Cedula");
        modelo.addColumn("Deuda");
        modelo.addColumn("id_prestamo");
        modelo.addColumn("valor_total");
        modelo.addColumn("cuotas pagadas");
        return modelo;
    }

    public DefaultTableModel prestamosPorFecha() {
        TableModel modelo = new TableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Valor prestamo");
        modelo.addColumn("Numero de Cuotas");
        modelo.addColumn("Intereses");
        modelo.addColumn("Metodo de pago");
        modelo.addColumn("Fecha entrega");
        modelo.addColumn("Valor Total");
        modelo.addColumn("Valor Cuota");
        return modelo;
    }

    public DefaultTableModel abonoPorFecha() {
        TableModel modelo = new TableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Valor Abonado");
        modelo.addColumn("Fecha de abono");
        modelo.addColumn("Cobrador");
        modelo.addColumn("Metodo de Pago");
        modelo.addColumn("Nuevo saldo");
        modelo.addColumn("Cuotas Pagadas");
        return modelo;
    }

    public DefaultTableModel multaPorFecha() {
        TableModel modelo = new TableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Descripción");
        modelo.addColumn("Valor multa");
        modelo.addColumn("Valor prestado");
        return modelo;
    }

    public DefaultTableModel remanente() {
        TableModel modelo = new TableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cobrador");
        modelo.addColumn("Valor Remanente");
        return modelo;
    }
}
