/**
 * 
 */
package name.webdizz.clt.crx.client;

import com.google.gwt.chrome.crx.client.ExtensionScript;
import com.google.gwt.chrome.crx.client.ExtensionScript.ManifestInfo;

/**
 * Stab to load Google Language API.
 * 
 * @author Izzet_Mustafa
 * 
 */
@ManifestInfo(path = "http://google.com/jsapi", script = "google.load(\"language\", \"1\");")
public class LanguageApiProvider extends ExtensionScript {

}
