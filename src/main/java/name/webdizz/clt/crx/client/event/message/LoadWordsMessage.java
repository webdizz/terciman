/**
 * 
 */
package name.webdizz.clt.crx.client.event.message;

/**
 * @author Izzet_Mustafa
 * 
 */
public class LoadWordsMessage extends Message {

	public static final String TYPE = "LoadWordsMessage";

	protected LoadWordsMessage() {
	}

	public static final native LoadWordsMessage create()/*-{
		return {type:'LoadWordsMessage'};
	}-*/;
}
