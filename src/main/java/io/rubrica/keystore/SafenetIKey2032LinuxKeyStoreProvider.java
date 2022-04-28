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
package io.rubrica.keystore;

import java.io.File;

/**
 * Implementacion de <code>KeyStoreProvider</code> para utilizar con librerias
 * PKCS#11 de SafeNet, instaladas previamente.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class SafenetIKey2032LinuxKeyStoreProvider extends PKCS11KeyStoreProvider {

    private static final String CONFIG;
    private static final String DRIVER_FILE = "/usr/local/SafeNet/lib/libsfntpkcs11.so";

    static {
        StringBuilder config = new StringBuilder();
        config.append("name=SafenetIKey2032\n");
        config.append("library=" + DRIVER_FILE);
        CONFIG = config.toString();
    }

    @Override
    public String getConfig() {
        return CONFIG;
    }

    @Override
    public boolean existeDriver() {
        File driver = new File(DRIVER_FILE);
        return driver.exists();
    }
}
