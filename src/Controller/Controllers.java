/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class Controllers {

    protected DecimalFormat formateador;

    protected static void numerarTabla(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(i + 1, i, 0);
        }
    }

    protected float totalDeUnaTabla(DefaultTableModel modelo, int columna) {
        float total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            total = Integer.parseInt(modelo.getValueAt(i, columna).toString()) + total;
        }
        return total;
    }

    protected void clearForm(Object panel) {
        String nombre_clase = panel.getClass().getName();
        if (nombre_clase.equals("javax.swing.JPanel")) {
            clearPanel((javax.swing.JPanel) panel);
        } else if (nombre_clase.equals("javax.swing.JScrollPane")) {
            clearScrollPane((javax.swing.JScrollPane) panel);
        }
    }

    protected void clearPanel(javax.swing.JPanel panel) {
        java.awt.Component[] componentes = panel.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            clearComponent(componentes[i]);
        }
    }

    protected void clearScrollPane(javax.swing.JScrollPane panel) {
        java.awt.Component[] componentes = panel.getViewport().getComponents();
        for (int i = 0; i < componentes.length; i++) {
            clearComponent(componentes[i]);
        }
    }

    private void clearComponent(java.awt.Component comp) {
        String nombre_clase = comp.getClass().getName();
        if (nombre_clase.equals("javax.swing.JTextField")) {
            ((javax.swing.JTextField) comp).setText("");
        } else if (nombre_clase.equals("javax.swing.JComboBox")) {
            ((javax.swing.JComboBox) comp).setSelectedIndex(0);
        } else if (nombre_clase.equals("javax.swing.JTextArea")) {
            ((javax.swing.JTextArea) comp).setText("");
        } else if (nombre_clase.equals("javax.swing.JPanel")) {
            clearPanel((javax.swing.JPanel) comp);
        } else if (nombre_clase.equals("javax.swing.JScrollPane")) {
            clearScrollPane((javax.swing.JScrollPane) comp);
        }
    }

    protected void setVisibleColumnTable(javax.swing.JTable jt, int[] position) {

        for (int i = 0; i < position.length; i++) {
            jt.getColumnModel().getColumn(position[i]).setMaxWidth(0);
            jt.getColumnModel().getColumn(position[i]).setMinWidth(0);
            jt.getColumnModel().getColumn(position[i]).setPreferredWidth(0);
        }

    }

    public TableRowSorter filtrarTabla(DefaultTableModel modelo) {
        TableRowSorter sorter = new TableRowSorter(modelo);
        return sorter;
    }

    public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog) {
        boolean fitWidth = true;
        boolean interactive = true;
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try {
            boolean complete = jTable.print(mode,
                    new MessageFormat(header),
                    new MessageFormat(footer),
                    showPrintDialog,
                    null,
                    interactive);
            if (complete) {
                JOptionPane.showMessageDialog(null,
                        "Impresión completa",
                        "Resultado de la impresión",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Impresión cancelada",
                        "Resultado de la impresión",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(null,
                    "Fallo de impresión: " + pe.getMessage(),
                    "Resultado de la impresión",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
