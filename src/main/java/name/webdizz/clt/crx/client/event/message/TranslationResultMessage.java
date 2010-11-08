/**
 * 
 */
package name.webdizz.clt.crx.client.event.message;

import name.webdizz.clt.crx.client.translation.TranslationResult;

/**
 * @author Izzet_Mustafa
 * 
 */
public class TranslationResultMessage extends Message {

	protected TranslationResultMessage() {
	}

	public static final String TYPE = "TranslationResultMessage";

	public final native TranslationResult getTranslation() /*-{
		return this.translation;
	}-*/;

	public static final native TranslationResultMessage create(TranslationResult translationResult)/*-{
		return {translation: translationResult, type:'TranslationResultMessage'};
	}-*/;

}
