package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;
import org.fourgeeks.gha.webclient.client.message.GWTMessageServiceAsync;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

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
	
	public static class ModalInfoNotification extends VLayout implements ResizeHandler, GHAClosable{

		private int width = 300;
		
		public ModalInfoNotification(String title, String errorMessage) {
			super();
			GHAUiHelper.addGHAResizeHandler(this);
			
			setPosition(Positioning.ABSOLUTE);
			setWidth(width);
			setHeight("*");
			setLeft((Window.getClientWidth()/2)-(width/2));
			setTop(140);
			setDefaultLayoutAlign(Alignment.CENTER);
			
			setVisible(false);
			setAnimateTime(600);
			
			setShadowDepth(6);
			setShowShadow(true);
			
			
			// TITLE LAYOUT
			HLayout titleLayout = GHAUiHelper.verticalGraySeparatorLabel("40px","Informacion");
			
			// LABEL LAYOUT
			GHALabel errorText = new GHALabel(errorMessage);
			
			VLayout userdataLayout = new VLayout();
			userdataLayout.setHeight("*");
			userdataLayout.setWidth100();
			userdataLayout.setStyleName("sides-padding padding-top");
			userdataLayout.setBackgroundColor("#EOEOEO");
			userdataLayout.setAlign(Alignment.CENTER);
			userdataLayout.setMembersMargin(10);
			
			GHAButton acceptButton = new GHAButton("Aceptar", new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					close();
				}
			});
			
			userdataLayout.addMembers(errorText, acceptButton);
			
			addMembers(titleLayout,userdataLayout);
			
			show();
		}
		
		

		public ModalInfoNotification(int membersMargin) {
			super(membersMargin);
			// TODO Auto-generated constructor stub
		}

		public ModalInfoNotification(JavaScriptObject jsObj) {
			super(jsObj);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onResize(ResizeEvent event) {
			setLeft((Window.getClientWidth()/2)-(width/2));
		}

		@Override
		public void close() {
			// TODO Auto-generated method stub
			animateHide(AnimationEffect.FADE);
		}
		
		@Override
		public void show() {
			super.show();
			animateShow(AnimationEffect.FADE);
		}
	}
	
	
}
