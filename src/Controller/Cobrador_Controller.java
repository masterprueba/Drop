/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Model.Cobrador_Model;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Cobrador_Controller extends Controllers{
    
    public DefaultTableModel dfm;
    private List<TCobrador> listc;
    
    public void initTable(JTable table) {
        //Llenar Tabla
        dfm = new DefaultTableModel();
        table.setModel(dfm);

        dfm.setColumnIdentifiers(new Object[]{"Nombre"});
        selectAll();
        for (int i = 0; i < listc.size(); i++) {
            dfm.addRow(new Object[]{listc.get(i).getTcobNombre()});

        }
    }
   public List<TCobrador> selectAll() {
        listc = new ArrayList<TCobrador>();        
        listc = new Cobrador_Model().findAll(TCobrador.class);
        return listc;
    }
}
