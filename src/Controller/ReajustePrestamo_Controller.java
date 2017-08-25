/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCuota;
import Entity.TPrestamo;
import Model.Prestamo_model;
import UI.ReajustePrestamo_UI;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ReajustePrestamo_Controller extends Controllers {

    public void getPrestamo() {
        String id = ReajustePrestamo_UI.txt_id_prestamo.getText();
        //if(id.)
        TPrestamo prestamo = (TPrestamo) new Prestamo_model().consultar(TPrestamo.class, Integer.parseInt(id));
        if (prestamo != null) {
            Set a = prestamo.getTCuotas();
            long pagado = 0;
            if (a.size() > 0) {                
                for (int i = 0; i < a.size(); i++) {
                    TCuota tc = (TCuota) a.toArray()[i];
                    pagado += tc.getTcuoAbono();
                }
            }
            //Actual
                ReajustePrestamo_UI.txt_r_vprestamo.setText(prestamo.getTpreValorPrestamo() + "");
                ReajustePrestamo_UI.txt_r_interes.setText(prestamo.getTpreIntereses() + "");
                ReajustePrestamo_UI.txt_r_tcuotas.setText(prestamo.getTpreNumCuotas() + "");
                ReajustePrestamo_UI.txt_r_tiempo.setText(prestamo.getTpreMetodPago());
                ReajustePrestamo_UI.txt_r_vpagado.setText(pagado + "");
                ReajustePrestamo_UI.txt_r_tintereses.setText(prestamo.getTpreValorTotal() - prestamo.getTpreValorPrestamo() + "");
                ReajustePrestamo_UI.txt_r_vpestamointeres.setText(prestamo.getTpreValorTotal() + "");  
                
            //Hasta hoy
                ReajustePrestamo_UI.txt_rhoy_vprestamo.setText(prestamo.getTpreValorPrestamo() + "");
                ReajustePrestamo_UI.txt_rhoy_interes.setText(prestamo.getTpreIntereses() + "");
                //ReajustePrestamo_UI.txt_r_tcuotas.setText(prestamo.getTpreNumCuotas() + "");
                //ReajustePrestamo_UI.txt_r_tiempo.setText(prestamo.getTpreMetodPago());
                ReajustePrestamo_UI.txt_rhoy_vpagado.setText(pagado + "");
                //ReajustePrestamo_UI.txt_r_tintereses.setText(prestamo.getTpreValorTotal() - prestamo.getTpreValorPrestamo() + "");
                //ReajustePrestamo_UI.txt_r_vpestamointeres.setText(prestamo.getTpreValorTotal() + "");  
        } else {
            JOptionPane.showMessageDialog(null, "No existe prestamo");
        }

    }
    
    public int abonoAPagar(){
        return 0;
    }

}
