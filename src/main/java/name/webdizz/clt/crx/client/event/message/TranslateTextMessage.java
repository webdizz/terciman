/**
 * 
 */
package name.webdizz.clt.crx.client.event.message;

/**
 * @author Izzet_Mustafa
 * 
 */
public class TranslateTextMessage extends Message {

	public static final String TYPE = "TranslateTextMessage";

	protected TranslateTextMessage() {
	}

	public final native String getText() /*-{
		return this.text;
	}-*/;

	public static final native TranslateTextMessage create(String text)/*-{
		return {text: text, type:'TranslateTextMessage'};
	}-*/;
}
