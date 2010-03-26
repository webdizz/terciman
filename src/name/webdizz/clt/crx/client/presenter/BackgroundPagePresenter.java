/**
 * 
 */
package name.webdizz.clt.crx.client.presenter;

import name.webdizz.clt.crx.client.ActivationKeysHolder;
import name.webdizz.clt.crx.client.ExtConfiguration;
import name.webdizz.clt.crx.client.ExtEventBus;
import name.webdizz.clt.crx.client.event.message.SelectTextMessage;
import name.webdizz.clt.crx.client.event.message.ShowTranslatedTextMessage;
import name.webdizz.clt.crx.client.event.message.TranslateTextMessage;
import name.webdizz.clt.crx.client.view.BackgroundPageView;

import com.google.gwt.chrome.crx.client.Tabs;
import com.google.gwt.chrome.crx.client.Tabs.OnDetectLanguageCallback;
import com.google.gwt.chrome.crx.client.Tabs.OnTabCallback;
import com.google.gwt.chrome.crx.client.Tabs.Tab;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

/**
 * @author Izzet_Mustafayev
 * 
 */
@Presenter(view = BackgroundPageView.class)
public class BackgroundPagePresenter extends
		BasePresenter<BackgroundPagePresenter.IBackgroundPageView, ExtEventBus> {

	/**
	 * Holds reference to the last received {@link SelectTextMessage} to be able
	 * to display translation in the appropriate place.
	 */
	private SelectTextMessage selectTextMessage;

	private ExtConfiguration configuration;

	public BackgroundPagePresenter() {
		configuration = new ExtConfiguration();
	}

	/**
	 * The interface for background page.
	 * 
	 * @author Izzet_Mustafayev
	 * 
	 */
	public interface IBackgroundPageView {

	}

	public void onStart() {
		// detect language
		detectLanguage();
	}

	private void detectLanguage() {
		if ("".equals(configuration.getDestLanguage())) {
			Tabs.detectLanguage(new OnDetectLanguageCallback() {
				public void onDetect(String languageCode) {
					eventBus
							.trace("BackgroundPagePresenter.detectLanguage(): Detected lang - "
									+ languageCode);
					configuration.setDestLanguage(languageCode);
				}
			});
		}
	}

	/**
	 * Is called by {@link ExtEventBus} when a "selectText" event triggered.
	 * 
	 * @param message
	 *            the {@link SelectTextMessage} contains text to translate
	 */
	public void onSelectText(final SelectTextMessage message) {
		if (isTranslatable(message.getKeys())) {
			eventBus.trace("BackgroundPagePresenter.onSelectText() :"
					+ message.getText() + " should be translated");
			TranslateTextMessage transTextMessage;
			transTextMessage = TranslateTextMessage.create(message.getText());
			eventBus.translateText(transTextMessage);
			selectTextMessage = message;
		}
	}

	/**
	 * Send message to the extension script to display translated text within a
	 * window.
	 * 
	 * @param widget
	 *            a {@link Widget} to show as a translation
	 */
	public void onShowTranslatedText(final Widget widget) {
		Tabs.getSelected(new OnTabCallback() {
			public void onTab(Tab tab) {
				ShowTranslatedTextMessage message;
				String asString = JsonUtils.escapeValue(widget.toString());
				message = ShowTranslatedTextMessage.create(selectTextMessage,
						asString);
				Tabs.sendRequest(tab.getId(), message);
			}
		});
	}

	/**
	 * Perform validation whether message is able to be translated.
	 * 
	 * @param keys
	 *            the array of pressed keyboard keys
	 * @return true if right keyboard key was pressed
	 */
	private boolean isTranslatable(final String[] keys) {
		boolean result = false;
		for (String key : keys) {
			if (ActivationKeysHolder.CTRL.equals(key)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
