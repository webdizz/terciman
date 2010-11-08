/**
 * 
 */
package name.webdizz.clt.crx.client;

import com.google.gwt.chrome.crx.client.Chrome;
import com.google.gwt.chrome.crx.client.LocalStorage;

/**
 * @author Izzet_Mustafa
 * 
 */
public class ExtConfiguration {

	private final static LocalStorage LOCAL_STORAGE = Chrome.getExtension()
			.getBackgroundPage().getLocalStorage();

	public enum Configuration {

		/**
		 * The destination language.
		 */
		DEST_LANGUAGE, MODE
	}

	public String getDestLanguage() {
		return LOCAL_STORAGE.getItem(Configuration.DEST_LANGUAGE.name());
	}

	public void setDestLanguage(String value) {
		LOCAL_STORAGE.setItem(Configuration.DEST_LANGUAGE.name(), value);
	}

	public boolean isDebug() {
		return Boolean
				.valueOf(LOCAL_STORAGE.getItem(Configuration.MODE.name()));
	}

	public void setDebug(boolean value) {
		LOCAL_STORAGE.setItem(Configuration.MODE.name(), Boolean.valueOf(value)
				.toString());
	}

}