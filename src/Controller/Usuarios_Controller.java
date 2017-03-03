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
    private static TDatosBasicosPersona Persona;
    private static TLogin Login;
    private List<TDatosBasicosPersona> personaresult;

    public Usuarios_Controller(Usuarios_UI VistaUsuarios) {
        this.VistaUsuarios = VistaUsuarios;
    }

    public void Registrar() {
        if (Validar()) {
            if (VerificarDatosExist()) {
                Persona = new TDatosBasicosPersona();
                Login = new TLogin();
                LlenarObjetosPersonaLogin();
                if (PModel.insertar(Persona) != null) {
                    if (LModel.insertar(Login) != null) {
                        JOptionPane.showMessageDialog(null, "Se registro al nuevo usuario exitosamente!");
                        VerUsuarios();
                        VaciarCampos();
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
    private boolean VerificarDatosExist() {
        LlenarObjetosPersonaLogin();
        Persona = PModel.ConsultarCedula(Persona);
        if (Persona == null) {
            //consultar si existe el  login
            Login = LModel.ConsultarUsuario(Login, 1);
            if (Login == null) {
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

    public void ActualizarUsuario() {
        if (Validar()) {
            LlenarObjetosPersonaLogin();
            if (LModel.ConsultarUsuario(Login, 2) == null) {
                if (PModel.editar(Persona)) {
                    if (LModel.editar(Login)) {
                        VaciarCampos();
                        VerUsuarios();
                        DeshabilitarHabilitar(1);
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
                LlenarObjetosPersonaLogin();
                Persona.setTdbpCedula(VistaUsuarios.modelo.getValueAt(fila, 1).toString());
                Persona = PModel.ConsultarCedula(Persona);
                Login.setTDatosBasicosPersona(Persona);
                Login = LModel.ConsultarUsuario(Login, 3);
                DeshabilitarHabilitar(2);
                if (Persona != null) {
                    VistaUsuarios.U_text_Identificacion.setText(Persona.getTdbpCedula());
                    VistaUsuarios.U_text_NomComplet.setText(Persona.getTdbpNombre());
                    VistaUsuarios.U_text_NomComplet1.setText(Persona.getTdbpApellido());
                    VistaUsuarios.U_text_NomUsuario.setText(Login.getTlogUserLogin());
                    VistaUsuarios.U_text_Contraseña.setText(new String(Login.getTlogPassword()));
                    VistaUsuarios.U_text_ReptContraseña.setText(new String(Login.getTlogPassword()));
                    VistaUsuarios.U_text_Telefono.setText(Persona.getTdbpTel());
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error, es posible que esta persona ya no exista en la base de datos o que no haya conexion a la base de datos");
                }
            }
        }
    }

    private void LlenarObjetosPersonaLogin() {
        if (Persona == null) {
            Persona = new TDatosBasicosPersona();
        }
        if (Login == null) {
            Login = new TLogin();
        }
        //setear todos los datos a los objetos
        Persona.setTdbpCedula(VistaUsuarios.U_text_Identificacion.getText());
        Persona.setTdbpNombre(VistaUsuarios.U_text_NomComplet.getText());
        Persona.setTdbpApellido(VistaUsuarios.U_text_NomComplet1.getText());
        Persona.setTdbpTel(VistaUsuarios.U_text_Telefono.getText());

        Login.setTlogUserLogin(VistaUsuarios.U_text_NomUsuario.getText());
        Login.setTlogPassword(new String(VistaUsuarios.U_text_Contraseña.getPassword()));
        Login.setTDatosBasicosPersona(Persona);
    }

    public void VaciarCampos() {
        VistaUsuarios.U_text_Identificacion.setText(null);
        VistaUsuarios.U_text_NomComplet.setText(null);
        VistaUsuarios.U_text_NomComplet1.setText(null);
        VistaUsuarios.U_text_NomUsuario.setText(null);
        VistaUsuarios.U_text_Contraseña.setText(null);
        VistaUsuarios.U_text_ReptContraseña.setText(null);
        VistaUsuarios.U_text_Telefono.setText(null);
    }
}
