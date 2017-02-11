/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import UI.Gastos_UI;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author ITERIA
 */
public class Gastos_Controller {

    private final Gastos_UI VistaGastos;

    public Gastos_Controller(Gastos_UI VistaGastos) {
        this.VistaGastos = VistaGastos;
    }

    public void RegistrarGasto() {
        if (Validar()) {

        }
    }

    public void CargarGastos(int Via) {

        switch (Via) {
            case 1:
                Calendar fecha = Calendar.getInstance();
                int año = fecha.get(Calendar.YEAR);
                int mes = (fecha.get(Calendar.MONTH) + 1);
                JOptionPane.showMessageDialog(null, "Trae las de este mes " + mes);
                break;

            case 2:
                JOptionPane.showMessageDialog(null, "Trae todo");
                break;

            case 3:
                String fecha1 = VistaGastos.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                String fecha2 = VistaGastos.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
                JOptionPane.showMessageDialog(null, "Trae lo del filtro fecha 1: " + fecha1 + "  FEcha 2" + fecha2);
                break;
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
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n" + mensaje);
            return false;
        }
        return true;
    }
}
