package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;
import org.fourgeeks.gha.webclient.client.message.GWTMessageServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

public class GHANotification {
	private static final GWTMessageServiceAsync messageService = GWT
			.create(GWTMessageService.class);

	/*
	 * public GHANotification() { setPosition(Positioning.ABSOLUTE);
	 * setBottom(Window.getClientHeight()-20); setSize("280px", "*");
	 * setBackgroundColor("#FFFFFF"); setBorder("1px solid #E0E0E0");
	 * setVisibility(Visibility.HIDDEN);
	 * 
	 * HLayout topUserLayout = new HLayout(); topUserLayout.setSize("100%",
	 * "25px"); topUserLayout.setBackgroundColor("#666666");
	 * 
	 * addMember(GHAUiHelper.verticalGraySeparator("25px")); }
	 */

	/**
	 * @param message
	 */
	public static void alert(String message) {
		SC.say("Información", message);
	}

	/**
	 * @param title
	 * @param message
	 * @param callback
	 */
	public static void confirm(String title, String message,
			BooleanCallback callback) {
		SC.ask(title, message, callback);
	}

	private static void alert(List<String> keys,
			GHAAsyncCallback<List<GHAMessage>> callback) {
		messageService.find(keys, callback);
	}

	private static void alertMessage(String key,
			GHAAsyncCallback<GHAMessage> callback) {
		messageService.find(key, callback);
	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	public static void alertMessage(String key) {
		alertMessage(key, new GHAAsyncCallback<GHAMessage>() {

			@Override
			public void onSuccess(GHAMessage result) {
				alert(result.getText());
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
		alert(keys, new GHAAsyncCallback<List<GHAMessage>>() {

			@Override
			public void onSuccess(List<GHAMessage> result) {
				StringBuilder builder = new StringBuilder();
				for (GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
				}
				alert(builder.toString());
			}
		});
	}

	/**
	 * @param ghaMessage
	 */
	public static void alert(GHAMessage ghaMessage) {
		alert(ghaMessage.getText());
	}
	
//	public static class ModalInfoNotification extends VLayout implements ResizeHandler{
//
//		public ModalInfoNotification() {
//			super();
//			setWidth();
//			setLeft(-5);
//			setBackgroundColor("#E0E0E0");
//			setStyleName("sides-padding padding-top box");
//			setAlign(Alignment.CENTER);
//			setVisibility(Visibility.HIDDEN);
//			setAnimateTime(600);
//			GHAUiHelper.addGHAResizeHandler(this);
//		}
//
//		public ModalInfoNotification(int membersMargin) {
//			super(membersMargin);
//			// TODO Auto-generated constructor stub
//		}
//
//		public ModalInfoNotification(JavaScriptObject jsObj) {
//			super(jsObj);
//			// TODO Auto-generated constructor stub
//		}
//
//		@Override
//		public void onResize(ResizeEvent event) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
	
	
}
