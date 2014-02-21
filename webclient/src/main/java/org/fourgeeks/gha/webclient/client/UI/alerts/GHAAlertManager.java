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
 * @author alacret
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

	private static GHADialog getMessageByType(GHAMessageType type, String title, String message) {
		GHADialog messageDialog;
		if(title.equals("")){
			if (type.getCode().equals("SAY"))
				messageDialog = new GHASayDialog(type,message);
			else if (type.getCode().equals("ERROR-HARD"))
				messageDialog = new GHAHardErrorDialog(type,message);
			else if (type.getCode().equals("ERROR-SOFT"))
				messageDialog = new GHASoftErrorDialog(type,message);
			else if (type.getCode().equals("WARNING"))
				messageDialog = new GHAWarningDialog(type,message);
			else if (type.getCode().equals("INFORMATION"))
				messageDialog = new GHAInformationDialog(type,message);
			else if (type.getCode().equals("FAILURE"))
				messageDialog = new GHAFailureDialog(type,message);
			else if (type.getCode().equals("SUCCESS"))
				messageDialog = new GHASuccessDialog(type,message);
			else if (type.getCode().equals("PROCESSING"))
				messageDialog = new GHAProgressDialog(type,message);
			else if (type.getCode().equals("NEW_MESSAGE"))
				messageDialog = new GHANewMessageDialog(type,message);
			else
				messageDialog = new GHASayDialog(type,message);
		}else{
			if (type.getCode().equals("SAY"))
				messageDialog = new GHASayDialog(type,title,message);
			else if (type.getCode().equals("ERROR-HARD"))
				messageDialog = new GHAHardErrorDialog(type,title,message);
			else if (type.getCode().equals("ERROR-SOFT"))
				messageDialog = new GHASoftErrorDialog(type,title,message);
			else if (type.getCode().equals("WARNING"))
				messageDialog = new GHAWarningDialog(type,title,message);
			else if (type.getCode().equals("INFORMATION"))
				messageDialog = new GHAInformationDialog(type,title,message);
			else if (type.getCode().equals("FAILURE"))
				messageDialog = new GHAFailureDialog(type,title,message);
			else if (type.getCode().equals("SUCCESS"))
				messageDialog = new GHASuccessDialog(type,title,message);
			else if (type.getCode().equals("PROCESSING"))
				messageDialog = new GHAProgressDialog(type,title,message);
			else if (type.getCode().equals("NEW_MESSAGE"))
				messageDialog = new GHANewMessageDialog(type,title,message);
			else
				messageDialog = new GHASayDialog(type,title,message);
		}
		return messageDialog;
	}

	private static GHAMessageType createMessageTypeByName(String type) {
		GHAMessageType messageType;
		final int secsToMills = 1000;
		if(type.equals("SAY"))
			messageType = new GHAMessageType("SAY", 4*secsToMills, false);
		else if(type.equals("CONFIRMATION"))
			messageType = new GHAMessageType("CONFIRMATION", 0, true);
		else if(type.equals("ASKYESNO"))
			messageType = new GHAMessageType("ASKYESNO", 0, true);
		else if(type.equals("ERROR-HARD"))
			messageType = new GHAMessageType("ERROR-HARD", 0, true);
		else if(type.equals("ERROR-SOFT"))
			messageType = new GHAMessageType("ERROR-SOFT", 0, false);
		else if(type.equals("WARNING"))
			messageType = new GHAMessageType("WARNING", 4*secsToMills, false);
		else if(type.equals("INFORMATION"))
			messageType = new GHAMessageType("INFORMATION", 4, false);
		else if(type.equals("FAILURE"))
			messageType = new GHAMessageType("FAILURE", 4*secsToMills, false);
		else if(type.equals("SUCCESS"))
			messageType = new GHAMessageType("SUCCESS", 4*secsToMills, false);
		else if(type.equals("PROCESSING"))
			messageType = new GHAMessageType("PROCESSING", 0, false);
		else if(type.equals("NEW_MESSAGE"))
			messageType = new GHAMessageType("NEW_MESSAGE", 0, false);
		else
			messageType = new GHAMessageType("SAY", 4*secsToMills, false);
		return messageType;
	}

	/**
	 * @param ghaMessage
	 */
	public static void alert(GHAMessage ghaMessage) {
		if (canShowNewMessage()) {
			GHADialog messageDialog = null;
			messageDialog = getMessageByType(ghaMessage.getType(), "", ghaMessage.getText());
			messageDialog.openWindow();
		}else{
			messageQueue.add(ghaMessage);
		}
	}



	/**
	 * this method receives a list of keys to find the messages and then show
	 * them to the user
	 * 
	 * @param keys
	 */
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
				GHAMessageType type = new GHAMessageType();
				for (final GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
					type = msg.getType();
				}
				if (canShowNewMessage()) {
					messageDialog = getMessageByType(type, "", builder.toString());
					messageDialog.openWindow();
				}else{
					messageQueue.add(new GHAMessage(result.get(0).getLang(),
							"waited-multiple-message-"+messageQueue.size(),
							builder.toString(),
							type));
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
					messageDialog = getMessageByType(result.getType(),"",result.getText());
					messageDialog.openWindow();
				}else{
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
		GHAMessageType messageType;
		messageType = createMessageTypeByName(type);
		messageDialog = getMessageByType(messageType,title,message);
		if (canShowNewMessage()) {
			messageDialog.openWindow();
		}else{
			messageQueue.add(new GHAMessage(LanguageEnum.ES,
					"waited-message-"+messageQueue.size(),
					message,
					createMessageTypeByName(type)));
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

		final GHADialog messageDialog = new GHAAskDialog(ghaMessage.getType(),ghaMessage.getText(),
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

		messageDialog.openWindow();
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
				GHAMessageType type = new GHAMessageType();
				for (final GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
					type = msg.getType();
				}
				final GHADialog messageDialog = new GHAAskDialog(type,builder
						.toString(), buttonYes, buttonNo, buttonCancel);

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

				messageDialog.openWindow();
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
				final GHADialog messageDialog = new GHAAskDialog(result.getType(), result
						.getText(), buttonYes, buttonNo, buttonCancel);

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

				messageDialog.openWindow();
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

		final GHADialog messageDialog = new GHAAskDialog(createMessageTypeByName("ASKYESNO"),title, message,
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
		messageDialog.openWindow();
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
		final GHADialog messageDialog = new GHAConfirmDialog(ghaMessage.getType(),
				ghaMessage.getText(), buttonYes, buttonNo);

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
		messageDialog.openWindow();
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
				GHAMessageType type = new GHAMessageType();
				for (final GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
					type = msg.getType();
				}
				final GHADialog messageDialog = new GHAConfirmDialog(type,builder
						.toString(), buttonYes, buttonNo);

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
				messageDialog.openWindow();
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
				final GHADialog messageDialog = new GHAConfirmDialog(result.getType(),result
						.getText(), buttonYes, buttonNo);

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
				messageDialog.openWindow();
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

		final GHADialog messageDialog = new GHAConfirmDialog(createMessageTypeByName("CONFIRM"),title, message,
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
		messageDialog.openWindow();
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

	/**
	 * @return the openMessagesCounter
	 */
	public static int getOpenMessagesCounter() {
		return openMessagesCounter;
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
		if(messageQueue.size()>0){
			final GHAMessage message = messageQueue.poll();
			final GHAMessageType messageType = message.getType();
			if(!messageType.equals("ASKYESNO") && !messageType.equals("CONFIRMATION"))
				alert(message);
		}

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
