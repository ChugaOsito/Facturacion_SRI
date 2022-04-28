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
package io.rubrica.core;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * Objeto para almacenar un PrivateKey y un Certificate chain a la vez.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 * @deprecated
 */
public class PrivateKeyAndCertificateChain {

    private final String alias;
    private final PrivateKey privateKey;
    private final Certificate[] certificateChain;

    public PrivateKeyAndCertificateChain(String alias, PrivateKey privateKey, Certificate[] certificateChain) {
        this.alias = alias;
        this.privateKey = privateKey;
        this.certificateChain = certificateChain;
    }

    public String getAlias() {
        return alias;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public Certificate[] getCertificateChain() {
        return certificateChain;
    }

    public String toString() {
        return alias;
    }
}
