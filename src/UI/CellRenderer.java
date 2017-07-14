/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ITERIA
 */
public class CellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    Color c = null;

    public CellRenderer(Color color) {
        c = color;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (c != null) {
            super.getTableCellRendererComponent(table, value, false, hasFocus, row, column);
            setBackground(c);
            if (row == table.getRowCount() - 1) {
                setBackground(null);
            }
        } else {
            super.getTableCellRendererComponent(table, value, false, hasFocus, row, column);
            Color c = new Color(87, 166, 057);
            setBackground(null);
            if (row == 0) {
                setBackground(c);
            }
        }
        return this;
    }

}
