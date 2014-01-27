package org.fourgeeks.gha.webclient.client.UI.alerts;

import java.util.List;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.modal.GHAAskDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.modal.GHAConfirmDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.modal.GHAHardErrorDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.modal.GHASoftErrorDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal.GHAFailureDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal.GHAInformationDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal.GHANewMessageDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal.GHAProgressDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal.GHASayDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal.GHASuccessDialog;
import org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal.GHAWarningDialog;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;
import org.fourgeeks.gha.webclient.client.message.GWTMessageServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
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
		if (ghaMessage.getType().getType().equals("SAY"))
			messageDialog = new GHASayDialog(GHAStrings.get("information"),
					ghaMessage.getText());
		else if (ghaMessage.getType().getType().equals("ERROR_HARD"))
			messageDialog = new GHAHardErrorDialog(
					GHAStrings.get("information"), ghaMessage.getText(),
					buttonOK);
		else if (ghaMessage.getType().getType().equals("ERROR_SOFT"))
			messageDialog = new GHASoftErrorDialog(
					GHAStrings.get("information"), ghaMessage.getText(),
					buttonOK);
		else if (ghaMessage.getType().getType().equals("WARNING"))
			messageDialog = new GHAWarningDialog(GHAStrings.get("information"),
					ghaMessage.getText(), buttonOK);
		else if (ghaMessage.getType().getType().equals("INFORMATION"))
			messageDialog = new GHAInformationDialog(
					GHAStrings.get("information"), ghaMessage.getText());
		else if (ghaMessage.getType().getType().equals("FAILURE"))
			messageDialog = new GHAFailureDialog(GHAStrings.get("information"),
					ghaMessage.getText());
		else if (ghaMessage.getType().getType().equals("SUCCESS"))
			messageDialog = new GHASuccessDialog(GHAStrings.get("information"),
					ghaMessage.getText());
		else if (ghaMessage.getType().getType().equals("PROCESSING"))
			messageDialog = new GHAProgressDialog(
					GHAStrings.get("information"), ghaMessage.getText());
		else if (ghaMessage.getType().getType().equals("NEW_MESSAGE"))
			messageDialog = new GHANewMessageDialog(
					GHAStrings.get("information"), ghaMessage.getText());
		messageDialog.show();
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
				String type = "INFORMATION";
				for (GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
					type = msg.getType().getType();
				}
				if (type.equals("SAY"))
					messageDialog = new GHASayDialog(GHAStrings
							.get("information"), builder.toString());
				else if (type.equals("ERROR_HARD"))
					messageDialog = new GHAHardErrorDialog(GHAStrings
							.get("information"), builder.toString(), buttonOK);
				else if (type.equals("ERROR_SOFT"))
					messageDialog = new GHASoftErrorDialog(GHAStrings
							.get("information"), builder.toString(), buttonOK);
				else if (type.equals("WARNING"))
					messageDialog = new GHAWarningDialog(GHAStrings
							.get("information"), builder.toString(), buttonOK);
				else if (type.equals("INFORMATION"))
					messageDialog = new GHAInformationDialog(GHAStrings
							.get("information"), builder.toString());
				else if (type.equals("FAILURE"))
					messageDialog = new GHAFailureDialog(GHAStrings
							.get("information"), builder.toString());
				else if (type.equals("SUCCESS"))
					messageDialog = new GHASuccessDialog(GHAStrings
							.get("information"), builder.toString());
				else if (type.equals("PROCESSING"))
					messageDialog = new GHAProgressDialog(GHAStrings
							.get("information"), builder.toString());
				else if (type.equals("NEW_MESSAGE"))
					messageDialog = new GHANewMessageDialog(GHAStrings
							.get("information"), builder.toString());
				messageDialog.show();
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
				if (result.getType().getType().equals("SAY"))
					messageDialog = new GHASayDialog(GHAStrings
							.get("information"), result.getText());
				else if (result.getType().getType().equals("ERROR_HARD"))
					messageDialog = new GHAHardErrorDialog(GHAStrings
							.get("information"), result.getText(), buttonOK);
				else if (result.getType().getType().equals("ERROR_SOFT"))
					messageDialog = new GHASoftErrorDialog(GHAStrings
							.get("information"), result.getText(), buttonOK);
				else if (result.getType().getType().equals("WARNING"))
					messageDialog = new GHAWarningDialog(GHAStrings
							.get("information"), result.getText(), buttonOK);
				else if (result.getType().getType().equals("INFORMATION"))
					messageDialog = new GHAInformationDialog(GHAStrings
							.get("information"), result.getText());
				else if (result.getType().getType().equals("FAILURE"))
					messageDialog = new GHAFailureDialog(GHAStrings
							.get("information"), result.getText());
				else if (result.getType().getType().equals("SUCCESS"))
					messageDialog = new GHASuccessDialog(GHAStrings
							.get("information"), result.getText());
				else if (result.getType().getType().equals("PROCESSING"))
					messageDialog = new GHAProgressDialog(GHAStrings
							.get("information"), result.getText());
				else if (result.getType().getType().equals("NEW_MESSAGE"))
					messageDialog = new GHANewMessageDialog(GHAStrings
							.get("information"), result.getText());
				else
					messageDialog = new GHAInformationDialog(GHAStrings
							.get("information"), result.getText());
				messageDialog.show();
			}
		});
	}

	/**
	 * shows a custom debug information alert to the user with the message and
	 * title specified
	 * 
	 * @param type
	 * 
	 * @param title
	 * @param message
	 * @param callback
	 */
	public static void alert(String type, String title, String message) {
		final Button buttonOK = new Button(GHAStrings.get("accept"));
		buttonOK.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				messageDialog.close();
			}
		});
		if (type.equals("SAY"))
			messageDialog = new GHASayDialog(title, message);
		else if (type.equals("ERROR_HARD"))
			messageDialog = new GHAHardErrorDialog(title, message, buttonOK);
		else if (type.equals("ERROR_SOFT"))
			messageDialog = new GHASoftErrorDialog(title, message, buttonOK);
		else if (type.equals("WARNING"))
			messageDialog = new GHAWarningDialog(title, message, buttonOK);
		else if (type.equals("INFORMATION"))
			messageDialog = new GHAInformationDialog(title, message);
		else if (type.equals("FAILURE"))
			messageDialog = new GHAFailureDialog(title, message);
		else if (type.equals("SUCCESS"))
			messageDialog = new GHASuccessDialog(title, message);
		else if (type.equals("PROCESSING"))
			messageDialog = new GHAProgressDialog(title, message);
		else if (type.equals("NEW_MESSAGE"))
			messageDialog = new GHANewMessageDialog(title, message);
		messageDialog.show();
	}

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
				if (buttonYesHandler != null)
					buttonYesHandler.onClick(event);
				messageDialog.close();
			}
		});
		Button buttonNo = new Button(GHAStrings.get("no"));
		buttonNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonNoHandler != null)
					buttonNoHandler.onClick(event);
				messageDialog.close();
			}
		});
		Button buttonCancel = new Button(GHAStrings.get("cancel"));
		buttonCancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonCancelHandler != null)
					buttonCancelHandler.onClick(event);
				messageDialog.close();
			}
		});
		messageDialog = new GHAAskDialog(title, message, buttonYes, buttonNo,
				buttonCancel);
		messageDialog.show();
	}

	/**
	 * @param title
	 * @param message
	 * @param callback
	 */
	public static void confirm(String title, String message,
			final BooleanCallback callback) {
		// SC.ask(title, message, callback);
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
		messageDialog = new GHAConfirmDialog(title, message, buttonYes,
				buttonNo);
		messageDialog.show();
	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	@Deprecated
	public static void inform(String key) {
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
				// SC.say(GHAStrings.get("information"), result.getText());
				messageDialog = new GHAInformationDialog(GHAStrings
						.get("information"), result.getText());
				messageDialog.show();
			}
		});

	}

	/**
	 * @param message
	 */
	@Deprecated
	public static void oldAlert(String message) {
		SC.say(GHAStrings.get("information"), message);
	}
}
