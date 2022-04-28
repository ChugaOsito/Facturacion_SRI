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
package io.rubrica.utils;

import java.util.logging.Logger;

/**
 * Utilidades varias.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class OsUtils {

    private static final Logger logger = Logger.getLogger(OsUtils.class.getName());

    public static boolean isWindows() {
        String osName = System.getProperty("os.name");
        logger.finer("Operating System:" + osName);
        return (osName.toUpperCase().indexOf("WINDOWS") == 0);
    }

    public static boolean isMac() {
        String osName = System.getProperty("os.name");
        logger.finer("Operating System:" + osName);
        return osName.toUpperCase().contains("MAC");
    }

    public static String getOs() {
        String osName = System.getProperty("os.name");
        logger.finer("Operating System:" + osName);
        return osName.toUpperCase();
    }

    public static String getJavaVersion() {
        String javaVersion = System.getProperty("java.version");
        logger.finer("Java Version:" + javaVersion);
        return javaVersion.toUpperCase();
    }

    public static boolean is64Bits() {
        return System.getProperty("os.arch").equals("xmd64");
    }
}
