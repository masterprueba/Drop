/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TPersona;
import Entity.TReferencia;
import UI.Cliente__UI;
import UI.Referencia_UI;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class Referencia_Controller {

    //getSelectedItem()
    private Referencia_UI refUI;
    private String modulo;
    private DefaultTableModel dfm;
    private TPersona objPersona;
    private TReferencia r;

    public Referencia_Controller(Referencia_UI refUI, String modulo, TPersona objPersona) {
        this.refUI = refUI;
        this.modulo = modulo;
        this.objPersona = objPersona;
    }

    public void setDataTable() {
        dfm = (DefaultTableModel) refUI.jTable1.getModel();
        if (dfm.getRowCount() < 4) {
            dfm.addRow(new Object[]{
                refUI.jtfNombreReferencia.getText(),
                refUI.jtfApellidoReferencia.getText(),
                refUI.jtfTelefonoReferencia.getText(),
                refUI.jcbTipoReferencia.getSelectedItem()
            });
        } else {
            JOptionPane.showMessageDialog(refUI, "Limite de referencias alzanzado", "Advertencia!!!", JOptionPane.ERROR_MESSAGE);
        }

        refUI.jTable1.setModel(dfm);
        clean();
    }

    public void setData() {
        switch (modulo) {
            case "Cliente":
                Cliente__UI.objectsRefeCli = dataReferencia();
                Cliente__UI.jtfReferenciaCliente.setText(dfm.getRowCount() + " Referencias");
                System.out.println(Cliente__UI.objectsRefeCli.size());
                break;
            case "Codeudor":
                Cliente__UI.objectsRefeCod = dataReferencia();
                Cliente__UI.jtfReferenciaCodeudor.setText(dfm.getRowCount() + " Referencias");
                break;
            default:
                break;
        }

        refUI.dispose();
    }

    public ArrayList<TReferencia> dataReferencia() {
        ArrayList<TReferencia> tr = new ArrayList<>();
        for (int i = 0; i < dfm.getRowCount(); i++) {
            r = new TReferencia();
            r.setTPersona(objPersona);
            r.setTrefNombre("" + dfm.getValueAt(i, 0));
            r.setTrefApellido("" + dfm.getValueAt(i, 1));
            r.setTrefTelefono("" + dfm.getValueAt(i, 2));
            r.setTrefTipo("" + dfm.getValueAt(i, 3));

            tr.add(r);
        }

        return tr;
    }

    public void clean() {
        refUI.jtfNombreReferencia.setText("");
        refUI.jtfApellidoReferencia.setText("");
        refUI.jtfTelefonoReferencia.setText("");
        refUI.jcbTipoReferencia.setSelectedIndex(0);
    }

    public void initTable() {
        switch (modulo) {
            case "Cliente":
                if (Cliente__UI.objectsRefeCli.size() > 0) {
                    dfm = (DefaultTableModel) refUI.jTable1.getModel();
                    for (int i = 0; i < Cliente__UI.objectsRefeCli.size(); i++) {
                        dfm.addRow(new Object[]{
                            Cliente__UI.objectsRefeCli.get(i).getTrefNombre(),
                            Cliente__UI.objectsRefeCli.get(i).getTrefApellido(),
                            Cliente__UI.objectsRefeCli.get(i).getTrefTelefono(),
                            Cliente__UI.objectsRefeCli.get(i).getTrefTipo()
                        });
                    }
                }
                break;
            case "Codeudor":
                if (Cliente__UI.objectsRefeCod.size() > 0) {
                    dfm = (DefaultTableModel) refUI.jTable1.getModel();
                    for (int i = 0; i < Cliente__UI.objectsRefeCod.size(); i++) {
                        dfm.addRow(new Object[]{
                            Cliente__UI.objectsRefeCod.get(i).getTrefNombre(),
                            Cliente__UI.objectsRefeCod.get(i).getTrefApellido(),
                            Cliente__UI.objectsRefeCod.get(i).getTrefTelefono(),
                            Cliente__UI.objectsRefeCod.get(i).getTrefTipo()
                        });
                    }
                }
                break;
            default:
                break;
        }

        dfm = (DefaultTableModel) refUI.jTable1.getModel();
    }

    public void deleteReferencia() {
        int[] cantSelect;
        int r;
        dfm = (DefaultTableModel) refUI.jTable1.getModel();
        try {
            cantSelect = refUI.jTable1.getSelectedRows();
            if (cantSelect.length == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar minimo 1(una) referencia", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                r = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea quitar esta referencia?", "Quitar Referencia", JOptionPane.YES_NO_CANCEL_OPTION);
                if (r == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < cantSelect.length; i++) {
                        dfm.removeRow(i);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void editReferencia() {
        int[] cantSelect;
        int r;
        dfm = (DefaultTableModel) refUI.jTable1.getModel();
        try {
            cantSelect = refUI.jTable1.getSelectedRows();
            if (cantSelect.length == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar minimo 1(una) referencia", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                refUI.jtfNombreReferencia.setText(String.valueOf(refUI.jTable1.getValueAt(refUI.jTable1.getSelectedRow(), 0)));
                refUI.jtfApellidoReferencia.setText(String.valueOf(refUI.jTable1.getValueAt(refUI.jTable1.getSelectedRow(), 1)));
                refUI.jtfTelefonoReferencia.setText(String.valueOf(refUI.jTable1.getValueAt(refUI.jTable1.getSelectedRow(), 2)));
                refUI.jcbTipoReferencia.setSelectedItem(String.valueOf(refUI.jTable1.getValueAt(refUI.jTable1.getSelectedRow(), 3)));
            }
        } catch (Exception e) {
        }
    }
}
