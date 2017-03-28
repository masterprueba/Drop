/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBitacora;
import Entity.TLogin;
import Model.Bitacora_Model;
import UI.BitacoraSesion_UI;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITERIA
 */
public class BitSesion_Controller {

    private final Bitacora_Model mBitacora = new Bitacora_Model();
    private final BitacoraSesion_UI vistaBitacora;
    private static TBitacora tBitacora;
    private List<TBitacora> lBitacora;

    public BitSesion_Controller(BitacoraSesion_UI vistaBitacora) {
        this.vistaBitacora = vistaBitacora;
    }

    public void verBitacoraGenenar() {

        try {

            Gson gson = new Gson();
            System.err.println(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE) + "---------------------" + Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE));
            lBitacora = mBitacora.consultarFechaBitsesion(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE), Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DATE)
            );
            if (!lBitacora.isEmpty()) {
                System.out.println("Controller.BitSesion_Controller.verBitacoraGenenar()");
                switch (lBitacora.get(0).getTbitClassname()) {
                    case "Entity.TLogin":
                        TLogin login = gson.fromJson(lBitacora.get(0).getTbitRegistro(), TLogin.class);
                        break;

                }
            }
        } catch (JsonSyntaxException ex) {
            Logger.getLogger(BitSesion_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
