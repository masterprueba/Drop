/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TDatosBasicosPersona;
import Entity.TPersona;
import Entity.TReferencia;
import UI.Cliente_UI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class Codeudor_Controller extends Persona_Controller {

    private Cliente_Controller cli_Controller;

    public Codeudor_Controller(Cliente_UI cli_UI) {
        super(cli_UI);

    }

//<editor-fold defaultstate="collapsed" desc="prepare INSERT">
    public void prepareInsert() {
        if (validar()) {
            createObjectCodeudorTo();

            setDbpCodeudor(getDbp_Controller().insert(getDbpCodeudor()));

            if (getDbpCodeudor().getTdbpId() != null) {
                getpCodeudor().getTDatosBasicosPersona().setTdbpId(getDbpCodeudor().getTdbpId());

                if (insert(getpCodeudor())) {

                    if (getCli_UI().objectRefeCod.size() > 0) {
                        for (int i = 0; i < getCli_UI().objectRefeCod.size(); i++) {
                            getCli_UI().objectRefeCod.get(i).setTDatosBasicosPersona(getDbpCodeudor());

                            getRef__Controller().prepareInsert(getCli_UI().objectRefeCod.get(i));

                        }
                    }

                    cli_Controller = new Cliente_Controller(getCli_UI());
                    cli_Controller.prepareInsert(getpCodeudor());
                    cli_Controller.clean();
                    cli_Controller.enabledForEdit(false);
                    cli_Controller.colorJText(new java.awt.Color(205, 205, 255));
                }

            }
        }

    }
//</editor-fold> 

//<editor-fold defaultstate="collapsed" desc="prepare SELECT">
    public void prepareSelect(String cCliente) {
        setDbpCodeudor(new TDatosBasicosPersona());
        getDbpCodeudor().setTdbpCedula(cCliente);

        setpCodeudor(new TPersona());
        getpCodeudor().setTDatosBasicosPersona(getDbpCodeudor());
        getpCodeudor().setTperTipo("CODEUDOR");

        if (selectOne(getpCodeudor())) {
            setDataJTextCodeudor();
            getRef__Controller().prepareSelect(getpCodeudor().getTDatosBasicosPersona().getTdbpCedula(), "CODEUDOR");
        }

    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="prepare SELECT Codeudor">
    public List<TPersona>  prepareSelectCodeudor(String cCodeudor) {
        setDbpCodeudor(new TDatosBasicosPersona());
        getDbpCodeudor().setTdbpCedula(cCodeudor);

        setpCodeudor(new TPersona());
        getpCodeudor().setTDatosBasicosPersona(getDbpCodeudor());
        getpCodeudor().setTperTipo("CODEUDOR");

        return SelectCodeudor(getpCodeudor());

    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="prepare UPDATE">
    public void prepareUpdate() {
        if (validar()) {
            createObjectCodeudorTo();
            getDbpCodeudor().setTdbpId(getCli_UI().objectCodeudor.getTDatosBasicosPersona().getTdbpId());
            getpCodeudor().setTperId(getCli_UI().objectCodeudor.getTperId());

            if (getDbp_Controller().update(getDbpCodeudor())) {
                if (update(getpCodeudor())) {

                    if (getCli_UI().objectRefeCod.size() > 0) {

                        if (getRef__Controller().prepareSelect(getpCodeudor().getTDatosBasicosPersona().getTdbpCedula(), "")) {
                            List<TReferencia> temp = new ArrayList<>();
                            temp = getRef__Controller().getListRef();

                            if (temp.size() > 0) {
                                for (int j = 0; j < temp.size(); j++) {
                                    getRef__Controller().prepareDelete(temp.get(j));

                                    //System.out.println(temp.get(j).getTrefNombre());
                                }
                            }
                        }

                        for (int i = 0; i < getCli_UI().objectRefeCod.size(); i++) {
                            getCli_UI().objectRefeCod.get(i).setTDatosBasicosPersona(getDbpCodeudor());

                            getRef__Controller().prepareInsert(getCli_UI().objectRefeCod.get(i));

                        }
                    }
                    cli_Controller = new Cliente_Controller(getCli_UI());
                    cli_Controller.prepareUpdate(getpCodeudor());
                    cli_Controller.clean();
                    cli_Controller.enabledForEdit(false);
                    cli_Controller.colorJText(new java.awt.Color(205, 205, 255));
                }
            }
        }
    }
//</editor-fold>

    public boolean validar() {
        String mensaje = "";

        if (getCli_UI().jtfCedulaCliente.getText().trim().equals("")) {
            mensaje += "-Identificación de Codeudor esta vacío \n";
        }

        if (getCli_UI().jtfNombreCliente1.getText().trim().equals("")) {
            mensaje += "-Nombre de Codeudor esta vacío \n";
        }
        if (getCli_UI().jtfApellidoCliente1.getText().trim().equals("")) {
            mensaje += "-Apellido de Codeudor esta vacío \n";
        }
        if (getCli_UI().jtfCasaDirCliente.getText().trim().equals("")) {
            mensaje += "-Direccion Codeudor esta vacío \n";
        }

        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n \n" + mensaje, "Error!!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
}
