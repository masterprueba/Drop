/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ITERIA
 */
public class Funciones_Vistas {

    public void SoloNumeros(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if ((caracter < '0') || (caracter > '9') && (caracter != '\b')) {
            evt.consume();
        }
    }

    public void LimitarCaracteres(KeyEvent evt, JTextField JT, int cant) {
        if (JT.getText().length() == cant) {
            evt.consume();
        }
    }

    public void LimitarCaracteresJTxArea(KeyEvent evt, JTextArea JT, int cant) {
        if (JT.getText().length() == cant) {
            evt.consume();
        }
    }

    public void NumerarTabla(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(i + 1, i, 0);
        }
    }
}
