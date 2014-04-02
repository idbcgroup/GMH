package org.fourgeeks.gha.webclient.client.UI.pmewindows;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.modal.GHAAskDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.modal.GHAConfirmDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.modal.GHAHardErrorDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.modal.GHAMinorErrorDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal.GHAAdvanceDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal.GHADefaultDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal.GHAInformationDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal.GHANewMessageDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal.GHASuccessDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal.GHAValidationErrorDialog;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal.GHAWarningDialog;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;
import org.fourgeeks.gha.webclient.client.message.GWTMessageServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * PME- Procesador de mensajes de error. Clase que se encarga de mostrar los mensajes emergentes en la aplicación.
 * 
 * @author jfuentes
 */
public class GHAErrorMessageProcessor {

	/* Servicio de mensajes*/
	private static final GWTMessageServiceAsync messageService = GWT
			.create(GWTMessageService.class);
	/* El nuemero Maximo de mensajes (tamaño de la lista de mensajes).*/
	private static final int MAX_MESSAGES = 3;
	/* La lista de mensajes que maneja las posiciones que se pueden mostrar en la aplicacion.*/
	private static boolean[] messagesPositionsOpen = new boolean[MAX_MESSAGES];
	/* Contador de mensajes abiertos.*/
	private static int openMessagesCounter = 0;
	/* La cola de espera de los mensajes.*/
	private static Queue<GHAMessage> waitingMessagesQueue = new LinkedList<GHAMessage>();

	/**
	 *  Agrega un nuevo mensaje al contador de mensajes.
	 */
	public static void addNewMessageToCounter() {
		GHAErrorMessageProcessor.openMessagesCounter++;
	}

	/**
	 *  Metodo para mostrar una VEM (Ventana Emergente de Mensajes).
	 * @param ghaMessage El mensaje a mostrar (la clase GHAMessage).
	 */
	public static void alert(GHAMessage ghaMessage) {
		if (canShowNewMessage()) {
			GHADialog messageDialog = null;
			messageDialog = getMessageWindow(ghaMessage, "");
			messageDialog.show();
		} else {
			// Window.alert("Queuing a message: type:"+ghaMessage.getType().getCode()+" Text:"+ghaMessage.getText());
			waitingMessagesQueue.add(ghaMessage);
		}
	}

	/**
	 * Metodo para mostrar una VEM (Ventana Emergente de Mensajes). Con una lista de claves, invoca al servicio de mensajes para traer los
	 * mensajes de la base de datos y los muestra en una unica ventana.
	 * @param keys Las claves de los mensajes en la base de datos.
	 * 
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
				GHAMessageType type = createMessageTypeByName("VEM-DEFAULT");
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
					waitingMessagesQueue.add(new GHAMessage(result.get(0).getLanguage(),
							"waited-multiple-message-" + waitingMessagesQueue.size(),
							builder.toString(), "", type, -1));
				}
			}
		});
	}

	/**
	 * Metodo para mostrar una VEM (Ventana Emergente de Mensajes). Con una clave, invoca al servicio de mensajes para traer ese
	 * mensaje de la base de datos y lo muestra de acuerdo a su tipo.
	 * 
	 * @param key La clave del mensaje a mostrar.
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
					waitingMessagesQueue.add(result);
				}
			}
		});
	}

	/**
	 * Metodo para mostrar una VEM (Ventana Emergente de Mensajes) que no este registrada en la base de datos.
	 * 
	 * @param type El tipo de la ventana a mostrar.
	 * @param title El titulo de la ventana a mostrar.
	 * @param message El mensaje de la ventana a mostrar.
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
			waitingMessagesQueue.add(new GHAMessage(LanguageEnum.ES, "waited-message-"
					+ waitingMessagesQueue.size(), message, "",
					createMessageTypeByName(type), -1));
		}
	}

	/**
	 * Metodo para mostrar una VEC-ACTION (Ventana Emergente de Confirmacion).
	 * @param ghaMessage El mensaje a mostrar (la clase GHAMessage).
	 * @param buttonYesHandler El handler del boton "si".
	 * @param buttonNoHandler El handler del boton "no".
	 * @param buttonCancelHandler El handler del boton "Cancelar".
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
	 * Metodo para mostrar una VEC-ACTION (Ventana Emergente de Confirmacion).
	 * @param keys Las claves de los mensajes a mostrar.
	 * @param buttonYesHandler El handler del boton "si".
	 * @param buttonNoHandler El handler del boton "no".
	 * @param buttonCancelHandler El handler del boton "Cancelar".
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
				GHAMessageType type = createMessageTypeByName("VEM-DEFAULT");
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
	 * Metodo para mostrar una VEC-ACTION (Ventana Emergente de Confirmacion).
	 * @param key La clave del mensaje a mostrar.
	 * @param buttonYesHandler El handler del boton "si".
	 * @param buttonNoHandler El handler del boton "no".
	 * @param buttonCancelHandler El handler del boton "Cancelar".
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
	 * Metodo para mostrar una VEC-ACTION (Ventana Emergente de Confirmacion).
	 * @param title El titulo del mensaje a mostrar.
	 * @param message El mensaje a mostrar.
	 * @param buttonYesHandler El handler del boton "si".
	 * @param buttonNoHandler El handler del boton "no".
	 * @param buttonCancelHandler El handler del boton "Cancelar".
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
	 * @return true si es posible mostrar una nueva Ventana Emergente.
	 */
	public static boolean canShowNewMessage() {
		return openMessagesCounter < MAX_MESSAGES;
	}

	/**
	 * Metodo para mostrar una VEC-USER-DECISION (Ventana Emergente de Confirmacion).
	 * @param ghaMessage El mensaje a mostrar (la clase GHAMessage).
	 * @param callback El handler booleano que maneja la decision del usuario.
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
	 * Metodo para mostrar una VEC-USER-DECISION (Ventana Emergente de Confirmacion).
	 * @param keys Las claves de los mensajes a mostrar.
	 * @param callback El handler booleano que maneja la decision del usuario.
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
				GHAMessageType type = createMessageTypeByName("VEM-DEFAULT");
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
	 * Metodo para mostrar una VEC-USER-DECISION (Ventana Emergente de Confirmacion).
	 * @param key La clave del mensaje a mostrar.
	 * @param callback El handler booleano que maneja la decision del usuario.
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
	 * Metodo para mostrar una VEC-USER-DECISION (Ventana Emergente de Confirmacion).
	 * @param title El titulo del mensaje a mostrar.
	 * @param message El mensaje a mostrar.
	 * @param callback El handler booleano que maneja la decision del usuario.
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
		if (type.equals("VEM-DEFAULT"))
			messageType = new GHAMessageType("VEM-DEFAULT", 4, false);
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
			messageType = new GHAMessageType("VEM-DEFAULT", 4, false);
		return messageType;
	}

	/**
	 * @return Una posicion libre donde pueda ser colocado el mensaje en la cola de mensajes
	 * en la aplicacion. Retorna -1 si no hay espacio.
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
	 * Funcion que crea los mensajes dependiendo de su tipo.
	 * @return una instancia de la subclase del mensaje a mostrar dependiendo el tipo de mensaje recibido.
	 */
	private static GHADialog getMessageWindow(GHAMessage message, String title) {
		GHADialog messageDialog;
		if (title.equals("")) {
			if (message.getMessageType().getCode().equals("VEM-DEFAULT"))
				messageDialog = new GHADefaultDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEC-ERROR"))
				messageDialog = new GHAHardErrorDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEC-MINOR-ERROR"))
				messageDialog = new GHAMinorErrorDialog(message.getMessageType(),
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
				messageDialog = new GHAValidationErrorDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-RESULTS"))
				messageDialog = new GHASuccessDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-ADVANCE"))
				messageDialog = new GHAAdvanceDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-NOTIFICATION"))
				messageDialog = new GHANewMessageDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else
				messageDialog = new GHADefaultDialog(message.getMessageType(),
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
		} else {
			if (message.getMessageType().getCode().equals("VEM-DEFAULT"))
				messageDialog = new GHADefaultDialog(message.getMessageType(), title,
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
				messageDialog = new GHAMinorErrorDialog(message.getMessageType(),
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
				messageDialog = new GHAValidationErrorDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-RESULTS"))
				messageDialog = new GHASuccessDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
			else if (message.getMessageType().getCode().equals("VEM-ADVANCE"))
				messageDialog = new GHAAdvanceDialog(message.getMessageType(), title,
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
				messageDialog = new GHADefaultDialog(message.getMessageType(), title,
						message.getMessageText() + ". " + message.getMessageIndications()
						+ "<br>Código del Mensaje: "
						+ message.getMessageCode(), message.getTimeShowing());
		}
		return messageDialog;
	}

	/**
	 * @return El contador de mensajes abiertos.
	 */
	public static int getOpenMessagesCounter() {
		return openMessagesCounter;
	}

	/**
	 * Funcion ejecuta las acciones cuando se cierran los mensajes.
	 */
	public static void messageClosedActions() {
		if (!waitingMessagesQueue.isEmpty()) {
			final GHAMessage message = waitingMessagesQueue.poll();
			// Window.alert("Dequeing a message: type:"+message.getType().getCode()+" Text:"+message.getText());
			final GHAMessageType messageType = message.getMessageType();
			if (!messageType.getCode().equals("VEC-ACTION")
					&& !messageType.getCode().equals("VEC-USER-DECISION"))
				alert(message);
		}
	}

	/**
	 * Como se mostraban los alert antes.
	 * @param message
	 */
	@Deprecated
	public static void oldAlert(String message) {
		SC.say(GHAStrings.get("information"), message);
	}

	/**
	 * Quita un mensaje al contador de mensajes.
	 */
	public static void removeOpenMessageFromCounter() {
		if (openMessagesCounter > 0)
			GHAErrorMessageProcessor.openMessagesCounter--;
	}

	/**
	 * @param openMessagesCounter
	 *            the openMessagesCounter to set
	 */
	public static void setOpenMessagesCounter(int openMessagesCounter) {
		GHAErrorMessageProcessor.openMessagesCounter = openMessagesCounter;
	}

	/**
	 * Ocupa o Desocupa la posicion enviada por parametros en la cola de mensajes (cambia la bandera de esa posicion).
	 * @param position La posicion en la cola de mensajes.
	 */
	public static void toggleMessagePosition(int position) {
		if (messagesPositionsOpen[position] == false) {
			messagesPositionsOpen[position] = true;
		} else {
			messagesPositionsOpen[position] = false;
		}
	}

}
