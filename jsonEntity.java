-------TCuota.java-------  **TPrestamo** -- **TPersona** -- **TDatosBasicosPersona**

@Override
    public String toString() {
        String json = "{\"tcuoId\":" + tcuoId + ",";
        if (TCobrador != null) {
            json += "\"TCobrador\":{\"tcobId\":" + TCobrador.getTcobId() + ",\"tcobNombre\":\"" + TCobrador.getTcobNombre() + "\",\"TCuotas\":[]},";
        }
        if (TPago != null) {
            json += "\"TPago\":{\"tpagId\":" + TPago.getTpagId() + ",\"tipo\":\"" + TPago.getTipo() + "\",\"TCuotas\":[]},";
        }
        if (TPrestamo != null) {
            json += "\"TPrestamo\":{\"tpreId\":" + TPrestamo.getTpreId() + ",";
            if (TPrestamo.getTPersona() != null) {
                json += "\"TPersona\":{\"tperId\":" + TPrestamo.getTPersona().getTperId() + ",";
                if (TPrestamo.getTPersona().getTDatosBasicosPersona() != null) {
                    json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpCedula() + "\","
                            + "\"tdbpNombre\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpApellido() + "\","
                            + "\"tdbpTel\":\"" + TPrestamo.getTPersona().getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
                }
                json += "\"tperCasDir\":\"" + TPrestamo.getTPersona().getTperCasDir() + "\",\"tperCasPro\":\"" + TPrestamo.getTPersona().getTperCasPro() + "\","
                        + "\"tperCasTipo\":\"" + TPrestamo.getTPersona().getTperCasTipo() + "\","
                        + "\"tperEmpNom\":\"" + TPrestamo.getTPersona().getTperEmpNom() + "\",\"tperEmpDir\":\"" + TPrestamo.getTPersona().getTperEmpDir() + "\","
                        + "\"tperEmpTel\":\"" + TPrestamo.getTPersona().getTperEmpTel() + "\",\"tperTipo\":\"" + TPrestamo.getTPersona().getTperTipo() + "\","
                        + "\"tperCodeudor\":\"" + TPrestamo.getTPersona().getTperCodeudor() + "\",\"TPrestamos\":[]},";
            }
            json += " \"tpreValorPrestamo\":" + TPrestamo.getTpreValorPrestamo() + ","
                    + " \"tpreNumCuotas\":" + TPrestamo.getTpreNumCuotas() + ","
                    + " \"tpreIntereses\":" + TPrestamo.getTpreIntereses() + ","
                    + " \"tpreMetodPago\":\"" + TPrestamo.getTpreMetodPago() + "\","
                    + " \"tpreFechaEntrega\":\"" + TPrestamo.getTpreFechaEntrega() + "\","
                    + " \"tpreValorTotal\":" + TPrestamo.getTpreValorTotal() + ","
                    + " \"tpreValorCuota\":" + TPrestamo.getTpreValorCuota() + ","
                    + " \"TCuotas\":[]},";
        }
        json += "\"tcuoFecha\":\"" + tcuoFecha + "\",\"tcuoAbono\":" + tcuoAbono + ",\"tcuoNuevoSaldo\":" + tcuoNuevoSaldo + ","
                + "\"tcuoCuotasPagadas\":" + tcuoCuotasPagadas + "}";

        return json;
    }


-------TDatosBasicosPersona.java-------

    @Override
    public String toString() {
        String json = "{\"tdbpId\":" + tdbpId + ",\"tdbpCedula\":\"" + tdbpCedula + "\",\"tdbpNombre\":\"" + tdbpNombre + "\","
                + "\"tdbpApellido\":\"" + tdbpApellido + "\",\"tdbpTel\":\"" + tdbpTel + "\","
                + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]}";
        return json;
    }

-------TGasto.java-------

    @Override
    public String toString() {
        String json = "{\"tgasId\":" + tgasId + ",\"tgasDesc\":\"" + tgasDesc + "\",\"tgasFecha\":\"" + tgasFecha + "\",\"tgasCosto\":\"" + tgasCosto + "\"}";
        return json;
    }

--------TLogin.java--------  **TDatosBasicosPersona**

    @Override
    public String toString() {
        String json = "{\"tlogId\":" + tlogId + ",";
        if (TDatosBasicosPersona != null) {
            json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + TDatosBasicosPersona.getTdbpId() + ",\"tdbpCedula\":\"" + TDatosBasicosPersona.getTdbpCedula() + "\",\"tdbpNombre\":\"" + TDatosBasicosPersona.getTdbpNombre() + "\","
                    + "\"tdbpApellido\":\"" + TDatosBasicosPersona.getTdbpApellido() + "\",\"tdbpTel\":\"" + TDatosBasicosPersona.getTdbpTel() + "\","
                    + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
        }
        json += "\"tlogUserLogin\":\"" + tlogUserLogin + "\",\"tlogPassword\":\"" + tlogPassword + "\",\"TBitacoras\":[]}";

        return json;
    }

--------TPersona.java-------    **TDatosBasicosPersona**

    @Override
    public String toString() {
        String json = "{\"tperId\":" + tperId + ",";
        if (TDatosBasicosPersona != null) {
            json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + TDatosBasicosPersona.getTdbpId() + ",\"tdbpCedula\":\"" + TDatosBasicosPersona.getTdbpCedula() + "\","
                    + "\"tdbpNombre\":\"" + TDatosBasicosPersona.getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + TDatosBasicosPersona.getTdbpApellido() + "\","
                    + "\"tdbpTel\":\"" + TDatosBasicosPersona.getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
        }
        json += "\"tperCasDir\":\"" + tperCasDir + "\",\"tperCasPro\":\"" + tperCasPro + "\","
                + "\"tperCasTipo\":\"" + tperCasTipo + "\","
                + "\"tperEmpNom\":\"" + tperEmpNom + "\",\"tperEmpDir\":\"" + tperEmpDir + "\","
                + "\"tperEmpTel\":\"" + tperEmpTel + "\",\"tperTipo\":\"" + tperTipo + "\","
                + "\"tperCodeudor\":\"" + tperCodeudor + "\",\"TPrestamos\":[]}";
        return json;
    }

---------TPrestamo.java------- **TPersona** -- **TDatosBasicosPersona**

    @Override
    public String toString() {
        String json = "{\"tpreId\":" + tpreId + ",";
        if (TPersona != null) {
            json += "\"TPersona\":{\"tperId\":" + TPersona.getTperId() + ",";
            if (TPersona.getTDatosBasicosPersona() != null) {
                json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + TPersona.getTDatosBasicosPersona().getTdbpId() + ",\"tdbpCedula\":\"" + TPersona.getTDatosBasicosPersona().getTdbpCedula() + "\","
                        + "\"tdbpNombre\":\"" + TPersona.getTDatosBasicosPersona().getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + TPersona.getTDatosBasicosPersona().getTdbpApellido() + "\","
                        + "\"tdbpTel\":\"" + TPersona.getTDatosBasicosPersona().getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
            }
            json += "\"tperCasDir\":\"" + TPersona.getTperCasDir() + "\",\"tperCasPro\":\"" + TPersona.getTperCasPro() + "\","
                    + "\"tperCasTipo\":\"" + TPersona.getTperCasTipo() + "\","
                    + "\"tperEmpNom\":\"" + TPersona.getTperEmpNom() + "\",\"tperEmpDir\":\"" + TPersona.getTperEmpDir() + "\","
                    + "\"tperEmpTel\":\"" + TPersona.getTperEmpTel() + "\",\"tperTipo\":\"" + TPersona.getTperTipo() + "\","
                    + "\"tperCodeudor\":\"" + TPersona.getTperCodeudor() + "\",\"TPrestamos\":[]},";
        }
        json += " \"tpreValorPrestamo\":" + tpreValorPrestamo + ","
                + " \"tpreNumCuotas\":" + tpreNumCuotas + ","
                + " \"tpreIntereses\":" + tpreIntereses + ","
                + " \"tpreMetodPago\":\"" + tpreMetodPago + "\","
                + " \"tpreFechaEntrega\":\"" + new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.UK).format(tpreFechaEntrega) + "\","
                + " \"tpreValorTotal\":" + tpreValorTotal + ","
                + " \"tpreValorCuota\":" + tpreValorCuota + ","
                + " \"TCuotas\":[]}";
        
        return json;
    }

---------TReferencia.java---------    **TDatosBasicosPersona**

    @Override
    public String toString() {
        String json = "{\"trefId\":" + trefId + ",";
        if (TDatosBasicosPersona != null) {
            json += "\"TDatosBasicosPersona\":{\"tdbpId\":" + TDatosBasicosPersona.getTdbpId() + ",\"tdbpCedula\":\"" + TDatosBasicosPersona.getTdbpCedula() + "\","
                    + "\"tdbpNombre\":\"" + TDatosBasicosPersona.getTdbpNombre() + "\"," + "\"tdbpApellido\":\"" + TDatosBasicosPersona.getTdbpApellido() + "\","
                    + "\"tdbpTel\":\"" + TDatosBasicosPersona.getTdbpTel() + "\"," + "\"TReferencias\":[],\"TLogins\":[],\"TPersonas\":[]},";
        }
        json += "\"trefTipo\":\"" + trefTipo + "\",\"trefNombre\":\"" + trefNombre + "\",\"trefApellido\":\"" + trefApellido + "\","
                + "\"trefTelefono\":\"" + trefTelefono + "\"}";
        return json;
    }

---------TPago.java-----------


    @Override
    public String toString() {
        String json = "{\"tpagId\":" + tpagId + ",\"tipo\":\"" + tipo + "\",\"TCuotas\":[]}";

        return json;
    }

--------TCobrador.java ----------


    @Override
    public String toString() {
        String json = "{\"tcobId\":" + tcobId + ",\"tcobNombre\":\"" + tcobNombre + "\",\"TCuotas\":[]}";

        return json;
    }