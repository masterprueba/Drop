/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TLogin;
import Model.Login_Model;

import UI.Login;
import UI.MainDesktop;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Yoimar
 */
public class Login_Controller {

    private final Login_Model Lmodel = new Login_Model();
    private static TLogin UsuarioLogueado;
    private List<TLogin> loginresult;

    public Login_Controller() {
    }

    //Verifica que el usuario y contraseña esnten la DB
    public void Ingresar(TLogin User, Login login) {
        loginresult = Lmodel.ConsultarUsuarioContraseña(User);
        if (!loginresult.isEmpty()) {
            boolean Continua = false;
            for (int i = 0; i < loginresult.size(); i++) {
                if (loginresult.get(i).getTlogUserLogin().equals(User.getTlogUserLogin()) && loginresult.get(i).getTlogPassword().equals(User.getTlogPassword())) {
                    Continua = true;
                    UsuarioLogueado = loginresult.get(i);
                    new MainDesktop().setVisible(true);
                    login.dispose();
                    break;
                }
            }           
            if (!Continua) {
                JOptionPane.showMessageDialog(null, "Error Usuario o Contraseña incorrectos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error Usuario o Contraseña incorrectos");
        }

    }
}
