/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TMulta;
import Entity.TPrestamo;
import Entity.TRefinanciacion;
import Model.Multa_Model;
import UI.ListaInteres_UI;
import UI.Multa_Ui;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class Multa_Controller extends Controllers {

    private final Multa_Model Mmulta = new Multa_Model();
    private final Multa_Ui VistaMulta;
    private static TMulta Multa;
    private List<TMulta> multaresult;
    private TPrestamo prestamo;
    private String cc;

    public Multa_Controller(Multa_Ui VistaGastos) {
        this.VistaMulta = VistaGastos;
    }

    public Multa_Controller() {
        this.VistaMulta = null;
    }        

    public void registrar() {
        if (validar()) {
            llenarObjeto();
            if (Cierre_Controller.consutarCierre(Multa.getTmulFecha())) {
                if (Mmulta.insertar(Multa, "PRESTAMO") != null) {
                    vaciarCampos();
                    traer(obtenerRadiobuttonSeleccionado());
                    JOptionPane.showMessageDialog(null, "Se ha ingresado un nuevo interes");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            }
        }
    }

    private boolean validar() {
        String mensaje = "";
        if (VistaMulta.jTextField2.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el valor \n";
        }
        if (VistaMulta.jTextArea1.getText().equals("")) {
            mensaje += "-No se puede dejar vacia la descripcion \n";
        }
        if (VistaMulta.Comp_Fecha_Gasto.getDate() == null) {
            mensaje += "-No se puede dejar vacia la fecha \n";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n" + mensaje);
            return false;
        }
        return true;
    }

    public void actualizar() {
        if (validar()) {
            llenarObjeto();
            if (Cierre_Controller.consutarCierre(Multa.getTmulFecha())) {
                if (Mmulta.editar(Multa, "PRESTAMO")) {
                    JOptionPane.showMessageDialog(null, "El interes fue editado correctamente!");
                    traer(obtenerRadiobuttonSeleccionado());
                    vaciarCampos();
                    desactivarBotones(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al editar el interes, intente nuevamente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            }
        }
    }

    public void eliminar() {
        if (validar()) {
            llenarObjeto();
            if (Cierre_Controller.consutarCierre(Multa.getTmulFecha())) {
                if (Mmulta.eliminar(Multa, "PRESTAMO")) {
                    JOptionPane.showMessageDialog(null, "El interes fue eliminado correctamente!");
                    traer(obtenerRadiobuttonSeleccionado());
                    vaciarCampos();
                    desactivarBotones(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el interes, intente nuevamente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            }
        }
    }

    private void llenarObjeto() {
        if (Multa == null) {
            Multa = new TMulta();
        }
        Multa.setTPrestamo(prestamo);
        Multa.setTmulFecha(Multa_Ui.Comp_Fecha_Gasto.getDate());
        Multa.setTmulDescripcion(Multa_Ui.jTextArea1.getText());
        Multa.setTmulValor(Integer.parseInt(Multa_Ui.jTextField2.getText()));
        Multa.setTmulEstado("realizada");
    }

    public void vaciarCampos() {
        Multa_Ui.jTextArea1.setText(null);
        Multa_Ui.jTextField2.setText(null);
        Multa_Ui.Comp_Fecha_Gasto.setDate(null);
        Multa_Ui.a_cedula.setText("");
        prestamo = null;
        cc = "";
        Multa_Ui.Comp_Fecha_Desde1.setDate(null);
        Multa_Ui.Comp_Fecha_Desde2.setDate(null);
        Multa = null;
        Multa_Ui.jPanel2.setVisible(false);
    }

    public void traerUN(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int fila = Multa_Ui.jTable1.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                Multa = (TMulta) Mmulta.consultar(TMulta.class, multaresult.get(Integer.parseInt(VistaMulta.modelo.getValueAt(fila, 0).toString()) - 1).getTmulId());
                Multa_Ui.jTextField2.setText(String.valueOf(Multa.getTmulValor()));
                Multa_Ui.jTextArea1.setText(Multa.getTmulDescripcion());
                Multa_Ui.Comp_Fecha_Gasto.setDate(Multa.getTmulFecha());
                desactivarBotones(1);
            }
        }
    }

    public void traer(int V) {
        switch (V) {
            case 1:
                multaresult = Mmulta.ConsultarMes((Calendar.getInstance().get(Calendar.MONTH) + 1), Calendar.getInstance().get(Calendar.YEAR), "and tmulDescripcion like '" + VistaMulta.jTextField3.getText() + "%' and tmulValor like '" + VistaMulta.jTextField4.getText() + "%' AND TPrestamo.TPersona.TDatosBasicosPersona.tdbpCedula = '" + cc + "'");
                break;
            case 2:
                multaresult = Mmulta.findAll(TMulta.class, "tmulDescripcion like '" + VistaMulta.jTextField3.getText() + "%' and tmulValor like '" + VistaMulta.jTextField4.getText() + "%' AND TPrestamo.TPersona.TDatosBasicosPersona.tdbpCedula = " + cc);
                break;
            case 3:
                if (VistaMulta.Comp_Fecha_Desde1.getDate() != null && VistaMulta.Comp_Fecha_Desde2.getDate() != null) {
                    String fecha1 = VistaMulta.Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + "/" + (VistaMulta.Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaMulta.Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay();
                    String fecha2 = VistaMulta.Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + "/" + (VistaMulta.Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "/" + VistaMulta.Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay();
                    multaresult = Mmulta.ConsultarPorFechas(fecha1, fecha2, "and tmulDescripcion like '" + VistaMulta.jTextField3.getText() + "%' and tmulValor like '" + VistaMulta.jTextField4.getText() + "%' AND TPrestamo.TPersona.TDatosBasicosPersona.tdbpCedula = " + cc);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione la fecha de inicio y la fecha final");
                }
                break;
        }
        VistaMulta.modelo.setNumRows(0);
        for (int i = 0; i < multaresult.size(); i++) {
            String[] fila = new String[6];
            fila[1] = multaresult.get(i).getTmulFecha().toString();
            fila[2] = multaresult.get(i).getTmulDescripcion();
            fila[3] = String.valueOf(multaresult.get(i).getTmulValor());
            fila[4] = multaresult.get(i).getTPrestamo().getTpreId().toString();
            fila[5] = multaresult.get(i).getTmulId().toString();
            VistaMulta.modelo.addRow(fila);
        }
        numerarTabla(VistaMulta.modelo);
        Multa_Ui.jTextField1.setText("" + totalDeUnaTabla(VistaMulta.modelo, 3));
        int[] position = {5};
        setVisibleColumnTable(Multa_Ui.jTable1, position);
    }

    public void desactivarFechas(int V) {
        if (V == 1) {
            Multa_Ui.jLabel7.setEnabled(false);
            Multa_Ui.jLabel8.setEnabled(false);
            Multa_Ui.Comp_Fecha_Desde1.setEnabled(false);
            Multa_Ui.Comp_Fecha_Desde2.setEnabled(false);
            Multa_Ui.jButton6.setEnabled(false);
        } else {
            Multa_Ui.jLabel7.setEnabled(true);
            Multa_Ui.jLabel8.setEnabled(true);
            Multa_Ui.Comp_Fecha_Desde1.setEnabled(true);
            Multa_Ui.Comp_Fecha_Desde2.setEnabled(true);
            Multa_Ui.jButton6.setEnabled(true);
        }
    }

    public int obtenerRadiobuttonSeleccionado() {
        if (VistaMulta.jRadioButton1.isSelected() == true) {
            return 1;
        } else if (VistaMulta.jRadioButton2.isSelected() == true) {
            return 2;
        } else if (VistaMulta.jRadioButton3.isSelected() == true) {
            return 3;
        }
        return 2;
    }

    public void desactivarBotones(int v) {
        if (v == 0) {
            Multa_Ui.jButton3.setEnabled(false);
            Multa_Ui.jButton4.setEnabled(false);
            Multa_Ui.jButton7.setEnabled(false);
            Multa_Ui.jButton3.setEnabled(true);
        } else {
            Multa_Ui.jButton3.setEnabled(true);
            Multa_Ui.jButton4.setEnabled(true);
            Multa_Ui.jButton7.setEnabled(true);
            Multa_Ui.jButton3.setEnabled(false);
        }
    }

    public boolean aceptar() {
        cc = Multa_Ui.a_cedula.getText();
        prestamo = (TPrestamo) Mmulta.getPrestamo(cc);
        return prestamo != null;
    }

    public boolean eliminar(String id) {
        TMulta multaTemp = (TMulta) Mmulta.consultar(TMulta.class, Integer.parseInt(id));
        multaTemp.setTmulEstado("eliminado");
        Boolean editado = Mmulta.editar(multaTemp, "PRESTAMO");
        traer(1);
        return editado;
    }
    
    public void listar(){
        List<TRefinanciacion> refinanceos = Mmulta.findAll(TRefinanciacion.class);
        DefaultTableModel d = new TableModel().verListadoIntereses();
        for (TRefinanciacion refinanceo : refinanceos) {
            Object[] o= new Object[7];
            o[0] = refinanceo.getTrefiId();
            o[1] = refinanceo.getTrefiIdprestamor();
            TPrestamo p = (TPrestamo) Mmulta.consultar(TPrestamo.class, refinanceo.getTrefiIdprestamor());
            o[2] = p.getTPersona().getTDatosBasicosPersona().getTdbpNombre()+" "+p.getTPersona().getTDatosBasicosPersona().getTdbpApellido();
            o[3] = refinanceo.getTrefiValor();
            o[4] = refinanceo.getTrefiIdprestamoxr();
            TPrestamo pxr = (TPrestamo) Mmulta.consultar(TPrestamo.class, refinanceo.getTrefiIdprestamoxr());
            o[5] = pxr.getTPersona().getTDatosBasicosPersona().getTdbpNombre()+" "+pxr.getTPersona().getTDatosBasicosPersona().getTdbpApellido();
            o[6] = refinanceo.getTrefFecha();
            d.addRow(o);
        }        
        ListaInteres_UI.tabla_estra.setModel(d);                
    }
    public void filter(JTable jt, String textBuscar, int columna) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jt.getModel());
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscar, columna));
        jt.setRowSorter(tr);
    }
}
