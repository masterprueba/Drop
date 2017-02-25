/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TDatosBasicosPersona;
import Entity.TReferencia;
import Model.Referencia_Model;
import UI.Cliente_UI;
import UI.Referencia_UI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author Andres
 */
public class Referencia_Controller {

    private TReferencia ref;
    private Referencia_Model refModel;
    private List<TReferencia> listRef;

    private Cliente_UI cli_UI;
    private Referencia_UI ref_UI;

    private int ObjectIdAfterInserting;
    private int globalSelected = -1;

    public Referencia_Controller(Cliente_UI cli_UI, Referencia_UI ref_UI) {
        this.cli_UI = cli_UI;
        this.ref_UI = ref_UI;
        refModel = new Referencia_Model();
    }

//<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public boolean insert(TReferencia objRef) {
        boolean boo = false;
        ObjectIdAfterInserting = Integer.parseInt("" + refModel.insertar(objRef, true));

        if (ObjectIdAfterInserting != 0) {
            ref = objRef;
            ref.setTrefId(ObjectIdAfterInserting);
            boo = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
        }
        return boo;
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Method to SELECT ALL WHERE">
    public boolean selectAll(TReferencia objRef) {
        boolean boo = false;
        try {
            List<TReferencia> rResult = refModel.SelectAllWhere(objRef);
            if (rResult.size() > 0) {
                listRef = rResult;
                boo = true;
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
        return boo;
    }
//</editor-fold>      

//<editor-fold defaultstate="collapsed" desc="Method to DELETE ALL WHERE">
    public boolean delete(TReferencia objRef) {
        return refModel.eliminar(objRef);
    }
//</editor-fold>     

//<editor-fold defaultstate="collapsed" desc="prepare INSERT">
    public void prepareInsert(TReferencia objRef) {
        insert(objRef);
    }
//</editor-fold>  
    
//<editor-fold defaultstate="collapsed" desc="prepare SELECT">
    public List<TReferencia> prepareSelect(String cc, String by) {
        TDatosBasicosPersona temp = new TDatosBasicosPersona();
        temp.setTdbpCedula(cc);

        ref = new TReferencia();
        ref.setTDatosBasicosPersona(temp);
        
        boolean b = selectAll(ref);
        
        if (b) {
            if (by == "CLIENTE") {
                //setDataRefCliente();
                cli_UI.objectRefeCli = listRef;
            } else if (by == "CODEUDOR") {
                //setDataRefCodeudor();
                cli_UI.objectRefeCod = listRef;
            }
        }
        
        return listRef;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="prepare DELETE">
    public void prepareDelete(TReferencia objRef) {
        delete(objRef);
    }
    //</editor-fold>     

//<editor-fold defaultstate="collapsed" desc="set Referencia in Table">
    public void setDataRefCliente() {
        DefaultTableModel dfm = (DefaultTableModel) ref_UI.jTable1.getModel();
        if (cli_UI.objectRefeCli.size() > 0) {
            
            for (int i = 0; i < cli_UI.objectRefeCli.size(); i++) {
                dfm.addRow(new Object[]{
                    cli_UI.objectRefeCli.get(i).getTrefNombre(),
                    cli_UI.objectRefeCli.get(i).getTrefApellido(),
                    cli_UI.objectRefeCli.get(i).getTrefTelefono(),
                    cli_UI.objectRefeCli.get(i).getTrefTipo()
                });
            }
        }
        
        //dfm = (DefaultTableModel) ref_UI.jTable1.getModel();
    }

    public void setDataRefCodeudor() {
        DefaultTableModel dfm = (DefaultTableModel) ref_UI.jTable1.getModel();
        if (cli_UI.objectRefeCod.size() > 0) {
            for (int i = 0; i < cli_UI.objectRefeCod.size(); i++) {
                dfm.addRow(new Object[]{
                    cli_UI.objectRefeCod.get(i).getTrefNombre(),
                    cli_UI.objectRefeCod.get(i).getTrefApellido(),
                    cli_UI.objectRefeCod.get(i).getTrefTelefono(),
                    cli_UI.objectRefeCod.get(i).getTrefTipo()
                });
            }
        }
        //dfm = (DefaultTableModel) ref_UI.jTable1.getModel();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Agregar Referencia">
    public void agregarReferencia() {
        DefaultTableModel dfm = (DefaultTableModel) ref_UI.jTable1.getModel();

        if (globalSelected != -1) {
            Object[] ob = new Object[]{
                ref_UI.jtfNombreReferencia.getText(),
                ref_UI.jtfApellidoReferencia.getText(),
                ref_UI.jtfTelefonoReferencia.getText(),
                ref_UI.jcbTipoReferencia.getSelectedItem()
            };

            for (int i = 0; i < dfm.getColumnCount(); i++) {
                dfm.setValueAt(ob[i], globalSelected, i);
            }

            clean();

        } else {

            if (dfm.getRowCount() < 4) {
                dfm.addRow(new Object[]{
                    ref_UI.jtfNombreReferencia.getText(),
                    ref_UI.jtfApellidoReferencia.getText(),
                    ref_UI.jtfTelefonoReferencia.getText(),
                    ref_UI.jcbTipoReferencia.getSelectedItem()
                });

                globalSelected = -1;
            } else {
                JOptionPane.showMessageDialog(ref_UI, "Limite de referencias alzanzado", "Advertencia!!!", JOptionPane.ERROR_MESSAGE);
            }

            ref_UI.jTable1.setModel(dfm);
            clean();
        }
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Quitar Referencia">
    public void quitarReferencia() {
        int[] cantSelect;
        int r;
        DefaultTableModel dfm = (DefaultTableModel) ref_UI.jTable1.getModel();
        try {
            cantSelect = ref_UI.jTable1.getSelectedRows();

            if (cantSelect.length == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar 1(una) referencia", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (cantSelect.length > 1) {
                JOptionPane.showMessageDialog(null, "Seleccione solo una para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                r = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea quitar esta referencia?", "Quitar Referencia", JOptionPane.YES_NO_CANCEL_OPTION);
                if (r == JOptionPane.YES_OPTION) {
                    dfm.removeRow(cantSelect[0]);
                }
            }
        } catch (Exception e) {
        }
    }
//</editor-fold>  

//<editor-fold defaultstate="collapsed" desc="Editar Referencia">
    public void editReferencia() {
        int cantSelect;
        int r;
        DefaultTableModel dfm = (DefaultTableModel) ref_UI.jTable1.getModel();
        try {
            cantSelect = ref_UI.jTable1.getSelectedRow();
            globalSelected = cantSelect;
            if (cantSelect == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar 1(una) referencia", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                ref_UI.jtfNombreReferencia.setText(String.valueOf(ref_UI.jTable1.getValueAt(ref_UI.jTable1.getSelectedRow(), 0)));
                ref_UI.jtfApellidoReferencia.setText(String.valueOf(ref_UI.jTable1.getValueAt(ref_UI.jTable1.getSelectedRow(), 1)));
                ref_UI.jtfTelefonoReferencia.setText(String.valueOf(ref_UI.jTable1.getValueAt(ref_UI.jTable1.getSelectedRow(), 2)));
                ref_UI.jcbTipoReferencia.setSelectedItem(String.valueOf(ref_UI.jTable1.getValueAt(ref_UI.jTable1.getSelectedRow(), 3)));
            }
        } catch (Exception e) {
        }
    }
//</editor-fold>  

//<editor-fold defaultstate="collapsed" desc="set Data">
    public void setData() {
        switch (ref_UI.by) {
            case "CLIENTE":
                cli_UI.objectRefeCli = new ArrayList<>();
                cli_UI.objectRefeCli = dataReferencia();
                cli_UI.jtfReferenciaCliente.setText(cli_UI.objectRefeCli.size() + " Referencias");
                System.out.println(cli_UI.objectRefeCli.size());
                ref_UI.dispose();
                break;
            case "CODEUDOR":
                cli_UI.objectRefeCod = new ArrayList<>();
                cli_UI.objectRefeCod = dataReferencia();
                cli_UI.jtfReferenciaCodeudor.setText(cli_UI.objectRefeCod.size() + " Referencias");
                ref_UI.dispose();
                break;
            default:
                break;
        }
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="data Referencia">
    public ArrayList<TReferencia> dataReferencia() {
        DefaultTableModel dfm = (DefaultTableModel) ref_UI.jTable1.getModel();
        dfm.getRowCount();
        ArrayList<TReferencia> tr = new ArrayList<>();
        for (int i = 0; i < dfm.getRowCount(); i++) {
            ref = new TReferencia();
            ref.setTrefNombre("" + dfm.getValueAt(i, 0));
            ref.setTrefApellido("" + dfm.getValueAt(i, 1));
            ref.setTrefTelefono("" + dfm.getValueAt(i, 2));
            ref.setTrefTipo("" + dfm.getValueAt(i, 3));

            tr.add(ref);
        }
        System.out.println("Se agrego al arreglo" + tr.size());
        return tr;
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Clean">
    public void clean() {
        globalSelected = -1;
        ref_UI.jtfNombreReferencia.setText("");
        ref_UI.jtfApellidoReferencia.setText("");
        ref_UI.jtfTelefonoReferencia.setText("");
        ref_UI.jcbTipoReferencia.setSelectedIndex(0);
    }
//</editor-fold>    

}
