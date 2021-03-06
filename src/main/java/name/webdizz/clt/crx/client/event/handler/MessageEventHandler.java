/**
 * 
 */
package name.webdizz.clt.crx.client.event.handler;

import name.webdizz.clt.crx.client.ExtEventBus;
import name.webdizz.clt.crx.client.event.message.LoadWordsMessage;
import name.webdizz.clt.crx.client.event.message.Message;
import name.webdizz.clt.crx.client.event.message.SelectTextMessage;
import name.webdizz.clt.crx.client.event.message.StoreTranslationMessage;

import com.google.gwt.chrome.crx.client.Chrome;
import com.google.gwt.chrome.crx.client.Console;
import com.google.gwt.chrome.crx.client.events.MessageEvent.Listener;

class MessageEventHandler implements Listener {

	private static final Console CONSOLE = Chrome.getExtension()
			.getBackgroundPage().getConsole();

	private ExtEventBus eventBus;

	public MessageEventHandler(ExtEventBus eventBus) {
		this.eventBus = eventBus;
	}

	/**
	 * Handles {@link com.google.gwt.chrome.crx.client.events.Message} from
	 * content scripts.
	 * 
	 * @param the
	 *            {@link com.google.gwt.chrome.crx.client.events.Message} to
	 *            handle
	 */
	public void onMessage(
			final com.google.gwt.chrome.crx.client.events.Message message) {
		CONSOLE.log("Message event handler receives a message");
		if (null != message) {
			processMessage(message);
		}
	}

	private void processMessage(
			final com.google.gwt.chrome.crx.client.events.Message message) {
		Message castedMessage = message.cast();
		if (SelectTextMessage.TYPE.equals(castedMessage.getType())) {
			eventBus.selectText((SelectTextMessage) castedMessage);
		}
		if (StoreTranslationMessage.TYPE.equals(castedMessage.getType())) {
			eventBus.storeTranslation((StoreTranslationMessage) castedMessage);
		}
		if (LoadWordsMessage.TYPE.equals(castedMessage.getType())) {
			eventBus.loadWords((LoadWordsMessage) castedMessage);
		}
	}

}