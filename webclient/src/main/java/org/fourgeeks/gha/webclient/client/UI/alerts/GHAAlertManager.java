package org.fourgeeks.gha.webclient.client.UI.alerts;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
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
 * @author jfuentes
 * 
 */
public class GHAAlertManager {

	private static final int MAX_MESSAGES = 3;
	private static boolean[] messagesPositionsOpen = new boolean[MAX_MESSAGES];

	private static final GWTMessageServiceAsync messageService = GWT
			.create(GWTMessageService.class);
	private static int openMessagesCounter = 0;
	private static Queue<GHAMessage> messageQueue = new LinkedList<GHAMessage>();

	/**
	 * 
	 */
	public static void addNewMessageToCounter() {
		GHAAlertManager.openMessagesCounter++;
	}

	/**
	 * @param ghaMessage
	 */
	public static void alert(GHAMessage ghaMessage) {
		if (canShowNewMessage()) {
			GHADialog messageDialog = null;
			messageDialog = getMessageWindow(ghaMessage, "");
			messageDialog.show();
		} else {
			// Window.alert("Queuing a message: type:"+ghaMessage.getType().getCode()+" Text:"+ghaMessage.getText());
			messageQueue.add(ghaMessage);
		}
	}

	/**
	 * this method receives a list of keys to find the messages and then show
	 * them to the user
	 * 
	 * @param keys
	 */
	@Deprecated
	public static void alert(List<String> keys) {
		if (keys.isEmpty()) {
			alert("form-errors");
			return;
		}
		messageService.find(keys, new GHAAsyncCallback<List<GHAMessage>>() {

			@Override
			public void onSuccess(List<GHAMessage> result) {
				GHADialog messageDialog = null;
				final StringBuilder builder = new StringBuilder();
				GHAMessageType type = createMessageTypeByName("SAY");
				for (final GHAMessage msg : result) {
					builder.append(msg.getMessageText()).append("<br>");
					type = msg.getMessageType();
				}
				if (canShowNewMessage()) {
					messageDialog = getMessageWindow(
							new GHAMessage(result.get(0).getLanguage(),
									"multiple-message", builder.toString(), "",
									type, -1), "");
					messageDialog.show();
				} else {
					// Window.alert("Queuing a message: type:"+type.getCode()+" Text:"+builder.toString());
					messageQueue.add(new GHAMessage(result.get(0).getLanguage(),
							"waited-multiple-message-" + messageQueue.size(),
							builder.toString(), "", type, -1));
				}
			}
		});
	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	public static void alert(String key) {
		messageService.find(key, new GHAAsyncCallback<GHAMessage>() {

			@Override
			public void onSuccess(GHAMessage result) {
				if (canShowNewMessage()) {
					GHADialog messageDialog = null;
					messageDialog = getMessageWindow(result, "");
					messageDialog.show();
				} else {
					// Window.alert("Queuing a message: type:"+result.getType().getCode()+" Text:"+result.getText());
					messageQueue.add(result);
				}
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
	 */
	public static void alert(String type, String title, String message) {
		GHADialog messageDialog = null;
		if (canShowNewMessage()) {
			messageDialog = getMessageWindow(new GHAMessage(LanguageEnum.ES,
					"server-error-message", message, "",
					createMessageTypeByName(type), -1), title);
			messageDialog.show();
		} else {
			// Window.alert("Queuing a message: type:"+type+" Text:"+message);
			messageQueue.add(new GHAMessage(LanguageEnum.ES, "waited-message-"
					+ messageQueue.size(), message, "",
					createMessageTypeByName(type), -1));
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

		final GHADialog messageDialog = new GHAAskDialog(ghaMessage.getMessageType(),
				ghaMessage.getMessageText(), ghaMessage.getTimeShowing(), buttonYes,
				buttonNo, buttonCancel);

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
	@Deprecated
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
				final StringBuilder builder = new StringBuilder();
				GHAMessageType type = createMessageTypeByName("SAY");
				for (final GHAMessage msg : result) {
					builder.append(msg.getMessageText()).append("<br>");
					type = msg.getMessageType();
				}
				final GHADialog messageDialog = new GHAAskDialog(type, builder
						.toString(), -1, buttonYes, buttonNo, buttonCancel);

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
				final GHADialog messageDialog = new GHAAskDialog(result
						.getMessageType(), result.getMessageText(), result.getTimeShowing(),
						buttonYes, buttonNo, buttonCancel);
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
		final Button buttonYes = new Button(GHAStrings.get("yes"));
		final Button buttonNo = new Button(GHAStrings.get("no"));
		final Button buttonCancel = new Button(GHAStrings.get("cancel"));

		final GHADialog messageDialog = new GHAAskDialog(
				createMessageTypeByName("VEC-ACTION"), title, message, -1,
				buttonYes, buttonNo, buttonCancel);
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
	public static boolean canShowNewMessage() {
		return openMessagesCounter < MAX_MESSAGES;
	}

	/**
	 * @param ghaMessage
	 * @param callback
	 */
	public static void confirm(GHAMessage ghaMessage,
			final BooleanCallback callback) {
		final Button buttonYes = new Button(GHAStrings.get("yes"));
		final Button buttonNo = new Button(GHAStrings.get("no"));
		final GHADialog messageDialog = new GHAConfirmDialog(
				ghaMessage.getMessageType(), ghaMessage.getMessageText(),
				ghaMessage.getTimeShowing(), buttonYes, buttonNo);

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
	@Deprecated
	public static void confirm(List<String> keys, final BooleanCallback callback) {
		if (keys.isEmpty()) {
			alert("form-errors");
			return;
		}
		messageService.find(keys, new GHAAsyncCallback<List<GHAMessage>>() {

			@Override
			public void onSuccess(List<GHAMessage> result) {
				final Button buttonYes = new Button(GHAStrings.get("yes"));
				final Button buttonNo = new Button(GHAStrings.get("no"));
				final StringBuilder builder = new StringBuilder();
				GHAMessageType type = createMessageTypeByName("SAY");
				for (final GHAMessage msg : result) {
					builder.append(msg.getMessageText()).append("<br>");
					type = msg.getMessageType();
				}
				final GHADialog messageDialog = new GHAConfirmDialog(type,
						builder.toString(), -1, buttonYes, buttonNo);

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
				final Button buttonYes = new Button(GHAStrings.get("yes"));
				final Button buttonNo = new Button(GHAStrings.get("no"));
				final GHADialog messageDialog = new GHAConfirmDialog(result
						.getMessageType(), result.getMessageText(), result.getTimeShowing(),
						buttonYes, buttonNo);
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
		final Button buttonYes = new Button(GHAStrings.get("yes"));
		final Button buttonNo = new Button(GHAStrings.get("no"));

		final GHADialog messageDialog = new GHAConfirmDialog(
				createMessageTypeByName("CONFIRM"), title, message, -1,
				buttonYes, buttonNo);

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

	private static GHAMessageType createMessageTypeByName(String type) {
		GHAMessageType messageType;
		if (type.equals("SAY"))
			messageType = new GHAMessageType("SAY", 4, false);
		else if (type.equals("VEC-USER-DECISION"))
			messageType = new GHAMessageType("VEC-USER-DECISION", 0, true);
		else if (type.equals("VEC-ACTION"))
			messageType = new GHAMessageType("VEC-ACTION", 0, true);
		else if (type.equals("VEC-ERROR"))
			messageType = new GHAMessageType("VEC-ERROR", 0, true);
		else if (type.equals("VEC-MINOR-ERROR"))
			messageType = new GHAMessageType("VEC-MINOR-ERROR", 0, false);
		else if (type.equals("VEM-WARNING"))
			messageType = new GHAMessageType("VEM-WARNING", 4, false);
		else if (type.equals("VEM-INFORMATION"))
			messageType = new GHAMessageType("VEM-INFORMATION", 4, false);
		else if (type.equals("VEM-VALIDATION"))
			messageType = new GHAMessageType("VEM-VALIDATION", 4, false);
		else if (type.equals("VEM-RESULTS"))
			messageType = new GHAMessageType("VEM-RESULTS", 4, false);
		else if (type.equals("VEM-ADVANCE"))
			messageType = new GHAMessageType("VEM-ADVANCE", 0, false);
		else if (type.equals("VEM-NOTIFICATION"))
			messageType = new GHAMessageType("VEM-NOTIFICATION", 0, false);
		else
			messageType = new GHAMessageType("SAY", 4, false);
		return messageType;
	}

	/**
	 * @return a position to set the message. Returns -1 if there is no space.
	 */
	public static int getFreeMessagePosition() {
		int ret = -1;
		for (int i = 0; i < messagesPositionsOpen.length; i++) {
			if (!messagesPositionsOpen[i]) {
				ret = i;
				break;
			}
		}
		return ret;
	}

	private static GHADialog getMessageWindow(GHAMessage message, String title) {
		GHADialog messageDialog;
		if (title.equals("")) {
			if (message.getMessageType().getCode().equals("SAY"))
				messageDialog = new GHASayDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEC-ERROR"))
				messageDialog = new GHAHardErrorDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEC-MINOR-ERROR"))
				messageDialog = new GHASoftErrorDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-WARNING"))
				messageDialog = new GHAWarningDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-INFORMATION"))
				messageDialog = new GHAInformationDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-VALIDATION"))
				messageDialog = new GHAFailureDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-RESULTS"))
				messageDialog = new GHASuccessDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-ADVANCE"))
				messageDialog = new GHAProgressDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-NOTIFICATION"))
				messageDialog = new GHANewMessageDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else
				messageDialog = new GHASayDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
		} else {
			if (message.getMessageType().getCode().equals("SAY"))
				messageDialog = new GHASayDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEC-ERROR"))
				messageDialog = new GHAHardErrorDialog(message.getMessageType(),
						title, message.getMessageText() + ". "
								+ message.getMessageIndications()
								+ "<br>Código del Mensaje: "
								+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEC-MINOR-ERROR"))
				messageDialog = new GHASoftErrorDialog(message.getMessageType(),
						title, message.getMessageText() + ". "
								+ message.getMessageIndications()
								+ "<br>Código del Mensaje: "
								+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-WARNING"))
				messageDialog = new GHAWarningDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-INFORMATION"))
				messageDialog = new GHAInformationDialog(message.getMessageType(),
						title, message.getMessageText() + ". "
								+ message.getMessageIndications()
								+ "<br>Código del Mensaje: "
								+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-VALIDATION"))
				messageDialog = new GHAFailureDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-RESULTS"))
				messageDialog = new GHASuccessDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-ADVANCE"))
				messageDialog = new GHAProgressDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-NOTIFICATION"))
				messageDialog = new GHANewMessageDialog(message.getMessageType(),
						title, message.getMessageText() + ". "
								+ message.getMessageIndications()
								+ "<br>Código del Mensaje: "
								+ message.getMessageCode(), message.getTimeShowing());
			else
				messageDialog = new GHASayDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
		}
		return messageDialog;
	}

	/**
	 * @return the openMessagesCounter
	 */
	public static int getOpenMessagesCounter() {
		return openMessagesCounter;
	}

	/**
	 * 
	 */
	public static void messageClosedActions() {
		if (!messageQueue.isEmpty()) {
			final GHAMessage message = messageQueue.poll();
			// Window.alert("Dequeing a message: type:"+message.getType().getCode()+" Text:"+message.getText());
			final GHAMessageType messageType = message.getMessageType();
			if (!messageType.getCode().equals("VEC-ACTION")
					&& !messageType.getCode().equals("VEC-USER-DECISION"))
				alert(message);
		}
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
		if (openMessagesCounter > 0)
			GHAAlertManager.openMessagesCounter--;
	}

	/**
	 * @param openMessagesCounter
	 *            the openMessagesCounter to set
	 */
	public static void setOpenMessagesCounter(int openMessagesCounter) {
		GHAAlertManager.openMessagesCounter = openMessagesCounter;
	}

	/**
	 * @param position
	 */
	public static void toggleMessagePosition(int position) {
		if (messagesPositionsOpen[position] == false) {
			messagesPositionsOpen[position] = true;
		} else {
			messagesPositionsOpen[position] = false;
		}
	}

	// {
	// for (int i = 0; i < messagesPositionsOpen.length; i++) {
	// messagesPositionsOpen[i] = false;
	// }
	// }

}
