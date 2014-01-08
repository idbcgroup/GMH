package org.fourgeeks.gha.webclient.client.UI.alerts;

import java.util.List;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog.DialogType;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;
import org.fourgeeks.gha.webclient.client.message.GWTMessageServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class GHAAlertManager {
	private static final GWTMessageServiceAsync messageService = GWT
			.create(GWTMessageService.class);

	/**
	 * 
	 */
	public static GHADialog messageDialog = null;
	
	/**
	 * @param title
	 * @param message
	 * @param buttonYesHandler
	 * @param buttonNoHandler
	 * @param buttonCancelHandler
	 *            the cancelbutton handler, or null if you just want the dialog
	 *            to disapear
	 */
	public static void askYesNoCancel(final String title, String message,
			final ClickHandler buttonYesHandler,
			final ClickHandler buttonNoHandler,
			final ClickHandler buttonCancelHandler) {
		Button buttonYes = new Button(GHAStrings.get("yes"));
		buttonYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(buttonYesHandler!=null)
					buttonYesHandler.onClick(event);
				messageDialog.close();
			}
		});
		Button buttonNo = new Button(GHAStrings.get("no"));
		buttonNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(buttonNoHandler!=null)
					buttonNoHandler.onClick(event);
				messageDialog.close();		
			}
		});
		Button buttonCancel = new Button(GHAStrings.get("cancel"));
		buttonCancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(buttonCancelHandler!=null)
					buttonCancelHandler.onClick(event);
				messageDialog.close();
			}
		});
		messageDialog = new GHAModalDialog(DialogType.ASKYESNO,false,title, message,buttonYes,buttonNo,buttonCancel);
	}

	/**
	 * @param message
	 */
	@Deprecated
	public static void oldAlert(String message) {
		SC.say(GHAStrings.get("information"), message);
	}
	
	/**
	 * @param title
	 * @param message
	 * @param callback
	 */
	public static void confirm(String title, String message,
			final BooleanCallback callback) {
//		SC.ask(title, message, callback);
		Button buttonYes = new Button(GHAStrings.get("yes"));
		buttonYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callback.execute(true);
				messageDialog.close();
			}
		});
		Button buttonNo = new Button(GHAStrings.get("no"));
		buttonNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callback.execute(false);
				messageDialog.close();
			}
		});
		messageDialog = new GHAModalDialog(DialogType.CONFIRMATION, false,title, message,buttonYes,buttonNo);
	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	public static void info(String key) {
		final Button buttonOK = new Button(GHAStrings.get("accept"));
		buttonOK.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageDialog.close();
			}
		});
		messageService.find(key, new GHAAsyncCallback<GHAMessage>() {

			@Override
			public void onSuccess(GHAMessage result) {
//				SC.say(GHAStrings.get("information"), result.getText());
				messageDialog = new GHANonModalDialog(DialogType.INFORMATION, false,GHAStrings.get("information"), result.getText(), buttonOK);
			}
		});

	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	public static void alert(String key) {
		final Button buttonOK = new Button(GHAStrings.get("accept"));
		buttonOK.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageDialog.close();
			}
		});
		messageService.find(key, new GHAAsyncCallback<GHAMessage>() {

			@Override
			public void onSuccess(GHAMessage result) {
//				SC.say(GHAStrings.get("information"), result.getText());
				messageDialog = new GHANonModalDialog(DialogType.INFORMATION, false, GHAStrings.get("information"), result.getText(), buttonOK);
			}
		});

	}

	/**
	 * this method receives a list of keys to find the messages and then show
	 * them to the user
	 * 
	 * @param keys
	 */
	public static void alert(List<String> keys) {
		final Button buttonOK = new Button(GHAStrings.get("accept"));
		buttonOK.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageDialog.close();
			}
		});
		if (keys.isEmpty()) {
			alert("form-errors");
			return;
		}
		messageService.find(keys, new GHAAsyncCallback<List<GHAMessage>>() {

			@Override
			public void onSuccess(List<GHAMessage> result) {
				StringBuilder builder = new StringBuilder();
				for (GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
				}
//				SC.say(GHAStrings.get("information"), builder.toString());
				messageDialog = new GHANonModalDialog(DialogType.INFORMATION, false,GHAStrings.get("information"), builder.toString(), buttonOK);
			}
		});
	}

	/**
	 * @param ghaMessage
	 */
	public static void alert(GHAMessage ghaMessage) {
		final Button buttonOK = new Button(GHAStrings.get("accept"));
		buttonOK.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageDialog.close();
			}
		});
//		SC.say(ghaMessage.getText());
		messageDialog = new GHANonModalDialog(DialogType.INFORMATION, false, ghaMessage.getText(), buttonOK);
	}

	/**
	 * @param message
	 */
	@Deprecated
	public static void testDialog(String message) {
		messageDialog = new GHANonModalDialog(DialogType.INFORMATION,true, "Prueba de Say", "Esta es una prueba del untimedSay"+message, Dialog.OK, Dialog.CANCEL);
	}
}
