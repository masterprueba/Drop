/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TLogin;
import Entity.TPersona;
import Model.Login_Model;
import Model.Persona_Model;
import UI.Usuarios_UI;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Yoimar
 */
public class Usuarios_Controller {

    private final Usuarios_UI VistaUsuarios;
    private final Login_Model LModel = new Login_Model();
    private final Persona_Model PModel = new Persona_Model();
    private final TPersona Persona = new TPersona();
    private final TLogin Login = new TLogin();
    private List<TPersona> personaresult;
    private List<TLogin> loginresult;

    public Usuarios_Controller(Usuarios_UI VistaUsuarios) {
        this.VistaUsuarios = VistaUsuarios;
    }

    public void Registrar() {
        if (Validar()) {
            if (VerificarDatosExist()) {
                if (PModel.insertar(Persona) != null) {
                    if (LModel.insertar(Login) != null) {
                        JOptionPane.showMessageDialog(null, "Se registro al nuevo usuario exitosamente!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar el login de la persona");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar el perfil de la persona");
                }
            }
        }
    }

    private boolean Validar() {
        String mensaje = "";

        if (VistaUsuarios.U_text_Identificacion.getText().equals("")) {
            mensaje += "-No se puede dejar vacia la identificacion \n";
        }
        if (VistaUsuarios.U_text_NomComplet.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el nombre \n";
        }
        if (VistaUsuarios.U_text_NomComplet1.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el apellido \n";
        }
        if (VistaUsuarios.U_text_NomUsuario.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el nombre de usuario \n";
        }
        if (new String(VistaUsuarios.U_text_Contraseña.getPassword()).equals("")) {
            mensaje += "-No se puede dejar vacia la contraseña \n";
        }
        if (new String(VistaUsuarios.U_text_ReptContraseña.getPassword()).equals("")) {
            mensaje += "-No se puede dejar vacio el campo repetir contraseña \n";
        }
        if (VistaUsuarios.U_text_Telefono.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el telefono \n";
        }
        if (VistaUsuarios.U_text_Direccion.getText().equals("")) {
            mensaje += "-No se puede dejar vacia la direccion \n";
        }
        if (!new String(VistaUsuarios.U_text_Contraseña.getPassword()).equals(new String(VistaUsuarios.U_text_ReptContraseña.getPassword()))) {
            mensaje += "-Las contraseñas introducidas no coinciden \n";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n" + mensaje);
            return false;
        }
        return true;
    }

    //Verifica que no exista la identificacion  ni el usuario en la base de datos
    private boolean VerificarDatosExist() {
        LlenarObjetosPersonaLogin();
        personaresult = PModel.ConsultarCedula(Persona);
        if (personaresult.isEmpty()) {
            //consultar si existe el  login
            loginresult = LModel.ConsultarUsuario(Login);
            //verificar si es igual (con minusculas y mayusculas)
            boolean igual = false;
            for (int i = 0; i < loginresult.size(); i++) {
                if (loginresult.get(i).getTlogUserLogin().equals(VistaUsuarios.U_text_NomUsuario.getText())) {
                    igual = true;
                    break;
                }
            }
            if (!igual) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya esta en uso, por favor intente con otro Nombre de Usuario ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario en la base de datos con ese numero de identificacion!");
        }
        return false;
    }

    public void VerUsuarios() {
        personaresult = PModel.findAll(TPersona.class);
        for (int i = 0; i < personaresult.size(); i++) {
            String[] fila = new String[6];
            fila[1] = personaresult.get(i).getTperCedula();
            fila[2] = personaresult.get(i).getTperNombre() + " " + personaresult.get(i).getTperApellido();
            fila[3] = personaresult.get(i).getTperTel();
            VistaUsuarios.modelo.addRow(fila);
        }
    }

    public void DeshabilitarHabilitar(int V) {
        switch (V) {
            case 1:
                VistaUsuarios.U_btn_Guardar.setEnabled(false);
                VistaUsuarios.U_btn_Limpiar.setEnabled(false);
                VistaUsuarios.U_btn_Registrar.setEnabled(true);
                VistaUsuarios.U_text_Identificacion.setEditable(true);
                break;
            case 2:
                VistaUsuarios.U_btn_Guardar.setEnabled(true);
                VistaUsuarios.U_btn_Limpiar.setEnabled(true);
                VistaUsuarios.U_btn_Registrar.setEnabled(false);
                VistaUsuarios.U_text_Identificacion.setEditable(false);
                break;

        }

    }

    public void TraerUsuario(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int fila = VistaUsuarios.U_jtable_VerUsuario.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                Persona.setTperCedula(VistaUsuarios.modelo.getValueAt(fila, 1).toString());
                personaresult = PModel.ConsultarCedula(Persona);
                DeshabilitarHabilitar(2);
                if (!personaresult.isEmpty()) {

                }
            }
        }
    }

    private void LlenarObjetosPersonaLogin() {
        //setear todos los datos a los objetos
        Persona.setTperCedula(VistaUsuarios.U_text_Identificacion.getText());
        Persona.setTperNombre(VistaUsuarios.U_text_NomComplet.getText());
        Persona.setTperApellido(VistaUsuarios.U_text_NomComplet1.getText());
        Persona.setTperTel(VistaUsuarios.U_text_Telefono.getText());

        Login.setTlogUserLogin(VistaUsuarios.U_text_NomUsuario.getText());
        Login.setTlogPassword(new String(VistaUsuarios.U_text_Contraseña.getPassword()));
        Login.setTPersona(Persona);
        //consultar si existe la cedula
    }
}
