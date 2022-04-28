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
public class UKCGenericAppleKeyStoreProvider extends PKCS11KeyStoreProvider {

	private static final String CONFIG;
	private static final String DRIVER_FILE = "/Applications/UniversalKeychain/UniversalKeychain.app/Contents/Resources/pkcs11/libbit4p11.so";

	static {
		StringBuilder config = new StringBuilder();
		config.append("name=UKCGeneric\n");
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
