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
 * KeyStoreProvider para tokens Pcsc.
 *
 * @author mfernandez
 */
public class WindowsPcscKeyStoreProvider extends PKCS11KeyStoreProvider {

    private static final String CONFIG;
    private static final String DRIVER_FILE_32_BITS = "C:\\Windows\\System32\\sgdatap11.dll";
    private static final String DRIVER_FILE_64_BITS = "C:\\Windows\\System32\\sgdatap11.dll";

    static {
        StringBuilder config = new StringBuilder();
        config.append("name=Pcsc\n");
        config.append("library=").append(is64bit() ? DRIVER_FILE_64_BITS : DRIVER_FILE_32_BITS);
        CONFIG = config.toString();
    }

    @Override
    public String getConfig() {
        return CONFIG;
    }

    @Override
    public boolean existeDriver() {
        File driver = is64bit() ? new File(DRIVER_FILE_64_BITS) : new File(DRIVER_FILE_32_BITS);
        return driver.exists();
    }
}