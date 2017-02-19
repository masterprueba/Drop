/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ITERIA
 */
public class Vistas extends javax.swing.JFrame{

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

    public void numerarTabla(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(i + 1, i, 0);
        }
    }

    public float totalDeUnaTabla(DefaultTableModel modelo, int columna) {
        float total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            total = Integer.parseInt(modelo.getValueAt(i, columna).toString()) + total;
        }
        return total;
    }
}
