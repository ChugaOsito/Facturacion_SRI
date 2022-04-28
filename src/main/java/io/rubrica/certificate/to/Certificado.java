/*
 * Copyright (C) 2020 
 * Authors: Ricardo Arguello, Misael Fernández
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.*
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.rubrica.certificate.to;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Objeto para acceder informacion legible del certificado digital
 *
 * @author mfernandez
 */
public class Certificado {

    private String issuedTo;//DInformación del firmante
    private String issuedBy;//Información de la entidad certificadora
    private Calendar validFrom;//certificado digital válido desde
    private Calendar validTo;//certificado digital válido hasta
    private Calendar generated;//fecha de firmar del documento
    private Calendar revocated;//fecha de revocado del certificado digital
    private Boolean validated;//validación del certificado en las fecha de vigencia
    private String keyUsages;//llaves de uso
    private DatosUsuario datosUsuario;
    private Boolean signVerify;//Integridad Firma
    private Date docTimeStamp;//Estampa de tiempo
    private String docReason;//Razón del documento
    private String docLocation;//Localización del documento

    public Certificado() {
    }

    public Certificado(String issuedTo, String issuedBy, Calendar validFrom, Calendar validTo, Calendar generated, Calendar revocated, Boolean validated, DatosUsuario datosUsuario) {
        this.issuedTo = issuedTo;
        this.issuedBy = issuedBy;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.generated = generated;
        this.revocated = revocated;
        this.validated = validated;
        this.datosUsuario = datosUsuario;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Calendar getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Calendar validFrom) {
        this.validFrom = validFrom;
    }

    public Calendar getValidTo() {
        return validTo;
    }

    public void setValidTo(Calendar validTo) {
        this.validTo = validTo;
    }

    public Calendar getGenerated() {
        return generated;
    }

    public void setGenerated(Calendar generated) {
        this.generated = generated;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getKeyUsages() {
        return keyUsages;
    }

    public void setKeyUsages(String keyUsages) {
        this.keyUsages = keyUsages;
    }

    public Calendar getRevocated() {
        return revocated;
    }

    public void setRevocated(Calendar revocated) {
        this.revocated = revocated;
    }

    public DatosUsuario getDatosUsuario() {
        return datosUsuario;
    }

    public Boolean getSignVerify() {
        return signVerify;
    }

    public void setSignVerify(Boolean signVerify) {
        this.signVerify = signVerify;
    }

    public Date getDocTimeStamp() {
        return docTimeStamp;
    }

    public void setDocTimeStamp(Date docTimeStamp) {
        this.docTimeStamp = docTimeStamp;
    }

    public String getDocReason() {
        return docReason;
    }

    public void setDocReason(String docReason) {
        this.docReason = docReason;
    }

    public String getDocLocation() {
        return docLocation;
    }

    public void setDocLocation(String docLocation) {
        this.docLocation = docLocation;
    }

    public void setDatosUsuario(DatosUsuario datosUsuario) {
        this.datosUsuario = datosUsuario;
    }

    @Override
    public String toString() {
        return "\tCertificado\n"
                + "\t[issuedTo=" + issuedTo + "\n"
                + "\tissuedBy=" + issuedBy + "\n"
                + "\tvalidFrom=" + (validFrom == null ? null : (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((validFrom.getTime()))) + "\n"
                + "\tvalidTo=" + (validTo == null ? null : (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((validTo.getTime()))) + "\n"
                + "\tgenerated=" + (generated == null ? null : (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((generated.getTime()))) + "\n"
                + "\trevocated=" + (revocated == null ? null : (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((revocated.getTime()))) + "\n"
                + "\tvalidated=" + validated + "\n"
                + "\tkeyUsages=" + keyUsages + "\n"
                + "\tdocTimeStamp=" + (docTimeStamp == null ? null : (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((docTimeStamp))) + "\n"
                + "\tsignVerify=" + signVerify + "\n"
                + "\tdocReason=" + docReason + "\n"
                + "\tdocLocation=" + docLocation + "\n"
                + "\t" + (datosUsuario == null ? "\tDatosUsuario[Sin información de usuario]" : datosUsuario.toString()) + "\n"
                + "\t]"
                + "\n----------";
    }

}
