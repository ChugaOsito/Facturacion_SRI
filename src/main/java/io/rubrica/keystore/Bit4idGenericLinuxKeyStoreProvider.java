/* 
 * AppsHandler © 2021
 * development@appshandler.com
 * http://www.appshandler.com
 */
package io.rubrica.keystore;

import java.io.File;

/**
 * @author Edison Lomas Almeida
 */
public class Bit4idGenericLinuxKeyStoreProvider extends PKCS11KeyStoreProvider {

	private static final String CONFIG;
	private static final String DRIVER_FILE = "/usr/lib/bit4id/libbit4xpki.so";

	static {
		StringBuilder sb = new StringBuilder();
		sb.append("name=Bit4Id\n");
		sb.append("library=").append(DRIVER_FILE).append("\n");
		CONFIG = sb.toString();
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
