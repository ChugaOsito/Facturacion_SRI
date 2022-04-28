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

import java.util.List;

/**
 * Objeto para acceder informacion legible del certificado digital
 *
 * @author mfernandez
 */
public class Documento {
    private Boolean signValidate;//validez de todas las firmas
    private Boolean docValidate;//validez de todo el documento
    private List<Certificado> certificados;//si la lista de certificados se encuentra en null, el documento no contiene firmas
    private String error;

    public Documento() {
    }

    public Documento(Boolean signValidate, Boolean docValidate, List<Certificado> certificados, String error) {
        this.signValidate = signValidate;
        this.docValidate = docValidate;
        this.certificados = certificados;
        this.error = error;
    }

    public Boolean getSignValidate() {
        return signValidate;
    }

    public void setSignValidate(Boolean signValidate) {
        this.signValidate = signValidate;
    }

    public Boolean getDocValidate() {
        return docValidate;
    }

    public void setDocValidate(Boolean docValidate) {
        this.docValidate = docValidate;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    public String getError() { return error; }

    public void setProcess(String error) { this.error = error; }


    @Override
    public String toString() {
        return "\tDocumento\n"
                + "\t[signValidate=" + signValidate + "\n"
                + "\tdocValidate=" + docValidate + "\n"
                + "\terror=" + error + "\n"
                + "\t]"
                + "\n----------";
    }

}
