/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.rubrica.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import io.rubrica.certificate.to.Certificado;
import io.rubrica.certificate.to.Documento;
import java.text.SimpleDateFormat;

/**
 *
 * @author mfernandez
 */
public class Json {

    private static final SimpleDateFormat simpleDateFormatISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static String GenerarJsonDocumento(Documento documento) {
        //creacion del JSON
        com.google.gson.JsonArray gsonArray = new com.google.gson.JsonArray();
        com.google.gson.JsonObject jsonObjectDocumento = null;
        jsonObjectDocumento = new com.google.gson.JsonObject();
        jsonObjectDocumento.addProperty("signValidate", documento.getSignValidate());
        jsonObjectDocumento.addProperty("docValidate", documento.getDocValidate());
        jsonObjectDocumento.addProperty("error", documento.getError());

        //Arreglo de Certificado(s)
        com.google.gson.JsonArray jsonDocumentoArray = new com.google.gson.JsonArray();
        com.google.gson.JsonObject jsonObjectCertificado = null;
        for (Certificado certificado : documento.getCertificados()) {
            jsonObjectCertificado = new com.google.gson.JsonObject();
            jsonObjectCertificado.addProperty("issuedTo", certificado.getIssuedTo());
            jsonObjectCertificado.addProperty("issuedBy", certificado.getIssuedBy());
            jsonObjectCertificado.addProperty("validFrom", simpleDateFormatISO8601.format(certificado.getValidFrom().getTime()));
            jsonObjectCertificado.addProperty("validTo", simpleDateFormatISO8601.format(certificado.getValidTo().getTime()));
            jsonObjectCertificado.addProperty("generated", simpleDateFormatISO8601.format(certificado.getGenerated().getTime()));
            if (certificado.getRevocated() != null) {
                jsonObjectCertificado.addProperty("revocated", simpleDateFormatISO8601.format(certificado.getRevocated().getTime()));
            }
            jsonObjectCertificado.addProperty("validated", certificado.getValidated());
            jsonObjectCertificado.addProperty("keyUsages", certificado.getKeyUsages());
            if (certificado.getDocTimeStamp() != null) {
                jsonObjectCertificado.addProperty("docTimeStamp", simpleDateFormatISO8601.format(certificado.getDocTimeStamp()));
            }
            jsonObjectCertificado.addProperty("signVerify", certificado.getSignVerify());
            jsonObjectCertificado.addProperty("docReason", certificado.getDocReason());
            jsonObjectCertificado.addProperty("docLocation", certificado.getDocLocation());
            jsonObjectCertificado.add("datosUsuario", new JsonParser().parse(new Gson().toJson(certificado.getDatosUsuario())).getAsJsonObject());
            jsonDocumentoArray.add(jsonObjectCertificado);
        }
        jsonObjectDocumento.add("certificado", new com.google.gson.JsonParser()
                .parse(new com.google.gson.Gson().toJson(jsonDocumentoArray)).getAsJsonArray());
        gsonArray.add(jsonObjectDocumento);
        return jsonDocumentoArray.toString();
    }

    public static String GenerarJsonCertificado(Certificado certificado) {
        //creacion del JSON
        com.google.gson.JsonArray gsonArray = new com.google.gson.JsonArray();
        com.google.gson.JsonObject jsonObjectCertificado = null;
        jsonObjectCertificado = new com.google.gson.JsonObject();
        jsonObjectCertificado.addProperty("issuedTo", certificado.getIssuedTo());
        jsonObjectCertificado.addProperty("issuedBy", certificado.getIssuedBy());
        jsonObjectCertificado.addProperty("validFrom", simpleDateFormatISO8601.format(certificado.getValidFrom().getTime()));
        jsonObjectCertificado.addProperty("validTo", simpleDateFormatISO8601.format(certificado.getValidTo().getTime()));
        if (certificado.getGenerated() != null) {
            jsonObjectCertificado.addProperty("generated", simpleDateFormatISO8601.format(certificado.getGenerated().getTime()));
        }
        if (certificado.getRevocated() != null) {
            jsonObjectCertificado.addProperty("revocated", simpleDateFormatISO8601.format(certificado.getRevocated().getTime()));
        }
        jsonObjectCertificado.addProperty("validated", certificado.getValidated());
        jsonObjectCertificado.addProperty("keyUsages", certificado.getKeyUsages());
        if (certificado.getDocTimeStamp() != null) {
            jsonObjectCertificado.addProperty("docTimeStamp", simpleDateFormatISO8601.format(certificado.getDocTimeStamp()));
        }
        jsonObjectCertificado.addProperty("signVerify", certificado.getSignVerify());
        jsonObjectCertificado.addProperty("docReason", certificado.getDocReason());
        jsonObjectCertificado.addProperty("docLocation", certificado.getDocLocation());
        jsonObjectCertificado.add("datosUsuario", new JsonParser().parse(new Gson().toJson(certificado.getDatosUsuario())).getAsJsonObject());
        gsonArray.add(jsonObjectCertificado);
        return gsonArray.toString();
    }

}
