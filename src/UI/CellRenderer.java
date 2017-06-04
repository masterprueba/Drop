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
    
    Color c;
    public CellRenderer(Color color) {
        c =color;
    }
    
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);        
        setBackground(c);
        if (row == table.getRowCount()-1) {            
            setBackground(null);
        }
        return this;
    }             

}
