/**
 * 
 */
package name.webdizz.clt.crx.client.presenter;

import name.webdizz.clt.crx.client.Alert;
import name.webdizz.clt.crx.client.ExtConfiguration;

import com.google.gwt.chrome.crx.client.Chrome;
import com.google.gwt.chrome.crx.client.Console;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.event.EventBus;
import com.mvp4g.client.presenter.BasePresenter;

/**
 * @author Izzet_Mustafa
 * 
 */
@Presenter(view = MonitorPresenter.MonitorPresenterView.class)
public class MonitorPresenter extends
		BasePresenter<MonitorPresenter.IMonitorPresenterView, EventBus> {

	interface IMonitorPresenterView {
	}

	@Inject
	private ExtConfiguration configuration;

	private Console console = Chrome.getExtension().getBackgroundPage()
			.getConsole();

	public static class MonitorPresenterView implements IMonitorPresenterView {
	}

	public void onError(String message) {
		if (configuration.isDebug())
			Alert.info("Error: " + message);
		if (!configuration.isDebug())
			console.log("Error: " + message);
	}

	public void onInfo(String message) {
		if (configuration.isDebug())
			Alert.info("Info: " + message);
		if (!configuration.isDebug())
			console.log("Info: " + message);
	}

	public void onTrace(String message) {
		if (configuration.isDebug())
			Alert.info("Trace: " + message);
		if (!configuration.isDebug())
			console.log("Trace: " + message);
	}
}
