/**
 * 
 */
package name.webdizz.clt.crx.client.event.message;

/**
 * Base message to communicate within extension.
 * 
 * @author Izzet_Mustafa
 * 
 */
public class Message extends com.google.gwt.chrome.crx.client.events.Message {

	protected Message() {
	}

	public final native String getType() /*-{
		return this.type;
	}-*/;
}
