/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ITERIA
 */
public class Views extends javax.swing.JInternalFrame{

    public void soloNumeros(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if ((caracter < '0') || (caracter > '9') && (caracter != '\b')) {
            evt.consume();
        }
    }

    public void limitarCaracteres(KeyEvent evt, JTextField JT, int cant) {
        if (JT.getText().length() == cant) {
            evt.consume();
        }
    }

    public void limitarCaracteresJTxArea(KeyEvent evt, JTextArea JT, int cant) {
        if (JT.getText().length() == cant) {
            evt.consume();
        }
    }
    
    public void setCellRender(JTable table, Color color) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new CellRenderer(color));
        }
    }
    public TableRowSorter filtrarTabla(DefaultTableModel modelo){            
            TableRowSorter sorter = new TableRowSorter(modelo);            
            return sorter;
    }
}
