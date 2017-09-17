/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCierre;
import Model.Cierre_Model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ITERIA
 */
public final class Cierre_Controller {

    private static final Cierre_Model MODELO = new Cierre_Model();
    private static List<TCierre> listCierres;

    public static void consultarCierre() {
        listCierres = MODELO.consultarAllCierre();
    }

    public static boolean consutarCierre(Date date) {
        TCierre fecha = new TCierre(Integer.parseInt(new SimpleDateFormat("MM").format(date)), Integer.parseInt(new SimpleDateFormat("yyyy").format(date)));
        return listCierres.stream().noneMatch((a) -> a.getTciAno() == fecha.getTciAno() && a.getTciMes() == fecha.getTciMes());
    }
}
