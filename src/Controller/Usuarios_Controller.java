/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TDatosBasicosPersona;
import Entity.TLogin;
import Model.DatosBasicosPersona_Model;
import Model.Login_Model;
import UI.Usuarios_UI;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Yoimar
 */
public class Usuarios_Controller extends Controllers {

    private final Usuarios_UI VistaUsuarios;
    private final Login_Model LModel = new Login_Model();
    private final DatosBasicosPersona_Model PModel = new DatosBasicosPersona_Model();
    private static TDatosBasicosPersona persona = new TDatosBasicosPersona();
    private static TLogin Login;
    private List<TDatosBasicosPersona> personaresult;
    private boolean old = false;

    public Usuarios_Controller(Usuarios_UI VistaUsuarios) {
        this.VistaUsuarios = VistaUsuarios;
    }

    public void registrar() {
        if (validar()) {
            if (verificarDatosExist()) {
                Login = new TLogin();
                llenarObjetosPersonaLogin();
                if (old) {
                    PModel.editar(persona, "USUARIOS");
                } else {
                    PModel.insertar(persona, "USUARIOS");
                }
                if (LModel.insertar(Login, "USUARIOS") != null) {
                    JOptionPane.showMessageDialog(null, "Se registro al nuevo usuario exitosamente!");
                    verUsuarios();
                    vaciarCampos();
                    old = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar el login de la persona");
                }

            }
        }
    }

    private boolean validar() {
        String mensaje = "";

        if (VistaUsuarios.U_text_Identificacion.getText().equals("")) {
            mensaje += "-No se puede dejar vacia la identificacion \n";
        }
        if (VistaUsuarios.U_text_NomComplet.getText().equals("")) {
            mensaje += "-No se puede dejar vacio el nombre \n";
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
        if (!new String(VistaUsuarios.U_text_Contraseña.getPassword()).equals(new String(VistaUsuarios.U_text_ReptContraseña.getPassword()))) {
            mensaje += "-Las contraseñas introducidas no coinciden \n";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n" + mensaje);
            return false;
        } else {
            return true;
        }
    }

    //Verifica que no exista la identificacion  ni el usuario en la base de datos
    private boolean verificarDatosExist() {
        llenarObjetosPersonaLogin();
        //consultar si existe el  login
        Login = LModel.ConsultarUsuario(Login, 1);
        if (Login == null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya esta en uso, por favor intente con otro Nombre de Usuario ");
        }
        return false;
    }

    public void verUsuarios() {
        personaresult = PModel.ConsultarPersonasConLogin();
        VistaUsuarios.modelo.setNumRows(0);
        for (int i = 0; i < personaresult.size(); i++) {
            String[] fila = new String[6];
            fila[1] = personaresult.get(i).getTdbpCedula();
            fila[2] = personaresult.get(i).getTdbpNombre();
            fila[3] = personaresult.get(i).getTdbpTel();
            VistaUsuarios.modelo.addRow(fila);
        }
        numerarTabla(VistaUsuarios.modelo);
    }

    public void actualizarUsuario() {
        if (validar()) {
            llenarObjetosPersonaLogin();
            if (LModel.ConsultarUsuario(Login, 2) == null) {
                if (PModel.editar(persona, "USUARIOS")) {
                    if (LModel.editar(Login, "USUARIOS")) {
                        vaciarCampos();
                        verUsuarios();
                        deshabilitarHabilitar(1);
                        JOptionPane.showMessageDialog(null, "Los datos del usuario han sido actualizados exitosamente!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar los datos del login");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar los datos del usuario");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya esta en uso, por favor intente con otro Nombre de Usuario ");
            }
        }
    }

    public void deshabilitarHabilitar(int V) {
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

    public void traerUsuario(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            int fila = VistaUsuarios.U_jtable_VerUsuario.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                llenarObjetosPersonaLogin();
                persona.setTdbpCedula(VistaUsuarios.modelo.getValueAt(fila, 1).toString());
                persona = PModel.ConsultarCedula(persona);
                Login.setTDatosBasicosPersona(persona);
                Login = LModel.ConsultarUsuario(Login, 3);
                deshabilitarHabilitar(2);
                if (persona != null) {
                    VistaUsuarios.U_text_Identificacion.setText(persona.getTdbpCedula());
                    VistaUsuarios.U_text_NomComplet.setText(persona.getTdbpNombre());
                    VistaUsuarios.U_text_NomComplet1.setText(persona.getTdbpApellido());
                    VistaUsuarios.U_text_NomUsuario.setText(Login.getTlogUserLogin());
                    VistaUsuarios.U_text_Contraseña.setText(new String(Login.getTlogPassword()));
                    VistaUsuarios.U_text_ReptContraseña.setText(new String(Login.getTlogPassword()));
                    VistaUsuarios.U_text_Telefono.setText(persona.getTdbpTel());
                    VistaUsuarios.editando = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error, es posible que esta persona ya no exista en la base de datos o que no haya conexion a la base de datos");
                }
            }
        }
    }

    private void llenarObjetosPersonaLogin() {
        if (persona == null) {
            persona = new TDatosBasicosPersona();
        }
        if (Login == null) {
            Login = new TLogin();
        }
        //setear todos los datos a los objetos
        persona.setTdbpCedula(VistaUsuarios.U_text_Identificacion.getText());
        persona.setTdbpNombre(VistaUsuarios.U_text_NomComplet.getText());
        persona.setTdbpApellido(VistaUsuarios.U_text_NomComplet1.getText());
        persona.setTdbpTel(VistaUsuarios.U_text_Telefono.getText());

        Login.setTlogUserLogin(VistaUsuarios.U_text_NomUsuario.getText());
        Login.setTlogPassword(new String(VistaUsuarios.U_text_Contraseña.getPassword()));
        Login.setTDatosBasicosPersona(persona);
    }

    public void vaciarCampos() {
        VistaUsuarios.U_text_Identificacion.setText(null);
        VistaUsuarios.U_text_NomComplet.setText(null);
        VistaUsuarios.U_text_NomComplet1.setText(null);
        VistaUsuarios.U_text_NomUsuario.setText(null);
        VistaUsuarios.U_text_Contraseña.setText(null);
        VistaUsuarios.U_text_ReptContraseña.setText(null);
        VistaUsuarios.U_text_Telefono.setText(null);
        VistaUsuarios.editando = false;
    }

    public void trarDatos() {
        persona.setTdbpCedula(VistaUsuarios.U_text_Identificacion.getText());
        TDatosBasicosPersona temp = PModel.ConsultarCedula(persona);
        if (temp != null) {
            persona = temp;
            VistaUsuarios.U_text_NomComplet.setText(persona.getTdbpNombre());
            VistaUsuarios.U_text_NomComplet1.setText(persona.getTdbpApellido());
            VistaUsuarios.U_text_Telefono.setText(persona.getTdbpTel());
            old = true;
        }
    }
}
