/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TCobrador;
import Entity.TCuota;
import Entity.TPago;
import Entity.TPrestamo;
import Entity.TReajuste;
import Model.Cobrador_Model;
import Model.Prestamo_model;
import Model.TPagos_Model;
import UI.ReajustePrestamo_UI;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author PC
 */
public class ReajustePrestamo_Controller extends Controllers {

    Prestamo_model pmodel;
    TPrestamo prestamo;
    private int dias;
    private long pagado;
    private float valorinteres;
    private float prestamointeres;

    public ReajustePrestamo_Controller() {
        pmodel = new Prestamo_model();
    }

    public boolean getPrestamo() {
        String id = ReajustePrestamo_UI.txt_id_prestamo.getText();
        Boolean resp = false;
        prestamo = (TPrestamo) pmodel.consultar(TPrestamo.class, Integer.parseInt(id));
        if (prestamo != null) {
            Set a = prestamo.getTCuotas();
            pagado = 0;
            if (a.size() > 0) {
                for (int i = 0; i < a.size(); i++) {
                    TCuota tc = (TCuota) a.toArray()[i];
                    pagado += tc.getTcuoAbono();
                }
            }
            if (pagado < prestamo.getTpreValorTotal()) {
                //Actual
                ReajustePrestamo_UI.fechaini.setDate(prestamo.getTpreFechaEntrega());
                ReajustePrestamo_UI.txt_r_vprestamo.setText(prestamo.getTpreValorPrestamo() + "");
                ReajustePrestamo_UI.txt_r_interes.setText(prestamo.getTpreIntereses() + "");
                ReajustePrestamo_UI.txt_r_tcuotas.setText(prestamo.getTpreNumCuotas() + "");
                ReajustePrestamo_UI.txt_r_tiempo.setText(prestamo.getTpreMetodPago());
                ReajustePrestamo_UI.txt_r_vpagado.setText(pagado + "");
                ReajustePrestamo_UI.txt_r_tintereses.setText(prestamo.getTpreValorTotal() - prestamo.getTpreValorPrestamo() + "");
                ReajustePrestamo_UI.txt_r_vpestamointeres.setText(prestamo.getTpreValorTotal() + "");

                //Hasta hoyº            
                ReajustePrestamo_UI.fechafin.setDate(new Date());
                dias = (int) ((new Date().getTime() - prestamo.getTpreFechaEntrega().getTime()) / 86400000);
                ReajustePrestamo_UI.txt_rhoy_vprestamo.setText(prestamo.getTpreValorPrestamo() + "");
                ReajustePrestamo_UI.txt_rhoy_interes.setText(prestamo.getTpreIntereses() + "");
                ReajustePrestamo_UI.txt_rhoy_dias.setText(dias + "");
                float mes = (float) dias / 30;
                float interes = prestamo.getTpreValorPrestamo() * ((float) prestamo.getTpreIntereses() / 100);
                valorinteres = (interes / 30) * dias;
                if (mes < 1) {
                    valorinteres = interes;
                }
                prestamointeres = prestamo.getTpreValorPrestamo() + valorinteres;
                resp = true;
            } else {
                resp = false;
            }
        } else {
            resp = false;
        }
        ReajustePrestamo_UI.txt_nombre.setText(prestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + prestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido());
        return resp;
    }

    public Serializable abonoAPagar() {
        TCuota cuota = new TCuota();
        cuota.setTcuoAbono(Math.round(Double.parseDouble(String.valueOf(ReajustePrestamo_UI.txt_vabonar.getText()))));
        cuota.setTcuoFecha(new Date());
        cuota.setTcuoCuotasPagadas(Integer.parseInt(String.valueOf(ReajustePrestamo_UI.txt_r_tcuotas.getText())));
        cuota.setTPrestamo(prestamo);
        cuota.setTcuoNuevoSaldo(Math.round(Double.parseDouble(String.valueOf(ReajustePrestamo_UI.txt_rhoy_pretamointeres.getText()))));
        TCobrador cobrador = new TCobrador();
        cobrador.setTcobNombre("Reajuste");
        TPago pago = new TPago();
        pago.setTipo("Reajuste-.");
        TCobrador cobradorTemp = null;
        TPago pagoTemp = null;
        try {
            System.err.println("try reajuste");
            cobradorTemp = new Cobrador_Model().SelectOne(cobrador);
            pagoTemp = new TPagos_Model().SelectOne(pago);
            System.out.println(cobradorTemp.toString());
            System.out.println(pagoTemp.toString());
        } catch (NullPointerException e) {
            System.err.println("catch reajuste");
            pmodel.insertar(cobrador, "prestamo");
            cobradorTemp = new Cobrador_Model().SelectOne(cobrador);
            pmodel.insertar(pago, "prestamo");
            pagoTemp = new TPagos_Model().SelectOne(pago);
        }
        cuota.setTCobrador(cobradorTemp);
        cuota.setTPago(pagoTemp);
        prestamo.setTpreValorTotal(Math.round(Double.parseDouble(String.valueOf(ReajustePrestamo_UI.txt_rhoy_pretamointeres.getText()))));
        TReajuste tr = new TReajuste();
        tr.setTPrestamo(prestamo);
        tr.setTreaFecha(new Date());
        tr.setTreaValor(Math.round(Double.parseDouble(String.valueOf(ReajustePrestamo_UI.txt_r_vpestamointeres.getText())) - (Double.parseDouble(String.valueOf(ReajustePrestamo_UI.txt_rhoy_pretamointeres.getText())))));
        return Cierre_Controller.consutarCierre(prestamo.getTpreFechaEntrega()) ? pmodel.insertReajuste(prestamo, cuota, tr) : null;
    }

    public void calcular() {
        dias = Integer.parseInt(ReajustePrestamo_UI.txt_rhoy_dias.getText());
        float mes = (float) dias / 30;
        float interes = prestamo.getTpreValorPrestamo() * ((float) prestamo.getTpreIntereses() / 100);
        valorinteres = (interes / 30) * dias;
        if (mes < 1) {
            valorinteres = interes;
        }
        prestamointeres = prestamo.getTpreValorPrestamo() + valorinteres;
        ReajustePrestamo_UI.txt_thoy_tintereses.setText(valorinteres + "");
        ReajustePrestamo_UI.txt_rhoy_pretamointeres.setText(prestamointeres + "");
        ReajustePrestamo_UI.txt_vabonar.setText((prestamointeres - pagado) + "");
    }

}
