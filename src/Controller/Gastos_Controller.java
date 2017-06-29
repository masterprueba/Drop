/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TGasto;
import Model.Gastos_Model;
import UI.Gastos_UI;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ITERIA
 */
public class Gastos_Controller extends Controllers {

    private final Gastos_Model MGastos = new Gastos_Model();
    private final Gastos_UI VistaGastos;
    private static TGasto Gasto;
    private List<TGasto> gastosresult;

    public Gastos_Controller(Gastos_UI VistaGastos) {
        this.VistaGastos = VistaGastos;
    }

    public void registrarGasto() {
        if (validar()) {
            llenarObjetoGastos();
            if (MGastos.insertar(Gasto,"GASTOS") != null) {
                vaciarCampos();
                traerGastos(obtenerRadiobuttonSeleccionado());
                JOptionPane.showMessageDialog(null, "Se ha ingresado un nuevo gasto");
            }
        }
    }

    private boolean validar() {
        String mensaje = "";
        if (VistaGastos.jTextField2.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el valor del gasto \n";
        }
        if (VistaGastos.jTextArea1.getText().equals("")) {
            mensaje += "-No se puede dejar vacia la descripcion del gasto \n";
        }
        if (VistaGastos.Comp_Fecha_Gasto.getDate() == null) {
            mensaje += "-No se puede dejar vacia la fecha del gasto \n";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n" + mensaje);
            return false;
        }
        return true;
    }

    public void actualizarGasto() {
        if (validar()) {
            llenarObjetoGastos();
            if (MGastos.editar(Gasto,"GASTOS")) {
                JOptionPane.showMessageDialog(null, "El gasto fue editado correctamente!");
                vaciarCampos();
                traerGastos(obtenerRadiobuttonSeleccionado());
                desactivarBotones(0);
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al editar el gasto, intente nuevamente");
            }
        }
    }

    private void llenarObjetoGastos() {
        if (Gasto == null) {
            Gasto = new TGasto();
        }
        Gasto.setTgasFecha(VistaGastos.Comp_Fecha_Gasto.getDate());
        Gasto.setTgasDesc(VistaGastos.jTextArea1.getText());
        Gasto.setTgasCosto(Long.parseLong(VistaGastos.jTextField2.getText()));
    }

    public void vaciarCampos() {
        VistaGastos.jTextArea1.setText(null);
        VistaGastos.jTextField2.setText(null);
        VistaGastos.Comp_Fecha_Gasto.setDate(null);
        Gasto = null;
    }

    public void traerUnGasto(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int fila = VistaGastos.jTable1.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                Gasto = (TGasto) MGastos.consultar(TGasto.class, gastosresult.get(Integer.parseInt(VistaGastos.modelo.getValueAt(fila, 0).toString()) - 1).getTgasId());
                VistaGastos.jTextField2.setText(Gasto.getTgasCosto().toString());
                VistaGastos.jTextArea1.setText(Gasto.getTgasDesc());
                VistaGastos.Comp_Fecha_Gasto.setDate(Gasto.getTgasFecha());
                desactivarBotones(1);
            }
        }
    }

    public void traerGastos(int V) {
        switch (V) {
            case 1:
                gastosresult = MGastos.ConsultarGastosMes((Calendar.getInstance().get(Calendar.MONTH) + 1), Calendar.getInstance().get(Calendar.YEAR), "and tgasDesc like '" + VistaGastos.jTextField3.getText() + "%' and tgasCosto like '" + VistaGastos.jTextField4.getText() + "%'");
                break;
            case 2:
                gastosresult = MGastos.findAll(TGasto.class, "tgasDesc like '" + VistaGastos.jTextField3.getText() + "%' and tgasCosto like '" + VistaGastos.jTextField4.getText() + "%'");
                break;
            case 3:
                if (VistaGastos.Comp_Fecha_Desde1.getDate() != null && VistaGastos.Comp_Fecha_Desde2.getDate() != null) {
                    String fecha1 = VistaGastos.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                    String fecha2 = VistaGastos.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
                    gastosresult = MGastos.ConsultarPorFechas(fecha1, fecha2, "and tgasDesc like '" + VistaGastos.jTextField3.getText() + "%' and tgasCosto like '" + VistaGastos.jTextField4.getText() + "%'");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione la fecha de inicio y la fecha final");
                }
                break;
        }
        VistaGastos.modelo.setNumRows(0);
        for (int i = 0; i < gastosresult.size(); i++) {
            String[] fila = new String[6];
            fila[1] = gastosresult.get(i).getTgasFecha().toString();
            fila[2] = gastosresult.get(i).getTgasDesc();
            fila[3] = gastosresult.get(i).getTgasCosto().toString();
            VistaGastos.modelo.addRow(fila);
        }
        numerarTabla(VistaGastos.modelo);
        VistaGastos.jTextField1.setText("" + totalDeUnaTabla(VistaGastos.modelo, 3));

    }

    public void desactivarFechas(int V) {
        if (V == 1) {
            VistaGastos.jLabel7.setEnabled(false);
            VistaGastos.jLabel8.setEnabled(false);
            VistaGastos.Comp_Fecha_Desde1.setEnabled(false);
            VistaGastos.Comp_Fecha_Desde2.setEnabled(false);
            VistaGastos.jButton2.setEnabled(false);
        } else {
            VistaGastos.jLabel7.setEnabled(true);
            VistaGastos.jLabel8.setEnabled(true);
            VistaGastos.Comp_Fecha_Desde1.setEnabled(true);
            VistaGastos.Comp_Fecha_Desde2.setEnabled(true);
            VistaGastos.jButton2.setEnabled(true);
        }
    }

    public int obtenerRadiobuttonSeleccionado() {
        if (VistaGastos.jRadioButton1.isSelected() == true) {
            return 1;
        } else if (VistaGastos.jRadioButton2.isSelected() == true) {
            return 2;
        } else if (VistaGastos.jRadioButton3.isSelected() == true) {
            return 3;
        }
        return 2;
    }

    public void desactivarBotones(int v) {
        if (v == 0) {
            VistaGastos.jButton3.setEnabled(false);
            VistaGastos.jButton4.setEnabled(false);
            VistaGastos.jButton1.setEnabled(true);
        } else {
            VistaGastos.jButton3.setEnabled(true);
            VistaGastos.jButton4.setEnabled(true);
            VistaGastos.jButton1.setEnabled(false);
        }
    }
}
