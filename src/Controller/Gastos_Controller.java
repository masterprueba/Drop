/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TGasto;
import Model.Gastos_Model;
import UI.Gastos_UI;
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

    public void RegistrarGasto() {
        if (Validar()) {
            LlenarObjetoGastos();
            if (MGastos.insertar(Gasto) != null) {
                VaciarCampos();
                TraerGastos(ObtenerRadiobuttonSeleccionado());
                JOptionPane.showMessageDialog(null, "Se ha ingresado un nuevo gasto");
            }

        }
    }

    private boolean Validar() {
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

    private void LlenarObjetoGastos() {
        if (Gasto == null) {
            Gasto = new TGasto();
        }
        Gasto.setTgasFecha(VistaGastos.Comp_Fecha_Gasto.getDate());
        Gasto.setTgasDesc(VistaGastos.jTextArea1.getText());
        Gasto.setTgasCosto(Long.parseLong(VistaGastos.jTextField2.getText()));
    }

    private void VaciarCampos() {
        VistaGastos.jTextArea1.setText(null);
        VistaGastos.jTextField2.setText(null);
        VistaGastos.Comp_Fecha_Gasto.setDate(null);
    }

    public void TraerGastos(int V) {
        switch (V) {
            case 1:
                gastosresult = MGastos.ConsultarGastosMes((Calendar.getInstance().get(Calendar.MONTH) + 1), Calendar.getInstance().get(Calendar.YEAR));
                break;
            case 2:
                gastosresult = MGastos.findAll(TGasto.class);
                break;
            case 3:
                if (VistaGastos.Comp_Fecha_Desde1.getDate() != null && VistaGastos.Comp_Fecha_Desde2.getDate() != null) {
                    String fecha1 = VistaGastos.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                    String fecha2 = VistaGastos.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
                    gastosresult = MGastos.ConsultarPorFechas(fecha1, fecha2);
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

    public void DesactivarFechas(int V) {
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

    public int ObtenerRadiobuttonSeleccionado() {

        if (VistaGastos.jRadioButton1.isSelected() == true) {
            return 1;
        } else if (VistaGastos.jRadioButton2.isSelected() == true) {
            return 2;
        } else if (VistaGastos.jRadioButton3.isSelected() == true) {
            return 3;
        }
        return 2;
    }
}
