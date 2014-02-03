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

	private static final int MAX_MESSAGES = 10;
	private static final GWTMessageServiceAsync messageService = GWT
			.create(GWTMessageService.class);
	private static int openMessagesCounter=0;
	private static int totalOpenMessagesOffset=0;

	/**
	 * @param openMessageOffset 
	 */
	public static void addOpenMessageOffset(int openMessageOffset) {
		GHAAlertManager.totalOpenMessagesOffset += openMessageOffset;
	}

	/**
	 * 
	 */
	public static void addOpenMessageToCounter() {
		GHAAlertManager.openMessagesCounter++;
	}

	/**
	 * @param ghaMessage
	 */
	public static void alert(GHAMessage ghaMessage) {
		if(canShowNewMessage()){
			GHADialog messageDialog = null;

			if (ghaMessage.getType().getTypeName().equals("SAY"))
				messageDialog = new GHASayDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("ERROR_HARD"))
				messageDialog = new GHAHardErrorDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("ERROR_SOFT"))
				messageDialog = new GHASoftErrorDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("WARNING"))
				messageDialog = new GHAWarningDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("INFORMATION"))
				messageDialog = new GHAInformationDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("FAILURE"))
				messageDialog = new GHAFailureDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("SUCCESS"))
				messageDialog = new GHASuccessDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("PROCESSING"))
				messageDialog = new GHAProgressDialog(ghaMessage.getText());
			else if (ghaMessage.getType().getTypeName().equals("NEW_MESSAGE"))
				messageDialog = new GHANewMessageDialog(ghaMessage.getText());
			else
				messageDialog = new GHASayDialog(ghaMessage.getText());

			messageDialog.show();
		}
	}

	/**
	 * this method receives a list of keys to find the messages and then show
	 * them to the user
	 * 
	 * @param keys
	 */
	public static void alert(List<String> keys) {
		if(canShowNewMessage()){
			if (keys.isEmpty()) {
				alert("form-errors");
				return;
			}
			messageService.find(keys, new GHAAsyncCallback<List<GHAMessage>>() {

				@Override
				public void onSuccess(List<GHAMessage> result) {
					GHADialog messageDialog = null;
					StringBuilder builder = new StringBuilder();
					String type = "SAY";
					for (GHAMessage msg : result) {
						builder.append(msg.getText()).append("<br>");
						type = msg.getType().getTypeName();
					}
					if (type.equals("SAY"))
						messageDialog = new GHASayDialog(builder.toString());
					else if (type.equals("ERROR_HARD"))
						messageDialog = new GHAHardErrorDialog(builder.toString());
					else if (type.equals("ERROR_SOFT"))
						messageDialog = new GHASoftErrorDialog(builder.toString());
					else if (type.equals("WARNING"))
						messageDialog = new GHAWarningDialog(builder.toString());
					else if (type.equals("INFORMATION"))
						messageDialog = new GHAInformationDialog(builder.toString());
					else if (type.equals("FAILURE"))
						messageDialog = new GHAFailureDialog(builder.toString());
					else if (type.equals("SUCCESS"))
						messageDialog = new GHASuccessDialog(builder.toString());
					else if (type.equals("PROCESSING"))
						messageDialog = new GHAProgressDialog(builder.toString());
					else if (type.equals("NEW_MESSAGE"))
						messageDialog = new GHANewMessageDialog(builder.toString());
					else
						messageDialog = new GHASayDialog(builder.toString());
					messageDialog.show();
				}
			});
		}
	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	public static void alert(String key) {
		if(canShowNewMessage()){
			messageService.find(key, new GHAAsyncCallback<GHAMessage>() {

				@Override
				public void onSuccess(GHAMessage result) {
					GHADialog messageDialog = null;
					if (result.getType().getTypeName().equals("SAY"))
						messageDialog = new GHASayDialog(result.getText());
					else if (result.getType().getTypeName().equals("ERROR_HARD"))
						messageDialog = new GHAHardErrorDialog(result.getText());
					else if (result.getType().getTypeName().equals("ERROR_SOFT"))
						messageDialog = new GHASoftErrorDialog(result.getText());
					else if (result.getType().getTypeName().equals("WARNING"))
						messageDialog = new GHAWarningDialog(result.getText());
					else if (result.getType().getTypeName().equals("INFORMATION"))
						messageDialog = new GHAInformationDialog(result.getText());
					else if (result.getType().getTypeName().equals("FAILURE"))
						messageDialog = new GHAFailureDialog(result.getText());
					else if (result.getType().getTypeName().equals("SUCCESS"))
						messageDialog = new GHASuccessDialog(result.getText());
					else if (result.getType().getTypeName().equals("PROCESSING"))
						messageDialog = new GHAProgressDialog(result.getText());
					else if (result.getType().getTypeName().equals("NEW_MESSAGE"))
						messageDialog = new GHANewMessageDialog(result.getText());
					else
						messageDialog = new GHASayDialog(result.getText());
					messageDialog.show();
				}
			});
		}
	}

	/**
	 * shows a custom debug information alert to the user with the message and
	 * title specified
	 * 
	 * @param type
	 * 
	 * @param title
	 * @param message
	 */
	public static void alert(String type, String title, String message) {
		if(canShowNewMessage()){
			GHADialog messageDialog = null;
			if (type.equals("SAY"))
				messageDialog = new GHASayDialog(title, message);
			else if (type.equals("ERROR_HARD"))
				messageDialog = new GHAHardErrorDialog(title, message);
			else if (type.equals("ERROR_SOFT"))
				messageDialog = new GHASoftErrorDialog(title, message);
			else if (type.equals("WARNING"))
				messageDialog = new GHAWarningDialog(title, message);
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
			else
				messageDialog = new GHASayDialog(title, message);

			messageDialog.show();
		}
	}

	/**
	 * @param ghaMessage 
	 * @param buttonYesHandler
	 * @param buttonNoHandler
	 * @param buttonCancelHandler
	 *            the cancelbutton handler, or null if you just want the dialog
	 *            to disapear
	 */
	public static void askYesNoCancel(GHAMessage ghaMessage,
			final ClickHandler buttonYesHandler,
			final ClickHandler buttonNoHandler,
			final ClickHandler buttonCancelHandler) {

		final Button buttonYes = new Button(GHAStrings.get("yes"));
		final Button buttonNo = new Button(GHAStrings.get("no"));
		final Button buttonCancel = new Button(GHAStrings.get("cancel"));
		final GHADialog messageDialog = new GHAAskDialog(ghaMessage.getText(), buttonYes, buttonNo, buttonCancel);

		buttonYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonYesHandler != null)
					buttonYesHandler.onClick(event);
				messageDialog.close();
			}
		});
		buttonNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonNoHandler != null)
					buttonNoHandler.onClick(event);
				messageDialog.close();
			}
		});
		buttonCancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonCancelHandler != null)
					buttonCancelHandler.onClick(event);
				messageDialog.close();
			}
		});

		messageDialog.show();
	}

	/**
	 * @param keys 
	 * @param buttonYesHandler
	 * @param buttonNoHandler
	 * @param buttonCancelHandler
	 *            the cancelbutton handler, or null if you just want the dialog
	 *            to disapear
	 */
	public static void askYesNoCancel(List<String> keys,
			final ClickHandler buttonYesHandler,
			final ClickHandler buttonNoHandler,
			final ClickHandler buttonCancelHandler) {

		if (keys.isEmpty()) {
			alert("form-errors");
			return;
		}
		messageService.find(keys, new GHAAsyncCallback<List<GHAMessage>>() {
			@Override
			public void onSuccess(List<GHAMessage> result) {
				final Button buttonYes = new Button(GHAStrings.get("yes"));
				final Button buttonNo = new Button(GHAStrings.get("no"));
				final Button buttonCancel = new Button(GHAStrings.get("cancel"));
				StringBuilder builder = new StringBuilder();
				for (GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
				}
				final GHADialog messageDialog = new GHAAskDialog(builder.toString(), buttonYes, buttonNo, buttonCancel);

				buttonYes.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (buttonYesHandler != null)
							buttonYesHandler.onClick(event);
						messageDialog.close();
					}
				});
				buttonNo.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (buttonNoHandler != null)
							buttonNoHandler.onClick(event);
						messageDialog.close();
					}
				});
				buttonCancel.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (buttonCancelHandler != null)
							buttonCancelHandler.onClick(event);
						messageDialog.close();
					}
				});

				messageDialog.show();
			}
		});
	}
	/**
	 * @param key 
	 * @param buttonYesHandler
	 * @param buttonNoHandler
	 * @param buttonCancelHandler
	 *            the cancelbutton handler, or null if you just want the dialog
	 *            to disapear
	 */
	public static void askYesNoCancel(String key,
			final ClickHandler buttonYesHandler,
			final ClickHandler buttonNoHandler,
			final ClickHandler buttonCancelHandler) {

		messageService.find(key, new GHAAsyncCallback<GHAMessage>() {
			@Override
			public void onSuccess(GHAMessage result) {
				final Button buttonYes = new Button(GHAStrings.get("yes"));
				final Button buttonNo = new Button(GHAStrings.get("no"));
				final Button buttonCancel = new Button(GHAStrings.get("cancel"));
				final GHADialog messageDialog = new GHAAskDialog(result.getText(), buttonYes, buttonNo, buttonCancel);

				buttonYes.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (buttonYesHandler != null)
							buttonYesHandler.onClick(event);
						messageDialog.close();
					}
				});
				buttonNo.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (buttonNoHandler != null)
							buttonNoHandler.onClick(event);
						messageDialog.close();
					}
				});
				buttonCancel.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (buttonCancelHandler != null)
							buttonCancelHandler.onClick(event);
						messageDialog.close();
					}
				});

				messageDialog.show();
			}
		});
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
		Button buttonNo = new Button(GHAStrings.get("no"));
		Button buttonCancel = new Button(GHAStrings.get("cancel"));

		final GHADialog messageDialog = new GHAAskDialog(title, message, buttonYes, buttonNo,
				buttonCancel);
		buttonYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonYesHandler != null)
					buttonYesHandler.onClick(event);
				messageDialog.close();
			}
		});
		buttonNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonNoHandler != null)
					buttonNoHandler.onClick(event);
				messageDialog.close();
			}
		});
		buttonCancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (buttonCancelHandler != null)
					buttonCancelHandler.onClick(event);
				messageDialog.close();
			}
		});
		messageDialog.show();
	}

	/**
	 * @return true if its possible to open a new IT Message.
	 */
	public static boolean canShowNewMessage(){
		return openMessagesCounter < MAX_MESSAGES;
	}

	/**
	 * @param ghaMessage 
	 * @param callback
	 */
	public static void confirm(GHAMessage ghaMessage, final BooleanCallback callback) {
		Button buttonYes = new Button(GHAStrings.get("yes"));
		Button buttonNo = new Button(GHAStrings.get("no"));
		final GHADialog messageDialog = new GHAConfirmDialog(ghaMessage.getText(), buttonYes, buttonNo);

		buttonYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callback.execute(true);
				messageDialog.close();
			}
		});
		buttonNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callback.execute(false);
				messageDialog.close();
			}
		});
		messageDialog.show();				
	}

	/**
	 * @param keys 
	 * @param callback
	 */
	public static void confirm(List<String> keys, final BooleanCallback callback) {
		if (keys.isEmpty()) {
			alert("form-errors");
			return;
		}
		messageService.find(keys, new GHAAsyncCallback<List<GHAMessage>>() {

			@Override
			public void onSuccess(List<GHAMessage> result) {
				Button buttonYes = new Button(GHAStrings.get("yes"));
				Button buttonNo = new Button(GHAStrings.get("no"));
				StringBuilder builder = new StringBuilder();
				for (GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
				}
				final GHADialog messageDialog = new GHAConfirmDialog(builder.toString(), buttonYes, buttonNo);

				buttonYes.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						callback.execute(true);
						messageDialog.close();
					}
				});
				buttonNo.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						callback.execute(false);
						messageDialog.close();
					}
				});
				messageDialog.show();				
			}
		});
	}

	/**
	 * @param key 
	 * @param callback
	 */
	public static void confirm(String key, final BooleanCallback callback) {

		messageService.find(key, new GHAAsyncCallback<GHAMessage>() {

			@Override
			public void onSuccess(GHAMessage result) {
				Button buttonYes = new Button(GHAStrings.get("yes"));
				Button buttonNo = new Button(GHAStrings.get("no"));
				final GHADialog messageDialog = new GHAConfirmDialog(result.getText(), buttonYes,buttonNo);

				buttonYes.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						callback.execute(true);
						messageDialog.close();
					}
				});
				buttonNo.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						callback.execute(false);
						messageDialog.close();
					}
				});
				messageDialog.show();				
			}
		});
	}
	/**
	 * @param title
	 * @param message
	 * @param callback
	 */
	public static void confirm(String title, String message,
			final BooleanCallback callback) {
		Button buttonYes = new Button(GHAStrings.get("yes"));
		Button buttonNo = new Button(GHAStrings.get("no"));

		final GHADialog messageDialog = new GHAConfirmDialog(title, message, buttonYes,
				buttonNo);

		buttonYes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callback.execute(true);
				messageDialog.close();
			}
		});
		buttonNo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callback.execute(false);
				messageDialog.close();
			}
		});
		messageDialog.show();
	}

	/**
	 * @return the openMessagesCounter
	 */
	public static int getOpenMessagesCounter() {
		return openMessagesCounter;
	}

	public static int getOpenMessagesOffset() {
		return totalOpenMessagesOffset;
	}

	/**
	 * @param message
	 */
	@Deprecated
	public static void oldAlert(String message) {
		SC.say(GHAStrings.get("information"), message);
	}

	/**
	 * 
	 */
	public static void removeOpenMessageFromCounter() {
		if(openMessagesCounter>0)
			GHAAlertManager.openMessagesCounter--;
	}

	/**
	 * @param openMessagesCounter the openMessagesCounter to set
	 */
	public static void setOpenMessagesCounter(int openMessagesCounter) {
		GHAAlertManager.openMessagesCounter = openMessagesCounter;
	}

	/**
	 * @param openMessagesOffset
	 */
	public static void setOpenMessagesOffset(int openMessagesOffset) {
		GHAAlertManager.totalOpenMessagesOffset = openMessagesOffset;
	}

}
