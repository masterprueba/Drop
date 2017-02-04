/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TLogin;
import Model.Login_Model;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Login_Controller {

    private JTextField user;
    private JPasswordField pass;
    private final Login_Model Lmodel = new Login_Model();

    public void setUser(JTextField user) {
        this.user = user;
    }

    public void setPass(JPasswordField pass) {
        this.pass = pass;
    }

    //Verifica que el usuario y contraseña esnten la DB
    public int Ingresar() {
        List<TLogin> login = Lmodel.findAll(TLogin.class);
        for (int i = 0; i < login.size(); i++) {
            if (user.getText().equals(login.get(i).getTlogUserLogin()) && Arrays.toString(pass.getPassword()).equals(login.get(i).getTlogPassword())) {
                return login.get(i).getTlogId();
            }
        }
        return 0;
    }
}
