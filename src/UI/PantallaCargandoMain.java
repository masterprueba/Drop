/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Persistence.hibernateUtil;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import org.hibernate.Session;

public class PantallaCargandoMain {

  Cargando screen;

  public PantallaCargandoMain() {
      Runnable Hilo = new Hilo();
     Thread  Real = new Thread(Hilo);
     Real.start();
    inicioPantalla();
	screen.velocidadDeCarga(); 
        Hilo = null;
        Real = null;
        new Login().setVisible(true);
  }
  class Hilo implements Runnable{

        @Override
        public void run() {
            Session s;
            s = hibernateUtil.getSessionFactory();
            s.beginTransaction();
            s.getTransaction().commit();
        }
      
  }

  private void inicioPantalla() {
    ImageIcon myImage = new ImageIcon(PantallaCargandoMain.class.getResource("../Icons/Cargando.gif"));
    screen = new Cargando(myImage);
    screen.setLocationRelativeTo(null);
    screen.setProgresoMax(100);
    screen.setVisible(true);
    screen.toFront();
  }

  public static void main(String[] args)
  {
    new PantallaCargandoMain();
  }
}
