/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Entity.TRemanente;
import Model.Remanente_Modelo;
import UI.Remanente_UI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Remanente_Controller extends Controllers {

    private final Remanente_UI vista;
    private final Remanente_Modelo modelo;
    private List<TRemanente> listaRemanentes;
    private TRemanente remanente;
    private List<TCobrador> cobradores;

    public Remanente_Controller(Remanente_UI vista) {
        cobradores = new ArrayList<>();
        this.vista = vista;
        modelo = new Remanente_Modelo();
        cargarCombo();
    }

    private void cargarCombo() {
        modelo.findAll(TCobrador.class).stream().filter((t) -> {
            return !((TCobrador) t).getTcobNombre().equals("REFINANCIACION") && !((TCobrador) t).getTcobNombre().equals("Reajuste");
        }).forEach((c) -> {
            vista.jComboBox3.addItem(((TCobrador) c).getTcobNombre());
            cobradores.add((TCobrador) c);
        });
    }

    public void guardar() {
        if (validar()) {
            llenarObjetoRemanente();
            if (Cierre_Controller.consutarCierre(remanente.getTreFecha())) {
                if (!verificarExistencia(remanente.getTreFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                    if (modelo.insertar(remanente, "REMANENTE") != null) {
                        vaciarCampos();
                        // traerRemanentes(vista.jComboBox1.getSelectedIndex());
                        JOptionPane.showMessageDialog(null, "Se ha ingresado el remanente correctamente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya se ha ingresado un remanente para este dia!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            }
        }
    }

    public void cargarUnRemanente(Integer id) {
        remanente = listaRemanentes.stream().filter(a -> a.getTreId().equals(id)).findFirst().get();
        vista.Comp_Fecha.setDate(remanente.getTreFecha());
        vista.jTextField1.setText(remanente.getTreValor() + "");
        vista.jComboBox3.removeAllItems();
        int cont = 0;
        for (int i = 0; i < cobradores.size(); i++) {
            if (cobradores.get(i).getTcobId().equals(remanente.getTCobrador().getTcobId())) {
                vista.jComboBox3.addItem(cobradores.get(i).getTcobNombre());
                vista.jComboBox3.setSelectedIndex((cont));
            } else {
                vista.jComboBox3.addItem(cobradores.get(i).getTcobNombre());
            }
            cont++;
        }
    }

    public boolean actualizarRemanente() {
        if (validar()) {
            llenarObjetoRemanente();
            if (Cierre_Controller.consutarCierre(remanente.getTreFecha())) {
                if (modelo.editar(remanente, "REMANENTE")) {
                    JOptionPane.showMessageDialog(null, "El remanente fue editado correctamente!");
                    vaciarCampos();
                    traerRemanentes(vista.jComboBox1.getSelectedIndex());
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al editar el remanente, intente nuevamente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede guardar o modificar datos en un mes al que se le realizo cierre");
            }
        }
        return false;
    }

    private boolean validar() {
        String mensaje = "";
        if (vista.jTextField1.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el valor del remanente \n";
        }
        if (vista.Comp_Fecha.getDate() == null) {
            mensaje += "-No se puede dejar vacia la fecha del remanente \n";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se presentaron los siguientes inconvenientes: \n" + mensaje);
            return false;
        }
        return true;
    }

    private void llenarObjetoRemanente() {
        if (remanente == null) {
            remanente = new TRemanente();
        }
        remanente.setTreFecha(vista.Comp_Fecha.getDate());
        remanente.setTreValor(Long.valueOf(vista.jTextField1.getText()));
        remanente.setTCobrador(cobradores.stream().filter(a -> a.getTcobNombre().equals(vista.jComboBox3.getSelectedItem().toString())).findFirst().get());
    }

    private void vaciarCampos() {
        remanente = null;
        vista.jTextField1.setText(null);
        vista.Comp_Fecha.setDate(null);
    }

    private boolean verificarExistencia(LocalDate fecha) {
        return modelo.comprobarExistencia(fecha.getDayOfMonth(), fecha.getMonth().getValue(), fecha.getYear()) != null;
    }

    public void traerRemanentes(int v) {
        vista.model.setRowCount(0);
        Consumer consumer = (a) -> {
            TRemanente x = (TRemanente) a;
            vista.model.addRow(new String[]{x.getTreId().toString(), x.getTreFecha().toString(), x.getTCobrador().getTcobNombre(), "" + x.getTreValor()});
        };

        switch (v) {
            case 0:
                listaRemanentes = modelo.traerRemanentes("MONTH(remanente.treFecha) = " + (vista.jComboBox2.getSelectedIndex() + 1));
                listaRemanentes.stream().forEach(consumer);
                break;
            case 1:
                if (vista.Comp_Fecha_Desde1.getDate() != null && vista.Comp_Fecha_Desde2.getDate() != null) {
                    String fecIni = vista.Comp_Fecha_Desde1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String fecFin = vista.Comp_Fecha_Desde2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    listaRemanentes = modelo.traerRemanentes("remanente.treFecha BETWEEN '" + fecIni + "' AND '" + fecFin + "'");
                    listaRemanentes.stream().forEach(consumer);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de inicio y una fecha de fin");
                }
                break;
            case 2:
                listaRemanentes = modelo.findAll(TRemanente.class);
                listaRemanentes.stream().forEach(consumer);
                break;
        }
    }
}
