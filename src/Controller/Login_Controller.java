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
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yoimar
 */
public class Login_Controller extends Thread {

    private final Login_Model Lmodel = new Login_Model();
    private static TLogin USUARIO_LOGEADO;
    private List<TLogin> loginresult;
    private boolean cone = false;
    public static boolean continuar = true;

    @Override
    public void run() {
        new Hilo().start();
        int contador = 0;
        long velocidad = 1000;
        while (continuar) {
            if (cone == false) {
                if (contador < 95) {
                    Login.jProgressBar1.setValue(contador = contador + 4);
                    contador++;
                } else {
                    JOptionPane.showMessageDialog(null, "Se ha superado el tiempo maximo de espera!,  intente nuevamente", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            } else if (cone) {
                velocidad = 5;
                Login.jProgressBar1.setValue(contador = contador + 3);
            }
            try {
                sleep(velocidad);
            } catch (InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (contador >= 100) {
                continuar = false;
            }
        }
        Login.jLabel5.setVisible(false);
        if (cone) {
            Login.jPanel1.setVisible(true);
        }
    }

    class Hilo extends Thread {

        @Override
        public void run() {
            carga();
        }
    }

    public void carga() {
        if (Lmodel.conexion()) {
            cone = true;
        }
    }

    public static TLogin getUsuarioLogueado() {
        return USUARIO_LOGEADO;
    }

    public void Ingresar(TLogin User, Login login) {

        loginresult = Lmodel.ConsultarUsuarioContraseña(User);
        if (!loginresult.isEmpty()) {
            boolean Continua = false;
            for (int i = 0; i < loginresult.size(); i++) {
                if (loginresult.get(i).getTlogUserLogin().equals(User.getTlogUserLogin()) || loginresult.get(i).getTlogPassword().equals(User.getTlogPassword())) {
                    Continua = true;
                    USUARIO_LOGEADO = loginresult.get(i);
                    Lmodel.bitacora(USUARIO_LOGEADO, "INICIO", "LOGIN");
                    new MainDesktop().setVisible(true);
                    login.dispose();
                    break;
                }
            }
            if (!Continua) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
