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
public class Gastos_Controller {

    private final Gastos_Model MGastos = new Gastos_Model();
    private final Gastos_UI VistaGastos;
    private static TGasto Gasto;
    private List<TGasto> gastosresult;
    private final Funciones_Vistas FnVistas = new Funciones_Vistas();

    public Gastos_Controller(Gastos_UI VistaGastos) {
        this.VistaGastos = VistaGastos;
    }

    public void RegistrarGasto() {
        if (Validar()) {
            LlenarObjetoGastos();
            if (MGastos.insertar(Gasto) != null) {
                VaciarCampos();
                TraerGastos();
                JOptionPane.showMessageDialog(null, "Se ha ingresado un nuevo gasto para este mes");
            }

        }
    }

    public void CargarGastos(int Via) {

        switch (Via) {
            case 1:
                Calendar fecha = Calendar.getInstance();
                int año = fecha.get(Calendar.YEAR);
                int mes = (fecha.get(Calendar.MONTH) + 1);
                break;

            case 2:
                JOptionPane.showMessageDialog(null, "Trae todo");
                break;

            case 3:
                String fecha1 = VistaGastos.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                String fecha2 = VistaGastos.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "/" + (VistaGastos.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaGastos.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
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
        Gasto.setTgasFecha(VistaGastos.Comp_Fecha_Desde1.getDate());
        Gasto.setTgasDesc(VistaGastos.jTextArea1.getText());
        Gasto.setTgasCosto(Integer.parseInt(VistaGastos.jTextField2.getText()));
    }

    private void VaciarCampos() {
        VistaGastos.jTextArea1.setText(null);
        VistaGastos.jTextField2.setText(null);
    }

    public void TraerGastos() {
        gastosresult = MGastos.findAll(TGasto.class);
        VistaGastos.modelo.setNumRows(0);
        for (int i = 0; i < gastosresult.size(); i++) {
            String[] fila = new String[6];
            fila[1] = gastosresult.get(i).getTgasFecha() + "";
            fila[2] = gastosresult.get(i).getTgasDesc();
            fila[3] = gastosresult.get(i).getTgasCosto().toString();
            VistaGastos.modelo.addRow(fila);
        }
        FnVistas.NumerarTabla(VistaGastos.modelo);
    }
}
